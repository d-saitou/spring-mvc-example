package com.example.spring.domain.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.spring.utility.StringUtility;

import lombok.Data;

/**
 * JPA entity (view: v_user_authority).
 */
@Entity
@Table(name = "v_user_authority")
@IdClass(VUserAuthorityPK.class)
@Data
public class VUserAuthority implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", length = 10)
	private String userId;

	@Id
	@Column(name = "authority_name", length = 40)
	private String authorityName;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
