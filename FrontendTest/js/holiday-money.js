var button = document.querySelector(".requestButton");
var paymentFeld = document.getElementById("payment"); 
var daysFeld = document.getElementById("days"); 
var resultField = document.getElementById("resultField");
button.addEventListener('click', (evt)=>
    {
       const Http = new XMLHttpRequest();
       const url ="http://localhost:8000/holiday-money/year-payment/"+paymentFeld.value+"/for/"+daysFeld.value;
       Http.open("GET", url);
       Http.send();
       Http.onreadystatechange = (e) => {
        resultField.innerText =Http.responseText;
      }
    });
