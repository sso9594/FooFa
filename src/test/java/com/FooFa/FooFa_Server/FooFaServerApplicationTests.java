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
		Memo memo = new Memo();
		memo.setMemoid("1");
		memo.setLat(1.0);
		memo.setLng(1.0);
		memo.setTextmemo("hi hi");
		memo.setImgurl("sdasd");

		System.out.println(memoService.findById("1"));
	}

}
