package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ログイン画面表示
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String pw = request.getParameter("password");

		// ログイン処理を行う
		UserDAO dao = new UserDAO();
		if (dao.isRegisteredUser(new User(email, pw))) { // ログイン成功
			// セッションスコープにUser情報を格納する
			HttpSession session = request.getSession(true);
			session.setAttribute("user", dao.selectByEmail(email));

			response.sendRedirect(request.getContextPath() + "/HomeServlet");
		} else { // ログイン失敗
			request.setAttribute("result", "メールアドレスまたはPWに間違いがあります。");
			// 結果ページにフォワードする
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}
}