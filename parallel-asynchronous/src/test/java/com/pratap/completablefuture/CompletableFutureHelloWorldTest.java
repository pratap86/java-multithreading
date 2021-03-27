package com.pratap.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import com.pratap.service.HelloWorldService;
import static com.pratap.util.CommonUtil.stopWatchReset;

class CompletableFutureHelloWorldTest {

	HelloWorldService hws = new HelloWorldService();
	CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);
	
	@Test
	void testHelloWorls() {
		
		CompletableFuture<String> completableFuture = cfhw.helloWorld();
		
		completableFuture.thenAccept(result -> {
			assertEquals("HELLO WORLD", result);
		}).join();
	}

	@Test
	void testHelloWorldWithTwoAsyncCalls() {
		stopWatchReset();
		String actual = cfhw.helloWorldWithTwoAsyncCalls();
		assertEquals("HELLO WORLD!", actual);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCalls() {
		stopWatchReset();
		String actual = cfhw.helloWorldWithThreeAsyncCalls();
		assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", actual);
	}
	
	@Test
	void testHelloWorldWithFourAsyncCalls() {
		stopWatchReset();
		String actual = cfhw.helloWorldWithFourAsyncCalls();
		assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE COMPLETABLEFUTURE!", actual);
	}
	
	@Test
	void testHelloWorldWithThenCompose() {
		CompletableFuture<String> completableFuture = cfhw.helloWorldWithThenCompose();
		completableFuture.thenAccept(result -> assertEquals("hello world!", result)).join();
	}
}
