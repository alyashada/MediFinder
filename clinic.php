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
    <h1>Clinic Detail</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">No</th>
            <th scope="col">Clinic Name</th>
            <th scope="col">Description</th>
            <th scope="col">Latitude</th>
            <th scope="col">Longitude</th>
        </tr>
        </thead>
        <tbody id="tbody"></tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.9.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.9.0/firebase-database.js"></script>
<script src="clinic.js"></script>

</body>
</html>