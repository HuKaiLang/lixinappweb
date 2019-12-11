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