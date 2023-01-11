package com.shashank.resources;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.shashank.resources.model.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    private final CqlSession session;

    public EmployeeResource(CqlSession session) {
        this.session = session;
    }

    @GET
    @Path("/{id}")
    public Employee getUser(@PathParam("id") Integer id) {
        ResultSet result = session.execute("SELECT * FROM employee WHERE emp_id = " + id);
        Row row = result.one();
        if (row != null) {
            return new Employee(row.getInt("emp_id"), row.getString("name"), row.getString("city"));
        }
        return new Employee();
    }
}
