package com.pratap.completablefuture;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.pratap.util.CommonUtil.stopWatchReset;

import com.pratap.service.HelloWorldService;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {
	
	@Mock
	private HelloWorldService helloWorldService;
	
	@InjectMocks
	private CompletableFutureHelloWorldException completableFutureHelloWorldException;

	@Test
	void testHelloWorldWithThreeAsyncCallsWithHandleExceptionWithHello() {
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithHandleException();
		assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithHandleExceptionWithWorld() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenThrow(new RuntimeException("Excetion turned up"));
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithHandleException();
		assertEquals(" HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithHandleExceptionWithHelloAndWorld() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenThrow(new RuntimeException("Excetion turned up"));
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithHandleException();
		assertEquals(" HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithHandleException() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithHandleException();
		assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithExceptionally() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithExceptionally();
		assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithExceptionallyWithHello() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithExceptionally();
		assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithExceptionallyWithWorld() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithExceptionally();
		assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHelloWorldWithThreeAsyncCallsWithExceptionallyWithHelloAndWorld() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenThrow(new RuntimeException("Excetion turned up"));
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithExceptionally();
		assertEquals(" HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHhelloWorldWithThreeAsyncCallsWithWhenComplete() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithWhenComplete();
		assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHhelloWorldWithThreeAsyncCallsWithWhenCompleteWithHelloException() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenCallRealMethod();
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithWhenComplete();
		assertEquals(" HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHhelloWorldWithThreeAsyncCallsWithWhenCompleteWithWorldException() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenCallRealMethod();
		when(helloWorldService.world()).thenThrow(new RuntimeException("Excetion turned up"));
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithWhenComplete();
		assertEquals(" HI COMPLETABLEFUTURE!", result);
	}
	
	@Test
	void testHhelloWorldWithThreeAsyncCallsWithWhenCompleteWithHelloAndWorldException() {
		
		stopWatchReset();
		when(helloWorldService.hello()).thenThrow(new RuntimeException("Excetion turned up"));
		when(helloWorldService.world()).thenThrow(new RuntimeException("Excetion turned up"));
		
		String result = completableFutureHelloWorldException.helloWorldWithThreeAsyncCallsWithWhenComplete();
		assertEquals(" HI COMPLETABLEFUTURE!", result);
	}

}
