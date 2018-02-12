$(document).ready(function() {
	$(document).on('click', '.btn-filter', function() {
		var name = $('.name-range-input').val();
		var priceHigh = $('.price-range-input').val();
		var priceLow = $('.price-range-input').attr('min');
		data = {name: name, priceLow: priceLow, priceHigh: priceHigh};
		$.ajax({
		    method: 'GET',
		    headers: {
		    	'Accept' : 'text/html',
		    	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
		    },
		    url: window.location.pathname,
		    data: data
		}).done(function(data) {
	        $('.products-right').html(data);
        })
	});
	
	$(document).on('input', '.price-range-input', function() {
		$('.price-value').html('Value: ' + Number($(this).val()).toLocaleString('en-US', {currency: 'USD', style: 'currency', minimumFractionDigits: 0}));	    
    });
	
	$(document).on('click', '.products-paginate .paginate-a', function() {
	    var url = $(this).data('href');
		var name = $('.name-range-input').val();
		var priceHigh = $('.price-range-input').val();
		var priceLow = $('.price-range-input').attr('min');
		data = {name: name, priceLow: priceLow, priceHigh: priceHigh};
		$.ajax({
		    method: 'GET',
		    headers: {
		    	'Accept' : 'text/html',
		    	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
		    },
		    url: url,
		    data: data
		}).done(function(data) {
	        $('.products-right').html(data);
        })	    
    });    
});
