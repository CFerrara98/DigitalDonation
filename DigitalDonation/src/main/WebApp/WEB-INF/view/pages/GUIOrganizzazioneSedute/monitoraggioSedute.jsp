<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 30/12/2021
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="monitoraggio Sedute">

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
                            <a href="./dashboardOperatore" role="button"> <i
                                    class="fas fa-arrow-left float-left icone"></i></a>

                            <!-- titolo -->
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Monitoraggio Sedute</h1>
                            <c:choose>
                                <c:when test="${success != null}">
                                    <div class="alert alert-success" role="alert">
                                        <h4 class="alert-heading"><i class="fas fa-calendar-check"
                                                                     style="font-size: 36px"> </i> &nbsp <b>Complimenti!</b>  ${success}
                                        </h4>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <!-- Sezione Elenco monitoraggi -->


                    <div class="card shadow mb-4 ">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Elenco sedute da svolgersi:</h6>
                        </div>


                        <c:forEach begin="0" end="${listaSedute.size()-1}" step="1"
                                   var="i"> <!-- controllare il dollaro -->

                            <!-- seduta %n% -->
                            <div class="card shadow mb-4">
                                <div class=" m-0 card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary h6">Seduta <c:out
                                            value="${i+1}"></c:out>:</h6>
                                </div>
                                <div class="card-body">

                                    <!-- Tabella -->

                                    <div class="table-container">
                                        <table class="table spazio-table ">
                                            <thead>
                                            <tr>
                                                <th scope="col"></th>
                                                <th scope="col">Elenco partecipanti</th>
                                                <th scope="col">Modifica seduta</th>
                                                <th scope="col">Elimina seduta</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th width="1000">
                                                    <h4 class="small font-weight-bold">Data: <c:out
                                                            value="${listaSedute.get(i).dataSeduta.date}"></c:out>
                                                        /
                                                        <c:out
                                                                value="${listaSedute.get(i).dataSeduta.month + 1}"></c:out>
                                                        /
                                                        <c:out
                                                                value="${listaSedute.get(i).dataSeduta.year + 1900}"></c:out>
                                                    </h4>
                                                    <h4 class="small font-weight-bold">Ora di inizio: <c:out
                                                            value="${listaSedute.get(i).oraInizio}"></c:out></h4>
                                                    <h4 class="small font-weight-bold">Ora di fine: <c:out
                                                            value="${listaSedute.get(i).oraFine}"></c:out></h4>
                                                    <h4 class="small font-weight-bold">Luogo:<c:out
                                                            value="${listaSedute.get(i).luogo}"></c:out></h4>
                                                </th>
                                                <td scope="row">
                                                    <!-- Bottom Elenco partecipanti -->
                                                    <span class="float-center col-xl-1 col-md-6 mb-2">
                            <a href="/goElencoPartecipanti?idSeduta=<c:out value="${listaSedute.get(i).idSeduta}"></c:out>"
                               role="button">
                                <i class="fas fa-user icone"></i>
                            </a>
                        </span>
                                                </td>

                                                <!-- Bottom modifica seduta -->
                                                <td>
                        <span class="float-center col-xl-1 col-md-6 mb-2">
                            <a href="/goModicaSeduta?idSeduta=<c:out value="${listaSedute.get(i).idSeduta}"></c:out>"
                               role="button"> <i class="fas fa-cogs icone"></i></a>
                        </span>
                                                </td>

                                                <!-- Bottom Elimina seduta -->
                                                <td>
                        <span class="float-center col-xl-1 col-md-6 mb-2">
                            <a href="/eliminaSeduta?idSeduta=<c:out value="${listaSedute.get(i).idSeduta}"></c:out>"
                               role="button"> <i class="fas fa-times icone"></i></a>
                        </span>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <!--FINE Tabella -->

                                </div>

                                <!-- fine elenco monitoraggio -->
                            </div>
                        </c:forEach>
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