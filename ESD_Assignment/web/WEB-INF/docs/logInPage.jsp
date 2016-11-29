<h1 class="h1">Login Page</h1>
This login session will only last 20 minutes

<h2 class="h2">Login as an existing user</h2>
<form action="<%=request.getContextPath()%>/docs/memberPage">
    Username:<br><input type=text name='lUsername'><br>
    Password:<br><input type=text name='lPassword'><br>
    <br><input type=submit value='Member Page'>
</form>

<h2 class="h2">Register a new user</h2>
<form action="<%=request.getContextPath()%>/docs/loginPage/registerNewUser">
    Name:<br><input type=text name='rName'><br>
    Address:<br><input type=text name='rAddress'><br>
    Date of Birth:<br><input type=text name='rDOB'><br>
    Username and password will be generated for you
    <br><input type=submit value='Login Page Register'>
</form>
