<%@page import="model.MemberModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Front"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
    </head>
    <h1>Member Page</h1>
    <% 
        String memberID = (String) getServletContext().getAttribute("currentUser");
        MemberModel memberModel = new MemberModel();
    %>
    Hello, <%=memberID%><br>
    Your password is: <%=memberModel.retrieveMemberPassword(memberID)%><br>
    Your account balance is: <%=memberModel.retrieveMemberBalance(memberID)%>
    <h2>Claims</h2>
    <table>
        <tr>
            <th>Date Submitted</th>
            <th>Claim Rationale</th>
            <th>Status</th>
            <th>Amount</th>
        </tr>
        <%
            List<String> claims = new ArrayList();
            claims = memberModel.getClaims(memberID);
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
    <form action="<%=request.getContextPath()%>/docs/memberPage/makeclaim">
        <input type="text" name="claimAmount" value="Amount">
        <input type="text" name="claimDescription" value="Description">
        <input type="submit" value="Make a claim">
    </form>
    <h2>Payments</h2>
    <table>
        <tr>
            <th>Type of Payment</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <%
            List<String> payments = new ArrayList();
            payments = memberModel.getPayments((String) getServletContext().getAttribute("currentUser"));
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
    <form action="<%=request.getContextPath()%>/docs/memberPage/makepayment">
        <input type="text" name="paymentAmount" value="Amount">
        <input type="submit" value="Make a payment">
    </form>
</html>