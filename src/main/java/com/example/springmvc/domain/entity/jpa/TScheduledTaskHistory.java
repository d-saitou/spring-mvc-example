package com.example.springmvc.domain.entity.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;

/**
 * JPA entity (table: t_scheduledtask_history).
 */
@Entity
@Table(name = "t_scheduledtask_history")
@Data
public class TScheduledTaskHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "method", length = 30)
	private String method;

	@Column(name = "message", length = 100)
	private String message;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
