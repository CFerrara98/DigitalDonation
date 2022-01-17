<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="Sedute Disponibili">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">


                <!-- Begin Page Content -->
                <div class="container-fluid" style="background-color:#eaeef1; padding-top: 30px;">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <div class="card-body">
                            <!-- indietro -->
                            <a href="./dashboardDonatore" role="button"> <i
                                    class="fas fa-arrow-left float-left icone"></i></a>
                            <!-- Titolo -->
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Elenco sedute ancora da
                                svolgersi:</h1>

                        </div>
                    </div>

                    <div class="card shadow mb-4 ">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Elenco sedute:</h6>
                        </div>
                        <h6>&nbsp;</h6>

                <c:choose>
                    <c:when test="${listaSedutePrenotabili.size() > 0}">
                        <c:forEach begin="0" end="${listaSedutePrenotabili.size()-1}" step="1"
                                   var="i">
                            <!-- prima seduta-->
                            <div class="card shadow mb-4">

                                <div class=" m-0 card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary h6">Seduta <c:out
                                            value="${i+1}"></c:out>:</h6>
                                </div>
                                <div class="card-body">

                                    <!-- tabella-->
                                    <div class="table-botton">
                                        <table class="table  ">
                                            <thead>
                                            <tr>
                                                <td scope="col"><h3 class="small font-weight-bold"> Luogo:<c:out
                                                        value="${listaSedutePrenotabili.get(i).luogo}"></c:out></h3>
                                                    <h3 class="small font-weight-bold"> Data: <c:out
                                                            value="${listaSedutePrenotabili.get(i).dataSeduta.date}"></c:out>
                                                        /
                                                        <c:out
                                                                value="${listaSedutePrenotabili.get(i).dataSeduta.month + 1}"></c:out>
                                                        /
                                                        <c:out
                                                                value="${listaSedutePrenotabili.get(i).dataSeduta.year + 1900}"></c:out></h3>
                                                <td scope="col"><span class=" float-right">
                                                    <a class="btn btn-primary botton-sm large"
                                                       href="./goPartecipaSeduta?idSeduta=<c:out value="${listaSedutePrenotabili.get(i).idSeduta}"></c:out>"
                                                       role="button">Partecipa</a>
                                                 </span>
                                                </td>
                                            </tr>
                                            </thead>
                                        </table>
                                    </div>
                                    <!-- fine tabella-->
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h6 align="center"> Nessuna seduta disponibile per te </h6><br>
                    </c:otherwise>
                </c:choose>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- ======= Footer ======= -->
            <footer id="footer" class="sticky-footer bg-white">
                <div align="center" class="container my-auto">
                    <h3 style="color:#6C757D";>Digital Donation</h3>

                    <p style="color:#6C757D;"> Grazie per aver visitato il nostro sito!<br>
                        Qui trovi una piccola descrizione del nostro team, visita la pagina
                        <a href="./goAboutUs"> About us </a>
                    </p>


                    <div class="copyright" style="color:#6C757D;">
                        <h6 >&copy; Copyright 2021</h6><br>
                        Il trattamento dei dati personali è svolto nel rispetto dei diritti sulla privacy in base alla legge n. 675 del 31 dicembre 1996
                        <br> e dei diritti per condivisione dei dati personali come stabilito dalla legge n 675/96 del 2003.
                        <br>


                    </div>
                    <div class="credits">
                    </div>
                </div>

            </footer>
            <!-- End Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->


</z:layout>