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

@WebFilter("/*")
public class RootRedirectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        // 静的ファイル無視
        if (uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|ico|svg|woff2?)$")) {
            chain.doFilter(request, response);
            return;
        }

        // ルートならリダイレクト
        if (uri.equals(contextPath) || uri.equals(contextPath + "/")) {
            res.sendRedirect(contextPath + "/HomeServlet");
            return;
        }

        chain.doFilter(request, response);
    }
}
