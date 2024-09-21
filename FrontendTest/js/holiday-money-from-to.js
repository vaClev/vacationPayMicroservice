var button2 = document.querySelector(".requestButtonFromTo");
var salaryFeld2 = document.getElementById("payment2"); 
var firstday = document.getElementById("firstday"); 
var lastday = document.getElementById("lastday"); 
var resultField2 = document.getElementById("resultField2");


button2.addEventListener('click', (evt)=>
    {
       const Http = new XMLHttpRequest();
       const url ="http://localhost:8000/holiday-money/averageSalary/"+salaryFeld2.value+"/firstDay/"+firstday.value +"/lastDay/"+lastday.value;
       Http.open("GET", url);
       Http.send();
       Http.onreadystatechange = (e) => {
        resultField2.innerText = Http.responseText;
      }
    });
