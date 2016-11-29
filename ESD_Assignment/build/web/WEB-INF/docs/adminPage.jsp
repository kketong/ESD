<html>
    <body>
        <h1 align="center">Admin Dashboard</h1>
        <p>
        <div class="functionality" align="center">
            <!-- Send to Admin Controller -->
            <form method="POST" action="<%=request.getContextPath()%>/AdminController">
                <p align="center">
                    <select name="action" size="1">
                        <option value="list">List approvals</option>
                        <option value="approveMember">Approve Member</option>
                        <option value="approveClaim">Approve Claim</option>
                    </select>
                </p>
                <br><br>
                <center>
                    <input type="SUBMIT">
                </center>
            </form>
                
                
        </div>
        <p>
            <%
                out.print(request.getAttribute("output"));
            %>
        </p>
    </body>
</html>