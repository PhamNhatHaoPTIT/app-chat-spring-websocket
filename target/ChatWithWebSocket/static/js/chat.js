$(function () {
    var from_ = "";
    var to_ = "";
    var findUserByUserName = function(userName, current_page) {
        $.ajax({
            url: "findUserByUserName",
            type: "POST",
            data: {
                userName: userName,
                current_page: current_page,
                page_size: 10
            },
            dataType: "json",
            success: function (response) {
                console.log(response);
                $("#find-result-list ul").html("");
                $("#find-result-list ul").append("<li><span>Kết quả tìm kiếm: " + response.total_record + "</span></li>");

                if (response.total_page > 1) {
                    $(".add-friend-pager").css("display", "block");
                } else {
                    $(".add-friend-pager").css("display", "none");
                }

                if (response.total_record == 0) {
                    $(".add-friend-pager").css("display", "none");
                    var msg = "Không tìm thấy user";
                    msgtips(msg);
                }

                var dataList = response.dataList;
                var length = dataList.length;
                var dataFindResult = "";
                for (var i = 0; i < length; i++) {
                    dataFindResult +=
                        "<li>" +
                            "<span>" + dataList[i].userName + "</span> " +
                            "<button class=\"add-friend-request btn btn-primary\" " + "data-add-user-id=\"" + dataList[i].id + "\">" +
                                            "Thêm bạn</button>" +
                        "</li>";
                }

                $(".add-friend-pager").attr("data-current-page", response.current_page);

                $(".add-friend-pager").attr("data-total-page", response.total_page);
                $(".add-friend-pager").eq(1).find("span").eq(1).text(response.total_page);
                $("#find-result-list ul").append(dataFindResult);
                $("body").on("click", ".add-friend-request", function () {
                    var index = $(this).parent().index();
                    $(this).attr("disabled", "disabled");
                    var from_user_id = $(".chat").attr("data-user-id");
                    var to_user_id = $(this).attr("data-add-user-id");
                    if (from_user_id == null || from_user_id == "") {
                        var msg = "Không thể nhận được giá trị của a_id";
                        msgtips(msg);
                        return false;
                    }
                    if (to_user_id == null || to_user_id == "") {
                        var msg = "Không thể nhận được giá trị của b_id";
                        msgtips(msg);
                        return false;
                    }

                    $.ajax({
                        url: "sendFriendRequest",
                        type: "POST",
                        data: JSON.stringify({
                            from_user_id: from_user_id,
                            to_user_id: to_user_id,
                            content: "test",
                            message_type: 1,
                            send_time: new Date()
                        }),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (response) {
                            $("#find-result-list ul li").eq(index).find("button").removeAttr("disabled");
                            msgtips(response.msg);
                        },
                        error: function () {

                        }
                    });
                });
            },
            error: function () {

            }
        });
    }

    var getChatRecord = function (to_user_id) {
        var login_user_id = $(".chat").attr("data-user-id");
        console.log("to_user_id = " + to_user_id);
        $("#sendMessage").attr("data-to-user-id", to_user_id);
        $.ajax({
            url: "getMessageRecord",
            timeout: 3000,
            async: false,
            type: "GET",
            data: {
                to_user_id: to_user_id
            },
            dataType: "json",
            success: function (response) {
                console.log(response);
                from_ = response[0].from_avatar;
                to_ = response[0].to_avatar;
                var dataHtmlRecord = "";
                for (var i = 0; i < response.length; i++) {
                    if (response[i].to_user_id == to_user_id) {
                        console.log("right = " + response[i].to_user_id);
                        dataHtmlRecord +=
                            "<div class=\"receiver\">" +
                                "<div>" + "<span class='avatar_chat'>" + response[i].from_avatar + "</span>" + "</div>" +
                                "<div>" +
                                    "<div class=\"right_triangle\">" + "</div>"
                                    + "<span>" + response[i].content + "</span>" +
                                "</div>" +
                            "</div>";
                    } else if (response[i].to_user_id == login_user_id) {
                        console.log("left = " + response[i].from_user_id);
                        dataHtmlRecord +=
                            "<div class=\"sender\">" +
                                "<div>" + "<span class='avatar_chat'>" + response[i].to_avatar + "</span>" + "</div>" +
                                "<div>" +
                                    "<div class=\"left_triangle\"></div>"
                                + "<span>" + response[i].content + "</span>" +
                                "</div>" +
                            "</div>";
                    }
                }
                $(".chat").html(dataHtmlRecord);
                var chat = document.getElementsByClassName("chat")[0];
                chat.scrollTop = chat.scrollHeight;
            },
            error: function () {
                alert("Kết nối với máy chủ không thành công");
            }
        });
    }

    var msgtips = function (msg) {
        $(".alert-success").css("display", "block");
        $(".alert-success").html(msg);
        setTimeout(function () {
            $(".alert-success").css("display", "none");
        }, 1500);
    }

    var getUserFriendList = function() {
        $.ajax({
            url: "getUserFriendList",
            type: "GET",
            dataType: "json",
            success: function (response) {
                var dataUserListHtml = "";
                var dataLength = response.length;
                for (var i = 0; i < dataLength; i++) {
                    dataUserListHtml += "<span class=\"user message\" data-user-list-id=\"" + response[i].id + "\">" +
                        "<img src=\"static/images/avatar.jpg\" alt=\"avatar\" class=\"avatar\">" +
                        "<span>" + response[i].userName + "</span></span>";
                }
                console.log("dataUserListHtml" + dataUserListHtml);
                $(".user-list").html(dataUserListHtml);
                var to_user_id_default = $(".message").eq(0).attr("data-user-list-id");
                $(".message").eq(0).addClass("user-select");
                $("#sendMessage").attr("data-to-user-id", to_user_id_default);
                getChatRecord(to_user_id_default);
            },
            error: function () {

            }
        });
    }

    var data_user_list_length = $(".user-list").attr("data-user-list-length");
    console.log(data_user_list_length);
    if (data_user_list_length > 0) {
        var to_user_id_default = $(".message").eq(0).attr("data-user-list-id");
        $(".message").eq(0).addClass("user-select");
        $("#sendMessage").attr("data-to-user-id", to_user_id_default);
        getChatRecord(to_user_id_default);
    }

    var websocket;
    setTimeout(function () {
        websocket = new WebSocket("ws://localhost:8080/ChatWithWebSocket/websocket");

        websocket.onopen = function (evnt) {
            console.log("websocket opened");
        };

        websocket.onmessage = function (evnt) {
            console.log("evet: " + evnt);
            var data = JSON.parse(evnt.data);
            if (data.message_type == 0) {
                $(".message").each(function () {
                    var from_user_id = $(this).attr("data-user-list-id");
                    if ($(this).hasClass("user-select")) {
                        $(this).removeClass("user-select");
                    }
                    if (from_user_id == data.from_user_id) {
                        console.log("index = " + $(this).index());
                        if ($(this).index() != 0) {
                            var dataHtml = $(this).prop("outerHTML");
                            $(".user-list").prepend(dataHtml);
                            $(this).remove();
                        }
                        getChatRecord(from_user_id);
                    }
                });

                $(".message").eq(0).addClass("user-select");
                $(".chat").append(
                    "<div class=\"sender\">" + "<div>" + "<span class='avatar_chat'>" + to_ + "</span>" + "</div>" +
                        "<div>" + "<div class=\"left_triangle\"></div>"
                            + "<span>" + data.content + "</span>" +
                        "</div>" +
                    "</div>");
                var chat = document.getElementsByClassName("chat")[0];
                chat.scrollTop = chat.scrollHeight;
            } else if (data.message_type == 1) {
                $("#system-message div ul").prepend
                ("<li data-from-user-id=\"" + data.from_user_id + "\">" +
                    "<span>" + data.content + "đã gửi yêu cầu kết bạn</span>" +
                    "<br>" +
                    "<button class=\"user-request btn btn-primary\" user-status=\"0\">Chấp nhận</button>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<button class=\"user-request btn btn-primary\" user-status=\"1\">Từ chối</button>" +
                    "</li>"
                );
            }
            console.log("websocket listen message");
        };

        websocket.onerror = function (evnt) {
            console.log("websocket error");
        };

        websocket.onclose = function (evnt) {
            console.log("websocket close");
        };
    }, 1000);

    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            $("#sendMessage").click();
        }
    });

    $("#sendMessage").click(function () {
        var message_content = $("#content").val();
        if (message_content == null || message_content.trim() == "") {
            var msg = "Nội dung tin nhắn không thể để trống";
            msgtips(msg);
            $("#content").val("");
            return false;
        }
        var to_user_id = $(this).attr("data-to-user-id");
        if (to_user_id == null || to_user_id == "") {
            var msg = "Không thể lấy id của người nhận";
            msgtips(msg);
            return false;
        }
        var from_user_id = $(".chat").attr("data-user-id");
        if (from_user_id == null || from_user_id == "") {
            var msg = "Không thể lấy id của người gửi";
            msgtips(msg);
            return false;
        }
        var send_time = new Date();
        $.ajax({
            url: "message",
            type: "POST",
            data: JSON.stringify({
                from_user_id: from_user_id,
                to_user_id: to_user_id,
                content: message_content,
                send_time: send_time,
                message_type: 0
            }),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                $(".chat").append
                ("<div class=\"receiver\">"
                    + "<div>" + "<span class='avatar_chat'>" + from_ + "</span>" + "</div>" +
                        "<div>" + "<div class=\"right_triangle\"></div>"
                        + "<span>" + response.data.content + "</span>" +
                        "</div>" +
                    "</div>"
                );

                var chat = document.getElementsByClassName("chat")[0];
                chat.scrollTop = chat.scrollHeight;
                $("#content").val("");
            },
            error: function () {

            }
        });
    });

    $("#loginOut").click(function () {
        if (!websocket.closed) {
            websocket.close();
        }
        $.ajax({
            url: "loginOut",
            type: "GET",
            dataType: "json",
            success: function (response) {
                if (response.code == 0) {
                    location.href = "Login";
                } else {
                    var msg = "Đăng xuất thất bại";
                    msgtips(msg);
                }
            },
            error: function () {

            }
        });

    });

    $(".user-list").on("click", ".message", function () {
        $(".user").each(function () {
            if ($(this).hasClass("user-select")) {
                $(this).removeClass("user-select");
            }
        });
        $(this).addClass("user-select");
        var to_user_id = $(this).attr("data-user-list-id");
        getChatRecord(to_user_id);
    });

    $(".add-friend").click(function () {
        $(this).addClass("span-select");
        $(".system-message").removeClass("span-select");
        $("#system-message").css('display', 'none');
        $("#add-user").css('display', 'block');
        $(".add-friend-pager").css("display", "none");
        $("#find-result-list ul").html("");
    });

    $("#find-user").click(function () {
        var userName = $("#userName").val().trim();
        if (userName == null || userName == "") {
            var msg = "Vui lòng nhập tên người dùng bạn đang tìm kiếm";
            msgtips(msg);
            return false;
        }
        findUserByUserName(userName, 1);
    });

    $(".system-message").click(function () {
        $(this).addClass("span-select");
        $(".add-friend").removeClass("span-select");
        $("#system-message").css('display', 'block');
        $("#add-user").css('display', 'none');
        $(".add-friend-pager").css("display", "none");
        var user_id = $(".chat").attr("data-user-id");

        $.ajax({
            url: "getVerificationResult",
            type: "GET",
            dataType: "json",
            success: function (response) {
                var dataVerification = "";
                var response_format = response;
                for (key in response_format) {
                    console.log("response_format = " + response_format[0].data1);
                    if (response_format[key].status == 2) {
                        if (response_format[key].data1.id != user_id) {
                            dataVerification += "<li data-from-user-id=\"" +
                                response_format[key].data1.id + "\"><span>" + response_format[key].process_result
                                + "</span><br><button class=\"user-request btn btn-primary\" user-status=\"0\">Chấp nhận</button>" +
                                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "<button class=\"user-request btn btn-primary\" user-status=\"1\">Từ chối</button></li>";
                        } else if (response_format[key].data1.id == user_id) {
                            dataVerification += "<li data-from-user-id=\"" +
                                response_format[key].data1.id + "\"><span>" + response_format[key].process_result +
                                "</span><br>" + "</li>";
                        }
                    } else {
                        dataVerification += "<li data-from-user-id=\"" +
                            response_format[key].data1.id + "\"><span>" + response_format[key].process_result +
                            "</span><br>" + "</li>";
                    }
                    //
                }
                console.log(dataVerification);
                $("#system-message div ul").html(dataVerification);
            },
            error: function () {

            }
        });
    });

    $("body").on("click", ".user-request", function () {
        var b_id = $(".chat").attr("data-user-id");
        var a_id = $(this).parent().attr("data-from-user-id");
        var status = $(this).attr("user-status");

        if (b_id == null || b_id == "") {
            var msg = "Giá trị của b_id không có sẵn";
            msgtips(msg);
            return false;
        }

        if (a_id == null || a_id == "") {
            var msg = "Giá trị của a_id không có sẵn";
            msgtips(msg);
            return false;
        }

        if (status == null || status == "") {
            var msg = "Giá trị của trạng thái không có sẵn";
            msgtips(msg);
            return false;
        }

        $.ajax({
            url: "processUserRequest",
            type: "POST",
            data: JSON.stringify({
                a_id: a_id,
                b_id: b_id,
                status: status,
                add_time: new Date()
            }),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                $(".system-message").click();
                msgtips(response.msg);
                if (response.data.status == 0) {
                    getUserFriendList();
                }
            },
            error: function () {

            }
        });
    });

    $(".add-friend-pager li a").click(function () {
        var current_page = parseInt($(this).parent().parent().attr("data-current-page"));
        var userName = $("#userName").val().trim();

        if ($(this).hasClass("prev")) {
            current_page -= 1;
            if (current_page <= 1) {
                current_page = 1;
                $(".add-friend-pager li .prev").css("display", "none");
            }
            $(".add-friend-pager").eq(1).find("span").eq(0).text(current_page);
            $(".add-friend-pager li .next").css("display", "inline-block");
            findUserByUserName(userName, current_page);
            console.log("current_page = " + current_page + ", userName = " + userName);
        } else if ($(this).hasClass("next")) {
            var total_page = $(this).parent().parent().attr("data-total-page");
            current_page += 1;
            if (current_page >= total_page) {
                current_page = total_page;
                $(".add-friend-pager li .next").css("display", "none");
            }
            $(".add-friend-pager").eq(1).find("span").eq(0).text(current_page);
            $(".add-friend-pager li .prev").css("display", "inline-block");
            findUserByUserName(userName, current_page);
            console.log("current_page = " + current_page + ", userName = " + userName);
        }
    });
});