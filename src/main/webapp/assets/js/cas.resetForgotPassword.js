// get the namespace, or declare it here
window.eureka = (typeof window.eureka == "undefined" || !window.eureka ) ? {} : window.eureka;

// add in the namespace
window.eureka.password = new function () {
	var self = this;

	self.emailValidator = null;

	self.setup = function (formElem) {
		self.emailValidator = self.setupEmailValidator(formElem);
		$.validator.addMethod("emailCheck",
			function (value, element) {
				return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
			},
			"Please enter a valid email address."
		);
	};

	self.setupEmailValidator = function (formElem) {
		return $(formElem).validate({
			rules: {
				emailAddress: {
					required: true,
					emailCheck: true
				}
			},
			messages: {
				emailAddress: {
					required: "Please enter your registered email address."
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
