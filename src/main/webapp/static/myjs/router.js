define(['jquery', 'pjax', 'config', 'sidebar', 'topbar'], function ($, pjax, config, sidebar, sidebar) {

    var initRouter = function () {

        // 激活 pjax 请求
        $(document).pjax('a[data-pjax]', '#page-content-wrapper');

        setSelected();

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
        var page_select = $("#jcontent-wrapper").attr("menuSelect");

        /*---------------------顶部栏相关激活效果---begin----------------------*/
        // 1, 清除旧效果
        // 1,1 关闭下拉框
        $('.jtopbar-dropdown').removeClass('open');
        // 1,2 取消按钮的激活效果
        $('.jtop-menu').parents('li').removeClass('active');
        $('.jtop-menu').removeClass('active');

        // 获取顶部栏相关 Dom 对象
        var topbarTarget = '.jtop-menu[page_select="' + page_select + '"]';

        if ($(topbarTarget).length > 0) {
            // 2, 若有选中顶部栏相关组件, 则激活相应效果
            // 2.1, 激活按钮
            $(topbarTarget).parents('li').addClass('active');
            $(topbarTarget).addClass('active');
            // 2.2, 打开下拉框组件
            $(topbarTarget).parents('.jtopbar-dropdown').addClass('open');
        }
        /*---------------------顶部栏相关激活效果---end----------------------*/

        /*---------------------侧边栏相关激活效果---begin-----------------------*/
        // 1, 清除旧效果
        // 1.1, 关闭下拉框组件
        $('.jsidebar-dropdown').removeClass('open');
        // 1.2, 取消按钮的激活效果
        $('.jside-menu').css('background-color', '');
        $('.jsidebar-dropdown').css('background-color', '');


        // 取得侧边栏相关 Dom 对象
        var sidebarTarget = '.jside-menu[page_select="' + page_select + '"]';

        if ($(sidebarTarget).length > 0) {
            // 2, 若有选中侧边栏相关组件, 则激活相应效果
            // 2.1, 激活按钮, 效果来自伪元素
            var cssAttr = window.getComputedStyle($(sidebarTarget).parent()[0], '::before').getPropertyValue('background-color');
            $(sidebarTarget).css('background-color', cssAttr);
            // 2.1.2, 如果有上级节点应同时选中上级节点
            var parentsNum = $(sidebarTarget).parents(".jsidebar-dropdown").length;
            if (parentsNum > 0) {
                var cssAttr_parent = window.getComputedStyle($(sidebarTarget).parents(".jsidebar-dropdown")[parentsNum - 1], '::before').getPropertyValue('background-color');
                $(sidebarTarget).parents(".jsidebar-dropdown").css('background-color', cssAttr_parent);
            }
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