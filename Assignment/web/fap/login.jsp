<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {
                background-color: lightblue;
                background-size: cover;
                background-position: center center;
                height: 100vh;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .content {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 20px;
                border-radius: 10px;
                max-width: 400px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <form action="/Assignment/login" method="POST">
                <div style="margin-bottom : 5px">
                    <label for="userID">Username</label> 
                    <input type="text" id="userID" name="username"><br>
                </div>
                <label for="pass">Password</label>
                <input style="margin-left: 3px" type="password" id="pass" name="password"><br><br>
                <input type="submit" value="Login" name="submit"><br>
            </form>
        </div>
    </body>
</html>
