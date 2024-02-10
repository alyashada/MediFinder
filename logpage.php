<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
            margin-bottom: 50px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #dbe3f9;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        .container-inner {
            padding-left: 15px;
            padding-right: 15px;
        }
        .form-group:last-child {
            margin-bottom: 20px;
        }
        body {
            background-image: url('img2.jpg'); 
            background-size: cover;
            background-position: center;
            min-height: 100vh; 
            margin: 0; 
            padding: 0; 
        img {
            max-width: 300px; 
            height: auto; 
            margin-bottom: 20px; 
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-6 container-inner">
                <div class="container">
                    <img src="dw1.jpeg" alt="Image">
                    <h2 class="text-center mb-4">Admin Login</h2>
                    <?php
                    if(isset($_GET['error']) && $_GET['error'] == 1) {
                        echo '<div class="alert alert-danger" role="alert">Invalid username or password!</div>';
                    }
                    ?>
                    <form action="index.php" method="post">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

<?php
    session_start();

    if(isset($_POST['username']) && isset($_POST['password'])) {
        $username = $_POST['username'];
        $password = $_POST['password'];
        
        if($username === 'admin' && $password === 'admin123') {
            $_SESSION['username'] = $username;
            
            header('Location: logpage.php');
            exit();
        } else {
            header('Location: logpage.php?error=1');
            exit();
        }
    }
?>