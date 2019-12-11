<%--
  Created by IntelliJ IDEA.
  User: 11864
  Date: 2019/11/25
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./themes/icon.css">
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>
<script type="text/javascript">
    function addNotice() {
        alert('Add Notice function called');
        $('#dlg').dialog('open').dialog('setTitle', 'New Notice');
        $('#dialog').form('clear');
    }

    function editNotice() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', 'Edit Notice');
            $('#fm').form('load', row);
        } else {
            alert('请先选择一条数据');
        }
    }

    function deleteNotice() {
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
            alert("请先选择一条数据");
        }

    }

    function saveNotice() {
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
<html>
<jsp:forward page="login.jsp"></jsp:forward>
<head>
    <title>管理系统</title>
</head>

<body>
<table id="dg" title="Test table" class="easyui-datagrid" style="width: 100%;height: 250px"
       url="weixin/notices?method=query" data="{method:query}" method="get" toolbar="#toolbar" rownumbers="true"
       fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="notice_id" width="50">notice id</th>
        <th field="notice_title" width="50">notice title</th>
        <th field="notce_text_content" width="50">notice text content</th>
        <th field="notice_img_path" width="50">notice img path</th>
        <th field="notice_type" width="50">notice type</th>
        <th field="notice_date" width="50">notice date</th>
        <th field="notice_state" width="50">notice state</th>
        <th field="user_id" width="50">user id</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addNotice()">Add App</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNotice()">Edit App</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNotice()">Delete App</a>
</div>
<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px" closed="true"
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
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNotice()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>
</body>

</html>