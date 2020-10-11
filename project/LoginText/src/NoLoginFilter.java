import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.UserDao;
import model.vo.User;


public class NoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest res, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
//		System.out.println("notzhixing le =----------------");
		response.setContentType("text/html;charset=UTF-8");
		UserDao userDao = new UserDao();
		// �ж�cookie���Ƿ���֮ǰ��½������
		HttpServletRequest request = (HttpServletRequest) res;
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if(null != cookies){
			String userName = null;
			String password = null;
			// cookie�е����ݲ�Ϊ��
			for(Cookie cookie : cookies){
				// ��ȡcookie�д�ŵ�userName ��password
				if("userName".equals(cookie.getName())){
					userName = cookie.getValue();
				}else if ("password".equals(cookie.getName())){
					password = cookie.getValue();
				}
			}
			// ��cookie����ֵ��ȥ��֤�û��������Ƿ���ȷ
			if(null != userName && null != password){
				User user = userDao.login(userName);
				if(null != user){
					// �����û�����ȷ��Ƚ������Ƿ���֤�ɹ�
					if(password.equals(user.getPassword())){
						// ������ȷ
						request.setAttribute("user", user);
						session.setAttribute("loginUser", user);
						request.getRequestDispatcher("/index.jsp").forward(request, response); 
					}else{
						chain.doFilter(request, response);			
					}
				}
			}else{
				chain.doFilter(request, response);			
			}
		}else{
			chain.doFilter(request, response);			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
