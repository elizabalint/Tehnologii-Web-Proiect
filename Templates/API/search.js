const searchI = document.getElementById('searchI');

 
const url = 'http://localhost:8081/api/souvenir'; // API URL


    fetch(url, {
        method: 'GET',
    })
      .then(response => response.json())
      .then(data  => {
        /* 

        if (data.success){
          console.log('data merge');
        console.log(data);
        const keys = Object.keys(data);
        keys.forEach((key) => {
        const value = data[key];
        console.log('Valoare:', value);

        })}
        else{
          console.log('data nu merge');
            console.log('Error message:', data.message);
        }
    }) */
          // Manipulați datele primite în răspuns
    // Manipulați datele primite în răspuns
    const souvenirsList = document.getElementById('souvenirs-list');
    const keys = Object.keys(data);
    console.log(data);
    keys.forEach(key => {
      console.log(key);
      const souvenir = data[key];
      console.log(souvenir);

      const souvenirElement = document.createElement('h1');
      souvenirElement.textContent = souvenir.name;
      console.log(souvenir.name);
      souvenirsList.appendChild(souvenirElement);
      console.log(souvenirElement);

    });
  })
      .catch(error => {
        console.error('Error:', error);
        console.log("error");
      });
