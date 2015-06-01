<%@page import="sensos.platform.helper.DeviceHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../shared_header.jsp" />

<div id="content">

	Manage device <br />

	<%= DeviceHelper.getDevices(request) %>

</div>

<jsp:include page="../shared_footer.jsp" />

</body>

</html>
