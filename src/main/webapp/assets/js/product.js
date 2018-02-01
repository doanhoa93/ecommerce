$(document).ready(function() {
	$('.btn-filter').on('click', function() {
		var name = $('.name-range-input').val();
		var priceHigh = $('.price-range-input').val();
		var priceLow = $('.price-range-input').attr('min');
		data = {name: name, priceLow: priceLow, priceHigh: priceHigh};
		$.ajax({
		    method: 'GET',
		    headers: {
		    	"Accept" : "text/html",
		    	"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
		    },
		    url: window.location.pathname,
		    data: data
		}).done(function(data) {
	        $('.products-right').html(data);
        })
	});
	
	$('.price-range-input').on('change', function() {
		$('.price-value').html('Value: ' + Number($(this).val()).toLocaleString('en-US', {currency: 'USD', style: 'currency', minimumFractionDigits: 0}));	    
    })
});
