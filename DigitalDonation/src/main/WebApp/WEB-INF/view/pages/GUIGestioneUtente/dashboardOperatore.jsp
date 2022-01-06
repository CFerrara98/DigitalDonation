<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
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
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Dashboard Operatore</h1>

                            <c:choose>
                                <c:when test="${success != null}">
                                    <div class="alert alert-success" role="alert">
                                        <h4 class="alert-heading"><i class="fas fa-calendar-check"
                                                                     style="font-size: 36px"> </i> &nbsp <b>Complimenti!</b>  ${success}
                                        </h4>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <!-- Sezione Crea seduta -->


                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Crea seduta</h6>
                        </div>
                        <div class="card-body">

                            <!-- bottone crea seduta -->
                            <div class="table-botton">
                                <table class="table  ">
                                    <thead>
                                    <tr>
                                        <td scope="col"><h3 class="small font-weight-bold"> Crea una nuova Seduta: </h3>
                                        </td>
                                        <td scope="col"><span class=" float-right">
                                                <a class="btn btn-primary botton-sm large"
                                                   href="./goSchedulazioneSeduta" role="button">Crea seduta</a>
                                             </span>
                                        </td>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!-- Sezione Elenco sedute -->

                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Elenco sedute</h6>
                        </div>
                        <div class="card-body">

                            <div class="table-botton">
                                <table class="table  ">
                                    <thead>
                                    <tr>
                                        <td scope="col"><h3 class="small font-weight-bold"> Monitora una seduta: </h3>
                                        </td>
                                        <td scope="col"><span class=" float-right">
                                                <a class="btn btn-primary botton-sm large"
                                                   href="./visualizzaElencoSedute" role="button">Elenco sedute</a>
                                             </span>
                                        </td>
                                    </tr>
                                    </thead>
                                </table>
                            </div>


                        </div>
                    </div>

                    <!-- Sezione Creazione tesserino -->

                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Creazione tesserino</h6>
                        </div>
                        <div class="card-body">

                            <div class="table-botton">
                                <table class="table  ">
                                    <thead>
                                    <tr>
                                        <td scope="col"><h3 class="small font-weight-bold"> Crea un nuovo
                                            tesserino: </h3></td>
                                        <td scope="col"><span class=" float-right">
                                                <a class="btn btn-primary botton-sm large" href="./goCreazioneTesserino" role="button">Crea tesserino</a>
                                             </span>
                                        </td>
                                    </tr>
                                    </thead>
                                </table>
                            </div>


                        </div>
                    </div>


                    <!-- Per dividere a blocchi-->

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