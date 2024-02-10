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
  let tbody = document.getElementById("tbody");
  
  database.ref("Clinic Location").on("value", ambildata);
  
  function ambildata(snapshoot) {
    let table = "";
    let no = 1;
    snapshoot.forEach((data) => {
      table += `
        <tr>
          <th scope="row">${no}</th>
          <td>${data.val().clinicname}</td>
          <td>${data.val().desc}</td>
          <td>${data.val().lat}</td>
          <td>${data.val().lng}</td>
        </tr>`;
      no++;
    });
  
    tbody.innerHTML = table;
  }