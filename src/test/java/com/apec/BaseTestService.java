package com.apec;

import com.apec.druid.mapper.IdSegmentMapper;
import com.apec.druid.model.ZookeeperProfile;
import com.apec.druid.service.impl.MysqlIdleafServiceImpl;
import com.apec.druid.service.impl.ZookeeperIdleafServiceImpl;
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
    public void getIdByZookeeper(){
        String id1 = zookeeperIdleafService.getId("order");
        String id2 = zookeeperIdleafService.getId("user");

    }
}
