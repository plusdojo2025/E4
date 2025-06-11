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

  
-- ご褒美マスターテーブル
DROP TABLE IF EXISTS rewards_collection;
CREATE TABLE rewards_collection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gacha_item VARCHAR(100) NOT NULL,
    gacha_rarity INT NOT NULL
); 

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

DELETE FROM mood_records
WHERE record_date = CURDATE();

-- 架空のデータ、CURDATEはその日の日付を返す	
INSERT INTO mood_records (user_id, record_date, mood, comment)
VALUES 
(1, CURDATE(), 3, '昼頃から少し気分が落ちた'),
(1, CURDATE(), 4, 'コーヒーを飲んで回復した'),
(2, CURDATE(), 2, '疲れが溜まっている感じ');

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
