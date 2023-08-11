function goToUser() {
    window.location.href = "/user";
}

function goToAdmin() {
    window.location.href = "/admin";
}

function changeBackground() {
    document.body.style.backgroundColor = "#34a853"; // green
}

function checkLogin() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Dummy check for this example
    if(username === "admin" && password === "password123") {
        document.getElementById("loginForm").style.display = "none";
        document.getElementById("adminBtn").style.display = "block";
    }
}