<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  />

    <?php include 'header.php'; ?>

    <title>Medi Finder Details</title>
  </head>
  <body>
  <br>
    <div class="container">
      <h1>User Detail</h1>
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Email</label>
        <input type="text" class="form-control" id="email"  />
      </div>
      <div class="mb-3">
        <label for="exampleInputName" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" />
      </div>
      <div class="mb-3">
        <label for="exampleInputPass" class="form-label">Password</label>
        <input type="text" class="form-control" id="password" />
      </div>
      <div class="mb-3">
        <label for="exampleInputUsername" class="form-label">Username</label>
        <input type="text" class="form-control" id="username" />
      </div>
      <button type="submit" class="btn btn-primary" onclick="createData()">Create</button>

      <table class="table">
        <thead>
          <tr>
            <th scope="col">No</th>
            <th scope="col">Email</th>
            <th scope="col">Name</th>
            <th scope="col">Password</th>
            <th scope="col">Username</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody id="tbody"></tbody>
      </table>
    </div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit User Detail</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h1>User Detail</h1>
                <div class="mb-3">
                    <label for="editEmail" class="form-label">Email</label>
                    <input type="text" class="form-control" id="editEmail" />
                </div>
                <div class="mb-3">
                    <label for="editName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="editName" />
                </div>
                <div class="mb-3">
                    <label for="editPassword" class="form-label">Password</label>
                    <input type="text" class="form-control" id="editPassword" />
                </div>
                <div class="mb-3">
                    <label for="editUsername" class="form-label">Username</label>
                    <input type="text" class="form-control" id="editUsername" />
                </div>
                <input type="hidden" id="editId" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="updateData" onclick="updateData()">Save changes</button>
            </div>
        </div>
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
    <script src="https://www.gstatic.com/firebasejs/8.9.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.9.0/firebase-database.js"></script>
    <script src="app.js"></script>

  </body>
</html>
