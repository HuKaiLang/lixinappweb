function parseUserHtml(){
    console.log("loadUser()");
    $.ajax({
        url:'webapp/user.html',
        type:'get',
        success:function (res) {
            console.log("loadUser() success function");
            let target = $("#main-content").html(res);
            $.parser.parse(target); // EasyUI控件在由ajax方法获取之后需要重新渲染
        }
    });
}

function addUser() {
    $('#dlg').dialog('open').dialog('setTitle', 'New User');
    $('#dialog').form('clear');
}

function editUser() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#dlg').dialog('open').dialog('setTitle', 'Edit User');
        $('#fm').form('load', row);
    } else {
        $.messager.show({
            title: 'Error',
            msg: "请先选择一条数据"
        })
    }
}

function deleteUser() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm("Confirm", "Are you sure to delete this User?", function (r) {
            if (r) {
                $.get('weixin/user', {
                    method: "delete",
                    user_id: row.user_id
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


function saveUser() {
    var user_id = $("[name='user_id']").val();
    var user_name = $("[name='user_name']").val();
    var user_gender = $("[name='user_gender']").val();
    var user_phone_number = $("[name='user_phone_number']").val();
    var user_email = $("[name='user_email']").val();
    var user_identity = $("[name='user_identity']").val();
    var user_school_id = $("[name='user_school_id']").val();
    var user_charge = $("[name='user_charge']").val();
    var user_account_state = $("[name='user_account_state']").val();
    var user_avatar_path = $("[name='user_avatar_path']").val();
    var user_md5_password = $("[name='user_md5_password']").val();
    var user_balance = $("[name='user_balance']").val();

    var title = $('#dlg').panel('options').title;

    if (title == 'New User') {
        $.ajax({
            url: "weixin/user",
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