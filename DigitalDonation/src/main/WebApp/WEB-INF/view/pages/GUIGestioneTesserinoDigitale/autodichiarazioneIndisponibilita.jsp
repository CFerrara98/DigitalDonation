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

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">


                <!-- Logo -->
                <a class="navbar-brand" href="#"><img class="rounded-circle" src="img/logo.png" alt="..." style="width:50px"></a>

                <div>
                    <a>
                        <h1 class="h3 mb-0 text-gray-800 scrittalogo">DIGITAL DONATION</h1>
                    </a>
                </div>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Ciao, %Utente% </span>
                            <img class="img-profile rounded-circle icone" src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">

                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> Dashboard
                            </a>
                            <div class="dropdown-divider"></div>

                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Logout
                            </a>
                        </div>
                    </li>

                </ul>
            </nav>
            <!-- End of Topbar -->


            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <div class="card-body">
                        <!-- indietro -->
                        <a href="dashboardDonatore.html" role="button"> <i class="fas fa-arrow-left float-left icone"></i></a>

                        <!-- titolo -->
                        <h1 class="h3 mb-2 text-gray-800" style="text-align: center">Autodichiarazione d'indisponibilità</h1>

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
                                            <h3 class="h4 text-gray-900 mb-4">Compila il form per l'autodichiarazione d'indisponibilità</h3>
                                        </div>

                                        <form:form action="./autodichiarazioneIndisponibilita" method="post" modelAttribute="autodichiarazioneForm" cssClass="user" enctype="application/x-www-form-urlencoded">
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <p style="color:#4e73df;">Data prossima disponibilità: </p>
                                                <c:choose>
                                                    <c:when test="${DataIndisponibilitaError == null}">
                                                        <form:input type="date" class="form-control form-control-user" id="dataIndisponibilita" placeholder="Data indisponibilità:" path="dataIndisponibilita"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:input type="date" class="form-control form-control-user is-invalid" id="dataIndisponibilita" placeholder="Data indisponibilità:" path="dataIndisponibilita"/>
                                                        <span class="myError">${DataIndisponibilitaError}</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                            <div class="form-group">
                                                <p style="color:#4e73df;">Motivazione di indisponibilità: </p>
                                                <c:choose>
                                                    <c:when test="${MotivazioneError == null}">
                                                        <form:textarea class="textarea form-control" id="exampleMotivazione" placeholder="es. Abuso di sostanze stupefacenti" rows="4" path="motivazione"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:textarea class="textarea form-control" id="exampleMotivazione" placeholder="es. Abuso di sostanze stupefacenti" rows="4" path="motivazione"/>
                                                        <span class="myError">${MotivazioneError}</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        <form class="user">
                                            <br>

                                            <input type="submit" value="Conferma" class="btn btn-primary btn-user btn-block">

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

</html>
</z:layout>