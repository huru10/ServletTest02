package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

/**
 * Servlet implementation class DeleteTweetServlet
 */
@WebServlet("/delete_tweet")
public class DeleteTweetServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int tweetId = Integer.parseInt(request.getParameter("id"));
			TweetDAO tweetDAO = new TweetDAO();
			tweetDAO.deleteTweet(tweetId);
			// 削除が成功した場合の処理（例えば、成功メッセージを設定するなど）
			String message = "ツイートを削除しました。";
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
			// 削除が失敗した場合の処理
			String errorMessage = "ツイートの削除中にエラーが発生しました。";
			request.setAttribute("errorMessage", errorMessage);
		}
		// ツイート一覧ページにリダイレクト
		response.sendRedirect("tweet_list");
	}

}
