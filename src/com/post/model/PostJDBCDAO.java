package com.post.model;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;


public class PostJDBCDAO implements PostDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott";
	String passwd = "tiger";
	
	private static final String INSERT_STMT = 
			 "INSERT INTO post (post_Id,mem_Id,post_class,post_class_Id,post_title,post_content,post_time, post_upDate,post_resNum) VALUES(post_seq1.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT post_Id,mem_Id,post_class,post_class_Id,post_title,post_content,to_char(post_time,'yyyy-mm-dd')post_time,to_char(post_upDate,'yyyy-mm-dd')post_upDate,post_resNum FROM post order by post_Id";
	private static final String GET_ONE_STMT = 
			"SELECT post_Id,mem_Id,post_class,post_class_Id,post_title,post_content,to_char(post_time,'yyyy-mm-dd')post_time,to_char(post_upDate,'yyyy-mm-dd')post_upDate,post_resNum FROM post where post_Id = ?";
	private static final String DELETE = 
			"DELETE FROM post where post_Id = ?";
	private static final String UPDATE = 
			"UPDATE post set mem_Id=?, post_class=?, post_class_Id=?, post_title=?, post_content=?, post_time=?, post_upDate=?, post_resNum=? where post_Id = ?";
	
	
	@Override
	public void insert(PostVO postVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, postVO.getMem_Id());
			pstmt.setString(2, postVO.getPost_class());
			pstmt.setString(3, postVO.getPost_class_Id());
			pstmt.setString(4, postVO.getPost_title());
			pstmt.setString(5, postVO.getPost_content());
			pstmt.setDate(6, postVO.getPost_time());
			pstmt.setDate(7, postVO.getPost_upDate());
			pstmt.setInt(8, postVO.getPost_resNum());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(PostVO postVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, postVO.getMem_Id());
			pstmt.setString(2, postVO.getPost_class());
			pstmt.setString(3, postVO.getPost_class_Id());
			pstmt.setString(4, postVO.getPost_title());
			pstmt.setString(5, postVO.getPost_content());
			pstmt.setDate(6, postVO.getPost_time());
			pstmt.setDate(7, postVO.getPost_upDate());
			pstmt.setInt(8, postVO.getPost_resNum());
			pstmt.setString(9, postVO.getPost_Id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String post_Id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, post_Id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public PostVO findByPrimaryKey(String post_Id) {
		
		PostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, post_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// postVO 也稱為 Domain objects
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
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return postVO;
	}

	@Override
	public List<PostVO> getAll() {
		
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// postVO 也稱為 Domain objects
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
				list.add(postVO); // Store the row in the list 
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	public static void main(String[] args) {

		PostJDBCDAO dao = new PostJDBCDAO();

		// 新增
//		PostVO postVO1 = new PostVO();
//
//		postVO1.setMem_Id("7001");
//		postVO1.setPost_class("");
//		postVO1.setPost_class_Id("");
//		postVO1.setPost_title("aaa");
//		postVO1.setPost_content("bbbbbbbb");
//		postVO1.setPost_time(java.sql.Date.valueOf("2017-02-10"));
//		postVO1.setPost_upDate(java.sql.Date.valueOf("2017-03-11"));
//		postVO1.setPost_resNum(2);
//		dao.insert(postVO1);
//		

		// 修改
//		PostVO postVO2 = new PostVO();
//		postVO2.setPost_Id("10001");
//		postVO2.setMem_Id("7000");
//		postVO2.setPost_class("");
//		postVO2.setPost_class_Id("");
//		postVO2.setPost_title("aaa");
//		postVO2.setPost_content("bbbbbbbb");
//		postVO2.setPost_time(java.sql.Date.valueOf("2017-02-10"));
//		postVO2.setPost_upDate(java.sql.Date.valueOf("2017-03-11"));
//		postVO2.setPost_resNum(5);
//		dao.update(postVO2);

		// 刪除
//		dao.delete("100011");

		// 查詢
//		PostVO postVO3 = dao.findByPrimaryKey("10001");
//		System.out.print(postVO3.getPost_Id() + ",");
//		System.out.print(postVO3.getMem_Id() + ",");
//		System.out.print(postVO3.getPost_class() + ",");
//		System.out.print(postVO3.getPost_class_Id() + ",");
//		System.out.print(postVO3.getPost_title() + ",");
//		System.out.print(postVO3.getPost_content() + ",");
//		System.out.print(postVO3.getPost_time() + ",");
//		System.out.print(postVO3.getPost_upDate() + ",");
//		System.out.println(postVO3.getPost_resNum());
//		System.out.println("---------------------");

		// 查詢
		List<PostVO> list = dao.getAll();
		for (PostVO aPost : list) {
			System.out.print(aPost.getPost_Id() + ",");
			System.out.print(aPost.getMem_Id() + ",");
			System.out.print(aPost.getPost_class() + ",");
			System.out.print(aPost.getPost_class_Id() + ",");
			System.out.print(aPost.getPost_title() + ",");
			System.out.print(aPost.getPost_content() + ",");
			System.out.print(aPost.getPost_time() + ",");
			System.out.print(aPost.getPost_upDate() + ",");
			System.out.print(aPost.getPost_resNum());
			System.out.println();
		}
	}
	
}
 