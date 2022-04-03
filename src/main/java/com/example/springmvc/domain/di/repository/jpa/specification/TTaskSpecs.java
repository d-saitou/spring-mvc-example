package com.example.springmvc.domain.di.repository.jpa.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.example.springmvc.domain.entity.jpa.TTask;

/**
 * Spring Data JPA specification (table: t_task).
 */
public class TTaskSpecs {

	/**
	 * Private constructor.
	 */
	private TTaskSpecs() {
	}

	/**
	 * Search condition: title.
	 * @param title title search string.
	 * @return Specification object.
	 */
	public static Specification<TTask> titleContains(String title) {
		return !StringUtils.hasLength(title) ? null
				: (root, query, builder) -> builder.like(root.get("title"), title + "%");
	}

	/**
	 * Search condition: scheduledDate.
	 * @param minDate start date of search period.
	 * @return Specification object.
	 */
	public static Specification<TTask> scheduledDateGreaterThanOrEquals(LocalDate minDate) {
		return minDate == null ? null
				: (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("scheduledDate"), minDate);
	}

	/**
	 * Search condition: scheduledDate.
	 * @param maxDate end date of search period.
	 * @return Specification object.
	 */
	public static Specification<TTask> scheduledDateLessThanOrEquals(LocalDate maxDate) {
		return maxDate == null ? null
				: (root, query, builder) -> builder.lessThanOrEqualTo(root.get("scheduledDate"), maxDate);
	}

	/**
	 * Search condition: completion status.
	 * @param completion true:completed, false:incomplete, null:unspecified
	 * @return Specification object.
	 */
	public static Specification<TTask> completionEquals(Boolean completion) {
		if (completion == null) {
			return null;
		}
		return (root, query, builder) -> builder.equal(root.get("completion"), completion);
	}

	/**
	 * Search condition: description.
	 * @param description description search string.
	 * @return Specification object.
	 */
	public static Specification<TTask> descriptionContains(String description) {
		return !StringUtils.hasLength(description) ? null
				: (root, query, builder) -> builder.like(root.get("description"), "%" + description + "%");
	}

	/**
	 * Search condition: createdBy.
	 * @param createdBy user id.
	 * @return Specification object.
	 */
	public static Specification<TTask> createdByEquals(String createdBy) {
		return !StringUtils.hasLength(createdBy) ? null
				: (root, query, builder) -> builder.equal(root.get("createdBy"), createdBy);
	}

}
