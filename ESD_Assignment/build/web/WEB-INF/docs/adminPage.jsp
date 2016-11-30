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
                <br>
                <input name ="mem_id" type="input" value="Member ID">
                <input name="action" type=submit value='Approve Outstanding Member' class="button">
                <br>
                <input name ="id" type="input" value="Member or Claim ID">
                <input name="action" type=submit value='List Claims' class="button">
                <input name="action" type=submit value='Approve Claim' class="button">
                <input name="action" type=submit value='Reject Claim' class="button">
            </form>
        </div>
        <p>
            <%
                if (request.getParameter("action").equals("Check Approvals")) {
                    List<String> approvals = (List) request.getAttribute("output");

                    for (String approval : approvals) {
                        String parts[] = approval.split("<");
                        out.println("Member ID: " + parts[0]
                                + ", Status: " + parts[5]
                                + "<br>");
                    }
                } else if (request.getParameter("action").equals("Approve Outstanding Member")) {
                    String result = (String) request.getAttribute("output");
                    out.print(result);
                } else if (request.getParameter("action").equals("List Claims")) {
                    List<String> claims = (List) request.getAttribute("output");
                    for (String claim : claims) {
                        String parts[] = claim.split("<");
                        out.println("Claim ID: " + parts[0]
                                + ", Member ID: " + parts[1]
                                + ", Date: " + parts[2]
                                + ", Reason: " + parts[3]
                                + ", Status: " + parts[4]
                                + ", Amount: " + parts[5]
                                + "<br>");
                    }
                }
            %>
        </p>
    </body>
</html>