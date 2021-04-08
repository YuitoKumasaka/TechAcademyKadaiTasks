<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
<title>タスク管理</title>
</head>

<body>
    <div>
        <h1>タスク一覧</h1>
    </div>
     <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
    <ul>
        <c:forEach var="task" items="${tasks}">
            <li><c:out value="${task.id}" /> ： <c:out value="${task.content}" />
                <div>
                    <a href="${pageContext.request.contextPath}/show?id=${task.id}">詳細</a>
                </div>
            </li>
        </c:forEach>
    </ul>
    <div>
       <p>
            <a href="${pageContext.request.contextPath}/new">新規タスク作成</a>
        </p>
    </div>
</body>
</html>