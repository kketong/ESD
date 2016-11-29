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
    <h1> Claims </h1>
    <table>
        <tr>
            <th> Date Submitted </th>
            <th>Claim Rationale</th>
            <th>Status</th>
            <th>Amount</th>
        </tr>
        <%
            List<String> claims = new ArrayList();
            claims = Front.dbm.getClaims((String) getServletContext().getAttribute("currentUser"));
            for (String claim : claims) {
                String[] claimString = claim.split("<");
                out.println("<tr>"
                        + "<th>" + claimString[2] + "</th>"
                        + "<th>" + claimString[3] + "</th>"
                        + "<th>" + claimString[4] + "</th>"
                        + "<th>" + claimString[5] + "</th>"
                        + "</tr>");
            }
        %>
    </table>
    <h1> Payments </h1>
    <table>
        <tr>
            <th>Type of Payment</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <%
            List<String> payments = new ArrayList();
            payments = Front.dbm.getPayments((String) getServletContext().getAttribute("currentUser"));
            for (String payment : payments) {
                String[] paymentDetails = payment.split("<");
                out.println("<tr>"
                        + "<th>" + paymentDetails[2] + "</th>"
                        + "<th>" + paymentDetails[3] + "</th>"
                        + "<th>" + paymentDetails[4] + "</th>"
                        + "</tr>");
            }
        %>
    </table>
    <form action="<%=request.getContextPath()%>/docs/loginPage">
    </form>
</html>
