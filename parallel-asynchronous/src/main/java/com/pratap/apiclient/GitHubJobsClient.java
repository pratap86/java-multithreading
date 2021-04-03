package com.pratap.apiclient;
/**
 * 
 * @author Pratap Narayan
 *
 */

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import static com.pratap.util.LoggerUtil.log;

import com.pratap.domain.github.GitHubPosition;
import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;

public class GitHubJobsClient {

	private WebClient webClient;

	public GitHubJobsClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	/**
	 * 
	 * @param pageNumber
	 * @param description
	 * @return List<GitHubPosition>
	 */
	public List<GitHubPosition> invokeGithubjobsAPI_withPageNumber(int pageNumber, String description) {

		String uri = UriComponentsBuilder.fromUriString("/positions.json").queryParam("description", description)
				.queryParam("page", pageNumber).buildAndExpand().toUriString();

		log("uri : " + uri);
		List<GitHubPosition> gitHubPositions = webClient.get().uri(uri).retrieve().bodyToFlux(GitHubPosition.class)
				.collectList().block();

		return gitHubPositions;
	}

	/**
	 * 
	 * @param pageNumbers
	 * @param description
	 * @return List<GitHubPosition>
	 */
	public List<GitHubPosition> invokeGithubjobsAPI_withMultiplePageNumbers(List<Integer> pageNumbers,
			String description) {

		startTimer();

		List<GitHubPosition> gitHubPositions = pageNumbers.stream()
				.map(pageNumber -> invokeGithubjobsAPI_withPageNumber(pageNumber, description))
				.flatMap(Collection::stream).collect(Collectors.toList());

		timeTaken();

		return gitHubPositions;
	}

	// use completablefuture to improve the performance

	/**
	 * 
	 * @param pageNumbers
	 * @param description
	 * @return List<GitHubPosition>
	 */
	public List<GitHubPosition> invokeGithubjobsAPI_withMultiplePageNumbers_withCompletableFuture(
			List<Integer> pageNumbers, String description) {

		startTimer();

		List<CompletableFuture<List<GitHubPosition>>> cfGitHubpositions = pageNumbers.stream()
				.map(pageNumber -> CompletableFuture
						.supplyAsync(() -> invokeGithubjobsAPI_withPageNumber(pageNumber, description)))
				.collect(Collectors.toList());

		List<GitHubPosition> gitHubPositions = cfGitHubpositions.stream()
				.map(CompletableFuture::join)// wait each compleatablefuture task to be complete
				.flatMap(Collection::stream).collect(Collectors.toList());

		timeTaken();
		

		return gitHubPositions;
	}
	
	public List<GitHubPosition> invokeGithubjobsAPI_withMultiplePageNumbers_withCompletableFutureAllOf(
			List<Integer> pageNumbers, String description) {

		startTimer();

		List<CompletableFuture<List<GitHubPosition>>> cfGitHubpositions = pageNumbers.stream()
				.map(pageNumber -> CompletableFuture
						.supplyAsync(() -> invokeGithubjobsAPI_withPageNumber(pageNumber, description)))
				.collect(Collectors.toList());
		
		CompletableFuture<Void> completableFutureAllOf = CompletableFuture.allOf(cfGitHubpositions.toArray(new CompletableFuture[cfGitHubpositions.size()]));
		
		List<GitHubPosition> gitHubPositions = completableFutureAllOf.thenApply(v -> cfGitHubpositions.stream()
				.map(CompletableFuture::join)
				.flatMap(Collection::stream)
				.collect(Collectors.toList()))
				.join();


		timeTaken();

		return gitHubPositions;
	}
}
