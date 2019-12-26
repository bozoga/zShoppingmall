package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		String viewPage = null;
		int rss = 0;
		response.setContentType("text/html;charset=utf-8"); // 어떤 타입으로 출력할것인지 명시하였다.
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		if (action.equals("deleteReview2.do")) {
			ReviewVO vo = new ReviewVO();
			ReviewDAO dao = ReviewDAO.getInstance();
			
			String id = request.getParameter("id");
			String bid = request.getParameter("bid");
			
			rss=dao.deleteReview(id);
			
			if(rss==1) {
				response.sendRedirect("read.do?id="+bid);
				return;
			}else {
				out.println("<script>");
				out.println("alert('구매가 실패하였습니다')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		if (action.equals("deleteReview.do")) {
			ReviewVO vo = new ReviewVO();
			ReviewDAO dao = ReviewDAO.getInstance();
			
			String id = request.getParameter("id");
			
			rss=dao.deleteReview(id);
			
			if(rss==1) {
				response.sendRedirect("review.jsp");
				return;
			}else {
				out.println("<script>");
				out.println("alert('구매가 실패하였습니다')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		if (action.equals("buyGoods.do")) {
			GoodsVO vo = new GoodsVO();
			GoodsDAO dao = GoodsDAO.getInstance();
			
			String id = request.getParameter("id");
			String amount = request.getParameter("amount");
			rss=dao.buyGoods(id, amount);
			
			if(rss==1) {
				out.println("<script>");
				out.println("alert('구매가 완료되었습니다')");
				out.println("location.href='read.do?id='"+id);
				out.println("</script>");
				response.sendRedirect("read.do?id=" + id);
				return;
			}else {
				out.println("<script>");
				out.println("alert('구매가 실패하였습니다')");
				out.println("history.back();");
				out.println("</script>");
			}
		}

		if (action.equals("myPage.do")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			if (session.getAttribute("userID") != null) {
				String id = (String) session.getAttribute("userID");
				MemberVO mvo = dao.getOneMember(id);
				session.setAttribute("userInfo", mvo);
				viewPage = "myPage.jsp";
			} else {
				out.println("<script>");
				out.println("alert('로그인이 필요합니다')");
				out.println("location.href='login.jsp'");
				out.println("</script>");
			}

			String userID = request.getParameter("id");
			vo.setId(userID);
			vo.setPassword(request.getParameter("password"));

			rss = dao.login(vo);
			if (rss == 1) {
				session.setAttribute("userID", userID);
				viewPage = "index.jsp";
			} else {
				out.println("<script>");
				out.println("alert('로그인에 실패했습니다')");
				out.println("location.href='login.jsp'");
				out.println("</script>");
			}
		}

		if (action.equals("searchGoods.do")) {
			GoodsDAO dao = GoodsDAO.getInstance();
			String keyword = request.getParameter("keyword");
			ArrayList<GoodsVO> list = dao.searchGoods(keyword);
			session.setAttribute("list", list);
			if (session.getAttribute("list") != null) {
				viewPage = "list.jsp";
			} else {
				out.println("검색된 상품이 없습니다");
			}
		}

		if (action.equals("deleteCart.do")) {
			CartDAO dao = CartDAO.getInstance();
			String id = request.getParameter("id");
			rss = dao.deleteCart(id);

			if (rss == 1) {
				response.sendRedirect("cart.do");
				return;
			}
		}

		if (action.equals("insertCart.do")) {
			CartDAO dao = CartDAO.getInstance();
			String id = (String) session.getAttribute("userID");
			String gid = request.getParameter("id");

			if (id != null) {
				rss = dao.insertCart(id, gid);
				if (rss == 1) {
					out.println("<script>");
					out.println("alert('장바구니에 추가됐습니다')");
					out.println("history.back();");
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('실패했습니다')");
					out.println("history.back();");
					out.println("</script>");
				}
			} else {
				out.println("<script>");
				out.println("alert('로그인이 필요합니다');");
				out.println("location.href='login.jsp';");
				out.println("</script>");
			}
		}

		if (action.equals("cart.do")) {
			CartDAO dao = CartDAO.getInstance();
			String id = (String) session.getAttribute("userID");
			ArrayList<GoodsVO> cartList = dao.getCartList(id);
			session.setAttribute("cartList", cartList);

			if (session.getAttribute("cartList") != null) {
				viewPage = "cart.jsp";
			} else {
				viewPage = "cart.jsp";
			}
		}

		if (action.equals("list.do")) {
			GoodsVO vo = new GoodsVO();
			GoodsDAO dao = GoodsDAO.getInstance();

			vo.setDiv(request.getParameter("div"));

			ArrayList<GoodsVO> list = dao.getGoodsList(vo);
			session.setAttribute("list", list);

			if (session.getAttribute("list") != null) {
				viewPage = "list.jsp";
			} else {
				out.println("등록된 상품이 없습니다");
			}
		}

		if (action.equals("insertNotice.do")) {
			NoticeVO vo = new NoticeVO();
			NoticeDAO dao = NoticeDAO.getInstance();

			vo.setWriter(request.getParameter("writer"));
			vo.setContent(request.getParameter("content"));
			vo.setSubject(request.getParameter("subject"));

			rss = dao.insertNotice(vo);

			if (rss == 0) {
				viewPage = "";
			} else {
				viewPage = "notice.jsp";
			}
		}

		if (action.equals("insertReview.do")) {
			String saveDirectory = session.getServletContext().getRealPath("/img");
			int maxPostSize = 10 * 1024 * 1024;
			String encoding = "utf-8";
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			String fileName = "fileName";
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);

			if (multi.getFile(fileName) != null) {
				File file = multi.getFile(fileName);
				fileName = file.getName();
			} else {
				fileName = "noimage.gif";
			}

			ReviewVO vo = new ReviewVO();
			ReviewDAO dao = ReviewDAO.getInstance();
			
			String id =multi.getParameter("id");
			vo.setBid(multi.getParameter("id"));
			vo.setWriter(multi.getParameter("writer"));
			vo.setContent(multi.getParameter("content"));

			vo.setImg("img/" + fileName);

			rss = dao.insertReview(vo);

			if (rss == 0) {
				viewPage = "";
			} else {
				response.sendRedirect("read.do?id=" + id);
				return;
			}
		}

		if (action.equals("readNotice.do")) {
			NoticeVO vo = new NoticeVO();
			NoticeDAO dao = NoticeDAO.getInstance();
			vo.setIdx(request.getParameter("idx"));

			NoticeVO nvo = dao.getOneList(vo);
			session.setAttribute("nvo", nvo);

			if (nvo == null) {
				viewPage = "";
			} else {
				viewPage = "readNotice.jsp";
			}
		}

		if (action.equals("read.do")) {
			GoodsVO vo = new GoodsVO();
			GoodsDAO dao = GoodsDAO.getInstance();
			vo.setId(request.getParameter("id"));

			GoodsVO gvo = dao.getOneList(vo);
			session.setAttribute("gvo", gvo);

			ReviewVO rvo = new ReviewVO();
			ReviewDAO dao2 = ReviewDAO.getInstance();
			rvo.setBid(request.getParameter("id"));

			ArrayList<ReviewVO> rlist = dao2.getReviewList(rvo);
			session.setAttribute("rlist", rlist);

			if (gvo == null) {
				viewPage = "";
			} else {
				viewPage = "read.jsp";
			}
		}

		if (action.equals("insertGoods.do")) {
			String saveDirectory = session.getServletContext().getRealPath("/img");
			System.out.println(saveDirectory);
			int maxPostSize = 10 * 1024 * 1024;
			String encoding = "utf-8";
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
			vo.setImg("img/" + fileName);

			rss = dao.insertGoods(vo);

			if (rss == 0) {
				viewPage = "";
			} else {
				viewPage = "insertGoods.jsp";
			}
		}

		if (action.equals("checkId.do")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			String id = request.getParameter("id");

			vo = dao.ckeckId(id);
			if (vo != null) {
				out.println("<script>");
				out.println("alert('이미 존재하는 아이디입니다')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('사용할 수 있는 아이디입니다')");
				out.println("history.back()");
				out.println("</script>");
			}
		}

		if (action.equals("deleteMember.do")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			vo.setId(request.getParameter("id"));
			vo.setPassword(request.getParameter("password"));

			rss = dao.deleteMember(vo);
			if (rss == 0) {
				out.println("<script>");
				out.println("alert('회원 탈퇴가 실패했습니다')");
				out.println("history.back();");
				out.println("</script>");
			} else {
				session.removeAttribute("userID");
				out.println("<script>");
				out.println("alert('회원 탈퇴가 성공했습니다')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			}
		}

		if (action.equals("updateMember.do")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			vo.setId(request.getParameter("id"));
			vo.setPassword(request.getParameter("password"));
			vo.setName(request.getParameter("name"));
			vo.setAddress(request.getParameter("address"));
			vo.setPhone(request.getParameter("phone"));

			rss = dao.updateMember(vo);
			if (rss == 0) {
				out.println("<script>");
				out.println("alert('회원정보 수정이 실패했습니다')");
				out.println("history.back();");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원정보 수정이 성공했습니다')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			}
		}

		if (action.equals("join.do")) {
			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			vo.setId(request.getParameter("id"));
			vo.setPassword(request.getParameter("password"));
			vo.setName(request.getParameter("name"));
			vo.setAddress(request.getParameter("address"));
			vo.setPhone(request.getParameter("phone"));

			rss = dao.join(vo);
			if (rss == 0) {
				out.println("<script>");
				out.println("alert('회원가입이 실패했습니다')");
				out.println("history.back();");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원가입이 성공했습니다')");
				out.println("location.href='login.jsp'");
				out.println("</script>");
			}
		}

		if (action.equals("login.do")) {

			MemberVO vo = new MemberVO();
			MemberDAO dao = MemberDAO.getInstance();
			String userID = request.getParameter("id");
			vo.setId(userID);
			vo.setPassword(request.getParameter("password"));

			rss = dao.login(vo);
			if (rss == 1) {
				session.setAttribute("userID", userID);
				viewPage = "index.jsp";
			} else {
				out.println("<script>");
				out.println("alert('로그인에 실패했습니다')");
				out.println("location.href='login.jsp'");
				out.println("</script>");
			}
		}

		if (action.equals("logout.do")) {
			session.removeAttribute("userID");
			viewPage = "index.jsp";
		}

		RequestDispatcher rDis = request.getRequestDispatcher(viewPage);
		rDis.forward(request, response);
	}

}
