package com.agreeble;

import com.agreeble.issues.dto.Issue;
import com.agreeble.thoughts.dao.ThoughtDAO;
import com.agreeble.thoughts.dto.Thought;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.agreeble.votes.dao.VoteDAO;
import com.agreeble.votes.dto.Vote;

import org.apache.http.client.HttpClient;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/agreeblerest")
@Produces(MediaType.APPLICATION_JSON)
public class AgreebleResource {

    private final HttpClient httpClient;
    private final VoteDAO voatDAO;
    private final ThoughtDAO thoughtDAO;
    private static final Logger LOG = LoggerFactory.getLogger(AgreebleResource.class);

    public AgreebleResource(HttpClient httpClient, VoteDAO voteDAO, ThoughtDAO thoughtDAO) {
      this.httpClient = httpClient;
      this.voatDAO = voteDAO;
      this.thoughtDAO = thoughtDAO;
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


    @GET
    @Path("/dbtest")
    @Produces({MediaType.APPLICATION_JSON})
    public String dbTest() {


        StringBuffer output = new StringBuffer();

        Thought thought = thoughtDAO.getIssueIdById(6);
        output.append("THOUGHT:" + thought.getIssue_id());

        List<Vote> voteList = voatDAO.getVotesByThoughtId(6);

        for(Vote v : voteList){
            output.append("VOTE:" + v.getId());
        }

        return output.toString();

    }



    @POST
    @Path("/agreebletest2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String agreebleTest2(List<Issue> issue) {


        StringBuffer output = new StringBuffer();

        for(Issue i : issue){
            output.append("ISSUE ID:" + i.getIssue());

            for(Integer thought : i.getThoughts()){
                output.append("THOUGHT:" + thought);
            }
        }

        return output.toString();

    }




}
