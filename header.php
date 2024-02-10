<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .custom-color-navbar {
            background-color: #dbe3f9; 
        }

        .custom-color-navbar .navbar-brand .brand-name {
            color: #000000; 
            font-weight: bold;
        }

        .custom-color-navbar .navbar-nav .nav-link {
            color: #000000 !important; 
        }

        .custom-color-navbar .navbar-nav .nav-link:hover {
            color: #000000 !important;
        }
    </style>
</head>

<body>

    <nav class="navbar navbar-expand-lg custom-color-navbar">
        <div class="container">
            <a class="navbar-brand" href="#">
                <span class="brand-name">DASHBOARD ADMIN</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.php">User</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="user_location.php">User Location</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="clinic.php">Clinic Location</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logpage.php">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>
