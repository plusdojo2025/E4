//package lib;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet Filter implementation class AuthFilter
// */
//@WebFilter("/*")
//public class AuthFilter extends HttpFilter implements Filter {
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		String contextPath = httpRequest.getContextPath();
//		String requestURI = httpRequest.getRequestURI();
//
//		HttpSession session = httpRequest.getSession(false);
//		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
//
//		boolean isLoginRequest = requestURI.equals(contextPath + "/login.html");
//		boolean isAuthChangerRequest = requestURI.equals(contextPath + "/AuthChanger");
//		boolean isStyleSheet = requestURI.equals(contextPath + "/style.css");
//
//		if (isLoggedIn || isLoginRequest || isAuthChangerRequest || isStyleSheet) {
//			chain.doFilter(request, response);
//		} else {
//			httpResponse.sendRedirect(contextPath + "/login.html");
//		}
//	}
//}
