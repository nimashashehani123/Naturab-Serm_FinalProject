package lk.ijse.Naturab.Controller;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class Mail {
        public static void sendMail(String recepient)throws MessagingException {
            try {
                System.out.println("Preparing to send email");
                Properties properties = new Properties();


                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                String myAccountEmail = "nimashashehani0715@gmail.com";
                String password = "fixjkdflxxgcftkk";

                Session session = Session.getInstance(properties, new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myAccountEmail, password);
                    }
                });


                Message message = prepareMessage(session, myAccountEmail, recepient);
                try {
                    Transport.send(message);
                    System.out.println("Email Send successfully");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }catch (Exception ex){
                new Alert(Alert.AlertType.ERROR, "Connect Internet Connection ........... !!").show();
            }
        }

        private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
            try {
                Message message = new MimeMessage(session);
                ((MimeMessage) message).setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
                message.setSubject("machine alert");
                message.setText("machine is broken");
                return message;
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Connect Internet Connection !!").show();
            }
            return null;
        }

}

