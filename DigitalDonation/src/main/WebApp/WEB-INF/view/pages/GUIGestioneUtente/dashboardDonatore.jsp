<%--
  Created by IntelliJ IDEA.
  User: marik
  Date: 29/12/2021
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="dashboardDonatore">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">


                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1>&nbsp;</h1>
                    <h1 class=" mb-4" style="text-align: center">Dashboard Donatore</h1>

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
                                    <div class="text-center">
                                        <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 24rem;"
                                             src="img/logo.png" alt="...">
                                        <h1 align="center" class="h3 mb-2 text-gray-800"> %Nome Cognome%</h1>
                                        <a class="btn btn-primary" href="#" role="button">Vai al tuo tesserino</a>
                                    </div>
                                </div>
                                <div class="mt-4 mb-4 text-center small">

                                </div>
                            </div>
                        </div>


                        <!-- Attività -->
                        <div class="col-xl-6 col-lg-12">
                            <div class="card shadow mb-4">
                                <!-- Card Header -->
                                <div
                                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Attività</h6>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="mt-4 mb-4 text-center small">
                                        <h1>&nbsp;</h1>
                                        <h1 align="center" class="h3 mb-2 text-gray-800">Visualizza le sedute
                                            disponibili per te:</h1>
                                        <a class="btn btn-primary" href="./goSeduteDisponibili"
                                           role="button">Sedute</a>
                                        <h1>&nbsp;</h1>
                                        <h1>&nbsp;</h1>
                                        <h1 align="center" class="h3 mb-2 text-gray-800">Compila la tua
                                            autodichiarazione di indisponibilità:</h1>
                                        <a class="btn btn-primary" href="./goAutodichiarazioneIndisponibilita" role="button">Compila</a>
                                        <h1>&nbsp;</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">

                        <!-- Requisiti per donare -->
                        <div class="col-xl-12 col-lg-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Requisiti per donare</h6>
                                </div>
                                <div class="card-body">
                                    <div class="text-center" style="background: #dcebf4">
                                        <img class="img-fluid px-sm-4 mt-3 mb-4"
                                             src="../../resources/img/requisiti.svg" alt="..." width="1000" height="600">
                                    </div>
                                    <br>
                                    <h4 style="text-align: center"><b>Chi non può donare il sangue? </b></h4>
                                    <h5><p> Criteri di esclusione temporanea del donatore</p></h5>
                                    <p>

                                    <ul>
                                        <li>Parto – sospensione per 6 mesi</li>
                                        <li>Ciclo mestruale in atto – da valutare</li>
                                        <li>Allergie in atto</li>
                                        <li>Ulcera gastrica e/o duodenale in atto</li>
                                        <li> Alcune malattie infettive (da valutare alla guarigione)</li>
                                        <li> Agopuntura – 4 mesi (Se eseguiti con strumenti monouso)</li>
                                        <li> Foratura orecchie*/Piercing – 4 mesi</li>
                                        <li>Trasfusione di emocomponenti o emoderivati (immunoglobuline) – 4 mesi</li>
                                        <li> Assunzione di alcune tipologie di farmaci</li>
                                        <li> Rapporti sessuali a rischio – 4 mesi</li>
                                        <li>  Viaggi (da valutare)</li>

                                    </ul>
                                    </p>

                                    <h5><p> Criteri di esclusione permanente del donatore</p></h5>
                                    <p>

                                    <ul>
                                        <li>Alcolismo cronico</li>
                                        <li>Assunzione di sostanze stupefacenti, steroidi o ormoni a scopo di culturismo fisico</li>
                                        <li>Epatite B, C o ad eziologia indeterminata</li>
                                        <li>Infezione da HIV 1-2, da HTLV1-2</li>
                                        <li>Sifilide</li>
                                        <li>Trapianto cornea/dura madre</li>
                                        <li>Diabete insulino dipendente</li>
                                        <li>Ipertensione grave o in trattamento con B-bloccanti</li>
                                        <li>Nefropatie croniche</li>
                                        <li>Neoplasie maligne</li>
                                    </ul>
                                    </p>

                                </div>
                            </div>
                        </div>
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