<%@page import="java.util.ArrayList"%>
<%@page import="controller.Front"%>
<%@page import="java.util.List"%>
<%@page import="model.MemberModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
    </head>
    <% if (session.getAttribute("isNewUser") == "true") {
            String currentUserID = getServletContext().getAttribute("currentUser").toString();
            out.println("Your username is: " + currentUserID + "<br>"
                    + "Your password is " );
        }
    %>
    <table>
        <%
            List<Integer> claimIds = Front.dbm.getClaimIds((String) getServletContext().getAttribute("currentUser"));
            for (int id : claimIds) {
                String[] claimString = (Front.dbm.getClaimById(Integer.toString(id))).split("<");
                out.println("<tr>"
                        + "<th>" + claimString[3] + "</th>"
                        + "<th>" + claimString[4] + "</th>"
                        + "<th>" + claimString[5] + "</th>"
                        + "</tr>");
            }
        %>
    </table>
    <form action="<%=request.getContextPath()%>/docs/loginPage">
    </form>
</html>
