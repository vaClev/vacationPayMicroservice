Микросервис на spring --
Возвращает размер отпускных по известной годовой зарплате и чилсу дней
--
реализованы два способа обращения

1. по ивестному кол-ву ополачиваемых дней
   GET "http://localhost:8000/holiday-money/year-payment/"+payment+"/for/"+days
   например http://localhost:8000/holiday-money/year-payment/1500000/for/14

2. По датам начала и окончания отпуска
   GET "http://localhost:8000/holiday-money/year-payment/"+paymentFeld.value+"/firstDay/"+first +"/lastDay/"+lastday;
   например http://localhost:8000/holiday-money/year-payment/1500/firstDay/2024-09-30/lastDay/2024-10-30
   не оплачиваются празничные дни отмеченные в рабочем календаре -- доступ к ним через класс PublicHolidaysRepository
