//package com.example.emailsender;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//public class EmailSender {
//    private static final String SMTP_HOST = "smtp.mail.ru"; // SMTP сервер Mail.ru
//    private static final String SMTP_PORT = "587"; // Порт для TLS
//    private static final String USERNAME = "daurbekov.001@mail.ru"; // Ваш адрес Mail.ru
//    private static final String PASSWORD = "scyLUX39QjiWUcCLpuqN"; // Ваш пароль от почты Mail.ru
//
//
//    public static void main(String[] args) throws IOException {
//        String csvFilePath = "/Users/ramzan/Desktop/EmailSender/src/main/java/com/example/emailsender/email.csv";
//        String subject = "Заголовок письма";
//        String messageText = "Текст сообщения";
//
//        List<String> emailList = readEmailsFromCSV(csvFilePath);
//        sendEmails(emailList, subject, messageText);
//    }
//
//    private static List<String> readEmailsFromCSV(String filePath) throws IOException {
//        List<String> emails = new ArrayList<>();
//        FileReader fileReader = new FileReader(filePath);
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(fileReader);
//
//        for (CSVRecord record : records) {
//            String email = record.get(0); // Считаем, что email в первом столбце
//            emails.add(email);
//        }
//        return emails;
//    }
//
//    private static void sendEmails(List<String> emailList, String subject, String messageText) {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", SMTP_HOST);
//        properties.put("mail.smtp.port", SMTP_PORT);
//
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(USERNAME, PASSWORD);
//            }
//        });
//
//        for (String recipient : emailList) {
//            try {
//                Message message = new MimeMessage(session);
//                message.setFrom(new InternetAddress(USERNAME));
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//                message.setSubject(subject);
//                message.setText(messageText);
//                Transport.send(message);
//                System.out.println("Email sent to " + recipient);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

/////////////// с ФАЙЛОМ
package com.example.emailsender;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    private static final String SMTP_HOST = "smtp.mail.ru"; // SMTP сервер Mail.ru
    private static final String SMTP_PORT = "587"; // Порт для TLS
    private static final String USERNAME = "your-email@mail.ru";
    private static final String PASSWORD = "your-application-password";


    public static void main(String[] args) throws IOException {
        String csvFilePath = "email.csv";// Путь к файлу
        String subject = "Заголовок письма";
        String messageText = "Текст сообщения";
        String attachmentPath = ""; // Путь к файлу для вложения

        List<String> emailList = readEmailsFromCSV(csvFilePath);
        sendEmails(emailList, subject, messageText, attachmentPath);
    }

    private static List<String> readEmailsFromCSV(String filePath) throws IOException {
        List<String> emails = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(fileReader);

        for (CSVRecord record : records) {
            String email = record.get(0); // Считаем, что email в первом столбце
            emails.add(email);
        }
        return emails;
    }

    private static void sendEmails(List<String> emailList, String subject, String messageText, String attachmentPath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        for (String recipient : emailList) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(USERNAME));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                message.setSubject(subject);

                // Создаем тело сообщения
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(messageText);

                // Создаем вложение
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(attachmentPath));

                // Собираем части сообщения
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

                // Устанавливаем Multipart как контент сообщения
                message.setContent(multipart);

                // Отправляем сообщение
                Transport.send(message);
                System.out.println("Email sent to " + recipient);
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
