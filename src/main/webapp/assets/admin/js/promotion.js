$(document).ready(function() {
    $(document).on('click', '.new-promotion', function() {
        $.ajax({
            url : $(this).data('href'),
            method : 'GET',
            headers : {
                'Accept' : 'text/html',
                'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).done(function(data) {
            $('#form-promotion').modal('show');
            $('#form-promotion').html(data);
        });
    });

    $(document).on('click', '.edit-promotion', function() {
        $.ajax({
            url : $(this).data('href'),
            method : 'GET',
            headers : {
                'Accept' : 'text/html',
                'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).done(function(data) {
            $('#form-promotion').modal('show');
            $('#form-promotion').html(data);
        });
    });

    $(document).on('submit', 'form#create-promotion', function(e) {
        e.preventDefault();
        var formData = new FormData(this);
        $.ajax({
            url : $(this).attr('action'),
            type : 'POST',
            data : formData,
            success : function(data) {
                if (isError(data)) {
                    $('.error').remove();
                    $(data).filter('.error').each(function(index, error) {
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

    $(document).on('submit', 'form#update-promotion', function(e) {
        e.preventDefault();
        var formData = new FormData(this);
        $.ajax({
            url : $(this).attr('action'),
            type : 'POST',
            data : formData,
            success : function(data) {
                if (isError(data)) {
                    $('.error').remove();
                    $(data).filter('.error').each(function(index, error) {
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

    $(document).delegate('.datepicker', 'focusin', function() {
        $(this).datepicker();
    });

    function isError(data) {
        return $(data).filter('.error').length;
    }
});
