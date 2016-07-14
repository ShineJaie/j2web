requirejs.config({
    // RequireJS 通过一个相对的路径 baseUrl 来加载所有代码.
    // baseUrl 通常被设置成 data-main 属性.
    baseUrl: '/j2web/static',
    urlArgs: "bust=" + (new Date()).getTime(), // 有利于开发模式, 但正式部署前一定要删除.
    waitSeconds: 20, // 设置加载脚本的超时为20秒, 默认7秒
    // 配置 css 依赖, 直接在 shim 的 deps 节点配置需要的 css 文件
    // 以 css! 开头, 路径为相对路径
    shim: {
        // 只能用 shim 配置非 AMD 脚本, 并且脚本没有 define().
        // 注意: jquery 已经注册为一个 AMD 异常模块, 所以这里配置对 jquery 无效.
        'bootstrap': {
            deps: [
                'jquery',
                'css!../static/dependency/bootstrap/css/bootstrap.min.css',
                'css!../static/dependency/font-awesome/css/font-awesome.min.css',
                'css!../static/mycss/globallayout.css'
            ]
            // exports 对于 bootstrap 不需要.
            // 因为 bootstrap 的所有 js 代码都已经作为 jquery 插件在 jquery 闭包中执行, 没有东西需要输出.
        },

        'pjax': {
            deps: [
                'jquery'
            ]
        },

        'sweetalert': {
            deps: ['css!../static/dependency/sweetalert/dist/sweetalert.css']
        },

        'nprogress': {
            deps: ['css!../static/dependency/nprogress/nprogress.css'],
            exports: 'NProgress'
        },

        'jstree': {
            deps: ['css!../static/dependency/jstree/dist/themes/default/style.min.css'],
            exports: 'jstree'
        },

        'datatables': {
            deps: ['css!../static/dependency/datatables/media/css/dataTables.bootstrap.min.css'],
            exports: 'datatables'
        },

        'icheck': {
            deps: ['css!../static/dependency/icheck/skins/flat/blue.css'],
            exports: 'iCheck'
        },

        'jquery.validate': {
            deps: ['jquery']
        },

        'summernote': {
            deps: ['css!../static/dependency/summernote/summernote.css'],
            exports: 'summernote'
        },
        'summernote-lang': {
            deps: ['summernote']
        }

    },
    map: {
        '*': {
            'css': 'dependency/requirecss/css' // 配置 require-css, 实现 css! 功能
        }
    },
    // 第三方脚本模块的别名, jquery 比 libs/jquery.min.js 简洁明了.
    paths: {
        // requirejs 判断 DOM 加载完毕插件
        'domReady': 'dependency/requirejs/domReady',

        'jquery': 'dependency/js/jquery.min',
        'jquery-ui': 'dependency/js/jquery-ui.min',

        'pjax': 'dependency/jquery-pjax/jquery.pjax',

        'bootstrap': 'dependency/bootstrap/js/bootstrap.min',

        // jquery.validate 表单验证插件配置
        'jquery.validate': 'dependency/jquery-validation/dist/jquery.validate.min',

        // jquery-file-upload 文件上传配置
        'jquery.ui.widget': 'dependency/jquery-file-upload/js/vendor/jquery.ui.widget', // The jQuery UI widget factory, can be omitted if jQuery UI is already included
        'jquery.iframe-transport': 'dependency/jquery-file-upload/js/jquery.iframe-transport', // he Iframe Transport is required for browsers without support for XHR file uploads
        'jquery.fileupload': 'dependency/jquery-file-upload/js/jquery.fileupload', // he basic File Upload plugin

        // sweetalert
        'sweetalert': 'dependency/sweetalert/dist/sweetalert.min',

        // nprogress
        'nprogress': 'dependency/nprogress/nprogress',

        // jstree
        'jstree': 'dependency/jstree/dist/jstree.min',

        // datatables
        'datatables.net': 'dependency/datatables/media/js/jquery.dataTables.min',
        'datatables': 'dependency/datatables/media/js/dataTables.bootstrap.min',

        // icheck
        'icheck': 'dependency/icheck/icheck.min',

        // summernote
        'summernote': 'dependency/summernote/summernote.min',
        'summernote-lang': 'dependency/summernote/lang/summernote-zh-CN',

        'router': 'myjs/router', // 前端动态加载 js, css 的路由规则

        'config': 'myjs/config', // 网站基础配置

        'init_icheck': 'myjs/utils/init_icheck', // icheck 初始化工具
        'validate_conf': 'myjs/utils/validate_conf', // jquery.validate 相关配置
        'listtable': 'myjs/utils/listtable', // 有关列表的的工具
        'popupdialog': 'myjs/utils/popupdialog', // 有关弹出框工具

        'sidebar': 'myjs/bar/sidebar', // 侧边栏
        'topbar': 'myjs/bar/topbar', // 顶部栏

        'home': 'myjs/app/home',
        'register': 'myjs/app/register'

    }
});

/**
 * 初始化页面布局
 */
define(['jquery', 'pjax', 'bootstrap', 'config', 'sidebar', 'topbar', 'router'], function ($, pjax, bootstrap, config, sidebar, topbar, router) {

});