<%--
  Created by IntelliJ IDEA.
  User: binhnguyen
  Date: 6/2/20
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Result</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<h3>Uploaded Files:</h3>
Description: ${description}
<br/>
<c:forEach items="${uploadedFiles}" var="file">
    - ${file} <br>
</c:forEach>
</body>
</html>
