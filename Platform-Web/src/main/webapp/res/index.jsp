<%@page import="sensos.platform.helper.ApplicationHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../shared_header.jsp" />    

<div id="content">

    Welcome to the platform: <%= request.getRemoteUser()%>! <br/>
    Are you in Basic role: <%= request.isUserInRole("Basic")%> <br/>
    Are you in Home role: <%= request.isUserInRole("HomePlus")%> <br/>
    Are you in Fun role: <%= request.isUserInRole("FunPlus")%> <br/>
    Are you in Profi role: <%= request.isUserInRole("ProfiPlus")%> <br/>
    Are you in johndoe role: <%= request.isUserInRole("johndoe")%> <br/>

    <form id="logoutForm" action="logout" method=post>
        <p class="submit">
            <button type="submit" name="logoutAction" value="1">Logout</button> <br/>
        </p>
    </form>        

    <form id="logoutForm" action="manageProfileAction" method=post>
        <p class="submit">
            <button type="submit" name="manageProfileAction" value="1">Manage Profile</button><br/>
        </p>
    </form>        

    <form id="logoutForm" action="manageDeviceAction" method=post>
        <p class="submit">
            <button type="submit" name="manageDeviceAction" value="1">Manage Device</button><br/>
        </p>
    </form>        

    <%= ApplicationHelper.getApplications(request)%>


    <!--
    <button type="submit" name="websocketApplication" value="1">Websocket Application example</button><br/>
    <button type="submit" name="jsonApplication" value="1">json Application example</button><br/>
    -->

</div>

<jsp:include page="../shared_footer.jsp" />        

</body>

</html>        