package org.pratap.stream.basic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReadFileThroughStreamAPI {

	public static void main(String[] args) {

		String PATH = "/Users/835698/development/java-multithreading/concurrency-app/student_names";
		
		try {
			Stream<String> namesStream = Files.lines(Path.of(PATH));
			namesStream.forEach(System.out::println);
			namesStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
