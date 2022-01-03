<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 03/01/22
  Time: 16:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="autodichiarazioneIndisponibilita">
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <div class="card-body">
                        <!-- indietro -->
                        <a href="dashboardDonatore.html" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>

                        <!-- titolo -->
                        <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Autodichiarazione d'indisponibilità</h1>

                    </div>
                </div>
                <div class="container">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row justify-content-md-center">
                                <div class="col-lg-7">
                                    <div class="p-5">

                                        <div class="text-center">
                                            <h3 class="h4 text-gray-900 mb-4">Compila il form per l'autodichiarazione d'indisponibilità</h3>
                                        </div>
                                            <%--@elvariable id="autodichiarazioneForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm"--%>
                                        <form:form action="./autodichiarazioneIndisponibilita" method="post" modelAttribute="autodichiarazioneForm" cssClass="user" enctype="application/x-www-form-urlencoded">
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Data prossima disponibilità: </p>
                                                <c:choose>
                                                    <c:when test="${DataDisponibilitaError == null}">
                                                        <form:input type="date" class="form-control form-control-user" id="dataDisponibilita" placeholder="Data prossima disponibilità:" path="dataProssimaDisponibilita"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="date" class="form-control form-control-user is-invalid" id="dataDisponibilita" placeholder="Data prossima disponibilità:" path="dataProssimaDisponibilita"/>
                                                        <span class="myError">${DataDisponibilitaError}</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                            <div class="form-group">
                                                <p style="color:#4e73df;">Motivazione di indisponibilità: </p>
                                                <c:choose>
                                                    <c:when test="${MotivazioneError == null}">
                                                        <form:textarea class="textarea form-control" id="exampleMotivazione" placeholder="es. Abuso di sostanze stupefacenti" rows="4" path="motivazioni"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:textarea class="textarea form-control" id="exampleMotivazione" placeholder="es. Abuso di sostanze stupefacenti" rows="4" path="motivazioni"/>
                                                        <span class="myError">${MotivazioneError}</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        <form class="user">
                                            <br>
                                            <input type="submit" value="Conferma" class="btn btn-primary btn-user btn-block">
                                        </form>
                                            </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->
                </div>
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white ">
                <div class="container my-auto ">
                    <div class="copyright text-center my-auto ">
                        <span>Digital Donation 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
</z:layout>