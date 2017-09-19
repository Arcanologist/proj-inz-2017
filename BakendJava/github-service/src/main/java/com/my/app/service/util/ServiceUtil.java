package com.my.app.service.util;

public class ServiceUtil {

	private static final String GITHUB_BASIC_REST_ENDPOINT = "https://api.github.com";

	private static final String GITHUB_REPO_REST_ENDPOINT = GITHUB_BASIC_REST_ENDPOINT
			+ "/repos/%1/%2";

	private static final String STARGAZERS = GITHUB_REPO_REST_ENDPOINT + "stargazers";

	public static String getGithubRepoUrl(String owner, String repoName) {
		return GITHUB_REPO_REST_ENDPOINT.replace("%1", owner).replace("%2", repoName);
	}
	
	public static String getGithubStargazers(String owner, String repoName) {
		return STARGAZERS.replace("%1", owner).replace("%2", repoName);
	}

	public static final String FULL_NAME = "full_name";
	public static final String DESCRIPTION = "description";
	public static final String CLONE_URL = "clone_url";
	public static final String STARS = "stargazers_url";
	public static final String CREATED_AT = "created_at";
	
	public static final String EMPTY_JSON = "[]";

}
