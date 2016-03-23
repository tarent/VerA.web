package org.evolvis.veraweb.onlinereg.mail;

import org.evolvis.veraweb.onlinereg.utils.VworConstants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MailDateFormat;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author Atanas Alexandrov, tarent solutions GmbH
 */
public class MailDispatcher {
    private static final String PROPERTY_MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
	private static final String PROPERTY_MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String PROPERTY_MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String PROPERTY_MAIL_SMTP_AUTH = "mail.smtp.auth";

	private static final String TYPE_SSL = "ssl";
	private static final String TYPE_STARTTLS = "starttls";
	private static final String TYPE_HTML = "html";

	private MailDateFormat dateFormat = new MailDateFormat();

    private String host;
    private Integer port;
    private String security;
    private String username;
    private String password;
    private Transport transport;
    private Session session;

    public MailDispatcher(final EmailConfiguration emailConfiguration) throws NoSuchProviderException {
        this.host = emailConfiguration.getHost();
        this.port = emailConfiguration.getPort();
        this.security = emailConfiguration.getSecurity();
        if (this.security == null) {
            this.security = "none";
        }
        this.username = emailConfiguration.getUsername();
        this.password = emailConfiguration.getPassword();
        session = getSession();
        transport = session.getTransport("smtp");
    }

    public void sendVerificationEmail(String from, String to, String subject, String text, String link, String contentType) throws MessagingException {
        final String emailContent = text.replace("${link}", link);
        final Message message;
        if (TYPE_HTML.equalsIgnoreCase(contentType)) {
            message = getMessage(session, from, to, subject, emailContent, VworConstants.HTML_CONTENT_TYPE);
        } else {
            message = getMessage(session, from, to, subject, emailContent, VworConstants.PLAINTEXT_CONTENT_TYPE);
        }
        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public void sendEmailWithAttachments(String from, String to, String subject, String emailContent, Map<String, File> attachments) throws MessagingException {
        final Multipart multipart = new MimeMultipart();
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(emailContent);
        multipart.addBodyPart(messageBodyPart);
        if (attachments != null && !attachments.isEmpty()) {
            for (Entry<String, File> entry : attachments.entrySet()) {
                final DataSource attachment = new FileDataSource(entry.getValue());
                final MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.setDataHandler(new DataHandler(attachment));
                attachmentBodyPart.setFileName(entry.getKey());
                multipart.addBodyPart(attachmentBodyPart);
            }
        }
        final Message message = getMessage(session, from, to, subject, multipart, multipart.getContentType());
        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private Message getMessage(Session session, String from, String to, String subject, Object text, String contentType) throws MessagingException {
        final MimeMessage message = initMessage(text, contentType, session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setHeader("Date", dateFormat.format(new Date(System.currentTimeMillis())));
        message.saveChanges();
        return message;
    }

    private MimeMessage initMessage(Object text, String contentType, Session session) throws MessagingException {
        final MimeMessage message = new MimeMessage(session);
        message.setContent(text, contentType);
        return message;
    }

    private Session getSession() {
        final Properties properties = System.getProperties();
        if (username != null && password != null) {
            properties.put(PROPERTY_MAIL_SMTP_AUTH, "true");
        }
        if (port != null) {
            properties.put(PROPERTY_MAIL_SMTP_PORT, port);
        }
        setSecurityProperties(properties);

        return Session.getInstance(properties);
    }

    private void setSecurityProperties(Properties properties) {
        if (TYPE_STARTTLS.equals(security)) {
            properties.put(PROPERTY_MAIL_SMTP_STARTTLS_ENABLE, true);
        } else if (TYPE_SSL.equals(security)) {
            properties.put(PROPERTY_MAIL_SMTP_SSL_ENABLE, true);
        }
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

}
