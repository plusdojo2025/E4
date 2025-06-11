package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ガチャ画面表示
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
 
        request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp").forward(request, response);
    }
}