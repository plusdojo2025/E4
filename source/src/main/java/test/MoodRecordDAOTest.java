package test;

import java.sql.Date;
import java.util.List;

import dao.MoodRecordDAO;
import model.MoodRecord;

public class MoodRecordDAOTest {
    public static void main(String[] args) {
        int testUserId = 1;

        // 今日の日付を取得
        Date today = new Date(System.currentTimeMillis());

        // 登録するMoodRecordを作成
        MoodRecord newRecord = new MoodRecord();
        newRecord.setUser_id(testUserId);
        newRecord.setRecord_date(today);
        newRecord.setMood(4);
        newRecord.setComment("DAOテスト用の気分記録");

        // DAOインスタンス化
        MoodRecordDAO dao = new MoodRecordDAO();

        // INSERTの方のテスト
        boolean success = dao.insert(newRecord);
        if (success) {
            System.out.println("登録成功: 気分記録が挿入されました");
        } else {
            System.err.println("登録失敗: 気分記録の挿入に失敗しました");
            return;
        }

        // 挿入確認用の全件取得テスト
        List<MoodRecord> records = dao.findAllByUser(testUserId);
        System.out.println("【ユーザーID: " + testUserId + " の記録一覧】");

        for (MoodRecord record : records) {
            System.out.println("日付: " + record.getRecord_date());
            System.out.println("気分段階: " + record.getMood());
            System.out.println("コメント: " + record.getComment());
            if (record.getCreated_at() != null) {
                System.out.println("記録時刻: " + record.getCreated_at().toLocalDateTime().toLocalTime());
            }
            System.out.println("--------------------------");
        }
    }
}
