package pdl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pdl.UserTableDao;

public class EditTalkFromWeb extends HttpServlet {

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
		String id = request.getParameter("id");
		if (id == null || id.length() == 0) {
			return;
		}
		String nickname = request.getParameter("nickname"); // 昵称
		if (nickname == null || nickname.length() == 0) {
			return;
		}
		String musicname = request.getParameter("musicname"); // 歌曲
		if (musicname == null || musicname.length() == 0) {
			return;
		}
		String talkText = request.getParameter("text"); // 评论
		if (talkText == null || talkText.length() == 0) {
			return;
		}

		//修改评论的代码
		UserTableDao db = new UserTableDao();
		String sql = "update talk set nickname='" + nickname + "', text='"
				+ talkText + "',  musicname='" + musicname + "' where id=" + id;
		db.editTalk(sql); // 执行更新

		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("编辑成功");
		out.flush();
		out.close();
	}

}
