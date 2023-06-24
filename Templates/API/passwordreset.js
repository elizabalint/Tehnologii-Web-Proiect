function showErrorWithTimeout(message, timeout, _colour) {
    const errorMessage = document.getElementById('errorMessage');
  
    errorMessage.textContent = message;
    errorMessage.style.display = 'block';
    errorMessage.style.color = _colour;
    errorMessage.style.fontSize = '24px';
    errorMessage.style.textAlign = 'center';
    errorMessage.style.fontWeight='600';

    setTimeout(function () {
      errorMessage.style.display = 'none';
    }, timeout);
  }
  
  const resetForm = document.getElementById('resetForm');
  
  //if you are in a session you can't access this
  //if (document.cookie) location.replace("General.html");
  
  //reset password form
  if(resetForm ) {
     resetForm.addEventListener('submit', (event) => {
      event.preventDefault(); // Prevent the default form submission
  
      const formData = new FormData(resetForm );
      const url = 'https://elizabalint.github.io:8081/api/resetpassword'; // API URL
    
  
  
      fetch(url, {
        method: 'POST',
        body: formData
      })
        .then(response => response.json())
        .then(data => {
          
          if (data.success) {
              // If request was successful          
              showErrorWithTimeout(data.message, 2000,'green');
            
            } else {
              // If request failed
              console.log('Error message:', data.message);
              showErrorWithTimeout(data.message, 2000,'red');
            }
  
        })
        .catch(error => {
          console.error('Error:', error);
        });
    });
  
  }