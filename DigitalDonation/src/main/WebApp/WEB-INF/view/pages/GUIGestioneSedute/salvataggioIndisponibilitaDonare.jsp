<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 04/01/2022
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
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
                    <a href="./goMonitoraggioSedute" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>
                    <h1 class=" mb-4 " style="text-align: center" > Salvataggio Indisponibilità: </h1>
                </div>

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
                                        <img class="card-img-top" src="img/blood4.png" alt="..." />
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
                                                    <c:out value="${Nome}"></c:out>
                                                </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${Cognome}"></c:out>
                                                </h3>
                                            </th>

                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Matricola:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${Matricola}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Data rilascio:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${dataRilascio}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Residente a:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${residenza}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Nato:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${dataDiNascita}"></c:out>
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
                                                    <c:out value="${gruppoSanguigno}"></c:out>
                                                </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${RH}"></c:out>
                                                </h3>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Altre indicazioni:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${altreIndicazioni}"></c:out>
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
                                    <%--@elvariable id="indisponibilitaDonazioneForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.IndisponibilitaDonazioneForm"--%>
                                    <form:form action="./indisponibilitaByOperatore" method="post"
                                               modelAttribute="indisponibilitaDonazioneForm" cssClass="user"
                                               enctype="application/x-www-form-urlencoded">
                                        <!-- tabella lista donazioni effettuate -->
                                        <div class="card-body">
                                            <div class="col-sm-12 mb-3 mb-sm-1">

                                                <div class="form-group">
                                                    <p style="color:#4e73df;">Motivazioni: </p>
                                                    <c:choose>
                                                        <c:when test="${motivazioniIndisponibilitaError == null}">
                                                            <form:textarea class="textarea form-control" id="exampleMotivazioni" rows="4" path="motivazioni"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:textarea class="textarea form-control is-invalid" id="exampleMotivazioni" rows="4" path="motivazioni"/>
                                                            <span class="myError">${motivazioniIndisponibilitaError}</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                            </div>



                                            <div class="col-sm-12 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Nome Medico: </p>
                                                <c:choose>
                                                    <c:when test="${nomeMedicoError == null}">
                                                        <form:input type="text" class="form-control form-control-user" id="nomeMedico" path="nomeMedico"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="text" class="form-control form-control-user is-invalid" id="nomeMedico" path="nomeMedico"/>
                                                        <span class="myError">${nomeMedicoError}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                            <br>

                                            <div class="col-sm-12 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Data scadenza non idoneità: </p>

                                                <c:choose>
                                                    <c:when test="${dataScadenzaNonIdoneitaError == null}">
                                                        <form:input type="date" class="form-control form-control-user" id="exampleData" placeholder="es. 18/01/2022" path="dataProssimaDisponibilita"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="date" class="form-control form-control-user is-invalid" id="exampleData" placeholder="es. 18/01/2022" path="dataProssimaDisponibilita"/>
                                                        <span class="myError">${dataScadenzaNonIdoneitaError}</span>
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

