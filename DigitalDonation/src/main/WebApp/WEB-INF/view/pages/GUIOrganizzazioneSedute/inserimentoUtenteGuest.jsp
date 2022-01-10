<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>



<z:layout pageTitle="inserimento Utente Guest">


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
                            <a href="./goElencoPartecipanti?idSeduta=${idSeduta}" role="button"> <i
                                    class="fas fa-arrow-left float-left icone"></i></a>

                            <!-- titolo -->
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Inserimento utente Guest</h1>

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
                                                <h3 class="h4 text-gray-900 mb-4">Compila i campi per inserire un Guest</h3>
                                            </div>
                                            <c:if test="${SedutaError!=null}">
                                                <span class="myError">${SedutaError}</span>
                                            </c:if>
                                            <c:if test="${GuestError!=null}">
                                                <span class="myError">${GuestError}</span>
                                            </c:if>

                                                <%--@elvariable id="guestForm" type="it.unisa.is.c09.digitaldonation.utils.form.GuestForm"--%>
                                            <form:form action="./inserimentoGuest" method="post"
                                                       modelAttribute="guestForm" cssClass="user"
                                                       enctype="application/x-www-form-urlencoded">
                                                <input type="hidden" name="idSeduta" value=${idSeduta} />
                                            <div class="form-group">
                                                    <p style="color:#4e73df;">Inserisci il nome </p>
                                                    <c:choose>
                                                        <c:when test="${NomeError == null}">
                                                            <form:input type="text"
                                                                        class="form-control form-control-user"
                                                                        id="nome"
                                                                        placeholder="es. Angela"
                                                                        path="nome"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="text"
                                                                        class="form-control form-control-user is-invalid"
                                                                        id="nome" placeholder="es. Angela"
                                                                        path="nome"/>
                                                            <span class="myError">${NomeError}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>

                                                <div class="form-group">
                                                    <p style="color:#4e73df;">Inserisci il cognome: </p>
                                                    <c:choose>
                                                        <c:when test="${CognomeError == null}">
                                                            <form:input type="text"
                                                                        class="form-control form-control-user"
                                                                        id="cognome"
                                                                        placeholder="es. De Martino"
                                                                        path="cognome"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="text"
                                                                        class="form-control form-control-user is-invalid"
                                                                        id="cognome"
                                                                        placeholder="es. De Martino"
                                                                        path="cognome"/>

                                                            <span class="myError">${CognomeError}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="form-group">
                                                    <p style="color:#4e73df;">Inserisci il telefono: </p>
                                                    <c:choose>
                                                        <c:when test="${TelefonoError == null}">
                                                            <form:input type="tel"
                                                                        class="form-control form-control-user"
                                                                        id="telefono"
                                                                        placeholder="es. 3456789123"
                                                                        path="telefono"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="tel"
                                                                        class="form-control form-control-user is-invalid"
                                                                        id="telefono"
                                                                        placeholder="es. 3456789123"
                                                                        path="telefono"/>
                                                            <span class="myError">${TelefonoError}</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>

                                                <div class="form-group">
                                                    <p style="color:#4e73df;">
                                                        Inserisci il codice fiscale: </p>
                                                    <c:choose>
                                                        <c:when test="${CodiceFiscaleError == null}">
                                                            <form:input
                                                                    type="text"
                                                                    class="form-control form-control-user "
                                                                    id="codiceFiscale"
                                                                    placeholder="es. MVYZZV65L56I556J"
                                                                    path="codiceFiscale"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="text"
                                                                        class="form-control form-control-user is-invalid"
                                                                        id="codiceFiscale"
                                                                        placeholder="es. MVYZZV65L56I556J"
                                                                        path="codiceFiscale"/>
                                                            <span class="myError">${CodiceFiscaleError}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>

                                                <div class="form-group">
                                                            <p style="color:#4e73df;">
                                                                Inserisci le
                                                                patologie: </p>
                                                            <c:choose>
                                                                <c:when test="${PatologieError == null}">
                                                                    <form:textarea class="textarea form-control"
                                                                                   id="exampleCodiceFiscale"
                                                                                   placeholder="es. Nessuna"
                                                                                   rows="4" path="patologie"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form:textarea class="textarea form-control"
                                                                           id="exampleCodiceFiscale"
                                                                           placeholder="es. Nessuna"
                                                                           rows="4" path="patologie"/>
                                                                    <span class="myError">${PatologieError}</span>
                                                                </c:otherwise>
                                                            </c:choose>

                                                </div>

                                                <div class="form-group">
                                                            <label for="gruppoSanguigno"
                                                                   style="color:#4e73df;">Inserisci
                                                                il gruppo sanguigno:
                                                                </label>
                                                            <form:select name="gruppoSanguigno"
                                                                         id="gruppoSanguigno"
                                                                         class="form-control gruppo-sanguigno"
                                                                         path="gruppoSanguigno">
                                                            <form:option selected="true" value="A+ ">
                                                                A+
                                                            </form:option>
                                                            <form:option value="A-">
                                                                A-
                                                            </form:option>
                                                            <form:option value="B+">
                                                                B+
                                                            </form:option>
                                                            <form:option value="B-">
                                                                B-
                                                            </form:option>
                                                            <form:option value="0+">
                                                                0+
                                                            </form:option>
                                                            <form:option value="0-">
                                                                0-
                                                            </form:option>
                                                            <form:option value="AB+">
                                                                AB+
                                                            </form:option>
                                                            <form:option value="AB-">
                                                                AB-
                                                            </form:option>

                                                            </form:select>
                                                </div>

                                                        <br>

                                                        <input type="submit"
                                                               value="Aggiungi utente"
                                                               class="btn btn-primary btn-user btn-block">

                                            </form:form>

                                       </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                     </div>
                                <!-- /.container -->
                </div>
                            <!-- End Page Content-->
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