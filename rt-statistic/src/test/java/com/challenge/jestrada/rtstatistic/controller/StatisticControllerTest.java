package com.challenge.jestrada.rtstatistic.controller;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.challenge.jestrada.rtstatistic.RtStatisticApplication;
import com.challenge.jestrada.rtstatistic.bo.Statistic;
import com.challenge.jestrada.rtstatistic.service.StatisticService;

/**
 * 
 * @author jonathanestrada
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RtStatisticApplication.class)
@AutoConfigureMockMvc
public class StatisticControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticService statisticService;

	@Test
	public void getEmptyStatistic() throws Exception {
		Long currentEpoch = Instant.now().getEpochSecond() * 1000;
		Mockito.when(statisticService.getLastSixtySecondsStatistic(currentEpoch)).thenReturn(new Statistic());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expectedStatistic = "{sum:0.0,avg:0.0,min:0.0,max:0.0,count:0}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		JSONAssert.assertEquals(expectedStatistic, result.getResponse().getContentAsString(), true);
	}

	@Test
	public void getPopulatedStatistic() throws Exception {
		Statistic statistic = new Statistic(5.5, 3.5, 1.1, 4.1, 3L);
		Long currentEpoch = Instant.now().getEpochSecond() * 1000;
		Mockito.when(statisticService.getLastSixtySecondsStatistic(currentEpoch)).thenReturn(statistic);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expectedStatistic = "{sum:5.5,avg:3.5,min:1.1,max:4.1,count:3}";

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		JSONAssert.assertEquals(expectedStatistic, result.getResponse().getContentAsString(), true);
	}

}
