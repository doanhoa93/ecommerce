package com.framgia.job;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import redis.clients.jedis.Jedis;

public class EmailTask {

	private MailSender mailSender;

	@SuppressWarnings({ "resource", "unchecked" })
	public void sendEmail() {
		try {
			Jedis jedis = new Jedis();
			Set<byte[]> emailBytes = jedis.smembers("mails".getBytes());
			if (!emailBytes.isEmpty()) {
				ApplicationContext context = new ClassPathXmlApplicationContext(
				    "/WEB-INF/configs/spring-mail-config.xml");
				mailSender = (MailSender) context.getBean("mailSender");
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				ByteArrayInputStream bis;
				ObjectInput in;
				for (byte[] emailByte : emailBytes) {
					bis = new ByteArrayInputStream(emailByte);
					in = new ObjectInputStream(bis);
					HashMap<String, String> map = (HashMap<String, String>) in.readObject();
					simpleMailMessage.setTo(map.get("to"));
					simpleMailMessage.setSubject(map.get("subject"));
					simpleMailMessage.setText(map.get("body"));

					mailSender.send(simpleMailMessage);
				}

				jedis.del("mails".getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
