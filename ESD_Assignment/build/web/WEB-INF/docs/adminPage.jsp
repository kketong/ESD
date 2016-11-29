<%@page import="java.util.Arrays"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
    <body>
        <h1 align="center">Admin Dashboard</h1>
        <p>
        <div class="functionality" align="center">
            <form method="GET" action="<%=request.getContextPath()%>/AdminController">
                <input name="action" type=submit value='Check Approvals' class="button">
                <input name="action" type=submit value='approveMember' class="button">
                <input name="action" type=submit value='approveClaim' class="button">
            </form>
        </div>
        <p>
            <%
                String[] results = (String[]) request.getAttribute("output");
                String[] parts;
                String id;
                String status;
                String temp = "";

                for (int i = 0; i < results.length; i++) {
                    parts = results[i].split("<");

                    id = parts[0];
                    status = parts[5];

                    temp += "Customer ID: " + id + " Status: " + status + "<br>";
                }
            %>
            <%=temp%>
        </p>
    </body>
</html>