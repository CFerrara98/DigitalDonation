<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 30/12/2021
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<z:layout pageTitle="monitoraggioSedute">

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
    <a href="dashboardOperatore.html" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>

    <!-- titolo -->
    <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Monitoraggio Sedute</h1>

    </div>
    </div>
    <!-- Sezione Elenco monitoraggi -->


    <div class="card shadow mb-4 ">
    <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">Elenco sedute da svolgersi:</h6>
    </div>
    <h6>&nbsp;</h6>
    <!-- seduta 1 -->
    <div class="card shadow mb-4">
    <div class=" m-0 card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary h6">Seduta 1:</h6>
    </div>
    <div class="card-body">

    <!-- Tabella -->

    <div class="table-container">
    <table class="table  ">
    <thead>
    <tr>
    <th scope="col"></th>
    <th scope="col">Elenco partecipanti</th>
    <th scope="col">Modifica seduta</th>
    <th scope="col">Elimina seduta</th>
    </tr>
    </thead>
    <tbody>
    <tr>
    <th>
    <h4 class="small font-weight-bold">Data: %data%</h4>
    <h4 class="small font-weight-bold">Luogo:%luogo%</h4>
    </th>

    <th scope="row">
    <!-- Bottom Elenco partecipanti -->
    <span  class="float-center col-xl-1 col-md-6 mb-2">
    <a href="elencoPartecipanti.html" role="button">
    <i class="fas fa-user icone"></i>
    </a>
    </span>
    </th>

    <!-- Bottom modifica seduta -->
    <td>
    <span  class="float-center col-xl-1 col-md-6 mb-2">
    <a href=".html" role="button"> <i class="fas fa-cogs icone"></i></a>
    </span>
    </td>

    <!-- Bottom Elimina seduta -->
    <td>
    <span  class="float-center col-xl-1 col-md-6 mb-2">
    <a href=".html" role="button"> <i class="fas fa-times icone"></i></a>
    </span>
    </td>
    </tr>
    </tbody>
    </table>
    </div>

    <!--FINE Tabella -->








    </div>
    </div>
    </div>
    <!-- fine elenco monitoraggio -->
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