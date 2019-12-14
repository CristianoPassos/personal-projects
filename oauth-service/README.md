# OAuth2 

This project holds an OAuth2 Authorization Server with the following characteristics:

- Access Tokens are stored in a Cache (Redis)
- Refresh Tokens are stored in DataBase (H2)
- Flyway is used for database migration
- OAuth Clients are externalized in application.yml
- It uses Bcrypt as an encryption mechanism.  
- I have attached an in-memory Redis cache that auto starts.



