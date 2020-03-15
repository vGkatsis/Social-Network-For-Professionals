<%@ include file = "header.jsp" %>
<div class="float-right">
	<div class="d-flex justify-content-center">
		<form class="form-inline my-2 my-lg-0" action="UserSearchServlet" method="GET">
      		<input class="form-control mr-sm-2" type="search" id="search" placeholder="Search for Users by Name" name="usersearch" aria-label="Search">
      		<button class="btn btn-primary my-2 my-md-0 btn-md btn-defoutline" type="submit">Search</button>
		</form>
	</div>
	<c:if test="${not empty searchuserslist}">	
		<div class="dropdown">
	  		<button class="btn btn-defoutline btn-width dropdown-toggle" style="color: #FFFFFF" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    		Search Results
	  		</button>
	  		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<c:forEach items="${searchuserslist}" var="user" varStatus="status">
	    			<a class="dropdown-item" href="ViewUsersPIServlet?viewuserid=${user.idusers}">${user.name} ${user.surname} email: ${user.email}</a>
	  			</c:forEach>
	  		</div>
		</div>
	</c:if>
</div>
<br/>

<div class="container float-left mt-5">
    <div class="row">
		<c:forEach items="${friendslist}" var="friend">
			<div class="col-auto mb-3">
            	<div class="card border-info text-center" style="width: 18rem;">
                	<div class="card-body">
                    	<a class="card-title lead" href="ViewUsersPIServlet?viewuserid=${friend.user.idusers}">${friend.user.name} ${friend.user.surname}</a>
                    	<c:if test="${not empty friend.workspace}">
                    		<p class="card-text"> Working At: ${friend.workspace} </p>
                    	</c:if>
                    	<c:if test="${empty friend.workspace}">
                    		<p class="card-text"> Workspace: Not Registered </p>
                    	</c:if>
                    	<c:if test="${empty friend.position}">
                    		<p class="card-text"> Work Position: Not Registered </p>
                		</c:if>
                		<c:if test="${not empty friend.position}">
                    		<p class="card-text"> Working As: ${friend.position} </p>
                    	</c:if>
                		<form action="FriendRequestServlet?action=decline&sender=${friend.user.idusers}&receiver=${user.idusers}" method="POST">
								<button type="submit" class="btn btn-defoutline" style="color: #FFFFFF">Remove Friend</button>
						</form>
                	</div>
            	</div>
        	</div>
		</c:forEach>    
   </div>
</div>
<%@ include file = "footer.jsp" %>