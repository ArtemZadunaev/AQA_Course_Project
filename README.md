# План автоматизированного тестирования покупки тура в Маракеш

## Начало работы

Cклонировать репозиторий, вполнив команду git clone https://github.com/ArtemZadunaev/AQA_Course_Project

### Prerequisites

Необходимо установить:

- IntelliJ IDEA
- Git
- Docker
- Google Chrome

### Установка и запуск

- Для запуска MySQL
1. Выполнить команду: ``` docker compose up ```
2. Выполнить команду: ``` java -jar artifacts/aqa-shop.jar```
3. Выполнить команду: ``` ./gradlew clean test ```
4. Выполнить команду: ``` ./gradlew allureReport ```
5. Выполнить команду: ``` ./gradlew allureServe ```


## Лицензия
- IntelliJ IDEA - бесплатное ПО.
- Git - бесплатное ПО.
- Docker - бесплатное ПО.
- Google Chrome - беслатное ПО.

# Документация

- [План тестирования]( ./docs/plan.md)
