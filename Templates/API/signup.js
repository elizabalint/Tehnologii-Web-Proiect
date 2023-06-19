function showErrorWithTimeout(message, timeout) {
  const errorMessage = document.getElementById('errorMessage');

  errorMessage.textContent = message;
  errorMessage.style.display = 'block';

  setTimeout(function () {
    errorMessage.style.display = 'none';
  }, timeout);
}

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
            
            document.cookie = "session="+data.message; 
           // alert(document.cookie);          
            //location.replace("General.html");
            window.location.href = "General.html";
            //console.log(data.message);
          
          } else {
            // If login failed
            
            console.log('Login failed');
            console.log('Error message:', data.message);
            showErrorWithTimeout(data.message, 3000);
          }

      })
      .catch(error => {
        console.error('Error:', error);
      });
  });

}