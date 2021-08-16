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
