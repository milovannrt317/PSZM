package servlets;
import beans.*;
import util.DB;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Signup extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		rd.forward(request, response);	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poruka = "";
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String firstname = (String) request.getParameter("firstname");
		String lastname = (String) request.getParameter("lastname");
		String email = (String) request.getParameter("email");
		
		UserDB korisnikDB = new UserDB();
		
		korisnikDB.setUsername(username);
		try {
			korisnikDB.setPassword(korisnikDB.cipherPass(password,null));
		} catch (Exception e) {
		}
		korisnikDB.setFirstname(firstname);
		korisnikDB.setLastname(lastname);
		korisnikDB.setEmail(email);
		
		User korisnik = new User(korisnikDB);
		
		String address = "signup.jsp";

		Connection con = null;
		try {
			con = DB.getInstance().getConnection();
			String upit = "{ call login.spSignup(?, ?, ?, ?, ?, ?, ?) }";
			CallableStatement stmt = con.prepareCall(upit);
			stmt.setString(1, korisnikDB.getUsername());
			stmt.setString(2, korisnikDB.getEmail());
			stmt.setString(3, korisnikDB.getPassword());
			stmt.setString(4, korisnikDB.getSalt());
			stmt.setString(5, korisnikDB.getFirstname());
			stmt.setString(6, korisnikDB.getLastname());
			stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			

			stmt.executeQuery();
            String msg=stmt.getString(7);
            
            if(msg.equals("Successfully added!")) {
            	poruka = "You have successfully sign up into the system. Now please log in with your account!";
            	address = "/";
            }
            else {
            	poruka = msg;
            	request.setAttribute("user", korisnik);
            }
            
		} catch (SQLException ex) {
			String errormsg = ex.getMessage();
			request.setAttribute("errormsg", errormsg);
			address = "error.jsp";
		}
		request.setAttribute("poruka", poruka);
		RequestDispatcher rd = request.getRequestDispatcher(address);
		rd.forward(request, response);
	}
}
