/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MartinHongJava2Lab4 {

    private final static String DATABASE_URL = "jdbc:derby://localhost:1527/AddressBook";

    public static void main(String[] args) {
        String sqlStatement = "SELECT * FROM addresses";

        //use java's new try with resources ability.
        // the only code that can go in here are ones that extends from autocloseable
        try (
                // create the connection
                Connection connection = DriverManager.getConnection(DATABASE_URL,
                        "java", "java");
                // associate the statement to the connection
                Statement statement = connection.createStatement();
                // create a query statement to execute on the connection
                ResultSet resultSet = statement.executeQuery(sqlStatement);) {

            // process the result set
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numCol = metaData.getColumnCount();
            System.out.println("Address Book:\n");

            // output the column name from the metadata
            for (int i = 1; i <= numCol; i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));

            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numCol; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println();
            }

        } // catch (ClassNotFoundException exc) {
        // exc.printStackTrace();
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
