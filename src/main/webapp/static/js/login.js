$(function () {
    $("#userName").focus();

    $(document).keydown(function(event) {
        if (event.keyCode == 13) {
            $("#userLogin").click();
        }
    });

    $("#userLogin").click(function() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var captcha = $("#captcha").val();

        if (userName == null || userName == "") {
            $("#userName").focus();
            $("#msgtips").text("Tên người dùng không thể để trống");
            return false;
        }
        if (password == null || password == "") {
            $("#password").focus();
            $("#msgtips").text("Mật khẩu không thể để trống");
            return false;
        }
        if (captcha == null || captcha == "") {
            $("#captcha").focus();
            $("#msgtips").text("Mã xác minh không thể để trống");
            return false;
        }
        $.ajax({
            url: "checkCaptcha",
            type: "POST",
            data: {
                captcha_client : captcha
            },
            dataType: "json",
            success: function(response) {
                if (response.code == 0) {
                    checkUserLogin(userName, password);
                } else {
                    $("#msgtips").text(response.msg);
                    $(".update-captcha").click();
                }
            },
            error: function() {

            }
        });

    });

    function checkUserLogin(userName, password) {
        $.ajax({
            url: "login",
            type: "POST",
            data: JSON.stringify({
                userName: userName,
                password: password
            }),
            contentType: "application/json",
            dataType: "json",
            success: function(response) {
                console.log(response);
                if (response.code == 0) {
                    location.href = 'chat';
                } else {
                    $("#msgtips").text(response.msg);
                    $(".update-captcha").click();
                }
            },
            error: function() {

            }
        });
    }

    $(".update-captcha").click(function() {
        var date = new Date();
        var captchaUrl = "captcha?timestamp=" + date.valueOf();
        $(".update-captcha").attr("src", captchaUrl);
    });
});