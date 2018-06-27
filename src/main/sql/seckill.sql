Delimiter $$
-- 秒杀执行存储过程
CREATE  PROCEDURE  `seckill`.`execute_seckill`(
  in v_seckill_id bigint ,in v_phone bigint ,in v_kill_time TIMESTAMP  ,out r_result int
) BEGIN
  DECLARE  insert_count  int DEFAULT  0;
            start TRANSACTION ;
            INSERT ignore  into success_killed(
            seckill_id ,user_phone ,create_time
            )VALUES (
              v_seckill_id,v_phone,v_kill_time
            );
            SELECT  ROW_COUNT () into insert_count;
            if(insert_count=0) THEN
                ROLLBACK ;
                set r_result=-1;
            elseif(insert_count <0) THEN
                ROLLBACK ;
                set r_result =-2;
            ELSE
                UPDATE  seckill SET number=number-1
            WHERE  seckill_id=v_seckill_id
                AND end_time>v_kill_time
                AND start_time<v_kill_time
                and  NUMBER >0;
            SELECT  ROW_COUNT () into insert_count;
            if(insert_count =0) THEN
              ROLLBACK ;
              set r_result=0;
            elseIf(insert_count<0) THEN
              ROLLBACK ;
                  set r_result =-2;
              ELSE
                COMMIT ;
                set r_result=1;
              end if;
            end if;
        END ;
$$
Delimiter ;
set @r_result=-3;
call execute_seckill(1003,135123123123,now(),@r_result);
SELECT @r_result;
