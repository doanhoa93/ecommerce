$(document).ready(
    function() {
        initTotalPrice();

        displayCartLoadMore();

        $(document).on('click', '.cart-close', function(event) {
            event.preventDefault();
            var id = $(this).data('id');
            $.ajax({
                method : 'DELETE',
                url : getContextPath() + '/carts/' + id,
                dataType : 'json',
                contentType : 'application/json',
                success : function(data) {
                    handleCloseSuccess(id);
                }
            });
        });

        function handleCloseSuccess(id) {
            var cartProduct = $('.cart-product-' + id);
            var cartSize = Number($('.carts-size').text());

            if ($('.cart-product-select-' + id).is(':checked')) {
                var quantityCart = Number(cartProduct.find('.cart-quantity').first().val());
                var moneyCart =
                    convertToMoney(cartProduct.find('.cart-product-price').first().text());
                var totalMoney = convertToMoney($('.total-money').text());
                setTotalMoney(totalMoney - quantityCart * moneyCart);
            }

            if (cartSize > 0)
                $('.carts-size').text(cartSize - 1);

            $('.item-cart .cart-size').text(Number($('.item-cart .cart-size').text()) - 1);
            cartProduct.remove();
        }

        $(document).on(
            'click',
            '.quantity-minus',
            function() {
                var parent = $(this).parent();
                var id = parent.data('id');
                var cartQuantity = parent.find('.cart-quantity').first();
                if (cartQuantity.val() > 1) {
                    var data = {
                        quantity : Number(cartQuantity.val()) - 1
                    };
                    changeQuantityCart(id, data, cartQuantity, Number(cartQuantity.val()) - 1,
                        'minus');

                }
            });

        $(document).on('click', '.quantity-plus', function() {
            var parent = $(this).parent();
            var productNumber = Number(parent.parent().find('.product-number').text());
            var id = parent.data('id');
            var cartQuantity = parent.find('.cart-quantity').first();
            var quantity = Number(cartQuantity.val()) + 1;
            if (quantity <= productNumber) {
                var data = {
                    quantity : quantity
                };
                changeQuantityCart(id, data, cartQuantity, quantity, 'plus');
            }
        });

        $(document).on('click', '.order-button', function() {
            var cartSize = $('.cart-product-select:checked').length;
            if (!cartSize) {
                $('.alert-warning').show();
                $('.alert-warning').html('Products cannot empty!');
            } else {
                $('.cart-products').hide();
                $('.order-form-create').show();
                $('.carts-size').html(cartSize);
                $('.order-button').hide();
                $('.alert-warning').hide();
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
                url : $(this).attr('action'),
                type : 'POST',
                data : formData,
                success : function(data) {
                    if (isError(data)) {
                        $('#preview-order').modal('hide');
                        $('.error').remove();
                        var errorMsgs = [];
                        $(data).filter('.error').each(function(index, error) {
                            if ($(error).filter('.cartIds').length) {
                                $('.alert-warning').show();
                                $('.alert-warning').append(error);
                                $('.order-form-create').hide();
                                $('.cart-products').show();
                                $('.order-button').show();
                            }
                            $('input[name=' + $(error).attr('data-name') + ']').after(error);
                        });
                    } else {
                        var url = $(data).filter('.redirect').attr('href');
                        window.location.replace(url);
                    }
                },
                cache : false,
                contentType : false,
                processData : false
            });
        });

        $(document).on('click', '.btn-order-submit', function(e) {
            if (!e.isDefaultPrevented())
                $('form#new-order').submit();
        });

        $(document).on('click', '.cart-product-select', function() {
            initTotalPrice();
        });

        $(document).on('click', '.carts-load-more .load-more', function() {
            var url = $(this).data('href') + $('.cart-page').val();
            $.ajax({
                method : 'GET',
                headers : {
                    'Accept' : 'text/html',
                    'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                url : url
            }).done(function(data) {
                $('.cart-page').val(Number($('.cart-page').val()) + 1);
                $('.carts-index').append(data);

                if ($('.cart-product').length == Number($('.carts-size').text()))
                    $('.carts-load-more').remove();
            })
        });

        $('#preview-order').on('shown.bs.modal', function() {
            $('.preorder-phone-number').text($('input[name=phoneNumber]').val());
            $('.preorder-name').text($('input[name=name]').val());
            $('.preorder-email').text($('input[name=email]').val());
            $('.preorder-address').text($('input[name=address]').val());
            $('.order-detail-price').text($('.total-money-value').text());
        });

        function changeQuantityCart(id, data, cartQuantity, quantity, type) {
            $.ajax({
                method : 'PATCH',
                url : getContextPath() + '/carts/' + id,
                data : JSON.stringify(data),
                dataType : 'json',
                contentType : 'application/json',
                success : function(data) {
                    if (data.error == undefined) {
                        cartQuantity.val(quantity);
                        if ($('.cart-product-select-' + id).is(':checked')) {
                            var money = convertToMoney($('.total-money-value').text());
                            var moneyProduct =
                                convertToMoney($('.cart-product-' + id + ' .cart-product-price')
                                        .text());
                            if (type === 'minus')
                                setTotalMoney(money - moneyProduct);
                            else
                                setTotalMoney(money + moneyProduct);
                        }
                    }
                }
            });
        }

        function setTotalMoney(price) {
            price = price.toLocaleString('en-US', {
                currency : 'USD',
                style : 'currency'
            });
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
            return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
        }

        function displayCartLoadMore() {
            if ($('.cart-product').length >= Number($('.cart-size').text()))
                $('.carts-load-more').remove();
        }

        function isError(data) {
            return $(data).filter('.error').length;
        }
    });
