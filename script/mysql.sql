CREATE  TABLE sys_users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE sys_user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES sys_users (username));
/*admin 123456*/
INSERT INTO sys_users(username,password,enabled) VALUES ('admin','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
/*user 123456*/
INSERT INTO sys_users(username,password,enabled) VALUES ('user','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO sys_user_roles (username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO sys_user_roles (username, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO sys_user_roles (username, role) VALUES ('user', 'ROLE_USER');