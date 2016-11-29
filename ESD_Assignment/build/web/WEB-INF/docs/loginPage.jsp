<h1 class="h1">Login Page</h1>
    <%@page import="java.util.ArrayList"%>
    <form action="<%=request.getContextPath()%>/docs/tryLogin">
        Username <input type="text" name="username"><br>
        Password <input type="password" name="password"><br>
        <input type="submit" value="Log in">
    </form>
    <%
        try {
            String status = (String) session.getAttribute("status");
            if (status.equals("User not found") || status.equals("Invalid Password")) {
                out.println("<h1>" + status + "</h1>");
            }
        } catch (NullPointerException ex) {
        }
    %>
    <h2>Register a new user</h2>
    <form action="<%=request.getContextPath()%>/docs/loginPage/registerNewUser">
        Name:<br><input type=text name='rName'><br>
        Address:<br><input type=text name='rAddress'><br>
        Date of Birth:<br><input type=text name='rDOB'><br>
        Username and password will be generated for you
        <br><input type=submit value='Register'>
    </form>

</html>