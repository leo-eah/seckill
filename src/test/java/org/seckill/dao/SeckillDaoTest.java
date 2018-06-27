package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合,junit 启动时加载springIOc容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
//   注入dao实现累依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void queryById() throws Exception {
        long id= 1000;
        Seckill seckill =seckillDao.queryById(id);
        System.out.println(seckill.getName());
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,1000);
        seckills.forEach(n-> System.out.println(n.getName()));
    }
    @Test
    public void reduceNumber() throws Exception {
        Date killTime  = new Date();
        int updateCount= seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount="+updateCount);
    }
}