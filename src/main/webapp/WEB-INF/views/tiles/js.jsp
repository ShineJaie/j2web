<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="<c:url value='/static/dependency/js/jquery.min.js'/>"></script>
<script src="<c:url value='/static/dependency/bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<c:url value="/static/dependency/jquery-pjax/jquery.pjax.js"/>"></script>
<script src="<c:url value="/static/dependency/nprogress/nprogress.js"/>"></script>

<script>
    $(document).ready(function () {

        var pjaxProgress = function () {
            $(document).on('pjax:start', function () {
                NProgress.start();
            });
            $(document).on('pjax:end', function () {
                NProgress.done();
            });
        };
        pjaxProgress();

        /**
         * jQuery.ajax 的全局配置<br/>
         * statusCode 参数用来做这件事再好不过了, <br/>
         * 而且重要的是, 即使 ajax 代码中禁用了全局配置(global:false),<br/>
         * 关于 statusCode 的配置都仍然有效(这点对我们之前项目中来说很重要, 因为有很多的 ajax 都禁用了全局的遮罩效果)
         */
        var setAjaxSetup = function () {
            $.ajaxSetup({
                statusCode: {
                    500: function () {
                        window.location.href = "/j2web/500";
                    },
                    404: function () {
                        window.location.href = "/j2web/404";
                    },
                    499: function () {
                        window.location.href = '/j2web/login?status=invalid';
                    }
                },
                beforeSend: function() {
                    NProgress.start();
                },
                complete: function(){
                    NProgress.done();
                }
            });
        };
        setAjaxSetup();
    });
</script>