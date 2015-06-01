<%@page import="sensos.platform.helper.ProfileHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../shared_header.jsp" />

<div id="content">

	Manage Profile <br />

	<%= ProfileHelper.getProfile(request) %>

</div>

<jsp:include page="../shared_footer.jsp" />

</body>

</html>
