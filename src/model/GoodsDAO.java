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

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int rss = 0;
	private String sql = null;

	public int buyGoods(String id , String amount) {
		try {
			conn = getConnection();
			sql = "update shop_goods set stock=(stock-?) where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(amount));
			pstmt.setString(2, id);
			rss = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
	
	public ArrayList<GoodsVO> searchGoods(String keyword) {
		ArrayList<GoodsVO> list = null;
		try {
			conn = getConnection();
			sql = "select * from shop_goods where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
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

			if (Integer.parseInt(vo.getDiv()) == 100) {
				sql = "select * from shop_goods where div=1 or div=2 or div=3 or div=4";
				pstmt = conn.prepareStatement(sql);
			} else if (Integer.parseInt(vo.getDiv()) == 200) {
				sql = "select * from shop_goods where div=11 or div=12 or div=13 or div=14";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "select * from shop_goods where div=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getDiv());
			}

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
