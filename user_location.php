<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <?php include 'header.php'; ?>

    <title>Medi Finder Details</title>
</head>
<body>
<br>
<div class="container">
    <h1>User's Current Location</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">No</th>
            <th scope="col">Address</th>
            <th scope="col">Coordinate</th>
            <th scope="col">Date/Time</th>
            <th scope="col">Latitude</th>
            <th scope="col">Longitude</th>
            <th scope="col">User Agent</th>
            <th scope="col">Username</th>
        </tr>
        </thead>
        <tbody id="tbody"></tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://www.gstatic.com/firebasejs/8.9.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.9.0/firebase-database.js"></script>
<script src="loc.js"></script>


</body>
</html>