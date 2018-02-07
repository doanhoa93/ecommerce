$(document).ready(function() {
	$(document).on('change', '.suggest-form .avatar', function() {
		readURL(this, $('.suggest-avatar-panel'));
	});
	
	function readURL(input, image) {
	    if (input.files && input.files[0]) {
	    	var reader = new FileReader();
	    	reader.onload = function (e) {
	    		image.attr('src', e.target.result);
	    	}
	    	reader.readAsDataURL(input.files[0]);
	    }
	}
});
