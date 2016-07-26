define(['jquery', 'icheck'], function ($, iCheck) {

    // 提示: 对于 checkbox 获取选中的值为 $('input:checkbox:checked').val()
    // 对于 radio 获取选中的值为 $('input:radio:checked').val()

    var initICheck = function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });
    };

    return {
        initICheck: initICheck
    }
});