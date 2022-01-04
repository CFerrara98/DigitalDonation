<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 04/01/2022
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<z:layout pageTitle="Salvataggio Donazione">


<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">


            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">


                <!-- Logo -->
                <a class="navbar-brand" href="#"><img class="rounded-circle" src="img/logo.png" alt="..." style="width:50px"></a>

                <div>
                    <a><h1 class="h3 mb-0 text-gray-800 scrittalogo">DIGITAL DONATION</h1></a>
                </div>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Ciao, %Utente% </span>
                            <img class="img-profile rounded-circle icone"
                                 src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">

                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Dashboard
                            </a>
                            <div class="dropdown-divider"></div>

                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>
            </nav>
            <!-- End of Topbar -->




            <!-- Begin Page Content -->

            <div class="container-fluid" >


                <!-- Page Heading -->

                <div class="card-body">
                    <!-- indietro -->
                    <a href="elencoPartecipanti.html" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>


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
                                                    <c:out value="${RH:}"></c:out>
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
                                    <form:form>
                                        <!-- tabella lista donazioni effettuate -->

                                        <div class="card-body">
                                            <div class="col-sm-12 mb-3 mb-sm-1">

                                                <div class="form-group">
                                                    <p style="color:#4e73df;">Motivazioni: </p>
                                                    <c:choose>
                                                        <c:when test="${motivazioniIndisponibilitaError == null}">
                                                            <form:textarea class="textarea form-control" id="exampleMotivazioni" placeholder="es. Nessuna" rows="4" path="motivazioni"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:textarea class="textarea form-control is-invalid" id="exampleMotivazioni" placeholder="es. Nessuna"  rows="4" path="motivazioni"/>
                                                            <span class="myError">${motivazioniIndisponibilitaError}</span>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                            </div>



                                            <div class="col-sm-12 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Nome Medico: </p>
                                                <input type="text" class="form-control form-control-user" id="exampleNome" placeholder="es. Angela">
                                                <c:choose>
                                                    <c:when test="${nomeMedicoError == null}">
                                                        <form:nput type="text" class="form-control form-control-user" id="nomeMedico" placeholder="es. Angela" path="nomeMedico"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="text" class="form-control form-control-user is-invalid" id="nomeMedico" placeholder="es. Angela" path="nomeMedico"/>
                                                        <span class="myError">${nomeMedicoError}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                            <br>

                                            <div class="col-sm-12 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Data scadenza non idoneità: </p>

                                                <c:choose>
                                                    <c:when test="${dataScadenzaNonIdoneitaError == null}">
                                                        <form:input type="date" class="form-control form-control-user" id="exampleData" placeholder="es. 18/01/2022" path="dataScadenzaNonIdoneita"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="date" class="form-control form-control-user is-invalid" id="exampleData" placeholder="es. 18/01/2022" path="dataScadenzaNonIdoneita"/>
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

