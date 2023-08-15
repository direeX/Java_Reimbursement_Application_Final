function goToUser() {
    window.location.href = "/static/user.html";
}

function goToAdmin() {
    window.location.href = "/static/login.html";
}

function backToHome() {
    window.location.href = "/";
}

function checkLogin() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (username === "admin" && password === "admin") {
        window.location.href = "/static/admin.html";
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const reimbursementForm = document.getElementById('reimbursementForm');
    if (reimbursementForm) {

        const totalAmountSpan = document.getElementById('total-amount');
        reimbursementForm.addEventListener('submit', function(event) {
            event.preventDefault();

            const tripDate = document.getElementById('trip-date').value;
            const receiptType = document.getElementById('receipts-dropdown').value;
            const days = document.getElementById('days').value;
            const distance = document.getElementById('distance').value;

            const formData = new URLSearchParams();
            formData.append('trip-date', tripDate);
            formData.append('receipt-type', receiptType);
            formData.append('days', days);
            formData.append('distance', distance);

            //walidacja w przypadku pustych pól:
            if (!tripDate || !receiptType || !distance) {
                alert('Please complete all required fields.');
                return;
            }

            fetch('/submit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                },
                body: formData.toString()
            })
                .then(response => {
                    return response.text();
                })
                .then(text => {
                    totalAmountSpan.textContent = text.split(": ")[1];
                })
                .catch(error => {
                    console.error('There was an error sending the form data:', error);
                });
        });
    }
});