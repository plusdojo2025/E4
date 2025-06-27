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

-- ご褒美マスタ
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
('漫画を1話だけ読む', 2),
('ジャズを流しておうちカフェ', 2),
('散歩に出かける', 2),
('軽いストレッチをする', 2),
('贅沢なパンを買う', 2),
('1,000円分自分の欲しいものを買う', 2),
('好きなドリンクを1つテイクアウト', 2),
('帰りにスイーツを1つ買う', 2),
('帰りにホットスナックを買う', 2),
('帰りにチョコレート菓子を買う', 2),
('早く寝ましょう', 2),
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

-- 気分記録サンプル(全ユーザー同一)
INSERT INTO mood_records (user_id, record_date, mood, comment)
SELECT
  u.id,
  d.record_date,
  d.mood,
  d.comment
FROM users u
JOIN (
  SELECT '2025-06-01' AS record_date, 3 AS mood, '朝は眠くて少しだるかった' AS comment UNION ALL
  SELECT '2025-06-02', 4, '散歩してリフレッシュできた' UNION ALL
  SELECT '2025-06-03', 2, '少し気分が落ち込んだが夜に映画を観て回復' UNION ALL
  SELECT '2025-06-04', 3, '仕事は忙しかったが穏やかに過ごせた' UNION ALL
  -- 6/5は穴抜け
  SELECT '2025-06-06', 3, '平穏な一日だった' UNION ALL
  SELECT '2025-06-07', 3, '特に変わりなし' UNION ALL
  SELECT '2025-06-08', 1, '体調不良でほとんど寝ていた' UNION ALL
  SELECT '2025-06-09', 5, '朝から元気で最高の気分' UNION ALL
  SELECT '2025-06-10', 4, '友人と楽しく過ごせた' UNION ALL
  SELECT '2025-06-11', 3, '普通の一日だった' UNION ALL
  SELECT '2025-06-12', 4, '仕事で成果があって満足' UNION ALL
  -- 6/13は穴抜け
  SELECT '2025-06-14', 5, '休日にリラックスできた' UNION ALL
  SELECT '2025-06-15', 3, '普段通りの一日だった' UNION ALL
  SELECT '2025-06-16', 2, '体調はまずまずだったが少しだるい' UNION ALL
  SELECT '2025-06-17', 4, '運動して気分リフレッシュ' UNION ALL
  SELECT '2025-06-18', 3, 'ストレスはあったがなんとか乗り切った' UNION ALL
  SELECT '2025-06-19', 3, 'やや気分が落ち着かない日' UNION ALL
  SELECT '2025-06-20', 4, '映画を観て元気になった' UNION ALL
  SELECT '2025-06-21', 5, 'とても充実した日だった' UNION ALL
  -- 6/22は穴抜け
  SELECT '2025-06-23', 2, '気分が少し沈み気味' UNION ALL
  SELECT '2025-06-24', 4, '同僚と楽しい時間を過ごした' UNION ALL
  SELECT '2025-06-25', 3, '仕事で少し悩みもあったが持ち直した' UNION ALL
  -- 6/26はリハ
 -- SELECT '2025-06-27', 5, '最高の気分で楽しかった' UNION ALL
  SELECT '2025-06-28', 4, '休日をゆっくり過ごせた'
) d
ORDER BY d.record_date;


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
