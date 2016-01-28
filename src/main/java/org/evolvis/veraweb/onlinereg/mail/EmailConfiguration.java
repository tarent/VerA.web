package org.evolvis.veraweb.onlinereg.mail;

import org.evolvis.veraweb.onlinereg.utils.VworPropertiesReader;

/**
 * @author Atanas Alexandrov, tarent solutions GmbH
 */
public class EmailConfiguration {
    private String host;
    private Integer port;
    private String security;
    private String username;
    private String password;
    private String from;
    private String subject;
    private String content;
    private VworPropertiesReader vworPropertiesReader;

    public EmailConfiguration(String currentLanguageKey) {
        final VworPropertiesReader propertiesReader = getVworPropertiesReader();
        readProperties(currentLanguageKey, propertiesReader);
    }

    public void readProperties(String currentLanguageKey, VworPropertiesReader propertiesReader) {
        this.host = propertiesReader.getProperty("mail.smtp.host");
        if (propertiesReader.getProperty("mail.smtp.port") == null) {
            this.port = 25;
        } else {
            this.port = new Integer(propertiesReader.getProperty("mail.smtp.port"));
        }
        this.security = propertiesReader.getProperty("mail.smtp.security");
        this.username = propertiesReader.getProperty("mail.smtp.user");
        this.password = propertiesReader.getProperty("mail.smtp.password");
        this.from = propertiesReader.getProperty("mail.smtp.from");
        this.subject = propertiesReader.getProperty("mail.subject." + currentLanguageKey);
        this.content = propertiesReader.getProperty("mail.content." + currentLanguageKey);
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getSecurity() {
        return security;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public VworPropertiesReader getVworPropertiesReader() {
        if (vworPropertiesReader == null) {
            vworPropertiesReader = new VworPropertiesReader();
        }
        return vworPropertiesReader;
    }

    public void setVworPropertiesReader(VworPropertiesReader vworPropertiesReader) {
        this.vworPropertiesReader = vworPropertiesReader;
    }
}
