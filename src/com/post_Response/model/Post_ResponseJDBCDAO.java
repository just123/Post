package com.post_Response.model;

import java.util.*;
import java.sql.*;

import com.post.model.PostVO;

public class Post_ResponseJDBCDAO implements Post_ResponseDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott";
	String passwd = "tiger";

	private static final String INSERT_STMT = "INSERT INTO  post_Response (res_Id,mem_Id,post_Id,post_Response_content,post_time,post_Response_upDate) VALUES (post_Response_seq1.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT res_Id,mem_Id,post_Id,post_Response_content,post_time,post_Response_upDate FROM post_Response";
	private static final String GET_ONE_STMT =

			"SELECT res_Id,mem_Id,post_Id,post_Response_content,post_time,post_Response_upDate FROM post_Response where res_Id = ?";
	private static final String GET_Posts_ByPost_Id_STMT = "SELECTpost_Id,mem_Id,post_class,post_class_Id,post_title,post_content,to_char(post_time,'yyyy-mm-dd') post_time,to_char(post_upDate,'yyyy-mm-dd') post_upDate,post_resNum  FROM post where post_Id = ? order by mem_Id";
	private static final String DELETE_POSTs = "DELETE FROM post where post_Id = ?";
	private static final String DELETE_POST_RESPONSE = "DELETE FROM post_Response where res_Id = ?";

	private static final String UPDATE = "UPDATE post_Response set mem_Id=?, post_Id=? post_Response_content=?, post_time=? post_Response_upDate=? where res_Id = ?";

	@Override
	public void insert(Post_ResponseVO post_ResponseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, post_ResponseVO.getMem_Id());
			pstmt.setString(2, post_ResponseVO.getPost_Id());
			pstmt.setString(3, post_ResponseVO.getPost_Response_content());
			pstmt.setDate(4, post_ResponseVO.getPost_time());
			pstmt.setDate(5, post_ResponseVO.getPost_Response_upDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Post_ResponseVO post_ResponseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, post_ResponseVO.getMem_Id());
			pstmt.setString(2, post_ResponseVO.getPost_Id());
			pstmt.setString(3, post_ResponseVO.getPost_Response_content());
			pstmt.setDate(4, post_ResponseVO.getPost_time());
			pstmt.setDate(5, post_ResponseVO.getPost_Response_upDate());
			pstmt.setString(8, post_ResponseVO.getRes_Id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String res_Id) {
		int updateCount_POST_RESPONSEs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除回應
			pstmt = con.prepareStatement(DELETE_POST_RESPONSE);
			pstmt.setString(1, res_Id);
			updateCount_POST_RESPONSEs = pstmt.executeUpdate();
			// 再刪除文章
			pstmt = con.prepareStatement(DELETE_POSTs);
			pstmt.setString(1, post_Id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除文章編號" + post_Id + "時,共有回應" + updateCount_POST_RESPONSEs + "文章同時被刪除");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Post_ResponseVO findByPrimaryKey(String res_Id) {

		Post_ResponseVO post_ResponseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, res_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// post_ResponseVO 也稱為 Domain objects
				post_ResponseVO = new Post_ResponseVO();
				post_ResponseVO.setRes_Id(rs.getString("res_Id"));
				post_ResponseVO.setMem_Id(rs.getString("mem_Id"));
				post_ResponseVO.setPost_Id(rs.getString("post_Id"));
				post_ResponseVO.setPost_Response_content(rs.getString("post_Response_content"));
				post_ResponseVO.setPost_time(rs.getDate("post_time"));
				post_ResponseVO.setPost_Response_upDate(rs.getDate("post_Response_upDate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return post_ResponseVO;
	}

	@Override
	public List<Post_ResponseVO> getAll() {

		List<Post_ResponseVO> list = new ArrayList<Post_ResponseVO>();
		Post_ResponseVO post_ResponseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_ResponseVO = new Post_ResponseVO();
				post_ResponseVO.setRes_Id(rs.getString("res_Id"));
				post_ResponseVO.setMem_Id(rs.getString("mem_Id"));
				post_ResponseVO.setPost_Id(rs.getString("post_Id"));
				post_ResponseVO.setPost_Response_content(rs.getString("post_Response_content"));
				post_ResponseVO.setPost_time(rs.getDate("post_time"));
				post_ResponseVO.setPost_Response_upDate(rs.getDate("post_Response_upDate"));
				list.add(post_ResponseVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public Set<PostVO> getPostsByPost_Id(String post_Id) {

		Set<PostVO> set = new LinkedHashSet<PostVO>();
		PostVO postVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Posts_ByPost_Id_STMT);
			pstmt.setString(1, post_Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPost_Id(rs.getString("post_Id"));
				postVO.setMem_Id(rs.getString("mem_Id"));
				postVO.setPost_class(rs.getString("post_class"));
				postVO.setPost_class_Id(rs.getString("post_class_Id"));
				postVO.setPost_title(rs.getString("post_title"));
				postVO.setPost_content(rs.getString("post_content"));
				postVO.setPost_time(rs.getDate("post_time"));
				postVO.setPost_upDate(rs.getDate("post_upDate"));
				postVO.setPost_resNum(rs.getInt("post_resNum"));
				set.add(postVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return set;
	}
	
	public static void main(String[] args) {

		Post_ResponseJDBCDAO dao = new Post_ResponseJDBCDAO();

		// 新增
//		Post_ResponseVO post_ResponseVO1 = new Post_ResponseVO();
//		post_ResponseVO1.setMem_Id("7001");
//		post_ResponseVO1.setPost_Id("10001");
//		post_ResponseVO1.setPost_Response_content("我是留言!");
//		post_ResponseVO1.setPost_time(java.sql.Date.valueOf("2017-02-10"));
//		post_ResponseVO1.setPost_Response_upDate(java.sql.Date.valueOf("2017-03-01"));
//		dao.insert(post_ResponseVO1);
		
		// 修改
//		Post_ResponseVO post_ResponseVO2 = new Post_ResponseVO();
//		post_ResponseVO2.setRes_Id("1");
//		post_ResponseVO2.setMem_Id("7002");
//		post_ResponseVO2.setPost_Id("10002");
//		post_ResponseVO2.setPost_Response_content("我是留言!YA");
//		post_ResponseVO2.setPost_time(java.sql.Date.valueOf("2017-03-10"));
//		post_ResponseVO2.setPost_Response_upDate(java.sql.Date.valueOf("2017-04-01"));
//		dao.update(post_ResponseVO2);

		// 刪除
//		dao.delete(3);
		
		// 查詢
//		Post_ResponseVO post_ResponseVO3 = dao.findByPrimaryKey(1);
//		System.out.print(post_ResponseVO3.getRes_Id() + ",");
//		System.out.print(post_ResponseVO3.getMem_Id() + ",");
//		System.out.print(post_ResponseVO3.getPost_Id()+ ",");
//		System.out.print(post_ResponseVO3.getPost_Response_content() + ",");
//		System.out.print(post_ResponseVO3.getPost_time() + ",");
//		System.out.println(post_ResponseVO3.getPost_Response_upDate());
//		System.out.println("---------------------");
		
		// 查詢回應
		List<Post_ResponseVO> list = dao.getAll();
		for (Post_ResponseVO aPost_Response : list) {
			System.out.print(aPost_Response.getRes_Id() + ",");
			System.out.print(aPost_Response.getMem_Id() + ",");
			System.out.print(aPost_Response.getPost_Id());
			System.out.println();
		}

}
