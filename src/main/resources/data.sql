-- categories テーブルにデータを挿入
INSERT INTO categories (category_name) VALUES('和食');
INSERT INTO categories (category_name) VALUES('洋食');
INSERT INTO categories (category_name) VALUES('中華');
INSERT INTO categories (category_name) VALUES('その他');


-- users テーブルにデータを挿入
INSERT INTO users (user_name, password) VALUES ( '匿名太郎', 'test123');
INSERT INTO users (user_name, password) VALUES ( '匿名一郎', 'test456');
INSERT INTO users (user_name, password) VALUES ( '匿名二郎', 'test456');


-- recipes テーブルにデータを挿入
INSERT INTO recipes (category_id, name, recipe_name, materials, contents) VALUES(2, '匿名太郎', 'ハンバーグ', 'ひき肉、玉ねぎ、パン粉、牛乳、塩、コショウ','材料を混ぜて、捏ねて、焼く');
INSERT INTO recipes (category_id, name, recipe_name, materials, contents) VALUES(1, '匿名一郎', '寿司', '  刺身、米、お酢、砂糖、塩','米、お酢、砂糖、塩を混ぜて酢飯を作り、刺身と握る');
INSERT INTO recipes (category_id, name, recipe_name, materials, contents) VALUES(3, '匿名一郎', 'チャーハン', '  米、卵、かにかま、ごま油、醤油','油をフライパンに入れ、卵、米、かにかまを入れる。お好みで醤油や塩を入れて味を整える');
INSERT INTO recipes (category_id, name, recipe_name, materials, contents) VALUES(4, '匿名二郎', 'ピーマンの肉詰め', '  ピーマン、ひき肉、塩','ピーマンを半分に切り、種を抜く。ピーマンにひき肉を詰めて、塩で味を調えてオーブンで蒸し焼きする。');
INSERT INTO recipes (category_id, name, recipe_name, materials, contents) VALUES(4, '匿名太郎', 'ハイボール', '  ウイスキー（お好みで焼酎）、炭酸水、氷',E'360ccのグラスに氷をたっぷり入れ、マドラーでグラスのフチに沿うように氷をまわし、グラスを冷やす。\r\n グラスを逆さまにして、マドラーで氷をおさえながら溶けた水を捨て、氷をたっぷり入れる。 ウイスキーを入れて、軽く混ぜる。グラスのフチに沿って、静かに炭酸水をそそぐ。 マドラーでくるっと1周だけ混ぜる');
INSERT INTO recipes (category_id, name, recipe_name, materials, contents) VALUES(1, '匿名太郎', '焼き鳥', '  鳥肉、醤油、砂糖、みりん','醤油、砂糖、みりんを煮詰め、たれを作る。鶏肉を焼き、お好みでたれをかけつつ焼く');