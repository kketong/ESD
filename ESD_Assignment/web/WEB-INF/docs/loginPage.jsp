<h1>Login Page</h1>
This login session will only last 20 minutes

<h2>Login as an existing user</h2>
<form action="<%=request.getContextPath()%>/docs/memberPage">
    Username:<br><input type=text name='lUsername'><br>
    Password:<br><input type=text name='lPassword'><br>
    <br><input type=submit value='Member Page'>
</form>

<h2>Register a new user</h2>
<form action="<%=request.getContextPath()%>/docs/memberPage">
    First Name:<br><input type=text name='rFirstName'><br>
    Last Name:<br><input type=text name='rLastName'><br>
    Date of Birth:<br><input type=text name='rDateOfBirth'><br>
    Address:<br><input type=text name='rAddress'><br>
    Username and password will be generated for you
    <br><input type=submit value='Member Page'>
</form>