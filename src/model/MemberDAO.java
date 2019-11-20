package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class MemberDAO extends JDBCUtil{
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance(){
		return instance;
	}
	private MemberDAO() {}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int rss = 0;
	String sql=null;
	

	
	public int login(MemberVO vo) {
		try {
			conn=getConnection();
			sql="select password from shop_member where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(vo.getPassword().equals(rs.getString(1))){
					return 1; 
				}else {
					return 0;
				}
			}
			return -1;//아이디없음
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(rs,pstmt, conn);
		}
		return -2;  //db오류
	}
	
	public int join(MemberVO vo) {
		try {
			conn=getConnection();
			sql="insert into shop_member values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getPhone());
			rss=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
}
