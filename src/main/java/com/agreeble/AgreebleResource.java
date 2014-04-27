package com.agreeble;

import com.agreeble.issues.dto.Issue;
import com.agreeble.issues.dto.Issues;
import com.agreeble.utils.Globals;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.skife.jdbi.v2.exceptions.DBIException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/agreeblerest")
@Produces(MediaType.APPLICATION_JSON)
public class AgreebleResource {

    private final HttpClient httpClient;
    private static final Logger LOG = LoggerFactory.getLogger(AgreebleResource.class);

    public AgreebleResource(HttpClient httpClient) {
      this.httpClient = httpClient;
    }


    @POST
      @Path("/agreebletest")
      @Consumes({MediaType.APPLICATION_JSON})
      @Produces({MediaType.APPLICATION_JSON})
      public String agreebleTest(Issue issue) {


        StringBuffer output = new StringBuffer();
        output.append("ISSUE ID:" + issue.getIssue());

        for(Integer thought : issue.getThoughts()){
            output.append("THOUGHT:" + thought);
        }

        return output.toString();

    }


    @POST
    @Path("/agreebletest2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String agreebleTest2(Issues issues) {


        StringBuffer output = new StringBuffer();

        for(Issue issue : issues.getIssues()){
            output.append("ISSUE ID:" + issue.getIssue());

            for(Integer thought : issue.getThoughts()){
                output.append("THOUGHT:" + thought);
            }
        }

        return output.toString();

    }





}
