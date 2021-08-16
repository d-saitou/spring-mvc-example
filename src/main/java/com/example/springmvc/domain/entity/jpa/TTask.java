package com.example.springmvc.domain.entity.jpa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.springmvc.utility.StringUtility;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * JPA entity (table: t_task).
 */
@Entity
@Table(name = "t_task")
@Accessors(chain = true)
@Data
public class TTask implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "schedule_date")
	private LocalDate scheduleDate;

	@Column(name = "status", nullable = false, columnDefinition = "BIT", length = 1)
	private boolean status;

	@Column(name = "description", columnDefinition = "TEXT", length = 65535)
	private String description;

	@Column(name = "user_id", length = 11)
	private String userId;

	@Override
	public String toString() {
		return StringUtility.toJsonStyleString(this);
	}

}
