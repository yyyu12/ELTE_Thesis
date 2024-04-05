

/* ======== user register end ======== */

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

/* ======== choice select end ======== */

/* 
document.addEventListener('DOMContentLoaded', function() {
    // 假设这个函数返回true如果用户登录，否则返回false
    function isUserLoggedIn() {
        // 这里的逻辑应该根据实际应用的需求来实现
        // 例如，可以检查localStorage或cookies
        return false; // 假设用户未登录
    }

    var userIconLink = document.querySelector('.nav-item .navbar-icon-link');

    userIconLink.addEventListener('click', function(event) {
        event.preventDefault(); // 阻止链接默认行为

        if (isUserLoggedIn()) {
            // 用户已登录，显示下拉菜单
            // 这里需要您根据实际的下拉菜单实现来编写代码
            // 例如，可以使用Bootstrap的dropdown组件
            console.log('显示下拉菜单');
        } else {
            // 用户未登录，跳转到登录页面
            window.location.href = 'user-login.html';
        }
    });
});

/* ======== theme switcher end ======== */

