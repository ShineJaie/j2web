define(['jquery', 'pjax', 'bootstrap', 'config', 'router', 'sidebar'],
    function ($, pjax, bootstrap, config, router, sidebar) {

        /**
         * 初始化顶部栏
         *
         */
        var initPart = function () {

            perfectDropdown();
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

        /*-----------------------------------------------------*/

        require(['domReady'], function (domReady) {
            domReady(function () {
                console.log('init topbar');
                initPart();
            });
        });

    });