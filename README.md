# REST API for a habit building application.
In this project I realize full mechanism of user login and registration. Also Implement Role Based Action Control.

To implement it, I used `Spring Boot`, `Spring Data JPA`, `Flyway` and `Postgres` to store and migration data, `Spring Security` to implement security issues, `JWT` to communicate between server and client. 

# Running
To run the application enter in the command line: `gradlew bootRun`

After project running, schema in the Database created.

## API:

### POST localhost:9966/api/v1/sign-up

Creates new user
 
##### Example Input: 
```
{
    "username": "test",
    "password": "qwerty123"
}
```


### POST localhost:9966/api/v1/sign-in

Get JWT Token based on user credentials

##### Example Input: 
```
{
    "username": "test",
    "password": "qwerty123"
}
```

##### Example Response: 
```
{
    "tokens": {
        "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY1OTk1ODI1MH0.ELK-UQ_1T9Q7diOwgaLq76gEVM3SALI5wNzAKos0X_A",
        "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjU5OTYxMjUwfQ.wx3Qz5jzyob9Zj7cwcPvp1gIpFyjDA9aJHQjTvCrTQo"
    },
    "type": "Bearer",
    "id": 5,
    "username": "test",
    "roles": [
        "ROLE_USER"
    ]
}
```


### GET localhost:9966/api/v1/profile

Make Request to Secure API for USER_ROLE.

##### Example Input: 
```Bearer {jwt}```

##### Example Response: 
```
{
    "id": 5,
    "username": "test",
    "trails": [],
    "roles": [
        {
            "roleId": 1,
            "name": "ROLE_USER"
        }
    ]
}
```
