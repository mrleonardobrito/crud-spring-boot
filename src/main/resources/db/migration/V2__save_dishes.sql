INSERT INTO category(id, name) VALUES 
(1, 'Refeições'), 
(2, 'Sobremesas'), 
(3, 'Bebidas');

INSERT INTO ingredient(id, name) VALUES
(1, 'alface'),
(2, 'cebola'),
(3, 'pão naan'),
(4, 'pepino'),
(5, 'rabanete'),
(6, 'tomate');

INSERT INTO dish(id, name, description, price, category_id) VALUES 
(1, 'Salada Ravanello', 'Rabanetes, folhas verdes e molho agridoce salpicados com gergelim. O pão naan dá um toque especial.', 49.97, 1);

INSERT INTO dish_ingredients(dish_id, ingredient_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6);

