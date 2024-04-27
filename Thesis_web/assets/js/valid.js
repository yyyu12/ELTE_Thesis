document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('checkoutForm');
    const useDifferentShippingCheckbox = document.getElementById('show-shipping-address');
    const shippingFields = document.querySelectorAll('#shippingAddress input');
    const billingFields = {
        street: document.getElementById('street_invoice'),
        city: document.getElementById('city_invoice'),
        zip: document.getElementById('zip_invoice'),
        country: document.getElementById('country_invoice'),
        phone: document.getElementById('phonenumber_invoice')
    };
    const shippingFieldsMap = {
        street: document.getElementById('street_shipping'),
        city: document.getElementById('city_shipping'),
        zip: document.getElementById('zip_shipping'),
        country: document.getElementById('country_shipping'),
        phone: document.getElementById('phonenumber_shipping')
    };

    // Handle checkbox to show/hide shipping address fields
    useDifferentShippingCheckbox.addEventListener('change', function () {
        if (this.checked) {
            shippingFields.forEach(field => field.removeAttribute('disabled'));
        } else {
            shippingFields.forEach(field => field.setAttribute('disabled', 'disabled'));
            copyBillingToShipping();
        }
    });

    function copyBillingToShipping() {
        shippingFieldsMap.street.value = billingFields.street.value;
        shippingFieldsMap.city.value = billingFields.city.value;
        shippingFieldsMap.zip.value = billingFields.zip.value;
        shippingFieldsMap.country.value = billingFields.country.value;
        shippingFieldsMap.phone.value = billingFields.phone.value;
    }

    // Form submission handling
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        if (validateForm()) {
            updateArtwork();
            addToBlindBox();
            proceedToNextPage();

            // clear the session storage
            sessionStorage.removeItem('receivedArtwork');
            sessionStorage.removeItem('boxPrice');
        } else {
            alert('Please fill in all required fields.');
        }
    });

    function validateForm() {
        const fullname = document.getElementById('fullname_invoice').value.trim();
        const email = document.getElementById('emailaddress_invoice').value.trim();
        const street = document.getElementById('street_invoice').value.trim();
        const city = document.getElementById('city_invoice').value.trim();
        const zip = document.getElementById('zip_invoice').value.trim();
        const country = document.getElementById('country_invoice').value.trim();
        const phoneNumber = document.getElementById('phonenumber_invoice').value.trim();
        let valid = true;

        document.querySelectorAll('.error').forEach(function (el) {
            el.remove();
        });

        if (!fullname) {
            addError('fullname_invoice', 'Full name is required.');
            valid = false;
        } else if (validateCharater(fullname)) {
            addError('fullname_invoice', 'Fullname cannot contain numbers.');
            valid = false;
        }

        if (!email) {
            addError('emailaddress_invoice', 'Email address is required.');
            valid = false;
        } else if (!isValidEmail(email)) {
            addError('emailaddress_invoice', 'Invalid email address format.');
            valid = false;
        }

        if (!street) {
            addError('street_invoice', 'Street address is required.');
            valid = false;
        }

        if (!city) {
            addError('city_invoice', 'City is required.');
            valid = false;
        } else if (validateCharater(city)) {
            addError('city_invoice', 'City cannot contain numbers.');
            valid = false;
        }

        if (!zip) {
            addError('zip_invoice', 'ZIP code is required.');
            valid = false;
        } else if (isNaN(zip)) {
            addError('zip_invoice', 'ZIP code must be a number.');
            valid = false;
        } else if (zip.length >= 6) {
            addError('zip_invoice', 'ZIP code must be less than 6 digits.');
            valid = false;
        }

        if (!country) {
            addError('country_invoice', 'Country is required.');
            valid = false;
        } else if (validateCharater(country)) {
            addError('country_invoice', 'Country cannot contain numbers.');
            valid = false;
        }

        if (!phoneNumber) {
            addError('phonenumber_invoice', 'Phone number is required.');
            valid = false;
        } else if (!isValidPhone(phoneNumber)) {
            addError('phonenumber_invoice', 'Invalid phone number format.');
            valid = false;
        }

        // 如果用户选择了不同的送货地址，则需要验证送货地址
        if (useDifferentShippingCheckbox.checked) {
            const shippingStreet = document.getElementById('street_shipping').value.trim();
            const shippingCity = document.getElementById('city_shipping').value.trim();
            const shippingZip = document.getElementById('zip_shipping').value.trim();
            const shippingCountry = document.getElementById('country_shipping').value.trim();
            const shippingPhoneNumber = document.getElementById('phonenumber_shipping').value.trim();

            if (!shippingStreet) {
                addError('street_shipping', 'Street address is required.');
                valid = false;
            }

            if (!shippingCity) {
                addError('city_shipping', 'City is required.');
                valid = false;
            } else if (validateCharater(shippingCity)) {
                addError('city_shipping', 'City cannot contain numbers.');
                valid = false;
            }

            if (!shippingZip) {
                addError('zip_shipping', 'ZIP code is required.');
                valid = false;
            } else if (isNaN(shippingZip)) {
                addError('zip_shipping', 'ZIP code must be a number.');
                valid = false;
            } else if (shippingZip.length >= 6) {
                addError('zip_shipping', 'ZIP code must be less than 6 digits.');
                valid = false;
            }

            if (!shippingCountry) {
                addError('country_shipping', 'Country is required.');
                valid = false;
            } else if (validateCharater(shippingCountry)) {
                addError('country_shipping', 'Country cannot contain numbers.');
                valid = false;
            }

            if (!isValidPhone(shippingPhoneNumber)) {
                addError('phonenumber_shipping', 'Invalid phone number format.');
                valid = false;
            }
        }

        const cardName = document.getElementById('card-name').value.trim();
        const cardNumber = document.getElementById('card-number').value.trim();
        const expiryDate = document.getElementById('expiry-date').value.trim();
        const cvv = document.getElementById('cvv').value.trim();

        if (!cardName) {
            addError('card-name', 'Name on card is required.');
            valid = false;
        } else if (validateCharater(cardName)) {
            addError('card-name', 'Name on card cannot contain numbers.');
            valid = false;
        }

        if (!isValidCardNumber(cardNumber)) {
            addError('card-number', 'Need a valid 16-digit card number.');
            valid = false;
        }

        if (!isValidExpiryDate(expiryDate)) {
            addError('expiry-date', 'Invalid expiry date format.');
            valid = false;
        }

        if (!isValidCVV(cvv)) {
            addError('cvv', 'Need a valid 3-digit CVC.');
            valid = false;
        }

        return valid;
    }

    function addError(fieldId, message) {
        const inputElement = document.getElementById(fieldId);
        const error = document.createElement('div');
        error.className = 'error';
        error.textContent = message;
        error.style.color = 'red';
        error.style.fontSize = '0.8em';
        if (inputElement.nextSibling) {
            inputElement.parentNode.insertBefore(error, inputElement.nextSibling);
        } else {
            inputElement.parentNode.appendChild(error);
        }
    }

    const isValidEmail = email => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    };

    const isValidPhone = phone => {
        const re = /^\+?\d{6,14}$/;
        return re.test(phone);
    };

    const isValidCardNumber = cardNumber => {
        const re = /^\d{16}$/;
        return re.test(cardNumber);
    };

    const isValidExpiryDate = expiryDate => {
        const re = /^(0[1-9]|1[0-2])\/\d{2}$/;
        return re.test(expiryDate);
    };

    const isValidCVV = cvv => {
        const re = /^\d{3}$/;
        return re.test(cvv);
    };

    function validateCharater(value) {
        const regex = /^[a-zA-Z\s]+$/;
        return regex.test(name);
    }

    // update the artwork owner
    const artworkData = sessionStorage.getItem('receivedArtwork');
    const artwork = JSON.parse(artworkData);

    // get the user id
    const userInfoString = localStorage.getItem('userInfo');
    const userInfo = JSON.parse(userInfoString);

    // get blind box price and info
    let price = sessionStorage.getItem('boxPrice');

    async function updateArtwork() {
        console.log('Artwork:', artwork);
        // update the artwork owner
        fetch(`http://localhost:8080/artwork/purchase/${artwork.id}?userId=${userInfo.id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log('Artwork updated successfully.');
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

    async function addToBlindBox() {
        // http://localhost:8080/blindBox/addBlindBox
        fetch('http://localhost:8080/blindBox/addBlindBox', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                user_id: userInfo.id,
                artwork_id: artwork.id,
                price: price
            })
        })
        .then(response => response.json())
        .then(data => {
            const blindBoxId = data.info.id;
            sessionStorage.setItem('blindBoxId', blindBoxId);
            console.log('Blind box added successfully.');
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

    function proceedToNextPage() {
        window.location.href = 'openBox.html';
    }
});