<%--
  Created by IntelliJ IDEA.
  User: elpid
  Date: 29/12/2021
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isErrorPage="true" %>

<z:layout pageTitle="dashboardDonatore">

    <div class="objects">
        <img class="object_face_rocket" src="${pageContext.request.contextPath}/resources/img/errorImg/triste.png" width="80px">

        <div class="box_face">
            <img class="object_face" src="${pageContext.request.contextPath}/resources/img/errorImg/triste.png" width="140px">
        </div>

</z:layout>