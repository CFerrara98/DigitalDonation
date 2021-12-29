<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isErrorPage="true" %>


<link href="resources/css/product.css" rel="stylesheet" type="text/css">
<link href="<c:url value="../../../view/css/error.css"/>" rel="stylesheet" type="text/css">

<z:layout pageTitle="ErrorPage401">

		<div>	
			
			<h1 class="error-code">401</h1>
			<p class="error">Oops! Authorization required.</p>
			<p class="error">Try login frist.</p>
			
		</div>
			
		<form method="get" action="Home">
			<button  class="btn btn-success mt-2" >Go Back</button>
		</form>
                       

            
                
            </div>
            


</z:layout>