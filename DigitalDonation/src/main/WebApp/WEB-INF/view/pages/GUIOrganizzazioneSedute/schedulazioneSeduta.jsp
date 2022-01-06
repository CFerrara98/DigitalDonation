<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 30/12/2021
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<z:layout pageTitle="Schedulazione Seduta">

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
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Schedulazione seduta</h1>
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
                                                <h3 class="h4 text-gray-900 mb-4">Compila i campi per schedulare una
                                                    seduta</h3>
                                            </div>

                                            <%--@elvariable id="sedutaForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm"--%>
                                            <form:form action="./schedulazioneSeduta" method="post"
                                                       modelAttribute="sedutaForm" cssClass="user"
                                                       enctype="application/x-www-form-urlencoded">
                                                <div class="form-group row">
                                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                                        <p style="color:#4e73df;">Data seduta: </p>
                                                        <c:choose>
                                                            <c:when test="${DataError == null}">
                                                                <form:input type="date"
                                                                            class="form-control form-control-user"
                                                                            id="data" placeholder="Data Seduta:"
                                                                            path="dataSeduta"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="date"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="data"
                                                                            placeholder="Data Seduta:"
                                                                            path="dataSeduta"/>
                                                                <span class="myError">${DataError}</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                </div>
                                                <div class="form-group">

                                                    <p style="color:#4e73df;">Indirizzo della seduta: </p>
                                                    <c:choose>
                                                        <c:when test="${IndirizzoError == null}">
                                                            <form:input type="text"
                                                                        class="form-control form-control-user"
                                                                        id="indirizzo"
                                                                        placeholder="es. Via Giuseppe Verdi 1"
                                                                        path="indirizzo"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="text"
                                                                        class="form-control form-control-user is-invalid"
                                                                        id="indirizzo"
                                                                        placeholder="es. Via Giuseppe Verdi 1"
                                                                        path="indirizzo"/>

                                                            <span class="myError">${IndirizzoError}</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-4 mb-3 mb-sm-0">
                                                        <p style="color:#4e73df;">Città: </p>

                                                        <c:choose>
                                                            <c:when test="${CittaError == null}">

                                                                <form:input type="text"
                                                                            class="form-control form-control-user"
                                                                            id="citta" placeholder="es. Salerno"
                                                                            path="citta"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="text"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="citta" placeholder="es. Salerno"
                                                                            path="citta"/>
                                                                <span class="myError">${CittaError}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <p style="color:#4e73df;">CAP: </p>
                                                        <c:choose>
                                                            <c:when test="${CAPError == null}">
                                                                <form:input type="text"
                                                                            class="form-control form-control-user"
                                                                            id="cap"
                                                                            maxlength="5"
                                                                            placeholder="es. 84100"
                                                                            path="CAP"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="text"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="cap"
                                                                            maxlength="5"
                                                                            placeholder="es. 84100" path="CAP"/>
                                                                <span class="myError">${CAPError}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <p style="color:#4e73df;">Provincia: </p>
                                                        <c:choose>
                                                            <c:when test="${ProvinciaError == null}">
                                                                <form:input type="text"
                                                                            class="form-control form-control-user"
                                                                            id="provincia"
                                                                            maxlength="2"
                                                                            placeholder="es. SA"
                                                                            path="provincia"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="text"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="provincia" placeholder="es. SA"
                                                                            maxlength="2"
                                                                            path="provincia"/>
                                                                <span class="myError">${ProvinciaError}</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                                        <p style="color:#4e73df;">Orario di inizio: </p>
                                                        <c:choose>
                                                            <c:when test="${OraInizioError == null}">
                                                                <form:input type="time"
                                                                            class="form-control form-control-user"
                                                                            id="oraInizio"
                                                                            placeholder="Orario inizio:"
                                                                            path="orarioInizio"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="time"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="oraInizio" placeholder="Orario inizio:"
                                                                            path="orarioInizio"/>
                                                                <span class="myError">${OraInizioError}</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                    <div class="col-sm-6">
                                                        <p style="color:#4e73df;">Orario di fine: </p>
                                                        <c:choose>
                                                            <c:when test="${OraFineError == null}">

                                                                <form:input type="time"
                                                                            class="form-control form-control-user"
                                                                            id="oraFine" placeholder="Orario fine:"
                                                                            path="orarioFine"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="time"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="oraFine" placeholder="Orario fine:"
                                                                            path="orarioFine"/>
                                                                <span class="myError">${OraFineError}</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                                        <p style="color:#4e73df;">Data di inizio prenotazione: </p>
                                                        <c:choose>
                                                            <c:when test="${DataInizioError == null}">
                                                                <form:input type="date"
                                                                            class="form-control form-control-user"
                                                                            id="dataInizio"
                                                                            placeholder="Data inizio Prenotazione:"
                                                                            path="dataInizioPrenotazione"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="date"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="dataInizio"
                                                                            placeholder="Data inizio Prenotazione:"
                                                                            path="dataInizioPrenotazione"/>
                                                                <span class="myError">${DataInizioError}</span>
                                                            </c:otherwise>
                                                        </c:choose>


                                                    </div>
                                                    <div class="col-sm-6">
                                                        <p style="color:#4e73df;">Data di fine prenotazione: </p>
                                                        <c:choose>
                                                            <c:when test="${DataFineError == null}">
                                                                <form:input type="date"
                                                                            class="form-control form-control-user"
                                                                            id="dataFine"
                                                                            placeholder="Data fine Prenotazione"
                                                                            path="dataFinePrenotazione"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="date"
                                                                            class="form-control form-control-user is-invalid"
                                                                            id="dataFine"
                                                                            placeholder="Data fine Prenotazione"
                                                                            path="dataFinePrenotazione"/>
                                                                <span class="myError">${DataFineError}</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                </div>
                                                <br>
                                                <input type="submit" value="Conferma"
                                                       class="btn btn-primary btn-user btn-block">
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