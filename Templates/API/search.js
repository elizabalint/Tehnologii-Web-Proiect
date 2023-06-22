/*const searchI = document.getElementById('searchI');
var jsonPayload = JSON.stringify(jsonData);
const url = 'http://localhost:8081/api/souvenir'; // API URL
    fetch(url, {
        method: 'GET',
        body: jsonPayload,
    })
      .then(response => response.json())
      .then(data  => {
        var nameS=data.nameS;
        var nameC=data.nameC;
    const souvenirsList = document.getElementById('souvenirs-list');
    const keys = Object.keys(data);
    console.log(data);
    keys.forEach(key => {
      console.log(key);
      const souvenir = data[key];
      console.log(souvenir);

      const souvenirElement1 = document.createElement('h1');
      souvenirElement1.textContent = nameC;
      souvenirsList.appendChild(souvenirElement1);
      console.log(souvenirElement1);

      

    });
  })
      .catch(error => {
        console.error('Error:', error);
        console.log("error");
      });
*/
// Funcția pentru a obține datele din baza de date și a le afișa în pagină
const url = 'http://localhost:8081/api/souvenirg'; // API URL

console.log("dxcfgvh");

function afiseazaDate(data){
  console.log("dxcfgvh");

      const nameS = data.nameS;
      const nameC = data.nameC;
      const period = data.period;
      const container = document.getElementById('data-container');
      console.log(response);
      // Creați conținutul HTML cu datele
      let htmlContent = '';

      // Iterați prin fiecare pereche de nume de suvenir și țară
      for (let i = 0; i < nameS.length; i++) {
        htmlContent += `
          <template>
            <h1>${nameC[i]}</h1>
            <table>
              <tr>
                <th>${nameS[i]}</th>
                <th>${period[i]} </th>
              </tr>
            </table>
          </template>
        `;
      }

      // Adăugați conținutul HTML la elementul container
      container.innerHTML = htmlContent;
    }
  fetch(url, {
    method: 'GET',
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("dxcfgvh");
      console.log(data);
      if(data.success){afiseazaDate();}
    })
    .catch(error => {
      console.error('Eroare:', error);
    });

// Apelați funcția fetchData pentru a iniția procesul de obținere a datelor
