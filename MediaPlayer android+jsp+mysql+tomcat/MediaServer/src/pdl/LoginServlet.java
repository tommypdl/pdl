package pdl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		UserTableDao dao = new UserTableDao();
		ArrayList<User> users = new ArrayList<User>();
		// �õ��������������ʺ�����
		String loginusername = request.getParameter("username");
		String loginpassword = request.getParameter("password");
		// ��ѯ���ݿ⣬��������QQ����Ϊ�ж�
		users = dao.findAll();
		int counts = dao.counts();

		for (User user : users) {
			int ID = user.getId();
			int username = user.getUsername();
			String password = user.getPassword();
			int online = user.getOnline();
			response.setHeader("content-type", "text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (loginusername.equals(username + "")) {
				if (loginpassword.equals(password)) {
					// ����û�������������ˣ����ͻ��˷��ص�¼����û���ID�ţ������½�ɹ�
					out.write(String.valueOf(ID));
				} else {
					// ���������ˡ���ʾ�������
					out.write("errorpassword");
				}
			} else {
				if (!loginusername.equals(username + "") && ID == counts) {
					out.write("doNotFoundUsername");
				}
			}
			out.flush();
			out.close();
		}
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
