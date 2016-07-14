define(['jquery', 'icheck'], function ($, iCheck) {

    // 提示: 对于 checkbox 获取选中的值为 $('input:checkbox:checked').attr('name')

    var initICheck = function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });
    };

    var runICheckRadio = function () {
        $('input:radio').off('ifChecked').on('ifChecked', function (event) {
            $(this).attr("param_value", true);
        });
        $('input:radio').off('ifUnchecked').on('ifUnchecked', function (event) {
            $(this).attr("param_value", false);
        });
    };

    var initICheckRadioValue = function (selectorStr) {
        $(selectorStr).each(function () {
            if (!$(this).attr("param_value")) {
                $(this).attr("param_value", false);
            } else if ($(this).attr("param_value") == "false") {
                $(this).iCheck('uncheck');
            } else {
                $(this).iCheck('check');
            }
        });
    };


    return {
        initICheck: initICheck,
        runICheckRadio: runICheckRadio,
        initICheckRadioValue: initICheckRadioValue
    }
});