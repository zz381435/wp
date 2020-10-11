import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.vo.User;


public class PermissionFilter implements Filter {

	private String notCheckPath;



	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// ��ServletRequest���Ͳ���ת��ΪHttpServletRequest����
		HttpServletRequest request = (HttpServletRequest)req;
		String path = request.getServletPath(); //��ȡ�����URL-Pattern��ַ
		System.out.println("�����ַurl-pattern:" + path);
		//�����ַ���ڲ�����˵��б�Χ�ڣ���Ҫ�ж��Ƿ��Ѿ���¼
		if (notCheckPath.indexOf(path) == -1) {
			HttpSession session = request.getSession();
			if (session.getAttribute("currentUser") == null) {
				request.setAttribute("info", "û��Ȩ�޷���");
				request.getRequestDispatcher("/error.jsp").forward(request, resp);
			
			}else { //�Ѿ���¼��ֱ�ӷ���
				chain.doFilter(req, resp);
			}
			}
		}



	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//��web.xml�����ļ���filter�ж�ȡ��ΪnotCeckPath�ĳ�ʼֵ
		notCheckPath = config.getInitParameter("notCheckPath");
	}

}
