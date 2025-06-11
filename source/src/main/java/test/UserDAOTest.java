package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.UserDAO;
import model.User;

public class UserDAOTest {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		UserDAO dao = new UserDAO();
		User correctUser = new User("test@example.com", "password");
		System.out.println("正しいユーザーの認証:" + dao.isRegisteredUser(correctUser));

		User inCorrectUser1 = new User("test1@example.com", "password");
		System.out.println("正しくないユーザーの認証:" + dao.isRegisteredUser(inCorrectUser1));

		User inCorrectUser2 = new User("test@example.com", "password1");
		System.out.println("正しくないユーザーの認証:" + dao.isRegisteredUser(inCorrectUser2));

		User insertUser = new User("hogehoge@example.com", "woonyannn");
		User insertedUser = dao.insertUser(insertUser);
		System.out.println("新規ユーザーの登録:" +gson.toJson(insertedUser));
	}
}