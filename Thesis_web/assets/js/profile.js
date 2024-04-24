// user profile left card information
document.addEventListener('DOMContentLoaded', function() {
    // 假设用户信息存储在localStorage的userInfo中
    const userInfoString = localStorage.getItem('userInfo');
    
    if (userInfoString) {
        // 解析存储的字符串以获得用户信息对象
        const userInfo = JSON.parse(userInfoString);

        // 创建存放用户名的元素
        const userNameElement = document.createElement('h6');
        userNameElement.textContent = userInfo.username; // 假设userInfo对象中包含username

        // 创建存放电子邮件的元素
        const userEmailElement = document.createElement('div'); // 使用div而不是a
        userEmailElement.textContent = userInfo.email; // 直接设置文本内容为email
        userEmailElement.setAttribute('class', 'text-reset text-primary-hover small');

        // 创建一个<hr>元素
        const hrElement = document.createElement('hr');

        // 获取容器元素并添加这些新元素
        const container = document.getElementById('userNameAndEmail');
        container.appendChild(userNameElement);
        container.appendChild(userEmailElement);
        container.appendChild(hrElement); // 添加<hr>到容器
    }
});