package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RewardsDAO;

@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ガチャ画面表示(疲労度によって色を変える処理)
    private String[] getEnvelopeImages(int mood) {
        String closedImage = "";
        String openedImage = "";

        if (mood == 1) {
            closedImage = "img/binsen_close_red.png";
            openedImage = "img/binsen_open_red.png";
        } else if (mood >= 2 && mood <= 4) {
            closedImage = "img/binsen_close_yellow.png";
            openedImage = "img/binsen_open_yellow.png";
        } else if (mood == 5) {
            closedImage = "img/binsen_close_blue.png";
            openedImage = "img/binsen_open_blue.png";
        } else {
            closedImage = "img/binsen_close_red.png";
            openedImage = "img/binsen_open_red.png";
        }
        return new String[] { closedImage, openedImage };
    }

    // ガチャ画面表示（初期表示）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 例えばセッションなどから疲労度を取得。無ければデフォルト1
        HttpSession session = request.getSession(false);
        int mood = 1;
        if (session != null && session.getAttribute("fatigueLevel") != null) {
            mood = (int) session.getAttribute("fatigueLevel");
        }

        String[] images = getEnvelopeImages(mood);
        request.setAttribute("closedImage", images[0]);
        request.setAttribute("openedImage", images[1]);

        request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp").forward(request, response);
    }

    // ガチャの処理
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        int userId = (int) session.getAttribute("userId");

        // パラメータから mood を取得（POSTで送られてくる前提）
        int mood = 1;
        try {
            mood = Integer.parseInt(request.getParameter("mood"));
        } catch (NumberFormatException e) {
            mood = 1; // デフォルト
        }

        // mood によって rarity を決定
        int rarity;
        if (mood == 1) {
            rarity = 3;
        } else if (mood <= 4) {
            rarity = 2;
        } else {
            rarity = 1;
        }

        // ガチャを引く
        RewardsDAO rewardsDao = new RewardsDAO();
        String rewardItem = rewardsDao.taikinGacha(rarity, userId);

        // 疲労度に応じた封筒画像を決定
        String[] images = getEnvelopeImages(mood);

        // JSPに結果と画像パスを渡す
        request.setAttribute("rewardItem", rewardItem);
        request.setAttribute("closedImage", images[0]);
        request.setAttribute("openedImage", images[1]);

        request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp").forward(request, response);
    }
}
