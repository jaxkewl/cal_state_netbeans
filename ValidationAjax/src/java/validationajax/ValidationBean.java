// ValidationBean.java
// Validating user input.
package validation;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "validationBean")
public class ValidationBean implements Serializable {

    private String name;
    private String email;
    private String phone;
    private String radioSelection;

    public String getRadioSelection() {
        return radioSelection;
    }

    public void setRadioSelection(String radioSelection) {
        this.radioSelection = radioSelection;
    }

    // return the name String
    public String getName() {
        return name;
    } // end method getName

    // set the name String
    public void setName(String name) {
        this.name = name;
    } // end method setName

    // return the email String
    public String getEmail() {
        return email;
    } // end method getEmail

    // set the email String
    public void setEmail(String email) {
        this.email = email;
    } // end method setEmail

    // return the phone String
    public String getPhone() {
        return phone;
    } // end method getPhone

    // set the phone String
    public void setPhone(String phone) {
        this.phone = phone;
    } // end method setPhone

    // returns result for rendering on the client
    public String getResult() {
        if (name != null && email != null && phone != null) {
            return "<p style=\"background-color:yellow;width:600px;"
                    + "padding:5px\">Name: " + getName() + "<br/>E-Mail: "
                    + getEmail() + "<br/>Phone: " + getPhone() + "<br/> Radio Selection: " + getRadioSelection() + "</p>";
        } else {
            return ""; // request has not yet been made
        }
    } // end method getResult
} // end class ValidationBean

/**
 * ***********************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors* and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 * **********************************************************************
 */
