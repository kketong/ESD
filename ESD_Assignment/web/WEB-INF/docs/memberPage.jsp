
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
    <table>
        <%
            List<Integer> claimIds = Front.dbm.getClaimIds((String) request.getAttribute("username"));
            List claims = new ArrayList();
            for (int id : claimIds) {
                claims.add(Front.dbm.getClaimById(Integer.toString(id)));
            }
            return claims;
        %>
    </table>
    <form action="<%=request.getContextPath()%>/docs/loginPage">
    </form>
</html>

<%String currentUserID = getServletContext().getAttribute("currentUser").toString();%>
Your username is: <%=currentUserID%><br>
Your password is:
