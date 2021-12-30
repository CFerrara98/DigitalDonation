<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 30/12/2021
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<z:layout pageTitle="dashboardOperatore">

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
    <a href="dashboardOperatore.html" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>

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
    <h3 class="h4 text-gray-900 mb-4">Compila i campi per schedulare una seduta</h3>
    </div>
            <%--@elvariable id="SedutaForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm"--%>
        <form:form action="./schedulazioneSeduta" method="post" modelAttribute="sedutaForm" cssClass="user" enctype="application/x-www-form-urlencoded">
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <p style="color:#4e73df;">Data seduta: </p>
            <c:choose>
                <c:when test="${DataError == null}">
                    <c:choose>
                        <c:when test="${DataPrecedente == null}">
                            <form:input type="date" class="form-control form-control-user" id="data" placeholder="Data Seduta:" path="data"/>
                </c:when>
                        <c:otherwise>
                        <form:input type="date" value="${DataPrecedente}" class="form-control form-control-user" id="data" placeholder="Data Seduta:" path="data"/>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                        <form:input type="date" class="form-control form-control-user" id="data" placeholder="Data Seduta:" path="data"/>

                        <p>${DataError}</p>
                        <span class="myError">${DataError}</span>
                </c:otherwise>
            </c:choose>

            </div>

        <div class="col-sm-6">
            <p style="color:#4e73df;">Numero di partecipanti: </p>
            <c:choose>
                <c:when test="${NumeroPartecipantiError == null}">
                    <c:choose>
                        <c:when test="${NumeroPartecipantiPrecedente == null}">
                            <form:input type="text" class="form-control form-control-user" id="numeroPartecipanti" placeholder="es. 10" path="numeroPartecipanti"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" value="${NumeroPartecipantiPrecedente}" class="form-control form-control-user" id="numeroPartecipanti" placeholder="es. 10" path="numeroPartecipanti"/>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <form:input type="text" class="form-control form-control-user" id="numeroPartecipanti" placeholder="data Seduta:" path="numeroPartecipanti"/>

                    <p>${NumeroPartecipantiError}</p>
                    <span class="myError">${NumeroPartecipantiError}</span>
                </c:otherwise>
            </c:choose>

    </div>
    </div>
    <div class="form-group">

    <p style="color:#4e73df;">Indirizzo della seduta: </p>
        <c:choose>
            <c:when test="${IndirizzoError == null}">
                <c:choose>
                    <c:when test="${IndirizzoPrecedente == null}">
                        <form:input type="text" class="form-control form-control-user" id="indirizzo" placeholder="es. Via Giuseppe Verdi 1" path="indirizzo"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="text" value="${IndirizzoPrecedente}" class="form-control form-control-user" id="indirizzo" placeholder="es. Via Giuseppe Verdi 1" path="indirizzo"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="text" class="form-control form-control-user" id="indirizzo" placeholder="es. Via Giuseppe Verdi 1" path="indirizzo"/>

                <p>${IndirizzoError}</p>
                <span class="myError">${IndirizzoError}</span>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="form-group row">
    <div class="col-sm-4 mb-3 mb-sm-0">
    <p style="color:#4e73df;">Città: </p>

        <c:choose>
            <c:when test="${CittaError == null}">
                <c:choose>
                    <c:when test="${CittaPrecedente == null}">
                        <form:input type="text" class="form-control form-control-user" id="citta" placeholder="es. Salerno" path="citta"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="text" value="${CittaPrecedente}" class="form-control form-control-user" id="citta" placeholder="es. Salerno" path="citta"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="text" class="form-control form-control-user" id="citta" placeholder="es. Salerno" path="citta"/>

                <p>${CittaError}</p>
                <span class="myError">${CittaError}</span>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="col-sm-4">
    <p style="color:#4e73df;">CAP: </p>
        <c:choose>
            <c:when test="${CAPError == null}">
                <c:choose>
                    <c:when test="${CAPPrecedente == null}">
                        <form:input type="text" class="form-control form-control-user" id="cap" placeholder="es. 84100" path="cap"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="text" value="${CAPPrecedente}" class="form-control form-control-user" id="cap" placeholder="es. 84100" path="cap"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="text" class="form-control form-control-user" id="cap" placeholder="es. 84100" path="cap"/>

                <p>${CAPError}</p>
                <span class="myError">${CAPError}</span>
            </c:otherwise>
        </c:choose>

    <input type="text" class="form-control form-control-user" id="exampleCAP" placeholder="es. 84100">
    </div>
    <div class="col-sm-4">
    <p style="color:#4e73df;">Provincia: </p>
        <c:choose>
            <c:when test="${ProvinciaError == null}">
                <c:choose>
                    <c:when test="${ProvinciaPrecedente == null}">
                        <form:input type="text" class="form-control form-control-user" id="provincia" placeholder="es. Salerno" path="provincia"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="text" value="${ProvinciaPrecedente}" class="form-control form-control-user" id="provincia" placeholder="es. Salerno" path="provincia"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="text" class="form-control form-control-user" id="provincia" placeholder="es. Salerno" path="provincia"/>

                <p>${ProvinciaError}</p>
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
                <c:choose>
                    <c:when test="${OraInizioPrecedente == null}">
                        <form:input type="time" class="form-control form-control-user" id="oraInizio" placeholder="Orario inizio:" path="oraInizio"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="time" value="${OraInizioPrecedente}" class="form-control form-control-user" id="oraInizio" placeholder="Orario inizio:" path="oraInizio"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="time" class="form-control form-control-user" id="oraInizio" placeholder="Orario inizio:" path="oraInizio"/>

                <p>${OraInizioError}</p>
                <span class="myError">${OraInizioError}</span>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="col-sm-6">
    <p style="color:#4e73df;">Orario di fine: </p>
        <c:choose>
            <c:when test="${OraFineError == null}">
                <c:choose>
                    <c:when test="${OraFinePrecedente == null}">
                        <form:input type="time" class="form-control form-control-user" id="oraFine" placeholder="Orario fine:" path="oraFine"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="time" value="${OraFinePrecedente}" class="form-control form-control-user" id="oraFine" placeholder="Orario fine:" path="oraFine"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="time" class="form-control form-control-user" id="oraFine" placeholder="Orario fine:" path="oraFine"/>

                <p>${OraFineError}</p>
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
                <c:choose>
                    <c:when test="${DataInizioPrecedente == null}">
                        <form:input type="date" class="form-control form-control-user" id="dataInizio" placeholder="Data inizio Prenotazione:" path="dataInizio"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="date" value="${DataInizioPrecedente}" class="form-control form-control-user" id="dataInizio" placeholder="Data inizio Prenotazione:" path="dataInizio"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="date" class="form-control form-control-user" id="dataInizio" placeholder="Data inizio Prenotazione:" path="dataInizio"/>

                <p>${DataInizioError}</p>
                <span class="myError">${DataInizioError}</span>
            </c:otherwise>
        </c:choose>


    </div>
    <div class="col-sm-6">
    <p style="color:#4e73df;">Data fine prenotazione: </p>
        <c:choose>
            <c:when test="${DataFineError == null}">
                <c:choose>
                    <c:when test="${DataFinePrecedente == null}">
                        <form:input type="date" class="form-control form-control-user" id="dataFine" placeholder="Data fine Prenotazione" path="dataFine"/>
                    </c:when>
                    <c:otherwise>
                        <form:input type="date" value="${DataFinePrecedente}" class="form-control form-control-user" id="dataFine" placeholder="Data fine Prenotazione" path="dataFine"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form:input type="date" class="form-control form-control-user" id="dataFine" placeholder="Data fine Prenotazione" path="dataFine"/>

                <p>${DataFineError}</p>
                <span class="myError">${DataFineError}</span>
            </c:otherwise>
        </c:choose>

    </div>
    </div>
    <br>
    <input type="submit" value="Conferma" class="btn btn-primary btn-user btn-block">

    </form>

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

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
    <div class="modal-content">
    <div class="modal-header">
    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">×</span>
    </button>
    </div>
    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
    <div class="modal-footer">
    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
    <a class="btn btn-primary" href="login.html">Logout</a>
    </div>
    </div>
    </div>
    </div>

</z:layout>