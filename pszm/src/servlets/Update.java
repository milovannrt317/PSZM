package servlets;
import beans.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import util.DB;


public class Update extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
		rd.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesija = request.getSession();
		String poruka = "";
		UserDB korisnik = new UserDB();
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		korisnik.setUsername(username);
		try {
			korisnik.setPassword(korisnik.cipherPass(password, null));
		} catch (Exception e) {
		}
		String captchaStr = (String) sesija.getAttribute("captcha");
		String captchaUsr = (String) request.getParameter("captcha");
		
		
		Connection con = null;
		String address = "update.jsp";
		
		if(captchaStr!=null && !captchaStr.equals(captchaUsr)) {
			poruka="Captcha is not correct!";
			request.setAttribute("username", username);
		} 
		else {
			try {
				con = DB.getInstance().getConnection();
				String upit = "{ call login.spChangePassword(?, ?, ?, ?) }";
				CallableStatement stmt = con.prepareCall(upit);
				
				stmt.setString(1, korisnik.getUsername());
				stmt.setString(2, korisnik.getPassword());
				stmt.setString(3, korisnik.getSalt());
				stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
				

				stmt.executeQuery();
	            String msg=stmt.getString(4);
	            
	            if(msg.equals("Successfully updated!")) {
	            	poruka = "Successfully changed password. Now you can log in with your account!";
	            	address = "/";
	            	sesija.invalidate();
	            }
	            else {
	            	poruka = msg;
	            	request.setAttribute("username", username);
	            }
			} catch (SQLException ex) {
				sesija.invalidate();
				String errormsg = ex.getMessage();
				request.setAttribute("errormsg", errormsg);
				address = "error.jsp";
			}
		}
		request.setAttribute("poruka", poruka);
		RequestDispatcher rd = request.getRequestDispatcher(address);
		rd.forward(request, response);

	}
	
}