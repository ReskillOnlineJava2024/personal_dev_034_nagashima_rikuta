-- 各種テーブル削除
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS recipes;

-- categories（カテゴリー）
CREATE TABLE categories
(
id SERIAL PRIMARY KEY,
category_name VARCHAR(20)
);

-- users（ユーザー）
CREATE TABLE users
(
id SERIAL PRIMARY KEY,
user_name VARCHAR(50),
password VARCHAR(50)
);

-- recipes（レシピ）
CREATE TABLE recipes
(
id SERIAL PRIMARY KEY,
category_id INTEGER,
name VARCHAR(50),
recipe_name VARCHAR(30),
materials VARCHAR(200), 
contents VARCHAR(500)
);