define(['jquery', 'pjax', 'bootstrap', 'config', 'sidebar', 'domReady!'],
    function ($, pjax, bootstrap, config, sidebar) {

        var gotoPageAction = function () {

            $('#jtopbar-wrapper').off('click', '.jtop-menu').on('click', '.jtop-menu', function (e) {

                var $this = $(this);

                /*---------------------顶部栏相关激活效果---begin----------------------*/
                // 1, 清除旧效果
                // 1,1 关闭下拉框并取消选中
                $('.jtopbar-dropdown').removeClass('open');
                $('.jtopbar-dropdown').removeClass('active');
                $this.removeClass('active');
                // 1,2 取消按钮的激活效果
                $('.jtop-menu').parents('li').removeClass('active');
                $('.jtop-menu').removeClass('active');

                // 2, 若有选中顶部栏相关组件, 则激活相应效果
                // 2.1, 激活按钮
                $this.parents('li').addClass('active');
                $this.addClass('active');
                // 2.2, 打开下拉框组件
                $this.parents('.jtopbar-dropdown').addClass('open');

                // 3, 取消侧边栏的选中效果
                $('.jsidebar-dropdown').css('background-color', '');
                $('.jside-menu').css('background-color', '');
                $('.jsidebar-dropdown').removeClass('open');
                // 3.2, 关闭侧边栏
                if (!$('.hamburger').hasClass('is-closed')) {
                    $('.hamburger').trigger('click');
                }
                /*---------------------顶部栏相关激活效果---end----------------------*/
            });
        };

        /**
         * 解决下拉组件内部点击关闭的问题
         *
         */
        var perfectDropdown = function () {
            //Removing the data attribute data-toggle="dropdown"
            // and implementing the open/close of the dropdown can be a solution.

            // 1, First by handling the click on the link to open/close the dropdown like this :
            $('.jtopbar-dropdown a').off('click').on('click', function (e) {

                $(this).parent('.jtopbar-dropdown').toggleClass('open');
            });

            // 2, Listening the clicks outside of the dropdown to close it like this :
            $('body').off('click').on('click', function (e) {

                if (!$('li.jtopbar-dropdown').is(e.target) &&
                    $('li.jtopbar-dropdown').has(e.target).length === 0 &&
                    $('.open').has(e.target).length === 0
                ) {
                    $('li.jtopbar-dropdown').removeClass('open');
                }
            });

        };

        (function () {
            console.log('init topbar');

            perfectDropdown();

            gotoPageAction();
        })();

    });