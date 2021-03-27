package com.pratap.completablefuture;

import static com.pratap.util.CommonUtil.delay;
import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;
import static com.pratap.util.LoggerUtil.log;

import java.util.concurrent.CompletableFuture;

import com.pratap.service.HelloWorldService;

public class CompletableFutureHelloWorldException {

	private HelloWorldService helloWorldService;

	public CompletableFutureHelloWorldException(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	public String helloWorldWithThreeAsyncCallsWithHandleException() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		});

		String helloWorld = hello
				.handle((result, exception) -> {
					log("result : "+result);
					if(exception != null) {
						log("Exception is : "+exception.getMessage());
						return "";
					} else {
						return result;
					}
				})
				.thenCombine(world, (h, w) -> h + w)//" world!"
				.handle((result, exception) -> {
					log("result : "+result);
					if(exception != null) {
						log("Exception after world is : "+exception.getMessage());
						return "";
					} else {
						return result;
					}
					
				})
				.thenCombine(hiCompletableFuture, (previous, current) -> previous + current)//" world! Hi CompletableFuture!"
				.thenApply(String::toUpperCase)
				.join();// " WORLD! HI COMPLETABLEFUTURE!"
		timeTaken();
		return helloWorld;
	}
	
	public String helloWorldWithThreeAsyncCallsWithExceptionally() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		});

		String helloWorld = hello
				.exceptionally(exception -> {
					log("exception occured : "+exception.getMessage());
					return "";
				})
				.thenCombine(world, (h, w) -> h + w)//" world!"
				.exceptionally(exception -> {
					log("exception occured after world : "+exception.getMessage());
					return "";
				})
				.thenCombine(hiCompletableFuture, (previous, current) -> previous + current)//" world! Hi CompletableFuture!"
				.thenApply(String::toUpperCase)
				.join();
		timeTaken();
		return helloWorld;
	}
	
	public String helloWorldWithThreeAsyncCallsWithWhenComplete() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		});

		String helloWorld = hello
				.whenComplete((result, exception) -> {
					log("result : "+result);
					if(exception != null) {
						log("Exception is : "+exception.getMessage());
					}
				})
				.thenCombine(world, (h, w) -> h + w)//" world!"
				.whenComplete((result, exception) -> {
					log("result : "+result);
					if(exception != null) {
						log("Exception after world is : "+exception.getMessage());
					}
					
				})
				.exceptionally(exception -> {
					log("Recoverable Exception after thenCombine "+exception);
					return "";
				})
				.thenCombine(hiCompletableFuture, (previous, current) -> previous + current)//" world! Hi CompletableFuture!"
				.thenApply(String::toUpperCase)
				.join();// " HI COMPLETABLEFUTURE!"
		timeTaken();
		return helloWorld;
	}
}
