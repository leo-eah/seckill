package org.seckill.exception;

import org.seckill.dto.SeckillExecution;

/**
 * 秒杀关闭异常
 */
public class SeckillColseException extends SeckillException {
    public SeckillColseException(String message) {
        super(message);
    }

    public SeckillColseException(String message, Throwable cause) {
        super(message, cause);
    }
}
