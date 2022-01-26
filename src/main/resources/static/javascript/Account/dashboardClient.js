const authContainer = document.querySelector("#accountAuthorized")
const ownContainer = document.querySelector("#accountOwned")


document.onreadystatechange = function (e) {
    let user = JSON.parse(localStorage.getItem(`user`))
    let clientId = user["id"]
    let clientFullName = user["name"]
    // document.getElementById("userFullName").innerText = getName["clientName"]
    localStorage.setItem("clientId", clientId)
    if (document.readyState === 'complete') {
        window.onload = function () {
            getAllAccounts()
            setInnerHTMLOfElement("active-client-name-breadcrumb", clientFullName)
        }
    }
};

function getAllAccounts() {
    const authContainer = document.querySelector("#accountAuthorized")
    const ownContainer = document.querySelector("#accountOwned")
    authURL = "getAccountWhereUserIsAuthorized";
    ownedURL = "getAccountWhereUserIsOwner";
    getAccounts(authURL, authContainer);
    getAccounts(ownedURL, ownContainer);
}

async function getAccounts(url, container) {
    console.log(container)
    let authorizedAccountData = null

    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    const id = localStorage.getItem("clientId")

    fetch(`http://localhost:8888/${url}?id=${id}`, requestOptions)
        .then(response => response.json())
        .then(result => {
            console.log("fetch => ", result)
        })
        .catch(error => console.log('error', error));


    const response = await fetch(`http://localhost:8888/${url}?id=${id}`, requestOptions)

    authorizedAccountData = await response.json();

    console.table(authorizedAccountData)

    authorizedAccountData.forEach((obj) => {

        const div = document.createElement("div")
        div.classList.add("card", "card-primary", "card-outline", "card-body", "col-md-4")

        const h5 = document.createElement("h5")
        let iban = obj["accountIBAN"]
        h5.innerText = iban
        div.appendChild(h5)

        const p = document.createElement("p")

        let balanceValueInCents = obj["accountBalanceInCents"].toString()
        if (balanceValueInCents === "0") {
            p.innerText = "€ 0"
        } else {
            p.innerText = "€ " + balanceValueInCents.slice(0, -2) + "," + balanceValueInCents.slice(-2)
        }
        div.appendChild(p)

        const p_account_Owner = document.createElement("p")

        p_account_Owner.innerText = obj["clientName"]
        div.appendChild(p_account_Owner)

        const p_account_type = document.createElement("p")

        p_account_type.innerText = obj["accountType"]
        div.appendChild(p_account_type)

        const a1 = createHyperlink(iban, `/html/bankAccount/singleBankAccountPage.html`, 'naar je account')
        div.appendChild(a1)

        if (p_account_Owner.innerText === retrieveObjectFromLocalStorage('user').name) {
            const a2 = createHyperlink(iban, `/html/bankAccount/addAuthorizedClientToAccount.html`, 'machtig iemand')
            div.appendChild(a2)
        }
        container.appendChild(div)
    })
}

function createHyperlink(iban, link, text) {
    const a = document.createElement("a")

    a.setAttribute("href", link)

    a.onclick = function () {
        localStorage.setItem("SelectedIban", iban)
    }
    a.innerText = text;

    return a;
}




