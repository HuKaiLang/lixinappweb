$.extend($.fn.validatebox.defaults.rules, {
    integer: {
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
    intOrFloat : {
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '请输入数字，并确保格式正确'
    }
});

function loadMain(){
    console.log("loadMain function called");
    // loadApply();
    loadAward();
}

/* 加载Apply表格 */
function loadApply(){
    $.ajax({
        url:'webapp/apply.html',
        type:'get',
        success:function (res) {
            let target = $("#main-content").html(res);
            $.parser.parse(target); // EasyUI控件在由ajax方法获取之后需要重新渲染
        }
    });
}

function loadAward(){
    $.ajax({
        url:'webapp/award.html',
        type:'get',
        success:function (res) {
            let target = $("#main-content").html(res);
            $.parser.parse(target); // EasyUI控件在由ajax方法获取之后需要重新渲染
        }
    });
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
                $.get('weixin/applies', {
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



function addAward(){
    console.log("addAward function called");
    $('#dlg').dialog('open').dialog('setTitle', 'New Award');
    $('#dialog').form('clear');
}

function editAward() {
    console.log("editAward function called");
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#dlg').dialog('open').dialog('setTitle', 'Edit Award');
        $('#fm').form('load', row);
    } else {
        $.messager.show({
            title: 'Error',
            msg: "请先选择一条数据"
        })
    }
}

function deleteAward() {
    console.log("deleteAwardfunction called");
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm("Confirm", "Are you sure to delete this Award?", function (r) {
            if (r) {
                $.get('weixin/awards', {
                    method: "delete",
                    award_id: row.award_id
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

function passAward(){
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.ajax({
            url: "weixin/awards",
            type: 'get',
            data: {
                method: 'update',
                award_id: row.award_id,
                user_school_id: row.user_school_id,
                award_type: row.award_type,
                award_request_reason: row.award_request_reason,
                award_request_date: row.award_request_date,
                award_grade_point: row.award_grade_point,
                award_request_deed: row.award_request_deed,
                award_request_state: "pass"
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

function rejectAward(){
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.ajax({
            url: "weixin/awards",
            type: 'get',
            data: {
                method: 'update',
                award_id: row.award_id,
                user_school_id: row.user_school_id,
                award_type: row.award_type,
                award_request_reason: row.award_request_reason,
                award_request_date: row.award_request_date,
                award_grade_point: row.award_grade_point,
                award_request_deed: row.award_request_deed,
                award_request_state: "reject"
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

function saveAward() {
    var award_id = $("[name='award_id']").val();
    var user_school_id = $("[name='user_school_id']").val();
    var award_type = $("[name='award_type']").val();
    var award_request_reason = $("[name='award_request_reason']").val();
    var award_request_date = $("[name='award_request_date']").val();
    var award_grade_point = $("[name='award_grade_point']").val();
    var award_request_deed = $("[name='award_request_deed']").val();
    var award_request_state = $("[name='award_request_state']").val();

    var title = $('#dlg').panel('options').title;

    if (title == 'New Award') {
        $.ajax({
            url: "weixin/awards",
            type: "get",
            data: {
                method: "add",
                award_id: award_id,
                user_school_id: user_school_id,
                award_type: award_type,
                award_request_reason: award_request_reason,
                award_request_date: award_request_date,
                award_grade_point: award_grade_point,
                award_request_deed: award_request_deed,
                award_request_state: award_request_state
            },
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    $('#dlg').dialog('close');
                    $('#dg').datagrid('reload');
                    $.messager.show({
                        title: "成功",
                        msg: "已添加数据"
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
            url: "weixin/awards",
            type: 'get',
            data: {
                method: 'update',
                award_id: award_id,
                user_school_id: user_school_id,
                award_type: award_type,
                award_request_reason: award_request_reason,
                award_request_date: award_request_date,
                award_grade_point: award_grade_point,
                award_request_deed: award_request_deed,
                award_request_state: award_request_state
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