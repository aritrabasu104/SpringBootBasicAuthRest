package com.wavelet.stockmovement.controller;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wavelet.stockmovement.service.StockMovementService;


@RunWith(SpringRunner.class)
@WebMvcTest(StockMovementController.class)
public class StockMovementControllerTest {
	public static final String BASE_URL_BOOK="";
	public static final String BASE_URL_STOCKS = "/api/stocks";
	public static final String BASE_URL_ADD_STOCK = "/api/addStock";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String ERROR_MSG_BLANK_FIELD="-must not be blank";
	public static final String ERROR_MSG_NULL_FIELD="-must not be null";
	public static final String ERROR_MSG_BLANK_BODY ="request body is missing";
	public static final String ERROR_MSG_NEGATIVE_VALUE="-must be greater than or equal to 0";
	@Autowired
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext context;
	@MockBean
	private StockMovementService mockStockMovementService;
	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@WithMockUser
	@Test
	public void stocksWithInvalidMethodError() throws Exception {
		ResultActions resultActions = mvc.perform(post(BASE_URL_STOCKS));
		resultActions.andExpect(status().isForbidden());
		
	}
	@WithMockUser
	@Test
	public void stocksWithCorrectUserCredentialOk() throws Exception {
		ResultActions resultActions = mvc.perform(get(BASE_URL_STOCKS));
		resultActions.andExpect(status().isOk());
		
	}
	@WithMockUser
	@Test
	public void addStockWithInvalidMethodError() throws Exception {
		ResultActions resultActions = mvc.perform(get(BASE_URL_ADD_STOCK));
		resultActions.andExpect(status().isMethodNotAllowed());
		
	}
	@WithMockUser
	@Test
	public void addStockRequestBodyWithoutContentTypeError() throws Exception {
		String body= "{}";
		ResultActions resultActions = mvc.perform(post(BASE_URL_ADD_STOCK).with(csrf())
				.content(body));
		resultActions.andExpect(status().isUnsupportedMediaType());
		
	}
	@WithMockUser
	@Test
	public void addStockWithoutRequestBodyError() throws Exception {
		ResultActions resultActions = mvc.perform(post(BASE_URL_ADD_STOCK)
				.with(csrf()).
				header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON));
		resultActions.andExpect(status().isBadRequest());
		
	}
	@WithMockUser
	@Test
	public void addStockBlankRequestBodyError() throws Exception {
		String body= "{}";
		ResultActions resultActions = mvc.perform(post(BASE_URL_ADD_STOCK).content(body)
				.with(csrf()).
				header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON));
		resultActions.andExpect(status().isBadRequest());
		
	}
	@WithMockUser
	@Test
	public void addStockRequestBodyWithEmptyValuesError() throws Exception {
		
		String body= "{\"location\":\"\",\"date\":\"\","
				+ "\"item\":\"\",\"inQty\":\"\",\"outQty\":\"\"}";
		ResultActions resultActions = mvc.perform(post(BASE_URL_ADD_STOCK).
				content(body).with(csrf()).
				header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON));
		resultActions.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("location"+
							ERROR_MSG_BLANK_FIELD)))
					.andExpect(content().string(containsString("date"+
							ERROR_MSG_BLANK_FIELD)))
					.andExpect(content().string(containsString("item"+
							ERROR_MSG_BLANK_FIELD)))
					.andExpect(content().string(containsString("inQty"+
							ERROR_MSG_NULL_FIELD)))
					.andExpect(content().string(containsString("outQty"+
							ERROR_MSG_NULL_FIELD)));
		
	}
	@WithMockUser
	@Test
	public void addStockRequestBodyWithInvalidValuesOk() throws Exception {
		String body= "{\"location\":\"loc1\",\"date\":\"date1\","
				+ "\"item\":\"item1\",\"inQty\":-10,\"outQty\":-1}";
		ResultActions resultActions = mvc.perform(post(BASE_URL_ADD_STOCK)
				.content(body).with(csrf()).
				header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON));
		resultActions.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("inQty"+
					ERROR_MSG_NEGATIVE_VALUE)))
			.andExpect(content().string(containsString("outQty"+
					ERROR_MSG_NEGATIVE_VALUE)));
		
	}
	@WithMockUser
	@Test
	public void addStockRequestBodyWithProperValuesOk() throws Exception {
		String body= "{\"location\":\"loc1\",\"date\":\"date1\","
				+ "\"item\":\"item1\",\"inQty\":10,\"outQty\":0}";
		ResultActions resultActions = mvc.perform(post(BASE_URL_ADD_STOCK)
				.content(body).with(csrf()).
				header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON));
		//Assert
		resultActions.andExpect(status().isCreated());
		
	}
		
	
}
