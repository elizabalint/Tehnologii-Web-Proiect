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
usersButton.addEventListener('click', function() {
  tableTypeSpan.textContent = 'Users';
  const url = 'http://localhost:8081/api/table_users';

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
//souvenir table
souvenirsButton.addEventListener('click', function() {
  tableTypeSpan.textContent = 'Souvenirs';
  const url = 'http://localhost:8081/api/table_souvenirs';

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
  const endIndex = startIndex + rowsPerPage
  const tableData = userData.slice(startIndex, endIndex);

  elementTable.innerHTML = '';
  // Create table headers
  const headers = ['ID', 'Username', 'Password', 'Admin'];
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
    adminCell.textContent = item.admin ? 'Yes' : 'No';
    row.appendChild(adminCell);
  
    // Add the row to the table body
    elementTable.appendChild(row);
  });
  // Show the table
  elementTable.style.display = 'table';
}
// Display table data based on current page and the limit of rows per page

  function displayTableDataS() {
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage
    const tableData = souvenirData.slice(startIndex, endIndex);

    elementTable.innerHTML = '';
    // Create table headers
    const headers = ['ID', 'Name', 'ID_country', 'Period','Gender','Age','Where to buy'];
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
  
      const nameCell = document.createElement('td');
      nameCell.textContent = item.name;
      row.appendChild(nameCell);
  
      const IDCountryCell = document.createElement('td');
      IDCountryCell.textContent = item.id_country;
      row.appendChild(IDCountryCell);
  
      const periodCell = document.createElement('td');
      periodCell.textContent = item.period;
      row.appendChild(periodCell);
    
      const genderCell = document.createElement('td');
      genderCell.textContent = item.gender;
      row.appendChild(genderCell);

      const ageCell = document.createElement('td');
      ageCell.textContent = item.age;
      row.appendChild(ageCell);

      const buyCell = document.createElement('td');
      buyCell.textContent = item.buy;
      row.appendChild(buyCell);
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
prevButton.addEventListener('click', function() {
  if (currentPage > 1) {
    currentPage--;
    displayTableData();
    updatePaginationButtons();
  }
});

// Event listener for next button
nextButton.addEventListener('click', function() {
  const totalPages = Math.ceil(userData.length / rowsPerPage);
  if (currentPage < totalPages) {
    currentPage++;
    displayTableData();
    updatePaginationButtons();
  }
});
// Event listener for previous button
prevButton.addEventListener('click', function() {
  if (currentPage > 1) {
    currentPage--;
    displayTableData();
    updatePaginationButtons();
  }
});

// Event listener for next button
nextButton.addEventListener('click', function() {
  const totalPages = Math.ceil(souvenirData.length / rowsPerPage);
  if (currentPage < totalPages) {
    currentPage++;
    displayTableData();
    updatePaginationButtons();
  }
});
