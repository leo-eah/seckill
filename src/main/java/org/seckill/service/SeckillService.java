package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillColseException;
import org.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务接口
 * 站在使用者角度设计接口
 */
public interface SeckillService {
    /**
     * 展示列表页
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 单个
     * @return
     */
    Seckill getById(long id);

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckill);

    /**
     * \执行秒杀操作
     * @param seckill
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckill, long userPhone, String md5)
    throws RepeatKillException,SeckillColseException,SeckillException
    ;
    SeckillExecution executeSeckillProcedure(long seckill, long userPhone, String md5)
            ;

}
