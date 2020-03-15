<%@ include file = "header.jsp" %>
<div class="wrapper">
    <nav id="sidebar" style="border:1px solid #000;">
        <div class="sidebar-header text-center">
            <h4>Job Application Options</h4>
        </div>
        <ul class="list-unstyled components">
			<li>
				<a class="btn" role="button" href="JobAppServlet?action=net">Network JobApplications</a>			
			</li>
			<li>
				<a class="btn" role="button" href="JobAppServlet?action=recommended">Recommended JobApplications</a>			
			</li>
			<li>
				<a class="btn" role="button" href="JobAppServlet?action=my">My JobApplications</a>			
			</li>
			<li>
				<div class="container text-center">
					<button type="button" class="btn btn-primary btn-defoutline btn-lg" data-toggle="modal" data-target="#newadmodal">
  							&#8853; New Add
					</button>
				</div>			
			</li>
        </ul>
    </nav>
</div>

<div class="modal fade" id="newadmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header text-center">
        <h5 class="modal-title w-100 font-weight-bold" id="newadModalLongTitle">New Ad</h5>
         <button type="button" class="close" data-dismiss="modal" aria-label="Cancel">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="JobAppServlet?action=jobap" method="POST">
				<div class="form-group row">
					<label for="inputnameofad" class="col-sm-4 col-form-label">Ad Name</label>
					<div class="col-sm-7">
			      		<input type="text" class="form-control" id="inputnameofad" placeholder="Name Of Ad" name="nameofad">
			    	</div>
				</div>
				<div class="form-group row">
			    	<label for="inputposition" class="col-sm-4 col-form-label">Position</label>
			    	<div class="col-sm-7">
			      		<input type="text" class="form-control" id="inputposition" placeholder="Position" name="position">
			    	</div>
			  	</div>
				<div class="form-group row">
			    	<label for="inputcompany" class="col-sm-4 col-form-label">Company</label>
			    	<div class="col-sm-7">
			      		<input type="text" class="form-control" id="inputcompany" placeholder="Company" name="company">
			    	</div>
			  	</div>
			  	<div class="form-group row">
			    	<label for="inputincome" class="col-sm-4 col-form-label">Income(&#8364;)</label>
			    	<div class="col-sm-7">
			      		<input type="number" min="0" step="1" class="form-control" id="inputincome" placeholder="Income" name="income">
			    	</div>
			  	</div>
			  	<div class="form-group row">
			    	<label for="inputdescription" class="col-sm-4 col-form-label">Description</label>
			    	<div class="col-sm-7">
			    		<textarea class="form-control" id="inputdescription" placeholder="Enter Your Description Here..." name="description"></textarea>
			    	</div>
			  	</div>
				<div class="text-right">
					<a href="AdsPage.jsp" id="cancel" class="btn btn-outline-light btn-defoutline">Cancel</a>
					<button type="submit" class="btn btn-outline-light btn-defoutline">Save Changes</button>
				</div>
			</form>
      </div>
    </div>
  </div>
</div>


<div class="container mt-5">
    <div class="row">
		<c:forEach items="${jobapplications}" var="app">
			<div class="col-auto mb-3">
            	<div class="card border-info text-center" style="width: 18rem;">
                	<div class="card-body">
                    	<c:if test="${not empty app.jobname}">
                    		<p class="card-text lead"> ${app.jobname} </p>
                    	</c:if>
                    	<c:if test="${empty app.jobname}">
                    		<p class="card-text"> Untitled Job Application </p>
                    	</c:if>
                    	<c:if test="${not empty app.jobposition}">
                    		<p class="card-text"> Position: ${app.jobposition} </p>
                		</c:if>
                		<c:if test="${empty app.jobposition}">
                    		<p class="card-text"> Position Not Defined </p>
                    	</c:if>
                    	<c:if test="${not empty app.jobcompany}">
                    		<p class="card-text"> Company: ${app.jobcompany} </p>
                		</c:if>
                		<c:if test="${empty app.jobcompany}">
                    		<p class="card-text"> Company Not Defined </p>
                    	</c:if>
                    	<c:if test="${not empty app.jobincome}">
                    		<p class="card-text"> Income(&#8364;): ${app.jobincome} </p>
                		</c:if>
                		<c:if test="${empty app.jobincome}">
                    		<p class="card-text"> Income Not Defined </p>
                    	</c:if>
                    	<c:if test="${not empty app.jobdescription}">
                    		<p class="card-text"> Description: ${app.jobdescription} </p>
                		</c:if>
                		<c:if test="${empty app.jobdescription}">
                    		<p class="card-text"> No Description Available </p>
                    	</c:if>
                    	<form action="JobAppServlet?action=apply&jobapid=${app.idjobap}" method="POST">
							<div class="btn-group">	
	                    	<c:if test="${user.idusers ne app.user.idusers}">
								<button type="submit" class="btn  btn-defoutline" style="color: #FFFFFF">Apply</button>
							</c:if>
							<c:if test="${not empty app.jobapplications}">
								<div class="dropdown">
									<button class="btn btn-defoutline dropdown-toggle" style="color: #FFFFFF;" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    		Applied
									</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<c:forEach items="${app.jobapplications}" var="applied">
						                    <a class="card-text" href="ViewUsersPIServlet?viewuserid=${applied.user.idusers}">
						                    		${applied.user.name} ${applied.user.surname}
						                    	</a>										
										</c:forEach>
									</div>
								</div>
							</c:if>
							</div>
						</form>
                    	<c:if test="${user.idusers ne app.user.idusers}">
                    	<a class="card-text float-right" href="ViewUsersPIServlet?viewuserid=${app.user.idusers}">
                    		Ad posted by ${app.user.name} ${app.user.surname}</a>
                		</c:if>
                	</div>
            	</div>
        	</div>
		</c:forEach>    
   </div>
</div>
<%@ include file = "footer.jsp" %>