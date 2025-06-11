DROP DATABASE IF EXISTS E4;

-- データベース作成と使用
CREATE DATABASE IF NOT EXISTS E4;
USE E4;

-- ユーザーテーブル
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO users (email, password) VALUES
  ('test@example.com', 'password');

-- 気分記録テーブル
DROP TABLE IF EXISTS mood_records;
CREATE TABLE mood_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    record_date DATE NOT NULL,
    mood INT NOT NULL,
    comment VARCHAR(140),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 架空のデータ、CURDATEはその日の日付を返す	
INSERT INTO mood_records (user_id, record_date, mood, comment)
VALUES 
(1, CURDATE(), 3, '昼頃から少し気分が落ちた'),
(1, CURDATE(), 4, 'コーヒーを飲んで回復した'),
(2, CURDATE(), 2, '疲れが溜まっている感じ');

-- ご褒美マスターテーブル
DROP TABLE IF EXISTS rewards_collection;
CREATE TABLE rewards_collection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gacha_item VARCHAR(100) NOT NULL,
    gacha_rarity INT NOT NULL
); 

INSERT INTO rewards_collection (gacha_item, gacha_rarity) VALUES
('早く寝ましょう', 3),
('アイマスクを使おう', 3),
('入浴剤を使ってみよう', 3),
('今日は何もしない', 3),
('アロマや香りのミストを使いましょう', 3),
('首の後ろを温める', 3),
('2,000円分自分の好きなものを買う', 3),

('アイスを食べる', 2),
('お惣菜にしよう', 2),
('プリンを買う', 2),
('好きな動画やアニメを見る', 2),
('散歩に出かける', 2),
('軽いストレッチをする', 2),
('贅沢なパンを買う', 2),
('1,000円分自分好きなものを買う', 2),

('一人呑み', 1),
('入ったことのないお店に挑戦', 1),
('簡単な新しい料理に挑戦', 1),
('500円分自分の好きなものを買う', 1);

-- ご褒美実績テーブル
DROP TABLE IF EXISTS rewards_result;
CREATE TABLE rewards_result (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    gacha_time DATE NOT NULL,
    gacha_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (gacha_id) REFERENCES rewards_collection(id)
);
