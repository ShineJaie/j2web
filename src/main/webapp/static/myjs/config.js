define(['jquery', 'pjax', 'bootstrap', 'nprogress'], function ($, pjax, bootstrap, NProgress) {
    console.log('init config');

    /**
     * 加载模块脚本
     * @param {string} funcModel 引用的模块名
     * @param {string} funcInit 模块初始化函数名
     */
    var loadModel = function (funcModel, funcInit) {

        /*------------------------------------------------*/
        /* 1, funcModel 引用的模块                         */
        /* 2, funcInit  加载页面的函数                     */
        /*------------------------------------------------*/

        funcModel = trimStr(funcModel);
        funcInit = trimStr(funcInit);
        if (funcModel.length > 0 && funcInit.length > 0) {
            require([funcModel], function (model) {

                model[funcInit]();
            });
        }

    };

    /**
     * 去除字符串前后空隔
     * @param str 字符串
     * @returns {*|XML|void|string} string 若为 undefined 则返回空
     */
    var trimStr = function (str) {

        if (str) {
            return str.replace(/(^\s+)|(\s+$)/g, "");
        } else {
            return "";
        }

    };

    /**
     * 激活 pjax 请求时的进度条
     */
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
            beforeSend: function () {
                NProgress.start();
            },
            complete: function () {
                NProgress.done();
            }
        });
    };
    setAjaxSetup();

    /**
     *获取紧跟项目名后的模块名
     * @return {string} 项目/:appName
     */
    var getAppName = function () {
        var pathArr = window.location.pathname.split('/');
        return pathArr[2].toLowerCase();
    };

    return {
        loadModel: loadModel,
        strTrim: trimStr,
        getAppName: getAppName
    }

});