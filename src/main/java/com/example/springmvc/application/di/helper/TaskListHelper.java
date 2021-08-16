package com.example.springmvc.application.di.helper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	 * Convert entity to form.
	 * @param entity entity.
	 * @param locale Locale object.
	 * @return form.
	 */
	public TaskForm convertEntityToForm(TTask entity, Locale locale) {
		String format = msg.getMessage("common.format.date", null, locale);
		TaskForm form = new TaskForm();
		form.setId(entity.getId());
		form.setTitle(entity.getTitle());
		form.setScheduleDate(entity.getScheduleDate().format(DateTimeFormatter.ofPattern(format)));
		form.setStatus(entity.isStatus());
		form.setDescription(entity.getDescription());
		form.setUserId(entity.getUserId());
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
		String format = msg.getMessage("common.format.date", null, locale);
		TTask entity = new TTask();
		entity.setId(form.getId());
		entity.setTitle(form.getTitle());
		entity.setScheduleDate(DateUtility.parseLocalDate(form.getScheduleDate(), format));
		entity.setStatus(form.isStatus());
		entity.setDescription(form.getDescription());
		entity.setUserId(form.getUserId());
		return entity;
	}

}