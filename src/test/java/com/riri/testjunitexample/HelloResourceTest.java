package com.riri.testjunitexample;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import com.jayway.jsonpath.JsonPath;

import ch.qos.logback.core.status.Status;

import java.util.regex.Matcher;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloResourceTest {

	
	private MockMvc mockMvc;
	
	@InjectMocks
	private HelloResource helloResource;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(helloResource)
				.build();
	}
	
	@Test
	public void testHelloWorld() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/hello")
				)
		
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("hello world"));
		
	}
	
	@Test
	public void testHelloJson() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/hello/json")
				 .accept(MediaType.APPLICATION_JSON))
		 		.andExpect(MockMvcResultMatchers.status().isOk())
		 		.andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Greetings")))
				 .andExpect(MockMvcResultMatchers.jsonPath("$.value",Matchers.is("Hello World")))
				 .andExpect(MockMvcResultMatchers.jsonPath("$.*",Matchers.hasSize(2)));
		
	}

	
	
}
