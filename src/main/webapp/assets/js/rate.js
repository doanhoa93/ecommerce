$(document).ready(function() {
    $(document).on('click', '.rate-form-btn', function() {
        reToggle();
    });

    $(document).on('click', '.rate-cancel', function() {
        reToggle();
    });

    $(document).on('mouseover', '.star-large', function() {
        var rating = $('.star-large').index(this) + 1;
        var maxRate = $('.star-large').length;

        for (var i = 0; i < rating; i++) {
            $('.star-large').eq(i).addClass('active');
        }

        for (var i = rating; i < maxRate; i++) {
            $('.star-large').eq(i).removeClass('active');
        }
    });

    $(document).on('mouseout', '.star-large', function() {
        var rating = $('input[name="rating"]').val();
        var maxRate = $('.star-large').length;

        for (var i = 0; i < rating; i++) {
            $('.star-large').eq(i).addClass('active');
        }

        for (var i = rating; i < maxRate; i++) {
            $('.star-large').eq(i).removeClass('active');
        }
    });

    $(document).on('click', '.star-large', function() {
        var rating = $('.star-large').index(this) + 1;
        $('input[name="rating"]').val(rating);
    });

    $(document).on('submit', '#form-rate', function(event) {
        event.preventDefault();
        var formData = new FormData(this);
        $.ajax({
            url : $(this).attr('action'),
            type : $(this).attr('method'),
            data : formData,
            dataType : 'json',
            success : function(data) {
                if (data.result == 'success') {
                    window.location.reload();
                }
            },
            cache : false,
            contentType : false,
            processData : false
        });
    })

    function reToggle() {
        $('.rate').toggle();
        $('.rate-form').toggle();
        $('.rate-form-btn').toggle();
    }
});
