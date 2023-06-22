// Declare the map variable globally
var map;

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
      svgElement.style.transform = "scale(0.65)";
      svgElement.style.transformOrigin = "-15% 22%";

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



//
// var map = document.getElementById("map");
// var zoomedIn = false;

// map.addEventListener("click", function() {
//   if (zoomedIn) {
//     map.style.transform = "scale(1)";
//     zoomedIn = false;
//   } else {
//     map.style.transform = "scale(2)";
//     zoomedIn = true;
//   }
// });






