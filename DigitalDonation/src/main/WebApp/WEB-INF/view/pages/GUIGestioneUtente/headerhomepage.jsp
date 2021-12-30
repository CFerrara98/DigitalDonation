<%--
  Created by IntelliJ IDEA.
  User: utente
  Date: 30/12/2021
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<z:layout pageTitle="headerhomepage">

<!-- Topbar -->

<nav class="navbar navbar-expand-lg navbar-light bg-white">

    <!-- Logo -->
    <a class="navbar-brand" href="#"><img class="rounded-circle" src="../../resources/img/logo.png" alt="..." style="width:50px"></a>

    <!-- Topbar scritta Digital Donation -->
    <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
        <div class="input-group">
            <h1 class="h3 mb-0 text-gray-800">DIGITAL DONATION</h1>
        </div>
    </form>

    <!-- Topbar Login/Logout -->
    <ul class="navbar-nav ml-auto">
        <div class="topbar-divider d-none d-sm-block"></div>
        <a class="btn btn-primary" href="./goLogin" role="button">Login</a>

    </ul>

</nav>
<!-- End of Topbar -->

</z:layout>
