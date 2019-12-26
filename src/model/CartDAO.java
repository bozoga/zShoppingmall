package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CartDAO extends JDBCUtil{
	private static CartDAO instance = new CartDAO();
	public static CartDAO getInstance(){
		return instance;
	}
	private CartDAO() {}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int rss = 0;
	private String sql = null;
	
	public int deleteCart(String gid) {
		try {
			conn=getConnection();
			sql="delete from shop_cart where gid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, gid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
	
	public ArrayList<GoodsVO> getCartList(String id) {
		ArrayList<GoodsVO> list = null;
		try {
			conn = getConnection();
			sql = "select * from shop_cart c join shop_goods g on c.gid=g.id where c.id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<GoodsVO>();
				do {
					GoodsVO nvo = new GoodsVO();
					nvo.setId(rs.getString(3));
					nvo.setDiv(rs.getString(4));
					nvo.setName(rs.getString(5));
					nvo.setPrice(rs.getString(6));
					nvo.setStock(rs.getString(7));
					nvo.setImg(rs.getString(8));
					list.add(nvo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return list;
	}
	
	public int insertCart(String id, String gid) {
		try {
			conn=getConnection();
			sql="insert into shop_cart values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, gid);
			rss=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
}
