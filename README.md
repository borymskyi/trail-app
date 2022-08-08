# REST API for a habit building application.
In this project I realize full mechanism of user login and registration. Also Implement Role Based Action Control.

To implement it, I used `Spring Boot`, `Spring Data JPA`, `Flyway` and `Postgres` to store and migration data, `Spring Security` to implement security issues, `JWT` to communicate between server and client. 

# Running
To run the application enter in the command line: `gradlew bootRun`

After project running, schema in the Database created.

# Sign-up
`POST localhost:9966/api/v1/sign-up`
```
{
    "username": "test",
    "password": "qwerty123"
}
```

# Sign-in. Get JWT Token
`POST localhost:9966/api/v1/sign-in`
```
{
    "username": "test",
    "password": "qwerty123"
}
```

# Make Request to Secure API for USER_ROLE
`GET localhost:9966/api/v1/profile`
```Bearer {jwt}```
