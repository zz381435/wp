package model.dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class Createlmage {
	private static final int WIDTH = 100;// ������֤��ͼƬ���
	private static final int HEIGHT = 30;// ������֤��ͼƬ�߶�
	private static final int LENGTH = 4;// ������֤�볤��
	private static final int LINECOUNT = 6;// �����ߵ���Ŀ
	
	//��֤����ַ��⣬ȥ������ʶ���o0I1l�ַ�
	private static final String str = "23456789"
			+ "ABCDEFGHJKMNPQRSTUVWXYZ" + "abcdefghjkmnpqrstuvwxyz";
	
	private static Random random = new Random();
	
	//ͨ�������ȡ�ַ����е��ַ���ϳ�4λ��֤��
	public String createCode() {
		String code = "";
		for (int i = 0; i < LENGTH; i++) {
			//nextInt(n)����һ�������intֵ����ֵ����[0��n)�����䣬Ҳ����0��n֮������intֵ������0��������n
			char c = str.charAt(random.nextInt(str.length()));
			
			code = code + c;
		}
		return code;
	}
	//��ȡ��ͬ��ɫ
	public Color getColor() {
		return new Color(random.nextInt(255),random.nextInt(255),
		random.nextInt(255));
	}
	public Font getFont() {
		return new Font("����", Font.BOLD, 23);
	}
	public void drawChar(Graphics g, String code) {
		g.setFont(getFont());
		for (int i = 0; i < LENGTH; i++) {
			char c = code.charAt(i);
			g.setColor(getColor());
			g.drawString(c + "", 20 * i + 10, 25);
		}
	}
	//���������
	public void drawLine(Graphics g) {
		int x = random.nextInt(WIDTH);
		int y = random.nextInt(HEIGHT);
		int x1 = random.nextInt(13);
		int y1 = random.nextInt(15);
		g.setColor(getColor());
		g.drawLine(x, y, x + x1, y + y1);
	}
	//������֤��ͼƬ
	public BufferedImage CreateImage(String code) {
		//��ȡ����
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		//���ñ�����ɫ�����ƾ��α���
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//��֤�����
		drawChar(g, code);
		//����ߵĻ���
		for (int i = 0; i < LINECOUNT; i++) {
			drawLine(g);
		}
		//����ͼƬ
		g.dispose();
		//����ͼƬ
			return image;
		}
	}




