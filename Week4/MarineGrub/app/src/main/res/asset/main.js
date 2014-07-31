/*
	Kevin O'Toole
	MDF3 Term 1407
	Week 4 Project
	Project: Marine Grub
	File: main.js
*/

$(document).ready(function(){
	$('#collectionForm').validate({
		//errorElement: "div",
		errorPlacement: function(error, element){
			error.appendTo("div#errors");
		},
		rules:{
			fname: {
				required: true
			},
			lname: {
				required: true
			},
			email: {
				required: true,
				email: true
			},
			phone: {
				required: true
			}
		},
		messages: {
			fname: {
				required: "Please enter your first name"
			},
			lname: {
				required: "Please enter your last name"
			},
			email: {
				required: "Please enter a valid email address"
			},
			phone: {
				required: "please enter your telephone number"
			}
		},
		submitHandler: function(form){
			var fname = $('#fname').val();
			var lname = $('#lname').val();
			var email = $('#email').val();
			var phone = $('#phone').val();
			
			console.log('First Name: ' + fname + ' ' + 'Last Name: ' + lname);
			console.log('Email: ' + email + ' ' + 'Phone: ' + phone);
			
			Android.sendInfo(fname,lname,email,phone);
		}
	});
});