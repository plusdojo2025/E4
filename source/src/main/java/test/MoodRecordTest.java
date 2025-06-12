package test;

import java.sql.Date;
import java.util.Scanner;

import dao.MoodRecordDAO;
import model.MoodRecord;

public class MoodRecordTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 仮ユーザーID（）
		int userId = 1;

		// 気分（1〜5）を入力
		System.out.print("気分を入力してください（1〜5）: ");
		int mood = Integer.parseInt(scanner.nextLine());

		// コメント（任意）を入力
		System.out.print("コメントを入力してください（空欄可）: ");
		String comment = scanner.nextLine();

		// MoodRecordを作成
		MoodRecord record = new MoodRecord(userId, new Date(System.currentTimeMillis()), mood, comment);

		// 登録処理
		MoodRecordDAO dao = new MoodRecordDAO();
		boolean success = dao.insert(record);

		if (success) {
			System.out.println("気分記録の登録に成功しました。");
		} else {
			System.out.println("気分記録の登録に失敗しました。");
		}

		scanner.close();
	}
}
