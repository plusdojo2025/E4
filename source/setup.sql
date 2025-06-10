-- データベース作成と使用
CREATE DATABASE IF NOT EXISTS E4;

USE E4;

-- ユーザーテーブル
CREATE TABLE users (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- 気分記録テーブル
CREATE TABLE mood_records (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    record_date DATE NOT NULL,
    mood INT NOT NULL,
    comment VARCHAR(140),
    time TIME,
    FOREIGN KEY (user_id) REFERENCES users(ID)
);

-- ご褒美マスターテーブル
CREATE TABLE rewards_collection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gacha_low VARCHAR(100) NOT NULL,
    gacha_mid VARCHAR(100) NOT NULL,
    gacha_high VARCHAR(100) NOT NULL
);

-- ご褒美実績テーブル
CREATE TABLE rewards_result (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    gacha_time DATE NOT NULL,
    gacha_type VARCHAR(50) NOT NULL,
    gacha_number INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(ID),
    FOREIGN KEY (gacha_number) REFERENCES rewards_collection(id)
);
