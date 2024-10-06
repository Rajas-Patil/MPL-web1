function validateLogin() {
    // Sample credentials
    const validUsername = "user";
    const validPassword = "password";

    // Get input values
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Validate input
    if (username === validUsername && password === validPassword) {
        // Redirect on successful login
        window.location.href = "welcome.html";  // Change this URL to the page you want to redirect to
    } else {
        // Show error message
        const errorMessage = document.getElementById("error-message");
        errorMessage.style.display = "block";
    }
}