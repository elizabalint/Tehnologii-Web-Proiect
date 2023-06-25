
// Function to change the svg map colors
function changePathColors(idsToChange, classNamesToChange, color) {
  var paths = document.querySelectorAll('path');

  for (var i = 0; i < paths.length; i++) {
    var path = paths[i];
    var id = path.getAttribute('id');
    var classList = path.getAttribute('class');

    var hasId = idsToChange.includes(id);
    var hasClass = false;

    if (classList) {
      var classes = classList.split(' ');
      for (var j = 0; j < classes.length; j++) {
        if (classNamesToChange.includes(classes[j])) {
          hasClass = true;
          break;
        }
      }
    }

    if (hasId || hasClass) {
      path.style.fill = color;
    }
  }
}

function loadAndModifyMapColors() {
  var map = document.getElementById("map"); // Assign the map element to the globally declared variable

  // Load the SVG file
  fetch("SVG/world.svg")
    .then(response => response.text())
    .then(svgData => {
      map.innerHTML = svgData;

      // Modify the SVG proportions
      var svgElement = map.querySelector("svg");

      //svgElement.style.transform = "scale(0.65)";
      svgElement.style.transform =  "scale(0.65)";
      svgElement.style.transformOrigin = "-15% 22%";

      //Zoom listener
      var isZoomedIn = false;
      svgElement.addEventListener("click", function (event) {
        var svgRect = svgElement.getBoundingClientRect();
        var offsetX = (event.clientX - svgRect.left) / svgRect.width;
        var offsetY = (event.clientY - svgRect.top) / svgRect.height;

        if (!isZoomedIn) {
          // Zoom in
          var zoomFactor = 1.5;
          var newScale = zoomFactor * parseFloat(svgElement.style.transform.replace("scale(", "").replace(")", ""));
          var translateX = (1 - zoomFactor) * offsetX * 100;
          var translateY = (1 - zoomFactor) * offsetY * 100;
          svgElement.style.transform = `scale(${newScale}) translate(${translateX}%, ${translateY}%)`;
          isZoomedIn = true;
        } else {
          // Zoom out
          svgElement.style.transform = "scale(0.65)";
          isZoomedIn = false;
        }
      }); 
     
      //Show Country name
      var pathNameDisplay = document.getElementById('pathNameDisplay');
      svgElement.addEventListener('mouseover', function(event) {
        var path = event.target;
        var className = path.getAttribute('class');
        var name = path.getAttribute('name');
        var final;
        
        if(name)  final=name;
        else if(className) final=className;
        else final='';

        pathNameDisplay.textContent = final;

      });
      
      

      // We need to use the session
      var formData = new FormData();
      const url = 'http://localhost:8081/api/mapcolors';
      formData.append('session', document.cookie);

      // Convert data to JSON
      var jsonData = {};
      formData.forEach(function (value, key) {
        jsonData[key] = value;
      });
      var jsonPayload = JSON.stringify(jsonData);

      // Update colors
      fetch(url, {
        method: 'POST',
        body: jsonPayload
      })
        .then(response => response.json())
        .then(data => {
          // Name inversion
          var classNamesToChange = data.classNamesToChange;
          var idsToChange = data.idsToChange;

          // Call the function to change path colors
          changePathColors(idsToChange, classNamesToChange, '#49ff65');
        })
        .catch(error => {
          console.error('Error fetching map colors:', error);
        });
    })
    .catch(error => {
      console.log("Error loading SVG:", error);
    });
}


//function to observe country change in order to update the map
function watchVisitedCountries(callback) {
  var visitedCountriesElement = document.getElementById('visitedCountries');

  var observer = new MutationObserver(function(mutations) {
    mutations.forEach(function(mutation) {
      if (mutation.type === 'childList') {
        callback();
      }
    });
  });

  var config = { childList: true };
  observer.observe(visitedCountriesElement, config);
}


// Load the svg first time
window.addEventListener("DOMContentLoaded", function () {
  loadAndModifyMapColors();


});

//Load the svg when there is a change
watchVisitedCountries(loadAndModifyMapColors);

