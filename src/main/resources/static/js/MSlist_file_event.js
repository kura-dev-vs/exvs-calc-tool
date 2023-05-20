/**
 * 
 */

window.onload = function() {
	document.getElementById("btnUpload").disabled = true;
}
function selectFile() {
	if (document.getElementById("uploadFile").value === "") {
		document.getElementById("btnUpload").disabled = true;
	}
	else {
		document.getElementById("btnUpload").disabled = false;
	}
}
