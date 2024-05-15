CREATE TABLE IF NOT EXISTS `product`
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    version     BIGINT,
    name        VARCHAR(255),
    description TEXT,
    price       DOUBLE,
    quantity    INT,
    category    VARCHAR(255),
    brand       VARCHAR(255),
    image       VARCHAR(255),
    status      VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
