<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    require(['<c:url value="/static/myjs/main.js"/>'], function (main) {
        require(['home'], function (home) {
            home.initpage_home();
        });
    });
</script>
