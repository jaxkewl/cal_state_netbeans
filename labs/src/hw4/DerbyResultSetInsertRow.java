/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

/**
 * DerbyResultSetInsertRow.java
 * Copyright (c) 2007 by Dr. Herong Yang. All rights reserved.
 */
import java.sql.*;
public class DerbyResultSetInsertRow {
  public static void main(String [] args) {
    Connection con = null;
    try {
      con = DriverManager.getConnection(
        "jdbc:derby://localhost/TestDB");

// Create a Statement for scrollable ResultSet
      Statement sta = con.createStatement(
        ResultSet.TYPE_FORWARD_ONLY,
        ResultSet.CONCUR_UPDATABLE);

// Catch the ResultSet object
      ResultSet res = sta.executeQuery(
        "SELECT * FROM App.Profile WHERE 1=2");

// Check ResultSet's updatability
      if (res.getConcurrency() == ResultSet.CONCUR_READ_ONLY) {
        System.out.println("ResultSet non-updatable.");
      } else {
        System.out.println("ResultSet updatable.");
      }

// Move the cursor to the insert row
      res.moveToInsertRow();

// Set the new first name and last name
      res.updateString("FirstName", "Lucy");
      res.updateString("LastName", "Harrington");
      res.updateString("Point", "123.456");
      res.updateString("BirthDate", "1977-07-07");
      res.updateString("ModTime", "2007-01-01 01:01:01.001");

// Store the insert into database
      res.insertRow();
      
//      boolean firstRow = res.first();
      
// Move the cursor back to the current row
      res.moveToCurrentRow();
      
      boolean firstRow = res.first();
      
      System.out.println("Row inserted ok.");

// Close ResultSet and Statement
      res.close();
      sta.close();

      con.close();
    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
      e.printStackTrace();
    }
  }
}