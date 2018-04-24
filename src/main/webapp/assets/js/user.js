$(document).ready(
    function() {
        $(document).on(
            'change',
            '#upload',
            function() {
                readURL(this, $('.avatar'));
                $('.field').find('#upload').text(
                    'Type: ' + this.files[0].type + ', Size: '
                        + (this.files[0].size / 1048576).toFixed(2) + ' MB')
            });

        function readURL(input, image) {
            if (input.files && input.files[0]) {
                setURL(input.files[0], image);
            }
        }

        function setURL(file, image) {
            var reader = new FileReader();
            reader.onload = function(e) {
                image.attr('src', e.target.result);
            }
            reader.readAsDataURL(file);
        }

        $(document).on('submit', 'form#edit-user', function(e) {
            e.preventDefault();
            var formData = new FormData(this);
            formData.append('avatar', $('#upload').attr('src'));
            if (formData.get('profile.birthday') == '' || formData.get('profile.birthday') == null)
                formData.set('profile.birthday', new Date());
            $.ajax({
                url : $(this).attr('action'),
                type : 'POST',
                data : formData,
                success : function(data) {
                    if (isError(data)) {
                        $(data).filter('.error').each(function(index, error) {
                            if ($(error).filter('.gender').length)
                                $('.gender').after(error);
                            $('input[name="' + $(error).attr('data-name') + '"]').after(error);
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

        $('.datepicker').datepicker({});

        $(document).on('mouseover', '.upload-avatar', function() {
            $('.edit-avatar').toggle();
        });

        $(document).on('mouseout', '.upload-avatar', function() {
            $('.edit-avatar').toggle();
        });

        function isError(data) {
            $('.error').remove();
            return $(data).filter('.error').length;
        }
    });
