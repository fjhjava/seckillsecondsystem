package com.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKillDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExcution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillState;
import com.seckill.exception.RepteKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//注入service依赖
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKillDao successKillDao;
	// md5盐值用于混淆md5
	private final String slat = "asdn##asdnask***&*^*BJXA~!";

	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 2);
	}

	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		// 转化特定字符串
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepteKillException, SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		// 执行秒杀逻辑
		// 减库存
		try {
			Date nowTime = new Date();
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if (updateCount <= 0) {
				// 秒杀结束
				throw new SeckillCloseException("seckill is closed!");
			} else {
				// 记录购买行为
				int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
				if (insertCount <= 0) {
					throw new RepteKillException("seckill repeat!");
				} else {
					// 秒杀成功
					SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId);
					return new SeckillExcution(seckillId, SeckillState.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepteKillException e2) {
			throw e2;
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			// 所有编译期间异常，转化为运行时异常
			throw new SeckillException("seckill inner error" + e.getMessage());
		}
	}

}
