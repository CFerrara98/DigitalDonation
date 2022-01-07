<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="Salvataggio Donazione">


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
                <a href="./goElencoPartecipanti?idSeduta=${idSeduta}" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>
                <!-- Titolo -->
                <h1 class=" mb-4" style="text-align: center">Conferma donazione</h1>

                <!-- Per dividere a blocchi-->
                <div class="container mt-6" style="padding-top:60px;">

                    <!-- Seduta di donazione -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Conferma donazione</h6>
                        </div>
                        <div class="card-body">
                            <div class="text-center">
                                <h1>&nbsp;</h1>
                                <h4 class=" mb-4" style="text-align: center">Confermi l'avvenuta donazione?</h4>
                                <h1>&nbsp;</h1>


                                <!-- menu tendina -->
                                <div class="container">

                                    <!--
                                        <%--@elvariable id="confermaDonazioneForm" type="it.unisa.is.c09.digitaldonation.Utils.Forms.ConfermaDonazioneForm"--%>
                                    <form:form action="./salvataggioDonazione" method="post" modelAttribute="confermaDonazioneForm" cssClass="user" enctype="application/x-www-form-urlencoded">

                                        <div class="form-group row">
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                <label for="gruppoSanguigno" style="color:#4e73df;">Inserire il tipo di donazione del donatore: </label>

                                            </div>

                                            <div class="col-sm-6">


                                                <form:select name="gruppoSanguigno"
                                                             id="gruppoSanguigno"
                                                             class="form-control gruppo-sanguigno"
                                                             path="tipoDonazione">

                                                    <form:option value="plasma">
                                                        Plasma
                                                    </form:option>
                                                    <form:option value="cito">
                                                        Cito
                                                    </form:option>
                                                    <form:option value="sangue">
                                                        Sangue
                                                    </form:option>

                                                </form:select>
                                            </div>
                                        </div>

                                </div>
                                <input type="submit" value="Conferma" class="btn btn-primary">
                            </div>

                                    </form:form>
                                    --                                                             >

                                    <form action="./salvataggioDonazione" method="post" id="form">
                                        <div class="form-check">
                                            <div class="col-sm-8 mb-3 mb-sm-0">
                                            <select name="gruppoSanguigno"
                                                    id="gruppoSanguigno"
                                                    class="form-control gruppo-sanguigno">
                                                <option selected="true" value="plasma">Plasma</option>
                                                <option value="cito">Cito</option>
                                                <option value="sangue">Sangue</option>
                                            </select>
                                            </div>
                                        </div>
                                        <br>
                                        <input type="submit" value="Conferma" class="btn btn-primary">
                                    </form>

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
    </div>
</div>
<!-- End of Page Wrapper -->

</z:layout>

