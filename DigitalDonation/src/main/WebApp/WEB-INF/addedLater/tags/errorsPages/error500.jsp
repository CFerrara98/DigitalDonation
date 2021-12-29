<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isErrorPage="true" %>


<link href="resources/css/product.css" rel="stylesheet" type="text/css">

<link href="<c:url value="../../../view/css/error.css"/>" rel="stylesheet" type="text/css">
<z:layout pageTitle="ErrorPage500">



		<div  >	
			
			<h1 class="error-code">500</h1>
			<p class="error">Oops! Something went wrong.</p>
			<p class="error">Try to refresh this page or feel free to contact us if the problem persists.</p>
			
		</div>
	
		
		<form method="get" action="Home">
			<button  class="btn btn-success mt-2" >Go Back</button>
		</form>
            
            
            <div class="objects">
                <img class="object_face_rocket" src="${pageContext.request.contextPath}/resources/images/error/triste.png" width="80px">
           
                <div class="box_face">
                    <img class="object_face" src="${pageContext.request.contextPath}/resources/images/error/triste.png" width="140px">
            </div>
            
            
            
            </div>
            


</z:layout>