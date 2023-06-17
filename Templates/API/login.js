
const loginForm = document.getElementById('loginForm');

if(loginForm) {
  loginForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(loginForm);
    const url = 'http://localhost:8081/api/login'; // API URL



    fetch(url, {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        // console.log(data); // Process the response data

        if (data.success) {
            // If login was successful
            //console.log('Login successful');
            console.log(data);
            //location.replace("General.html");
            
            // You can access other properties from the data object as needed
            // For example: data.message, data.token, etc.
          } else {
            // If login failed
            console.log('Login failed');
            console.log('Error message:', data.message);
          }

      })
      .catch(error => {
        console.error('Error:', error);
      });
  });

}

