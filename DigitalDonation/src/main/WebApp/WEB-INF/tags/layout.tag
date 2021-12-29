<%@ tag body-content="scriptless"%>
<%@ attribute name="pageTitle" required="true" type="java.lang.String"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="http://fonts.cdnfonts.com/css/playlist" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="<c:url value="../../resources/css/errorCss/layout.css"/>" rel="stylesheet" type="text/css">

	<link href="resources/css/product.css" rel="stylesheet" type="text/css">

	<link href="<c:url value="../../resources/css/errorCss/error.css"/>" rel="stylesheet" type="text/css">
	<title>${pageTitle}</title>
</head> 

<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://kit.fontawesome.com/442bbb4090.js" crossorigin="anonymous"></script>
	<!-- Navbar -->
	
	<div id="navbar" class="main-bar">
        <nav>

        </nav>
    </div>

	<!-- Navbar -->
	<main>
		<div class="container">
			<jsp:doBody />
		</div>
	</main>


	<script>
	// When the user scrolls the page, execute myFunction
	window.onscroll = function() {myFunction()};

	// Get the navbar
	var navbar = document.getElementById("navbar");

	// Get the offset position of the navbar
	var sticky = navbar.offsetTop;

	// Add the sticky class to the navbar when you reach its scroll position. Remove "sticky" when you leave the scroll position
	function myFunction() {
	  if (window.pageYOffset >= sticky) {
	    navbar.classList.add("sticky")
	  } else {
	    navbar.classList.remove("sticky");
	  }
	}
	</script>
	<!-- Footer colors #2e2e2e   and #252525  -->

	<div class="footer">

	</div>
	<!-- Footer -->
</body>

</html>