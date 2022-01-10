<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<z:layout pageTitle="Modifica Seduta">

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
                        <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Modifica seduta</h1>
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
                                            <h3 class="h4 text-gray-900 mb-4">Modifica i campi e clicca su conferma per aggiornare la seduta</h3>
                                        </div>
                                            <%--@elvariable id="sedutaForm" type="it.unisa.is.c09.digitaldonation.utils.form.SedutaForm"--%>
                                        <form:form action="./modificaSeduta" method="post"
                                                   modelAttribute="sedutaForm"  cssClass="user"
                                                   enctype="application/x-www-form-urlencoded">
                                            <div class="form-group row">
                                                <div class="col-sm-12 mb-3 mb-sm-0">
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
                                                                        path="cap"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="text"
                                                                        class="form-control form-control-user is-invalid"
                                                                        id="cap"
                                                                        maxlength="5"
                                                                        placeholder="es. 84100" path="cap"/>
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
