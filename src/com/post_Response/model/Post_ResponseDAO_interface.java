package com.post_Response.model;

import com.post.model.*;
import java.util.*;

public interface Post_ResponseDAO_interface {

	 public void insert(Post_ResponseVO post_ResponseVO);
     public void update(Post_ResponseVO post_ResponseVO);
     public void delete(String res_Id);
     public Post_ResponseVO findByPrimaryKey(String res_Id);
     public List<Post_ResponseVO> getAll();
     //查詢某部門的員工(一對多)(回傳 Set)
     public Set<PostVO> getPostsByPost_Id(String post_Id);
	
}
