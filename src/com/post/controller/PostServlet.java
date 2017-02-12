package com.post.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.post.model.*;

public class PostServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("post_Id");
				System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String post_Id = null;
				try {
					post_Id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(post_Id);
				
				if (postVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // 資料庫取出的postVO物件,存入req
				String url = "/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePost.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPost.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String post_Id = new String(req.getParameter("post_Id"));	
				
				/***************************2.開始查詢資料****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(post_Id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("postVO", postVO);         // 資料庫取出的postVO物件,存入req
				String url = "/post/update_post_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}
			
	//update
		if ("update".equals(action)) { // 來自update_podt_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String post_Id = req.getParameter("post_Id").trim();	
				String mem_Id = req.getParameter("mem_Id").trim();
				
				String post_class_Id$post_class = req.getParameter("post_class_Id$post_class").trim();
				String post_class = post_class_Id$post_class.split("-")[1];
				String post_class_Id = post_class_Id$post_class.split("-")[0];

				
				//文章標題錯誤判斷
				String post_title = null;
				post_title = new String(req.getParameter("post_title").trim());
				if (post_title.length() == 0) {
					errorMsgs.add("請輸入文章內容!");
				}
				
				//文章內容錯誤判斷
				String post_content = null;
				post_content = new String(req.getParameter("post_content").trim());
				if (post_content.length() == 0) {
					errorMsgs.add("請輸入文章內容!");
				}
				
				java.sql.Date post_time = null;
				try {
					post_time = java.sql.Date.valueOf(req.getParameter("post_time").trim());
				} catch (IllegalArgumentException e) {
					post_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入發布日期!");
				}
				
				java.sql.Date post_upDate = null;
				try {
					post_upDate = java.sql.Date.valueOf(req.getParameter("post_upDate").trim());
				} catch (IllegalArgumentException e) {
					post_upDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入修改日期!");
				}
				
				Integer post_resNum = new Integer(req.getParameter("post_resNum").trim());

				PostVO postVO = new PostVO();
				postVO.setPost_Id(post_Id);
				postVO.setMem_Id(mem_Id);
				postVO.setPost_class(post_class);
				postVO.setPost_class_Id(post_class_Id);
				postVO.setPost_title(post_title);
				postVO.setPost_content(post_content);
				postVO.setPost_time(post_time);
				postVO.setPost_upDate(post_upDate);
				postVO.setPost_resNum(post_resNum);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/update_post_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.updatePost(post_Id, mem_Id, post_class, post_class_Id, post_title,post_content, post_time, post_upDate, post_resNum);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的postVO物件,存入req
				String url = "/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePost.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/post/update_post_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		  if ("insert".equals(action)) { // 來自addPost.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//					String post_Id = req.getParameter("post_Id").trim();	
					String mem_Id = req.getParameter("mem_Id").trim();
					
					String post_class_Id$post_class = req.getParameter("post_class_Id$post_class").trim();
					String post_class = post_class_Id$post_class.split("-")[1];
					 System.out.println(post_class);
					String post_class_Id = post_class_Id$post_class.split("-")[0];
					 System.out.println(post_class_Id);
					
					//文章標題錯誤判斷
					String post_title = null;
					post_title = new String(req.getParameter("post_title").trim());
					if (post_title.length() == 0) {
						errorMsgs.add("請輸入文章內容!");
					}
					
					//文章內容錯誤判斷
					String post_content = null;
					post_content = new String(req.getParameter("post_content").trim());
					if (post_content.length() == 0) {
						errorMsgs.add("請輸入文章內容!");
					}
					
					java.sql.Date post_time = null;
					try {
						post_time = java.sql.Date.valueOf(req.getParameter("post_time").trim());
					} catch (IllegalArgumentException e) {
						post_time=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入發布日期!");
					}
					
					java.sql.Date post_upDate = null;
					try {
						post_upDate = java.sql.Date.valueOf(req.getParameter("post_upDate").trim());
					} catch (IllegalArgumentException e) {
						post_upDate=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入修改日期!");
					}
					
					Integer post_resNum = new Integer(req.getParameter("post_resNum").trim());

					PostVO postVO = new PostVO();
					postVO.setMem_Id(mem_Id);
					postVO.setPost_class(post_class);
					postVO.setPost_class_Id(post_class_Id);
					postVO.setPost_title(post_title);
					postVO.setPost_content(post_content);
					postVO.setPost_time(post_time);
					postVO.setPost_upDate(post_upDate);
					postVO.setPost_resNum(post_resNum);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/post/addPost.jsp");
						failureView.forward(req, res);
						return;
					}
					 System.out.println(post_class);

					/***************************2.開始新增資料***************************************/
					PostService postSvc = new PostService();
					postVO = postSvc.addPost(mem_Id, post_class, post_class_Id, post_title,post_content, post_time, post_upDate, post_resNum);

					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/post/listAllPost.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPostp.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/addPost.jsp");
					failureView.forward(req, res);
				}
			}
		  
			
			if ("delete".equals(action)) { // 來自listAllPost.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數***************************************/
					String post_Id = new String(req.getParameter("post_Id"));	

					/***************************2.開始刪除資料***************************************/
					PostService postSvc = new PostService();
					postSvc.deletePost(post_Id);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/post/listAllPost.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/listAllPost.jsp");
					failureView.forward(req, res);
				}
			}
		}
}
