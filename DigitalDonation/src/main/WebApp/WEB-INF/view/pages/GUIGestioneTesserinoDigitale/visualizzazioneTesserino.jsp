<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 04/01/2022
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="visualizzazioneTesserino">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">



            <!-- Begin Page Content -->

            <div class="container-fluid" >


                <!-- Page Heading -->

                <div class="card-body">
                    <!-- indietro -->
                    <a href="./dashboardDonatore" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>


                    <h1 class=" mb-4 " style="text-align: center" > Il mio Tesserino: </h1>
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
                                                <h3 align="left" class="h6 mb-2 text-gray-800" > %Rh%
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
                                <h6 class="m-0 font-weight-bold text-primary">Donazioni Compiute</h6>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="mt-4 mb-4 text-center small" >

                                    <!-- tabella lista donazioni effettuate -->

                                    <div class="table-container">
                                        <table class="table  ">

                                            <thead>
                                            <tr>
                                                <th>
                                                    <h3 align="left" class="h6 mb-2 text-gray-800" >Data  </h3>
                                                </th>
                                                <th>
                                                    <h3 align="left" class="h6 mb-2 text-gray-800" >Tipo donazione  </h3>
                                                </th>
                                            </tr>
                                            </thead>


                                            <tbody>

                                            <!-- ciclo di righe con tutte le donazioni effettuate dal donatore-->

                                            <c:forEach begin="0" end="${listaDonazioni.size()-1}" step="1" var="i">
                                                <tr>
                                                    <th>
                                                        <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                            <c:out value="${listaDonazioniEffettuate.get(i).dataDonazione}"></c:out>
                                                        </h3>
                                                    </th>
                                                    <th>
                                                        <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                            <c:out value="${listaDonazioniEffettuate.get(i).tipoDonazione}"></c:out>
                                                        </h3>
                                                    </th>
                                                </tr>
                                            </c:forEach>
                                            </tbody>

                                        </table>
                                    </div>

                                </div>
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
