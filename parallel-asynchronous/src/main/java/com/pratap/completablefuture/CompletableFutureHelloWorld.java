package com.pratap.completablefuture;

import java.util.concurrent.CompletableFuture;

import com.pratap.service.HelloWorldService;
import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;
import static com.pratap.util.CommonUtil.delay;

/**
 * Intract with {@link com.pratap.service.HelloWorldService}
 * 
 * @author Pratap Narayan
 *
 */
public class CompletableFutureHelloWorld {

	private HelloWorldService hws;

	public CompletableFutureHelloWorld(HelloWorldService hws) {
		this.hws = hws;
	}

	public CompletableFuture<String> helloWorld() {

		return CompletableFuture.supplyAsync(hws::helloWorld)// as supplyAsync executed, release the main thread, & run
																// this in common fork-join pool
				.thenApply(String::toUpperCase);
	}

	public String helloWorldWithBasicApproach() {
		String hello = hws.hello();// 1-sec
		String world = hws.world();// 1-sec
		return hello + world;// 1 + 1 = 2 secs
	}

	public String helloWorldWithTwoAsyncCalls() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(hws::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(hws::world);

		String hw = hello.thenCombine(world, (h, w) -> h + w).thenApply(String::toUpperCase).join();
		timeTaken();
		return hw;
	}

	public String helloWorldWithThreeAsyncCalls() {
		startTimer();
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(hws::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(hws::world);
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
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(hws::hello);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(hws::world);
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
		return CompletableFuture.supplyAsync(hws::hello)
				.thenCompose(previous -> hws.worldFuture(previous));
	}
}
