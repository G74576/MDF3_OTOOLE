/*
	Kevin O'Toole
	MDF3 Term 1407
	Week 4 Project
	Project: Marine Grub
	File: main.js
*/

$('#home').on('pageinit', function(){

	function id(x){
		var elementId = document.getElementById(x);
		return elementId;
	}

	function validate(e){
		//Define elements to check:
		var getFname = id('fname');
		var getLname = id('lname');
		var getEmail = id('email');
		var getPhone = id('phone');


		//Reset error message:
		errMsg.innerHTML = "";
		getFname.removeAttribute("style", "border");
		getLname.removeAttribute("style", "border");
		getEmail.removeAttribute("style", "border");
		getPhone.removeAttribute("style", "border");

		//Get error messages:
		var errorMessages = [];

		//First name validation:
		if(getFname.value === ""){
			var fnameE = "Please enter a first name.";
			getFname.style.border = "1px solid red";
			errorMessages.push(fnameE);
		}

		//Last name validation:
		if(getLname.value === ""){
			var lnameE = "Please enter a last name.";
			getLname.style.border = "1px solid red";
			errorMessages.push(lnameE);
		}

		//Email validation:
		var emailR = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(!(emailR.exec(getEmail.value))){
			var emailE = "Please enter a valid email address.";
			getEmail.style.border = "1px solid red";
			errorMessages.push(emailE);
		}

		//Telephone validation:
		var phoneR = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
		if(getPhone.value.match(phoneR)){
			return true;
		}else {
			var phoneE = "Please enter a valid phone number.";
			getPhone.style.border = "1px solid red";
			errorMessages.push(phoneE);
		}

		//If there was an error :
		if(errorMessages.length >= 1){
			for(var i=0, j=errorMessages.length; i<j; i++){
				var errorText = document.createElement("li");
				errorText.style.color = "#BA0000";
				errorText.innerHTML = errorMessages[i];
				errMsg.appendChild(errorText);
			}
			e.preventDefault();
			return false;
		}else{
			//If all is ok :
		}
	}

	var errMsg = id('error');
	var submit = id('submit');
	submit.addEventListener("click", validate);
});