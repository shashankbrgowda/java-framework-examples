package com.shashank.resources.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CassandraFactory {
    private final String contactPoint;
    private final Integer port;
    private final String keyspace;
    private final String dc;

    public CassandraFactory(@JsonProperty("contactPoints") final String contactPoint,
                            @JsonProperty("port") final Integer port,
                            @JsonProperty("keyspace") final String keyspace,
                            @JsonProperty("dc") final String dc) {
        this.contactPoint = contactPoint;
        this.port = port;
        this.keyspace = keyspace;
        this.dc = dc;
    }

    public String getContactPoint() {
        return contactPoint;
    }

    public Integer getPort() {
        return port;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public String getDc() {
        return dc;
    }
}
