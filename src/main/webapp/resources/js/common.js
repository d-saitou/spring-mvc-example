/**
 * Set event after loading DOM
 */
$(document).ready(function(){
	
	// Setting to invalidate submit by enter key
	$(document).on('keypress', 'input:not(.allow_submit)', function(event) {
		return event.which !== 13;
	});
	
});

/**
 * escape HTML
 * @param str string before conversion
 * @return string after conversion
 */
function escapeHtml(str){
	var wk = (str === null || str === undefined) ? '' : '' + str;
	wk = wk.replace(/&/g, '&amp;');
	wk = wk.replace(/'/g, '&#x27;');
	wk = wk.replace(/`/g, '&#x60');
	wk = wk.replace(/"/g, '&quot');
	wk = wk.replace(/</g, '&lt');
	wk = wk.replace(/>/g, '&gt');
	return wk;
}

/**
 * Invalidate key input on input form by id attribute
 * @param tagId id attribute
 * @return result of key input event determination
 */
function setFormKeydownDisable(tagId) {
	$('#' + tagId).keydown(function(event){
		// Invalidate all key input except tab key
		return event.which == 9;
	});
}

/**
 * Invalidate key input on input form by name attribute
 * @param tagName name attribute
 * @return result of key input event determination
 */
function setFormKeydownDisableByName(tagName) {
	$("input[name='" + tagName + "']").keydown(function(event){
		// Invalidate all key input except tab key
		return event.which == 9;
	});
}

/**
 * Setting datepicker (jQuery UI) by id attribute
 * @param tagId  id attribute
 * @param format date format
 */
function setDatepicker(tagId, format) {
	// Convert from java date format to datepicker format
	format = format.replace(/yyyy/g,'yy').replace(/MM/g,'mm');
	// Setting datepicker by id attribute
	$('#' + tagId).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: format
	});
}

/**
 * Setting datepicker (jQuery UI) by name attribute
 * @param tagName name attribute
 * @param format  date format
 */
function setDatepickerByName(tagName, format) {
	// Convert from java date format to datepicker format
	format = format.replace(/yyyy/g,'yy').replace(/MM/g,'mm');
	// Setting datepicker by name attribute
	$("input[name='" + tagName + "']").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: format
	});
}
