package com.example.spring.domain.entity.jpa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.spring.domain.entity.jpa.base.AbstractAuditingBaseEntity;
import com.example.spring.utility.StringUtility;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JPA entity (table: m_user).
 */
@Entity
@Table(name = "m_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class MUser extends AbstractAuditingBaseEntity implements Serializable {

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

	@Column(name = "email_newsletter_1")
	private Boolean emailNewsletter1;

	@Column(name = "email_newsletter_2")
	private Boolean emailNewsletter2;

	@Column(name = "readonly")
	private Boolean readonly;

	@Column(name = "enabled")
	private Boolean enabled;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
