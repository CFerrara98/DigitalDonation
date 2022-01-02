<%--
  Created by IntelliJ IDEA.
  User: abasi
  Date: 30/12/2021
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>

<!-- ANCORA NON FINITO! -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<z:layout pageTitle="header">

    <!-- Topbar -->
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

        <!-- Logo -->
        <a class="navbar-brand" href="#"><img class="rounded-circle" src="../../resources/img/logo.png" alt="..."
                                              style="width:50px"></a>

        <div>
            <a><h1 class="h3 mb-0 text-gray-800 scrittalogo">DIGITAL DONATION</h1></a>
        </div>

        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Ciao,  <c:out value="${utente.nome}"></c:out>  </span>
                    <img class="img-profile rounded-circle icone"
                         src="../../resources/img/undraw_profile.svg">
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                     aria-labelledby="userDropdown">

                    <c:choose>
                        <c:when test="${utente.class.simpleName == 'Donatore'}">
                            <a class="dropdown-item" href="./dashboardDonatore">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Dashboard
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="dropdown-item" href="./dashboardOperatore">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Dashboard
                            </a>
                        </c:otherwise>
                    </c:choose>


                    <div class="dropdown-divider"></div>

                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Logout
                    </a>
                </div>
            </li>

        </ul>
    </nav>


    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Sicuro di voler uscire?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Clicca "Logout" se vuoi uscire dall'account.</div>
                <div class="modal-footer">


                    <c:if test="${utente!=null}"><a href="/logout"> </a></c:if>
                    <a class="btn btn-primary" href="/logout">Logout</a>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Annulla</button>
                </div>
            </div>
        </div>
    </div>


    <!-- End of Topbar -->

</z:layout>