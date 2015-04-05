<%-- 
    Document   : index
    Created on : Mar 14, 2015, 2:32:48 PM
    Author     : martin
--%>

<!-- 
Java2 HW7
Create an HTML form with three input fields: first name, last name and e-mail. 
Use scriplets to handle get requests. 
Verify all input fields are non-null and display them back to the client. -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Martin Hong Java2 HW7</h2>


        <%
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            if (null != firstName) {
                //if (null == firstName || null == lastName || null == email) {
        %>

        <h1>Hello <%= firstName%> <%= lastName%> @ <%= email%> </h1> 


        <% } else {
        %>

        <form action="index.jsp" method="get">
            <p>First Name: <input type="text" name="firstName"/></p>
            <p>Last Name: <input type="text" name="lastName"/></p>
            <p>Email: <input type="text" name="email"/></p>
            <input type="submit" name="submit"/>
        </form>

        <%
            }
        %>

    </body>
</html>
