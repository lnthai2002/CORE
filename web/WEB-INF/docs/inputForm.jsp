<%@page import="Financial.FinancialServlet" contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script language="JavaScript">
    function setCmd(value)
    {
         document.requestForm.cmd.value = value;
    }
</script>
   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="requestForm" action="/financial" method="get">
            <input name="id" type="text" value="please enter id" maxlength="10"/>
            get<input type="radio" name="cmd" value="get" title="get">
            getAll<input type="radio" name="cmd" value="getAll" title="getAll">
            <input type="submit">
        </form>
    </body>
</html>
