# Currency Exchange Service

## Описание

Backend-сервис для работы с валютными операциями:
- Хранение и отображение курсов валют (имя, стоимость, номинал, ISO-коды)
- Автоматическое обновление информации о валютах из Центрального банка РФ (XML)
- API для получения информации о всех валютах, валюте по ID и конвертации

## Технологии

- Java 17
- Spring Boot
- Hibernate (JPA)
- Liquibase
- PostgreSQL
- Lombok
- JAXB
- MapStruct
- Maven/Gradle

## Быстрый запуск

1. Клонируйте проект  
   `git clone https://github.com/skillbox-java/p3_currency_exchange.git`

2. Запустите PostgreSQL (можно через Docker):  
   `docker run --name currency-db -e POSTGRES_PASSWORD=yourpassword -p 5432:5432 -d postgres`

3. Настройте параметры в `application.yml`:
spring:
datasource:
url: jdbc:postgresql://localhost:5432/currency_db
username: postgres
password: yourpassword
cbr:
url: https://www.cbr-xml-daily.ru/daily_utf8.xml

text

4. Запустите приложение через IDE (main-класс) или Maven/Gradle:  
`mvn spring-boot:run`  
или  
`gradle bootRun`  

## Основные эндпоинты

- Получить все валюты  
`GET /api/currency`  
Ответ:
{
"currencies": [
{"name": "Доллар США", "value": 93.2801},
...
]
}

text

- Получить валюту по ID  
`GET /api/currency/{id}`

- Конвертировать сумму  
`GET /api/currency/convert?value=100&numCode=840`

- Добавить валюту  
`POST /api/currency/create`

## Автоматическое обновление валют

- Курсы валют из ЦБ РФ загружаются каждый час с помощью планировщика (`@Scheduled`).
- Используются JAXB для парсинга XML и Stream API для обработки коллекций.
- Источник данных настраивается в `application.yml`.

## Примечания

- Для изменений структуры таблицы используется Liquibase (миграции в `db/changelog`).
- Логирование всех операций обновления — через Slf4j.
- Код написан по Google Java Style Guide.
