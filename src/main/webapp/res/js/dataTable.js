/*
 * Created by Mr giraffe on 15/7/30.
 */
var oLanguage = {
    "sLengthMenu": "每页显示 _MENU_ 条记录",
    "sZeroRecords": "抱歉， 没有找到",
    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
    "sInfoEmpty": "没有数据",
    "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
    "sZeroRecords": "没有检索到数据",
    "sSearch": "模糊搜索:",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "前一页",
        "sNext": "后一页",
        "sLast": "尾页"
    }
};
var $oLanguage = oLanguage;
var $bDestroy = true;//重新绑定数据时清空原来的数据
var $processing = true;//加载数据时提示
var $serverSide = true;//
var $bFilter = false;//客户端过滤
var $bLengthChange = true;//显示每页数选择
var $bPaginate = true;//显示分页器
var $ajax = "";//json请求地址
var $aaSorting = [];
var $columns = [];
var $columnDefs = [];
var $obj = null;

var dataTableUtil = {
    option: function (option) {
        $obj = option.obj == undefined ? $obj : option.obj;
        $oLanguage = option.oLanguage == undefined ? $oLanguage : option.oLanguage;
        $bDestroy = option.bDestroy == undefined ? $bDestroy : option.bDestroy;//重新绑定数据时清空原来的数据
        $processing = option.processing == undefined ? $processing : option.processing;//加载数据时提示
        $serverSide = option.serverSide == undefined ? $serverSide : option.serverSide;//
        $bFilter = option.bFilter == undefined ? $bFilter : option.bFilter;//客户端过滤
        $bLengthChange = option.bLengthChange == undefined ? $bLengthChange : option.bLengthChange;//显示每页数选择
        $bPaginate = option.bPaginate == undefined ? $bPaginate : option.bPaginate;//显示分页器
        $ajax = option.ajax == undefined ? $ajax : option.ajax;//json请求地址
        $aaSorting = option.aaSorting == undefined ? $aaSorting : option.aaSorting;
        $columns = option.columns == undefined ? $columns : option.columns;
        $columnDefs = option.columnDefs == undefined ? $columnDefs : option.columnDefs;
    },

    dataTable: function () {
        $obj.dataTable({
            "oLanguage": $oLanguage,
            "bDestroy": $bDestroy,//重新绑定数据时清空原来的数据
            "processing": $processing,//加载数据时提示
            "serverSide": $serverSide,//
            "bFilter": $bFilter,//客户端过滤
            "bLengthChange": $bLengthChange,//显示每页数选择
            "bPaginate": $bPaginate,//显示分页器
            "ajax": $ajax,
            "aaSorting": $aaSorting,
            "columns": $columns,
            "columnDefs": $columnDefs
        });
    }
};