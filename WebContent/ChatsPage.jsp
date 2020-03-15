<%@ include file = "header.jsp" %>
<div class="wrapper">
    <nav id="sidebar" style="border:1px solid #000;">
        <div class="sidebar-header text-center">
            <h4>Chat List</h4>
        </div>
        <ul class="list-unstyled components">
	    	<c:if test="${not empty chatlist}">
	            <c:forEach items="${chatlist}" var="chatitem">
	            	<li>
	                	<a href="ChatServlet?chatid=${chatitem.idchats}" class="card text-white mb-3" style="max-width: 18rem; background: #113377;">
	                		Chat With: ${chatitem.user.name} ${chatitem.user.surname}</a>
	                </li>
	            </c:forEach>
        	</c:if>
        </ul>
    </nav>
</div>

<c:if test="${not empty chatlist}">
<form action="ChatServlet?action=message&chatid=${curchatid}" method="POST">
	<div class="input-group input-center input-sizing">
  		<textarea class="form-control" id="messagearea" placeholder="Enter Your Text Here..." name="msg"></textarea>
  		<div class="input-group-append">
			<input class="btn btn-primary btn-sm btn-defoutline" type="submit" value="Send">
  		</div>
	</div>
</form>
<c:if test="${not empty chat}">
 <div class="container" style="position: relative; left: 50%; transform: translatex(-50%);">	 
	 <c:forEach items="${chat}" var="msg">
		<c:if test="${msg.senderid eq user.idusers}">
				<div class="card-columns">	
					<div class="float-right">		
						<div class="card border-primary mb-3" style="max-width: 18rem;">
					  		<div class="card-body">
					    		<p class="card-text">${msg.message}</p>
					  		</div>
						</div>
					</div>
				</div>
		</c:if>
		<c:if test="${msg.senderid ne user.idusers}">	
				<div class="card-columns">	
					<div class="float-left">	
						<div class="card border-danger mb-3" style="max-width: 18rem;">
					  		<div class="card-body">
					    		<p class="card-text">${msg.message}</p>
					  		</div>
						</div>
					</div>
				</div>
		</c:if>
	 </c:forEach>
</div>
</c:if>
</c:if>
<%@ include file = "footer.jsp" %>