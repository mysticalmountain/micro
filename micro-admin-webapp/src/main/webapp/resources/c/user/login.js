$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");


    $("#save").click(function () {
        $("#loading").show();
        var loginDto = {
            username: $("#username").val(),
            password: $("#password").val()
        }
        var data = {
            requestId: '' + new Date().getTime(),
            data: loginDto
        };
        var dataJson = JSON.stringify(data);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8081/service/login",
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
            }
        });
        $("#loading").hide();
    });
});
