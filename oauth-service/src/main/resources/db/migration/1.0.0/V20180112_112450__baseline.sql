CREATE TABLE OAUTH_CLIENT_TOKEN
(
  TOKEN_ID          VARCHAR(255),
  TOKEN             BYTEA,
  AUTHENTICATION_ID VARCHAR(255) PRIMARY KEY,
  USER_NAME         VARCHAR(255),
  CLIENT_ID         VARCHAR(255)
);

CREATE TABLE OAUTH_REFRESH_TOKEN
(
  TOKEN_ID       VARCHAR(255),
  TOKEN          BYTEA,
  AUTHENTICATION BYTEA
);

CREATE TABLE OAUTH_CODE
(
  CODE           VARCHAR(255),
  AUTHENTICATION BYTEA
);

CREATE TABLE OAUTH_APPROVALS
(
  USERID         VARCHAR(255),
  CLIENTID       VARCHAR(255),
  SCOPE          VARCHAR(255),
  STATUS         VARCHAR(10),
  EXPIRESAT      TIMESTAMP,
  LASTMODIFIEDAT TIMESTAMP
);

CREATE TABLE USERS
(
  USERNAME VARCHAR(50) NOT NULL PRIMARY KEY,
  PASSWORD VARCHAR(50) NOT NULL,
  ENABLED  BOOLEAN     NOT NULL
);

CREATE TABLE AUTHORITIES
(
  USERNAME  VARCHAR(50) NOT NULL,
  AUTHORITY VARCHAR(50) NOT NULL,
  CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME)
);

CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES (USERNAME, AUTHORITY);
