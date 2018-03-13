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
		String nickname = request.getParameter("nickname"); // �ǳ�
		if (nickname == null || nickname.length() == 0) {
			return;
		}
		String musicname = request.getParameter("musicname"); // ����
		if (musicname == null || musicname.length() == 0) {
			return;
		}
		String talkText = request.getParameter("text"); // ����
		if (talkText == null || talkText.length() == 0) {
			return;
		}

		//�޸����۵Ĵ���
		UserTableDao db = new UserTableDao();
		String sql = "update talk set nickname='" + nickname + "', text='"
				+ talkText + "',  musicname='" + musicname + "' where id=" + id;
		db.editTalk(sql); // ִ�и���

		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("�༭�ɹ�");
		out.flush();
		out.close();
	}

}
