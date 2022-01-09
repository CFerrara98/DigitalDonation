<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="partecipa Seduta">
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
                    <!-- indietro -->
                    <a href="./goSeduteDisponibili" role="button"> <i
                            class="fas fa-arrow-left float-left icone"></i></a>
                    <!-- Titolo -->
                    <h1 class=" mb-4" style="text-align: center">Seduta di donazione</h1>

                    <!-- Per dividere a blocchi-->
                    <div class="container mt-6" style="padding-top:60px;">

                        <!-- Seduta di donazione -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Seduta di donazione</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <h1>&nbsp;</h1>
                                    <h4 class=" mb-4" style="text-align: center">E' disponibile una seduta di donazione
                                        <br> a cui può partecipare :</h4>
                                    <h1>&nbsp;</h1>
                                    <!-- Dati della seduta -->
                                    <div class="container-sm">
                                        <div class="table-container">
                                            <table class="table  table-bordered table-hover">
                                                <thead class="thead-dark">
                                                <tr>
                                                    <th scope="col">Data</th>
                                                    <th scope="col">Luogo</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td><c:out
                                                            value="${seduta.dataSeduta.date}"></c:out>
                                                        /
                                                        <c:out
                                                                value="${seduta.dataSeduta.month + 1}"></c:out>
                                                        /
                                                        <c:out
                                                                value="${seduta.dataSeduta.year + 1900}"></c:out></td>
                                                    <td><c:out value="${seduta.luogo}"></c:out></td>
                                                </tr>
                                                <tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <h1>&nbsp;</h1>


                                    <!-- Menu a tendina -->
                                    <form action="./feedback" method="get" id="form">
                                        <input type="hidden" name="idSeduta" value="${seduta.idSeduta}"/>

                                            <div align="center">
                                                <div class="col-sm-8 mb-3 mb-sm-0">
                                                    <select name="feedbackSeduta"
                                                            id="feedbackSeduta"
                                                            class="form-control gruppo-sanguigno" aling="center">
                                                        <option selected="true" value="positivo"> Voglio partecipare alla seduta </option>
                                                        <option value="negativo">Non voglio partecipare alla seduta </option>
                                                    </select>
                                                    <!-- Fine menu tendina-->
                                                </div>
                                            </div>
                                        <br>
                                        <label class="form-check-label"><h6 class="mb-4" style="text-align: left">
                                            Dichiarando di voler partecipare alla seduta verrai registrato
                                            nella lista dei donatori della relativa sede locale. </h6>
                                        </label>
                                        <br>

                                        <input type="submit" value="Conferma" class="btn btn-primary">
                                    </form>
                                </div>
                            </div>
                        </div>
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
                        Il trattamento dei dati personali è svolto nel rispetto dei diritti sulla privacy in base alla legge n. 675 del 31 dicembre 1996
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