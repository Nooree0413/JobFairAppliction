package com.elca.jobfairmanagementsystem;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JobFairManagementSystemApplicationTests {

	@Test
	void contextLoads() {
		int expected = 1;
		int actual = 1 + 1;
		assertEquals(expected,actual,"The add method shoud add 2 numbers");
	}



}
