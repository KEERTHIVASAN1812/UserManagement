package com.Servlet;

import com.DAO.UserDAO;
import com.entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	@WebServlet("/")
	public class UserListServlet extends HttpServlet{
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	public void init() {
	    userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    String action = request.getServletPath();

	    try {
	        switch (action) {
	            case "/new":
	                showNewForm(request, response);
	                break;
	            case "/insert":
	                insertUser(request, response);
	                break;
	            case "/delete":
	                deleteUser(request, response);
	                break;
	            case "/edit":
	                showEditForm(request, response);
	                break;
	            case "/update":
	                updateUser(request, response);
	                break;
	            default:
	                listUser(request, response);
	                break;
	        }
	    } 
	    catch (Exception e) {
	        throw new ServletException(e);
	    }
	}


	       private void listUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < User > listUser = userDAO.selectAllUsers();
		        request.setAttribute("listUser", listUser);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("userlist.jsp");
		        dispatcher.forward(request, response);
		    }
	       
	       
	       
	       protected void sendMail(String tomailid) {
	    	   
	    	   String host="localhost";  
	 		  final String user="cutieedharani@gmail.com";//change accordingly  
	 		  final String password="eykbibkoovpqbdyf";//change accordingly  
	 		    
	 		  String to=tomailid.trim();//change accordingly  
	 		  System.out.println("111222"); 
	 		   //Get the session object  
	 		  Properties properties = new Properties();
	 	        properties.put("mail.smtp.host", "smtp.gmail.com");
	 	        properties.put("mail.smtp.port", "587");
	 	        properties.put("mail.smtp.auth", "true");
	 	        properties.put("mail.smtp.starttls.enable", "true");
	 	        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
	 	        properties.setProperty("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

	 	        // creates a new session with an authenticator
	 	        Authenticator auth = new Authenticator() {
	 	            public PasswordAuthentication getPasswordAuthentication() {
	 	                return new PasswordAuthentication(user, password);
	 	            }
	 	        };
	 	 
	 	        Session session = Session.getInstance(properties, auth);
	 	 
	 	        // creates a new e-mail message
	 	      
	 	        try{
	 	        	Message msg = new MimeMessage(session);
	 	        
	 	 
	 	        msg.setFrom(new InternetAddress(user));
	 	        InternetAddress[] toAddresses = { new InternetAddress(to) };
	 	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	 	        msg.setSubject("");
	 	        msg.setSentDate(new Date());
	 	        msg.setText(" i am dharani...sorry for everything...i want u");
	 	        System.setProperty("https.protocols", "TLSv1.2");

	 	        
	 	        System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

	 	        // sends the e-mail
	 	        Transport.send(msg);
	         	System.out.println("Sent done");

	 	        }catch(Exception e) {
	 	        	System.out.println("Exception"+e.getMessage());
	 	        	System.out.println(e);
	 	        }
	 		 } 
	    	   
	    	  
	 		 
				
				 
	       
	       
		private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String uname=request.getParameter("name");
			String email=request.getParameter("email");
			int age=Integer.parseInt(request.getParameter("age"));
			String city=request.getParameter("city");
			int id = Integer.parseInt(request.getParameter("id"));

			User u = new User(id,uname, email, age, city);
			u.setId(id); 
			u.setUname(uname);
			u.setMailid(email);
			u.setAge(age);
			u.setCity(city);
			userDAO.updateUser(u);		
	        response.sendRedirect("list");

		}
		private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
	        User u=userDAO.editUser(id);
	        request.setAttribute("user", u);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("userform.jsp");
	        dispatcher.forward(request, response);

			
		}
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

			int id = Integer.parseInt(request.getParameter("id"));
	        userDAO.deleteUser(id);
	        response.sendRedirect("list");
	        
		}
		private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			
			String uname=request.getParameter("name");
			String email=request.getParameter("email");
			int age=Integer.parseInt(request.getParameter("age"));
			String city=request.getParameter("city");
			User u = new User(uname, email, age, city);
			u.setUname(uname);
			u.setMailid(email);
			u.setAge(age);
			u.setCity(city);
			 
			userDAO.insertUser(u);
			sendMail(email);
			response.sendRedirect("list");
		}
		
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("userform.jsp");
			        dispatcher.forward(request, response);
			    }
		
			
		}


