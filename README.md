# REST API for a habit building application.
>This application will help you introduce new healthy habits into your life. <b>Create a "trail" and every time you complete a task - update the date</b>.
A habit is like a trail, without using it, it overgrows.

In this project I realize full mechanism of user login and registration. Also Implement Role Based Action Control.

To implement it, I used `Spring Boot`, `Spring Data JPA`, `Flyway` and `Postgres` to store and migration data, `Spring Security` to implement security issues, `JWT` to communicate between server and client.

# Running
To run the application enter in the command line: `gradlew bootRun`

After project running, schema in the Database created.

## API:

### POST localhost:9966/api/v1/sign-up

Creates new user. Set the name to `Admin` to get `ROLE_ADMIN` role.
 
##### Example Input: 
```
{
    "username": "test",
    "password": "qwerty123"
}
```


### POST localhost:9966/api/v1/sign-in

Get JWT Token based on user credentials.

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
        "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY2MDAyOTE3M30.G3mISreP6oQvqSe_RYraxTE7cBiFtSJkQC7oftEy-1k",
        "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjYwMDMyMTczfQ.D3vQsVBfqvr2EcqfJfMkd9wxEkvPl98B80EVW6KT2tI"
    },
    "type": "Bearer",
    "id": 6,
    "username": "test",
    "roles": [
        "ROLE_USER"
    ]
}
```


### POST localhost:9966/api/v1/token/refresh

Get refresh JWT token.

##### Example Input: 
```Bearer {jwt}```

##### Example Response: 
```
{
    "tokens": {
        "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY2MDAyOTIxM30.x7E0cT3Gi7zzBwIduJi3CTxYo9C_vKjW1-kFGufUCHY",
        "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY2MDAyOTE3M30.G3mISreP6oQvqSe_RYraxTE7cBiFtSJkQC7oftEy-1k"
    },
    "type": "Bearer",
    "id": 6,
    "username": "test",
    "roles": [
        "ROLE_USER"
    ]
}
```


### GET localhost:9966/api/v1/profile

Request to Secure API for USER_ROLE, ADMIN_ROLE.<br>
Get your profile details.

##### Example Input: 
```Bearer {jwt}```

##### Example Response: 
```
{
    "id": 6,
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


### POST localhost:9966/api/v1/trail

Request to Secure API for USER_ROLE, ADMIN_ROLE.<br>
Create new habit.

##### Example Input: 
```
Bearer {jwt}

{
    "title": "20 minute full body"
}
```

##### Example Response: 
```
{
    "trailId": 7,
    "title": "20 minute full body",
    "update_time": "2022-08-09, 10:16 AM"
}
```


### GET localhost:9966/api/v1/trail/7

Request to Secure API for USER_ROLE, ADMIN_ROLE.<br>
Get detailed info about a {id} habit.

##### Example Input: 
```Bearer {jwt}```

##### Example Response: 
```
{
    "trailId": 7,
    "title": "20 minute full body",
    "update_time": "2022-08-09, 10:16 AM"
}
```


### PUT localhost:9966/api/v1/trail/7/edit

Request to Secure API for USER_ROLE, ADMIN_ROLE.<br>
Change the title of your {id} habit

##### Example Input: 
```
Bearer {jwt}

{
    "title": "45 min fullbody"
}
```

##### Example Response:
```
{
    "trailId": 7,
    "title": "45 min fullbody",
    "update_time": "2022-08-09, 10:16 AM"
}
```


### PUT localhost:9966/api/v1/trail/7/update_date

Request to Secure API for USER_ROLE, ADMIN_ROLE.<br>
Update the date of your {id} habit.

##### Example Input: 
```Bearer {jwt}```

##### Example Response:
```
{
    "trailId": 7,
    "title": "45 min fullbody",
    "update_time": "2022-08-09, 1:04 PM"
}
```


### DELETE localhost:9966/api/v1/trail/7/delete

Request to Secure API for USER_ROLE, ADMIN_ROLE.<br>
Delete your {id} habit.

##### Example Input: 
```Bearer {jwt}```

##### Example Response:
```{deleted}```


### PUT localhost:9966/api/v1/admin/role/addtouser

Request to Secure API for ADMIN_ROLE.<br>
Add a role to a user.

##### Example Input: 
```
Bearer {jwt}
{
    "username": "test",
    "roleName": "ROLE_ADMIN"
}
```


### PUT localhost:9966/api/v1/admin/role/removetouser

Request to Secure API for ADMIN_ROLE.<br>
Rmove a role to a user.

##### Example Input: 
```
Bearer {jwt}
{
    "username": "test",
    "roleName": "ROLE_ADMIN"
}
```
