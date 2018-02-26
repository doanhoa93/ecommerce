$(document).ready(function() {
	$(document).on('click', '.order-tr', function() {
		var href = $(this).data('href');
		window.location.replace(getContextPath() + href);
	});
	
	initTotalPrice();

	$(document).on('click', '.order-product-close', function(event) {
		event.preventDefault();
		var id = $(this).data('id');
		$('.order-product-' + id).remove();
		$('.order-size').text(Number($('.order-size').text()) - 1);
		initTotalPrice();
	});

	$(document).on('click', '.quantity-minus', function() {
		var parent = $(this).parent();
		var id = parent.data('id');
		var quantityPanel = parent.find('.order-product-quantity').first();
		var quantity = Number(quantityPanel.val()) - 1;
		if (quantity > 0) {
			var quantityForm = $(this).parent();
			quantityForm.find('.order-product-quantity').val(quantity);
			initTotalPrice();
		}
	});

	$(document).on('click', '.quantity-plus', function() {
		var parent = $(this).parent();
		var productNumber = Number(parent.parent().find('.product-number').text());
		var id = parent.data('id');
		var quantityPanel = parent.find('.order-product-quantity').first();
		var quantity = Number(quantityPanel.val()) + 1;
		if(quantity <= productNumber) {
			var quantityForm = $(this).parent();
			quantityForm.find('.order-product-quantity').val(quantity);	
			initTotalPrice();
		}
	});

	$(document).on('click', '.btn-order', function() {
		var orderProducts = [];
		$('.order-product').each(function() {
			var id = $(this).data('id');
			var quantity = $(this).find('.order-product-quantity').val();
			orderProducts.push({id: id, quantity: quantity});
		});
		
		if(orderProducts.length > 0) {
			var id = $(this).data('id');
			$.ajax({
				method: 'PATCH',
				url: getContextPath() + '/orders/' + id,
				data: JSON.stringify({orderProducts: orderProducts}),
				dataType: 'json',
			    contentType: 'application/json',
			    success: function(data) {
			    	if(data.msg == 'success')
			    		window.location.replace(getContextPath() + '/orders/' + id);
			    }
			});
		} else {
			$('.alert-warning').show();
			$('.alert-warning').html('Cannot order with empty!');
		}
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
		$('.order-product-price').each(function() {
			quantity = $(this).next().find('.order-product-quantity').val();
			money += convertToMoney($(this).text()) * quantity;
        })
        
		setTotalMoney(money);
	}
	
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
});
