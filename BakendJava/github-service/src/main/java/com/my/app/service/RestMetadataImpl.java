package com.my.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.app.client.RestDataReciever;
import com.my.app.service.model.RepositoryMetadata;
import com.my.app.service.util.ServiceUtil;

@Service("metadataService")
public class RestMetadataImpl implements MetadataService {

	@Autowired
	private RestDataReciever restDataReceiver;

	/**
	 * method convert json string with github api repo to RepositoryMetadata
	 * @return RepositoryMetadata
	 * @see com.my.app.service.MetadataService#loadMetadata(java.lang.String, java.lang.String)
	 */
	@Override
	public RepositoryMetadata loadMetadata(String owner, String repositoryName)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		String url = ServiceUtil.getGithubRepoUrl(owner, repositoryName);
		String json = restDataReceiver.getDataFromService(url);

		map = om.readValue(json, new TypeReference<Map<String, Object>>() {
		});
		RepositoryMetadata rmd = new RepositoryMetadata.RepositoryMetadataBuilder(map).build();

		json = restDataReceiver.getDataFromService(rmd.getStarsUrl());
		if (!json.equals(ServiceUtil.EMPTY_JSON)) {
			map = om.readValue(json, new TypeReference<Map<String, Object>>() {
			});
			return new RepositoryMetadata.RepositoryMetadataBuilder(map).addStars(map, rmd);
		}
		return rmd;
	}

}
