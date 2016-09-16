package com.seckill.dao;

import com.seckill.entity.SuccessKilled;

public interface SuccessKillDao {
	/**
	 * 插入购买明细,可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return 表述影响的行数
	 */
	int insertSuccessKilled(long seckillId,long userPhone);
	
	/**
	 * 查询successKilled并携带秒杀商品对象
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(long seckillId);

}
