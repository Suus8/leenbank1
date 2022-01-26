window.onload = function () {
    loadSelectedAccountInfo("iban", "name", "balance")
    loadTransactions()
}

const transactionsCurrentAccountEndpoint = `http://localhost:8888/transactionForIban?iban=${localStorage.getItem("SelectedIban")}`
const transactionTableBody = document.querySelector("#transaction-dto-table-body")
const accountInfoForIbanEndpoint = `http://localhost:8888/getAccountByIBAN?iban=${localStorage.getItem("SelectedIban")}`


async function loadSelectedAccountInfo(ibanId, nameId, balanceId) {
    let data = fetchData(accountInfoForIbanEndpoint,'GET')
        .then(data => {
            document.getElementById(ibanId).innerHTML = data["accountIBAN"]
            document.getElementById(nameId).innerHTML = data["clientName"]
            document.getElementById(balanceId).innerHTML = convertAmountInCentsToReadableString(data["accountBalanceInCents"])
        })
        .catch(error => console.log('error', error));
}

async function loadTransactions() {
    let data = await fetchData(transactionsCurrentAccountEndpoint,'GET')

    data.forEach(obj => {
        const tr = document.createElement("tr")

        const accountHolder = document.createElement("td")
        accountHolder.innerText = obj["offsetAccountHolder"]

        const offsetIban = document.createElement("td")
        offsetIban.innerText = obj["offsetAccountIban"]

        const description = document.createElement("td")
        description.innerText = obj["description"]

        const date = document.createElement("td")
        const rawDate = obj["date"]
        date.innerText = convertRawDateToDDMMYYYY(rawDate)

        const amount = document.createElement("td")
        const rawAmount = obj["amountInCents"]
        const receivedBoolean = obj["received"]
        amount.innerText = convertRawAmount(rawAmount, receivedBoolean)
        if (receivedBoolean === true) amount.style.color = "green"
        else amount.style.color = "red"

        tr.appendChild(accountHolder)
        tr.appendChild(offsetIban)
        tr.appendChild(description)
        tr.appendChild(date)
        tr.appendChild(amount)

        transactionTableBody.appendChild(tr)
    })

    function convertRawDateToDDMMYYYY(rawDate) {
        let tokens = rawDate.split("-")
        return tokens[2] + "-" + tokens[1] + "-" + tokens[0]
    }

    function convertRawAmount(rawAmount, receivedBoolean) {
        let amount
        if (receivedBoolean === true) amount = "+ "
        else amount = "- "
        return amount + convertAmountInCentsToReadableString(rawAmount)
    }
}
