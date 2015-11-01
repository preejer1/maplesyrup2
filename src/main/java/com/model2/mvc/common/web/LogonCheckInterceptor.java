package com.model2.mvc.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model2.mvc.service.domain.User;



public class LogonCheckInterceptor extends HandlerInterceptorAdapter {

	///Field
	
	///Constructor
	public LogonCheckInterceptor(){
		System.out.println("\nCommon :: "+this.getClass()+"\n");		
	}
	
	///Method
	public boolean preHandle(	HttpServletRequest request,
														HttpServletResponse response, 
														Object handler) throws Exception {
		
		System.out.println("\n[ LogonCheckInterceptor start........]");
		
	
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		
		if(   user != null || request.getServletPath().equals("/findUser.do") || request.getServletPath().equals("/emailAuth.do") )  {
			
			String uri = request.getRequestURI();
			if(		uri.indexOf("addUserView") != -1 	|| 	uri.indexOf("addUser") != -1 || 
					uri.indexOf("loginView") != -1 			||	uri.indexOf("login") != -1 		|| 
					uri.indexOf("checkDuplication") != -1 ){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				
				System.out.println("[ LogonCheckInterceptor end........]\n");
				return false;
			}
			
			System.out.println("[ LogonCheckInterceptor end........]\n");
			return true;
		}else{ 
			String uri = request.getRequestURI();
			if(		uri.indexOf("addUserView") != -1 	|| 	uri.indexOf("addUser") != -1 || 
					uri.indexOf("loginView") != -1 			||	uri.indexOf("login") != -1 		|| 
					uri.indexOf("checkDuplication") != -1 ){
				
				System.out.println("[ LogonCheckInterceptor end........]\n");
				return true;
			}
			
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
			response.sendRedirect("/index.jsp");
			System.out.println("[ LogonCheckInterceptor end........]\n");
			return false;
		}
	}
}//end of class