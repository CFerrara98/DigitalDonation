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
                                        <img class="card-img-top" src="${tesserino.imgSource}" alt="Errore caricamento immagine" />
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
                                                    <c:out value="${donatore.nome}"></c:out>
                                                </h3>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                    <c:out value="${donatore.cognome}"></c:out>
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
                                        <tr>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >Altre indicazioni:  </h3>
                                            </th>
                                            <th>
                                                <h3 align="left" class="h6 mb-2 text-gray-800" >
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
                                    <c:choose>
                                        <c:when test="${tesserino.listaDonazioni.size() > 0}">
                                            <c:forEach begin="0" end="${tesserino.listaDonazioni.size()-1}" step="1" var="i">
                                                <tr>
                                                    <th>
                                                        <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                            <c:out value="${tesserino.listaDonazioni.get(i).dataDonazione.date}"></c:out>
                                                            /
                                                            <c:out value="${tesserino.listaDonazioni.get(i).dataDonazione.month + 1}"></c:out>
                                                            /
                                                            <c:out value="${tesserino.listaDonazioni.get(i).dataDonazione.year + 1900}"></c:out>

                                                        </h3>
                                                    </th>
                                                    <th>
                                                        <h3 align="left" class="h6 mb-2 text-gray-800" >
                                                            <c:out value="${tesserino.listaDonazioni.get(i).tipoDonazione}"></c:out>
                                                        </h3>
                                                    </th>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </c:when>
                                        <c:otherwise>
                                            <h6 align="center"> Nessuna seduta disponibile per te </h6><br>
                                        </c:otherwise>
                                    </c:choose>
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
                    Il trattamento dei dati personali ?? svolto nel rispetto dei diritti sulla privacy in base alla legge n. 675 del 31 dicembre 1996
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
