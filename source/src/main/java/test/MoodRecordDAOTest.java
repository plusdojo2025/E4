package test;

import java.util.List;

import dao.MoodRecordDAO;
import model.MoodRecord;

public class MoodRecordDAOTest {
    public static void main(String[] args) {
        int userId = 1;  // 表示対象のユーザーID

        MoodRecordDAO dao = new MoodRecordDAO();
        List<MoodRecord> records = dao.findAllByUser(userId);

        System.out.println("【ユーザーID: " + userId + " の気分記録一覧】");
        for (MoodRecord record : records) {
            System.out.println("日付: " + record.getRecord_date());
            System.out.println("気分段階: " + record.getMood());
            System.out.println("コメント: " + record.getComment());
            System.out.println("記録時刻: " + record.getCreated_at().toLocalDateTime().toLocalTime());
            System.out.println("----------------------");
        }
    }
}
