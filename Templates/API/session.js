
console.log(document.cookie);

// API endpoints
const url = 'http://localhost:8081/api/session';
const url2 = 'http://localhost:8081/api/deletesession';

// Verify the session
if (document.cookie) {
    fetch(url, {
        method: 'POST',
        body: document.cookie
    })
        .then(response => response.json())
        .then(data => {

            if (data.success) {
                console.log(data);;
            }
            else {
                location.replace("Home.html");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
else location.replace("Home.html");


//LOG OUT
var logoutLink = document.getElementById("logoutLink");

// Add click event listener to the logout link
logoutLink.addEventListener("click", function (event) {
    event.preventDefault();

    //delete the current session in database
    fetch(url2, {
        method: 'POST',
        body: document.cookie
    })
        .then(response => response.json())
        .then(data => {

            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });

  //delete the cookie
  var x=document.cookie;
  document.cookie = x + ";expires=Thu, 01 Jan 1970 00:00:01 GMT";


    // send to login page
    var currentPage = window.location.pathname;
    var pathParts = currentPage.split("/");
    var lastPart = pathParts[pathParts.length - 1];
    //console.log(lastPart);

    if (lastPart === "Souvenir_personalised.html" || lastPart === "Souvenir_general.html" ){
        window.location.href = "../Home.html";
    }

    else {window.location.href = "Home.html";}
});



