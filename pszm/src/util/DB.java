package util;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.conf.PropertyDefinitions.SslMode;

public class DB {
	private static DB instance;
	private static Connection conn;
	private static String ip="192.168.0.23";
	private static final String port="3306";
	private static final String dbname="login";
	private static final String user="remote";
	private static final String password="password";
	private static final Properties properties = new Properties();
	
	private DB() {
		try {
			properties.put("sslMode", SslMode.VERIFY_CA.name());
			properties.put("user", user);
			properties.put("password", password);
			properties.put("trustCertificateKeyStoreUrl", "file:C:\\Users\\Korisnik\\Desktop\\CertificatesMysql\\javaKeystore\\truststore");
			properties.put("trustCertificateKeyStorePassword", "mypassword");
			properties.put("clientCertificateKeyStoreUrl", "file:C:\\Users\\Korisnik\\Desktop\\CertificatesMysql\\javaKeystore\\keystore");
			properties.put("clientCertificateKeyStorePassword", "mypassword");
			
			conn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+dbname, properties);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
		}
		return instance;
	}
	public Connection getConnection() {
		return conn;
	}
}