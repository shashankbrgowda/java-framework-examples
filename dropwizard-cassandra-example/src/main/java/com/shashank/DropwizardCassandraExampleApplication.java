package com.shashank;

import com.datastax.oss.driver.api.core.CqlSession;
import com.shashank.resources.EmployeeResource;
import com.shashank.resources.model.CassandraFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.InetSocketAddress;

public class DropwizardCassandraExampleApplication
    extends Application<DropwizardCassandraExampleConfiguration> {

  public static void main(final String[] args) throws Exception {
    new DropwizardCassandraExampleApplication().run(args);
  }

  @Override
  public String getName() {
    return "dropwizard-cassandra-example";
  }

  @Override
  public void initialize(final Bootstrap<DropwizardCassandraExampleConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(
      final DropwizardCassandraExampleConfiguration configuration, final Environment environment) {
    final CassandraFactory cassandraFactory = configuration.getCassandraFactory();
    final CqlSession session =
        CqlSession.builder()
            .addContactPoint(
                new InetSocketAddress(
                    cassandraFactory.getContactPoint(), cassandraFactory.getPort()))
            .withKeyspace(cassandraFactory.getKeyspace())
            .withLocalDatacenter(cassandraFactory.getDc())
            .build();
    environment.jersey().register(new EmployeeResource(session));
  }
}
