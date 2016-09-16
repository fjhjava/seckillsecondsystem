package com.seckill.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExcution;
import com.seckill.dto.SkillResult;
import com.seckill.entity.Seckill;
import com.seckill.enums.SeckillState;
import com.seckill.exception.RepteKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

@Controller
public class SeckillController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;

	@RequestMapping(name = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}

	// ajax/json
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public SkillResult<Exposer> exposer(Long seckillId) {
		SkillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SkillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SkillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execute", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public SkillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null) {
			return new SkillResult<SeckillExcution>(false, "未注册");
		}
		SkillResult<SeckillException> result;
		try {
			SeckillExcution excution = seckillService.excuteSeckill(seckillId, phone, md5);
			return new SkillResult<SeckillExcution>(true, excution);
		} catch (SeckillCloseException e) {
			SeckillExcution excution = new SeckillExcution(seckillId, SeckillState.END);
			return new SkillResult<SeckillExcution>(false, excution);
		} catch (RepteKillException e) {
			SeckillExcution excution = new SeckillExcution(seckillId, SeckillState.REPETE_KILL);
			return new SkillResult<SeckillExcution>(false, excution);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			SeckillExcution excution = new SeckillExcution(seckillId, SeckillState.INNER_ERROR);
			return new SkillResult<SeckillExcution>(false, excution);
		}
	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	public SkillResult<Long> time() {
		Date now = new Date();
		return new SkillResult<Long>(true, now.getTime());
	}
}
