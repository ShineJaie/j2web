define(['jquery', 'pjax', 'bootstrap', 'config', 'router', 'nprogress'], function ($, pjax, bootstrap, config, router, NProgress) {

    /**
     * 初始化侧边栏
     *
     */
    var initPart = function () {

        initSideAction();

        gotoPageAction();
    };

    /**
     * 添加页面跳转事件
     *
     */
    var gotoPageAction = function () {

        // Avoid bootstrap dropdown menu close on click inside
        perfectDropdown();

        $('#jsidebar-wrapper').off('click', '.jside-menu').on('click', '.jside-menu', function (e) {

            var $this = $(this);

            /*------------------begin 实现菜单按钮激活效果-----------------*/
            // 获取伪元素的属性值
            //var div=document.querySelector('li:nth-child(2)');
            //console.log($this.parent()[0], div);

            // 1.1, 高亮选中当前节点
            var cssAttr = window.getComputedStyle($this.parent()[0], '::before').getPropertyValue('background-color');
            $('.jsidebar-dropdown').css('background-color', '');
            $('.jside-menu').css('background-color', '');
            $this.css('background-color', cssAttr);
            // 1.2, 如果有上级节点应同时选中上级节点
            var parentsNum = $this.parents(".jsidebar-dropdown").length;
            if (parentsNum > 0) {
                var cssAttr_parent = window.getComputedStyle($this.parents(".jsidebar-dropdown")[parentsNum - 1], '::before').getPropertyValue('background-color');
                $this.parents(".jsidebar-dropdown").css('background-color', cssAttr_parent);
            }

            // 2, 关闭其它的多级菜单
            // 2.1, 关闭侧边栏其它多级菜单
            $('.jsidebar-dropdown').removeClass('open');
            // 2.2, 打开点击所在的菜单
            $this.parents('.jsidebar-dropdown').addClass('open');
            // 2.3, 关闭栏部栏选中效果
            $('.btn-jmenu-top').closest('li').removeClass('active');

            /*------------------end 实现菜单按钮激活效果--------------------*/

        });
    };

    /**
     * 解决下拉组件内部点击关闭的问题
     *
     */
    var perfectDropdown = function () {

        // Removing the data attribute data-toggle="dropdown"
        // and implementing the open/close of the dropdown can be a solution.

        // Handling the click on the link to open/close the dropdown like this :
        $('.jsidebar-dropdown a').off('click').on('click', function (e) {

            $(this).parent('.jsidebar-dropdown').toggleClass('open');
        });

    };

    /**
     * 侧边栏开关动作
     *
     */
    var initSideAction = function () {

        var $trigger = $('.hamburger');
        var $wrapper = $('#wrapper');
        var isClosed = false; // 侧边栏开关效果

        // 汉堡按钮动作
        $trigger.off('click').on('click', function () {

            if (isClosed == true) {  // 关闭
                $wrapper.removeClass('toggled');
                $trigger.removeClass('is-open');
                $trigger.addClass('is-closed');
                isClosed = false;
            } else { // 打开
                $wrapper.addClass('toggled');
                $trigger.removeClass('is-closed');
                $trigger.addClass('is-open');
                isClosed = true;
            }

        });
    };

    /*-----------------------------------------------------*/

    require(['domReady'], function (domReady) {
        domReady(function () {
            console.log('init sidebar');
            initPart();
        });
    });

});