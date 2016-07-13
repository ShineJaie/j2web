<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title><tiles:getAsString name="title"/></title>

    <tiles:insertAttribute name="meta"/>
    <tiles:insertAttribute name="stylesheets_default"/>
    <tiles:insertAttribute name="stylesheets_self"/>
    <tiles:insertAttribute name="is_support_h5"/>
</head>
<body>
<div id="wrapper">
    <tiles:insertAttribute name="header"/>
    <!-- Page Content -->
    <div id="page-content-wrapper">
        <tiles:insertAttribute name="content"/>
    </div>
    <tiles:insertAttribute name="footer"/>
</div>
<tiles:insertAttribute name="js_default"/>
<tiles:insertAttribute name="js_self"/>
</body>
</html>
