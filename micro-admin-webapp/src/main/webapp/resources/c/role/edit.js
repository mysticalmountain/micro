$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var roleId = $.cookie("role-edit-id");
    $("#id").val(roleId);
    var queryRoleDto = {
        id: roleId
    }
    var data = {
        requestId: new Date().getTime(),
        data: queryRoleDto
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://www.micro.com:8082/service/queryRole",
        data: dataJson,
        dataType: 'json',
        success: function (res) {
            $("#success").hide();
            $("#fail").hide();
            if (data.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
            } else {
                $("#name").val(res.data[0].name);

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "http://www.micro.com:8082/service/queryService",
                    data: dataJson,
                    dataType: 'json',
                    success: function (data) {
                        $("#success").hide();
                        $("#fail").hide();
                        if (data.success == false) {
                            $("#fail").html(data.errorMessage);
                            $("#fail").show();
                        }
                        $("#services").empty();
                        $.each(data.data, function (i, item) {
                            var checked = false;
                            $.each(res.data[0].permissions, function (j, ps) {
                                if (ps.id == item.permissionId) {
                                    checked = true;
                                }
                            })
                            if (checked) {
                                $("#services").append("<tr><td><input type='checkbox' name='permissionIds' checked value='" + item.permissionId + "'/>" + item.permissionId + "</td><td>" + item.code + "</td><td>" + item.content + "</td><td>" + item.system + "</td><td>" + item.module + "</td></tr>");
                            } else {
                                $("#services").append("<tr><td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.permissionId + "</td><td>" + item.code + "</td><td>" + item.content + "</td><td>" + item.system + "</td><td>" + item.module + "</td></tr>");
                            }
                        })
                    }
                });
                $("#loading").hide();
            }
        }
    });


    $("#save").click(function () {
        $("#loading").show();
        var permissionIds = [];
        $('input[name="permissionIds"]:checked').each(function () {
            permissionIds.push($(this).val());
        });
        var roleDto = {
            id: roleId,
            name: $("#name").val(),
            permissionIds: permissionIds
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: roleDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://www.micro.com:8082/service/editRole",
            data: dataJson,
            dataType: 'json',
            success: function (data) {
                $("#success").hide();
                $("#fail").hide();
                if (data.success == true) {
                    $("#success").html(data.errorMessage);
                    $("#success").show();
                } else {
                    $("#fail").html(data.errorMessage);
                    $("#fail").show();
                }
                $("#loading").hide();
            }
        });
    });
});
