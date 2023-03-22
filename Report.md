# Отчет по тестированию 

В результате реализации мероприятий по автоматизации тестированию [сервиса](https://github.com/Evgenii083/QA-Diploma/blob/main/aqa-shop.jar), взаимодействующего с СУБД и API Банка согласно [плану тестирования](https://github.com/Evgenii083/QA-Diploma/blob/main/Test-plan.md), основанному на предварительном анализе исследовательного тестирования, с применением двух баз данных MySQL и PostgreSQL получено:

- [реализовано 30 тест кейсов](https://drive.google.com/open?id=13xrvDaK6_yUnaK7syqB7ewGQPXKgpI0D&authuser=eugeny.rybalkin%40gmail.com&usp=drive_fs)
- [успешные тест-кейсы 66.66%](https://drive.google.com/open?id=148zmFIyVACdIvBR0LNDkrFMsRb9kKRpd&authuser=eugeny.rybalkin%40gmail.com&usp=drive_fs) 
- [не успешные тест-кейсы 33.33%](https://drive.google.com/open?id=140pu0TEIfmefBtUeoEM7ve0JiJzD7Yan&authuser=eugeny.rybalkin%40gmail.com&usp=drive_fs)

## Найденные баги 
- [Неверное название страницы при открытии сервиса в браузере](https://github.com/Evgenii083/QA-Diploma/issues/6)
- [Орфографическая ошибка в названии города на главной странице](https://github.com/Evgenii083/QA-Diploma/issues/5)
- [Возможна оплата тура картой со статусом "DECLINED"](https://github.com/Evgenii083/QA-Diploma/issues/4)
- [Форма оплаты картой допускает пробелы перед именем в поле "Владелец" при покупке как в кредит так и без него](https://github.com/Evgenii083/QA-Diploma/issues/3)
- [Система не содержит никакой верификации поля "Владелец" в форме оплаты картой](https://github.com/Evgenii083/QA-Diploma/issues/2)
- [Успешная покупка по карте, при условии ввода нулевого месяца в поле срока действия карты при покупки в кредит и не в кредит.](https://github.com/Evgenii083/QA-Diploma/issues/1)

## Общие рекомендации 
- Необходимо исправить все найденные баги
- Сделать обязательную валидацию необходимых полей
