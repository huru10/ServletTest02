package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Tweet;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/tweet_list")
public class TweetListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TweetDAO tweetDAO = new TweetDAO();
		try {
			List<Tweet> tweets = tweetDAO.getAllTweets();
			request.setAttribute("tweets", tweets);
			for (Tweet tweet : tweets) {
				System.out.println(tweet.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = (String) request.getAttribute("message");
		request.setAttribute("message", message);
		request.getRequestDispatcher("tweet_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
