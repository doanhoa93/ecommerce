$(document).ready(function() {
	initTotalPrice();
	
	displayCartLoadMore();

	$(document).on('click', '.cart-close', function(event) {
		event.preventDefault();
		var id = $(this).data('id');
		$.ajax({
		    method: 'DELETE',
		    url: getContextPath() + '/carts/' + id,
			dataType: 'json',
		    contentType: 'application/json',
		    success: function(data) {
		    	handleCloseSuccess(id);
		    }
		});
	});

	function handleCloseSuccess(id) {
		var cartProduct = $('.cart-product-' + id);
		var cartSize = Number($('.carts-size').text());
		
		if($('.cart-product-select-' + id).is(':checked')) {
	    	var quantityCart = Number(cartProduct.find('.cart-quantity').first().val());
	    	var moneyCart = convertToMoney(cartProduct.find('.cart-product-price').first().text());
	    	var totalMoney = convertToMoney($('.total-money').text());
	    	setTotalMoney(totalMoney - quantityCart * moneyCart);
		}
		
    	if(cartSize > 0)
    		$('.carts-size').text(cartSize - 1);
    	
    	$('.item-cart .cart-size').text(Number($('.item-cart .cart-size').text()) - 1);
    	cartProduct.remove();
	}

	$(document).on('click', '.quantity-minus', function() {
		var parent = $(this).parent();
		var id = parent.data('id');
		var cartQuantity = parent.find('.cart-quantity').first();
		if (cartQuantity.val() > 1) {
			var data = {quantity: Number(cartQuantity.val()) - 1};
			$.ajax({
				method: 'PATCH',
				url: getContextPath() + '/carts/' + id,
				data: JSON.stringify(data),
				dataType: 'json',
			    contentType: 'application/json',
				success: function(data) {
					cartQuantity.val(Number(cartQuantity.val()) - 1);
					if($('.cart-product-select-' + id).is(':checked')) {
						var money = convertToMoney($('.total-money-value').text());
						var moneyProduct = convertToMoney($('.cart-product-' + id + ' .cart-product-price').text());
						setTotalMoney(money - moneyProduct);
					}
				}
			});

		}
	});

	$(document).on('click', '.quantity-plus', function() {
		var parent = $(this).parent();
		var productNumber = Number(parent.parent().find('.product-number').text());
		var id = parent.data('id');
		var cartQuantity = parent.find('.cart-quantity').first();
		var quantity = Number(cartQuantity.val()) + 1;
		if(quantity <= productNumber) {
			var data = {quantity: quantity};
			$.ajax({
				method: 'PATCH',
				url: getContextPath() + '/carts/' + id,
				data: JSON.stringify(data),
				dataType: 'json',
			    contentType: 'application/json',
				success: function(data) {
					if(data.error == undefined) {
						cartQuantity.val(quantity);
						if($('.cart-product-select-' + id).is(':checked')) {					
							var money = convertToMoney($('.total-money-value').text());
							var moneyProduct = convertToMoney($('.cart-product-' + id + ' .cart-product-price').text());
							setTotalMoney(money + moneyProduct);
						}
					}
				}
			});
		}
	});
	
	$(document).on('submit', 'form#new-order', function(e) {
		var cartIds = [];
		$('.cart-product-select:checked').each(function(index, cart) {
			cartIds.push(cart.value);
		});
		
		e.preventDefault();
	    var formData = new FormData(this);
	    formData.append('cartIds', cartIds);
	    $.ajax({
	        url: $(this).attr('action'),
	        type: 'POST',
	        data: formData,
	        success: function (data) {
	        	data = JSON.parse(data);
	        	if(data.errors != undefined) {
	        		if(data.errors.length == 1 && data.errors[0].field == 'cartIds') {
	        			$('#form-order').modal('hide');
	        			$('.alert-warning').show();
	        			$('.alert-warning').html(data.errors[0].defaultMessage);
	        			return;
	        		}
	        		
	        		$('.error').remove();
	        		var errorMsgs = [];
	        		$.each(data.errors, function(index, error) {
	        			$('input[name=' + error.field + ']').after('<div class="error">' + error.defaultMessage + '</div>');
	        		});	        	
	        	} else {
	        		window.location.replace(data.url);
	        	} 
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });			
		
	});
	
	$(document).on('click', '.cart-product-select', function() {
		initTotalPrice();
	});
	
	$(document).on('click', '.carts-load-more .load-more', function() {
	    var url = $(this).data('href') + $('.cart-page').val();
		$.ajax({
		    method: 'GET',
		    headers: {
		    	'Accept' : 'text/html',
		    	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
		    },
		    url: url
		}).done(function(data) {
			$('.cart-page').val(Number($('.cart-page').val()) + 1);
			$('.carts-index').append(data);
			
			if($('.cart-product').length == Number($('.carts-size').text()))
				$('.carts-load-more').remove();
        })			
	});
	
	function setTotalMoney(price) {
		price = price.toLocaleString('en-US', {currency: 'USD', style: 'currency'});
		$('.total-money-value').html(price);
	}

	function convertToMoney(money) {
		money = money.replace(/[^0-9-.]/g, '');
		return Number(money);
	}

	function initTotalPrice() {
		var money = 0;
		var quantity = 0;
		$('.cart-product-select:checked').each(function() {
			var productPrice = $('.cart-product-price-' + $(this).val());
			quantity = productPrice.next().find('.cart-quantity').val();
			money += convertToMoney(productPrice.text()) * quantity;
        })
        
		setTotalMoney(money);
	}

	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf('/',2));
	}
	
	function displayCartLoadMore() {
		if($('.cart-product').length <= Number($('.cart-size').text()))
			$('.carts-load-more').remove();		
	}
});
