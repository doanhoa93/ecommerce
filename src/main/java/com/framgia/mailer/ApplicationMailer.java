package com.framgia.mailer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service("mailer")
public class ApplicationMailer {

	@SuppressWarnings({ "resource" })
	public void sendMail(String to, String subject, String body) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			Jedis jedis = new Jedis();
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("to", to);
			hashMap.put("subject", subject);
			hashMap.put("body", body);
			out = new ObjectOutputStream(bos);
			out.writeObject(hashMap);
			out.flush();
			byte[] mailBytes = bos.toByteArray();
			jedis.sadd("mails".getBytes(), mailBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
