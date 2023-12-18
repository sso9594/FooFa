package com.FooFa.FooFa_Server;

import com.FooFa.FooFa_Server.domain.Memo;
import com.FooFa.FooFa_Server.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FooFaServerApplicationTests {

	private final MemoService memoService;

	FooFaServerApplicationTests(@Autowired MemoService memoService) {
		this.memoService = memoService;
	}

	@Test
	void contextLoads() {

	}

}
