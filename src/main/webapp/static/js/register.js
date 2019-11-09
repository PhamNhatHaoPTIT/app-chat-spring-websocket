$(function() {

    $(document).keydown(function(event) {
        if (event.keyCode == 13) {
            $("#register").click();
        }
    });

    $("#userName").blur(function() {
        var userName = $(this).val().trim();
        if (userName == null || userName == "") {
            $("#msgtips").text("Tên người dùng không thể để trống");
            $("#userName").focus();
            return false;
        }

        $.ajax({
            url: "checkUserNameIfExist",
            type: "POST",
            data: {
                userName: userName
            },
            dataType: "json",
            success: function(response) {
                if (response.code == 1) {
                    $("#msgtips").text(response.msg);
                    $("#userName").focus();
                } else {
                    $("#msgtips").text(response.msg);
                }
            },
            error: function () {

            }
        });
    });

    $("#register").click(function() {
        var userName = $("#userName").val().trim();
        var password = $("#password").val().trim();
        var repeatPassword = $("#repeatpassword").val().trim();
        var captcha = $("#captcha").val().trim();

        if (userName == null || userName == "") {
            $("#msgtips").text("Tên người dùng không thể để trống");
            $("#userName").focus();
            return false;
        }

        if (password == null || password == "") {
            $("#msgtips").text("Mật khẩu không thể để trống");
            $("#password").focus();
            return false;
        }

        if (repeatPassword == null || repeatPassword == "") {
            $("#msgtips").text("Vui lòng xác nhận mật khẩu đã nhập");
            $("#repeatpassword").focus();
            return false;
        }

        if (password != repeatPassword) {
            $("#msgtips").text("Nhập mật khẩu không nhất quán hai lần");
            $("#repeatpassword").focus();
            return false;
        }

        if (captcha == null || captcha == "") {
            $("#msgtips").text("Mã xác minh không thể để trống");
            $("#captcha").focus();
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
                    registerUser(userName, password);
                } else {
                    $("#msgtips").text(response.msg);
                    $(".update-captcha").click();
                }
            },
            error: function() {

            }
        });
    });

    function registerUser(userName, password) {
        $.ajax({
            url: "register",
            type: "POST",
            data: JSON.stringify({
               userName: userName,
               password: password
            }),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                if (response.code == 0) {
                    $("#msgtips").text(response.msg);
                    setTimeout(function() {
                        location.href = "Login";
                    }, 1500);
                } else {
                    $("#msgtips").text(response.msg);
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
