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
		// 获取表单数据,并且将数据从iso-8859-1转码为utf-8
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
		// 调用方法把年月日合并到一块，然后插入数据库
		int birthday = combineNumber(year, month, day);
		String constellation = creatConstellation(birthday);
		// 随机生成QQ号码
		Random ran = new Random();
		int account = ran.nextInt(899999) + 100000;
		String number = account + "";
		try {
			// 如果返回0，说明没有重复QQ号码，则一切正常
			if (judge.judgeRepeat(number) == 0) {
				judge.registNumber(number);
				CreatQQFolder(number);
				dao.add(account, password, nickname, sex, birthday, province,
						city, constellation);
				response.getOutputStream().write(number.getBytes());
			} else {
				// 否则的话，返回一个错误给客户端，客户端会提示用户重新申请
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

		// 把月份，和天数转成int型，用来判断
		int month1 = Integer.parseInt(month);
		int day1 = Integer.parseInt(day);
		// 如果月份是 个位数
		if (month1 / 10 < 1 && day1 / 10 >= 1) {
			// 在月份前面加0 “%02d” 0代表前面加0,2代表长度为2，d代表整型
			String month2 = String.format("%02d", month1);
			StringBuffer buff = new StringBuffer(year);
			buff.append(month2).append(day);
			// 把追加好的字符串new出一个新的字符串
			String buff1 = new String(buff);
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		// 如果天数是个位数
		else if (day1 / 10 < 1 && month1 / 10 >= 1) {
			String day2 = String.format("%02d", day1);
			StringBuffer buff = new StringBuffer(year);
			buff.append(month).append(day2);
			// 把追加好的字符串new出一个新的字符串
			String buff1 = new String(buff);
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		// 如果月份和天数都是个位数
		else if (month1 / 10 < 1 && day1 / 10 < 1) {
			String month2 = String.format("%02d", month1);
			String day2 = String.format("%02d", day1);

			StringBuffer buff = new StringBuffer(year);
			buff.append(month2).append(day2);
			// 把追加好的字符串new出一个新的字符串
			String buff1 = new String(buff);
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		// 如果月日 都不是个位数
		else if (month1 / 10 >= 1 && day1 / 10 >= 1) {
			// 把月日都追加到 year的后面
			StringBuffer buff = new StringBuffer(year);
			buff.append(month).append(day);
			// 得到一个新的字符串
			String buff1 = new String(buff);
			// 把字符串转成int型 返回
			int buff2 = Integer.parseInt(buff1);
			return buff2;
		}
		return 0;

	}

	// 利用birthday创建星座,然后把星座插入到数据库里边
	public String creatConstellation(int birthday) {
		String bir = birthday + "";
		// 切割含有月份天数的字段，用来判断星座
		String month = bir.substring(4, 6);
		String day = bir.substring(6, 8);
		int month1 = Integer.parseInt(month);
		int day1 = Integer.parseInt(day);
		if (month1 == 1) {
			if (day1 < 20) {
				return "摩羯座";
			} else {
				return "水瓶座";
			}

		} else if (month1 == 2) {
			if (day1 < 19) {
				return "水瓶座";
			} else {
				return "双鱼座";
			}
		} else if (month1 == 3) {
			if (day1 >= 21) {
				return "白羊座";
			} else {
				return "双鱼座";
			}
		} else if (month1 == 4) {
			if (day1 >= 20) {
				return "金牛座";
			} else {
				return "白羊座";
			}
		} else if (month1 == 5) {
			if (day1 >= 21) {
				return "双子座";
			} else {
				return "金牛座";
			}
		} else if (month1 == 6) {
			if (day1 >= 22) {
				return "巨蟹座";
			} else {
				return "双子座";
			}
		} else if (month1 == 7) {
			if (day1 >= 23) {
				return "狮子座";
			} else {
				return "巨蟹座";
			}
		} else if (month1 == 8) {
			if (day1 >= 23) {
				return "处女座";
			} else {
				return "狮子座";
			}
		} else if (month1 == 9) {
			if (day1 >= 23) {
				return "天秤座";
			} else {
				return "处女座";
			}
		} else if (month1 == 10) {
			if (day1 >= 24) {
				return "天蝎座";
			} else {
				return "天秤座";
			}
		} else if (month1 == 11) {
			if (day1 >= 21) {
				return "射手座";
			} else {
				return "天蝎座";
			}
		} else if (month1 == 12) {
			if (day1 >= 22) {
				return "摩羯座";
			} else {
				return "射手座";
			}
		}
		return null;

	}

	// 在服务器硬盘创建一个文件夹，用来存储用户帐号 信息 文件夹,名称以QQ号码来命名
	public void CreatQQFolder(String number) {
		File file = new File("d:\\chat\\" + number);
		if (!file.exists()) {
			file.mkdirs();
		}

	}
}
