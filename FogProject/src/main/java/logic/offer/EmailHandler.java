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

    public static void sendMail(Offer offer) throws NoSuchProviderException, MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        System.out.println("Navn: " + offer.getRequest().getCustomer().getFullName());
        msg.setFrom(new InternetAddress(offer.getRequest().getCustomer().getEmail()));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(offer.getRequest().getCustomer().getEmail(), false));
        msg.setSubject("Vi har et tilbud til dig: " + offer.getRequest().getCustomer().getFullName());
        msg.setText(
                "Pris: " + Double.toString(offer.getPrice()) + "\n"
                + "Dimensioner: " + Integer.toString(offer.getRequest().getCarport().getWidth())
                + "x" + Integer.toString(offer.getRequest().getCarport().getLength())
                + "x" + Integer.toString(offer.getRequest().getCarport().getHeight()) + " cm\n"
                + "Kundenavn: " + offer.getRequest().getCustomer().getFullName() + "\n"
                + "Adresse: " + offer.getRequest().getCustomer().getAddress() + "\n"
                + "Telefon: " + offer.getRequest().getCustomer().getPhone() + "\n"
                + "Email: " + offer.getRequest().getCustomer().getEmail() + "\n"
                + "Kommentarer: " + offer.getRequest().getComments() + "\n"
                + "Godkendt: " + System.currentTimeMillis() + "\n"
                + "Pris: " + offer.getPrice() + "\n"
                + "Fragtomkostninger: " + offer.getShippingCosts() + "\n \n \n"
                + "De bedste hilsner \n Johannes Fog");
        msg.setHeader("Johannes Fog", "Johannes Fog");
        msg.setSentDate(new Date());
        SMTPTransport t
                = (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.gmail.com", EmailUtility.getEmailAddress(), EmailUtility.getPassword());
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    }

    

}
