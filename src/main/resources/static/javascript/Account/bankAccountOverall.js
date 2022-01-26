//retrieve balance, iban, name of account by from login User
async function getSelectedAccountInfo(ibanElement, nameElement, balanceElement, imageElement) {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
    const iban = localStorage.getItem("SelectedIban")
    fetch(`http://localhost:8888/getAccountByIBAN?iban=${iban}`, requestOptions)
        .then(response => response.json())
        .then(data => {
            document.getElementById(ibanElement).innerHTML = data["accountIBAN"]
            let balanceValueInCents = String(data["accountBalanceInCents"]).padStart(3,'0')
            document.getElementById(balanceElement).innerHTML = "€ " + balanceValueInCents.slice(0, -2) + "," + balanceValueInCents.slice(-2)

            let name = data["clientName"]
            document.getElementById(nameElement).innerHTML = name;
            splitname(name, imageElement)
        })

        .catch(error => console.log('error', error));
}

function splitname(name, imageElementID) {

    let firstName
    let secondName
    if (name.includes(",")) {
        let splitName = name.split(",")
        firstName = splitName[1]
        secondName = splitName[0]
    }
    else {
        let splitCompanyName = name.split(" ")
        firstName = splitCompanyName[0]
        secondName = splitCompanyName[1]
    }
        document.getElementById(imageElementID).setAttribute("src", `https://eu.ui-avatars.com/api/?name=${firstName}+${secondName}&background=random`)

    }



// get account , iban and name
async function getAccountByIbanRetrieveNameAndIban(IbanKey, ibanElement, nameElement, imageElement) {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    const iban = localStorage.getItem(IbanKey)

    fetch(`http://localhost:8888/getAccountByIBAN?iban=${iban}`, requestOptions)
        .then(response => response.json())
        .then(data => {
            document.getElementById(ibanElement).innerHTML = data["accountIBAN"]
            let name = data["clientName"]
            document.getElementById(nameElement).innerHTML = name;
            splitname(name, imageElement)
        })

        .catch(error => console.log('error', error));
}