$(document).ready(function() {
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

	$(document).on('click', '.btn-update-order', function(e) {
		e.preventDefault();
		var formData = new FormData(document.getElementById('edit-order'));
		var id = $(this).data('id');
		$('.order-product').each(function(index, element) {
		    var orderProductId = $(this).data('id'); 
			var quantity = $(this).find('.order-product-quantity').val();
			formData.append('orderProducts[' + index + '].id', orderProductId);
			formData.append('orderProducts[' + index + '].quantity', quantity);
			formData.append('orderProducts[' + index + '].productId', $(this).data('product-id'));
		});
        $.ajax({
            url: $('form#edit-order').attr('action'),
            type: 'POST',
            data: formData,
            success: function (data) {
                if(isError(data)) {
                    $('.error').remove();                    
                    var errorMsgs = [];                 
                    $(data).filter('.error').each(function(index, error)  {
                        if($(error).filter('.order').length || $(error).filter('.orderProducts').length) {
                            $('.alert-warning').show();
                            $('.alert-warning').append(error);
                        }
                        $('input[name=' + $(error).attr('data-name') + ']').after(error);
                    });
                } else {
                    var url = $(data).filter('.redirect').attr('href');
                    window.location.replace(url);
                }                   
            },
            cache: false,
            contentType: false,
            processData: false
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
		$('.order-product-price').each(function() {
			quantity = $(this).next().find('.order-product-quantity').val();
			money += convertToMoney($(this).text()) * quantity;
        })
        
		setTotalMoney(money);
	}
	
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
    
    function isError(data) {
        return $(data).filter('.error').length;
    }       
});
