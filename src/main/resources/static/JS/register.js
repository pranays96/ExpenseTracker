$(function(){
    const $registerForm = $('#registerForm');

    if($registerForm.length){

        $registerForm.validate({

            rules: {
                name: {
                    required: true,
                    minlength: 3
                },
                email: {
                    required: true,
                },
                password: {
                    required: true,
                    minlength: 5,
                    maxlength: 15
                },
                confirmpassword:{
                    required : true,
                    equalTo: '#password'
                }
            },

            messages: {
                name: {
                   required: 'Please enter name for the user',
                   minlength: 'Name should be at least 3 characters'
                },
                email: {
                   required: 'Please enter the email'

                },
                password: {
                   required: 'Please enter password',
                   minlength: 'Password should be at least 3 characters',
                   maxlength: 'Password should be less than 15 characters'
                },
                confirmpassword:{
                   required : 'Please enter confirm password',
                   equalTo: 'Password and confirm password should be same'
                }
            },
            errorElement: 'em',
                errorPlacement: function(error, element) {
                error.addClass('help-block');
                error.insertAfter(element);
            }

        })
    }


})