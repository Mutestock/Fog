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

/**
 *
 * @author Henning
 */
public class EmailHandler {

    /**
     * Method which sends emails to a client via google's SMTP.
     * Utilises the properties package and the Javax mail plugins to gain connection with an SMTP server.
     * This method uses google's SMTP: smtp.gmail.com
     * Because of this, the sender of the email can only be a gmail account.
     * 
     * Extracts information from the offer parameter and sends it to the email address specified from the client upon ordering.
     * Username and password of the sender's gmail account is necessary.
     * 
     * @param offer The offer parameters contains most of the information for the entire process.
     * @throws NoSuchProviderException Looks for SMTP providers. E.g if the google SMTP didn't exist.
     * @throws MessagingException The base class for all exceptions thrown by the Messaging classes 
     */
    public static void sendMail(Offer offer) throws NoSuchProviderException, MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
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
        t.close();
    }
}
