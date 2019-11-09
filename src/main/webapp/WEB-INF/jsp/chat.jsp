<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Chat</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color:white;">
<div class="container">
    <div class="row" style="margin-top: 5%;">
        <div class="col-md-offset-2 col-md-8">
            <div class="alert alert-success" style="display: none;" role="alert">

            </div>
        </div>
        <div class="col-md-offset-2 col-md-6 chatbox">
            <div class="user-list" data-user-list-length="${fn:length(userList)}">
                <c:forEach items="${userList}" var="user">
                    <span class="user message" data-user-list-id="${user.id}">
                        <img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>${user.userName}</span>
                    </span>
                </c:forEach>
            </div>
            <div class="chat" data-user-id="${user_id}">
            </div>
            <div class="input-text">
                <textarea id="content" style="width: 446px; height: 150px;" class="form-control" placeholder="Vui lòng nhập nội dung tin nhắn ở đây"></textarea>
                <button type="button" id="loginOut" class="btn btn-primary">Thoát ra</button>
                <button type="button" id="sendMessage" style="float: right;margin-top: 3px;" class="btn btn-primary" data-to-user-id="">Send</button>
            </div>
        </div>
        <div class="col-md-2 settingsbox">
            <div id="settings">
                <span class="add-friend span-select">Thêm bạn</span><span class="system-message">Thông báo</span>
            </div>
            <div id="add-user" style="display: block;">
                <span>Nhập tên người dùng bạn muốn thêm làm bạn bè</span>
                <input type="text" style="font-size: 12px; width: 140px; display: inline-block;" class="form-control" id="userName" maxlength="20"/>
                <button type="button" class="btn btn-primary" id="find-user">Tìm</button>
                <div id="find-result-list">
                    <ul>
                    </ul>
                </div>
                <nav>
                    <ul class="add-friend-pager pager" data-current-page="1" data-total-page="1">
                        <li><a href="javascript:;" class="prev" style="display: none;">Trang trước</a></li>
                        <li><a href="javascript:;" class="next">Trang tiếp theo</a></li>
                    </ul>
                    <ul class="add-friend-pager pager" style="margin-top: -10px;">
                        <li>Đầu tiên<span>1</span>/<span>3</span>Trang</li>
                    </ul>
                </nav>
            </div>
            <div id="system-message" style="display: none;">
                <div>
                    <ul>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/chat.js" type="text/javascript"></script>
</body>
</html>
