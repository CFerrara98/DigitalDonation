<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 29/12/2021
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="dashboardOperatore">

    <c:if test="${utente!=null}"><a href="/logout"> SLOGGA</a></c:if>

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
                                                   href="schedulazioneSeduta.html" role="button">Crea seduta</a>
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
                                        <td scope="col"><h3 class="small font-weight-bold"> Monitore una seduta: </h3>
                                        </td>
                                        <td scope="col"><span class=" float-right">
                                                <a class="btn btn-primary botton-sm large"
                                                   href="monitoraggioSedute.html" role="button">Elenco sedute</a>
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
                                                <a class="btn btn-primary botton-sm large" href=".html" role="button">Crea tesserino</a>
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

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

</z:layout>