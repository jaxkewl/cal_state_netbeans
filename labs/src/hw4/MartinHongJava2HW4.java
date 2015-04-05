/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

import com.sun.rowset.JdbcRowSetImpl;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

class TableModel extends AbstractTableModel {

    //SQL connection member variables
    JdbcRowSet rowSet;
    private ResultSetMetaData metaData;
    private String table;

    //keep track of the author name to authorID
    HashMap<String, Integer> authorsHM = new HashMap<String, Integer>();

    //total num of rows
    int rowCount;

    //generic query statement
    String query = "SELECT * FROM APP.";

    public TableModel(String url, String username, String password, String table) throws SQLException {
        //to use derby, make sure the DB has started and the derby.jar has been loaded into the library
        this.table = table;
        query += table;
        System.out.println("Query is: " + query + " on " + url);

        rowSet = new JdbcRowSetImpl();
        rowSet.setUrl(url);
        rowSet.setUsername(username);
        rowSet.setPassword(password);
        rowSet.setCommand(query);
        rowSet.setQueryTimeout(3);
        rowSet.execute();
        rowSet.last();
        rowCount = rowSet.getRow();
        executeUpdate(query);
        metaData = rowSet.getMetaData();
    }

    public int getAuthorID(String authorNameFromComboBox) {
        return authorsHM.get(authorNameFromComboBox);
    }

