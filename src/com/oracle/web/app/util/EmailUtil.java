/**
 * 
 */
package com.oracle.web.app.util;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import com.oracle.web.util.PropertyManager;

import static com.oracle.web.app.constants.Constants.EMAIL_PROPERTIES;

/**
 * @author raparash
 *
 */
public class EmailUtil {

	private static final Logger LOGGER = Logger.getLogger(EmailUtil.class.getName());

	/**
	 * Method responsible to send Email
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param from
	 * @param subject
	 * @param body
	 * @param fileNames
	 * @return
	 */
	public static boolean sendEmail(List<String> to, List<String> cc, List<String> bcc, String from, String subject,
			String body, List<String> fileNames) {
		boolean isAuthenticationEnabled = false;
		Session session = null;
		Boolean isEmailSent = true;
		try {
			isAuthenticationEnabled = Boolean
					.parseBoolean(PropertyManager.getValue(EMAIL_PROPERTIES, "mail.authentication"));
			LOGGER.log(Level.INFO,
					"Inside|EmailUtil|sendEmail()| isPasswordAuthentication enabled = " + isAuthenticationEnabled);
			session = getSession(getEmailProperties(isAuthenticationEnabled), isAuthenticationEnabled);
			LOGGER.log(Level.INFO, "Inside|EmailUtil|sendEmail()| Session created = " + session);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			setMimeMessageWithAddresses(message, RecipientType.TO, to.toArray(new String[to.size()]));
			if (cc != null)
				setMimeMessageWithAddresses(message, (RecipientType) RecipientType.CC,
						cc.toArray(new String[cc.size()]));
			if (bcc != null)
				setMimeMessageWithAddresses(message, (RecipientType) RecipientType.BCC,
						bcc.toArray(new String[bcc.size()]));
			message.setSubject(subject);
			Multipart multipart = new MimeMultipart();
			addMimeBodyPart(multipart, BodyType.TEXT, body);
			if (fileNames != null)
				addMimeBodyPart(multipart, BodyType.ATTACHMENT, fileNames.toArray(new String[fileNames.size()]));
			message.setContent(multipart);
			Transport.send(message);
			isEmailSent = true;
			LOGGER.log(Level.INFO, "Inside|EmailUtil|sendEmail()| isEmailSent = " + isEmailSent);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception|EmailUtil|sendEmail()| ", e);
		}
		return isEmailSent;
	}

	/**
	 * Adds text and attachment in message body
	 * @param multipart
	 * @param bodyType
	 * @param bodyResources
	 */
	private static void addMimeBodyPart(Multipart multipart, BodyType bodyType, String... bodyResources) {
		BodyPart bodyPart = null;
		try {
			LOGGER.log(Level.INFO,"Inside|EmailUtil|addMimeBodyPart()| bodyType="+bodyType+" & bodyResources="+bodyResources);
			if (bodyType == BodyType.TEXT) {
				bodyPart = new MimeBodyPart();
				StringBuilder bodyContent = new StringBuilder();
				for (String bodyText : bodyResources) {
					bodyContent.append(bodyText);
				}
				bodyPart.setText(bodyContent.toString());
				multipart.addBodyPart(bodyPart);
			} else if (bodyType == BodyType.ATTACHMENT) {
				for (String file : bodyResources) {
					bodyPart = new MimeBodyPart();
					bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
					bodyPart.setFileName(file);
					multipart.addBodyPart(bodyPart);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception|Emailutil|addMimeBodyPart()| ", e);
		}

	}

	/**
	 * 
	 * @param message
	 * @param to
	 * @param emailAddrs
	 * @return
	 */
	private static MimeMessage setMimeMessageWithAddresses(MimeMessage message, javax.mail.Message.RecipientType recipientType,
			String... emailAddrs) {
		try {
			for (String email : emailAddrs) {
				message.addRecipient(recipientType, new InternetAddress(email));
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception|Emailutil|setMimeMessageWithAddresses()| ", e);
		}
		return message;
	}

	/**
	 * Gets the email properties
	 * 
	 * @param authetication
	 * @return
	 */
	private static Properties getEmailProperties(boolean authentication) {
		Properties props = new Properties();
		try {
			props.put("mail.smtp.host", PropertyManager.getValue(EMAIL_PROPERTIES, "mail.host"));
			props.put("mail.smtp.port", PropertyManager.getValue(EMAIL_PROPERTIES, "mail.port"));
			props.put("mail.smtp.socketFactory.port", PropertyManager.getValue(EMAIL_PROPERTIES, "mail.port"));
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			LOGGER.log(Level.INFO, "Exception|Emailutil|getSession()| authentication="+authentication);
			if (authentication) {
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception|Emailutil|getEmailProperties()| ", e);
		}
		return props;
	}

	/**
	 * Returns mail Session
	 * @param properties
	 * @param authentication
	 * @return
	 */
	private static Session getSession(Properties properties, boolean authentication) {
		Session session = null;
		try {
			session = Session.getInstance(properties);
			LOGGER.log(Level.INFO, "Exception|Emailutil|getSession()| authentication="+authentication);
			if (authentication) {
				final String username = PropertyManager.getValue(EMAIL_PROPERTIES, "mail.username");
				final String password = PropertyManager.getValue(EMAIL_PROPERTIES, "mail.password");
				session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception|Emailutil|getSession()| ", e);
		}
		return session;
	}
}
