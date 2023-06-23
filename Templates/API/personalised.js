const url = 'http://localhost:8081/api/souvenirg'; // API URL
const userCardTemplate = document.querySelector("[data-user-template]")
const userCardContainer = document.querySelector("[data-user-cards-container]")
const searchInput = document.querySelector("[data-search]")
  fetch(url, {
    method: 'GET',
  })
    .then(response => response.json())
    .then(data => {
        const container = document.querySelector('.souvenir');
        const countryTableMap = new Map();
        
        data.forEach(item => {
          const country = item.country;
          const souvenir = {
            name: item.name,
            period: item.period,
            link: item.buy,
            gender: item.gender,
            age: item.age
          };
        
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
            souvenirLink.style.color = 'inherit'; // Setează culoarea textului pe "inherit" pentru a păstra culoarea din stilul implicit
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
})
    .catch(error => {
      console.error('Eroare:', error);
    });

// Apelați funcția fetchData pentru a iniția procesul de obținere a datelor
