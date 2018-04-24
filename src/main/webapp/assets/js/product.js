$(document).ready(
    function() {
        var filter = {};

        $(document).on('click', '.btn-filter', function() {
            filter.name = $('.name-range-input').val();
            filter.priceHigh = $('.price-range-input').val();
            filter.priceLow = $('.price-range-input').attr('min');
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

        $(document).on('change', '.product-order-field', function() {
            var url = $(this).data('href');
            filter.name = $('.name-range-input').val();
            filter.priceHigh = $('.price-range-input').val();
            filter.priceLow = $('.price-range-input').attr('min');
            filter.orderAttr = this.name;
            filter.orderType = this.value;
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

        scrollNameCategory();

        function scrollNameCategory() {
            if ($('.category .active').length) {
                var position =
                    $('.category .active').parent().position().top
                        - $('.categories').position().top;
                $('.categories').scrollTop(position);
            }
        }
    });
