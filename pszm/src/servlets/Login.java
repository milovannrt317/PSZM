package servlets;
import beans.*;
import util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import java.net.InetAddress;
import java.net.DatagramSocket;

public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesija = request.getSession();
		String poruka = "";
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String captchaStr = (String) sesija.getAttribute("captcha");
		String captchaUsr = (String) request.getParameter("captcha");
		User korisnik = new User();
		UserDB korisnikDB = new UserDB();
				
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String address = "/";
		if(captchaStr!=null && !captchaStr.equals(captchaUsr)) {
			poruka="Captcha is not correct!";
		} 
		else {
			try {
				con = DB.getInstance().getConnection();
				stmt = con.createStatement();
				String upit = "select login.funCheckUser('"+username+"') as chk;";
				rs = stmt.executeQuery(upit);
				
				if(rs.next()) {
					int chk = rs.getInt("chk");
					if(chk==1) {
						upit = "select login.funGetSalt('"+username+"') as salt;";
						rs = stmt.executeQuery(upit);
						if(rs.next()) {
							String salt = rs.getString("salt");
							String hashedPass="";
							String msg="";
							try {
								hashedPass=korisnikDB.cipherPass(password, salt);
							} catch (Exception e) {
								e.printStackTrace();
							}
							upit = "{ call login.spLogin(?,?,?) }";
							CallableStatement stmt2 = con.prepareCall(upit);
							
							
							stmt2.setString(1, username);
							stmt2.setString(2, hashedPass);
							stmt2.registerOutParameter(3, java.sql.Types.VARCHAR);
							
	
				            rs = stmt2.executeQuery();
				            msg=stmt2.getString(3);
				            
				            
				            if(msg.startsWith("Success")) {
				            	Gson gson=new Gson();
			                    korisnik=gson.fromJson(msg.split("#")[1], User.class); 
			                    sesija.setAttribute("korisnik", korisnik);
			                    address = "prikaz.jsp";
				            }
				            else if(msg.startsWith("Wrong password")) {
				            	poruka = "Wrong username or password! Try again.";
				            }
				            else if(msg.startsWith("Captcha")) {
				            	poruka = "You failed 3 or more times, need to correct fill captcha input.";
								sesija.setAttribute("option","Captcha");
				            }
				            else if(msg.startsWith("Account lock")) {
				            	poruka = "This account is locked for 5 minutes!";
				            	sesija.invalidate();
				            }
				            else if(msg.startsWith("Recovery password")) {
				            	sesija.invalidate();
				            	String ip="";
				            	try(final DatagramSocket socket = new DatagramSocket()){
				            		  socket.connect(InetAddress.getByName("google.com"), 80);
				            		  ip = socket.getLocalAddress().getHostAddress();
				            	}
				            	String recPass=msg.replaceFirst("Recovery password#", "").split(";")[0];
								String toEmail=msg.replaceFirst("Recovery password#", "").split(";")[1];
				            	String msgEmail="Poštovani "+username+",\nObaveštavamo Vas da je na Vašem nalogu pokušano neuspešno logovanje više od 7 puta. Iz tog razloga Vaš nalog je zaključan i poslat je sledeći link sa kojim možete da otključate Vaš nalog. Link: https://"+ip+":8443/pszm/recovery?usr="+username+"&pass="+recPass+"\nMolimo Vas da u najkraćem mogućem roku promenite lozinku.\nSrdačan pozdrav";
								Mailer.send("email","password",toEmail,"Recovery password",msgEmail);
								int mailUserLength=toEmail.split("@")[0].length();
								char[] mailStar=toEmail.toCharArray();
								StringBuffer mailSt=new StringBuffer();
								for(int i=2;i<mailUserLength-2;i++)
									mailStar[i]='*';
								for(char c:mailStar)
									mailSt.append(c);
								poruka = "We sent link with recovery password to this email address: "+mailSt.toString()+". Please follow instructions to update your password!";
				            }
				            else {
				            	poruka=msg;
				            }
				            
				            stmt2.close();
						}
					} else {
						poruka = "Wrong username or password! Try again.";
						stmt.close();
					}
				}
				
			} catch (SQLException ex) {
				sesija.invalidate();
				String errormsg = ex.getMessage();
				request.setAttribute("errormsg", errormsg);
				address = "error.jsp";
			}
			catch (Exception ex) {
				sesija.invalidate();
				String errormsg = ex.getMessage();
				request.setAttribute("errormsg", errormsg);
				address = "error.jsp";
			}
	
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
		}
		request.setAttribute("poruka", poruka);
		RequestDispatcher rd = request.getRequestDispatcher(address);
		rd.forward(request, response);	
	}
}
