// Get the elements
const usersButton = document.getElementById('Users');
const souvenirsButton = document.getElementById('Souvenirs');
const tableTypeSpan = document.getElementById('tabletype');
const elementList = document.getElementById('elem-list');
const elementTable = document.getElementById('element-table');
const prevButton = document.getElementById('prev-button');
const nextButton = document.getElementById('next-button');

// Variables for managing pagination
let currentPage = 1;
const rowsPerPage = 10;
let userData = [];
let souvenirData = [];

// User Table
usersButton.addEventListener('click', function () {
    tableTypeSpan.textContent = 'Users';
    const url = 'https://elizabalint.github.io:8081/api/table_users';

    fetch(url, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            userData = data;
            currentPage = 1;
            displayTableData();
            updatePaginationButtons();
        })
        .catch(error => {
            console.log('Error:', error);
        });
});

//Souvenir table
souvenirsButton.addEventListener('click', function() {
  tableTypeSpan.textContent = 'Souvenirs';
  const url = 'https://elizabalint.github.io:8081/api/table_souvenirs';

  fetch(url, {
    method: 'GET'
  })
  .then(response => response.json())
  .then(data => {
    souvenirData = data;
    console.log(data);
    currentPage = 1;
    displayTableDataS();
    updatePaginationButtons();
  })
  .catch(error => {
    console.log('Error:', error);
  });
});

// Display table data based on current page and the limit of rows per page
function displayTableData() {
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    const tableData = userData.slice(startIndex, endIndex);

    elementTable.innerHTML = '';

    // Create table headers
    const headers = ['ID', 'Username', 'Password', 'Admin', 'Reset wanted' ,'Reset','Delete'];
    const headerRow = document.createElement('tr');
    headers.forEach(headerText => {
        const headerCell = document.createElement('th');
        headerCell.textContent = headerText;
        headerRow.appendChild(headerCell);
    });

    // Add the header row to the table
    elementTable.appendChild(headerRow);

    // Loop through the table data and create table rows
    tableData.forEach(item => {
      
        const row = document.createElement('tr');

        // Create table cells for each attribute
        const idCell = document.createElement('td');
        idCell.textContent = item.id;
        row.appendChild(idCell);

        const usernameCell = document.createElement('td');
        usernameCell.textContent = item.username;
        row.appendChild(usernameCell);

        const passwordCell = document.createElement('td');
        passwordCell.textContent = item.password;
        row.appendChild(passwordCell);

        const adminCell = document.createElement('td');
        adminCell.textContent = (item.admin ==='true') ? 'Yes' : 'No';
        row.appendChild(adminCell);

        const reset_passwordCell = document.createElement('td');
        reset_passwordCell.textContent = (item.reset_password ==='true') ? 'Yes' : 'No';
        // reset_passwordCell.style.left='10px';
        row.appendChild(reset_passwordCell);
     
        //Create reset user button
        const resetbuttonCell = document.createElement('td'); // Create a new table cell for the button column

        if (item.reset_password === 'true') {
          const resetbutton = document.createElement('button');
          resetbutton.textContent = 'Reset';
          resetbutton.classList.add('delete-button');
          resetbutton.style.padding = '10px 20px'; 
          resetbutton.style.fontSize = '13px'; 
          resetbutton.addEventListener('click',function(){
            reset_password(item.id);
            resetbutton.remove();
          });

          resetbuttonCell.appendChild(resetbutton);
        }
        
        row.appendChild(resetbuttonCell);

       
        // Create delete button
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.classList.add('delete-button');
        deleteButton.style.padding = '10px 20px'; 
        deleteButton.style.fontSize = '13px'; 
        deleteButton.addEventListener('click', function () {
            deleteTableRow(item.id); // Pass the ID of the row to delete
        });
        const actionsCell = document.createElement('td');
        actionsCell.appendChild(deleteButton);
        row.appendChild(actionsCell);

        // Add the row to the table body
        elementTable.appendChild(row);
    });

    // Show the table
    elementTable.style.display = 'table';
}

// When to stop the pagination of the buttons
function updatePaginationButtons() {
  if(tableTypeSpan.textContent = 'Users')
  {
  prevButton.disabled = currentPage === 1;
  nextButton.disabled = currentPage === Math.ceil(userData.length / rowsPerPage);
  }
  else{
   prevButton.disabled = currentPage === 1;
   nextButton.disabled = currentPage === Math.ceil(souvenirData.length / rowsPerPage);

  }

}

// Event listener for previous button
prevButton.addEventListener('click', function () {
    if (currentPage > 1) {
        currentPage--;
        displayTableData();
        updatePaginationButtons();
    }
});

// Event listener for next button
nextButton.addEventListener('click', function () {
    const totalPages = Math.ceil(userData.length / rowsPerPage);
    if (currentPage < totalPages) {
        currentPage++;
        displayTableData();
        updatePaginationButtons();
    }
});

//Delete the row and the information form the DB
function deleteTableRow(rowId) {
    // Find the index of the row with the given ID
    const rowIndex = userData.findIndex(item => item.id === rowId);

    // Remove the row from the userData array
    userData.splice(rowIndex, 1);

    // Redisplay the table data
    displayTableData();
    updatePaginationButtons();

    //Delete form the DB

    var formData = new FormData();
    const url = 'https://elizabalint.github.io:8081/api/deleteuser';
    formData.append('id_user', rowId);

    //convert data to json
    var jsonData = {};
    formData.forEach(function (value, key) {
        jsonData[key] = value;
    });
    var jsonPayload = JSON.stringify(jsonData);
   
    //delete the user
    fetch(url, {
        method: 'POST',
        body: jsonPayload
    })
      .then(response => response.json())
      .then(data => {
       
        // console.log(data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  
}

//Reset the password for the selected user
function  reset_password(rowId){


    var formData = new FormData();
    const url = 'https://elizabalint.github.io:8081/api/adminresetpassword';
    formData.append('id_user', rowId);

    //convert data to json
    var jsonData = {};
    formData.forEach(function (value, key) {
        jsonData[key] = value;
    });
    var jsonPayload = JSON.stringify(jsonData);
   
    //delete the user
    fetch(url, {
        method: 'POST',
        body: jsonPayload
    })
      .then(response => response.json())
      .then(data => {
    
      })
      .catch(error => {
        console.error('Error:', error);
      });
}

