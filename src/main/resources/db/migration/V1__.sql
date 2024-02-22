CREATE TABLE authorization
(
    id                            VARCHAR(255)  NOT NULL,
    registered_client_id          VARCHAR(255)  NOT NULL,
    principal_name                VARCHAR(255)  NOT NULL,
    authorization_grant_type      VARCHAR(255)  NOT NULL,
    authorized_scopes             TEXT DEFAULT NULL,
    attributes                    TEXT DEFAULT NULL,
    state                         VARCHAR(500)  DEFAULT NULL,
    authorization_code_value      TEXT DEFAULT NULL,
    authorization_code_issued_at  timestamp      DEFAULT NULL,
    authorization_code_expires_at timestamp     DEFAULT NULL,
    authorization_code_metadata   VARCHAR(255)  DEFAULT NULL,
    access_token_value            TEXT DEFAULT NULL,
    access_token_issued_at        timestamp      DEFAULT NULL,
    access_token_expires_at       timestamp      DEFAULT NULL,
    access_token_metadata         TEXT DEFAULT NULL,
    access_token_type             VARCHAR(255)  DEFAULT NULL,
    access_token_scopes           VARCHAR(1000) DEFAULT NULL,
    refresh_token_value           TEXT DEFAULT NULL,
    refresh_token_issued_at       timestamp      DEFAULT NULL,
    refresh_token_expires_at      timestamp      DEFAULT NULL,
    refresh_token_metadata        VARCHAR(2000) DEFAULT NULL,
    oidc_id_token_value           TEXT DEFAULT NULL,
    oidc_id_token_issued_at       timestamp      DEFAULT NULL,
    oidc_id_token_expires_at      timestamp      DEFAULT NULL,
    oidc_id_token_metadata        VARCHAR(2000) DEFAULT NULL,
    oidc_id_token_claims          VARCHAR(2000) DEFAULT NULL,
    user_code_value               TEXT DEFAULT NULL,
    user_code_issued_at           timestamp      DEFAULT NULL,
    user_code_expires_at          timestamp      DEFAULT NULL,
    user_code_metadata            VARCHAR(2000) DEFAULT NULL,
    device_code_value             TEXT DEFAULT NULL,
    device_code_issued_at         timestamp      DEFAULT NULL,
    device_code_expires_at        timestamp      DEFAULT NULL,
    device_code_metadata          TEXT DEFAULT NULL,
    CONSTRAINT pk_authorization PRIMARY KEY (id)
);

CREATE TABLE authorization_consent
(
    registered_client_id VARCHAR(255)  NOT NULL,
    principal_name       VARCHAR(255)  NOT NULL,
    authorities          VARCHAR(1000) NOT NULL,
    CONSTRAINT pk_authorizationconsent PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE client
(
    id                            VARCHAR(255)  NOT NULL,
    client_id                     VARCHAR(255)  NOT NULL,
    client_id_issued_at           timestamp      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 VARCHAR(255)  DEFAULT NULL,
    client_secret_expires_at      timestamp      DEFAULT NULL,
    client_name                   VARCHAR(255)  NOT NULL,
    client_authentication_methods VARCHAR(1000) NOT NULL,
    authorization_grant_types     VARCHAR(1000) NOT NULL,
    redirect_uris                 VARCHAR(1000) DEFAULT NULL,
    post_logout_redirect_uris     VARCHAR(1000) DEFAULT NULL,
    scopes                        VARCHAR(1000) NOT NULL,
    client_settings               VARCHAR(2000) NOT NULL,
    token_settings                VARCHAR(2000) NOT NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    `role` VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    token          VARCHAR(255)          NULL,
    expiry_at      datetime              NULL,
    user_id        BIGINT                NULL,
    session_status TINYINT              NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255)          NULL,
    password VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);