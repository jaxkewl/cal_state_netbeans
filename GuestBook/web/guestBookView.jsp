<%-- 
    Document   : guestBookView
    Created on : Mar 14, 2015, 5:01:08 PM
    Author     : martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page errorPage ="guestBookErrorPage.jsp" %>
<%@ page import = "java.util.*" %>

<jsp:useBean id="guestData" scope="request" class="GuestDataBean" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest List</title>
    </head>
    <body>
        <p>Guest List</p>
        <table>
            <thead>
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Email</th>
                </tr>
            </thead>

            <tbody>
                <% List guestList = guestData.getGuestList();
                    Iterator guestListIterator = guestList.iterator();
                    GuestBean guest;

                    while (guestListIterator.hasNext()) {
                        guest = (GuestBean) guestListIterator.next();
                %>
                <tr>
                    <td><%= guest.getFirstName()%></td>
                    <td><%= guest.getLastName()%></td>
                    <td><a href = "mailto:<%= guest.getEmail()%>"><%= guest.getEmail()%></a></td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>
    </body>
</html>
