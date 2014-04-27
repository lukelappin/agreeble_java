package com.agreeble;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.client.HttpClientConfiguration;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;
import com.yammer.dropwizard.db.DatabaseConfiguration;


import org.hibernate.validator.constraints.NotEmpty;

public class AgreebleConfiguration extends Configuration {

	@Valid
	@NotNull
	@JsonProperty
	private DatabaseConfiguration database = new DatabaseConfiguration();

    @Valid
	@NotNull
	@JsonProperty
	private HttpClientConfiguration httpClient = new HttpClientConfiguration();


	public DatabaseConfiguration getDatabaseConfiguration() {
	      return database;
	}

	
    public HttpClientConfiguration getHttpClientConfiguration() {
	        return httpClient;
    }
    
  
}
