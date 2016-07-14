define(['jquery', 'pjax', 'bootstrap', 'config', 'domReady!'],
    function ($, pjax, bootstrap, config) {

        var initpage_home = function () {

            $('.form-control').val(new Date().toLocaleString());
        };

        return {
            initpage_home: initpage_home
        }

    }
);