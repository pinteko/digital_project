Доброго времени суток!
Это тестовое задание для вакансии Trainee Backend Developer (Java) от компании digital chief_.
В данном проекте реализовано взаимодействие с 4-мя сущностями,
такими как Novel, Author, Student, Group.


## Зависимости:

### Spring Boot
- **Spring Boot Web:** Зависимость для создания веб-приложений с использованием Spring Boot.
- **Spring Boot Data JPA:** Зависимость для работы с базами данных и использования JPA (Java Persistence API) в Spring Boot.
- **Spring Boot Starter Test:** Зависимость для тестирования веб-приложений с использованием Spring Boot.

### Базы данных
- **PostgreSQL Driver:** Зависимость для подключения к базе данных PostgreSQL.

### Другие зависимости
- **Lombok:** Зависимость для упрощения разработки Java кода с использованием аннотаций, таких как `@Data`, `@Getter`, `@Setter`, и т.д.
- ** Java Bean Validation API:** Зависимость предоставляет поддержку для валидации данных на основе аннотаций в Java-объектах.
- **Swagger UI:** Зависимость для создания интерактивной документации API с использованием Swagger.
- **HikariCP:** Зависимость для предоставления высокопроизводительного пула соединений (connection pool) для взаимодействия с базой данных.


## Сборка и запуск
1. Клонировать репозиторий:
    git clone <https://github.com/pinteko/digital_project>
2. Перейти в директорию проекта.
3. Собрать проект:
   mvn clean install
4. Перейти в директорию target проекта:
    cd <путь_к_директории_проекта>\target
5. Запустить приложение:
   java -jar <название_jar_файла>.jar
6. Воспользоваться интерактивной документацией API от Swagger UI по адресу:
   http://localhost:8189/app/swagger-ui/index.html


## Авторы
    Kiryl Korsuk, korsukkirill@gmail.com