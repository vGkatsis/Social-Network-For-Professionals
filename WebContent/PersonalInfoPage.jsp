<%@ include file = "header.jsp" %>
<div class="d-flex justify-content-center">	
<div class="card border-light" style="width: 100rem;">
<div class="card-body offset-4">
	<form  action="PersonalInfoServlet" method="POST">
		<div class="form-group row">
			<label for="inputschool" class="col-sm-2 col-form-label">School</label>
			<div class="col-sm-4">
			     <input type="text" class="form-control" id="inputschool" placeholder="School" name="school" value="${viewpi.school}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputbachelor" class="col-sm-2 col-form-label">Bachelor</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputbachelor" placeholder="Bachelor" name="bachelor" value="${viewpi.bachelor}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputmaster" class="col-sm-2 col-form-label">Master</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputmaster" placeholder="Master" name="master" value="${viewpi.master}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputdoctorate" class="col-sm-2 col-form-label">Doctorate</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputdoctorate" placeholder="Doctorate" name="doctorate" value="${viewpi.doctorate}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputcurrentworkspace" class="col-sm-2 col-form-label">Current Workspace</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputcurrentworkspace" placeholder="Working @" name="currentworkspace" value="${viewpi.workspace}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputposition" class="col-sm-2 col-form-label">Position</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputposition" placeholder="Position" name="position" value="${viewpi.position}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputmiscellaneous" class="col-sm-2 col-form-label">Skills & Interests</label>
			<div class="input-group input-center-form">
				<textarea class="form-control" id="inputmiscellaneous" placeholder="Miscellaneous skills and interests..." name="misc">${viewpi.misc}</textarea>
			</div>
		</div>
<c:if test="${(user.idusers == viewuserid) || (not empty sessionScope.admin)}">		
		<div class="text-left">
			<a href="SettingsPage.jsp" id="cancel" class="btn btn-outline-light btn-defoutline">Cancel</a>
			<button type="submit" class="btn btn-outline-light btn-defoutline">Save Changes</button>
		</div>
</c:if>
	</form>
</div>
</div>
</div>

<c:if test="${empty admin}">
	<c:set var="exists" value="0" />
	<c:forEach var="friend" items="${friendslist}">
	  	<c:if test="${friend.user.idusers == viewuserid}">
	    	<c:set var="exists" value="1" />
	  	</c:if>
	</c:forEach>
	
	<c:if test="${user.idusers != viewuserid}">		
		
		<c:if test="${exists eq 0}">				
			<form action="FriendRequestServlet?action=send" method="POST">
				<button type="submit" class="btn btn-lg btn-defoutline" style="color: #FFFFFF">Friend Request</button>
			</form>
		</c:if>
		<c:if test="${exists eq 1}">				
			<form action="ChatServlet?action=newchat&seconduserid=${viewuserid}" method="POST">
				<button type="submit" class="btn btn-lg btn-defoutline float-right" style="color: #FFFFFF">Personal Message</button>
			</form>
		</c:if>
	</c:if>
</c:if>
<%@ include file = "footer.jsp" %>