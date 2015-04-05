/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw10;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author martin
 */
@ManagedBean
@RequestScoped
public class MessageBean {

    private String name;
    private String email;
    private String message;

    @Resource(name = "jdbc/messages")
    DataSource dataSource;

    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String saveEntry() throws SQLException {

        if (null == dataSource) {
            throw new SQLException("unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();
        if (null == connection) {
            throw new SQLException("unable to connect to DataSource");
        }

        PreparedStatement ps = connection.prepareStatement("INSERT INTO MESSAGES (DATE, NAME, EMAIL, MESSAGE) VALUES (?, ?, ?, ?)");
        ps.setString(1, getDate());
        ps.setString(2, getName());
        ps.setString(3, getEmail());
        ps.setString(4, getMessage());
        ps.execute();

        return "index";
    }

    public ResultSet getEntries() throws SQLException {
        if (null == dataSource) {
            throw new SQLException("unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();
        if (null == connection) {
            throw new SQLException("unable to connect to DataSource");
        }
        try {
            PreparedStatement getMessages = connection.prepareStatement("SELECT * FROM MESSAGES ORDER BY DATE");
            CachedRowSet rowSet = new CachedRowSetImpl();
            rowSet.populate(getMessages.executeQuery());
            return rowSet;
        } finally {
            connection.close();
        }

    }

    public String getDate() {
        String dFormat = "MM/dd/yyyy";
        return new SimpleDateFormat(dFormat).format(new Date());
    }
}
