<h1>HEADER</h1>
<<<<<<< HEAD
<p><form action="<%=request.getContextPath()%>/docs/homePage">
    <input type=submit value='Home Page' class="button">
</form>
<form action="<%=request.getContextPath()%>/docs/loginPage">
    <input type=submit value='Login Page' class="button">
</form>
<form action="<%=request.getContextPath()%>/docs/memberPage">
    <input type=submit value='Member Page' class="button">
</form>
<form action="<%=request.getContextPath()%>/docs/adminPage">
    <input type=submit value='Admin Page' class="button">
</form></p>
=======

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/web.css" media="screen" />

<div class="navigation">
    <a href="<%=request.getContextPath()%>/docs/homePage">home</a>
    / <a href="<%=request.getContextPath()%>/docs/loginPage">login</a>
    / <a href="<%=request.getContextPath()%>/docs/memberPage">member page</a>
    / <a href="<%=request.getContextPath()%>/docs/adminPage">admin page</a> /
</div>

<!--<%=request.getAttribute("catalog")%> <br>-->
<!--<%=controller.Front.dbm.getUser("me-aydin").toString()%>-->
>>>>>>> refs/remotes/origin/master
