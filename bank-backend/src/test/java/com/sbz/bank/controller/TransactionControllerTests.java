package com.sbz.bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbz.bank.dto.TransactionDTO;
import com.sbz.bank.model.Location;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void contextLoads() {

	}

	@Test
	public void givenTransaction_shouldReturn200OK() throws Exception {
		TransactionDTO transaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			100,
			new Location(0, 0)
		);

		mvc.perform(post("/transaction/submit")
			.content(objectMapper.writeValueAsString(transaction))
			.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	public void twoTransactionsWithinOneHrAndMoreThan100KmAway_shouldReturn200OK_shouldReturnFraudulent() throws Exception {
		TransactionDTO firstTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			100,
			new Location(0, 0)
		);

		TransactionDTO secondTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			100,
			new Location(10, 10)
		);

		MvcResult res1 = mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(firstTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(secondTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("true"));
	}

	@Test
	public void distanceBetweenAllOtherIsLessThan300Km_shouldReturn200OK_shouldReturnFraudulent() throws Exception {
		TransactionDTO firstTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			50,
			new Location(0, 0)
		);

		TransactionDTO secondTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			50,
			new Location(0.1, 0.1)
		);

		TransactionDTO thirdTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			100,
			new Location(10, 10)
		);

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(firstTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(secondTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("false"));

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(thirdTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("true"));
	}

	@Test
	public void transactionAmountIs3TimesLargerThanAverage_shouldReturn200OK_shouldReturnFraudulent() throws Exception {
		TransactionDTO firstTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			10,
			new Location(0, 0)
		);

		TransactionDTO secondTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			10,
			new Location(0, 0)
		);

		TransactionDTO thirdTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			40,
			new Location(0, 0)
		);

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(firstTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(secondTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("false"));

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(thirdTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("true"));
	}

	@Test
	public void transactionIsBetween1and5amAndAmountIsMoreThan500_shouldReturn200OK_shouldReturnFraudulent() throws Exception {
		Clock clock = Clock.fixed(Instant.parse("2023-06-28T03:00:00Z"), ZoneId.of("UTC"));
		Instant instant = Instant.now(clock);

		System.out.println(LocalDateTime.now());

		TransactionDTO transaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			500,
			new Location(0, 0)
		);

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(transaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	public void threeTransactionWithinAnHour_shouldReturn200OK_shouldReturnFraudulent() throws Exception {
		TransactionDTO firstTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			50,
			new Location(0, 0)
		);

		TransactionDTO secondTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			50,
			new Location(0.1, 0.1)
		);

		TransactionDTO thirdTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			100,
			new Location(0, 0)
		);

		TransactionDTO fourthTransaction = new TransactionDTO(
			3l,
			100,
			LocalDateTime.of(2025, 1, 1, 0, 0),
			10,
			new Location(0, 0)
		);

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(firstTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(secondTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("false"));

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(thirdTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("false"));

		mvc.perform(post("/transaction/submit")
				.content(objectMapper.writeValueAsString(secondTransaction))
				.header("Authorization", "Basic am92YW5Aam92YW4uY29tOjEyMy5BdXRo")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.fraudulent").value("true"));

	}
}
