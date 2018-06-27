package org.seckill.service.impl;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillColseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private SeckillDao seckillDao;
    
    @Autowired
    private RedisDao redisDao;
    //用于混淆MD5
    private final String slat = "asdasdadsaSDGFSWAGADFHFDJQWER2WQT#!@$#%";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long id) {
        return seckillDao.queryById(id);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        //
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill==null){
            seckill = seckillDao.queryById(seckillId);
            if (null == seckill) {
                return new Exposer(false, seckillId);
            }else {
                redisDao.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串，不可逆
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckill, long userPhone, String md5) throws RepeatKillException, SeckillColseException, SeckillException {
        if (md5 == null || !md5.equals(getMd5(seckill))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀
        Date nowTime = new Date();
        try {
            int insertCount = successKilledDao.insertSuccessKilled(seckill, userPhone);
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            }
            else {
                int update = seckillDao.reduceNumber(seckill, nowTime);
                if (update <= 0) {
                    throw new SeckillColseException("seckill is closed");
                }
               else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckill, 1);
                    return new SeckillExecution(seckill, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillColseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error" + e.getMessage());
        }
    }

    @Override
    public SeckillExecution executeSeckillProcedure(long seckill, long userPhone, String md5) {
        if (md5==null||!md5.equals(getMd5(seckill))){
            return new SeckillExecution(seckill,SeckillStateEnum.DATA_rewrite);
        }
        Date killTime =  new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("seckillId",seckill);
        map.put("phone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);
        try {
            seckillDao.killByProcedure(map);
            Integer result = MapUtils.getInteger(map, "result", -2);
            if (result==1){
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckill,userPhone);
                return  new SeckillExecution(seckill,SeckillStateEnum.SUCCESS);

            }else {
                return  new SeckillExecution(seckill,SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return  new SeckillExecution(seckill,SeckillStateEnum.INNER_ERROR);
        }
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
