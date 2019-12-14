INSERT INTO users(username, password, enabled)
VALUES ('user', 'password', true);

INSERT INTO authorities(username, authority)
VALUES ('user', 'user');
