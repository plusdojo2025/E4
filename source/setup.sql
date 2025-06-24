DROP DATABASE IF EXISTS E4;

-- データベース作成と使用
CREATE DATABASE IF NOT EXISTS E4;
USE E4;

-- ユーザーテーブル
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (email, password) VALUES
  ('test@example.com', 'password'),
  ('dojouser1@plusdojo.jp', '#SEplus2025SEplus'),
  ('dojouser2@plusdojo.jp', '#SEplus2025SEplus'),
  ('dojouser3@plusdojo.jp', '#SEplus2025SEplus'),
  ('dojouser4@plusdojo.jp', '#SEplus2025SEplus'),
  ('dojouser5@plusdojo.jp', '#SEplus2025SEplus');

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

INSERT INTO mood_records (user_id, record_date, mood, comment)
VALUES 
(1, '2025-06-01', 3, '朝は眠かったが、午後は調子が良かった'),
(1, '2025-06-01', 2, '夕方に少し疲れを感じた'),
(1, '2025-06-02', 4, '散歩して気分がすっきりした'),
(1, '2025-06-03', 1, 'なんとなく落ち込んだ一日だった'),
(1, '2025-06-03', 3, '夜に映画を見て少し元気が出た'),
(1, '2025-06-04', 5, '朝からやる気に満ちていた'),
(1, '2025-06-05', 2, '仕事が忙しくて疲れた'),
(1, '2025-06-05', 3, '夜に音楽を聴いて少し癒された'),
(1, '2025-06-06', 4, 'ランチが美味しくて嬉しかった'),
(1, '2025-06-07', 3, '特に何もなかったけど平穏な日だった'),
(1, '2025-06-08', 1, '体調が悪くて寝込んでいた'),
(1, '2025-06-08', 2, '少し回復したけどまだ本調子じゃない'),
(1, '2025-06-09', 5, '朝から気分爽快！良いスタート'),
(1, '2025-06-10', 4, '友達と話して楽しかった'),
(1, '2025-06-10', 3, '夜は少し疲れていたけど、全体的には良い一日だった'),
(1, '2025-06-11', 3, '普通の一日だったが穏やかだった'),
(1, '2025-06-12', 4, '仕事で良い成果が出て満足'),
(1, '2025-06-13', 2, '少し疲れが溜まっていた'),
(1, '2025-06-14', 5, '友達と楽しい時間を過ごせた'),
(1, '2025-06-15', 3, 'いつも通りの一日だった'),
(1, '2025-06-16', 1, '体調が悪くて休んでいた'),
(1, '2025-06-17', 4, '運動してリフレッシュできた'),
(1, '2025-06-18', 3, '少しストレスを感じたがなんとか乗り切った');
-- ご褒美マスターテーブル
DROP TABLE IF EXISTS rewards_collection;
CREATE TABLE rewards_collection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gacha_item VARCHAR(100) NOT NULL,
    gacha_rarity INT NOT NULL
); 

INSERT INTO rewards_collection (gacha_item, gacha_rarity) VALUES
('早く寝ましょう', 3),
('ホットアイマスクを使おう', 3),
('入浴剤を使ってみよう', 3),
('ゆっくりお風呂につかろう', 3),
('今日は何もしない', 3),
('アロマや香りのミストを使いましょう', 3),
('首の後ろを温める', 3),
('自然音を聞いてみよう', 3),
('スマホから離れてみよう', 3),
('足元をお湯で温める', 3),
('暖かいお茶をゆっくり飲んでみよう', 3),
('2,000円分自分の好きなものを買う', 3),


('アイスを食べる', 2),
('お惣菜にしよう', 2),
('プリンを買う', 2),
('好きな動画やアニメを見る', 2),
('漫画を1話だけ読む',2),
('お取り寄せギフトを注文する',2),
('ジャズを流しておうちカフェ',2),
('散歩に出かける', 2),
('軽いストレッチをする', 2),
('贅沢なパンを買う', 2),
('1,000円分自分の欲しいものを買う', 2),
('好きなドリンクを1つテイクアウト',2),
('コンビニでスイーツを1つ買う',2),
('コンビニのホットスナックを買う',2),
('チョコレート菓子を買う',2),
('速く寝ましょう',2),


('一人呑み', 1),
('入ったことのないお店に挑戦', 1),
('簡単な新しい料理に挑戦', 1),
('500円分自分のお菓子を買う', 1)
('気になってた映画を見る', 1),
('プライベート用の服を1着買う', 1),
('自宅筋トレで汗をかく', 1),
('普段と違う道を歩いてみる', 1),
('全力でラジオ体操', 1),
('部屋の模様替えチャレンジ', 1),
('インド音楽を聞いてみる', 1),
('速く寝ましょう', 1);

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

INSERT INTO rewards_result (user_id, gacha_time, gacha_id) VALUES
(1, '2025-06-01', 1),
(1, '2025-06-02', 4),
(1, '2025-06-03', 7),
(1, '2025-06-04', 10),
(1, '2025-06-05', 13),
(1, '2025-06-06', 2),
(1, '2025-06-07', 5),
(1, '2025-06-08', 8),
(1, '2025-06-09', 11),
(1, '2025-06-10', 14),
(1, '2025-06-11', 3),
(1, '2025-06-12', 6),
(1, '2025-06-13', 9),
(1, '2025-06-14', 12),
(1, '2025-06-15', 15),
(1, '2025-06-16', 16),
(1, '2025-06-17', 17),
(1, '2025-06-18', 18);