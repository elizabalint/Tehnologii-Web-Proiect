const url1 = 'http://localhost:8081/api/souvenirg'; // API URL

function loadSouvenirs() {
  fetch(url1, {
    method: 'POST',
  })
    .then(response => response.json())
    .then(data => {
      console.log(data);
      displaySouvenirs(data);
    })
    .catch(error => {
      console.error('Eroare:', error);
    });
}
document.addEventListener('DOMContentLoaded', function() {
  loadSouvenirs();
});
function displaySouvenirs(data){
const container = document.querySelector('.souvenir');
      const countryTableMap = new Map();
      container.innerHTML = ' ';
      data.forEach(item => {
        const country = item.country;
        const souvenir = { name: item.name, period: item.period, gender: item.gender, age:item.age,link: item.buy }; 
      
        if (countryTableMap.has(country)) {
          // Tabel already exists
          const table = countryTableMap.get(country);
          const tableRow = document.createElement('tr');
          
          const td1 = document.createElement('td');
          const souvenirLink = document.createElement('a');
          souvenirLink.href = souvenir.buy;
          souvenirLink.textContent = souvenir.name;
          souvenirLink.style.color = 'inherit'; 
          
          td1.appendChild(souvenirLink);
          
          const td2 = document.createElement('td');
          td2.textContent = souvenir.period;
          const td3 = document.createElement('td');
          td3.textContent = souvenir.gender;
          const td4 = document.createElement('td');
          td4.textContent = souvenir.age;
          tableRow.appendChild(td1);
          tableRow.appendChild(td2);
          tableRow.appendChild(td3);
          tableRow.appendChild(td4);
          table.appendChild(tableRow);
        } else {
          // The table doesn't exist
          const div = document.createElement('div');
          div.classList.add('souvenir');
      
          const h1 = document.createElement('h1');
          h1.textContent = country;
      
          const table = document.createElement('table');
          const tableHeader = document.createElement('tr');
          const th1 = document.createElement('th');
          th1.textContent = 'Souvenir name';
          const th2 = document.createElement('th');
          th2.textContent = 'Period';
          const th3 = document.createElement('th');
          th3.textContent = 'Gender';
          const th4 = document.createElement('th');
          th4.textContent = 'Age';
          tableHeader.appendChild(th1);
          tableHeader.appendChild(th2);
          tableHeader.appendChild(th3);
          tableHeader.appendChild(th4);
          table.appendChild(tableHeader);
      
          const tableRow = document.createElement('tr');
          const td1 = document.createElement('td');
          const souvenirLink = document.createElement('a');
          souvenirLink.href = souvenir.link;
          souvenirLink.textContent = souvenir.name;
          souvenirLink.style.color = 'inherit'; 
          
          td1.appendChild(souvenirLink);
          
          const td2 = document.createElement('td');
          td2.textContent = souvenir.period;
          const td3 = document.createElement('td');
          td3.textContent = souvenir.gender;
          const td4 = document.createElement('td');
          td4.textContent = souvenir.age;
          tableRow.appendChild(td1);
          tableRow.appendChild(td2);
          tableRow.appendChild(td3);
          tableRow.appendChild(td4);
          table.appendChild(tableRow);
      
          div.appendChild(h1);
          div.appendChild(table);
      
          container.appendChild(div);
      
          countryTableMap.set(country, table);
        }
      });
    }
     //errors
 
const searchButton = document.getElementById('searchButton');
  document.querySelector('.search-bar').addEventListener('submit', function(event) {
    event.preventDefault();
    var searchInput = document.getElementById('searchInput').value;
    

    //create data
    var formData = new FormData();
    formData.append('search', searchInput);

    //convert data to json
    var jsonData = {};
    formData.forEach(function (value, key) {
        jsonData[key] = value;
    });
    var jsonPayload = JSON.stringify(jsonData);
    console.log("dfgh");
  fetch(url1, {
    method: 'POST',
    body: jsonPayload,
  })
    .then(response => response.json())
    .then(data => {
      console.log(data);
    
        displaySouvenirs(data);
      
    
})
    .catch(error => {
      console.error('Eroare:', error);
    });
  })
