/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webtime;

import java.text.DateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author martin
 */
@ManagedBean(name="webTimeBean")
@RequestScoped
public class WebTimeBean {

    /**
     * Creates a new instance of WebTimeBean
     */
    public WebTimeBean() {
    }

    public String getTime() {
        return DateFormat.getTimeInstance(DateFormat.LONG).format(new Date());
    }
}
