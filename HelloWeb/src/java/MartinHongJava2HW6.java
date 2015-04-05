/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin
 */
public class MartinHongJava2HW6 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.

        String fname = req.getParameter("firstname");
        String lname = req.getParameter("lastname");
        String email = req.getParameter("email");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // start XHTML document
        out.println("<?xml version = \"1.0\"?>");

        out.printf("%s%s%s", "<!DOCTYPE html PUBLIC",
                " \"-//W3C//DTD XHTML 1.0 Strict//EN\"",
                " \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");

        out.println(
                "<html xmlns = \"http://www.w3.org/1999/xhtml\">");

        // head section of document
        out.println("<head>");
        out.println("<title>Martin Hong HW6</title>");
        out.println("</head>");

        // body section of document
        out.println("<body>");
        out.println("<h1>Martin Hong HW6</h1>");
        out.println("<p>");
        out.println("<h2>Hello, " + fname + " " + lname + " with email " + email + "</h2>");
        out.println("</body>");

        // end XHTML document
        out.println("</html>");

        out.close();
    }

}
