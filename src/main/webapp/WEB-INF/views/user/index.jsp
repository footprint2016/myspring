<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title></title>
    <#include "../layout.jsp" />
    <link href="/res/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">
                        <div class="btn-group">
                            <a class="btn btn-info" href="/user/modify">添加用户</a>
                        </div>
                    </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <!-- /.search-->
                    <form>
                        <div class="row">
                            <div class="col-xs-2">
                                用户名：<input type="text" name="userId" class="form-control" placeholder="请输入用户名"
                                           value=""/>
                            </div>

                            <div class="col-xs-2" style="padding-top: 18px;">
                                <div class="btn-group">
                                    <button type="submit" class="btn btn-info" id="search">
                                        <i class="fa fa-search"></i>搜索
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- /.search-->
                    <div class="row" style="padding-top: 25px;">
                        <div class="col-xs-12">
                            <table id="example1" class="table table-bordered table-striped"></table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<script src="/res/js/jQuery-2.1.4.min.js"></script>
<script src="/res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/res/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/res/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    var t;
    function loadData(q) {
        t = $("#example1").dataTable({
            "oLanguage": oLanguage,
            "bDestroy": true,
            "processing": true,
            "serverSide": true,
            "bFilter": false,
            "ajax": "/user/get_users_json?" + q,
            "aaSorting": [[0, 'desc']],
            "columns": [
                {
                    "title": "ID",
                    "data": "id",
                    "class": "center",
                    "asSorting": ["desc", "asc"],
                    "width": "50"
                },
                {
                    "title": "用户名",
                    "data": "userId",
                    "class": "center",
                    "asSorting": ["asc", "desc"],
                    "width": "100"
                },
                {
                    "title": "角色",
                    "data": "rolesId",
                    "class": "center",
                    "asSorting": ["asc", "desc"],
                    "width": "100"
                },
                {"title": "状态", "data": "status", "asSorting": [], "width": "80"},
                {"title": "操作", "asSorting": [], "width": "150"}
            ],
            "columnDefs": [{
                "targets": -1,
                "data": null,
                "mRender": function (data, type, full) {
                    var s = "";
                    s += '<div class="btn-group">';
                    s += '<button type="button" data-id="' + data.id + '" class="btn btn-danger btn-sm del">删除</button>';
                    s += '<button type="button" data-id="' + data.id + '" class="btn btn-primary btn-sm modify">编辑</button>';
                    if (data.status == 0) {
                        s += '<button type="button" data-id="' + data.id + '" data-status="1" class="btn btn-success btn-sm changeStatus">启用</button>';
                    } else {
                        s += '<button type="button" data-id="' + data.id + '" data-status="0" class="btn btn-danger btn-sm changeStatus">禁用</button>';
                    }

                    s += '</div>';
                    return s;
                }
            }, {
                "targets": -2,
                "data": null,
                "mRender": function (data, type, full) {
                    var s = data == 0 ? '<span class="label label-danger">禁用</span>' : '<span class="label label-success">启用</span>';
                    return s;
                }
            }]
        });
    }
    $(function () {
        var q = "";
        loadData(q);

        $("form").submit(function (e) {
            e.preventDefault();
            q = "" + $('form').serialize();
            loadData(q);
        });

        $("#example1").on("click", ".modify", function () {
            var id = $(this)[0].dataset.id;
            window.location.href = "modify?id=" + id;
        });

        $("#example1").on("click", ".changeStatus", function () {
            var id = $(this)[0].dataset.id;
            var status = $(this)[0].dataset.status;
            if (confirm("确定修改该条数据状态？")) {
                $.post("changeStatus", {id: id, status: status}, function (a, b) {
                    alert(a);
                    if (a) {
                        alert("修改状态成功");
                        window.location.reload();
                    } else {
                        alert("修改状态失败");
                    }
                });
            }
        });

        $("#example1").on("click", ".del", function () {
            var id = $(this)[0].dataset.id;
            if (confirm("确定删除该条数据？")) {
                $.post("del", {id: id}, function (a, b) {
                    if (a) {
                        alert("删除成功");
                        window.location.reload();
                    } else {
                        alert("删除失败");
                    }
                });
            }
        });
    });
</script>
</body>
</html>