package com.my.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.app.service.MetadataService;
import com.my.app.web.util.AppUtil;

@RestController
public class RepoController {

	@Autowired
	private MetadataService metadataService;

	/**
	 * web-method called from rest-client. Return json string with github
	 * metadata or if failed: json string with error message and internal_server_error code
	 * 
	 * @param owner
	 * @param repositoryName
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/repositories/{owner}/{repository-name}", method = RequestMethod.GET, produces = AppUtil.REST_PRODUCES_JSON)
	public ResponseEntity<String> getMetadata(@PathVariable("owner") String owner,
			@PathVariable("repository-name") String repositoryName) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		try {
			String mess = om
					.writeValueAsString(metadataService.loadMetadata(owner, repositoryName));
			return new ResponseEntity<>(mess, HttpStatus.OK);
		} catch (Exception e) {
			String mess = om.writeValueAsString(AppUtil.getErrorMessage(e.getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mess);
		}
	}

}
