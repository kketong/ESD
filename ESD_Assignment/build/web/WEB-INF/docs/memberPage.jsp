<%@page import="java.util.List"%>
<%@page import="controller.Front"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Page</title>
    </head>
    <%
        List<Integer> claimIds = Front.dbm.getClaimIds(session.getId());
        List<Integer> paymentIds = Front.dbm.getPaymentIds(session.getId());
        
    %>
    <form action="<%=request.getContextPath()%>/docs/loginPage">
    </form>
</html>

<%String currentUserID = getServletContext().getAttribute("currentUser").toString();%>
Your username is: <%=currentUserID%><br>
Your password is:
