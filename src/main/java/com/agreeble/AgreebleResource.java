package com.agreeble;

import com.agreeble.issues.dto.Issue;
import com.agreeble.thoughts.dao.ThoughtDAO;
import com.agreeble.thoughts.dto.Thought;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.agreeble.userissueposition.dao.UserIssuePositionDAO;
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
    private final UserIssuePositionDAO userIssuePositionDAO;
    private static final Logger LOG = LoggerFactory.getLogger(AgreebleResource.class);

    public AgreebleResource(HttpClient httpClient, VoteDAO voteDAO, ThoughtDAO thoughtDAO,  UserIssuePositionDAO userIssuePositionDAO) {
      this.httpClient = httpClient;
      this.voatDAO = voteDAO;
      this.thoughtDAO = thoughtDAO;
      this.userIssuePositionDAO = userIssuePositionDAO;
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
        output.append("THOUGHT:" + thought);

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
        String apsType = "";
        double apsVal = 0;

        for(Issue i : issue){
            output.append("ISSUE ID:" + i.getIssue());

            for(Integer thought : i.getThoughts()){
                int thoughtIssue = thoughtDAO.getIssueIdById(thought).getIssue_id();
                List<Vote> votes = voatDAO.getVotesByThoughtId(thought);

                for(Vote v : votes){
                    double opinionVoteUserPosition = userIssuePositionDAO.getPositionById(v.getId()).getPosition();

                    int opinionVote = 0;

                    if(v.isVote()){
                        opinionVote = 1;
                    }
                    int Sa=0,Sd=0,Oa=0,Od = 0;

                    if (opinionVoteUserPosition >=.51 && opinionVote == 1){
                        Sa += 1;
                    }
                    if (opinionVoteUserPosition >=.51 && opinionVote == 0){
                        Sd += 1;
                    }
                    if (opinionVoteUserPosition <=.49 && opinionVote == 1){
                        Oa += 1;
                    }
                    if (opinionVoteUserPosition <=.49 && opinionVote == 0){
                        Od += 1 ;
                    }

                    int volume = Sa+Sd+Oa+Od;


                    if (Sa > Sd && Oa > Od){
                        apsType="Consensus (P)";
                        apsVal = (Sa + Oa) + (Sa+Oa)/volume;
                    }
                    if (Sa < Sd && Oa < Od)
                        apsType="Consensus (N)";
                        apsVal = (Sd + Od) + (Sd+Od)/volume;

                    if (Sa > Sd && Oa < Od){
                        apsType="Polar (P)";
                        apsVal = (Sa + Od) + (Sa+Od)/volume;
                    }

                    if (Sa < Sd && Oa > Od){
                        apsType="Polar (N)";
                        apsVal = (Sd + Oa) + (Sd+Oa)/volume;
                    }


                }

            }
            output.append("APS TYPE:" + apsType);
            output.append("APS VAL:" + apsVal);
        }


        return output.toString();

    }




}
