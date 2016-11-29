<%-- 
    Document   : main
    Created on : 09-Nov-2016, 12:12:31
    Author     : jacka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/web.css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main JSP</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" /> 

        <%String included = (String) request.getAttribute("includedView");%>
        <br/>
        <jsp:include page="<%=included%>" flush="true" /> 

        <jsp:include page="footer.jsp" flush="true" /> 
    </body>
</html>
