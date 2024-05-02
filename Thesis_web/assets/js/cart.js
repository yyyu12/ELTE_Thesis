document.addEventListener('DOMContentLoaded', function() {
    const userInfoString = localStorage.getItem('userInfo');
    const userInfo = JSON.parse(userInfoString);

    const cartBadge = document.getElementById('cart-badge');
    
    let cartDetails = [];
    function fetchCartData(userId) {
        fetch(`http://localhost:8080/cart/user/${userId}/details`)
            .then(response => response.json())
            .then(data => {
                cartDetails = data;
                updateCartNumber();
                updateOffcanvasCart();
                updateTotalPrice();
            })
            .catch(error => console.error('Error fetching cart details:', error));
    }

    function updateCartNumber() {
        let cartNumber = cartDetails.length;
        // console.log(cartNumber); 
        if (cartBadge) cartBadge.textContent = cartNumber;
    }

    function updateOffcanvasCart() {
        const cartItemsContainer = document.querySelector('.minicart-product-list');
        // console.log(cartItemsContainer);
        cartItemsContainer.innerHTML = '';

        cartDetails.forEach(item => {
            const li = document.createElement('li');
            li.innerHTML = `
                <a href="product-details.html?id=${item.artwork_id}" class="image">
                    <img src="${item.image_url}" alt="${item.title}" class="img-fluid navbar-cart-product-image">
                </a>
                <div class="content">
                    <a href="product-details.html?id=${item.artwork_id}" class="title">${item.title}</a>
                    <span class="quantity-price">1 x <span class="amount">$${item.price.toFixed(2)}</span></span>
                    <a href="#" class="remove">×</a>
                </div>
            `;
            li.querySelector('.remove').addEventListener('click', function(event) {
                event.preventDefault();
                removeItemFromCart(item.cart_id);
            });
            cartItemsContainer.appendChild(li);
        });
    }

    function updateTotalPrice() {
        const totalPriceElement = document.getElementById('navBar-total');
        if (!totalPriceElement) return;
        let totalPrice = 0;
        cartDetails.forEach(item => totalPrice += item.price);
        totalPriceElement.textContent = `$${totalPrice.toFixed(2)}`;
    }

    if (userInfo) {
        fetchCartData(userInfo.id);
    }
});

function removeItemFromCart(cartId) {
    fetch(`http://localhost:8080/cart/removeByCartId/${cartId}`, { method: 'DELETE' })
    .then(response => response.json())
    .then(result => {
        if (result.code === 200) {
            alert('Artwork removed successfully.');
            location.reload();
        } else {
            alert('Failed to remove artwork from cart.');
        }
    })
    .catch(error => {
        console.error('Error removing item from cart:', error);
        alert('Error removing item from cart.');
    });
}
