package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageServiceTest {

	private MessageService messageService;

	@BeforeAll
	void setup() {
		this.messageService = new MessageService();
	}

	@Test
	void testGetMessage() {
		List<String> l1 = Arrays.asList("", "", "", "todo", "");
		List<String> l2 = Arrays.asList("las", "", "repudian", "", "encierro");
		List<String> l3 = Arrays.asList("", "almas", "", "", "encierro");
		List<List<String>> messages = Arrays.asList(l1, l2, l3);
		String expected = "las almas repudian todo encierro";
		String actual = messageService.getMessage(messages);
		assertEquals(expected, actual);
	}

}
