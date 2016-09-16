<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="common/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秒杀列表页</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h1>秒杀列表</h1>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>库存</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>创建时间</th>
							<th>详情页</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="sk">
						<tr>
						<td>${sk.name }</td>
						<td>
							<fmt:formatDate value="${sk.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${sk.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${sk.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a class="btn btn-info" href="/seckill/${sk.seckillId }/detail" target="_blank">link</a>
						</td>
						<td>${sk.name }</td>
						<td>${sk.name }</td>
						<td>${sk.name }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>