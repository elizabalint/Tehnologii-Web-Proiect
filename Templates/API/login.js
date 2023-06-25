
function showErrorWithTimeout(message, timeout) {
  const errorMessage = document.getElementById('errorMessage');

  errorMessage.textContent = message;
  errorMessage.style.display = 'block';

  setTimeout(function () {
    errorMessage.style.display = 'none';
  }, timeout);
}

const loginForm = document.getElementById('loginForm');
const usernameInput = document.getElementById('username');
const rememberCheckbox = document.getElementById('rememberCheckbox');

//If the username was remembered
if (localStorage.getItem('remembered') === 'true') {
  rememberCheckbox.checked = true;
  usernameInput.value = localStorage.getItem('rememberedUsername');
}

//if you are in a session you can't access this
//if (document.cookie) location.replace("General.html");


//login form 
if (loginForm) {
  loginForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(loginForm);
    const url = 'http://localhost:8081/api/login'; // API URL
    const username = usernameInput.value;

    fetch(url, {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {


        if (data.success) {
          // If login was successful  

          // Check if the "Remember me" checkbox is checked
          if (rememberCheckbox.checked) {
            // Store the username and the "remembered" flag in localStorage
            localStorage.setItem('rememberedUsername', username);
            localStorage.setItem('remembered', 'true');

          } else {
            // Clear the stored username and the "remembered" flag from localStorage
            localStorage.removeItem('rememberedUsername');
            localStorage.removeItem('remembered');

          }

          //create session cookie and go to general.html page
          var expirationDate = new Date();
          expirationDate.setDate(expirationDate.getDate() + 1);
          document.cookie = "session=" + data.message + "; expires=" + expirationDate.toUTCString();

          //redirect according to the admin value
          if (data.admin === true) window.location.href = "AdminPage.html";
          else window.location.href = "General.html";



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

  // loginForm.reset();
}

