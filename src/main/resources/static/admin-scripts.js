function backToHome() {
    window.location.href = "/";
}

function submitEdit(buttonElement) {
    let detailsElement = buttonElement.closest('details');

    let tripDate = detailsElement.querySelector("input[name='tripDate']").value;
    let receiptType = detailsElement.querySelector("input[name='receiptType']").value;
    let days = detailsElement.querySelector("input[name='days']").value;
    let distance = detailsElement.querySelector("input[name='distance']").value;

    // Tworzymy obiekt z danymi
    let data = {
        tripDate: tripDate,
        receiptType: receiptType,
        days: days,
        distance: distance
    };

    // Wysyłamy dane na serwer
    fetch("/admin/edit", {
        method: "POST",
        headers: {
            "Content-Type": "text/plain"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(data => {
            if (data === "success") {
                alert("Changes have been saved!");
            } else {
                alert("An error occurred while saving changes!");
            }
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error.message);
        });
}

function fetchAndDisplayClaimsData() {
    fetch("/admin/claims-data")
        .then(response => response.text())
        .then(data => {
            document.getElementById("claims-data-container").innerHTML = data;
        });
}

function submitRates(buttonElement) {
    const dailyRateInput = document.querySelector("input[name='dailyRate']");
    const mileageRateInput = document.querySelector("input[name='mileageRate']");

    const dailyRateValue = dailyRateInput.value;
    const mileageRateValue = mileageRateInput.value;

    fetch('/admin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'dailyRate=' + dailyRateValue + '&mileageRate=' + mileageRateValue
    })
        .then(response => response.text())
        .then(data => {
            console.log(data); // Oczekiwana odpowiedź serwera
        })
        .catch(error => {
            console.error('Error sending rates:', error);
        });
}


window.onload = fetchAndDisplayClaimsData;


