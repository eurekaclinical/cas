// get the namespace, or declare it here
window.eureka = (typeof window.eureka == "undefined" || !window.eureka ) ? {} : window.eureka;

// add in the namespace
window.eureka.password = new function () {
	var self = this;

	self.pwValidator = null;

	self.setup = function (formElem) {
		self.pwValidator = self.setupPasswordValidator(formElem);
		$.validator.addMethod("passwordCheck",
			function (value, element) {
				return value.length >= 8 && /[0-9]+/.test(value) && /[a-zA-Z]+/.test(value);
			},
			"Please enter at least 8 characters with at least 1 digit."
		);
	};

	self.setupPasswordValidator = function (formElem) {
		return $(formElem).validate({
			rules: {
				oldPassword: {
					required: true
				},
				newPassword: {
					required: true,
					minlength: 8,
					passwordCheck: true
				},
				verifiedNewPassword: {
					required: true,
					minlength: 8,
					equalTo: "#newPassword"
				}
			},
			messages: {
				oldPassword: {
                                        required: "Please enter your old password."
                                },
				newPassword: {
					required: "Provide a new password.",
					rangelength: $.validator.format("Please enter at least {0} characters.")
				},
				verifiedNewPassword: {
					required: "Repeat your new password.",
					minlength: $.validator.format("Please enter at least {0} characters."),
					equalTo: "Enter the same new password as above."
				}
			},
			errorPlacement: function (error, element) {
				error.appendTo($(element).closest('.form-group').find('.help-inline'));
                                $('.help-inline').css("color", "#b94a48");
			},
			highlight: function (element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight: function (element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			// set this class to error-labels to indicate valid fields
			success: function (label) {
				// set &nbsp; as text for IE
				label.html("&nbsp;").addClass("checked");
			}
		});
	};

};
