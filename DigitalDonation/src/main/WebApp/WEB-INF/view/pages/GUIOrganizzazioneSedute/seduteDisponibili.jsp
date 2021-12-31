<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 30/12/2021
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="Sedute Disponibili">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">


                <!-- Begin Page Content -->
                <div class="container-fluid" style="background-color:#eaeef1; padding-top: 30px;">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <div class="card-body">
                            <!-- indietro -->
                            <a href="./dashboardDonatore" role="button"> <i
                                    class="fas fa-arrow-left float-left icone"></i></a>
                            <!-- Titolo -->
                            <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Elenco sedute ancora da
                                svolgersi:</h1>

                        </div>
                    </div>

                    <div class="card shadow mb-4 ">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Elenco sedute:</h6>
                        </div>
                        <h6>&nbsp;</h6>

                        <c:forEach begin="0" var="i" end="${listaSedutePrenotabili.size}">
                            <!-- prima seduta-->
                            <div class="card shadow mb-4">

                                <div class=" m-0 card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary h6">Seduta <c:out
                                            value="${i+1}"></c:out>:</h6>
                                </div>
                                <div class="card-body">

                                    <!-- tabella-->
                                    <div class="table-botton">
                                        <table class="table  ">
                                            <thead>
                                            <tr>
                                                <td scope="col"><h3 class="small font-weight-bold"> Luogo:<c:out
                                                        value="${listaSedutePrenotabili.getIndex(i).luogo}"></c:out></h3>
                                                    <h3 class="small font-weight-bold"> Data:<c:out
                                                            value="${listaSedutePrenotabili.getIndex(i).dataSeduta}"></c:out></h3>
                                                <td scope="col"><span class=" float-right">
                                                <a class="btn btn-primary botton-sm large"
                                                   href="./goPartecipaSeduta" role="button">Partecipa</a>
                                             </span>
                                                </td>
                                            </tr>
                                            </thead>
                                        </table>
                                    </div>
                                    <!-- fine tabella-->
                                </div>
                            </div>

                        </c:forEach>

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