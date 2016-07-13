define(["jquery", "pjax", "config"], function router($, pjax, config) {

    var initRouter = function () {

        // 激活 pjax 请求
        $(document).pjax('a[data-pjax]', '#page-content-wrapper');

        loadAction();
    };

    var loadAction = function () {
        $(document).on("pjax:end", function () {

            // 加载脚本
            loadJs();

            // 设置栏目选中效果
            setSelected();
        });
    };

    var setSelected = function () {
        var page_select = $("#jcontent-wrapper").attr("menuSelct");

        /*---------------------顶部栏相关激活效果---begin----------------------*/
        // 1, 清除旧效果
        // 1,1 关闭下拉框
        $('.jtopbar-dropdown').removeClass('open');
        // 1,2 取消按钮的激活效果
        $('.jtop-menu').parents('li').removeClass('active');

        // 2, 若有选中顶部栏相关组件, 则激活相应效果
        // 2.1, 激活按钮
        $('[page_select="' + page_select + '"]').addClass('active');
        $('[page_select="' + page_select + '"]').parents('li').addClass('active');
        // 2.2, 打开下拉框组件
        $('[page_select="' + page_select + '"]').parents('.jtopbar-dropdown').addClass('open');
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

    var loadJs = function () {
        var funcModel = $("#jcontent-wrapper").attr("funcModel");
        var funcInit = $("#jcontent-wrapper").attr("funcInit");
        config.loadModel(funcModel, funcInit);
    };

    /*-----------------------------------------------------*/

    require(['domReady'], function (domReady) {
        domReady(function () {
            console.log('init router');
            initRouter();
        });
    });

});