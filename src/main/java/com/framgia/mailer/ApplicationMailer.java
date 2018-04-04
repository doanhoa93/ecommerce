package com.framgia.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailer")
public class ApplicationMailer {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private TaskExecutor taskExecutor;

	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		taskExecutor.execute(new MailerRunnable(mailSender, simpleMailMessage));
	}
}
