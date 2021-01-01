package servlets;
import util.DB;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Recovery extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = (String) request.getParameter("usr");
		String rcvrypassword = (String) request.getParameter("pass");
		
		Connection con = null;
		String address = "/";
		String poruka="";
		try {
			con = DB.getInstance().getConnection();
			String upit = "{ call login.spCheckRecoveryPass(?, ?, ?) }";
			CallableStatement stmt = con.prepareCall(upit);
			stmt.setString(1, username);
			stmt.setString(2, rcvrypassword);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			
			stmt.executeQuery();
            String msg=stmt.getString(3);
            
            if(msg.equals("Success!")) {
            	poruka = "Successful try to recover password. Please set your new password!";
            	address = "/update";
            	request.setAttribute("username", username);
            }
            else if(msg.equals("Fail!")) {
            	poruka = "Unsuccessful try to recover your password!";
            }
            else {
            	poruka = "This user don have recovery password!";
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
