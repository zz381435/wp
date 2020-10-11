package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.Createlmage;

@WebServlet(urlPatterns = "/CreateVerfifyCodeImage.do")

public class CreateVerfifyCodeImageController extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.����4λ���ȵ�����ַ���
		Createlmage createImage = new Createlmage();
		String vCode = createImage.createCode(); //дһ����ͷ�����ʵ��

		//2.�����ɵ�4λ�ַ���������session��Χ��
		//��ȡsession����
		HttpSession session = request.getSession();
		//���
		session.setAttribute("verifycode", vCode);
		//���÷�������
		response.setContentType("img/jpeg");
		//�������������Ӧ����-��֤��ͼƬ����һ��ˢ��һ�Σ����Բ��ܻ������
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//������֤��ʧЧʱ��
		response.setDateHeader("Expires", 0);
		//3.����һ ��ͼƬ. ͼƬ����Ϊ�����ɵ�4�����
		BufferedImage image = createImage.CreateImage(vCode);
		
		//4.��ͼƬ�����ͻ���
		//��ȡ���������
		ServletOutputStream out=response.getOutputStream();
		//���
		ImageIO.write(image, "JPEG", out);
		//ˢ���������������������������ǵȴ��������������������磩
		out.flush();
		out.close();

	}

}