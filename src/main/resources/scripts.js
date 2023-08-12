
function goToUser() {
    window.location.href = "/user.html";
    console.log("asd")
}

function goToAdmin() {
    window.location.href = "/admin.html";
}

function changeBackground() {
    document.body.style.backgroundColor = "#34a853"; // green
}

function backToHome() {
    window.location.href = "/";
}

function checkLogin() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Dummy check for this example
    if(username === "admin" && password === "admin") {
        document.getElementById("loginForm").style.display = "none";
        document.getElementById("adminBtn").style.display = "block";
    }
}

let receipts = [];

function addReceipt() {
    const receiptType = document.getElementById("receiptType").value;
    const receiptValue = document.getElementById("receiptValue").value;
    receipts.push({ type: receiptType, value: parseFloat(receiptValue) });

    const list = document.getElementById("receiptList");
    const item = document.createElement("li");
    item.textContent = `${receiptType}: ${receiptValue} $`;
    list.appendChild(item);
}

function addReceipt() {
    const receiptType = document.getElementById('receiptType').value;
    const receiptValue = document.getElementById('receiptValue').value;

    if (!receiptValue) {
        alert('Please provide a receipt value.');
        return;
    }

    const receiptList = document.getElementById('receiptList');
    const listItem = document.createElement('li');
    listItem.textContent = `${receiptType}: ${receiptValue} $`;
    receiptList.appendChild(listItem);
}

function submitApplication() {
    // Tutaj będziemy przetwarzać wniosek i wysyłać go do serwera.
    // Na razie dodaję alert dla symulacji.
    alert("Application has been submitted!");
}

document.getElementById('userButton').addEventListener('click', function() {
    fetch('/user')
        .then(response => response.text())
        .then(data => {
            // Wyświetl odpowiedź lub zaktualizuj interfejs użytkownika
            console.log(data);
        });
});

document.getElementById('adminButton').addEventListener('click', function() {
    fetch('/admin')
        .then(response => response.text())
        .then(data => {
            // Wyświetl odpowiedź lub zaktualizuj interfejs użytkownika
            console.log(data);
        });
});