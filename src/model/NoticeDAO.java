package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NoticeDAO extends JDBCUtil{
	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

	private NoticeDAO() {}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int rss = 0;
	private String sql = null;
	
	
	public int getTotalNotice(NoticeVO vo) {
		int totalNotice = 0;
		try {
			conn = getConnection();
			sql = "select count(*) from shop_notice";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalNotice= Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return totalNotice;
	}
	
	public int insertNotice(NoticeVO vo) {
		try {
			conn=getConnection();
			sql="insert into shop_notice values((select nvl(max(idx),0)+1 from shop_notice),?,?,?,sysdate,0)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			rss=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
	
	public NoticeVO getOneList(NoticeVO vo) {
		NoticeVO nvo = null;
		try {
			conn = getConnection();
			sql = "select * from shop_notice where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getIdx());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nvo = new NoticeVO();
				nvo.setIdx(rs.getString(1));
				nvo.setSubject(rs.getString(2));
				nvo.setWriter(rs.getString(3));
				nvo.setContent(rs.getString(4));
				nvo.setRegdate(rs.getString(5));
				nvo.setReadcount(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return nvo;
	}
	
	public ArrayList<NoticeVO> getNoticeList(int startCount,int endCount) {
		ArrayList<NoticeVO> list = null;
		try {
			conn = getConnection();
			sql = "select * from (select rownum rnum, s.* from (select  * from shop_notice order by idx desc)s)\r\n" + 
					"where rnum BETWEEN ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,startCount);
			pstmt.setInt(2,endCount);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<NoticeVO>();
				do {
					NoticeVO nvo = new NoticeVO();
					nvo.setIdx(rs.getString(2));
					nvo.setSubject(rs.getString(3));
					nvo.setWriter(rs.getString(4));
					nvo.setContent(rs.getString(5));
					nvo.setRegdate(rs.getString(6));
					nvo.setReadcount(rs.getString(7));
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
}
