package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションの取得（ログイン情報の確認）
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("user") == null) {
			// セッションなしはログインページへ
			response.sendRedirect("/E4/LoginServlet");
			return;
		}

		// ユーザー情報（メールアドレス）を取得
		User user = (User) session.getAttribute("user");
		String email = user.getEmail();

		// メールアドレスをリクエストスコープに格納
		request.setAttribute("email", email);

		// アカウント情報表示ページへフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープを破棄する
		HttpSession session = request.getSession(false);
		session.invalidate();

		// ログインページにリダイレクトする
		response.sendRedirect("/E4/LoginServlet");

	}
}
