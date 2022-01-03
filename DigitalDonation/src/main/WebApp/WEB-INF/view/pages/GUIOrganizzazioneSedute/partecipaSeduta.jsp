<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 30/12/2021
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="partecipa Seduta">
    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1>&nbsp;</h1>
                    <!-- indietro -->
                    <a href="./goSeduteDisponibili" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>
                    <!-- Titolo -->
                    <h1 class=" mb-4" style="text-align: center">Seduta di donazione</h1>

                    <!-- Per dividere a blocchi-->
                    <div class="container mt-6" style="padding-top:60px;">

                        <!-- Seduta di donazione -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Seduta di donazione</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <h1>&nbsp;</h1>
                                    <h4 class=" mb-4" style="text-align: center">E' disponibile una seduta di donazione
                                        <br> a cui può partecipare :</h4>
                                    <h1>&nbsp;</h1>
                                    <!-- Dati della seduta -->
                                    <div class="container-sm">
                                        <div class="table-container">
                                            <table class="table  table-bordered table-hover">
                                                <thead class="thead-dark">
                                                <tr>
                                                    <th scope="col">Data</th>
                                                    <th scope="col">Luogo</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td><c:out value="${seduta.dataSeduta}"></c:out></td>
                                                    <td><c:out value="${seduta.luogo}"></c:out></td>
                                                </tr>
                                                <tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <h1>&nbsp;</h1>

                                    <!-- Checkbox -->
                                    <%--@elvariable id="feedbackForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.FeedbackForm"--%>
                                    <form:form action="./feedback" method="get"
                                               modelAttribute="feedbackForm" cssClass="user"
                                               enctype="application/x-www-form-urlencoded">

                                        <div class="form-check">
                                            <form:input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" path="feedback"/>
                                            <label class="form-check-label"> <h6 class="mb-4" style="text-align: left">Se vuole partecipare alla seduta spunti la casella e prema il tasto conferma. <br>
                                                Se non vuole partecipare alla seduta lasci la casella bianca e prema il tasto conferma.
                                            </h6>
                                            </label>
                                        </div>
                                    </form:form>

                                    <input type="submit" value="Conferma" class="btn btn-primary">
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
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