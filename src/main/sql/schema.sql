-- 数据库初始化脚本
-- 创建数据库
CREATE  DATABASE seckill;
-- 使用数据库
CREATE TABLE seckill(
  'seckill_id' bigint not NULL AUTO_INCREMENT comment '商品库存id',
  'name' VARCHAR (120) not NULL  comment '商品名称',
  'number' int  not NULL  comment '库存数量',
  'start_time' TIMESTAMP  not NULL  comment '秒杀开启时间',
  'end_time' TIMESTAMP  not NULL  comment '秒杀结束时间',
  'create_time' TIMESTAMP  not NULL  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  PRIMARY key(seckill_id),
  key idx_start_time (start_time),
  key idx_end_time (end_time),
  key idx_create_time (create_time),

)ENGINE= INNODB AUTO_INCREMENT =1000 DEFAULT  charset = utf8 comment = '秒杀库存表'

--初始化数据
INSERT  into
seckill(NAME ,number,start_time,end_time)
VALUES
('1000元秒杀iphone 6',100,'2018-11-01 00:00:00','2018-11-02 00:00:00'),
('500元秒杀小米 6',200,'2018-11-01 00:00:00','2018-11-02 00:00:00'),
('300元秒杀iphone 7',300,'2018-11-01 00:00:00','2018-11-02 00:00:00'),
('200元秒杀iphone 4',400,'2018-11-01 00:00:00','2018-11-02 00:00:00'),
('1000元秒杀iphone4',100,'2018-11-01 00:00:00','2018-11-02 00:00:00'),

--秒杀成功明细表
CREATE  TABLE success_killed{
  'seckill_id' bigint not NULL  comment '秒杀商品id',
  'user_phone' bigint not NULL  comment '用户手机号',
  'state' tinyint not NULL DEFAULT -1 comment '状态标识 -1 无效 0 成功，1失败',
  'create_time' TIMESTAMP  not NULL  DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  PRIMARY  key  (seckill_id,user_phone)
    key idx_create_time (create_time),

}ENGINE= INNODB  DEFAULT  charset = utf8 comment = '秒杀成功明细表'

--链接数据库控制台
mysql  -uroot -p
