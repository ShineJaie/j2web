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
     * 获取紧跟项目名后的模块名
     * @return {string} 项目/:appName
     */
    var getAppName = function () {
        var pathArr = window.location.pathname.split('/');
        return pathArr[2].toLowerCase();
    };

    /**
     * 封装 $.put and $.delete 到全局 jquery
     */
    var initPutAndDeleteRequest = function () {
        $.each(["put", "delete"], function (i, method) {
            /**
             * 封闭 $.put and $.delete
             * @param url 一个包含发送请求的 URL 字符串。
             * @param data 一个普通对象或字符串，通过请求发送给服务器。
             * @param success 当请求成功后执行的回调函数。<br/>
             * 如果提供 dataType 选项，那么这个 success 选项是必须的，<br/>
             * 但这种情况下你可以使用 null。<br/>
             * @param dataType 从服务器返回的预期的数据类型。<br/>
             * 默认：智能猜测（xml, json, script, text，html）。
             */
            $[method] = function (url, data, success, dataType) {
                if ($.isFunction(data)) {
                    dataType = dataType || success;
                    success = data;
                    data = undefined;
                }

                return $.ajax({
                    url: url,
                    type: method,
                    dataType: dataType,
                    data: data,
                    success: success
                });
            };
        });
    };
    initPutAndDeleteRequest();

    /**
     * 封闭 pjax 动态加载页面 $.pjax_load 到全局 jquery
     */
    var initPjaxLoadHtml = function () {
        $.each(["pjax_load"], function (i, method) {
            /**
             * 封闭 $.pjax_load
             * @param url 一个包含发送请求的 URL 字符串。
             * @param data 一个普通对象或字符串，通过请求发送给服务器。
             * @param success 当请求成功后执行的回调函数。
             */
            $[method] = function (url, data, success) {

                var selectors = url.split(" ");
                var container = "";
                for (var j = 1; j < selectors.length; ++j) {
                    container += (selectors[j] + " ");
                }
                if (container.length <= 0) {
                    container = "#page-content-wrapper";
                }
                container = trimStr(container);

                url = url.split(" ")[0];

                if ($.isFunction(data)) {
                    success = data;
                    data = undefined;
                }

                return $.pjax({
                    url: url,
                    type: 'GET',
                    container: container,
                    dataType: 'html',
                    data: data,
                    success: success
                });
            };
        });
    };
    initPjaxLoadHtml();

    return {
        loadModel: loadModel,
        strTrim: trimStr,
        getAppName: getAppName
    }

});