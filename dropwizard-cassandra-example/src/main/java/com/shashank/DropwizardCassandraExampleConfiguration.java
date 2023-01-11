package com.shashank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shashank.resources.model.CassandraFactory;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardCassandraExampleConfiguration extends Configuration {
  private final CassandraFactory cassandraFactory;

  public DropwizardCassandraExampleConfiguration(
      @JsonProperty("cassandra") @Valid @NotNull final CassandraFactory cassandraFactory) {
    this.cassandraFactory = cassandraFactory;
  }

  @JsonProperty("cassandra")
  public CassandraFactory getCassandraFactory() {
    return cassandraFactory;
  }
}
