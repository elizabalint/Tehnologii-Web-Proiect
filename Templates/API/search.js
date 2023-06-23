const url = 'http://localhost:8081/api/souvenirg'; // API URL
const userCardTemplate = document.querySelector("[data-user-template]")
const userCardContainer = document.querySelector("[data-user-cards-container]")
const form = document.querySelector('.search-bar');
function loadSouvenirs() {
  fetch(url, {
    method: 'GET',
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
      
      data.forEach(item => {
        const country = item.country;
        const souvenir = { name: item.name, period: item.period, link: item.buy }; // Modifică "item.buy" cu denumirea corectă a câmpului din baza de date
      
        if (countryTableMap.has(country)) {
          // Tabelul există pentru țara respectivă
          const table = countryTableMap.get(country);
          const tableRow = document.createElement('tr');
          
          const td1 = document.createElement('td');
          const souvenirLink = document.createElement('a');
          souvenirLink.href = souvenir.link;
          souvenirLink.textContent = souvenir.name;
          souvenirLink.style.color = 'inherit'; // Setează culoarea textului pe "inherit" pentru a păstra culoarea din stilul implicit
          
          td1.appendChild(souvenirLink);
          
          const td2 = document.createElement('td');
          td2.textContent = souvenir.period;
      
          tableRow.appendChild(td1);
          tableRow.appendChild(td2);
          table.appendChild(tableRow);
        } else {
          // Tabelul nu există pentru țara respectivă
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
      
          tableHeader.appendChild(th1);
          tableHeader.appendChild(th2);
          table.appendChild(tableHeader);
      
          const tableRow = document.createElement('tr');
          const td1 = document.createElement('td');
          const souvenirLink = document.createElement('a');
          souvenirLink.href = souvenir.link;
          souvenirLink.textContent = souvenir.name;
          souvenirLink.style.color = 'inherit'; // Setează culoarea textului pe "inherit" pentru a păstra culoarea din stilul implicit
          
          td1.appendChild(souvenirLink);
          
          const td2 = document.createElement('td');
          td2.textContent = souvenir.period;
      
          tableRow.appendChild(td1);
          tableRow.appendChild(td2);
          table.appendChild(tableRow);
      
          div.appendChild(h1);
          div.appendChild(table);
      
          container.appendChild(div);
      
          countryTableMap.set(country, table);
        }
      });
    }
form.addEventListener('submit', function(event) {
  event.preventDefault(); // Opriți comportamentul implicit de trimitere a formularului


// Obțineți elementele HTML necesare pentru căutare
const searchInput = document.getElementById('searchInput');
// Manipulați evenimentul de clic pe butonul de căutare
let isSearchClicked = false; // Variabilă de stare pentru a verifica dacă butonul de căutare a fost apăsat

function handleButtonClick() {
  isSearchClicked = true;
  console.log('Butonul a fost apăsat!');
}
const button = document.getElementById('searchButton');
button.addEventListener('click', handleButtonClick);



  fetch(url, {
    method: 'GET',
  })
    .then(response => response.json())
    .then(data => {
      if(button)
      
      console.log('Butonul a fost apăsat aici!');
       

})
    .catch(error => {
      console.error('Eroare:', error);
    });
  })
