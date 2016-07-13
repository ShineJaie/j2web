define(['jquery', 'bootstrap', 'config', 'datatables', 'dataTables.colReorder'],
    function ($, bootstrap, config, datatables, dataTables_colReorder) {

        /**
         * 调整 dataTables 向服务器端请求参数的格式
         * @param data DataTables 本身向服务器端发送的请求参数
         */
        var datatables2server = function (data) {
            for (var i = 0; i < data.columns.length; i++) {
                var column = data.columns[i];
                column.searchRegex = column.search.regex;
                column.searchValue = column.search.value;
                delete (column.search);
            }
        };

        /**
         * dataTables 插件的语言设置
         */
        var dataTablesConfig = {
            stateSave: true,
            language: {
                "emptyTable": "没有记录数据",
                "info": "显示 _START_ - _END_ 共 _TOTAL_ 条",
                "infoEmpty": "没有数据",
                "infoFiltered": "(共有 _MAX_ 条)",
                "infoPostFix": "",
                "thousands": ",",
                "lengthMenu": "每页 _MENU_ 条",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "search": "模糊查询:",
                "zeroRecords": "没有匹配的结果",
                "paginate": {
                    "first": "第一页",
                    "last": "最后一页",
                    "next": "下一页",
                    "previous": "上一页"
                },
                select: {
                    rows: {
                        _: " 已选中 %d 行",
                        0: "",
                        1: " 已选中 1 行"
                    }
                },
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                }
            }
        };

        /**
         * 画 dataTables 表格
         * @param container 要画表格的容器 (jquery 对象), 程序会插入一个panel
         * @param useroption {head,tableConfig,title,aftdrawfunc} 选项
         * @return datatables 实例对象, aftdrawfunc(datatables)
         */
        var drawTable_dataTables = function (container, useroption) {

            var option = $.extend(true, {}, {aftdrawfunc: null}, useroption);
            var theadname = [];
            for (var item in option.heads) {
                theadname.push("<th>" + option.heads[item] + "</th>");
            }

            var paneltitle = "";
            if (useroption.title) {
                paneltitle = "<div class='panel-heading'>" + useroption.title + "</div>";
            }

            var containerInner = $('<div class="panel panel-default">' +
                paneltitle +
                '<div class="panel-body">' +
                '<div class="table-responsive">' +
                '<table class="datalist table table-striped table-bordered nowrap" cellspacing="0" width="100%">' +
                '<thead><tr>' +
                theadname.join("") +
                '</tr></thead>' +
                '<tbody></tbody></table></div></div></div>');

            container.append(containerInner);

            var tableConfig = $.extend(true, {}, dataTablesConfig, option.tableConfig);

            var datatable = container.find(".datalist").DataTable(tableConfig).on('draw.dt', function () {

                if (typeof option.aftdrawfunc === "function") {

                    option.aftdrawfunc(datatable);
                }
            });

            return datatable;
        };

        /**
         * 在目标容器里面滑出子面板
         * @param container 目标容器 (jquery 对象)
         * @param title 面板标题
         * @param aftshowfunc aftshowfunc(containner) 回调函数
         * @return aftshowfunc($container)
         */
        var drawSubPanel = function (container, title, aftshowfunc) {

            var divout = $("<div class='subdlg-div-out' style='position: absolute; top: 0;left: 0;right: 0; height: 10px;'></div>");

            var back = $('<div class="subdlg-div" style="background-color: rgba(0,0,0,0.2);' +
                'position: fixed;top: 0;bottom: 0;left: 0;right: 0;z-index: 2000;"></div>');

            var containerInner = $('<div class="panel panel-default subdlg-div" ' +
                'style="position:fixed; left: 90%; right: -300px; top: 110px; bottom: 15px; ' +
                'margin-bottom: 0;z-index: 2000; ' +
                'box-shadow: 0px 0px 10px #888888; margin-bottom: 15px;">' +
                '<div class="panel-heading"></div>' +
                '<div class="panel-body" style="position: absolute;top: 47px;bottom: 0;left: 0;right: 0; ' +
                'padding: 15px; overflow-y: auto; "></div></div>');

            var closebtn = $('<div class="btn btn-xs btn-danger btn-back">返回</div>');
            containerInner.find(".panel-heading").append(closebtn);

            if (title) {
                containerInner.find(".panel-heading").append("<span style='margin-left: 30px;'>" + title + "</span>");
            }

            divout.append(back, containerInner);
            container.append(divout);

            containerInner.animate({left: (container.offset().left + 15) + "px", right: "18px"}, {
                duration: 200,
                complete: function () {
                    if (typeof aftshowfunc === "function") {
                        aftshowfunc(containerInner.find(".panel-body"));
                    }
                }
            });

            closebtn.click(function () {
                divout.remove();
            });
        };

        return {
            datatables2server: datatables2server,
            dataTablesConfig: dataTablesConfig,
            drawTable_dataTables: drawTable_dataTables,
            drawSubPanel: drawSubPanel
        }

    });