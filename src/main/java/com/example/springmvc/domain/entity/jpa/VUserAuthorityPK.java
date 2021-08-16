package com.example.springmvc.domain.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;

/**
 * JPA entity PK (table: m_user_role).
 */
@Embeddable
@Data
public class VUserAuthorityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "authority_name")
	private String authorityName;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
