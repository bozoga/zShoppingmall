package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReviewDAO extends JDBCUtil{
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}

	private ReviewDAO() {}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
		
	public int deleteReview(String id) {
		int rss = 0;
		try {
			conn=getConnection();
			sql="delete from shop_review where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rss=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
	
	public ArrayList<ReviewVO> getAllReviewList(ReviewVO vo) {
		ArrayList<ReviewVO> list = null;
		try {
			conn = getConnection();
			sql = "select * from shop_review order by id desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<ReviewVO>();
				do {
					ReviewVO gvo = new ReviewVO();
					gvo.setId(rs.getString(1));
					gvo.setBid(rs.getString(2));
					gvo.setWriter(rs.getString(3));
					gvo.setContent(rs.getString(4));
					gvo.setRegdate(rs.getString(5));
					gvo.setImg(rs.getString(6));
					list.add(gvo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return list;
	}
	
	public int insertReview(ReviewVO vo) {
		int rss = 0;
		try {
			conn=getConnection();
			sql="insert into shop_review values((select nvl(max(id),0)+1 from shop_review),?,?,?,sysdate,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBid());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getImg());
			rss=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
	
	public ArrayList<ReviewVO> getReviewList(ReviewVO vo) {
		ArrayList<ReviewVO> list = null;
		try {
			conn = getConnection();
			sql = "select * from shop_review where bid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBid());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<ReviewVO>();
				do {
					ReviewVO gvo = new ReviewVO();
					gvo.setId(rs.getString(1));
					gvo.setBid(rs.getString(2));
					gvo.setWriter(rs.getString(3));
					gvo.setContent(rs.getString(4));
					gvo.setRegdate(rs.getString(5));
					gvo.setImg(rs.getString(6));
					list.add(gvo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return list;
	}
}
