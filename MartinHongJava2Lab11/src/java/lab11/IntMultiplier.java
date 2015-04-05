/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author martin
 */
@WebService(serviceName = "IntMultiplier")
public class IntMultiplier {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "intMultiplier")
    public String intMultiplier(@WebParam(name = "input") String input) {
        Integer inputInt = Integer.parseInt(input);
        inputInt *= 3;
        return inputInt.toString();
    }
}
