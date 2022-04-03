package com.example.spring.domain.entity.jpa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.spring.domain.entity.jpa.base.AbstractAuditingBaseEntity;
import com.example.spring.utility.StringUtility;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JPA entity (table: t_task).
 */
@Entity
@Table(name = "t_task")
@Data
@EqualsAndHashCode(callSuper = false)
public class TTask extends AbstractAuditingBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id", nullable = false)
	private Integer taskId;

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "scheduled_date")
	private LocalDate scheduledDate;

	@Column(name = "completion")
	private Boolean completion;

	@Column(name = "description", columnDefinition = "TEXT", length = 65535)
	private String description;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
