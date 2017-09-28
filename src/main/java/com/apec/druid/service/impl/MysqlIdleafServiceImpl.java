package com.apec.druid.service.impl;

import com.apec.druid.mapper.IdSegmentMapper;
import com.apec.druid.model.IdSegment;
import com.apec.druid.service.IdleafService;


import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xk
 * @createTime 2017/9/27 0027 下午 2:14
 * @description 通过数据库实现id的自增长
 */
public class MysqlIdleafServiceImpl implements IdleafService {

    IdSegmentMapper idSegmentMapper;
    //两段缓存id
    private volatile IdSegment[] idSegment = new IdSegment[2];
    //当前的生成的id
    private AtomicLong currentId;
    //同步锁
    private static ReentrantLock lock = new ReentrantLock();
    //设置当前segment索引，默认为0
    private volatile int index = 0;
    //设置同步加载，异步加载标志
    private boolean isSynLoad;
    //业务类型
    private String bizTag;
    // 创建线程池
    private ExecutorService taskExecutor;
    // 创建加载任务
    FutureTask<Boolean> asynLoadSegmentTask = null;

    //初始化
    public void init(String bizTag, boolean isSynSwitch) {
        this.bizTag = bizTag;
        this.isSynLoad = isSynSwitch;
        if (null == taskExecutor) {
            taskExecutor = Executors.newSingleThreadExecutor();
        }
        initIdSegment(index);
        //初始化
        currentId = new AtomicLong(idSegment[index].getMinId());
    }

    /**
     * 根据切换标志位获取加载idSegment数组索引
     */
    private int loadIndex() {
        if (index == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private int switchIndex() {
        if (index == 0) {
            index = 1;
        } else {
            index = 0;
        }
        return index;
    }

    @Override
    public String getId() {
        Long id = null;
        try {
            if (isSynLoad()) {
                //同步切换方式获取id
                id = synGetId();
            } else {
                //异步切换方式获取id
                id = asynGetId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(id);
    }

    @Override
    public String getId(String name) {
        return null;
    }

    /**
     * 初始化segment
     *
     * @param index
     */
    private void initIdSegment(int index) {
        try {
            idSegment[index] = updateIdSegment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新segment
     *
     * @return
     */
    private IdSegment updateIdSegment() {
        //初始化一个idSegment
        final IdSegment currentSegment = idSegmentMapper.queryByBizTag(this.getBizTag());
        if (null == currentSegment) {
            throw new RuntimeException(this.getBizTag() + "该业务类型未初始化");
        }
        Long newMaxId = currentSegment.getMaxId() + currentSegment.getpStep();
        //更新数据库
        currentSegment.setMaxId(newMaxId);
        currentSegment.setCurrentUpdateTime(new Date());
        if (idSegmentMapper.updateByByBizTag(currentSegment) == 1) {
            //更新成功
            IdSegment idSegment = new IdSegment();
            idSegment.setpStep(currentSegment.getpStep());
            idSegment.setMaxId(newMaxId);
            return idSegment;
        } else {
            //递归，直到成功
            return updateIdSegment();
        }
    }

    /**
     * 异步切换获取id
     *
     * @return
     */
    public Long asynGetId() {
        if (idSegment[index].getSwitchPoint().equals(currentId.longValue()) ||
                idSegment[index].getMaxId().equals(currentId.longValue())) {
            try {
                lock.lock();
                if (idSegment[index].getSwitchPoint().equals(currentId.longValue())) {
                    //异步加载下一个segment
                    asynLoadSegmentTask = new FutureTask<Boolean>(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            initIdSegment(loadIndex());
                            return true;
                        }
                    });
                    taskExecutor.submit(asynLoadSegmentTask);
                }
                if (idSegment[index].getMaxId().equals(currentId.longValue())) {
                    boolean loadResult = false;
                    loadResult = asynLoadSegmentTask.get();
                    if (loadResult) {
                       switchIndex();
                    }
                    currentId = new AtomicLong(idSegment[index].getMinId());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                //执行中断，强制切换
                switchIndex();
                currentId = new AtomicLong(idSegment[index].getMinId());
            } catch (ExecutionException e) {
                e.printStackTrace();
                //线程执行失败

            } finally {
                lock.unlock();
            }
        }
        return currentId.incrementAndGet();
    }

    /**
     * 同步切换获取id
     *
     * @return
     */
    public Long synGetId() {
        if (idSegment[index].getSwitchPoint().equals(currentId.longValue()) ||
                idSegment[index].getMaxId().equals(currentId.longValue())) {
            try {
                lock.lock();
                if (idSegment[index].getSwitchPoint().equals(currentId.longValue())) {
                    //加载下一个segment
                    initIdSegment(loadIndex());
                }
                if (idSegment[index].getMaxId().equals(currentId.longValue())) {
                    //切换下一个segment
                    switchIndex();
                    currentId = new AtomicLong(idSegment[index].getMinId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return currentId.incrementAndGet();
    }

    public static ReentrantLock getLock() {
        return lock;
    }

    public static void setLock(ReentrantLock lock) {
        MysqlIdleafServiceImpl.lock = lock;
    }

    public boolean isSynLoad() {
        return isSynLoad;
    }

    public void setSynLoad(boolean synLoad) {
        isSynLoad = synLoad;
    }

    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag;
    }

    public IdSegmentMapper getIdSegmentMapper() {
        return idSegmentMapper;
    }

    public void setIdSegmentMapper(IdSegmentMapper idSegmentMapper) {
        this.idSegmentMapper = idSegmentMapper;
    }
}
