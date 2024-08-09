<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Students</title>
    <style>
        /* CSS Styles */

        body, h1, form {
            margin: 0;
            padding: 0;
        }

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
            max-width: 600px;
            margin-bottom: 1rem;
        }

        form {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 2rem;
            width: 100%;
            max-width: 600px;
            text-align: center;
        }

        label {
            display: block;
            margin: 0.5rem 0;
            font-weight: bold;
        }

        input[type="text"] {
            width: calc(100% - 2rem);
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
            font-size: 1rem;
            margin-top: 1rem;
            display: inline-block;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Search Students</h1>
    <form action="students" method="get">
        <input type="hidden" name="action" value="search"/>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"/>
        <label for="course">Course:</label>
        <input type="text" id="course" name="course"/>
        <input type="submit" value="Search"/>
    </form>
    <a href="index.jsp">Back</a>
</body>
</html>
