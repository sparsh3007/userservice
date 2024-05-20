CREATE TABLE `role`
(
    uuid   BINARY(16)   NOT NULL,
    `role` VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (uuid)
);

CREATE TABLE session
(
    uuid           BINARY(16)   NOT NULL,
    token          VARCHAR(1024) NULL,
    expiry_at      datetime     NULL,
    user_uuid      BINARY(16)   NULL,
    session_status TINYINT     NULL,
    CONSTRAINT pk_session PRIMARY KEY (uuid)
);

CREATE TABLE user
(
    uuid     BINARY(16)   NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(1024) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (uuid)
);

CREATE TABLE user_roles
(
    user_uuid  BINARY(16) NOT NULL,
    roles_uuid BINARY(16) NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_uuid, roles_uuid)
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER_UUID FOREIGN KEY (user_uuid) REFERENCES user (uuid);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_uuid) REFERENCES `role` (uuid);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_uuid) REFERENCES user (uuid);