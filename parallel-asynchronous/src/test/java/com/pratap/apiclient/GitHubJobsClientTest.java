package com.pratap.apiclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import com.pratap.domain.github.GitHubPosition;

class GitHubJobsClientTest {
	
	private static String BASE_URL = "https://jobs.github.com";

	WebClient webClient = WebClient.create(BASE_URL);
	GitHubJobsClient gitHubJobsClient = new GitHubJobsClient(webClient);
	
	@Test
	void testInvokeGithubjobsAPI_withPageNumber() {

		int pageNumber = 1;
		String description = "JavaScript";
		
		List<GitHubPosition> gitHubPositions = gitHubJobsClient.invokeGithubjobsAPI_withPageNumber(pageNumber, description);
		assertNotNull(gitHubPositions);
		gitHubPositions.forEach(Assertions::assertNotNull);
	}

}
