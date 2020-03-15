<%@ include file = "header.jsp" %>
<div class="wrapper">
    <nav id="sidebar">
    	<img src=${user.image} alt="User Image" class="img-thumbnail">
        <div class="sidebar-header text-center">
            <h4>${user.name} ${user.surname}</h4>
        	<p>${user.email}</p>
        </div>
        <ul class="list-unstyled components">
            <li class="active">
                <a href="ViewUsersPIServlet?viewuserid=${user.idusers}">Personal Information</a>
            </li>
            <li>
                <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Network</a>
                <ul class="collapse list-unstyled" id="pageSubmenu">
                    <c:forEach items="${friendslist}" var="friend">
                    	<li>
                    		<a class="card-title lead" href="ViewUsersPIServlet?viewuserid=${friend.user.idusers}">
                    				${friend.user.name} ${friend.user.surname}
                    				email: ${friend.user.email}</a>
                    	</li>
                    </c:forEach>
                </ul>
            </li>
        </ul>

    </nav>
</div>

<form action="PostServlet?action=post" method="POST">
	<div class="input-group input-center input-sizing">
  		<textarea class="form-control" id="textarea" placeholder="Enter Your Text Here..." name="post"></textarea>
  		<div class="input-group-append">
			<input class="btn btn-primary btn-sm btn-defoutline" type="submit" value="Post">
  		</div>
	</div>
</form>

<c:forEach items="${Postlist}" var="post">
			<div class="d-flex justify-content-center">	
				<div class="card border-info text-center" style="width: 30rem;">
			  		<div class="card-body">
			  			<div class="card-title float-left">
			  				${post.user.name} ${post.user.surname} says:
			  			</div>
			    		<p class="card-text">${post.text}</p>
				  		<c:if test="${not empty post.postcomments}">	 
				  			 <div class="dropdown">
	  							<button class="btn btn-link float-left" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    								Comments
	  							</button>
		  							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				  						<c:forEach items="${post.postcomments}" var="comment">	
			    							<p>${comment.user.name} ${comment.user.surname}: ${comment.comment}</p>
				  						</c:forEach>
		  							</div>
							</div>
						</c:if>
						<form action="PostServlet?action=interest&postid=${post.idposts}" method="POST">
							<div class="btn-group">	
	                    	<c:if test="${user.idusers ne post.user.idusers}">
								<button type="submit" class="btn  btn-defoutline" style="color: #FFFFFF">I'm Interested</button>
							</c:if>
							<c:if test="${not empty post.postinterests}">
									<div class="dropdown">
										<button class="btn btn-defoutline dropdown-toggle" style="color: #FFFFFF;" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    			All Interested
										</button>
										<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
											<c:forEach items="${post.postinterests}" var="interest">
						                    	<a class="card-text" href="ViewUsersPIServlet?viewuserid=${intereast.user.idusers}">${interest.user.name} ${interest.user.surname}</a>
						                    	<br/>										
											</c:forEach>
										</div>
									</div>
							</c:if>
							</div>
						</form>
			  		</div>
				</div>
			</div>
			<form action="PostServlet?action=comment&postid=${post.idposts}" method="POST">
				<div class="input-group input-center">
  					<textarea class="form-control" id="commentarea" placeholder="Enter Your Comment Here" name="comment"></textarea>
  					<div class="input-group-append">
						<input class="btn btn-primary btn-sm btn-defoutline" type="submit" value="Post">
  					</div>
				</div>
			</form>
		</c:forEach>
<%@ include file = "footer.jsp" %>