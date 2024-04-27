// user profile left card information
document.addEventListener('DOMContentLoaded', function() {
    const userInfoString = localStorage.getItem('userInfo');
    
    if (userInfoString) {
        const userInfo = JSON.parse(userInfoString);
        const userNameElement = document.createElement('h6');
        userNameElement.textContent = userInfo.username;

        // Set the class attribute of the userNameElement
        const userEmailElement = document.createElement('div'); 
        userEmailElement.textContent = userInfo.email;
        userEmailElement.setAttribute('class', 'text-reset text-primary-hover small');

        // Create a horizontal rule element
        const hrElement = document.createElement('hr');

        // Get the container element
        const container = document.getElementById('userNameAndEmail');
        container.appendChild(userNameElement);
        container.appendChild(userEmailElement);
        container.appendChild(hrElement);
    }

    // sign out button
    const signOutButton = document.getElementById('signOutButton');
    signOutButton.addEventListener('click', function() {
        localStorage.removeItem('userInfo');
        
        // Delete dropdown
        const userDropdown = document.getElementById('userDropdown');
        if (userDropdown) {
            userDropdown.remove();
        }
        
        // Show login link
        const userLoginLink = document.getElementById('userLoginLink');
        userLoginLink.style.display = 'block';
        
        window.location.href = 'user-login.html';
    });
});

