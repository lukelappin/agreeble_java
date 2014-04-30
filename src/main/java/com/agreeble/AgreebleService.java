package com.agreeble;



import com.agreeble.auth.PasswordGenerator;

import com.agreeble.thoughts.dao.ThoughtDAO;
import com.agreeble.votes.dao.VoteDAO;
import com.yammer.dropwizard.client.HttpClientBuilder;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import org.apache.http.client.HttpClient;
import org.skife.jdbi.v2.DBI;

import com.yammer.dropwizard.Service;

import com.yammer.dropwizard.config.Environment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class AgreebleService extends Service<AgreebleConfiguration> {

    public static void main(String[] args) throws Exception
    {
        new AgreebleService().run(args);
    }


    @Override
    public void initialize(Bootstrap<AgreebleConfiguration> bootstrap) {
        bootstrap.setName("agreeble");
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(AgreebleConfiguration config,
                              Environment environment) throws ClassNotFoundException {

        final DBIFactory factory = new DBIFactory();
        final DBI db = factory.build(environment, config.getDatabaseConfiguration(), "postgres");
        //final DatabaseFactory dbfactory = new DatabaseFactory(environment);
        //final Database db = dbfactory.build(config.getDatabaseConfiguration(), "mysql");
        //final Handle h = db.open();

        final HttpClient httpClient = new HttpClientBuilder().using(config.getHttpClientConfiguration())
                .build();

        final VoteDAO voteDAO = db.onDemand(VoteDAO.class);
        final ThoughtDAO thoughtDAO = db.onDemand(ThoughtDAO.class);

       environment.addResource(new AgreebleResource(httpClient,voteDAO,thoughtDAO));
        
    }
}
