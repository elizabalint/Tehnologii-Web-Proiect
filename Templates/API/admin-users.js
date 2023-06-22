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

// Display table data based on current page and the limit of rows per page
function displayTableData() {
  const startIndex = (currentPage - 1) * rowsPerPage;
  const endIndex = startIndex + rowsPerPage;
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

// When to stop the pagination of the buttons
function updatePaginationButtons() {
  prevButton.disabled = currentPage === 1;
  nextButton.disabled = currentPage === Math.ceil(userData.length / rowsPerPage);
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