    public ArrayList<String> getAuthors() {
        System.out.println("getting authors using table: " + table);
        ArrayList<String> authors = new ArrayList<>();

        authorsHM = new HashMap<String, Integer>();

        try {
            rowSet.setCommand("select FIRSTNAME, LASTNAME, AUTHORID from APP.AUTHORS");
            rowSet.execute();
            ResultSetMetaData md = rowSet.getMetaData();

            while (rowSet.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String authorName = rowSet.getObject("LASTNAME") + ", " + rowSet.getObject("FIRSTNAME");
                    Integer authorID = (Integer) rowSet.getObject("AUTHORID");

                    //add the key and value to our hashmap
                    if (!authorsHM.containsValue(authorID)) {
                        authors.add(authorName);
                        authorsHM.put(authorName, authorID);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authors;
    }

    public void modifyAuthor(int id, String firstName, String lastName) {
        try {
            rowSet.absolute(id);
            rowSet.updateString("FIRSTNAME", firstName);
            rowSet.updateString("LASTNAME", lastName);
            rowSet.updateRow();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableStructureChanged();
    }

    public void deleteAuthor(int id) {
        try {
            //FIXME need to delete all foreign keys that use this primary key.

            rowSet.absolute(id);
            rowSet.deleteRow();
            rowSet.first();
            rowCount--;
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableDataChanged();
    }

    public int getPrimaryKey(String firstName, String lastName) {
        System.out.println("Getting primary key for table: " + table);
        
        try {
            JdbcRowSet rowSet2 = new JdbcRowSetImpl();
            rowSet2.setUrl("jdbc:derby://localhost:1527/books");
            rowSet2.setUsername("admin");
            rowSet2.setPassword("admin");
            //rowSet2.setCommand(query);

            //String command = "SELECT AUTHORID FROM APP.AUTHORS WHERE FIRSTNAME='" + firstName + "' and LASTNAME='" + lastName + "'";
            String command = "SELECT AUTHORID FROM APP.AUTHORS WHERE FIRSTNAME='Batman' and LASTNAME='Login'";
            System.out.println("executing command: " + command);
            rowSet2.setCommand(command);
            rowSet2.execute();
            rowSet2.first();
            //ResultSetMetaData md = rowSet.getMetaData();

            //while (rowSet.next()) {
            Integer authorID = (Integer) rowSet2.getObject("AUTHORID");
            System.out.println("found primary key: " + authorID);
            rowSet2.close();
            return authorID;
            //}
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void linkAuthorToIsbn(int authorID, String isbn) {
        System.out.println("Inserting into table: " + table + " authorID: " + authorID + " isbn: " + isbn);
        try {
            rowSet.moveToInsertRow();
            rowSet.updateString("ISBN", isbn);
            rowSet.updateInt("AUTHORID", authorID);
            rowSet.insertRow();
            rowCount++;

            //query the DB again to repopulate rowSet.
            executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableDataChanged();
    }

    public void insertbook(String bookTitle, String isbn, String copyright, int edition) {
        try {

            System.out.println("Inserting book title: " + bookTitle + " isbn: " + isbn + " copyright: " + copyright + " edition: " + edition);
            rowSet.moveToInsertRow();
            rowSet.updateString("ISBN", isbn);
            rowSet.updateString("TITLE", bookTitle);
            rowSet.updateInt("EDITIONNUMBER", edition);
            rowSet.updateString("COPYRIGHT", copyright);
            rowSet.insertRow();
            rowCount++;

            //query the DB again to repopulate rowSet.
            executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableDataChanged();
    }

    public void insertAuthor(String firstName, String lastName) {
        try {
            System.out.println("inserting author with rows: " + rowSet.getRow());

            rowSet.moveToInsertRow();
            rowSet.updateString("FIRSTNAME", firstName);
            rowSet.updateString("LASTNAME", lastName);
            rowSet.insertRow();
            rowCount++;
            rowSet.moveToCurrentRow();
            metaData = rowSet.getMetaData();

            executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableDataChanged();
    }

    //execute the SQL statement to insert an entry into the table.
    public void executeUpdate(String sql) throws SQLException {
        System.out.println("Executing update: " + sql);
        rowSet.setCommand(sql);
        rowSet.execute();
        metaData = rowSet.getMetaData();
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        System.out.println("Rowcount: " + rowCount);
        try {
            return rowCount;

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return rowCount;

    }

    @Override
    public int getColumnCount() {
        
        try {
            System.out.println("column count: " + metaData.getColumnCount());
            return metaData.getColumnCount();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rowSet.absolute(rowIndex + 1);
            return rowSet.getObject(columnIndex + 1);
        } catch (SQLException exc) {
            System.err.println(table + " getValueAt row" + rowIndex + " col " + columnIndex);
            exc.printStackTrace();
        }
        return "";
    }

    public void disconnectFromDB() throws SQLException {
        System.out.println("disconnection from DB");
        rowSet.close();
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
public class MartinHongJava2HW4 extends WindowAdapter {

    JTable authorTable;
    JTable isbnTable;
    JTable titlesTable;

    TableModel tmAuthors;
    TableModel tmIsbn;
    TableModel tmTitles;

    JTextField queryText;

    //author Panel
    JTextField apFirstName;
    JTextField apLastName;
    JTextField isbn;
    JTextField title;
    JTextField copyright;
    JTextField edition;
    JComboBox<String> authors;
    JButton apAddNew;
    JButton apDelete;
    JButton apModify;
    JButton addBookButton;

    JButton submit;
    JTextArea statusLabel;

    String url = "jdbc:derby://localhost:1527/books";

    private JTable createTable(String tableName) throws SQLException {
        TableModel tm = new TableModel(url, "admin", "admin", tableName);

        JTable table = new JTable(tm);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    System.out.println("Selected item: " + table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
                }
            }
        });
        return table;
    }

    private void updateAuthorsInComboBox() {

        ArrayList<String> authorsList = tmAuthors.getAuthors();
        System.out.println("Updating author combo box " + authorsList.toString());

        authors.removeAllItems();
        for (String a : authorsList) {
            authors.addItem(a);
        }
    }

    private JPanel assembleTitlesPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel userPanel = new JPanel(new GridLayout(6, 2));

        //add the user interaction part to the userPanel
        // ISBN information
        userPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        userPanel.add(new JLabel("ISBN"));
        isbn = new JTextField();
        userPanel.add(isbn);

        //title information
        title = new JTextField();
        userPanel.add(new JLabel("Title"));
        userPanel.add(title);

        //copyright
        copyright = new JTextField();
        userPanel.add(new JLabel("Copyright"));
        userPanel.add(copyright);

        //edition
        edition = new JTextField();
        userPanel.add(new JLabel("Edition"));
        userPanel.add(edition);

        //author combo box information
        ArrayList<String> authorsList = tmAuthors.getAuthors();
        authors = new JComboBox(authorsList.toArray());
        userPanel.add(authors);
        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //first insert the book into the titles tables
                //int ed = Integer.parseInt(edition.getText());
                int ed = 4;
                tmTitles.insertbook(title.getText(), isbn.getText(), copyright.getText(), ed);

                //next, associate the book with an author
                tmIsbn.linkAuthorToIsbn(tmAuthors.getAuthorID((String) authors.getSelectedItem()), isbn.getText());
            }
        });
        userPanel.add(addBookButton);

        //add the two sections together
        panel.add(userPanel);
        panel.add(scrollPane);
        return panel;
    }

    private JPanel assembleAuthorsPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel userPanel = new JPanel(new GridLayout(4, 3));
        userPanel.setMaximumSize(new Dimension(400, 400));
        //add the user interaction part to the userPanel
        userPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        userPanel.add(new JLabel("Author First Name"));
        apFirstName = new JTextField();
        userPanel.add(apFirstName);
        userPanel.add(new JPanel());

        userPanel.add(new JLabel("Author Last Name"));
        apLastName = new JTextField();
        userPanel.add(apLastName);
        userPanel.add(new JPanel());

        apAddNew = new JButton("Add New");
        apAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //perform steps to add entry
                tmAuthors.insertAuthor(apFirstName.getText(), apLastName.getText());
                updateAuthorsInComboBox();
            }
        });
        apAddNew.setPreferredSize(new Dimension(10, 10));
        apDelete = new JButton("Delete Selected Row");
        apDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //get the row ID of the current selected row from the table
                tmAuthors.deleteAuthor(authorTable.getSelectedRow() + 1);
                updateAuthorsInComboBox();
            }
        });
        apModify = new JButton("Modify Selected Row");
        apModify.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected Row is: " + authorTable.getSelectedRow());
                tmAuthors.modifyAuthor(authorTable.getSelectedRow() + 1, apFirstName.getText(), apLastName.getText());
                updateAuthorsInComboBox();
            }
        });
        userPanel.add(apAddNew);
        userPanel.add(apModify);
        userPanel.add(apDelete);

        //add the two sections together
        panel.add(userPanel);
        panel.add(scrollPane);
        return panel;
    }

    private JPanel assembleIsbnPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel userPanel = new JPanel(new GridLayout(4, 3));

        //add the user interaction part to the userPanel
        //add the two sections together
        panel.add(userPanel);
        panel.add(scrollPane);
        return panel;
    }

    public MartinHongJava2HW4() throws SQLException {
        authorTable = createTable("AUTHORS");
        tmAuthors = (TableModel) authorTable.getModel();
        isbnTable = createTable("authorISBN");
        tmIsbn = (TableModel) isbnTable.getModel();
        titlesTable = createTable("titles");
        tmTitles = (TableModel) titlesTable.getModel();

        JFrame frame = new JFrame("Martin Hong Java2 HW4 using RowSet");
        frame.setLayout(new GridLayout(1, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        frame.addWindowListener(this);

        JPanel authorPanel = assembleAuthorsPanel(new JScrollPane(authorTable));
        JPanel authorIsbnPanel = assembleIsbnPanel(new JScrollPane(isbnTable));
        JPanel titlesPanel = assembleTitlesPanel(new JScrollPane(titlesTable));
        frame.add(authorPanel);
        frame.add(authorIsbnPanel);
        frame.add(titlesPanel);

        frame.setSize(1400, 700);
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MartinHongJava2HW4 hw4 = new MartinHongJava2HW4();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window closing event");

        //when the user closes the event, call to terminate the SQL connections
        try {
            tmIsbn.disconnectFromDB();
            tmAuthors.disconnectFromDB();
            tmTitles.disconnectFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(MartinHongJava2HW4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
