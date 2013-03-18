package ncc.examples.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ncc.examples.hibernate.DerbyHelper;

import java.util.ArrayList;
import java.util.Properties;

public class DerbyHelper {
    private String framework = "embedded";
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";

    static public void startEmbedded() {
        new DerbyHelper().go();
        System.out.println("Embedded Derby Instance Started.");
    }

    void go() {
        System.out.println("Starting _embedded_ Derby instance...");
        loadDriver();

        Connection connection = null;
        ArrayList statements = new ArrayList();

        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Properties props = new Properties();

            // Specifying username/password is optional in embedded
            props.put("user", "admin");
            props.put("password", "admin");


            String dbName = "hibernateExampleDb";
            connection = DriverManager.getConnection(protocol + dbName + ";create=true", props);
            System.out.println("Created and Connected to db " + dbName);

            // TODO - explain this
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            statements.add(statement);
            
            statement.execute("create table EMPLOYEE(id varchar(36), NAME varchar(200))");
            System.out.println("Created table EMPLOYEE");

            psInsert = connection.prepareStatement("insert into EMPLOYEE values (?, ?)");
            statements.add(psInsert);
           
            psInsert.setString(1, "50e13a86-8172-4e9b-9801-dddc8d3627b4");
            psInsert.setString(2, "Reginald VonSchwabenier");
            psInsert.executeUpdate();


            connection.commit();

        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {

        }



    }

    private void loadDriver() {
        try {
            Class.forName(driver).newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


}
