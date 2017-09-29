package com.apec;

import com.apec.druid.mapper.IdSegmentMapper;
import com.apec.druid.model.ZookeeperProfile;
import com.apec.druid.service.impl.MysqlIdleafServiceImpl;
import com.apec.druid.service.impl.ZookeeperIdleafServiceImpl;
import com.apec.druid.util.SnowFlakeKeyGen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * @author Administrator
 * @createTime 2017/9/27 0027 下午 6:21
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseTestService {
    @Autowired
    IdSegmentMapper idSegmentMapper;

    @Autowired
    ZookeeperProfile zookeeperProfile;

    @Autowired
    ZookeeperIdleafServiceImpl zookeeperIdleafService;

    MysqlIdleafServiceImpl idleafService;

    @Before
    public void getIdleafService() {
        idleafService = new MysqlIdleafServiceImpl();
        idleafService.setIdSegmentMapper(idSegmentMapper);
        idleafService.init("order", true);
    }

    @Test
    public void getId() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(idleafService.getId());
        }
        System.out.println(idleafService.getId());
        for (int i = 0; i < 9; i++) {
            System.out.println(idleafService.getId());
        }
        System.out.println(idleafService.getId());
        for (int i = 0; i < 9; i++) {
            System.out.println(idleafService.getId());
        }
        System.out.println(idleafService.getId());
        for (int i = 0; i < 9; i++) {
            System.out.println(idleafService.getId());
        }
        System.out.println(idleafService.getId());
    }

    @Test
    public void getZookeeperConfig() {
        System.out.println(zookeeperProfile.toString());
    }

    @Test
    public void getIdByZookeeper() throws Exception {
        String id1 = zookeeperIdleafService.getId("order");
        String id2 = zookeeperIdleafService.getId("user");
        String id3 = zookeeperIdleafService.getId("test");
        String id4 = zookeeperIdleafService.getId("test");
        Long id5 = zookeeperIdleafService.getId("test", "");
        Long id6 = zookeeperIdleafService.getId("test", "");
        Long id7 = zookeeperIdleafService.getId("test", "");
    }

    @Test
    public void getSnowGenId() {
        SnowFlakeKeyGen snowFlakeKeyGen = new SnowFlakeKeyGen(zookeeperIdleafService.getId("user", ""));
        String id1 = snowFlakeKeyGen.nextStringId();

        String id2 = snowFlakeKeyGen.nextStringId();
    }
}
