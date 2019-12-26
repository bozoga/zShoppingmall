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
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int rss = 0;
	private String sql = null;
	
	public MemberVO ckeckId(String id) {
		MemberVO vo = null;
		try {
			conn=getConnection();
			sql="select id from shop_member where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(rs,pstmt, conn);
		}
		return vo;
	}
	
	public MemberVO getOneMember(String id) {
		MemberVO vo = null;
		try {
			conn=getConnection();
			sql="select * from shop_member where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPassword(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setPhone(rs.getString(5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(rs,pstmt, conn);
		}
		return vo;
	}
	
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
	
	public int deleteMember(MemberVO vo) {
		try {
			conn=getConnection();
			sql="delete from shop_member where id=? and password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rss=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
	}
	
	public int updateMember(MemberVO vo) {
		try {
			conn=getConnection();
			sql="update shop_member set name=?, address=?, phone=? where id=? and password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getAddress());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getId());
			pstmt.setString(5, vo.getPassword());
			rss=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}
		return rss;
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
