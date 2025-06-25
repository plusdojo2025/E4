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

-- ご褒美マスタ（全件保持）
DROP TABLE IF EXISTS rewards_collection;
CREATE TABLE rewards_collection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    gacha_item VARCHAR(100) NOT NULL,
    gacha_rarity INT NOT NULL
);

INSERT INTO rewards_collection (gacha_item, gacha_rarity) VALUES
-- rarity 3
('早く寝ましょう', 3),
('ホットアイマスクを使おう', 3),
('入浴剤を使ってみよう', 3),
('ゆっくりお風呂につかろう', 3),
('今日は何もしない', 3),
('アロマや香りのミストを使いましょう', 3),
('首の後ろを温める', 3),
('自然音を聞いてみよう', 3),
('好きな曲だけを聴く', 3),
('スマホから離れてみよう', 3),
('足湯に浸かってみる', 3),
('半身浴でゆっくり', 3),
('暖かいお茶をゆっくり飲んでみよう', 3),
('ハーブティーを呑んでみよう', 3),
('2,000円分自分の好きなものを買う', 3),
-- rarity 2
('アイスを買う', 2),
('晩御飯をお惣菜にしよう', 2),
('プリンを買う', 2),
('好きな動画やアニメを見る', 2),
('漫画を1話だけ読む',2),
('ジャズを流しておうちカフェ',2),
('散歩に出かける', 2),
('軽いストレッチをする', 2),
('贅沢なパンを買う', 2),
('1,000円分自分の欲しいものを買う', 2),
('好きなドリンクを1つテイクアウト',2),
('帰りにスイーツを1つ買う',2),
('帰りにホットスナックを買う',2),
('帰りにチョコレート菓子を買う',2),
('早く寝ましょう',2),
-- rarity 1
('一人呑み', 1),
('入ったことのないお店に挑戦', 1),
('新しい料理に挑戦', 1),
('500円分自分のお菓子を買う', 1),
('気になってた映画を見る', 1),
('プライベート用の服を1着買う', 1),
('自宅筋トレで汗をかく', 1),
('普段と違う道を歩いてみる', 1),
('全力でラジオ体操', 1),
('部屋の模様替えチャレンジ', 1),
('テンションの上がる曲を聴いてみる', 1),
('小説を読み進めよう', 1),
('早く寝ましょう', 1),
('温泉や銭湯に行ってみる', 1);

-- ご褒美実績
DROP TABLE IF EXISTS rewards_result;
CREATE TABLE rewards_result (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    gacha_time DATE NOT NULL,
    gacha_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (gacha_id) REFERENCES rewards_collection(id)
);

-- 気分記録（全ユーザーに対して一括挿入）
INSERT INTO mood_records (user_id, record_date, mood, comment)
SELECT u.id, d.record_date, d.mood, d.comment
FROM users u
JOIN (
  SELECT '2025-06-01' AS record_date, 3 AS mood, '朝は眠かったが、午後は調子が良かった' UNION ALL
  SELECT '2025-06-01', 2, '夕方に少し疲れを感じた' UNION ALL
  SELECT '2025-06-02', 4, '散歩して気分がすっきりした' UNION ALL
  SELECT '2025-06-03', 1, 'なんとなく落ち込んだ一日だった' UNION ALL
  SELECT '2025-06-03', 3, '夜に映画を見て少し元気が出た' UNION ALL
  SELECT '2025-06-04', 5, '朝からやる気に満ちていた' UNION ALL
  SELECT '2025-06-05', 2, '仕事が忙しくて疲れた' UNION ALL
  SELECT '2025-06-05', 3, '夜に音楽を聴いて少し癒された' UNION ALL
  SELECT '2025-06-06', 4, 'ランチが美味しくて嬉しかった' UNION ALL
  SELECT '2025-06-07', 3, '特に何もなかったけど平穏な日だった' UNION ALL
  SELECT '2025-06-08', 1, '体調が悪くて寝込んでいた' UNION ALL
  SELECT '2025-06-08', 2, '少し回復したけどまだ本調子じゃない' UNION ALL
  SELECT '2025-06-09', 5, '朝から気分爽快！良いスタート' UNION ALL
  SELECT '2025-06-10', 4, '友達と話して楽しかった' UNION ALL
  SELECT '2025-06-10', 3, '夜は少し疲れていたけど、全体的には良い一日だった' UNION ALL
  SELECT '2025-06-11', 3, '普通の一日だったが穏やかだった' UNION ALL
  SELECT '2025-06-12', 4, '仕事で良い成果が出て満足' UNION ALL
  SELECT '2025-06-13', 2, '少し疲れが溜まっていた' UNION ALL
  SELECT '2025-06-14', 5, '友達と楽しい時間を過ごせた' UNION ALL
  SELECT '2025-06-15', 3, 'いつも通りの一日だった' UNION ALL
  SELECT '2025-06-16', 1, '体調が悪くて休んでいた' UNION ALL
  SELECT '2025-06-17', 4, '運動してリフレッシュできた' UNION ALL
  SELECT '2025-06-18', 3, '少しストレスを感じたがなんとか乗り切った' UNION ALL
  SELECT '2025-06-19', 2, '少し気分が落ち込んでいた' UNION ALL
  SELECT '2025-06-20', 4, '映画を見て元気が出た' UNION ALL
  SELECT '2025-06-21', 5, 'とても充実した日だった' UNION ALL
  SELECT '2025-06-22', 3, '特に何もなかったが平穏だった' UNION ALL
  SELECT '2025-06-23', 1, '朝からなんとなく気分が重かった' UNION ALL
  SELECT '2025-06-24', 4, '同僚と楽しい時間を過ごせた' UNION ALL
  SELECT '2025-06-25', 2, '仕事で少し失敗して落ち込んだ' UNION ALL
  SELECT '2025-06-26', 3, 'なんとか気持ちを立て直した' UNION ALL
  SELECT '2025-06-27', 5, '最高の一日だった！' UNION ALL
  SELECT '2025-06-28', 4, '休日をゆっくり過ごした' UNION ALL
  SELECT '2025-06-29', 3, '日曜の夜はちょっと憂鬱'
) AS d(record_date, mood, comment);

-- ご褒美記録（mood → rarity を判断し、対応する rewards_collection からランダムに1件選択）
INSERT INTO rewards_result (user_id, gacha_time, gacha_id)
SELECT
  m.user_id,
  m.record_date,
  (
    SELECT id
    FROM rewards_collection
    WHERE gacha_rarity = (
      CASE
        WHEN m.mood = 1 THEN 3
        WHEN m.mood = 5 THEN 1
        ELSE 2
      END
    )
    ORDER BY RAND()
    LIMIT 1
  )
FROM mood_records m;
