define(['jquery', 'pjax', 'bootstrap', 'nprogress'], function ($, pjax, bootstrap, NProgress) {
    console.log('init config');

    /**
     * 加载模块脚本
     * @param {string} funcModel 引用的模块名
     * @param {string} funcInit 模块初始化函数名
     * @param {string} target 激活目标 ()
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
     * 根据加载的页面激活相应顶部栏、侧边栏的效果
     *
     * @param target (bar-btntarget 的属性值)
     */
    var setBarToActive = function (target) {

        /*---------------------顶部栏相关激活效果---begin----------------------*/
        // 1, 清除旧效果
        // 1,1 关闭下拉框
        $('.jtopbar-dropdown').removeClass('open');
        // 1,2 取消按钮的激活效果
        $('.jtop-menu').parents('li').removeClass('active');

        // 获取顶部栏相关 Dom 对象
        var topbarTarget = '.jtop-menu.' + target;

        // 匹配是否为激活目标 +_+
        var pattern = /\w+_\w+/g;
        var isTarget = pattern.test(target);

        if (isTarget && target && $(topbarTarget).length > 0) {
            // 2, 若有选中顶部栏相关组件, 则激活相应效果
            // 2.1, 激活按钮
            $(topbarTarget).parents('li').addClass('active');
            // 2.2, 打开下拉框组件
            $(topbarTarget).parents('.jtopbar-dropdown').addClass('open');
        }
        /*---------------------顶部栏相关激活效果---end----------------------*/

        /*---------------------侧边栏相关激活效果---begin-----------------------*/
        // 1, 清除旧效果
        // 1.1, 关闭下拉框组件
        $('.jsidebar-dropdown').removeClass('open');
        // 1.2, 取消按钮的激活效果
        $('.btn-jmenu').css('background-color', '');


        // 取得侧边栏相关 Dom 对象
        var sidebarTarget = '.btn-jmenu.' + target;

        if (isTarget && target && $(sidebarTarget).length > 0) {
            // 2, 若有选中侧边栏相关组件, 则激活相应效果
            // 2.1, 激活按钮, 效果来自伪元素
            var cssAttr = window.getComputedStyle($(sidebarTarget).parent()[0], '::before').getPropertyValue('background-color');
            $(sidebarTarget).css('background-color', cssAttr);
            // 2.2, 打开下拉框组件
            $(sidebarTarget).parents('.jsidebar-dropdown').addClass('open');

            // 2.3, 打开侧边栏
            if (!$('.hamburger').hasClass('is-open')) {
                $('.hamburger').trigger('click');
            }
        } else {
            // 1.3, 关闭侧边栏
            if (!$('.hamburger').hasClass('is-closed')) {
                $('.hamburger').trigger('click');
            }
        }

        /*---------------------侧边栏相关激活效果----end----------------------*/
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