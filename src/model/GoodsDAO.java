package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GoodsDAO extends JDBCUtil {
	private static GoodsDAO instance = new GoodsDAO();

	public static GoodsDAO getInstance() {
		return instance;
	}

	private GoodsDAO() {
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int rss = 0;
	String sql = null;

	public GoodsVO getOneList(GoodsVO vo) {
		GoodsVO gvo = null;
		try {
			conn = getConnection();
			sql = "select * from shop_goods where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				gvo = new GoodsVO();
				gvo.setId(rs.getString(1));
				gvo.setDiv(rs.getString(2));
				gvo.setName(rs.getString(3));
				gvo.setPrice(rs.getString(4));
				gvo.setStock(rs.getString(5));
				gvo.setImg(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return gvo;

	}

	public int insertGoods(GoodsVO vo) {
		try {
			conn = getConnection();
			sql = "insert into shop_goods values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setInt(2, Integer.parseInt(vo.getDiv()));
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, Integer.parseInt(vo.getPrice()));
			pstmt.setInt(5, Integer.parseInt(vo.getStock()));
			pstmt.setString(6, vo.getImg());
			rss = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}

	public ArrayList<GoodsVO> getGoodsList(GoodsVO vo) {
		ArrayList<GoodsVO> list = null;
		try {
			conn = getConnection();
			sql = "select * from shop_goods";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<GoodsVO>();
				do {
					GoodsVO gvo = new GoodsVO();
					gvo.setId(rs.getString(1));
					gvo.setDiv(rs.getString(2));
					gvo.setName(rs.getString(3));
					gvo.setPrice(rs.getString(4));
					gvo.setStock(rs.getString(5));
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
