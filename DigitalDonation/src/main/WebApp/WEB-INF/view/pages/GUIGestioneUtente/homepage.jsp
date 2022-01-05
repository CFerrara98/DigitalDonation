<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>


<z:layout pageTitle="homepage">

    <!-- css solo della Homepage, da non mettere in layout-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Page Wrapper -->
    <div id="wrapper">

    <!-- Content Wrapper -->
        <!-- Inizio del carosello-->
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="../../resources/img/carosello1.png" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="../../resources/img/carosello2.png" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="../../resources/img/carosello3.png" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <!-- Fine del carosello -->

            <!-- Features section-->
            <section class="py-5" id="features">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                        <div class="col-lg-4 mb-5 mb-lg-0">
                            <h2 class="fw-bolder mb-0">Le donazioni in quattro punti</h2>
                        </div>
                        <div class="col-lg-8">
                            <div class="row gx-5 row-cols-1 row-cols-md-2">
                                <div class="col mb-5 h-100">
                                    <div class="feature bg-primary bg-gradient rounded-3 mb-3"><i class="bi bi-building"></i></div>

                                    <p class="mb-0">Il sangue è una risorsa importantissima, non riproducibile in laboratorio, fondamentale per la vita di tutti ma essenziale per la sopravvivenza di chi ne ha bisogno.</p>
                                </div>
                                <div class="col mb-5 h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-building"></i></div>

                                    <p class="mb-0">In Italia si effettua 1 donazione di sangue ogni 10 secondi che permette si salvare circa 1.626.506 pazienti al giorno.</p>
                                </div>
                                <div class="col mb-5 mb-md-0 h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-toggles2"></i></div>
                                    <p class="mb-0">Ogni anno si effettuano 2,8 milioni di trasfusioni per una media di 5,4 al minuto. Più di 800mila kg di plasma vengono utilizzati per la produzione di farmaci plasmaderivati.</p>
                                </div>
                                <div class="col h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-toggles2"></i></div>
                                    <p class="mb-0">Per diventare donatori bisogna avere un'età compresa tra 18 e 60 anni ed avere uno stato di salute ottimale.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Testimonial section-->
            <div class="py-5 " style="background: #D8EBFF">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-10 col-xl-7">
                            <div class="text-center">
                                <div class="fs-4 mb-4 fst-italic">"La donazione di sangue è un grande atto di umanità, è vita, condividila!"</div>
                                <div class="d-flex align-items-center justify-content-center">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Blog preview section-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8 col-xl-6">
                            <div class="text-center">
                                <h2 class="fw-bolder">Testimonianze</h2>
                                <p class="lead fw-normal text-muted mb-5">"Ho deciso di diventare donatore quando un mio carissimo amico si è ammalato di leucemia. Ho realizzato in quel momento quanto fosse importante un piccolo gesto per salvare delle persone. Da allora ho iniziato a guardare
                                    la vita in modo diverso ed apprezzare tutti coloro che rendono ciò possibile." <br> -Raffaele, 46 anni. </p>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-5">
                        <div class="col-lg-4 mb-5">
                            <div class="card h-80 shadow border-0">
                                <img class="card-img-top" src="../../resources/img/mani.png" alt="..." />
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-80 shadow border-0">
                                <img class="card-img-top" src="../../resources/img/lettino.png" alt="..." />
                            </div>
                        </div>
                        <div class="col-lg-4 mb-5">
                            <div class="card h-80 shadow border-0">
                                <img class="card-img-top" src="../../resources/img/operatori.png" alt="..." />
                            </div>
                        </div>
                    </div>

                </div>
            </section>






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


</z:layout>
