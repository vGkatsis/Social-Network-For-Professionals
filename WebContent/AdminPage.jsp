<%@ include file = "header.jsp" %>
<div class="container container-list-right">
		<c:forEach items="${userslist}" var="User">
			<div class="list-group text-center bg-primary" id="adminhover">
				<h4>${User.name} ${User.surname}</h4>
				<a class="btn btn-primary" role="button" style="color: #000000" href="ViewUsersPIServlet?viewuserid=${User.idusers}">Personal Info</a>
				<a class="btn btn-primary" role="button" style="color: #000000" href="SettingsServlet?action=admin&email=${User.email}&password=${User.password}">Settings</a>
			</div>
			<div class="text-center">
				<form action="AdminServlet?userid=${user.idusers}" method="POST">
					<button type="submit" class="btn btn-primary" style="color: #000000">Delete ${User.name} ${User.surname}</button>
				</form>
			</div>
		</c:forEach>
</div>
<%@ include file = "footer.jsp" %>