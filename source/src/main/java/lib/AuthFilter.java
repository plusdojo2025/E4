package lib;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		// ログイン・サインアップページ、静的ファイルは除外
		if (uri.endsWith("LoginServlet") || uri.endsWith("SignUpServlet")
				|| uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|ico|svg|woff2?)$")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			res.sendRedirect(contextPath + "/LoginServlet");
			return;
		}

		// 認証済みなら次へ
		chain.doFilter(request, response);
	}
}
