package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDao;
import model.vo.User;
@WebServlet(urlPatterns = "/login.do")
public class LoginController extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Date tasktime=new Date(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:mm:ss");  //�������������ʽ
		//��ʽ�����
		String nowTime = df.format(tasktime);
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
//		System.out.println(name +"---"+ password);
		// �ж���֤���Ƿ���ȷ
		HttpSession session = request.getSession();
		String saveVcode = (String)session.getAttribute("verifycode");
		if(vcode.equalsIgnoreCase(saveVcode)){
			// ��֤��������ȷ
			// ͨ���û��������������֤
			UserDao userDao = new UserDao();
			User user = userDao.login(userName);
			if(null != user && null != user.getUserName()){
				//�û�������
				if(user.getPassword().equals(password)){
					//������ȷ��½�ɹ�
					request.setAttribute("user", user);
					request.getRequestDispatcher("/main.jsp").forward(request, response); 
				}else{
					// ��������½ʧ��
					request.setAttribute("info","������󣬵�½ʧ��");
					request.getRequestDispatcher("/error.jsp").forward(request, response); 
				}
			}else{
				//�û���������
				request.setAttribute("info","�û�������������������");
				request.getRequestDispatcher("/error.jsp").forward(request,response); 
			}
		}else{
			// ��֤����֤ʧ��
			request.setAttribute("info","��������ȷ����֤��");
			request.getRequestDispatcher("/error.jsp").forward(request,response); 
		}
	
	}
}
