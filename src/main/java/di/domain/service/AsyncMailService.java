package di.domain.service;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import util.common.MailUtility;

/**
 * "Asynchronous (send mail)" screen service<br>
 * <p>
 *  This class implements Spring's asynchronous processing.
 *  This class uses Async annotation to implement mail sending function 
 *  as asynchronous processing.
 * <p>
 *  In Spring, asynchronous processing can be executed with two kinds of 
 *  threads under Spring MVC management and outside Spring MVC management.
 *  Asynchronous common function in Spring MVC managed thread and Spring 
 *  MVC unmanaged thread is implemented in the following classes.<br>
 *  * CustomCallableProcessingInterceptor<br>
 *  * CustomDeferredResultProcessingInterceptor
 * <p>
 *  Parameters for sending mail are stored in application.properties and 
 *  referenced by Value annotation.
 * <p>
 *  In order to use asynchronous processing, it is necessary to set the 
 *  configulation in web.xml and Spring configuration.<br>
 *  * See "asynchronous" comment part in web.xml.<br>
 *  * See the comment including "asynchronous" in application-config.xml.
 */
@Service @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class AsyncMailService {
	
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
	
	@Getter @Value("${javax.mail.toAddr}")
	private String toAddr;
	
	@Getter @Value("${javax.mail.fromAddr}")
	private String fromAddr;
	
	@Setter
	private String subject;
	
	@Setter
	private String message;
	
	/**
	 * Asynchronous mail send
	 * @param successUrl request URL when mail send succeeded
	 * @param failedUrl  request URL when mail send failed
	 * @return CompletableFuture
	 */
	@Async
	public CompletableFuture<String> sendMail(String successUrl, String failedUrl) {
		String url = null;
		boolean isSuccess = false;
		try {
			log.info("Start sending email.");
			isSuccess = MailUtility.getInstance()
					.setSmtpHost(this.smtpHost).setSmtpPort(this.smtpPort)
					.setUsername(this.username).setPassword(this.password)
					.setCharset(this.charset).setEncode(this.encode)
					.setStartTLS(this.startTLS).setDebug(this.debug)
					.setFromAddr(this.fromAddr).setToAddr(this.toAddr)
					.setSubject(this.subject).setMessage(this.message)
					.setConnectionTimeout(this.connectionTimeout).setTimeout(this.timeout)
					.sendMail();
			if (isSuccess) {
				url = successUrl;
				log.info("Succeeded to send mail.");
			} else {
				url = failedUrl;
				log.error("Failed to send email.");
			}
		} catch (EmailException e) {
			url = failedUrl;
			log.error("Failed to send email.", e);
		}
		return CompletableFuture.completedFuture(url);
	}
	
}
