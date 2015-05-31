<%@page import="sensos.dock.mqtt.MQTTActivator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>

    <jsp:include page="./shared_header.jsp" />    

    <div id="content">

        Platform-Backend-Dock-Welcome2
        
        <%MQTTActivator a = new MQTTActivator(); a.applicationStartup(); %>

    </div>

    <jsp:include page="./shared_footer.jsp" />        

</body>


</html>        