package com.challenge.jestrada.rtstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.jestrada.rtstatistic.bo.Statistic;
import com.challenge.jestrada.rtstatistic.service.StatisticService;

@Controller
public class StatisticController {

	@Autowired
	private StatisticService statisticService;
	
	@GetMapping(path = "/statistics")
	public @ResponseBody Statistic getStatistic() {
		return statisticService.getLastSixtySecondsStatistic();
	}

}
