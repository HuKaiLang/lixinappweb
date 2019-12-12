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
<body>
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand"><a href="#">主界面</a></li>
            <li><a href="#">审核</a></li>
            <li></li>
            <li><a href="#">登出</a></li>
        </ul>
    </div>
    <div class="page-content-wrapper">
        <div class="container-fluid"><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i
                class="fa fa-bars"></i></a>
            <div class="row">
                <div class="col-md-12 col-lg-12">
                    <div>
                        <h1>管理菜单</h1>
                    </div>
                    <table id="dg" title="学生事务申请表" class="easyui-datagrid" style="width: 100%;height: 100%"
                           url="weixin/applies?method=query" method="get" toolbar="#toolbar"
                           rownumbers="true"
                           fitColumns="true" singleSelect="true">
                        <thead>
                        <tr>
                            <th field="apply_id" width="50" hidden="true">申请ID</th>
                            <th field="apply_type" width="50">申请类型</th>
                            <th field="apply_name" width="50">申请人姓名</th>
                            <th field="user_school_id" width="50">学号</th>
                            <th field="apply_reason" width="50">申请理由</th>
                            <th field="apply_grade" width="50">申请人年级</th>
                            <th field="apply_department" width="50">申请人院系</th>
                            <th field="apply_state" width="50">申请项状态</th>
                            <th field="apply_create_date" width="50">申请创建日期</th>
                        </tr>
                        </thead>
                    </table>
                    <div id="toolbar">
                        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addItem()">添加</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                           onclick="editItem()">修改</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true"
                           onclick="passItem()">通过</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true"
                           onclick="rejectItem()">拒绝</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                           onclick="deleteItem()">删除</a>
                    </div>
                    <div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
                         closed="true"
                         buttons="#dlg-buttons">
                        <div style="text-align: center" class="ftitle">申请信息</div>
                        <form id="fm" method="post" class="dialog-fm">
                            <div class="fitem" hidden="true">
                                <label>Apply ID</label>
                                <input name="apply_id" class="easyui-validatebox" hidden="true">
                            </div>
                            <div class="fitem fm-item">
                                <label style="flex: 1">申请类型</label>
                                <select name="apply_type">
                                    <option value="教务处版在读证明">教务处版在读证明</option>
                                    <option value="校章版在读证明">校章版在读证明</option>
                                    <option value="户籍证明">户籍证明</option>
                                    <option value="打印成绩单">打印成绩单</option>
                                    <option value="学生证充磁">学生证充磁</option>
                                    <option value="保险理赔">保险理赔</option>
                                    <option value="创新实践学分申请">创新实践学分申请</option>
                                    <option value="摆摊申请">摆摊申请</option>
                                    <option value="横幅申请">横幅申请</option>
                                    <option value="海报申请">海报申请</option>
                                </select>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请人姓名</label>
                                <input name="apply_name" required="true"/>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请人学号</label>
                                <input name="user_school_id" class="easyui-validatebox"
                                       data-options="validType:['integer','length[5,20]']" required="true"/>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请理由</label>
                                <textarea name="apply_reason" required="true"></textarea>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请人年级</label>
                                <select name="apply_grade">
                                    <option value="1">大一</option>
                                    <option value="2">大二</option>
                                    <option value="3">大三</option>
                                    <option value="4">大四</option>
                                </select>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请人院系</label>
                                <input name="apply_department"/>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请项状态</label>
                                <input name="apply_state"/>
                            </div>
                            <div class="fitem fm-item">
                                <label>申请项创建时间</label>
                                <input name="apply_create_date" class="easyui-validatebox"
                                       data-options="validType:['date']" required="true"/>
                            </div>
                        </form>
                    </div>
                    <div id="dlg-buttons">
                        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveItem()">保存</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                           onclick="javascript:$('#dlg').dialog('close')">取消</a>
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
