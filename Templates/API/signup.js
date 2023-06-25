function showErrorWithTimeout(message, timeout) {
  const errorMessage = document.getElementById('errorMessage');

  errorMessage.textContent = message;
  errorMessage.style.display = 'block';

  setTimeout(function () {
    errorMessage.style.display = 'none';
  }, timeout);
}

const signupForm = document.getElementById('signupForm');

//if you are in a session you can't access this
//if (document.cookie) location.replace("General.html");

//signup form
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
            // If signup was successful          
            
            //create cookie
            var expirationDate = new Date();
            expirationDate.setDate(expirationDate.getDate() + 1);
            document.cookie = "session=" + data.message + "; expires=" + expirationDate.toUTCString();

            //redirect
            window.location.href = "General.html";
          
          } else {
            // If signup failed
            
            console.log('SignUp failed');
            console.log('Error message:', data.message);
            showErrorWithTimeout(data.message, 3000);
          }

      })
      .catch(error => {
        console.error('Error:', error);
      });
  });

}