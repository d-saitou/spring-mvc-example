package com.example.springmvc.domain.entity.jpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;

/**
 * JPA entity (table: m_user).
 */
@Entity
@Table(name = "m_user")
@Data
public class MUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", nullable = false, length = 10)
	private String userId;

	@Column(name = "user_name", length = 40)
	private String userName;

	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "email_address_1", length = 50)
	private String emailAddress1;

	@Column(name = "email_address_2", length = 50)
	private String emailAddress2;

	@Column(name = "gender", length = 1)
	private String gender;

	@Column(name = "nationality_1", length = 2)
	private String nationality1;

	@Column(name = "nationality_2", length = 2)
	private String nationality2;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "address", length = 50)
	private String address;

	@Column(name = "credit_card_no", length = 16)
	private String creditCardNo;

	@Column(name = "credit_card_expiration_date")
	private LocalDate creditExpirationDate;

	@Column(name = "password_hint", length = 1)
	private String passwordHint;

	@Column(name = "password_hint_answer", length = 50)
	private String passwordHintAnswer;

	@Column(name = "session_timeout")
	private Integer sessionTimeout;

	@Column(name = "email_newsletter_1", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean emailNewsletter1;

	@Column(name = "email_newsletter_2", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean emailNewsletter2;

	@Column(name = "readonly", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean readonly;

	@Column(name = "enabled", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
