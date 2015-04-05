/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class TableModel extends AbstractTableModel {

    //SQL connection member variables
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    
    public TableModel(String url, String username, String password, String query) throws SQLException {
        //to use derby, make sure the DB has started and the derby.jar has been loaded into the library
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
    }

    //execute the SQL statement to insert an entry into the table.
    public void executeUpdate(String sql) throws SQLException {
        System.out.println("Executing update: " + sql);
        statement.executeUpdate(sql);
        fireTableStructureChanged();
    }
    
    @Override
    public int getRowCount() {
        int rowCount = 0;
        
        try {
            resultSet = statement.executeQuery("SELECT * FROM APP.ADDRESSES");
            resultSet.last();
            rowCount = resultSet.getRow();
            
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return rowCount;
        
    }
    
    @Override
    public int getColumnCount() {
        //make a query to get the row count
        try {
            return metaData.getColumnCount();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return "";
    }
    
    public void disconnectFromDB() throws SQLException {
        System.out.println("disconnection from DB");
        statement.close();
        connection.close();
        //System.exit(1);
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        try {
            String className = metaData.getColumnClassName(columnIndex + 1);
            return Class.forName(className);
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}

/**
 *
 * @author martin
 */
public class MartinHongJava2Lab5 extends WindowAdapter {
    
    TableModel tm;
    JTextField queryText;
    
    JTextField firstNameTF;
    JTextField lastNameTF;
    JTextField phoneNumTF;
    JTextField emailTF;
    
    JButton submit;
    JTextArea statusLabel;
    
    public MartinHongJava2Lab5() throws SQLException {
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby:address";
        url = "jdbc:derby://localhost:1527/address";
        String query = "SELECT * FROM APP.ADDRESSES";
        tm = new TableModel(url, "admin", "admin", query);
        
        JTable table = new JTable(tm);
        JScrollPane scrollpane = new JScrollPane(table);
        
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        //set a gridlayout for the address information
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.add(new JLabel("First Name"));
        firstNameTF = new JTextField();
        panel.add(firstNameTF);
        
        panel.add(new JLabel("Last Name"));
        lastNameTF = new JTextField();
        panel.add(lastNameTF);
        
        panel.add(new JLabel("Email"));
        emailTF = new JTextField();
        panel.add(emailTF);
        
        panel.add(new JLabel("Phone Number"));
        phoneNumTF = new JTextField();
        panel.add(phoneNumTF);
        
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = formSQL();
                    if (0 != sql.length()) {
                        tm.executeUpdate(sql);
                        statusLabel.setText("Executed SQL: " + sql);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MartinHongJava2Lab5.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(submit);
        
        statusLabel = new JTextArea();
        statusLabel.setLineWrap(true);
        panel.add(statusLabel);
        
        frame.add(panel);
        frame.add(scrollpane);
        frame.addWindowListener(this);
        
        queryText = new JTextField();
        queryText.setText("INSERT INTO APP.ADDRESSES (FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER) VALUES ('Martin', 'Hong', 'martin@martin.com', '123-456-7890')");
        queryText.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tm.executeUpdate(e.getActionCommand());
                } catch (SQLException ex) {
                    Logger.getLogger(MartinHongJava2Lab5.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //frame.add(queryText, BorderLayout.SOUTH);

        frame.setSize(1000, 700);
        frame.setVisible(
                true);
    }
    
    private String formSQL() {
        //verify the text box inputs and create an SQL statement
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String email = emailTF.getText();
        String phone = phoneNumTF.getText();
        
        if (0 == firstName.length() || 0 == lastName.length() || 0 == email.length() || 0 == phone.length()) {
            statusLabel.setText("One or more fields is empty");
            return "";
        }

        // all fields are filled
        return "INSERT INTO APP.ADDRESSES (FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + phone + "')";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MartinHongJava2Lab5 lab5 = new MartinHongJava2Lab5();
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window closing event");

        //when the user closes the event, call to terminate the SQL connections
        try {
            tm.disconnectFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(MartinHongJava2Lab5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
