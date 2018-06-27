package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeckillDao {
    /**
     *
     * @param seckillId
     * @param killTime
     * @return 标识更新行数
     */
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);

    Seckill queryById(long seckillId);

   List<Seckill> queryAll(@Param("offset") int offset, @Param("limit")int limit);
   void killByProcedure(Map<String,Object> parameterMap);
}
