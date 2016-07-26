define(['jquery', 'pjax', 'bootstrap', 'config', 'sweetalert', 'validate_conf', 'init_icheck', 'domReady!'],
    function ($, pjax, bootstrap, config, sweetalert, validate_conf, init_icheck) {

        var initpage_register = function () {

            init_icheck.initICheck();

            validate_conf.setValidConfig();
            validate_conf.validateAction('#register_form', {
                email: {
                    required: true,
                    email: true
                },
                username: {
                    required: true,
                    username: true,
                    minlength: 2,
                    maxlength: 15
                },
                password: {
                    required: true,
                    digitAlphabet: true,
                    minlength: 6,
                    maxlength: 30
                },
                confirmPassword: {
                    required: true,
                    digitAlphabet: true,
                    minlength: 6,
                    maxlength: 30,
                    equalTo: "#password"
                },
                agree: "required"
            });

            submitAction();
        };

        /**
         * 表单验证
         */
        var submitAction = function () {

            $('.act_save').off('click').on('click', function () {

                swal({
                    title: "提交保存?",
                    text: "确认提交请点击确定按钮!",
                    type: "info",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定!",
                    cancelButtonText: "取消!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        if ($('#register_form').valid()) {
                            var params = {};

                            $('.my_edit_value').each(function () {
                                params[$(this).attr('name')] = $(this).val();
                            });

                            params.sex = $('input:radio:checked').val();

                            $('input:checkbox:checked').each(function () {
                                params[$(this).attr('name')] = $(this).val();
                            });

                            $.post('/j2web/register', function (result) {
                                if (result.status == 'success') {
                                    swal({
                                        title: "恭喜!",
                                        text: "注册成功, 5秒后将自动关闭该提示!",
                                        type: "success",
                                        timer: 5000,
                                        confirmButtonText: "确认:)"
                                    });

                                    $.pjax.reload('#page-content-wrapper');
                                } else {
                                    swal({
                                        title: "抱歉!",
                                        text: result.data,
                                        type: "error",
                                        confirmButtonText: "确认:("
                                    });
                                }
                            });
                        }
                    } else {
                        swal("取消", "数据未提交", "error");
                    }
                });

            });

        };

        return {
            initpage_register: initpage_register
        }

    });