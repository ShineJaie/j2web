(function(global) {
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
        initSideAction();

        /**
         * 解决下拉组件内部点击关闭的问题
         *
         */
        var perfectDropdown = function () {

            //Removing the data attribute data-toggle="dropdown"
            // and implementing the open/close of the dropdown can be a solution.

            // 1, First by handling the click on the link to open/close the dropdown like this :
            $('.jsidebar-dropdown a').off('click').on('click', function (e) {

                $(this).parent('.jsidebar-dropdown').toggleClass('open');
            });

            // 2, Listening the clicks outside of the dropdown to close it like this :
            /*$('#sidebar-wrapper').off('click', '.btn-jmenu').on('click', '.btn-jmenu', function (e) {
             console.log('remove active');
             $('li.dropdown.jsidebar-dropdown').removeClass('open');
             });*/

            /*$("#sidebar-wrapper .btn-jmenu").not(".jsidebar-dropdown .btn-jmenu").off('click').on('click', function () {
             $('li.dropdown.jsidebar-dropdown').removeClass('open');
             });*/

        };
        perfectDropdown();

        $(document).pjax('a[data-pjax]', '#page-content-wrapper')

    });
})(this);