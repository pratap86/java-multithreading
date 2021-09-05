package com.pratap.completablefuture;

import java.util.concurrent.CompletableFuture;

import com.pratap.service.HelloWorldService;
import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;
import static com.pratap.util.CommonUtil.delay;

/**
 * Interact with {@link com.pratap.service.HelloWorldService}
 * 
 * @author Pratap Narayan
 *
 */
public class CompletableFutureHelloWorld {

	private HelloWorldService helloWorldService;

	public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	public CompletableFuture<String> helloWorld() {

		return CompletableFuture.supplyAsync(helloWorldService::helloWorld)// as supplyAsync executed, release the main thread, & run
																// this in common fork-join pool
				.thenApply(String::toUpperCase);
	}

	public String helloWorldWithBasicApproach() {
		String hello = helloWorldService.hello();// 1-sec
		String world = helloWorldService.world();// 1-sec
		return hello + world;// 1 + 1 = 2 secs
	}

	public String helloWorldWithTwoAsyncCalls() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);

		String hw = hello.thenCombine(world, (h, w) -> h + w).thenApply(String::toUpperCase).join();
		timeTaken();
		return hw;
	}

	public String helloWorldWithThreeAsyncCalls() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		});

		String hw = hello.thenCombine(world, (h, w) -> h + w)
				.thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
				.thenApply(String::toUpperCase).join();
		timeTaken();
		return hw;
	}

	public String helloWorldWithFourAsyncCalls() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		});

		CompletableFuture<String> byeCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Bye CompletableFuture!";
		});

		String hw = hello.thenCombine(world, (h, w) -> h + w)
				.thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
				.thenCombine(byeCompletableFuture, (previous, current) -> previous + current)
				.thenApply(String::toUpperCase).join();
		timeTaken();
		return hw;
	}

	public CompletableFuture<String> helloWorldWithThenCompose(){
		return CompletableFuture.supplyAsync(helloWorldService::hello)
				.thenCompose(previous -> helloWorldService.worldFuture(previous));
	}
}
