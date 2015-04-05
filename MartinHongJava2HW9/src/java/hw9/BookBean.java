/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw9;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author martin
 */
@ManagedBean
@SessionScoped  //make sure this is session scoped, so a new bean is not created each time
public class BookBean implements Serializable {

    //contains our reference of books
    private final static HashMap<String, Double> booksMap = new HashMap<String, Double>();

    //static initializer block, runs only once
    static {
        booksMap.put("Book1", 49.99);
        booksMap.put("Book2", 59.99);
        booksMap.put("Book3", 43.99);
        booksMap.put("Book4", 67.99);
        booksMap.put("Book5", 12.99);
        booksMap.put("Book6", 89.99);
    }

    private double total;
    private String selection;
    private ArrayList<String> selections = new ArrayList<>();

    /**
     * Creates a new instance of BookBean
     */
    public BookBean() {

    }

    public String getTotal() {
        double total = 0.0;
        for (String str : selections) {
            total += booksMap.get(str);
        }
        return Double.toString(total);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
        selections.add(selection);
    }

    public String[] getSelections() {
        //create a custom selections that containst the book name plus the price
        String[] customSelections = new String[selections.size()];

        for (int i = 0; i < selections.size(); i++) {
            //booksMap contains the price of each book
            customSelections[i] = selections.get(i) + " $" + booksMap.get(selections.get(i));
        }

        return customSelections;
    }

    public String getResponse() {
        String response = "";

        response = "<p style=\"background-color:yellow;width:400px;"
                + "padding:5px\">For debugging purposes<br/>"
                + "Recent Selection: " + getSelection() + "<br/>"
                + "Selections Size: " + getSelections().length + "<br/>"
                + "total: " + getTotal() + "</p>";

        return response;
    }
}
