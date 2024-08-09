<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
    <h1>Update Student</h1>
    <form action="students" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${student.id}"/>
        <label for="name">Name:</label><br/>
        <input type="text" id="name" name="name" value="${student.name}"/><br/>
        <label for="age">Age:</label><br/>
        <input type="number" id="age" name="age" value="${student.age}"/><br/>
        <label for="email">Email:</label><br/>
        <input type="email" id="email" name="email" value="${student.email}"/><br/>
        <label for="course">Course:</label><br/>
        <input type="text" id="course" name="course" value="${student.course}"/><br/>
        <input type="submit" value="Update Student"/>
    </form>
    <a href="index.jsp">Back</a>
</body>
</html>
