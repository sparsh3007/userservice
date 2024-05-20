CREATE TABLE authorization
(
    id                            VARCHAR(255)  NOT NULL,
    registered_client_id          VARCHAR(255)  NULL,
    principal_name                VARCHAR(255)  NULL,
    authorization_grant_type      VARCHAR(255)  NULL,
    authorized_scopes             TEXT DEFAULT NULL,
    attributes                    TEXT DEFAULT NULL,
    state                         VARCHAR(500) DEFAULT NULL,
    authorization_code_value      TEXT NULL,
    authorization_code_issued_at  timestamp DEFAULT NULL,
    authorization_code_expires_at timestamp DEFAULT NULL,
    authorization_code_metadata   TEXT DEFAULT NULL,
    access_token_value            TEXT DEFAULT NULL,
    access_token_issued_at        timestamp DEFAULT NULL,
    access_token_expires_at       timestamp DEFAULT NULL,
    access_token_metadata         TEXT DEFAULT NULL,
    access_token_type             VARCHAR(255) DEFAULT NULL,
    access_token_scopes           VARCHAR(1000) DEFAULT NULL,
    refresh_token_value           TEXT DEFAULT NULL,
    refresh_token_issued_at       timestamp DEFAULT NULL,
    refresh_token_expires_at      timestamp DEFAULT NULL,
    refresh_token_metadata        TEXT DEFAULT NULL,
    oidc_id_token_value           TEXT DEFAULT NULL,
    oidc_id_token_issued_at       timestamp DEFAULT  NULL,
    oidc_id_token_expires_at      timestamp DEFAULT NULL,
    oidc_id_token_metadata        VARCHAR(2000) DEFAULT NULL,
    oidc_id_token_claims          VARCHAR(2000) DEFAULT NULL,
    user_code_value               TEXT DEFAULT NULL,
    user_code_issued_at           timestamp DEFAULT NULL,
    user_code_expires_at          timestamp DEFAULT NULL,
    user_code_metadata            VARCHAR(2000) DEFAULT NULL,
    device_code_value             VARCHAR(4000) DEFAULT NULL,
    device_code_issued_at         timestamp DEFAULT NULL,
    device_code_expires_at        timestamp DEFAULT NULL,
    device_code_metadata          VARCHAR(2000) DEFAULT NULL,
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
    client_id                     VARCHAR(255)  NULL,
    client_id_issued_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 VARCHAR(255)  NULL,
    client_secret_expires_at      timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_name                   VARCHAR(255)  NULL,
    client_authentication_methods VARCHAR(1000) NULL,
    authorization_grant_types     VARCHAR(1000) NULL,
    redirect_uris                 VARCHAR(1000) NULL,
    post_logout_redirect_uris     VARCHAR(1000) NULL,
    scopes                        VARCHAR(1000) NULL,
    client_settings               VARCHAR(2000) NULL,
    token_settings                VARCHAR(2000) NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);
