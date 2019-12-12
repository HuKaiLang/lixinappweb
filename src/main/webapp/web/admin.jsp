<%--
  Created by IntelliJ IDEA.
  User: 11864
  Date: 2019/12/11
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>userpage</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Advanced-NavBar---Multi-dropdown.css">
    <link rel="stylesheet" href="assets/css/dh-card-image-left-dark.css">
    <link rel="stylesheet" href="assets/css/Highlight-Clean.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="assets/css/Sidebar-Menu-1.css">
    <link rel="stylesheet" href="assets/css/Sidebar-Menu.css">
    <link rel="stylesheet" href="assets/css/smoothproducts.css">
    <link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./themes/icon.css">
    <script type="text/javascript" src="jquery.min.js"></script>
    <script type="text/javascript" src="jquery.easyui.min.js"></script>
    <script type="text/css" src="webapp/admin.css"></script>
    <script type="text/javascript">
        // 添加验证规则
        $.extend($.fn.validatebox.defaults.rules, {
            integer: {// 验证整数
                validator: function (value) {
                    return /^[+]?[1-9]+\d*$/i.test(value);
                },
                message: '请输入整数'
            },
            date: {
                validator: function (value) {
                    var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
                    if (r == null) {
                        return false;
                    }
                    var d = new Date(r[1], r[3] - 1, r[4]);
                    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
                },
                message: '时间格式不正确，请重新输入。(示例为 "1970-1-1" )'
            },
        });

        function loadMain(){
            console.log("loadMain function called");
            $.ajax({
                url:'apply.html',
                type:'get',
                success:function (res) {
                    let target = $("#main-content").html(res);
                    $.parser.parse(target); // EasyUI控件在由ajax方法获取之后需要重新渲染
                }
            })
        }

        function addItem() {
            console.log("addItem function called");
            $('#dlg').dialog('open').dialog('setTitle', 'New Apply');
            $('#dialog').form('clear');
        }

        function editItem() {
            console.log("editItem function called");
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', 'Edit Apply');
                $('#fm').form('load', row);
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: "请先选择一条数据"
                })
            }
        }

        function deleteItem() {
            console.log("deleteItem function called");
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm("Confirm", "Are you sure to delete this Apply?", function (r) {
                    if (r) {
                        $.post('weixin/applies', {
                            method: "delete",
                            apply_id: row.apply_id
                        }, function (result) {
                            if (result.code = 200) {
                                $('#dlg').dialog('close');
                                $('#dg').datagrid('reload');
                            } else {
                                $.messager.show({
                                    title: 'Error',
                                    msg: result.errorMsg
                                })
                            }
                        }, 'json');
                    }
                })
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: "请先选择一条数据"
                })
            }

        }

        function passItem(){
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.ajax({
                    url: "weixin/applies",
                    type: 'get',
                    data: {
                        method: 'update',
                        apply_id: row.apply_id,
                        apply_type: row.apply_type,
                        apply_name: row.apply_name,
                        user_school_id: row.user_school_id,
                        apply_reason: row.apply_reason,
                        apply_grade: row.apply_grade,
                        apply_department: row.apply_department,
                        apply_state: "pass",
                        apply_create_date: row.apply_create_date
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == 200) {
                            $('#dlg').dialog('close');
                            $('#dg').datagrid('reload');
                            $.messager.show({
                                title: "成功",
                                msg: "已通过该条目"
                            })
                        } else {
                            $.messager.show({
                                title: 'Error',
                                msg: result.errorMsg
                            })
                        }
                    }
                })
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: "请先选择一条数据"
                })
            }
        }

        function rejectItem(){
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.ajax({
                    url: "weixin/applies",
                    type: 'get',
                    data: {
                        method: 'update',
                        apply_id: row.apply_id,
                        apply_type: row.apply_type,
                        apply_name: row.apply_name,
                        user_school_id: row.user_school_id,
                        apply_reason: row.apply_reason,
                        apply_grade: row.apply_grade,
                        apply_department: row.apply_department,
                        apply_state: "reject",
                        apply_create_date: row.apply_create_date
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == 200) {
                            $('#dlg').dialog('close');
                            $('#dg').datagrid('reload');
                            $.messager.show({
                                title: "成功",
                                msg: "已拒绝该条目"
                            })
                        } else {
                            $.messager.show({
                                title: 'Error',
                                msg: result.errorMsg
                            })
                        }
                    }
                })
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: "请先选择一条数据"
                })
            }
        }

        function saveItem() {
            var apply_id = $("[name='apply_id']").val();
            var apply_type = $("[name='apply_type']").val();
            var apply_name = $("[name='apply_name']").val();
            var user_school_id = $("[name='user_school_id']").val();
            var apply_reason = $("[name='apply_reason']").val();
            var apply_grade = $("[name='apply_grade']").val();
            var apply_department = $("[name='apply_department']").val();
            var apply_state = $("[name='apply_state']").val();
            var apply_create_date = $("[name='apply_create_date']").val();

            var title = $('#dlg').panel('options').title;

            if (title == 'New Apply') {
                $.ajax({
                    url: "weixin/applies",
                    type: "get",
                    data: {
                        method: "add",
                        apply_id: apply_id,
                        apply_type: apply_type,
                        apply_name: apply_name,
                        user_school_id: user_school_id,
                        apply_reason: apply_reason,
                        apply_grade: apply_grade,
                        apply_department: apply_department,
                        apply_state: apply_state,
                        apply_create_date: apply_create_date
                    },
                    dataType: "json",
                    success: function (result) {
                        console.log("result = ", result);
                        if (result.code = 200) {
                            $('#dialog').dialog('close');
                            $('#dg').datagrid('reload');
                            $.messager.show({
                                title: "成功",
                                msg: "已添加新数据"
                            })
                        } else {
                            $.messager.show({
                                title: 'Error',
                                msg: result.errorMsg
                            })
                        }
                    }
                })
            } else {
                $.ajax({
                    url: "weixin/applies",
                    type: 'get',
                    data: {
                        method: 'update',
                        apply_id: apply_id,
                        apply_type: apply_type,
                        apply_name: apply_name,
                        user_school_id: user_school_id,
                        apply_reason: apply_reason,
                        apply_grade: apply_grade,
                        apply_department: apply_department,
                        apply_state: apply_state,
                        apply_create_date: apply_create_date
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == 200) {
                            $('#dlg').dialog('close');
                            $('#dg').datagrid('reload');
                            $.messager.show({
                                title: "成功",
                                msg: "已更新数据"
                            })
                        } else {
                            $.messager.show({
                                title: 'Error',
                                msg: result.errorMsg
                            })
                        }
                    }
                })
            }
        }
    </script>
</head>
<%-- 检查用户身份 --%>
<jsp:include page="checkLogin.jsp"></jsp:include>
<body onload="loadMain()">
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand"><a href="#">主界面</a></li>
            <li><a href="#">学生事务审核</a></li>
            <li><a href="#">奖学金申请审核</a></li>
            <li><a href="#">登出</a></li>
        </ul>
    </div>
    <div class="page-content-wrapper">
        <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                class="fa fa-bars"></i></a>
            <div class="row">
                <div class="col-md-12 col-lg-12">
                    <div>
                        <h1 style="text-align: center">管理菜单</h1>
                    </div>
                    <div id="main-content">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/js/bs-init.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
<script src="assets/js/smoothproducts.min.js"></script>
<script src="assets/js/theme.js"></script>
<script src="assets/js/Advanced-NavBar---Multi-dropdown.js"></script>
<script src="assets/js/Sidebar-Menu.js"></script>
</body>

</html>
