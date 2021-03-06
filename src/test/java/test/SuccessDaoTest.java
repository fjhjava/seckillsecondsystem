package test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKillDao;

/**
 * 配合spring和junit4整合 junit启动时加载apringIOC容器
 * 
 * @author phantomfjh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-dao.xml" })
public class SuccessDaoTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuccessKillDao successDao;

	@Test
	public void testGetById() throws Exception {
		long id = 1000;
		int c=successDao.insertSuccessKilled(id, 1358532731);
		logger.info("seckill", c);
	}

}
