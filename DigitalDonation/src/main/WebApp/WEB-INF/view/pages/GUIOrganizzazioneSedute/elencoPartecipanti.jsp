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


<z:layout pageTitle="elencoPartecipanti">

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
                            <a href="./visualizzaElencoSedute" role="button"> <i
                                    class="fas fa-arrow-left float-left icone"></i></a>

                            <!-- titolo -->
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Elenco partecipanti</h1>

                            <c:choose>
                                <c:when test="${success != null}">
                                    <br>
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
                            <h6 class="m-0 font-weight-bold text-primary">Elenco partecipanti alla seduta del
                                <c:out value="${dataSeduta}"></c:out> presso
                                <c:out value="${luogoSeduta}"></c:out>:</h6>
                        </div>
                        <h6>&nbsp;</h6>

                        <!-- inserimento utente guest -->
                        <div class="card shadow mb-4">
                            <div class=" m-0 card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary h6"> Inserimento utente</h6>
                            </div>
                            <div class="card-body">


                                <!-- Bottom indisponibilità seduta -->
                                <span class="float-left col-xl-1 col-md-6 mb-2">

                                    <a href="/goInserimentoUtenteGuest?idSeduta=<c:out value="${idSeduta}"></c:out>"
                                         <i class="fas fa-plus icone"></i> </a>
                                </span>
                                <h2>
                                    <a href="/goInserimentoUtenteGuest?idSeduta=<c:out value="${idSeduta}"></c:out>">
                                        Inserimento Utente Guest</a>
                                </h2>

                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${listaPartecipanti.get(0) != null}">
                                <c:forEach begin="0" end="${listaUtenti.size()-1}" step="1"
                                           var="i">


                                    <!-- pRTECIPANTE -->
                                    <div class="card shadow mb-4">
                                        <div class=" m-0 card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary h6">
                                                <c:out value="${listaUtenti.get(i).nome}"></c:out>
                                                <c:out value="${listaUtenti.get(i).cognome}"></c:out></h6>
                                        </div>
                                        <div class="card-body">
                                            <!-- tabella -->
                                            <div class="table-container">
                                                <table class="table  ">
                                                    <thead>
                                                    <tr>
                                                        <th scope="col">
                                                            <c:choose>
                                                                <c:when test="${listaPartecipanti.get(i)}">
                                                                    <div class="alert alert-primary" role="alert" style="width: 175px">
                                                                        <i class="fas fa-user" style="font-size: 30px"> </i>
                                                                        <span class="m-0 font-weight-bold text-primary h5"> &nbsp Donatore </span>
                                                                    </div>
                                                                 </c:when>
                                                                <c:otherwise>
                                                                    <div class="alert alert-dark" role="alert" style="width: 175px">
                                                                        <i class="fas fa-user-plus" style="font-size: 30px"></i>
                                                                        <span class="m-0 font-weight-bold text-primary h5"> &nbsp Guest </span>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </th>
                                                        <th scope="col">Indisponibilità</th>
                                                        <th scope="col">Conferma donazione</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <th width="1000">
                                                            <c:choose>

                                                                <c:when test="${listaPartecipanti.get(i)}">
                                                                    <h4 class="small font-weight-bold ">Codice Fiscale
                                                                        Utente: <c:out
                                                                                value="${listaUtenti.get(i).codiceFiscale}"></c:out></h4>
                                                                    <h4 class="small font-weight-bold ">Email: <c:out
                                                                            value="${listaUtenti.get(i).email}"></c:out></h4>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <h4 class="small font-weight-bold ">Telefono: <c:out
                                                                            value="${listaUtenti.get(i).telefono}"></c:out></h4>
                                                                    <h4 class="small font-weight-bold ">Codice Fiscale
                                                                        Guest: <c:out
                                                                                value="${listaUtenti.get(i).codiceFiscaleGuest}"></c:out></h4>
                                                                    <h4 class="small font-weight-bold ">Gruppo
                                                                        sanguigno:
                                                                        <c:out
                                                                                value="${listaUtenti.get(i).gruppoSanguigno}"></c:out></h4>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </th>
                                                        <!-- Bottom indisponibilità seduta -->
                                                        <th scope="row">
                                                        <span class="float-center col-xl-1 col-md-6 mb-2">
                                                        <a href=".html" role="button"> <i
                                                                class="far fa-times-circle icone"></i></a>
                                                    </span></th>
                                                        <!-- Bottom conferma donazione partecipanti -->
                                                        <td>
                                                        <span class="float-center col-xl-1 col-md-6 mb-2">
                                                                <a href=".html" role="button">
                                                                    <i class="far fa-check-circle icone"></i>
                                                                </a>
                                                        </span>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>
                            </c:when>
                            <c:otherwise>

                                <h6 align="center"> Nessun partecipante all'interno della seduta </h6>

                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!-- fine elenco monitoraggio -->
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