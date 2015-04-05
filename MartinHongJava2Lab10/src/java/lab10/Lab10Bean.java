package lab10;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author martin
 */
@ManagedBean
@RequestScoped
public class Lab10Bean {

    private String firstName;
    private String radioSelection;

    /**
     * Creates a new instance of Lab9Bean
     */
    public Lab10Bean() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRadioSelection() {
        return radioSelection;
    }

    public void setRadioSelection(String radioSelection) {
        this.radioSelection = radioSelection;
    }

    public String getResponse() {

        if (null != radioSelection && null != getFirstName()) {
            return "<p style=\"background-color:yellow;width:400px;"
                    + "padding:5px\">First Name: " + getFirstName() + "<br/>"
                    + "Radio Selection: " + getRadioSelection() + "</p>";
        } else {
            return "";
        }
    }

}
