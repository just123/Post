package com.post.model;

import java.util.*;

public interface PostDAO_interface {
	public void insert(PostVO postVO);
	public void update(PostVO postVO);
	public void delete(String post_Id);
	public PostVO findByPrimaryKey(String post_Id);
	public List<PostVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳List)
//	public List<PostVO> getAll(Map<String, String[]> map);
}
