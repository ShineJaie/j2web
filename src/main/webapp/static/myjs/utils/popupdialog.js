define(['jquery', 'bootstrap', 'jquery-ui'], function ($, bootstrap, jquery_ui) {

    /**
     * 修复多级模态框的显示和滚动条问题
     */
    var fixMultiModal = function () {

        // 多级弹框 Backdrop z-index fix
        $(document).off('show.bs.modal', '.modal').on('show.bs.modal', '.modal', function () {

            var zIndex = 1040 + (10 * $('.modal:visible').length); // z-index 硬编码
            $(this).css('z-index', zIndex);

            setTimeout(function () {
                $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
            }, 0);
        });

        // 多级弹框 Scrollbar fix
        $(document).off('hidden.bs.modal', '.modal').on('hidden.bs.modal', '.modal', function () {

            $('.modal:visible').length && $(document.body).addClass('modal-open');
        });
    };

    /**
     * 可拖拽模态框
     * @param $target jquery 对象
     * @param handle css 选择器
     */
    var modalDraggable = function ($target, handle) {

        $target.draggable({
            handle: handle,
            cancel: '#page-content-wrapper',
            cursor: "move",
            opacity: 0.35,
            scroll: false
        });
    };

    /**
     * bootstrap 多级弹框
     * @param option[level, title, width]
     * @param callback($model)
     */
    var drawPopupModal = function (option, callback) {

        var finalOption = $.extend(true, {
            'level': 1,
            'title': '标题',
            'width': '90%'
        }, option);

        var modalText = '<div class="modal fade my_bs_modal">' +
            '<div class="modal-dialog" style="width: 90%;">' +
            '<div class="modal-content"><div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>' +
            '<h4 class="modal-title">' + finalOption.title + '</h4></div>' +
            '<div class="modal-body" style="overflow-y: auto;">' +
            '<div class="my_bs_modal_content"><i class="fa fa-spinner fa-pulse"></i>&nbsp; 加载中...' +
            '</div></div>' + '<div class="modal-footer">' +
            '<button data-dismiss="modal" class="btn btn-danger">关闭</button>' +
            '</div></div></div></div>';

        $('#my_bs_modal_' + finalOption.level).remove();

        var $modal = $(modalText);
        $modal.attr('id', 'my_bs_modal_' + finalOption.level);

        $('body').prepend($modal);

        fixMultiModal();

        // 顶部, 底部都可以拖拽
        modalDraggable($modal, '.modal-header,.modal-footer');

        $modal.off('show.bs.modal').on('show.bs.modal', function () {

            if (finalOption.width) {
                $(this).find('.modal-dialog').css({'width': finalOption.width});
            }

            var height = $(window).height() - 200;
            $(this).find(".modal-body").css("max-height", height);
        });

        $modal.off('hidden.bs.modal').on('hidden.bs.modal', function () {
            $modal.remove();
        });

        $modal.modal({
            backdrop: 'static',
            keybard: false,
            show: true
        });

        if (typeof callback === 'function') {

            callback($modal);
        }

    };

    return {
        fixMultiModal: fixMultiModal,
        drawPopupModal: drawPopupModal
    }

});