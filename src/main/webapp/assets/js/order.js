$(document).ready(function() {
	initTotalPrice();

	$('.cart-close').on('click', function(event) {
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
    	var quantityCart = Number(cartProduct.find('.cart-quantity').first().val());
    	var moneyCart = convertToMoney(cartProduct.find('.cart-product-price').first().text());
    	var totalMoney = convertToMoney($('.total-money').text());
    	setTotalMoney(totalMoney - quantityCart * moneyCart);
    	if(cartSize > 0)
    		$('.carts-size').text(cartSize - 1);

    	cartProduct.remove();
	}


	$('.quantity-minus').on('click', function() {
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
					var money = convertToMoney($('.total-money-value').text());
					var moneyProduct = convertToMoney($('.cart-product-' + id + ' .cart-product-price').text());
					setTotalMoney(money - moneyProduct);
				}
			});

		}
	});

	$('.quantity-plus').on('click', function() {
		var parent = $(this).parent();
		var id = parent.data('id');
		var cartQuantity = parent.find('.cart-quantity').first();
		var data = {quantity: Number(cartQuantity.val()) + 1};
		$.ajax({
			method: 'PATCH',
			url: getContextPath() + '/carts/' + id,
			data: JSON.stringify(data),
			dataType: 'json',
		    contentType: 'application/json',
			success: function(data) {
				cartQuantity.val(Number(cartQuantity.val()) + 1);
				var money = convertToMoney($('.total-money-value').text());
				var moneyProduct = convertToMoney($('.cart-product-' + id + ' .cart-product-price').text());
				setTotalMoney(money + moneyProduct);
			}
		});
	});

	$('.btn-order').on('click', function() {
		var cartIds = [];
		$('.cart-product-select:checked').each(function(index, cart) {
			cartIds.push(cart.value);
		});
		$.ajax({
			method: 'POST',
			url: getContextPath() + '/orders',
			data: JSON.stringify({cartIds: cartIds}),
			dataType: 'json',
		    contentType: 'application/json',
		    success: function(data) {
		    	if (data.msg == 'error') {
		    		$('.alert-warning').show();
		    		var keys = Object.keys(data.error);
		    		var content = '';
		    		for(i = 0; i < keys.length; i++) {
		    			content += data.error[keys[i]] + '<br>';
		    		}
		    		$('.alert-warning').html(content);
		    	} else {
		    		window.location.replace(getContextPath() + data.url);
		    	}
		    }
		});
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
		$('.cart-product-price').each(function() {
			quantity = $(this).next().find('.cart-quantity').val();
			money += convertToMoney($(this).text()) * quantity;
		});
		setTotalMoney(money);
	}

	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
});
