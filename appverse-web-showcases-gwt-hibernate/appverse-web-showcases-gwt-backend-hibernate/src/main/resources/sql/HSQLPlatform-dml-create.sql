-- Roles
INSERT INTO ROLE (ID, CREATED, CREATED_BY, NAME, STATUS, ACTIVE, UPDATED, UPDATED_BY, VERSION) VALUES (1, current_date, '', 'ROLE_ADMIN', 'a', 1, current_date, '', 1);
INSERT INTO ROLE (ID, CREATED, CREATED_BY, NAME, STATUS, ACTIVE, UPDATED, UPDATED_BY, VERSION) VALUES (2, current_date, '', 'ROLE_PUBLIC', 'a', 1, current_date, '', 1);
UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 2 WHERE SEQ_NAME = 'ROLE_SEQ';

--Users
INSERT INTO USER(ID, CREATED, CREATED_BY, EMAIL, LASTNAME, NAME, PASSWORD, ACTIVE, UPDATED, UPDATED_BY, VERSION, STATUS) VALUES (1, current_date, 'GWTShowcase', 'test@email.com', '', 'Surname', 'Name', true, current_date, 'GWTShowcase', 1, 'a');
INSERT INTO USER(ID, CREATED, CREATED_BY, EMAIL, LASTNAME, NAME, PASSWORD, ACTIVE, UPDATED, UPDATED_BY, VERSION, STATUS) VALUES (2, current_date, 'GWTShowcase', 'test2@email.com', '', 'Surname2', 'Name2', true, current_date, 'GWTShowcase', 1, 'a');
UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 2 WHERE SEQ_NAME = 'USER_SEQ';

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 2);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 2);