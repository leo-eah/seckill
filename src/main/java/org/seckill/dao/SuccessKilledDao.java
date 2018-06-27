package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;

import java.util.Date;
import java.util.List;

public interface SuccessKilledDao {
    /**
     *插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return插入行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);
    /**
     * 根据id
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);



}

