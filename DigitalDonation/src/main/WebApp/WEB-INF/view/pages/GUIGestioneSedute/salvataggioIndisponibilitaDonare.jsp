<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="Salvataggio Donazione">

    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Page Heading -->
                <div class="card-body">
                    <!-- indietro -->
                    <a href="./goElencoPartecipanti?idSeduta=${idSeduta}" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>
                </div>
                <!-- Titolo -->
                <h1 class=" mb-4" style="text-align: center">Salvataggio indisponibilita'</h1>

                <!-- Content Row -->
                <div class="row ">
                    <!-- Profilo -->
                    <div class="col-xl-6 col-lg-12">
                        <div class="card shadow mb-4">
                            <!-- Card Header -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Profilo</h6>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">

                                <div class="col-lg-4 mb-5">
                                    <div class="card h-80 shadow border-0">
                                        <img class="card-img-top" src="${tesserino.imgSource}" alt="Impossibile caricare l'immagine" />
                                    </div>
                                </div>
                                <!-- Tabella -->

                                <div class="table-container">
                                    <table class="table  ">

                                        <!-- <thead> </thead> -->
                                        <tbody>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Nome:  </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Cognome:</h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${utenteDonatore.nome}"></c:out>
                                                </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${utenteDonatore.cognome}"></c:out>
                                                </h3>
                                            </th>

                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Matricola:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${tesserino.numeroMatricola}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Data rilascio:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${tesserino.dataRilascio.date}"></c:out>
                                                    /
                                                    <c:out value="${tesserino.dataRilascio.month + 1}"></c:out>
                                                    /
                                                    <c:out value="${tesserino.dataRilascio.year + 1900}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Residente a:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${donatore.residenza}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Nato:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${donatore.dataDiNascita.date}"></c:out>
                                                    /
                                                    <c:out value="${donatore.dataDiNascita.month + 1}"></c:out>
                                                    /
                                                    <c:out value="${donatore.dataDiNascita.year + 1900}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>

                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Gruppo sanguigno:  </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >RH:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${tesserino.gruppoSanguigno}"></c:out>
                                                </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${tesserino.rh}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <!--Fine card body -->
                        </div>
                        <div class="mt-4 mb-4 text-center small" >

                        </div>
                    </div>



                    <!--Donazioni compiute -->
                    <div class="col-xl-6 col-lg-12">
                        <div class="card shadow mb-4">
                            <!-- Card Header -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Salvataggio Indisponibilità</h6>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="card shadow mb-4" >
                                        <%--@elvariable id="indisponibilitaDonazioneForm" type="it.unisa.is.c09.digitaldonation.utils.form.IndisponibilitaDonazioneForm"--%>
                                    <form:form action="./indisponibilitaByOperatore" method="post"
                                               modelAttribute="indisponibilitaDonazioneForm" cssClass="user"
                                               enctype="application/x-www-form-urlencoded">
                                        <!-- tabella lista donazioni effettuate -->
                                        <div class="card-body">
                                            <div class="col-sm-12 mb-3 mb-sm-1">

                                                <div class="form-group">
                                                    <p style="color:#4e73df;">Motivazioni: </p>
                                                    <c:choose>
                                                        <c:when test="${MotivazioniIndisponibilitaError == null}">
                                                            <form:textarea class="textarea form-control" id="exampleMotivazioni" rows="4" path="motivazioni"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:textarea class="textarea form-control is-invalid" id="exampleMotivazioni" rows="4" path="motivazioni"/>
                                                            <span class="myError">${MotivazioniIndisponibilitaError}</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                            </div>



                                            <div class="col-sm-12 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Nome Medico: </p>
                                                <c:choose>
                                                    <c:when test="${NomeMedicoIndisponibilitaError == null}">
                                                        <form:input type="text" class="form-control form-control-user" id="nomeMedico" path="nomeMedico"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="text" class="form-control form-control-user is-invalid" id="nomeMedico" path="nomeMedico"/>
                                                        <span class="myError">${NomeMedicoIndisponibilitaError}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                            <br>

                                            <div class="col-sm-12 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Data scadenza non idoneità: </p>

                                                <c:choose>
                                                    <c:when test="${DataProssimaDisponibilitaError == null}">
                                                        <form:input type="date" class="form-control form-control-user" id="exampleData" placeholder="es. 18/01/2022" path="dataProssimaDisponibilita"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="date" class="form-control form-control-user is-invalid" id="exampleData" placeholder="es. 18/01/2022" path="dataProssimaDisponibilita"/>
                                                        <span class="myError">${DataProssimaDisponibilitaError}</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                            
                                            <br>
                                            <input type="submit" value="Conferma" class="btn btn-primary btn-user btn-block">
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">



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

