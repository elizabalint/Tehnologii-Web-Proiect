const signupForm = document.getElementById('signupForm');

if(signupForm ) {
    signupForm .addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(signupForm);
    const url = 'http://localhost:8081/api/signup'; // API URL
  
  


    fetch(url, {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        
        if (data.success) {
            // If login was successful          
            //console.log(data);
            location.replace("General.html");
          
          } else {
            // If login failed
            // data.message, data.token, etc.
            console.log('Login failed');
            console.log('Error message:', data.message);
          }

      })
      .catch(error => {
        console.error('Error:', error);
      });
  });

}