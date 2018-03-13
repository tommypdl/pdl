package pdl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pdl.UserTableDao;

public class InsertTalkFromWeb extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nickname = request.getParameter("nickname"); //昵称
		if (nickname == null || nickname.length() == 0) {
			return;
		}
		String musicname = request.getParameter("musicname"); //歌曲
		if (musicname == null || musicname.length() == 0) {
			return;
		}
		String talkText = request.getParameter("text"); //评论
		if (talkText == null || talkText.length() == 0) {
			return;
		}
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy/MM/dd-HH:mm:ss");
		String time = dateformat.format(new Date());

		//添加评论的代码
		UserTableDao db = new UserTableDao();
		db.addtalk(nickname,talkText,musicname,time);

		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("添加成功");
		out.flush();
		out.close();
	}

}
