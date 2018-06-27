package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private  SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id =1000;
        Seckill seckill   = seckillService.getById(id);
        logger.info("seckill={}"+seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id =1000;
        Exposer exposer =  seckillService.exportSeckillUrl(id);
        logger.info("exposer={}"+exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        long id =1000;
        long phone  =17702436740L;
        String md5=null;
        SeckillExecution seckillExecution= seckillService.executeSeckill(id,phone,md5);
        logger.info("seckillExecution={}"+seckillExecution);


    }
    @Test
    public  void executeSeckillProcedure(){
        long seckillId=1001;
        long phone =1231231231;
        Exposer exposer =seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            String md5=exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }

}