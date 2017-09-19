package com.my.app.service.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my.app.service.util.ServiceUtil;

public class RepositoryMetadata implements Serializable {

	private static final long serialVersionUID = 6958977701746625741L;

	private String fullName;
	private String description;
	private String cloneUrl;
	private int stars;
	private String starsUrl;
	private Date createdAt;

	private RepositoryMetadata(String fullName, String description, String cloneUrl,
			Date createdAt, String starsUrl) {
		this.fullName = fullName;
		this.description = description;
		this.cloneUrl = cloneUrl;
		this.createdAt = createdAt;
		this.starsUrl = starsUrl;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDescription() {
		return description;
	}

	public String getCloneUrl() {
		return cloneUrl;
	}

	public int getStars() {
		return stars;
	}

	private void setStars(int stars) {
		this.stars = stars;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Budapest")
	public Date getCreatedAt() {
		return createdAt;
	}

	@JsonIgnore
	public String getStarsUrl() {
		return starsUrl;
	}

	public static class RepositoryMetadataBuilder {

		private Map<String, Object> source;

		public RepositoryMetadataBuilder(Map<String, Object> source) {
			this.source = source;
		}

		public RepositoryMetadata build() {
			String fullName = getMappedValue(source, ServiceUtil.FULL_NAME);
			String description = getMappedValue(source, ServiceUtil.DESCRIPTION);
			String cloneUrl = getMappedValue(source, ServiceUtil.CLONE_URL);
			String starsUrl = getMappedValue(source, ServiceUtil.STARS);
			String createdAt = getMappedValue(source, ServiceUtil.CREATED_AT);
			Date date = null;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
			  date = formatter.parse(createdAt);
			} catch (ParseException e) {
			  e.printStackTrace();
			}

			return new RepositoryMetadata(fullName, description, cloneUrl, date, starsUrl);

		}

		public RepositoryMetadata addStars(Map<String, Object> source, RepositoryMetadata model) {
			model.setStars(source.size());
			return model;
		}

		private String getMappedValue(Map<String, Object> map, String key) {

			for (Entry<String, Object> e : map.entrySet()) {
				if (e.getKey().equals(key))
					return (String) e.getValue();
				if (e.getValue() instanceof Map) {
					String val = getMappedValue((Map<String, Object>) e.getValue(), key);
					if (!val.isEmpty())
						return val;
				}
			}
			return "";
		}
	}
}
