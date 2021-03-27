package com.pratap.apiclient;
/**
 * 
 * @author Pratap Narayan
 *
 */

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import static com.pratap.util.LoggerUtil.log;

import com.pratap.domain.github.GitHubPosition;

public class GitHubJobsClient {

	private WebClient webClient;

	public GitHubJobsClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	public List<GitHubPosition> invokeGithubjobsAPI_withPageNumber(int pageNumber, String description){
		
		String uri = UriComponentsBuilder.fromUriString("/positions.json")
		.queryParam("description", description)
		.queryParam("page", pageNumber)
		.buildAndExpand()
		.toUriString();
		
		log("uri : "+uri);
		List<GitHubPosition> gitHubPositions = webClient.get().uri(uri)
		.retrieve()
		.bodyToFlux(GitHubPosition.class)
		.collectList()
		.block();
		
		return gitHubPositions;
	}
}
