document.addEventListener('DOMContentLoaded', function() {
    fetch('/admin/claims')
        .then(response => response.json())
        .then(data => {
            const claimsDiv = document.getElementById('claims-list');
            data.forEach(claim => {
                const claimDiv = document.createElement('div');
                claimDiv.textContent = claim;
                // Tutaj możemy dodać przyciski do zatwierdzania/odrzucania
                claimsDiv.appendChild(claimDiv);
            });
        });
});

document.addEventListener('DOMContentLoaded', function() {
    fetch('/admin/claims')
        .then(response => response.json())
        .then(data => {
            const claimsList = document.getElementById('claims-list');
            data.forEach(claim => {
                const claimRow = document.createElement('tr');

                // Dodaj kolumny do wiersza
                const dateCell = document.createElement('td');
                dateCell.textContent = claim.tripDate;
                claimRow.appendChild(dateCell);

                const receiptTypeCell = document.createElement('td');
                receiptTypeCell.textContent = claim.receiptType;
                claimRow.appendChild(receiptTypeCell);

                const daysCell = document.createElement('td');
                daysCell.textContent = claim.days;
                claimRow.appendChild(daysCell);

                const distanceCell = document.createElement('td');
                distanceCell.textContent = claim.distance;
                claimRow.appendChild(distanceCell);

                // Dodaj przyciski akcji
                const actionsCell = document.createElement('td');
                const approveBtn = document.createElement('button');
                approveBtn.textContent = 'Zatwierdź';
                approveBtn.addEventListener('click', () => approveClaim(claim.id));
                actionsCell.appendChild(approveBtn);

                const rejectBtn = document.createElement('button');
                rejectBtn.textContent = 'Odrzuć';
                rejectBtn.addEventListener('click', () => rejectClaim(claim.id));
                actionsCell.appendChild(rejectBtn);

                claimRow.appendChild(actionsCell);

                claimsList.appendChild(claimRow);
            });
        });
});

function approveClaim(claimId) {
    // Logika zatwierdzania wniosku (np. wywołanie żądania POST)
}

function rejectClaim(claimId) {
    // Logika odrzucania wniosku
}

function loadClaims() {
    // ...
    // Tutaj wklej cały kod wczytujący wnioski
    // ...
}

document.addEventListener('DOMContentLoaded', function() {
    loadClaims();
});

function approveClaim(claimId) {
    // Logika zatwierdzania wniosku
    // ...
    loadClaims();  // Ponownie załaduj wnioski po zatwierdzeniu/odrzuceniu
}

function rejectClaim(claimId) {
    // Logika odrzucania wniosku
    // ...
    loadClaims();  // Ponownie załaduj wnioski po zatwierdzeniu/odrzuceniu
}