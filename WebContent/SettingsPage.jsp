<%@ include file = "header.jsp" %>
<div class="d-flex justify-content-center">	
<div class="card border-light" style="width: 100rem;">
<div class="card-body offset-4">
	<form  action="SettingsServlet" method="POST">
		<div class="form-group row">
			<label for="inputnewemail" class="col-sm-2 col-form-label">Email</label>
			<div class="col-sm-4">
			     <input type="email" class="form-control" id="inputnewemail" placeholder="youremail@e.g.com" name="newemail" value="${sessionScope.user.email}">
			</div>
		</div>
		<div class="form-group row">
			<c:if test="${not empty sessionScope.admin}">
				<label for="inputoldpassword" class="col-sm-2 col-form-label">Old Password*</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="inputoldpassword" placeholder="Old Password" name="oldpassword" value="${sessionScope.user.password}">
				</div>
			</c:if>
			<c:if test="${empty sessionScope.admin}">
				<label for="inputoldpassword" class="col-sm-2 col-form-label">Old Password*</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="inputoldpassword" placeholder="Old Password" name="oldpassword">
				</div>
			</c:if>
		</div>
		<div class="form-group row">
			<label for="inputnewpassword" class="col-sm-2 col-form-label">Password</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="inputnewpassword" placeholder="Password" name="newpassword" value="${sessionScope.user.password}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputnewpassword2" class="col-sm-2 col-form-label">Password (Repeat)</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="inputnewpassword2" placeholder="Password(Repeat)" name="newpassword2" value="${sessionScope.user.password}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputnewname" class="col-sm-2 col-form-label">Name</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputnewname" placeholder="Name" name="newname" value="${sessionScope.user.name}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputnewsurname" class="col-sm-2 col-form-label">Surname</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputnewsurname" placeholder="Surname" name="newsurname" value="${sessionScope.user.surname}">
			</div>
		</div>
		<div class="form-group row">
			<label for="inputnewtelnumber" class="col-sm-2 col-form-label">Tel.Number</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="inputnewtelnumber" placeholder="Tel.Number" name="newnumber" value="${sessionScope.user.telnumber}">
			</div>
		</div>
		<div class="form-group">
    		<label for="imagefile">Select an image</label>
    		<input type="file" class="form-control-file" id="imagefile" name="image">
  		</div>
		<p>*Password Fields Required To Change Your Settings</p>
		<div class="text-left">
			<a href="SettingsPage.jsp" id="cancel" name="cancel" class="btn btn-outline-light btn-defoutline">Cancel</a>
			<button type="submit" class="btn btn-outline-light btn-defoutline">Save Changes</button>
		</div>
	</form>
</div>
</div>
</div>
<%@ include file = "footer.jsp" %>