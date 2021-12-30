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


<z:layout pageTitle="inserimentoUtenteGuest">

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
    <a href="monitoraggioSedute.html" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>

    <!-- titolo -->
    <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Inserimento utente Guest</h1>

    </div>
    </div>
    <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
    <!-- Nested Row within Card Body -->
    <div class="row justify-content-md-center">
    <div class="col-lg-7">
    <div class="p-5">

    <div class="text-center">
    <h3 class="h4 text-gray-900 mb-4">Compila i campi per inserire un utente Guest</h3>
    </div>
    <form class="user">

    <div class="form-group">
    <p style="color:#4e73df;">Inserisci il nome dell'utente Guest: </p>
    <input type="text" class="form-control form-control-user" id="exampleNome" placeholder="es. Angela">
    </div>

    <div class="form-group">
    <p style="color:#4e73df;">Inserisci il cognome dell'utente Guest: </p>
    <input type="text" class="form-control form-control-user" id="exampleCognome" placeholder="es. De Martino">
    </div>

    <div class="form-group">
    <p style="color:#4e73df;">Inserisci il telefono dell'utente Guest: </p>
    <input type="tel" class="form-control form-control-user" id="exampleTelefono" placeholder="es. 3456789123">
    </div>

    <div class="form-group">
    <p style="color:#4e73df;">Inserisci il codice fiscale dell'utente Guest: </p>
    <input type="text" class="form-control form-control-user" id="exampleCodiceFiscale" placeholder="es. MVYZZV65L56I556J">
    </div>

    <div class="form-group">
    <p style="color:#4e73df;">Inserisci le patologie dell'utente Guest: </p>
    <input type="text" class="form-control form-control-user" id="examplePatologie" placeholder="es. Nessuna">
    </div>

    <div class="form-group">
    <label for="gruppoSanguigno" style="color:#4e73df;">Inserisci il gruppo sanguigno dell'utente Guest: </label>
    <select name="gruppoSanguigno" id="gruppoSanguigno" class="form-control gruppo-sanguigno">
    <option selected value="A+ ">A+</option>
    <option value="A-">A-</option>
    <option value="B+">B+</option>
    <option value="B-">B-</option>
    <option value="0+">0+</option>
    <option value="0-">0-</option>
    <option value="AB+">AB+</option>
    <option value="AB-">AB-</option>
    </select>
    </div>

    <br>

    <input type="submit" value="Aggiungi utente" class="btn btn-primary btn-user btn-block">

    </form>

    </div>
    </div>
    </div>
    </div>
    </div>
    <!-- /.container-fluid -->
    </div>
    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
    <footer class="sticky-footer bg-white ">
    <div class="container my-auto ">
    <div class="copyright text-center my-auto ">
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
    <a class="scroll-to-top rounded " href="#page-top ">
    <i class="fas fa-angle-up "></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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