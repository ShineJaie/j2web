define(['jquery', 'pjax', 'bootstrap', 'config', 'router'],
    function ($, pjax, bootstrap, config, router) {

        var initpage_home = function () {

            $('.form-control').val(new Date().toLocaleString());
        };

        return {
            initpage_home: initpage_home
        }

    }
);