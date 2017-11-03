<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title></title>
    <#include "../layout.jsp" />
</head>
<body>
<section class="content-header">
    <div style="float: left;width: 45px;border-right: 1px solid #367fa9;margin-right: 10px;">
        <a class="btn btn-primary btn-sm" href="index"><i class="fa fa-arrow-left"></i></a>
    </div>
    <h1>
        返回
    </h1>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <form role="form">
                <div class="box box-primary">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="userId">账号：</label>
                            <input type="hidden" name="id" id="id" value="${model.getId()!''}">

                            <div class="row">
                                <div class="col-xs-7">
                                    <input type="text" name="userId" class="form-control" id="userId"
                                           required="required"
                                    <#if model.getId()??>readonly</#if>
                                    placeholder="请填写账号" value="${model.getUserId()!''}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <#if (model.getId())??>
                                <label for="pwd">密码：(需要修改密码则填写)</label>
                                <#else>
                                    <label for="pwd">密码：</label>
                            </#if>
                            <div class="row">
                                <div class="col-xs-7">
                                    <#if (model.getId())??>
                                        <input type="password" name="pwd" class="form-control" id="pwd"
                                               placeholder="请填写密码" value="">
                                        <#else>
                                            <input type="password" name="pwd" class="form-control" id="pwd"
                                                   required="required"
                                                   placeholder="请填写密码" value="${model.getPwd()!''}">
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>状态：</label>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>
                                            <input type="radio" name="status" value="0" class="flat-red"
                                            <#if model.getStatus()==0> checked</#if>
                                            />
                                            禁用 &nbsp;&nbsp;&nbsp;&nbsp;
                                        </label>
                                        <label>
                                            <input type="radio" name="status" value="1" class="flat-red"
                                            <#if model.getStatus()==1> checked</#if>
                                            />
                                            启用
                                        </label>
                                        <label>
                                            <input type="checkbox" name="mailNotice" value="true" style="margin-left: 15px" />发邮件通知请勾选
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="box-footer">
                        <a class="btn btn-primary" href="index">取消</a>
                        <button type="button" id="btn_submit" class="btn btn-primary">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>

</section>
<!-- /.content -->
<!-- /.right-side -->
<script src="/res/js/jQuery-2.1.4.min.js"></script>
<script src="/res/js/bootstrap.min.js" type="text/javascript"></script>

<script src="/res/js/other.js" type="text/javascript"></script>
<script src="/res/js/checkform.js"></script>

<script>
    $(function () {
        var flag = true;
        $("#userId").blur(function () {
            if ($(this).val() != "") {
                var id = $("#id").val();
                $.post("checkUsersId", {userId: $(this).val(), id: id}, function (res) {
                    if (!res) {
                        flag = false;
                        alert("用户名重复了!");
                    } else {
                        flag = true;
                    }
                });
            } else {
                flag = true;
            }
        });

        $("#btn_submit").click(function () {
            if (!checkform()) {
                return false;
            }
            if (!flag) {
                alert("用户名重复了！");
                return false;
            }

            $.post("add", $('form').serialize(), function (a, b) {

            }, 'json').complete(function (a, b) {
                if (a) {
                    alert("保存成功");
                    window.location.href = "/users/index";
                } else {
                    alert("保存失败");
                    return false;
                }
            }).error(function (a, b) {

            });
            return false;
        });
    });
</script>
</body>
</html>