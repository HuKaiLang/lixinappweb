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
    <script type="text/javascript">
        function addItem() {
            console.log("addItem function called");
            $('#dlg').dialog('open').dialog('setTitle', 'New Notice');
            $('#dialog').form('clear');
        }

        function editNotice() {
            console.log("editItem function called");
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', 'Edit Notice');
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
                $.messager.confirm("Confirm", "Are you sure to delete this notice", function (r) {
                    if (r) {
                        $.post('weixin/notices', {
                            method: "delete",
                            notice_id: row.notice_id
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

        function saveItem() {
            var notice_id = $("[name='notice_id']").val();
            var notice_title = $("[name='notice_title']").val();
            var notice_text_content = $("[name='notice_text_content']").val();
            var notice_type = $("[name='notice_type']").val();
            var notice_date = $("[name='notice_date']").val();
            var notice_state = $("[name='notice_state']").val();
            var notice_img_path = $("[name='notice_img_path']").val();
            var title = $('#dlg').panel('options').title
            if (notice_img_path == "") {
                console.log("notice img path equals to undefined");
                notice_img_path = undefined;
            }
            if (title == 'New Notice') {
                $.ajax({
                    url: "weixin/notices",
                    type: "get",
                    data: {
                        method: "add",
                        notice_id: notice_id,
                        notice_title: notice_title,
                        notice_text_content: notice_text_content,
                        notice_type: notice_type,
                        notice_date: notice_date,
                        notice_state: notice_state,
                        notice_img_path: notice_img_path
                    },
                    dataType: "json",
                    success: function (result) {
                        console.log("result = ", result);
                        if (result.code = 200) {
                            alert('保存成功')
                            $('#dialog').dialog('close');
                            $('#dg').datagrid('reload');
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
                    url: "weixin/notices",
                    type: 'get',
                    data: {
                        method: 'update',
                        notice_id: notice_id,
                        notice_title: notice_title,
                        notice_text_content: notice_text_content,
                        notice_type: notice_type,
                        notice_date: notice_date,
                        notice_state: notice_state,
                        notice_img_path: notice_img_path
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == 200) {
                            $('#dlg').dialog('close');
                            $('#dg').datagrid('reload');
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
                    <table id="dg" title="学生事务申请表" class="easyui-datagrid" style="width: 100%;height: 250px"
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
                        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addItem()">Add
                            App</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">Edit
                            App</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                           onclick="deleteNotice()">Delete App</a>
                    </div>
                    <div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
                         closed="true"
                         buttons="#dlg-buttons">
                        <div class="ftitle">Notice Information</div>
                        <form id="fm" method="post">
                            <div class="fitem">
                                <label>Notice ID</label>
                                <input name="notice_id" class="easyui-validatebox" hidden="true">
                            </div>
                            <div class="fitem">
                                <label>Notice title</label>
                                <input name="notice_title" class="easyui-validatebox" required="true"/>
                            </div>
                            <div class="fitem">
                                <label>notice text content</label>
                                <input name="notice_text_content" required="true"/>
                            </div>
                            <div class="fitem">
                                <label>notice img path</label>
                                <input name="notice_img_path"/>
                            </div>
                            <div class="fitem">
                                <label>notice type</label>
                                <input name="notice_type"/>
                            </div>
                            <div class="fitem">
                                <label>notice date</label>
                                <input name="notice_date"/>
                            </div>
                            <div class="fitem">
                                <label>notice state</label>
                                <input name="notice_state"/>
                            </div>
                            <div class="fitem">
                                <label>user id</label>
                                <input name="user_id"/>
                            </div>
                        </form>
                    </div>
                    <div id="dlg-buttons">
                        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveItem()">Save</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                           onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/js/bs-init.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
<script src="assets/js/smoothproducts.min.js"></script>
<script src="assets/js/theme.js"></script>
<script src="assets/js/Advanced-NavBar---Multi-dropdown.js"></script>
<script src="assets/js/Sidebar-Menu.js"></script>
<script src="assets/js/weixinapp/webapp.js"></script>
</body>

</html>
