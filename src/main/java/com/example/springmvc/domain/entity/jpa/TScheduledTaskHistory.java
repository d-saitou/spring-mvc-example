package com.example.springmvc.domain.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.springmvc.domain.entity.jpa.base.AbstractAuditingBaseDateOnlyEntity;
import com.example.springmvc.utility.StringUtility;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JPA entity (table: t_scheduledtask_history).
 */
@Entity
@Table(name = "t_scheduledtask_history")
@Data
@EqualsAndHashCode(callSuper = false)
public class TScheduledTaskHistory extends AbstractAuditingBaseDateOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "method", length = 30)
	private String method;

	@Column(name = "message", length = 100)
	private String message;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
