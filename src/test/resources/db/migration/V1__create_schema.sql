CREATE TABLE IF NOT EXISTS category(
    id BIGSERIAL NOT NULL
        CONSTRAINT pk_category PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS dish(
    id BIGSERIAL NOT NULL
        CONSTRAINT pk_dish PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    price FLOAT NOT NULL,
    category_id BIGINT NOT NULL
        CONSTRAINT fk_dish_category REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS ingredient(
    id BIGSERIAL NOT NULL
        CONSTRAINT pk_ingredients PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS dish_ingredients(
    dish_id BIGSERIAL NOT NULL
        CONSTRAINT fk_dish_ingredients_dish REFERENCES dish(id),
    ingredient_id BIGSERIAL NOT NULL
        CONSTRAINT fk_dish_ingredients_ingredient REFERENCES ingredient(id)
);

