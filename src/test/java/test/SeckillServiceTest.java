package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExcution;
import com.seckill.entity.Seckill;
import com.seckill.service.SeckillService;
/**
 * 配合spring和junit4整合
 * junit启动时加载apringIOC容器
 * @author phantomfjh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		 "classpath:spring-dao.xml",
		 "classpath:spring-service.xml"})
public class SeckillServiceTest {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
	@Test
	public void testGetList() throws Exception {
		List<Seckill> list=seckillService.getSeckillList();
		logger.info("list{}", list);
	}
	
	@Test
	public void testGetById() throws Exception {
		long id=1000;
		Seckill seckill=seckillService.getById(id);
		logger.info("seckill", seckill.getName());
	}
	

	@Test
	public void testExposer() throws Exception {
		long id=1000;
		Exposer exposer=seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}", exposer);
			long phone=1312312312L;
			String md5=exposer.getMd5();
			SeckillExcution seckillExcution= seckillService.excuteSeckill(id, phone, md5);
			logger.info("SeckillExcution", seckillExcution);
		}else{
			logger.info("exposer={}", exposer);
		}
	}
	



}
