<%--
  Created by IntelliJ IDEA.
  User: binhnguyen
  Date: 6/2/20
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Upload Multi File</title>

</head>

<body>

<jsp:include page="_menu.jsp"/>

<h3>Upload Multiple File:</h3>

<form:form modelAttribute="myUploadForm" method="POST" action="" enctype="multipart/form-data">
    Description:
    <br>
    <form:input path="description" style="width:300px;"/>
    <br/><br/>

    File to upload (1): <form:input path="fileDatas" type="file"/><br />

    File to upload (2): <form:input path="fileDatas" type="file"/><br />

    File to upload (3): <form:input path="fileDatas" type="file"/><br />

    File to upload (4): <form:input path="fileDatas" type="file"/><br />

    File to upload (5): <form:input path="fileDatas" type="file"/><br />

    <input type="submit" value="Upload">

</form:form>

</body>

</html>
</body>
</html>
