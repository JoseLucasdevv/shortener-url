CREATE TABLE url (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uid CHAR(36) NOT NULL DEFAULT (UUID()),
    short_url varchar(50) NOT NULL,
    original_url varchar(255) NOT NULL,
    expires_at timestamp NOT NULL,
    created_at timestamp NOT NULL
    );