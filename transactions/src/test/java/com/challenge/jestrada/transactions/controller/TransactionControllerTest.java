package com.challenge.jestrada.transactions.controller;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
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

import com.challenge.jestrada.transactions.TransactionsApplication;
import com.challenge.jestrada.transactions.service.TransactionService;

/**
 * 
 * @author jonathanestrada
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TransactionsApplication.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private TransactionService transactionService; 
    
	@Test
	public void responseHttp201() throws Exception {
		Long currentEpoch = Instant.now().getEpochSecond() * 1000;
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transactions")
				.accept(MediaType.APPLICATION_JSON)
				.content("{ \"amount\":90, \"timestamp\":" + currentEpoch + "}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void responseHttp204() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transactions")
				.accept(MediaType.APPLICATION_JSON)
				.content("{ \"amount\":90, \"timestamp\":1478995209000}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}

}
