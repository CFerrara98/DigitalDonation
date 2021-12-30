<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: matti
  Date: 27/12/2021
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../../css/mycss.css"/>
    <title>HomePage</title>
    <p>${utente.email}</p>
    <p>${utente.password}</p>
    <p>${utente.nome}</p>
    <p>${utente.codiceFiscale}</p>
    <p>${utente.cognome}</p>
    <p>${utente.toString()}</p>
    <p>${operatore.toString()}</p>


    <form:form action="/goLogin" method="get"><button style="margin: 0 auto;" type="submit" id="reg" class="btn btn-primary btn-block">VAI A LOGGARTI</button> </form:form>

    <form:form action="/generateEntity" method="get"><button style="margin: 0 auto;" type="submit" id="reg" class="btn btn-primary btn-block">Genera donatori</button> </form:form>

    <form:form action="/logout" method="get"><button style="margin: 0 auto;" type="submit" id="reg" class="btn btn-primary btn-block">LogOut</button> </form:form>

</head>
<body>
<p>Provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
</body>
</html>
