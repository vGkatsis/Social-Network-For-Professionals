<%@ include file = "header.jsp" %>
 <div class="card-deck mt-3">	
	<div class="row">
		<c:forEach items="${senders}" var="sender">
			<div class="d-flex justify-content-center">	
				<div class="card text-center" style="width: 30rem;">
			  		<div class="card-header">
			    		Friend Request
			  		</div>
			  		<div class="card-body">
			  			<a class="card-title" href="ViewUsersPIServlet?viewuserid=${sender.idusers}">${sender.name} ${sender.surname}</a>
			    		<p class="card-title"> sent you a friend request.</p>
			    		<p class="card-text">Press accept to add ${sender.name} ${sender.surname} as your friend.</p>
			    		<div class="btn-group" role="group" aria-label="Basic example">
				    		<form action="FriendRequestServlet?action=accept&sender=${sender.idusers}&receiver=${user.idusers}" method="POST">
								<button type="submit" class="btn btn-defoutline" style="color: #FFFFFF">Accept</button>
							</form>
				    		<form action="FriendRequestServlet?action=decline&sender=${sender.idusers}&receiver=${user.idusers}" method="POST">
								<button type="submit" class="btn btn-defoutline" style="color: #FFFFFF">Decline</button>
							</form>
						</div>
			  		</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

 <div class="card-deck mt-3">	
	<div class="row">
		<c:forEach items="${commenters}" var="commenter">
			<div class="d-flex justify-content-center">	
				<div class="card text-center" style="width: 30rem;">
			  		<div class="card-header">
			    		A User Posted A Comment
			  		</div>
			  		<div class="card-body">
			    		<a class="card-title" href="ViewUsersPIServlet?viewuserid=${commenter.idusers}">${commenter.name} ${commenter.surname}</a>
			    		<p class="card-title">commented on one of your posts.</p>
			  		</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

 <div class="card-deck mt-3">	
	<div class="row">
		<c:forEach items="${interested}" var="interest">
			<div class="d-flex justify-content-center">	
				<div class="card text-center" style="width: 30rem;">
			  		<div class="card-header">
			    		A User Showed Interest
			  		</div>
			  		<div class="card-body">
			  			<a class="card-title" href="ViewUsersPIServlet?viewuserid=${interest.idusers}">${interest.name} ${interest.surname}</a>
			    		<p class="card-title">is interested in one of your posts.</p>
			  		</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<%@ include file = "footer.jsp" %>