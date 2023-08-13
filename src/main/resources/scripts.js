function goToUser() {
    window.location.href = "/user.html";
}

function goToAdmin() {
    window.location.href = "/login.html";
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

    if(username === "admin" && password === "admin") {
        window.location.href = "/admin.html";
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const reimbursementForm = document.getElementById('reimbursementForm');
    if (reimbursementForm) {

        const totalAmountSpan = document.getElementById('total-amount');
        reimbursementForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const tripDate = document.getElementById('trip-date').value;
            const receiptType = document.getElementById('receipts-dropdown').value;
            const days = document.getElementById('days').value;
            const disableDays = document.getElementById('disable-days').checked;
            const distance = document.getElementById('distance').value;

            const formData = new URLSearchParams();
            formData.append('trip-date', tripDate);
            formData.append('receipt-type', receiptType);
            formData.append('days', days);
            formData.append('disable-days', disableDays);
            formData.append('distance', distance);

            console.log("Sending data:", formData.toString()); // TODO



            fetch('/submit', { // Zmieniłem '/user' na '/submit', ponieważ w twoim HTML endpoint to '/submit'
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                },
                body: formData.toString()
            })
                .then(response => {
                    console.log(response); // Zobacz całą odpowiedź
                    return response.text();
                })
                .then(text => {
                    // Wyświetl odpowiedź serwera (czyli obliczony zwrot kosztów) na stronie
                    totalAmountSpan.textContent = text.split(": ")[1];
                })
                .catch(error => {
                    console.error('There was an error sending the form data:', error);
                });
        });
    }
});
