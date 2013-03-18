package ncc.examples.hibernate.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Request;

import java.util.UUID;
import java.util.Properties;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONException;

import ncc.examples.hibernate.Employee;
import ncc.examples.hibernate.EmployeeDAO;
import ncc.examples.hibernate.DerbyHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



@Path("/employee")
public class EmployeeResource {

    @Context private UriInfo     uriInfo;
    @Context private Request     request;
    @Context private HttpHeaders headers;
    //@Context private HttpServletRequest  servletRequest;
    //@Context private HttpServletResponse servletResponse; 

    public EmployeeResource() throws Exception {
/*        Connection connection = null;        
        Statement statement = null;

        String protocol = "jdbc:derby:";
        String dbName = "hibernateexample";

        Properties props = new Properties();
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection("jdbc:derby:hibernateexample;create=false", props);
        statement = connection.createStatement();
        statement.execute("create table EMPLOYEE(id varchar(36), name varchar(1000))");
        connection.commit();*/
    }

    /**
      * This method will take an Employee object and create and
      * return the JSON representation of it.
      *
      *@param employee The Employee instance to convert to JSON
      *@return JSON representation of an Employee object
      */
    static public JSONObject employeeToJson(Employee employee) throws JSONException {
        JSONObject result = new JSONObject();

        result.put("class", employee.getClass().toString());
        result.put("name", employee.getName());
        result.put("id", employee.getId());

        return result;
    }

    /** Entry Points */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject getEmployee(@PathParam("id") UUID id) throws JSONException {

        Employee employee = EmployeeDAO.getInstance().getEmployee(id);
        
        JSONObject json = employeeToJson(employee);
        return json;

//        return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String sayHello() {
        return "Yo Dawg";
    }

    @GET
    @Path("create")
    @Produces({MediaType.TEXT_PLAIN})
    public String createData() throws JSONException {
        Employee test = new Employee();
        test.setName("abcd");
        EmployeeDAO.getInstance().saveEmployee(test);
        return employeeToJson(test).toString();
    }

}
