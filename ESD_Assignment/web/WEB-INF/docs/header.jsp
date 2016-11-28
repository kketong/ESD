<h1>HEADER</h1>
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

<%=request.getAttribute("catalog")%> <br>
<%=controller.Front.dbm.registerNewMember("george smith", "18 Douglas Road, Bristol, BS7 0JD", "1994-03-25")%>