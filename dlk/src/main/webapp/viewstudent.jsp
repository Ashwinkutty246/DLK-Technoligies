<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Students</title>
    <style>
        /* CSS Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
        }

        h1 {
            background-color: #4CAF50;
            color: white;
            padding: 1rem;
            border-radius: 4px;
            font-size: 2rem;
            text-align: center;
            width: 100%;
            max-width: 900px;
            margin-bottom: 1rem;
        }

        table {
            width: 100%;
            max-width: 900px;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 0.75rem;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
            margin: 0 0.5rem;
            font-size: 0.9rem;
        }

        a:hover {
            text-decoration: underline;
        }

        .actions {
            display: flex;
            gap: 0.5rem;
        }

        .actions a {
            padding: 0.5rem;
            border-radius: 4px;
            border: 1px solid transparent;
        }

        .actions a:hover {
            border: 1px solid #4CAF50;
            background-color: #f1f1f1;
        }

        .back-link {
            margin-top: 1rem;
            display: inline-block;
            text-decoration: none;
            color: #4CAF50;
            font-size: 1rem;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Students List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Course</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Ensure students list is correctly populated -->
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                    <td>${student.email}</td>
                    <td>${student.course}</td>
                    <td class="actions">
                        <a href="students?action=edit&id=${student.id}">Edit</a>
                        <a href="students?action=delete&id=${student.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a class="back-link" href="index.jsp">Back</a>
</body>
</html>
