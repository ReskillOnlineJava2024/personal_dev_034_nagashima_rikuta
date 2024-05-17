-- categories テーブルにデータを挿入
INSERT INTO categories (category_name) VALUES('和食');
INSERT INTO categories (category_name) VALUES('洋食');
INSERT INTO categories (category_name) VALUES('中華');
INSERT INTO categories (category_name) VALUES('その他');


-- users テーブルにデータを挿入
INSERT INTO users (user_name, password) VALUES ( '田中太郎', 'test123');
INSERT INTO users (user_name, password) VALUES ( '鈴木一郎', 'test456');


-- recipes テーブルにデータを挿入
INSERT INTO recipes (category_id, user_id, recipe_name, materials, contents) VALUES(2, 1, 'ハンバーグ', 'ひき肉、玉ねぎ、パン粉、牛乳、塩、コショウ','材料を混ぜて、捏ねて、焼く');
INSERT INTO recipes (category_id, user_id, recipe_name, materials, contents) VALUES(1, 1, '寿司', '刺身、米、お酢、砂糖、塩','米、お酢、砂糖、塩を混ぜて酢飯を作り、刺身と握る');