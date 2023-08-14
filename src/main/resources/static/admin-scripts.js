function backToHome() {
    window.location.href = "/";
}

function submitEdit() {
    // Tu zbieramy dane z edytowanego wniosku. Na razie zakładam, że masz tylko jeden wniosek na stronie.
    // Jeśli masz ich więcej, trzeba będzie nieco zmodyfikować ten kod.

    let tripDate = document.querySelector("input[type='text'][name='tripDate']").value;
    let receiptType = document.querySelector("input[type='text'][name='receiptType']").value;
    let days = document.querySelector("input[type='text'][name='days']").value;
    let isDisabled = document.querySelector("input[type='checkbox'][name='isDisabled']").checked;
    let distance = document.querySelector("input[type='text'][name='distance']").value;

    // Tworzymy obiekt z danymi
    let data = {
        tripDate: tripDate,
        receiptType: receiptType,
        days: days,
        isDisabled: isDisabled,
        distance: distance
    };

    // Wysyłamy dane na serwer
    fetch("/admin/edit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Zmiany zostały zapisane!");
            } else {
                alert("Wystąpił błąd podczas zapisywania zmian!");
            }
        });
}

function fetchAndDisplayClaimsData() {
    fetch("/admin/claims-data")
        .then(response => response.text()) // Lub `response.json()`, jeśli zdecydowałeś się zwrócić dane jako JSON.
        .then(data => {
            document.getElementById("claims-data-container").innerHTML = data;
        });
}

// Uruchom funkcję, gdy strona zostanie wczytana
window.onload = fetchAndDisplayClaimsData;



// function viewClaims() {
//     window.location.href = "/admin/claims"
// }

// document.addEventListener('DOMContentLoaded', function() {
//
//     // Funkcja do wyświetlenia listy wniosków na stronie:
//     function loadClaims() {
//         fetch('/admin/claims')
//             .then(response => {
//                 if (!response.ok) {
//                     throw new Error('Network response was not ok');
//                 }
//                 return response.text();
//             })
//             .then(data => {
//                 const claimsContainer = document.getElementById('claims-container');                if (!claimsContainer) {
//                     console.error("claimsContainer not found!");
//                     return;
//                 }
//                 claimsContainer.textContent = data;
//             })
//             .catch(error => {
//                 console.log('There was a problem with the fetch operation:', error.message);
//             });
//     }
//
//     // Załaduj formularze po załadowaniu strony
//     loadClaims();
//     submitEdit();
// });



// document.addEventListener('DOMContentLoaded', function() {
//     fetch('/admin/claims')
//         .then(response => response.json())
//         .then(data => {
//             const claimsDiv = document.getElementById('claims-list');
//             data.forEach(claim => {
//                 const claimDiv = document.createElement('div');
//                 claimDiv.textContent = claim;
//                 // Tutaj możemy dodać przyciski do zatwierdzania/odrzucania
//                 claimsDiv.appendChild(claimDiv);
//             });
//         });
// });

// document.addEventListener('DOMContentLoaded', function() {
//     fetch('/admin/claims')
//         .then(response => response.json())
//         .then(data => {
//             const claimsList = document.getElementById('claims-list');
//             data.forEach(claim => {
//                 const claimRow = document.createElement('tr');
//
//                 // Dodaj kolumny do wiersza
//                 const dateCell = document.createElement('td');
//                 dateCell.textContent = claim.tripDate;
//                 claimRow.appendChild(dateCell);
//
//                 const receiptTypeCell = document.createElement('td');
//                 receiptTypeCell.textContent = claim.receiptType;
//                 claimRow.appendChild(receiptTypeCell);
//
//                 const daysCell = document.createElement('td');
//                 daysCell.textContent = claim.days;
//                 claimRow.appendChild(daysCell);
//
//                 const distanceCell = document.createElement('td');
//                 distanceCell.textContent = claim.distance;
//                 claimRow.appendChild(distanceCell);
//
//                 // Dodaj przyciski akcji
//                 const actionsCell = document.createElement('td');
//                 const approveBtn = document.createElement('button');
//                 approveBtn.textContent = 'Zatwierdź';
//                 approveBtn.addEventListener('click', () => approveClaim(claim.id));
//                 actionsCell.appendChild(approveBtn);
//
//                 const rejectBtn = document.createElement('button');
//                 rejectBtn.textContent = 'Odrzuć';
//                 rejectBtn.addEventListener('click', () => rejectClaim(claim.id));
//                 actionsCell.appendChild(rejectBtn);
//
//                 claimRow.appendChild(actionsCell);
//
//                 claimsList.appendChild(claimRow);
//             });
//         });
// });

// function approveClaim(claimId) {
//     // Logika zatwierdzania wniosku (np. wywołanie żądania POST)
// }
//
// function rejectClaim(claimId) {
//     // Logika odrzucania wniosku
// }

// function loadClaims() {
//     fetch('/admin/claims')
//         .then(response => response.json())
//         .then(data => {
//             const claimsContainer = document.getElementById('claims-container');
//
//             // Wyczyść obecną listę, by unikać duplikatów podczas ponownego ładowania.
//             claimsContainer.innerHTML = '';
//
//             data.forEach(claim => {
//                 const claimDetails = document.createElement('details');
//                 const claimSummary = document.createElement('summary');
//                 claimSummary.textContent = `Wniosek z dnia: ${claim.tripDate}`;
//                 claimDetails.appendChild(claimSummary);
//
//                 const infoDiv = document.createElement('div');
//                 infoDiv.innerHTML = `
//                     <p>Rodzaj paragonu: ${claim.receiptType}</p>
//                     <p>Dni: ${claim.days}</p>
//                     <p>Dystans: ${claim.distance} km</p>
//                 `;
//                 claimDetails.appendChild(infoDiv);
//
//                 const actionsDiv = document.createElement('div');
//                 const approveBtn = document.createElement('button');
//                 approveBtn.textContent = 'Zatwierdź';
//                 approveBtn.addEventListener('click', () => approveClaim(claim.id));
//                 actionsDiv.appendChild(approveBtn);
//
//                 const rejectBtn = document.createElement('button');
//                 rejectBtn.textContent = 'Odrzuć';
//                 rejectBtn.addEventListener('click', () => rejectClaim(claim.id));
//                 actionsDiv.appendChild(rejectBtn);
//
//                 claimDetails.appendChild(actionsDiv);
//                 claimsContainer.appendChild(claimDetails);
//             });
//         });
// }

// document.addEventListener('DOMContentLoaded', function() {
//     loadClaims();
// // });
//
// function approveClaim(claimId) {
//     // Logika zatwierdzania wniosku
//     // ...
//     loadClaims();  // Ponownie załaduj wnioski po zatwierdzeniu/odrzuceniu
// }
//
// function rejectClaim(claimId) {
//     // Logika odrzucania wniosku
//     // ...
//     loadClaims();  // Ponownie załaduj wnioski po zatwierdzeniu/odrzuceniu
// }