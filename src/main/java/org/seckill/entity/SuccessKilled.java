package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
    private long seckillId;
    private long userphone;
    private short  state    ;
    private Date createTime;

    //多对一
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserphone() {
        return userphone;
    }

    public void setUserphone(long userphone) {
        this.userphone = userphone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
