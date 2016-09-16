package com.seckill.service;

import java.util.List;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExcution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepteKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

/**
 * 业务接口：站在使用者的角度
 * 三个方面：方法定义粒度，参数，返回类型要友好
 * @author phantomfjh
 *
 */
public interface SeckillService {
	/**
	 * 查询秒杀记录
	 * @return
	 */
	List<Seckill> getSeckillList();
	/**
	 * 查询单个秒杀
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 暴露秒杀接口地址(秒杀开启后输出否则输出系统时间和倒计时)
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution excuteSeckill(long seckillId,long userPhone,String md5)
			throws SeckillException,RepteKillException,SeckillCloseException;

}
