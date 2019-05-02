package logic.offer;

/**
 *
 * @author Henning
 */
import com.sun.mail.smtp.SMTPTransport;
import data.help_classes.Offer;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EmailHandler {

//    public static void main(String[] args) throws Exception {
//
//        mailSend("cake", "cake");
//
//    }
    public static void mailSend(Offer offer) throws NoSuchProviderException, MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        System.out.println("Fullname: " + offer.getRequest().getCustomer().getFullName());
        msg.setFrom(new InternetAddress(offer.getRequest().getCustomer().getEmail()));;
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(offer.getRequest().getCustomer().getEmail(), false));
        msg.setSubject("We have an offer for you: " + offer.getRequest().getCustomer().getFullName());
        msg.setText(
                 "Price: " + Double.toString(offer.getPrice()) +"\n"
                + "Dimensions: "+Integer.toString(offer.getRequest().getCarport().getWidth())
                + "x"+Integer.toString(offer.getRequest().getCarport().getLength())
                + "x"+Integer.toString(offer.getRequest().getCarport().getHeight()) + " cm\n"
                + "Customer Name: " + offer.getRequest().getCustomer().getFullName() + "\n"
                + "Address: " + offer.getRequest().getCustomer().getAddress() + "\n"
                + "Phone: " + offer.getRequest().getCustomer().getPhone() + "\n"
                + "Email: " + offer.getRequest().getCustomer().getEmail() + "\n"
                + "Comments: " + offer.getRequest().getComments()+"\n"
                + "Time of Approval: " + System.currentTimeMillis()+"\n"
                + "Price: " + offer.getPrice()+"\n"
                + "Shipping cost: " +offer.getShippingCosts()+"\n \n \n"
                + "Best regards \n Johannes Fog");
        msg.setHeader("Johannes Fog", "Johannes Fog");
        msg.setSentDate(new Date());
        SMTPTransport t
                = (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.gmail.com", "asdfPO21sadeL19@gmail.com", "cakeman596912");
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    }
}
