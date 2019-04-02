We would like to have a service that provides fields management for farmers. We describe how initially entities look like but you are free to adjust it based on your own vision and implementation.

- `User`. The User contains id, name, specialisation. The specialisation could be one of several predefined values. Feel free to define your own specialisations.
- `Field`. The Field contains the id, name, crop type (feel free to define your types), coordinates, field conditions ...
- Every user can have several fields, each field can be managed by several users.
- User can not have fields with the same coordinates.
- `Field Condition (FC)`. Field condition describes condition for a given field for a given day.  `FC` contains the following information: date, temperature (min, max, the average for that day), cloudiness information, vegetation information in percentage.

Based on these entities, you need to implement a REST service that supports the following operations:
- Search / Filter fields based on `field's` parameters including `field condition` and `user`. We are interested in flexible solution by any optional parameter that you can come up. `For example, search fields that have this crop type, located here, belogs to that user, and has this temperature during this period.`  Consider the case of dealing with a big amount of data.


Below pseudo code that could help you to implement you solution. Feel free to design your own data structure that could help you to implement main method.

```java
class ToImplement {
    // search fields based on parameters
    YourTypeOfChoice search(YourTypeOfChoice criteria, Object yourParameters) {
        // implement me!
    }
}

class User {
    YourTypeOfChoice id;
    YourTypeOfChoice name;
    YourTypeOfChoice specialisation;
    YourTypeOfChoice<Field> fields; // feel free to implement a proper relation between users and fields. Relation is supposed to be many-to-many
}

class Field {
    YourTypeOfChoice id;
    YourTypeOfChoice name;
    YourTypeOfChoice cropType;
    YourTypeOfChoice coordinates; // latitude and longitude
    YourTypeOfChoice<FieldCondition> fieldConditions;
    YourTypeOfChoice<User> users; // feel free to implement a proper relation between users and fields. Relation is supposed to be many-to-many

}

class FieldCondition {
    YourTypeOfChoice id;
    YourTypeOfChoice date; // day
    Field field;  // feel free to implement a proper relation between fields and field conditions
    YourTypeOfChoice temperature; // min, max, the average for that day
    YourTypeOfChoice cloudiness;
    YourTypeOfChoice vegetation;
}
```

## Tech Requirements

- Please complete code challenge in **Java** with **Spring Boot** framework.
- We love tests. Please write test for your code to show us how you approach it.
- Feel free to use any supporting frameworks to show your experience and knowledge and simplify your work.
- The delivery should contain the application and a README file that says at least how to build and run a project. Other documentation (e.g. API) is appreciated.
- You can add to README your notes and assumptions that you made during the development if you think it would help us to see your motivation around technical decisions.

Some other requirements, which are obvious but listed here explicitly, in order to point out importance.

- Your REST service ​has​ ​to​ ​handle concurrent​ ​requests in a threadsafe manner
- Your REST service should function correctly and produce meaningful output, e.g. in JSON format
- Your ​project​ ​should​ ​be​ ​buildable,​ ​and​ ​tests​ ​should​ ​also​ ​complete​ ​successfully

## Overall tasks
1. Implement REST Service according to the tech task.
2. Write a README that explains how to use your service.
3. **Time boxed: 120 minutes.**

## Objectives
We like to see how you work, understand your approach of problem solving, the assumptions and trade-offs explicitly taken.

## Running 
To test, type mvn clean test

To run, type mvn clean install and then java -jar target/atfarm-0.0.1-SNAPSHOT.jar


Java 12,
Swagger