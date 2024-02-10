var firebaseConfig = {
  apiKey: "AIzaSyA_8h4X455Vn3V9P6NIR2MKc64osWj7pi0",
  authDomain: "healhub-e0c6b.firebaseapp.com",
  databaseURL: "https://healhub-e0c6b-default-rtdb.asia-southeast1.firebasedatabase.app",
  projectId: "healhub-e0c6b",
  storageBucket: "healhub-e0c6b.appspot.com",
  messagingSenderId: "269713508507",
  appId: "1:269713508507:web:9809a012ea7e4c6c41de3e",
  measurementId: "G-ZRDQG9LJ2N"
};

firebase.initializeApp(firebaseConfig);
let database = firebase.database();
let emailV = document.getElementById("email");
let nameV = document.getElementById("name");
let passwordV = document.getElementById("password");
let usernameV = document.getElementById("username");
let tbody = document.getElementById("tbody");
let editemail = document.getElementById("editemail");
let editname = document.getElementById("editname");
let editpassword = document.getElementById("editpassword");
let editusername = document.getElementById("editusername");
let idV = document.getElementById("id");

function createData() {
  let data = {
    email: emailV.value,
    name: nameV.value,
    password: passwordV.value,
    username: usernameV.value
  };
  database.ref("users").push(data);
  emailV.value = "";
  nameV.value = "";
  passwordV.value = "";
  usernameV.value = "";
}

database.ref("users").on("value", ambildata);

function ambildata(snapshoot) {
  let table = "";
  let no = 1;
  snapshoot.forEach((data) => {
    table += `
      <tr>
        <th scope="row">${no}</th>
        <td>${data.val().email}</td>
        <td>${data.val().name}</td>
        <td>${data.val().password}</td>
        <td>${data.val().username}</td>
        <td>
          <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="editRow('${data.key}')">Edit</button>
          <button type="button" class="btn btn-danger" onclick="deleteRow('${data.key}')">Delete</button>
        </td>
      </tr>`;
    no++;
  });

  tbody.innerHTML = table;
}

function editRow(id) {
  database.ref("users/" + id).on("value", function (snapshot) {
    document.getElementById("editemail").value = snapshot.val().email;
    document.getElementById("editname").value = snapshot.val().name;
    document.getElementById("editpassword").value = snapshot.val().password;
    document.getElementById("editusername").value = snapshot.val().username;
    document.getElementById("id").value = id;
  });
}

function updateData() {
  let data = {
    email: editemail.value,
    name: editname.value,
    password: editpassword.value,
    username: editusername.value
  };
  let id = idV.value;
  database.ref("users/" + id).update(data, function(error) {
    if (error) {
      console.error("Data could not be updated:", error);
    } else {
      console.log("Data updated successfully!");
      let modal = bootstrap.Modal.getInstance(document.getElementById("exampleModal"));
      modal.hide();
    }
  });
}

function deleteRow(id) {
  if (confirm("Are you sure you want to delete this record?")) {
    database.ref("users/" + id).remove();
  }
}
