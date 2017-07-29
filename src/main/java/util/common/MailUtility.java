package util.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Mail utility<br>
 * <p>
 * This class is a utility for sending mail using Commons Email.
 */
public class MailUtility {
	
	private static final String CON_ENCODE = "base64";
	private static final String CON_CHARSET = "UTF-8";
	
	private String smtpHost = null;
	private int smtpPort = 587;
	private String username = null;
	private String password = null;
	private String charset = null;
	private String encode = null;
	private boolean startTLS = false;
	private boolean debug = false;
	private String subject = null;
	private String message = null;
	private int connectionTimeout = 5000;
	private int timeout = 5000;
	private String fromAddr = null;
	private String fromName = null;
	private List<MailAddressSet> toAddrList = null;
	private List<MailAddressSet> ccAddrList = null;
	private List<MailAddressSet> bccAddrList = null;
	
	/**
	 * Private constructor
	 */
	private MailUtility() {
		toAddrList = new LinkedList<MailAddressSet>();
		ccAddrList = new LinkedList<MailAddressSet>();
		bccAddrList = new LinkedList<MailAddressSet>();
	}
	
	/**
	 * Static factory
	 * @return instance
	 */
	public static MailUtility getInstance() {
		return new MailUtility();
	}
	
	/* Setter */
	public MailUtility setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
		return this;
	}
	
	public MailUtility setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
		return this;
	}
	
	public MailUtility setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public MailUtility setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public MailUtility setCharset(String charset) {
		this.charset = charset;
		return this;
	}
	
	public MailUtility setEncode(String encode) {
		this.encode = encode;
		return this;
	}
	
	public MailUtility setStartTLS(boolean startTLS) {
		this.startTLS = startTLS;
		return this;
	}
	
	public MailUtility setDebug(boolean debug) {
		this.debug = debug;
		return this;
	}
	
	public MailUtility setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public MailUtility setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public MailUtility setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}
	
	public MailUtility setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}
	
	public MailUtility setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
		this.fromName = fromAddr;
		return this;
	}
	
	public MailUtility setFromAddrWithName(String fromAddr, String username) {
		this.fromAddr = fromAddr;
		this.fromName = username;
		return this;
	}
	
	public MailUtility setToAddr(String toAddr) {
		this.toAddrList.add(new MailAddressSet(toAddr, toAddr));
		return this;
	}
	
	public MailUtility setToAddrWithName(String toAddr, String username) {
		this.toAddrList.add(new MailAddressSet(toAddr, username));
		return this;
	}
	
	public MailUtility setCcAddr(String ccAddr) {
		this.ccAddrList.add(new MailAddressSet(ccAddr, ccAddr));
		return this;
	}
	
	public MailUtility setCcAddrWithName(String ccAddr, String username) {
		this.ccAddrList.add(new MailAddressSet(ccAddr, username));
		return this;
	}
	
	public MailUtility setBccAddr(String bccAddr) {
		this.bccAddrList.add(new MailAddressSet(bccAddr, bccAddr));
		return this;
	}
	
	public MailUtility setBccAddrWithName(String bccAddr, String username) {
		this.bccAddrList.add(new MailAddressSet(bccAddr, username));
		return this;
	}
	
	/**
	 * Mail send (Use Commons Email)
	 * @return success:true, failure:false
	 * @throws EmailException EmailException
	 */
	public boolean sendMail() throws EmailException {
		
		if (!checkPropertyValues()) {
			return false;
		}
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Transfer-Encoding", this.encode);
		
		Email email = new SimpleEmail();
		email.setHostName(this.smtpHost);
		email.setSmtpPort(this.smtpPort);
		email.setCharset(this.charset);
		email.setHeaders(headers);
		email.setStartTLSEnabled(this.startTLS);
		
		email.setAuthenticator(
				new DefaultAuthenticator(this.username, this.password));
		
		email.setFrom(this.fromAddr, this.fromName, this.charset);
		for (MailAddressSet set : this.toAddrList) {
			email.addTo(set.getAddress(), set.getUsername(), this.charset);
		}
		for (MailAddressSet set : this.ccAddrList) {
			email.addCc(set.getAddress(), set.getUsername(), this.charset);
		}
		for (MailAddressSet set : this.bccAddrList) {
			email.addBcc(set.getAddress(), set.getUsername(), this.charset);
		}
		
		email.setSubject(this.subject);
		email.setMsg(this.message);
		email.setSocketTimeout(this.timeout);
		email.setSocketConnectionTimeout(this.connectionTimeout);
		email.setDebug(this.debug);
		
		email.send();
		
		return true;
	}
	
	/**
	 * Property check for sending mail
	 * @return success:true, failure:false
	 */
	private boolean checkPropertyValues() {
		
		if (this.smtpHost == null) { return false; }
		if (this.username == null) { return false; }
		if (this.username == null) { return false; }
		if (this.password == null) { return false; }
		if (this.fromAddr == null) { return false; }
		if (this.subject == null) { return false; }
		if (this.message == null) { return false; }
		if (this.toAddrList.size() == 0 && this.ccAddrList.size() == 0 && 	this.bccAddrList.size() == 0) {
			return false;
		}
		if (this.charset == null) { this.charset = CON_CHARSET.toString(); }
		if (this.encode == null) { this.encode = CON_ENCODE.toString(); }
		
		return true;
	}
	
	/**
	 * Storing user and mail addresses
	 */
	private class MailAddressSet {
		
		private String address;
		private String username;
		
		public MailAddressSet(String address, String username) {
			this.address = address;
			this.username = username;
		}
		
		public String getAddress() { 	return this.address; }
		public String getUsername() { return this.username; }
	}
	
}
