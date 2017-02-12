package com.post.model;

import java.util.List;

public class PostService {

	private PostDAO_interface dao;

	public PostService() {
		dao = new PostDAO();
	}
	
	public PostVO addPost(String mem_Id, String post_class,String post_class_Id, String post_title, String post_content,
			java.sql.Date post_time,java.sql.Date post_upDate, Integer post_resNum) {
		
		PostVO postVO = new PostVO();
		

		postVO.setMem_Id(mem_Id);
		postVO.setPost_class(post_class);
		postVO.setPost_class_Id(post_class_Id);
		postVO.setPost_title(post_title);
		postVO.setPost_content(post_content);
		postVO.setPost_time(post_time);
		postVO.setPost_upDate(post_upDate);
		postVO.setPost_resNum(post_resNum);
		dao.insert(postVO);

		return postVO;
	}
	
	public PostVO updatePost(String post_Id, String mem_Id, String post_class,String post_class_Id, String post_title, String post_content,
			java.sql.Date post_time,java.sql.Date post_upDate, Integer post_resNum) {

		PostVO postVO = new PostVO();

		postVO.setPost_Id(post_Id);
		postVO.setMem_Id(mem_Id);
		postVO.setPost_class(post_class_Id);
		postVO.setPost_class_Id(post_class_Id);
		postVO.setPost_title(post_title);
		postVO.setPost_content(post_content);
		postVO.setPost_time(post_time);
		postVO.setPost_upDate(post_upDate);
		postVO.setPost_resNum(post_resNum);
		dao.update(postVO);

		return postVO;
	}
	
	public void deletePost(String post_Id) {
		dao.delete(post_Id);
	}
	
	public PostVO getOnePost(String post_Id) {
		return dao.findByPrimaryKey(post_Id);
	}

	public List<PostVO> getAll() {
		return dao.getAll();
	}
}
