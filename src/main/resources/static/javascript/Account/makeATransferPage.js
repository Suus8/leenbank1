
// on loading page
window.onload = function () {
    getSelectedAccountInfo("accountIBAN","userFullName","balance","senderAvatar")
    storeElementInLocalStorage("existsIbanInDatabase", false)
    storeElementInLocalStorage("checkIfSufficientFunds", false)
};

const params = new URLSearchParams(window.location.search);

const id = params.get("id");

//check IBAN
function validateIBAN() {
    let IBAN = document.getElementById('recipient_IBAN').value;
    let IBANtrim = IBAN.trim();
    let ibanRGEX = /^([A-Z]{2}[ \-]?[0-9]{2})(?=(?:[ \-]?[A-Z0-9]){9,30}$)((?:[ \-]?[A-Z0-9]{3,5}){2,7})([ \-]?[A-Z0-9]{1,3})?$/;
    let ibanResult = ibanRGEX.test(IBANtrim);
    if (ibanResult === false) {
        alert(IBAN + " is geen geldig IBAN nummer");
        return true;
    }
    return false;
}

// check amount
function checkSenderBalanceHasEnoughMoney() {
    console.log(localStorage.getItem("senderBalance"));
    if (getUserInputEuroAmount() > localStorage.getItem("senderBalance")) {
        alert("Uw heeft niet genoeg geld in je rekening om deze transactie te doen ");
        return true;
    } else return false;
}

function getALLUserInput() {
    getUserInputEuroAmount();
    getUserInputDescription();
    getUserInputReceiverIBAN();
}

// get user input receiver IBAN
function getUserInputReceiverIBAN() {
    let userReceiverIBAN = document.getElementById("recipient_IBAN").value;
    localStorage.setItem("userReceiverIBAN", userReceiverIBAN);
}

// get user input bedrag
function getUserInputEuroAmount() {
    let wholeEuro = document.getElementById("transaction_amount_euro").value;
    let euroCent = document.getElementById("transaction_amount_euro_cents").value;
    euroCent = ("000" + euroCent).slice(-2);
    let euroAmount = wholeEuro + "." + euroCent;

    console.log(euroAmount)
    let euroAmountInCent = "" + wholeEuro + euroCent
    console.log(euroAmount)
    let doubleUserEuroAmount = parseFloat(euroAmount);
    console.log(doubleUserEuroAmount);
    localStorage.setItem("userEuroAmount", euroAmount);

    localStorage.setItem("UserEuroAmountInCent", euroAmountInCent)
    return doubleUserEuroAmount;
}

// get user description input
function getUserInputDescription() {
    let userTransactionDescription = document.getElementById("transaction_description").value;
    localStorage.setItem("userDescription", userTransactionDescription);

}

function goToTransferPage() {
    window.location.href = "transferOverview.html";

}

async function checkIBANinDatabase() {
    const inputIBAN = getUserInput('recipient_IBAN');

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    await fetch("http://localhost:8888/checkIfIBANexists?iban="+inputIBAN, requestOptions)
        .then(response => response.text())
        .then(result =>{
            console.log("check iban: "+result)
            storeElementInLocalStorage("existsIbanInDatabase", result)
        })
        .catch(error => console.log('error', error));
}

async function checkSaldoSender() {
    const inputAmountEuro = getUserInput('transaction_amount_euro');
    const iban = localStorage.getItem("SelectedIban");

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    await fetch(`http://localhost:8888/checkIfSufficientFunds?fundsInEuro=${inputAmountEuro}&inputAmountEuro=$&iban=${iban}`, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log("api call check saldo: " + result)
            storeElementInLocalStorage("checkIfSufficientFunds", result)
        })
        .catch(error => console.log('error', error));
}

async function checkAllInformation(){
    // check if field requirements are met
    console.log("calling 1 "+new Date().getTime())
    await checkIBANinDatabase();
    console.log("calling 2 "+new Date().getTime())
    await checkSaldoSender();
    console.log("finished 3 "+new Date().getTime())

    let booleanIban = retrieveElementFromLocalStorage("existsIbanInDatabase")==="true"
    let booleanFunds = retrieveElementFromLocalStorage("checkIfSufficientFunds")==="true"

    console.log("iban: " + booleanIban)
    console.log("funds: " + booleanFunds)

    let checkReqs2 = booleanFunds && booleanIban

    console.log("combined boolean " + checkReqs2)

    // reset input fields
    resetHighlightInputField("recipient_IBAN");
    resetHighlightInputField("transaction_amount_euro");

    if (checkReqs2){

        const receiverIban = document.getElementById("recipient_IBAN");

        console.log("je zit nu in if statement ");
        goToTransferPage();
        getALLUserInput();
        window.location.href = "transferOverview.html";

    } else if (retrieveElementFromLocalStorage("existsIbanInDatabase")==="false"){
        // if code reqs not met, give error
        highlightInputField("recipient_IBAN");
        console.log("je zit nu in else if statement iban ");
    } else if(retrieveElementFromLocalStorage("checkIfSufficientFunds")==="false"){
        // if account reqs not met, give error
        highlightInputField("transaction_amount_euro");
        console.log("je zit nu in else if statement euro amount  ");
    } else {
        console.log("we snappen er niks meer van ");
    }


}
