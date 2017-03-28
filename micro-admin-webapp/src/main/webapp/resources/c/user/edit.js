$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var userId = $.cookie("user-edit-id");
    $("#id").val(userId);
    var queryUserDto = {
        id: userId
    }
    var data = {
        requestId: '' + new Date().getTime(),
        data: queryUserDto
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://www.micro.com:8081/service/queryUserDetail",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $("#name").val(data.data.name);
            $("#username").val(data.data.username);
            $("#age").val(data.data.profileDto.age);
            $("#address").val(data.data.profileDto.address);
            var options = $('select option');
            $.each(options, function () {
                if ($(this).val() == data.data.profileDto.sex) {
                    $(this).attr('selected', true);
                }
            });
            $("#accountNo").val(data.data.authorityDtos[0].accountNo);
            $("#password").val(data.data.authorityDtos[0].password);
            $("#roles").empty();

            //初始化角色
            var reqData = {
                requestId: '' + new Date().getTime()
            };
            var dataJson = JSON.stringify(reqData);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://www.micro.com:8082/service/queryRole",
                data: dataJson,
                dataType: 'json',
                success: function (res) {
                    if (res.success == true) {
                        $.each(res.data, function (i, item) {
                            var isCheck = false;
                            $.each(data.data.rolebakDtos, function (i, itemUser) {
                                if (item.id == itemUser.roleId) {
                                    isCheck = true;
                                }
                            });
                            if (isCheck) {
                                $("#roles").append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' checked value='" + item.id + "'>" + item.name + "</label>");
                            } else {
                                $("#roles").append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' value='" + item.id + "'>" + item.name + "</label>");
                            }
                        });
                    }
                }

            });
            $("#loading").hide();
        }
    });


    $("#save").click(function () {
        $("#loading").show();
        var profileDto = {
            age: $("#age").val(),
            sex: $("#sex").val(),
            address: $("#address").val()
        };
        var authorityDto = {
            accountNo: $("#accountNo").val(),
            password: $("#password").val()
        };
        var roleIds = $("*[name='roleIds']");
        var roleDtos = new Array();
        var chk_value = [];
        $('input[name="roleIds"]:checked').each(function () {
            chk_value.push($(this).val());
            var roleDto = {
                roleId: $(this).val(),
                name: ''
            };
            roleDtos.push(roleDto);
        });
        var userDto = {
            id: userId,
            name: $("#name").val(),
            username: $("#username").val(),
            userType: 'PERSONAL',
            profileDto: profileDto,
            authorityDto: authorityDto,
            roleDtos: roleDtos
        };
        var reqData = {
            requestId: '' + new Date().getTime(),
            data: userDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8081/service/editUser",
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
