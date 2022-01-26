// on loading page
document.onreadystatechange = function (e) {}
window.onload = function () {
    fillPage()

}

function fillTransferAmount() {
    let transferAmountString = localStorage.getItem("userEuroAmount")
    document.getElementById('amountTransferred').innerHTML = stringToCurrency(transferAmountString)
}

function stringToCurrency(amountAsString) {
    return "€ " + parseFloat(amountAsString).toLocaleString(undefined, {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    })
}

function fillPage() {
    getAccountByIbanRetrieveNameAndIban("userReceiverIBAN","receiverIBAN","receiverFullName", "receiverAvatar");
    getAccountByIbanRetrieveNameAndIban("SelectedIban","senderIBAN","senderFullName", "senderAvatar")
    fillTransferAmount();
}

// stores the new balance on both accounts and saves new transaction item in the database
function storeInfo() {
    saveTransaction()
}

function saveTransaction(){

    const transactionAmount = localStorage.getItem("UserEuroAmountInCent");
    const senderIban = localStorage.getItem("SelectedIban")
    const receiverIban = localStorage.getItem("userReceiverIBAN")
    const userDescription = localStorage.getItem("userDescription")

    const url = `http://localhost:8888/saveNewTransaction?transactionAmount=${transactionAmount}&ibanSender=${senderIban}&ibanReceiver=${receiverIban}&description=${userDescription}`

    let result = fetchData(url,'POST')
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

}




