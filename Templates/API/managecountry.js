function updateVisitedCountries() {
    const visitedCountriesElement = document.getElementById('visitedCountries');
    
    var formData = new FormData();
    const url = 'http://localhost:8081/api/numberofcountries';
    formData.append('session', document.cookie);

    //convert data to json
    var jsonData = {};
    formData.forEach(function (value, key) {
        jsonData[key] = value;
    });
    var jsonPayload = JSON.stringify(jsonData);
   
    //get the nr from the server
    fetch(url, {
        method: 'POST',
        body: jsonPayload,
    })
      .then(response => response.json())
      .then(data => {
       
        const visitedCountries = data.message; 
        visitedCountriesElement.textContent = visitedCountries;
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }

//First Call of the function
updateVisitedCountries();

// ADD and Remove buttons
document.querySelector('.search-bar').addEventListener('submit', function (event) {
    event.preventDefault();

    const addButton = document.getElementById('Add');
    const removeButton = document.getElementById('Remove');
    var searchInput = document.getElementById('searchInput').value;
    var url;

    //Add button
    if (event.submitter === removeButton) url = 'http://localhost:8081/api/removecountry';
    else if (event.submitter === addButton) url = 'http://localhost:8081/api/addcountry';

    //create data 
    var formData = new FormData();
    formData.append('country', searchInput);
    formData.append('session', document.cookie);

    //convert data to json
    var jsonData = {};
    formData.forEach(function (value, key) {
        jsonData[key] = value;
    });
    var jsonPayload = JSON.stringify(jsonData);


    fetch(url, {
        method: 'POST',
        body: jsonPayload,
    })
        .then(response => response.json())
        .then(data => {

            console.log(data);
            updateVisitedCountries();

        })
        .catch(error => {
            console.error('Error:', error);
        });

});








