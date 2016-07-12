<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>500 服务器内部错误 :(</title>

    <link rel="shortcut icon" href="<c:url value='/static/images/favicon.ico'/>">

    <!-- Bootstrap -->
    <link href="<c:url value='/static/dependency/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<c:url value='/static/dependency/js/html5shiv.min.js'/>"></script>
    <script src="<c:url value='/static/dependency/js/respond.min.js'/>"></script>
    <![endif]-->
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1 class="text-danger text-center">哎呀，服务器出错啦...</h1>

            <c:if test="${!empty url}">
                <h2>请求地址:
                    <mark>${url}</mark>
                </h2>
            </c:if>

            <h3>
                <img src="<c:url value='/static/images/500.jpg'/>" alt="404"
                     class="img-rounded img-responsive center-block">
            </h3>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/static/dependency/js/jquery.min.js'/>"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/static/dependency/bootstrap/js/bootstrap.min.js'/>"></script>

</body>
</html>