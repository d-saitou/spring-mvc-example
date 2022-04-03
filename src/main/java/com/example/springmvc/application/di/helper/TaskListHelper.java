package com.example.springmvc.application.di.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.example.springmvc.application.form.TaskForm;
import com.example.springmvc.domain.entity.jpa.TTask;
import com.example.springmvc.utility.DateUtility;

import lombok.RequiredArgsConstructor;

/**
 * Helper for screens that operate tasks.
 */
@Component
@RequiredArgsConstructor
public class TaskListHelper {

	private final MessageSource msg;

	/**
	 * Convert scheduled date from String to LocalDate.
	 * @param scheduledDate scheduled date of String.
	 * @param locale        Locale object.
	 * @return scheduled date of LocalDate.
	 */
	public LocalDate convertScheduledDate(String scheduledDate, Locale locale) {
		String format = msg.getMessage("common.format.date", null, locale);
		return DateUtility.parseLocalDate(scheduledDate, format);
	}

	/**
	 * Convert entity to form.
	 * @param entity entity.
	 * @param locale Locale object.
	 * @return form.
	 */
	public TaskForm convertEntityToForm(TTask entity, Locale locale) {
		String format = msg.getMessage("common.format.date", null, locale);
		TaskForm form = new TaskForm();
		form.setTaskId(entity.getTaskId());
		form.setTitle(entity.getTitle());
		form.setScheduledDate(entity.getScheduledDate().format(DateTimeFormatter.ofPattern(format)));
		form.setCompletion(entity.getCompletion());
		form.setDescription(entity.getDescription());
		form.setUserId(entity.getCreatedBy());
		return form;
	}

	/**
	 * Convert entity list to form list.
	 * @param entities entity list.
	 * @param locale   Locale object.
	 * @return form list.
	 */
	public List<TaskForm> convertEntityToForm(List<TTask> entities, Locale locale) {
		List<TaskForm> forms = new ArrayList<TaskForm>();
		for (TTask entity : entities) {
			forms.add(this.convertEntityToForm(entity, locale));
		}
		return forms;
	}

	/**
	 * Convert form to entity.
	 * @param form   TaskForm.
	 * @param locale Locale object.
	 * @return entity.
	 */
	public TTask convertFormToEntity(TaskForm form, Locale locale) {
		TTask entity = new TTask();
		entity.setTaskId(form.getTaskId());
		entity.setTitle(form.getTitle());
		entity.setScheduledDate(convertScheduledDate(form.getScheduledDate(), locale));
		entity.setCompletion(form.isCompletion());
		entity.setDescription(form.getDescription());
		return entity;
	}

	/**
	 * Generate a list to select the task completion status.
	 * @param locale Locale object.
	 * @return Map for selecting task completion status.
	 */
	public Map<String, String> getCompletionComboBoxMap(Locale locale) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(msg.getMessage("common.label.item.all", null, locale), "");
		map.put(msg.getMessage("common.label.item.incomplete", null, locale), "false");
		map.put(msg.getMessage("common.label.item.completed", null, locale), "true");
		return map;
	}

}
