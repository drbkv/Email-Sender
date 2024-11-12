# Email-Sender
This Java application allows sending emails with CSV-specified recipients and an optional file attachment through the Mail.ru SMTP server.

Features

Sends emails to multiple recipients, specified in a CSV file.
Allows specifying a subject and message body.
Supports attaching a file to the email.
Configured for use with the Mail.ru SMTP server.
Getting Started

Prerequisites
To run this application, you need:

JDK 17 or higher
Maven
An SMTP-compatible Mail.ru account with an application-specific password enabled.
Configuration
Update the following fields in EmailSender.java with your own Mail.ru SMTP credentials:


private static final String USERNAME = "your-email@mail.ru";

private static final String PASSWORD = "your-application-password";

File Paths
In EmailSender.java, specify the paths for:

csvFilePath: the CSV file with recipient email addresses.
attachmentPath: the file you want to attach to the email.
Running the Application
Clone this repository.
Update the USERNAME, PASSWORD, csvFilePath, and attachmentPath variables in EmailSender.java.
Run the application using:
mvn clean compile exec:java

The application will read emails from the specified CSV, attach the specified file, and send it to each listed recipient.

CSV File Format

The CSV file should contain one email address per line in the first column, without a header row.

Example:

recipient1@example.com

recipient2@example.com

recipient3@example.com

Project Structure

EmailSender.java: Main application file.
pom.xml: Maven configuration file with dependencies.
Dependencies

JavaMail API for email functionality.
Apache Commons CSV for CSV file processing.
