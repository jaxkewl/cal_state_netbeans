<%-- 
    Document   : guestBookLogin
    Created on : Mar 14, 2015, 4:40:46 PM
    Author     : martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- page settings -->
<%@ page errorPage = "guestBookErrorPage.jsp" %>

<%-- beans used in this jsp --%>
<jsp:useBean id="guest" scope="page"
             class="GuestBean" />
<jsp:useBean id="guestData" scope="request"
             class="GuestDataBean"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Book Login</title>
    </head>
    <body>

        <jsp:setProperty name="guest" property="*" />

        <%  //start scriplet
            if (guest.getFirstName() == null
                    || guest.getLastName() == null
                    || guest.getEmail() == null) {
        %>

        <%--  Enter fixed template here --%>
        <form method="post" action="guestBookLogin.jsp">
            <p>Enter your first name, last name and email address to register in our guest book.</p>

            <table>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName"/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email"</td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"</td>
                </tr>
            </table>
        </form>
        <%
        } else {
            guestData.addGuest(guest);
        %>            
        <jsp:forward page="guestBookView.jsp"/>
        <% }
        %>

    </body>
</html>
