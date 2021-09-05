package com.pratap.service;

import lombok.extern.slf4j.Slf4j;

import static com.pratap.util.CommonUtil.delay;
import static com.pratap.util.LoggerUtil.log;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class HelloWorldService {

	public  String helloWorld() {

        log.info("Executing helloWorld()");
        delay(1000);
        return "hello world";
    }
	
	public  String hello() {

        log.info("Executing hello()");
        delay(1000);
        log("inside hello");
        return "hello";
    }

    public  String world() {

        log.info("Executing world()");
        delay(1000);
        log("inside world");
        return " world!";
    }
    
    public CompletableFuture<String> worldFuture(String input) {

        log.info("Executing worldFuture()");
		return CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return input + " world!";
		});
	}
}
