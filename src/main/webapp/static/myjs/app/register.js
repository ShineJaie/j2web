define(['jquery', 'pjax', 'bootstrap', 'config', 'sweetalert', 'validate_conf', 'init_icheck', 'domReady!'],
    function ($, pjax, bootstrap, config, sweetalert, validate_conf, init_icheck) {

        var initpage_register = function () {

            init_icheck.initICheck();
            init_icheck.initICheckRadioValue('input:radio');
            init_icheck.runICheckRadio();

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

                if ($('#register_form').valid()) {
                    var params = {};

                    $('.my_edit_value').each(function () {
                        params[$(this).attr('name')] = $(this).val();
                    });

                    if ($('.radio_male').attr('param_value') == 'true') {
                        params.sex = 1;
                    } else {
                        params.sex = 0;
                    }

                    $.post("/j2web/register/save", params, function (res) {

                        if (res.status == 'success') {

                            swal({
                                title: "恭喜!",
                                text: "注册成功, 2秒后将自动关闭该提示!",
                                type: "success",
                                timer: 2000,
                                confirmButtonText: "确认:)"
                            });

                            $.pjax.reload('#page-content-wrapper');
                        } else {
                            swal({
                                title: "抱歉!",
                                text: res.data,
                                type: "error",
                                confirmButtonText: "确认:("
                            });
                        }

                    });


                    /*// 测试 pjax 动态加载其它页面
                    $.pjax({
                        type: 'POST',
                        url: "/j2web/home",
                        container: '#page-content-wrapper',
                        data: params,
                        dataType: 'html'
                    })*/
                }
            });

        };

        return {
            initpage_register: initpage_register
        }

    });