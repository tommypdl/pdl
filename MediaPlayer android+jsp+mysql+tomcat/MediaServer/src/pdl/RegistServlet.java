package pdl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegistServlet() {
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

		JudgeRegistNumber judge = new JudgeRegistNumber();
		UserTableDao dao = new UserTableDao();
		request.setCharacterEncoding("utf-8");
		// ��ȡ������,���ҽ����ݴ�iso-8859-1ת��Ϊutf-8
		String password = new String(request.getParameter("password").getBytes(
				"iso-8859-1"), "utf-8");
		String nickname = new String(request.getParameter("nickname").getBytes(
				"iso-8859-1"), "utf-8");
		String sex = new String(request.getParameter("sex").getBytes(
				"iso-8859-1"), "utf-8");
		String year = new String(request.getParameter("year").getBytes(
				"iso-8859-1"), "utf-8");
		String month = new String(request.getParameter("month").getBytes(
				"iso-8859-1"), "utf-8");
		String day = new String(request.getParameter("day").getBytes(
				"iso-8859-1"), "utf-8");
		String province = new String(request.getParameter("province").getBytes(
				"iso-8859-1"), "utf-8");
		String city = new String(request.getParameter("city").getBytes(
				"iso-8859-1"), "utf-8");
		// ���÷����������պϲ���һ�飬Ȼ��������ݿ�
		int birthday = combineNumber(year, month, day);
		String constellation = creatConstellation(birthday);
		// �������QQ����
		Random ran = new Random();
		int account = ran.nextInt(899999) + 100000;
		String number = account + "";
		try {
			// �������0��˵��û���ظ�QQ���룬��һ������
			if (judge.judgeRepeat(number) == 0) {
				judge.registNumber(number);
				CreatQQFolder(number);
				dao.add(account, password, nickname, sex, birthday, province,
						city, constellation);
				response.getOutputStream().write(number.getBytes());
			} else {
				// ����Ļ�������һ��������ͻ��ˣ��ͻ��˻���ʾ�û���������
				response.getOutputStream().write("error".getBytes());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public int combineNumber(String year, String month, String day) {

		// ���·ݣ�������ת��int�ͣ������ж�
		int month1 = Integer.parseInt(month);
		int day1 = Integer.parseInt(day);
		// ����·��� ��λ��
		if (month1 / 10 < 1 && day1 / 10 >= 1) {
			// ���·�ǰ���0 ��%02d�� 0����ǰ���0,2������Ϊ2��d��������
			String month2 = String.format("%02d", month1);
			StringBuffer buff = new StringBuffer(year);
			buff.append(month2).append(day);
			// ��׷�Ӻõ��ַ���new��һ���µ��ַ���
			String buff1 = new String(buff);
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		// ��������Ǹ�λ��
		else if (day1 / 10 < 1 && month1 / 10 >= 1) {
			String day2 = String.format("%02d", day1);
			StringBuffer buff = new StringBuffer(year);
			buff.append(month).append(day2);
			// ��׷�Ӻõ��ַ���new��һ���µ��ַ���
			String buff1 = new String(buff);
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		// ����·ݺ��������Ǹ�λ��
		else if (month1 / 10 < 1 && day1 / 10 < 1) {
			String month2 = String.format("%02d", month1);
			String day2 = String.format("%02d", day1);

			StringBuffer buff = new StringBuffer(year);
			buff.append(month2).append(day2);
			// ��׷�Ӻõ��ַ���new��һ���µ��ַ���
			String buff1 = new String(buff);
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		// ������� �����Ǹ�λ��
		else if (month1 / 10 >= 1 && day1 / 10 >= 1) {
			// �����ն�׷�ӵ� year�ĺ���
			StringBuffer buff = new StringBuffer(year);
			buff.append(month).append(day);
			// �õ�һ���µ��ַ���
			String buff1 = new String(buff);
			// ���ַ���ת��int�� ����
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		return 0;

	}

	// ����birthday��������,Ȼ����������뵽���ݿ����
	public String creatConstellation(int birthday) {
		String bir = birthday + "";
		// �и���·��������ֶΣ������ж�����
		String month = bir.substring(4, 6);
		String day = bir.substring(6, 8);
		int month1 = Integer.parseInt(month);
		int day1 = Integer.parseInt(day);
		if (month1 == 1) {
			if (day1 < 20) {
				return "Ħ����";
			} else {
				return "ˮƿ��";
			}

		} else if (month1 == 2) {
			if (day1 < 19) {
				return "ˮƿ��";
			} else {
				return "˫����";
			}
		} else if (month1 == 3) {
			if (day1 >= 21) {
				return "������";
			} else {
				return "˫����";
			}
		} else if (month1 == 4) {
			if (day1 >= 20) {
				return "��ţ��";
			} else {
				return "������";
			}
		} else if (month1 == 5) {
			if (day1 >= 21) {
				return "˫����";
			} else {
				return "��ţ��";
			}
		} else if (month1 == 6) {
			if (day1 >= 22) {
				return "��з��";
			} else {
				return "˫����";
			}
		} else if (month1 == 7) {
			if (day1 >= 23) {
				return "ʨ����";
			} else {
				return "��з��";
			}
		} else if (month1 == 8) {
			if (day1 >= 23) {
				return "��Ů��";
			} else {
				return "ʨ����";
			}
		} else if (month1 == 9) {
			if (day1 >= 23) {
				return "�����";
			} else {
				return "��Ů��";
			}
		} else if (month1 == 10) {
			if (day1 >= 24) {
				return "��Ы��";
			} else {
				return "�����";
			}
		} else if (month1 == 11) {
			if (day1 >= 21) {
				return "������";
			} else {
				return "��Ы��";
			}
		} else if (month1 == 12) {
			if (day1 >= 22) {
				return "Ħ����";
			} else {
				return "������";
			}
		}
		return null;

	}

	// �ڷ�����Ӳ�̴���һ���ļ��У������洢�û��ʺ� ��Ϣ �ļ���,������QQ����������
	public void CreatQQFolder(String number) {
		File file = new File("d:\\chat\\" + number);
		if (!file.exists()) {
			file.mkdirs();
		}

	}
}
