$(document).ready(
    function() {
        var filter = {};
        var autoCompleteDatas = [];

        $(document).on('click', '.btn-filter', function() {
            filter.name = $('.name-range-input').val();
            filter.priceHigh = $('.price-range-input').val();
            filter.priceLow = $('.price-range-input').attr('min');
            filter.orderAttr = $('.product-order-active').attr('name');
            filter.orderType = $('.product-order-active').val();
            $.ajax({
                method : 'GET',
                headers : {
                    'Accept' : 'text/html',
                    'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                url : window.location.pathname,
                data : filter
            }).done(function(data) {
                $('.products-right').html(data);
            })
        });

        $(document).on('click', '.products-paginate .paginate-a', function() {
            var url = $(this).data('href');
            filter.name = $('.name-range-input').val();
            filter.priceHigh = $('.price-range-input').val();
            filter.priceLow = $('.price-range-input').attr('min');

            $.ajax({
                method : 'GET',
                headers : {
                    'Accept' : 'text/html',
                    'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                url : url,
                data : filter
            }).done(function(data) {
                $('.products-right').html(data);
            })
        });

        $(document).on(
            'change',
            '.product-order-field',
            function() {
                var url = $(this).data('href');
                filter.name = $('.name-range-input').val();
                filter.priceHigh = $('.price-range-input').val();
                filter.priceLow = $('.price-range-input').attr('min');
                filter.orderAttr = this.name;
                filter.orderType = this.value;
                if (filter.orderAttr == 'name') {
                    if (!$('.order-price .empty').length) {
                        $('.order-price option').first().before(
                            $('.order-name option').first().clone().prop('outerHTML'));
                    }
                    $('.order-name .empty').remove();
                } else {
                    if (!$('.order-name .empty').length) {
                        $('.order-name option').first().before(
                            $('.order-price option').first().clone().prop('outerHTML'));
                    }
                    $('.order-price .empty').remove();
                }
                $('.product-order-field').removeClass('product-order-active');
                $(this).addClass('product-order-active');
                $.ajax({
                    method : 'GET',
                    headers : {
                        'Accept' : 'text/html',
                        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    url : url,
                    data : filter
                }).done(function(data) {
                    $('.products-right').html(data);
                })
            });

        $(document).on('input', '.name-category', function() {
            var name = $(this).val().toUpperCase();
            $('.category').each(function() {
                if ($(this).find('.category-name').text().toUpperCase().includes(name))
                    $(this).show();
                else
                    $(this).hide();
            });
        });

        $(document).on('input', '.name-range-input', function() {
            if (!autoCompleteDatas.length)
                loadAutoCompleteData();
        });

        scrollNameCategory();

        function scrollNameCategory() {
            if ($('.category .active').length) {
                var position =
                    $('.category .active').parent().position().top
                        - $('.categories').position().top;
                $('.categories').scrollTop(position);
            }
        }

        function loadAutoCompleteData() {
            $.ajax({
                url : getContextPath() + "/redises",
                method : 'GET',
                dataType : 'json',
                success : function(data) {
                    autoCompleteDatas = Object.values(data);

                    $('.name-range-input').autocomplete({
                        source : autoCompleteDatas
                    });
                }
            });
        }

        function getContextPath() {
            return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
        }
    });
