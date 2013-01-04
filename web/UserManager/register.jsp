<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSPRegistrationForm</title>
    </head>
    <body>
        <form name="registration" action="register">
            <table border="0">
                <thead>
                    <tr>
                        <th>Please fill out the form below to register</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Login name/username</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td>email address</td>
                        <td><input type="text" name="email" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="submit" /></td>
                    </tr>
                </tbody>
            </table>
            
            
            
            
            
            
        </form>
    <h1>JSP Page</h1>
    
    <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
    <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    --%>
    
    </body>
</html>
