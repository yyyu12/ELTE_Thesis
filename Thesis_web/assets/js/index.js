document.addEventListener('DOMContentLoaded', function() {
    const userInfoString = localStorage.getItem('userInfo');
    
    if (userInfoString) {
        const userInfo = JSON.parse(userInfoString);

        const adminLink = document.getElementById('adminPageLink');

        // check if the user is an admin and show/hide the admin link
        if (userInfo.isAdmin) {
            adminLink.style.display = 'block';
        } else {
            adminLink.style.display = 'none';
        }
    }
});


/* ======== user login profile dropdown start ======== */
function onUserLoggedIn(userInfo) {
    const navItem = document.querySelector('#userAccount');
    const userLoginLink = document.getElementById('userLoginLink');
    userLoginLink.style.display = 'none'; // 隐藏登录链接
    
    const username = userInfo.username;

    const dropdownHTML = `
        <div class="dropdown-center" id="userDropdown">
            <button class="btn btn-blue-green dropdown-toggle btn-font" type="button" id="userDropdownMenu" data-bs-toggle="dropdown" aria-expanded="false">
                Hi ${username} <!-- 插入用户名 -->
            </button>
            <ul class="dropdown-menu " aria-labelledby="userDropdownMenu">
                <li><a class="dropdown-item" href="user-profile.html">My Profile</a></li>
                <li><a class="dropdown-item" href="user-cart.html">My Cart</a></li>
                <li><a class="dropdown-item" href="user-orders.html">My Orders</a></li>
                <li><a class="dropdown-item" href="user-blindBoxHistory.html">My Blind Box</a></li>
                <li><a class="dropdown-item" href="user-wishlist.html">My Wish List</a></li>
                <li><a class="dropdown-item" href="#" id="logoutLink">Logout</a></li>
            </ul>
        </div>
    `;
    
    navItem.innerHTML += dropdownHTML;
    
    const logoutLink = document.getElementById('logoutLink');
    logoutLink.addEventListener('click', onLogoutClicked);
}

// Logout function
function onLogoutClicked(event) {
    event.preventDefault();
    localStorage.removeItem('userInfo');
    
    const userDropdown = document.getElementById('userDropdown');
    if (userDropdown) {
        userDropdown.remove();
    }
    
    const userLoginLink = document.getElementById('userLoginLink');
    userLoginLink.style.display = 'block';
    window.location.href = 'user-login.html';
}

document.addEventListener('DOMContentLoaded', function() {
    const userInfo = localStorage.getItem('userInfo');
    
    if (userInfo) {
        onUserLoggedIn(JSON.parse(userInfo));
    }
});
/* ======== user login profile dropdown end ======== */

/* ======== checkout and view cart button logic start ======== */
const checkoutButton = document.getElementById('checkoutButton');
const viewCartButton = document.getElementById('viewCartButton');

checkoutButton.addEventListener('click', function() {
    const userInfo = localStorage.getItem('userInfo');
    
    if (userInfo) {
        window.location.href = 'user-checkout.html';
    } else {
        window.location.href = 'user-login.html';
    }
});

viewCartButton.addEventListener('click', function() {
    const userInfo = localStorage.getItem('userInfo');
    
    if (userInfo) {
        window.location.href = 'user-cart.html';
    } else {
        window.location.href = 'user-login.html';
    }
});

/* ======== checkout button logic end ======== */

/* ======== Password visibility toggle start ======== */
const togglePasswordVisibility = (inputElement, toggleElement) => {
    if(inputElement.type === 'password') {
        inputElement.type = 'text';
        toggleElement.innerHTML = '<i class="fas fa-eye cursor-pointer p-2"></i>';
    } else {
        inputElement.type = 'password';
        toggleElement.innerHTML = '<i class="fas fa-eye-slash cursor-pointer p-2"></i>';
    }
};