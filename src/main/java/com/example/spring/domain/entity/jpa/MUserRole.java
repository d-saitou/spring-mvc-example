package com.example.spring.domain.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.spring.domain.entity.jpa.base.AbstractAuditingBaseEntity;
import com.example.spring.utility.StringUtility;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JPA entity (table: m_user_role).
 */
@Entity
@Table(name = "m_user_role")
@IdClass(MUserRolePK.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class MUserRole extends AbstractAuditingBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", length = 10)
	private String userId;

	@Id
	@Column(name = "role_id", length = 10)
	private String roleId;

	@Column(name = "description", length = 50)
	private String description;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
