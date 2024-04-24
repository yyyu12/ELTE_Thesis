document.addEventListener('DOMContentLoaded', function() {
    const userInfoString = localStorage.getItem('userInfo');
    if (userInfoString) {
        const userInfo = JSON.parse(userInfoString);

        // 获取管理员链接的DOM元素
        const adminLink = document.getElementById('adminPageLink');

        // 检查用户是否为管理员，并相应地显示或隐藏管理员链接
        if (userInfo.isAdmin) {
            adminLink.style.display = 'block'; // 或者使用'flex'，取决于你的CSS
        } else {
            adminLink.style.display = 'none';
        }
    }
});


/* ======== user login profile dropdown start ======== */
function onUserLoggedIn(userInfo) {
    const navItem = document.querySelector('#userAccount');
    const userLoginLink = document.getElementById('userLoginLink'); // 获取登录链接元素
    userLoginLink.style.display = 'none'; // 隐藏登录链接
    
    const username = userInfo.username; // 从用户信息中获取用户名
    // 创建下拉菜单的HTML字符串
    const dropdownHTML = `
        <div class="dropdown-center" id="userDropdown">
            <button class="btn btn-blue-green dropdown-toggle btn-font" type="button" id="userDropdownMenu" data-bs-toggle="dropdown" aria-expanded="false">
                Hi ${username} <!-- 插入用户名 -->
            </button>
            <ul class="dropdown-menu " aria-labelledby="userDropdownMenu">
                <li><a class="dropdown-item" href="user-profile.html">My Profile</a></li>
                <li><a class="dropdown-item" href="user-wishlist.html">My Wish List</a></li>
                <li><a class="dropdown-item" href="user-cart.html">My Cart</a></li>
                <li><a class="dropdown-item" href="user-orders.html">My Orders</a></li>
                <li><a class="dropdown-item" href="#" id="logoutLink">Logout</a></li>
            </ul>
        </div>
    `;
    
    // 将下拉菜单的HTML添加到.nav-item元素
    navItem.innerHTML += dropdownHTML;
    
    // 添加登出事件监听器
    const logoutLink = document.getElementById('logoutLink');
    logoutLink.addEventListener('click', onLogoutClicked);
}

// 登出事件处理函数
function onLogoutClicked(event) {
    event.preventDefault();
    localStorage.removeItem('userInfo');
    
    // 删除下拉菜单
    const userDropdown = document.getElementById('userDropdown');
    if (userDropdown) {
        userDropdown.remove();
    }
    
    // 显示登录链接
    const userLoginLink = document.getElementById('userLoginLink');
    userLoginLink.style.display = 'block';
    
    // 重定向到登录页面或首页
    window.location.href = 'user-login.html';
}

document.addEventListener('DOMContentLoaded', function() {
    const userInfo = localStorage.getItem('userInfo');
    
    if (userInfo) {
        onUserLoggedIn(JSON.parse(userInfo));
    }
});
/* ======== user login profile dropdown end ======== */

/* ======== checkout button logic start ======== */
const checkoutButton = document.getElementById('checkoutButton');
checkoutButton.addEventListener('click', function(event) {
    event.preventDefault();
    const userInfo = localStorage.getItem('userInfo');
    
    if (userInfo) {
        window.location.href = 'user-checkout.html';
    } else {
        alert('Please log in to continue to checkout.');
        window.location.href = 'user-login.html';
    }
});

/* ======== checkout button logic end ======== */

/* ======== Password visibility toggle start ======== */
// 注册表单提交处理
const togglePasswordVisibility = (inputElement, toggleElement) => {
    if(inputElement.type === 'password') {
        inputElement.type = 'text';
        toggleElement.innerHTML = '<i class="fas fa-eye cursor-pointer p-2"></i>';
    } else {
        inputElement.type = 'password';
        toggleElement.innerHTML = '<i class="fas fa-eye-slash cursor-pointer p-2"></i>';
    }
};

/* ======== choice select start ======== */
// Define the choicesSelect function
function choicesSelect() {
    var elements = document.querySelectorAll('.js-choice');
    
    // Check if elements exist by verifying the NodeList length
    if (elements.length > 0) {
        elements.forEach(function (item) {
            var removeItemBtn = item.getAttribute('data-remove-item-button') === 'true';
            var placeHolder = item.getAttribute('data-placeholder') !== 'false';
            var placeHolderVal = item.getAttribute('data-placeholder-val') ? item.getAttribute('data-placeholder-val') : 'Type and hit enter';
            var maxItemCount = item.getAttribute('data-max-item-count') ? parseInt(item.getAttribute('data-max-item-count'), 10) : 3;
            var searchEnabled = item.getAttribute('data-search-enabled') === 'true';
    
            var choices = new Choices(item, {
                removeItemButton: removeItemBtn,
                placeholder: placeHolder,
                placeholderValue: placeHolderVal,
                maxItemCount: maxItemCount,
                searchEnabled: searchEnabled,
                shouldSort: false,
                allowHTML: true // Add this line to your existing options
            });
        });
    }
}

// Call choicesSelect after the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function () {
    choicesSelect();
});



