package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import model.*;
import java.io.File;
/**
 * Servlet implementation class ShopController
 */
@WebServlet("*.do")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		String viewPage = null;
		int rss=0;
		HttpSession session = request.getSession();
		
		if(action.equals("read.do")) {
			GoodsVO vo = new GoodsVO();
			GoodsDAO dao = GoodsDAO.getInstance();
			vo.setId(request.getParameter("id"));
			
			GoodsVO gvo=dao.getOneList(vo);
			session.setAttribute("gvo", gvo);
			if(gvo==null) {
				viewPage="";
			}else {
				viewPage="read.jsp";
			}
		}
		
		if(action.equals("insertGoods.do")) {
			String saveDirectory = session.getServletContext().getRealPath("/img");
			System.out.println(saveDirectory);
			int maxPostSize=10*1024*1024;
			String encoding="utf-8";
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			String fileName = "fileName";
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);

			File file = multi.getFile(fileName);
			
			fileName = file.getName();
			
			GoodsVO vo = new GoodsVO();
			GoodsDAO dao = GoodsDAO.getInstance();
			
			vo.setId(multi.getParameter("id"));
			vo.setDiv(multi.getParameter("div"));
			vo.setName(multi.getParameter("name"));
			vo.setPrice(multi.getParameter("price"));
			vo.setStock(multi.getParameter("stock"));
			vo.setImg("img/"+fileName);
			
			rss=dao.insertGoods(vo);
			
			if(rss==0) {
				viewPage="";
			}else {
				viewPage="insertGoods.jsp";
			}
			
		}
		
		if(action.equals("join.do")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			vo.setId(request.getParameter("id"));
			vo.setPassword(request.getParameter("password"));
			vo.setName(request.getParameter("name"));
			vo.setAddress(request.getParameter("address"));
			vo.setPhone(request.getParameter("phone"));
			
			rss=dao.join(vo);
			if(rss==0) {
				viewPage="";
			}else {
				viewPage="login.jsp";
			}
		}
		
		if(action.equals("login.do")) {
			
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			String userID = request.getParameter("id");
			vo.setId(userID);
			vo.setPassword(request.getParameter("password"));
			
			rss=dao.login(vo);
			if(rss==1) {
				session.setAttribute("userID", userID);
				viewPage="index.jsp";
			}else{
				viewPage="login.jsp";
			}
		}
		
		RequestDispatcher rDis = request.getRequestDispatcher(viewPage);
		rDis.forward(request, response);
	}

}
