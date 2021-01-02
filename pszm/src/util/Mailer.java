package util;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    

public class Mailer{  
    public static void send(String from,String password,String to,String sub,String msg){     
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
            
          Session session = Session.getInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg,"UTF-8");
           Transport.send(message); 
          } catch (MessagingException e) {System.out.println(e.getMessage());}    
             
    }  
}  