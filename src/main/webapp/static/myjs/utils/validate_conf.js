define(['jquery', 'jquery.validate'], function ($, jquery_validate) {

    // 设置表单验证控件全局配置
    var setValidConfig = function () {

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

        jQuery.validator.addMethod("username", function (value, element) {
            // 只允许数字, 字母或下划线, 并且必须以字母或下划线开头,
            return this.optional(element) || /^\w{2,15}$/.test(value);
        }, '只允许数字或字母或下划线 长度大于2位不超过15位');

        jQuery.validator.addMethod("digitAlphabet", function (value, element) {
            // 至少包含一个数字和字母
            return this.optional(element) || /([A-Za-z].*[0-9])|([0-9].*[A-Za-z])/.test(value);
        }, '至少包含一个数字和字母');

        jQuery.validator.addMethod("postcode", function (value, element) {
            // 邮编 6位数字 第一位不是0
            return this.optional(element) || /^[1-9]\d{5}$/.test(value);
        }, '邮编 6位数字 第一位不是0');

        jQuery.validator.addMethod("telephone", function (value, element) {
            // 电话号码 8-9位 带区号
            return this.optional(element) || /^\d{3,4}-\d{8,9}$/.test(value);
        }, '电话号码 8-9位 带区号');

        jQuery.validator.addMethod("mobilephone", function (value, element) {
            // 手机号 11位 第一位不是0
            return this.optional(element) || /^[1-9]\d{10}$/.test(value);
        }, '手机号 11位 第一位不是0');
    };

    var validateAction = function (selectorStr, rules) {
        $(selectorStr).validate({
            rules: rules,
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
    };

    return {
        setValidConfig: setValidConfig,
        validateAction: validateAction
    }
});