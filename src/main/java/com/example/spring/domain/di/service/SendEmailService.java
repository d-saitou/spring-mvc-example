package com.example.spring.domain.di.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Service that sends emails by asynchronous processing.
 * This class implements Spring's asynchronous processing. This class uses {@link Async}
 * annotation to implement mail sending function as asynchronous processing.
 * Parameters for sending mail are stored in application.properties and referenced by
 * {@link Value} annotation.
 */
@Service
//@SessionScope
@Slf4j
public class SendEmailService {

	@Value("${javax.mail.smtpHost}")
	private String smtpHost;

	@Value("${javax.mail.smtpPort}")
	private int smtpPort;

	@Value("${javax.mail.startTLS}")
	private boolean startTLS;

	@Value("${javax.mail.username}")
	private String username;

	@Value("${javax.mail.password}")
	private String password;

	@Value("${javax.mail.charset}")
	private String charset;

	@Value("${javax.mail.encode}")
	private String encode;

	@Value("${javax.mail.connectionTimeout}")
	private int connectionTimeout;

	@Value("${javax.mail.timeout}")
	private int timeout;

	@Value("${javax.mail.debug}")
	private boolean debug;

	@Value("${javax.mail.fromAddr}")
	private String fromAddr;

	/**
	 * Send email by asynchronous processing.
	 * @param toAddr  destination email address.
	 * @param subject email subject.
	 * @param body    email body.
	 * @return CompletableFuture
	 */
	@Async
	public CompletableFuture<Boolean> sendMail(String toAddr, String subject, String body) {
		log.info("email sending thread - start. (address:" + toAddr + ")");
		boolean isSuccess = false;
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Transfer-Encoding", this.encode);

			Email email = new SimpleEmail();
			email.setHostName(this.smtpHost);
			email.setSmtpPort(this.smtpPort);
			email.setCharset(this.charset);
			email.setHeaders(headers);
			email.setStartTLSEnabled(this.startTLS);
			email.setAuthenticator(new DefaultAuthenticator(this.username, this.password));
			email.setFrom(this.fromAddr, this.fromAddr, this.charset);
			email.addTo(toAddr, toAddr, this.charset);
			email.setSubject(subject);
			email.setMsg(body);
			email.setSocketTimeout(this.timeout);
			email.setSocketConnectionTimeout(this.connectionTimeout);
			email.setDebug(this.debug);

			email.send();
			isSuccess = true;
			log.info("email sending thread - completed. (address:" + toAddr + ")");
		} catch (Exception e) {
			log.info("email sending thread - failed. (address:" + toAddr + ")");
		}
		return CompletableFuture.completedFuture(Boolean.valueOf(isSuccess));
	}

}
