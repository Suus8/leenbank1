function loadAccount() {
    let IbanInputValue = document.getElementById("IbanInput").value
    localStorage.setItem("SelectedIban", IbanInputValue)
    getSelectedAccountInfo("accountIBAN", "userFullName", "balance", "senderAvatar")
}

async function generateRegistrationCodePinTerminal() {
    const iban = localStorage.getItem("SelectedIban")
    const registrationCodePinTerminalEndpointURL = `http://localhost:8888/registerPinTerminal?accountIBAN=${iban}`
    fetch(registrationCodePinTerminalEndpointURL)
        .then(response => response.text())
        .then((response) => {
            document.getElementById("pin").innerHTML = response
        })
        .catch(err => console.log(err))
}