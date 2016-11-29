<html>
    <body>
        <h1 align="center">Admin Dashboard</h1>
        <p>
        <div class="functionality" align="center">
            <form method="GET" action="<%=request.getContextPath()%>/AdminController">
                <input name="action" type=submit value='list' class="button">
                <input name="action" type=submit value='approveMember' class="button">
                <input name="action" type=submit value='approveClaim' class="button">
            </form>
        </div>
        <p>
            <%
                out.print(request.getAttribute("output"));
            %>
        </p>
    </body>
</html>