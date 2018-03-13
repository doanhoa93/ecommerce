$(document).ready(function() { 
	setTimeout(function(){
	    $('.flash').slideUp(1000);
	  }, 1500);
	
	$(document).on('click', '.notification-dropdown', function() {
		$('.notifications-body').toggle();
	});

	$(document).on('submit', 'form', function(e) {
		if($(this).data('confirm') != undefined) {
			var c = confirm($(this).data('confirm'));
			if(!c)
		        e.preventDefault(e);
		}
	});
	
	$(document).on('click', 'input', function(e) {
		if($(this).data('confirm') != undefined) {
			var c = confirm($(this).data('confirm'));
			if(!c)
		        e.preventDefault(e);
		}
	});	
});
