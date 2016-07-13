define(['jquery', 'pjax', 'bootstrap', 'config', 'sweetalert', 'jquery.validate', 'icheck'],
    function ($, pjax, bootstrap, config, sweetalert, jquery_validate, iCheck) {

        /**
         * 加载注册页面, 并初始化相关事件
         */
        var initpage_register = function () {

            initICheck();
            initICheckValue();
            runICheck();

            validateAction();
        };

        var initICheck = function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_flat-blue',
                radioClass: 'iradio_flat-blue'
            });
        };

        var initICheckValue = function () {
            $('input:radio').each(function () {
                if (!$(this).attr("param_value")) {
                    $(this).attr("param_value", false);
                } else if ($(this).attr("param_value") == "false") {
                    $(this).iCheck('uncheck');
                } else {
                    $(this).iCheck('check');
                }
            });
        };

        var runICheck = function () {
            $('input:radio').off('ifChecked').on('ifChecked', function (event) {
                $(this).attr("param_value", true);
            });
            $('input:radio').off('ifUnchecked').on('ifUnchecked', function (event) {
                $(this).attr("param_value", false);
            });
        };


        /**
         * 表单验证
         */
        var validateAction = function () {

            var validateMsg = {
                required: "必选字段",
                remote: "请修正该字段",
                email: "请输入正确格式的电子邮件",
                url: "请输入合法的网址",
                date: "请输入合法的日期",
                dateISO: "请输入合法的日期 (ISO).",
                number: "请输入合法的数字",
                digits: "只能输入整数",
                creditcard: "请输入合法的信用卡号",
                equalTo: "请再次输入相同的值",
                accept: "请输入拥有合法后缀名的字符串",
                maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
                minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
                rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
                range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
                max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
                min: jQuery.validator.format("请输入一个最小为 {0} 的值")
            };

            jQuery.extend(jQuery.validator.messages, validateMsg);

            $('#registerForm').validate({
                rules: {
                    email: {
                        required: true,
                        email: true
                    },
                    username: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 6
                    },
                    confirmPassword: {
                        required: true,
                        minlength: 6,
                        equalTo: "#password"
                    },
                    agree: "required"
                },
                errorElement: "em",
                errorPlacement: function (error, element) {
                    // Add the `help-block` class to the error element
                    error.addClass("help-block");

                    // Add `has-feedback` class to the parent div.form-group
                    // in order to add icons to inputs
                    element.parents(".col-sm-5").addClass("has-feedback");

                    if (element.prop("type") === "checkbox") {
                        error.insertAfter(element.parent("label"));
                    } else {
                        error.insertAfter(element);
                    }

                    // Add the span element, if doesn't exists, and apply the icon classes to it.
                    if (!element.next("span")[0]) {
                        $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
                    }
                },
                success: function (label, element) {
                    // Add the span element, if doesn't exists, and apply the icon classes to it.
                    if (!$(element).next("span")[0]) {
                        $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
                    }
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
                    $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
                    $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
                }
            });

            $('.dbtn-confirm').off('click').on('click', function () {

                if ($('#registerForm').valid()) {
                    var regParam = {};

                    $('.myEditValue').each(function () {
                        regParam[$(this).attr('name')] = $(this).val();
                    });

                    if ($('.radioMale').attr('param_value') == 'true') {
                        regParam.sex = 1;
                    } else {
                        regParam.sex = 0;
                    }

                    /*$.post(url_register, regParam, function (rst) {

                     if (rst.status == 'success') {

                     swal({
                     title: "恭喜!",
                     text: "注册成功, 2秒后将自动关闭该提示!",
                     type: "success",
                     timer: 2000,
                     confirmButtonText: "确认:)"
                     });
                     } else {
                     swal({
                     title: "抱歉!",
                     text: rst.data,
                     type: "error",
                     confirmButtonText: "确认:("
                     });
                     }

                     });*/
                }
            });
        };

        return {
            initpage_register: initpage_register
        }

    });