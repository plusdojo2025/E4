package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 新規登録画面を表示
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(request, response);
	}

	// 新規登録処理
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// パラメータ取得
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// ユーザー登録処理
		UserDAO dao = new UserDAO();
		User registeredUser = dao.insertUser(new User(email, password));

		if (registeredUser != null) {
			// 登録成功 → 成功メッセージを表示
			// request.setAttribute("resultTitle", "登録成功");
//			request.setAttribute("nextUrl", request.getContextPath() + "/LoginServlet");
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
		} else {
			// 登録失敗（たとえばすでに同じemailがあるなど）
			// request.setAttribute("resultTitle", "登録失敗");
//			request.setAttribute("nextUrl", request.getContextPath() + "/SignUpServlet");
			response.sendRedirect(request.getContextPath() + "/SignUpServlet");
		}
	}

}