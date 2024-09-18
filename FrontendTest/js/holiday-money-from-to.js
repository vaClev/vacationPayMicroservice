var button = document.querySelector(".requestButtonFromTo");
var paymentFeld = document.getElementById("payment2"); 
var firstday = document.getElementById("firstday"); 
var lastday = document.getElementById("lastday"); 
var resultField = document.getElementById("resultField2");


button.addEventListener('click', (evt)=>
    {
       const Http = new XMLHttpRequest();
       const url ="http://localhost:8000/holiday-money/year-payment/"+paymentFeld.value+"/firstDay/"+firstday.value +"/lastDay/"+lastday.value;
       Http.open("GET", url);
       Http.send();
       Http.onreadystatechange = (e) => {
        resultField.innerText = Http.responseText;
      }
    });
