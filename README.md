# Дипломный проект профессии «Тестировщик»
[Описание задания](https://github.com/netology-code/qa-diploma)

[План автоматизации](https://github.com/Evgenii083/QA-Diploma/blob/main/Test-plan.md)

[Отчет по итогам тестирования](https://github.com/Evgenii083/QA-Diploma/blob/main/Report.md)

[Отчет по итогам автоматизации](https://github.com/Evgenii083/QA-Diploma/blob/main/Automation%20report.md)


# Инструкция по запуску

1) Склонировать репозиторий
git clone https://github.com/Evgenii083/QA-Diploma.git

2) Открыть в IntelliJ IDEA (или другой среде разработки)

3) Запустить контейнеры docker:

`docker-compose up` 

3) Запустить приложение:

Для запуска приложения с базой данных mysql выполнить команду:
`java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar` 

Для запуска приложения с базой данных postgres выполнить команду:
` java -Dspring.datasource.url=jdbc:postgresql://localhost:5431/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar
`

4) Запустить тесты:

Для запуска тестов с базой данных mysql выполнить команду:
`gradlew test -Dbd.url=jdbc:mysql://localhost:3306/app`

Для запуска тестов с базой данных postgres выполнить команду:
`gradlew test -Dbd.url=jdbc:postgres://localhost:5431/app`

5) Отчетность 

Сформировать отчеты командой:
`gradlew allureReport`

Открыть отчеты в браузере командой:
`gradlew allureServe`

