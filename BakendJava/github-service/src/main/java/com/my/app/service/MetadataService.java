package com.my.app.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.my.app.service.model.RepositoryMetadata;

public interface MetadataService {

	public RepositoryMetadata loadMetadata(String owner, String repositoryName) throws JsonParseException, JsonMappingException, IOException;
}
