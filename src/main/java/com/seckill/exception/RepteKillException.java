package com.seckill.exception;

/**
 * 重复秒杀异常(运行异常)
 * @author phantomfjh
 *
 */
public class RepteKillException extends SeckillException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepteKillException(String message) {
		super(message);
	}
	
	public RepteKillException(String message, Throwable cause) {
		super(message, cause);
	}


}
