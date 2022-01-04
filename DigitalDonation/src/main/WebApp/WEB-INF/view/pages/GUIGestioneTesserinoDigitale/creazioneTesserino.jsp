<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 04/01/2022
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:layout pageTitle="creazioneTesserino">

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
                            <a href="./dashboardOperatore" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>

                            <!-- titolo -->
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Creazione Tesserino</h1>

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
                                                <h3 class="h4 text-gray-900 mb-4">Nella seguente sezione è possibile creare un nuovo tesserino compilando il form sottostante</h3>
                                            </div>

                                                <%--@elvariable id="tesserinoForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.TesserinoForm"--%>
                                            <form:form action="./creazioneTesserino" method="post" modelAttribute="tesserinoForm" cssClass="user" enctype="application/x-www-form-urlencoded">
                                                <div class="form-group row">
                                                    <div class="col-sm-4 mb-3 mb-sm-0">
                                                        <p style="color:#4e73df;">Nome: </p>
                                                        <c:choose>
                                                            <c:when test="${TesserinoNomeError == null}">
                                                                <form:input type="text" class="form-control form-control-user" id="nomeTesserino" placeholder="es. Angela" path="nome"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="text" class="form-control form-control-user is-invalid" id="nomeTesserino" placeholder="es. Angela" path="nome"/>
                                                                <span class="myError">${TesserinoNomeError}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="col-sm-4 mb-3 mb-sm-0">
                                                            <p style="color:#4e73df;">Cognome: </p>
                                                            <c:choose>
                                                                <c:when test="${TesserinoCognomeError == null}">
                                                                    <form:input type="text" class="form-control form-control-user" id="cognomeTesserino" placeholder="es. De Martino" path="cognome"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form:input type="text" class="form-control form-control-user is-invalid" id="cognomeTesserino" placeholder="es. De Martino" path="cognome"/>
                                                                    <span class="myError">${TesserinoCognomeError}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                    </div>

                                                    <div class="col-sm-4 mb-3 mb-sm-0">
                                                        <p style="color:#4e73df;">Codice Fiscale: </p>
                                                        <c:choose>
                                                            <c:when test="${TesserinoCodiceFiscaleError == null}">
                                                                <form:input type="text" class="form-control form-control-user" id="codiceFiscaleTesserino" placeholder="es. MVYZZV65L56I556J" path="codiceFiscale"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form:input type="text" class="form-control form-control-user is-invalid" id="codiceFiscaleTesserino" placeholder="es. MVYZZV65L56I556J" path="codiceFiscale"/>
                                                                <span class="myError">${TesserinoCodiceFiscaleError}</span>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>

                                                <div class="form-group">

                                                    <div class="form-group row">
                                                        <div class="col-sm-4 mb-3 mb-sm-0">
                                                            <p style="color:#4e73df;">Data di nascita: </p>
                                                            <c:choose>
                                                                <c:when test="${TesserinoDataNascitaError == null}">
                                                                    <form:input type="date" class="form-control form-control-user" id="exampleData" placeholder="es. 03/01/1998" path="dataNascita"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form:input type="date" class="form-control form-control-user is-invalid" id="exampleData" placeholder="es. 03/01/1998" path="dataNascita"/>
                                                                    <span class="myError">${TesserinoDataNascitaError}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>

                                                        <div class="col-sm-4 mb-3 mb-sm-0">
                                                            <p style="color:#4e73df;">Luogo di nascita: </p>
                                                            <c:choose>
                                                                <c:when test="${TesserinoLuogoNascitaError == null}">
                                                                    <form:input type="text" class="form-control form-control-user" id="exampleLuogo" placeholder="es. Salerno" path="luogoNascita"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form:input type="text" class="form-control form-control-user is-invalid" id="exampleLuogo" placeholder="es. Salerno" path="luogoNascita"/>
                                                                    <span class="myError">${TesserinoLuogoNascitaError}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <p style="color:#4e73df;">Residenza: </p>
                                                            <c:choose>
                                                                <c:when test="${TesserinoResidenzaError == null}">
                                                                    <form:input type="text" class="form-control form-control-user" id="exampleResidenza" placeholder="es. Salerno" path="residenza"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form:input type="text" class="form-control form-control-user is-invalid" id="exampleResidenza" placeholder="es. Salerno" path="residenza"/>
                                                                    <span class="myError">${TesserinoResidenzaError}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                </div>


                                                <p style="color:#4e73df;">Email: </p>
                                                    <c:choose>
                                                        <c:when test="${TesserinoEmailError == null}">
                                                            <form:input type="email" class="form-control form-control-user" id="exampleEmail" placeholder="es. angela@live.com" path="email"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:input type="email" class="form-control form-control-user is-invalid" id="exampleEmail" placeholder="es. angela@live.com" path="email"/>
                                                            <span class="myError">${TesserinoEmailError}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                <br>

                                                <div class="form-group">
                                                    <div class="form-group row">
                                                        <div class="col-sm-5 mb-3 mb-sm-0">

                                                            <!--Menu' a tendina per l'inserimento del gruppo sanguigno-->
                                                            <div class="form-group">
                                                                <label for="gruppoSanguigno" style="color:#4e73df;">Inserisci  il gruppo sanguigno:
                                                                </label>
                                                                <form:select name="gruppoSanguigno" id="gruppoSanguigno" class="form-control gruppo-sanguigno" path="gruppoSanguigno">
                                                                    <form:option selected="true" value="A ">A
                                                                    </form:option>
                                                                    <form:option value="B"> B
                                                                    </form:option>
                                                                    <form:option value="0"> 0
                                                                    </form:option>
                                                                    <form:option value="AB">AB
                                                                    </form:option>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                        <!--Fine menu' a tendina per l'inserimento del gruppo sanguigno-->

                                                        <!--Menu' a tendina per la scelta dell'rh-->
                                                        <div class="col-sm-5 mb-3 mb-sm-0">
                                                            <label for="rh" style="color:#4e73df;">Fattore Rh: </label>
                                                            <form:select name="rh" id="rh" class="form-control gruppo-sanguigno" path="rh">
                                                            <form:option selected="true" value="POS">POS</form:option>
                                                            <form:option selected="true" value="NEG">NEG</form:option>
                                                            </form:select>
                                                        </div>
                                                        <!--Fine menu' a tendina per la scelta dell'rh-->
                                                    </div>

                                                </div>


                                                <div class="form-group">
                                                    <p style="color:#4e73df;">Altre indicazioni: </p>
                                                    <p style="color:#4e73df;">
                                                        Inserisci altre indicazioni: </p>
                                                    <c:choose>
                                                        <c:when test="${TesserinoAltreIndicazioniError == null}">
                                                            <form:textarea class="textarea form-control" id="exampleAltreIndicazioni" placeholder="es. Nessuna" rows="4" path="altreIndicazioni"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:textarea class="textarea form-control" id="exampleAltreIndicazioni" placeholder="es. Nessuna"  rows="4" path="altreIndicazioni"/>
                                                            <span class="myError">${TesserinoAltreIndicazioniError}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <br>
                                                    <input type="submit" value="Conferma" class="btn btn-primary btn-user btn-block">
                                        </div>
                                            </form:form>
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
        </div>
    </div>
</z:layout>