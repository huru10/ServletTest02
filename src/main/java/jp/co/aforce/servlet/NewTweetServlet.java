package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/new_tweet")
public class NewTweetServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String author = request.getParameter("author");

		TweetDAO tweetDAO = new TweetDAO();
		String message = "投稿に失敗しました。投稿内容が255文字を超えています。";
		try {
			if (content.length() <= 255) {
				tweetDAO.addTweet(content, author);
				message = "投稿が成功しました。";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "投稿に失敗しました。データベースエラーが発生しました。";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("/tweet_list").forward(request, response);
	}
}
