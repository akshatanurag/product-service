CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    create_at  datetime NULL,
    updated_at datetime NULL,
    is_delete  BIT(1) NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    create_at     datetime NULL,
    updated_at    datetime NULL,
    is_delete     BIT(1) NULL,
    title         VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id   BIGINT NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);