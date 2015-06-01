<%@page import="sensos.platform.helper.NoaccessHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./shared_header.jsp" />
No Access
<br />

<%= NoaccessHelper.getAccessedResource(request)%>

<a href="/platform/web/auth">Sign in today!</a>

<jsp:include page="./shared_footer.jsp" />



























































































<!--
<div id="app_header">
<h1>Sign-In today!</h1>
</div>-->




<!--
<div id="content">

    No Access <br/>

<%= NoaccessHelper.getAccessedResource(request)%>

<a href="/login">Sign in today!</a>

</div>-->

<jsp:include page="./shared_footer.jsp" />

</body>

</html>
