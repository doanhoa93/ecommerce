package com.framgia.mailer;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailerRunnable implements Runnable {
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;

	public MailerRunnable(MailSender mailSender, SimpleMailMessage simpleMailMessage) {
		this.mailSender = mailSender;
		this.simpleMailMessage = simpleMailMessage;
	}

	@Override
	public void run() {
		mailSender.send(simpleMailMessage);
	}
}
