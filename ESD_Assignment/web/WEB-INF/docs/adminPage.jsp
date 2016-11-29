<%@page import="java.util.Arrays"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
    <body>
        <h1 align="center">Admin Dashboard</h1>
        <p>
        <div class="functionality">
            <form method="GET" action="<%=request.getContextPath()%>/AdminController">
                <input name="action" type=submit value='Check Approvals' class="button">
                <input name="action" type=submit value='Approve Outstanding' class="button">
                <input name="action" type=submit value='List Claims' class="button">
                <input name="action" type=submit value='Approve Claims' class="button">
                <input name="action" type=submit value='Reject Claims' class="button">
                <input name ="id" type="input" value="Member or Claim ID">
            </form>
        </div>
        <p>
            <%
                String temp = "";
                if (request.getParameter("action").equals("Check Approvals")) {
                    String[] results = (String[]) request.getAttribute("output");
                    String[] parts;

                    for (int i = 0; i < results.length; i++) {
                        parts = results[i].split("<");
                        temp += "Customer ID: " + parts[0] + " Status: " + parts[5] + "<br>";
                    }
                } else if (request.getParameter("action").equals("Approve Outstanding")) {
                    String result = (String) request.getAttribute("output");
                    temp = result;
                } else if (request.getParameter("action").equals("Approve Claims")) {

                } else if (request.getParameter("action").equals("List Claims")) {
                    List<String> claims = (List) request.getAttribute("output");
                    //Iterator it = claims.iterator();
                    //while (it.hasNext()) {
                    //    out.print("<br>Claim: " + it.next());
                    //}
                    for (String claim : claims) {
                        String parts[] = claim.split("<");
                        out.println("Claim ID: " + parts[0] + 
                                " Member ID: " + parts[1] + 
                                " Date: " + parts[2] +
                                " Reason: " + parts[3] +
                                " Status: " + parts[4] +
                                " Amount: " + parts[5] +
                                "<br>");
                    }
                }
            %>
            <%=temp%>
        </p>
    </body>
</html>