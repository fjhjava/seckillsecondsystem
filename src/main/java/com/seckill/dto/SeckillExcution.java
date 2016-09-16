package com.seckill.dto;

import com.seckill.entity.*;
import com.seckill.enums.SeckillState;

/**
 * 封装秒杀执行后的结果
 * @author phantomfjh
 *
 */
public class SeckillExcution {

	private long seckillId;

	// 秒杀结果
	private int state;

	// 状态描述信息
	private String stateInfo;

	// 秒杀成功对象
	private SuccessKilled successKilled;

	public SeckillExcution(long seckillId, SeckillState seckillState, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = seckillState.getState();
		this.stateInfo = seckillState.getStateInfo();
		this.successKilled = successKilled;
	}

	public SeckillExcution(long seckillId, SeckillState seckillState) {
		this.seckillId = seckillId;
		this.state = seckillState.getState();
		this.stateInfo = seckillState.getStateInfo();
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	

}
