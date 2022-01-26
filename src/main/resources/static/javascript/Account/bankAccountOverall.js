// to get the current date
function setCurrentDate(elementID) {
    var d = new Date();
    var NoTimeDate = d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate();
    document.getElementById(elementID).innerHTML = NoTimeDate;
}

//retrieve balance, iban, name of account by from login User
async function getSelectedAccountInfo(ibanElement,nameElement,balanceElement) {

    console.log("start of loading")
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
    console.log("req set")
    const iban = localStorage.getItem("SelectedIban")

    console.log("iban gotten")
    fetch(`http://localhost:8888/getAccountByIBAN?iban=${iban}`, requestOptions)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            document.getElementById(ibanElement).innerHTML = data["accountIBAN"]
            document.getElementById(nameElement).innerHTML = data["clientName"]
            let balanceValueInCents = data["accountBalanceInCents"].toString()
            if (balanceValueInCents==="0") {
                document.getElementById(balanceElement).innerHTML = "€ 0"
            }
            else {
                document.getElementById(balanceElement).innerHTML = "€ " + balanceValueInCents.slice(0, -2) + "," + balanceValueInCents.slice(-2)
            }
        })

        .catch(error => console.log('error', error));
}


// get account , iban and name
async function getAccountByIbanRetrieveNameAndIban(IbanKey,ibanElement, nameElement, imageElement) {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    const iban = localStorage.getItem(IbanKey)

    fetch(`http://localhost:8888/getAccountByIBAN?iban=${iban}`, requestOptions)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            document.getElementById(ibanElement).innerHTML =data["accountIBAN"]
            let name =data["clientName"]
            document.getElementById(nameElement).innerHTML =name;
            let firstName
            let secondName
            if(name.includes(",")){
                let splitName= name.split(",")
               firstName = splitName[1]
               secondName = splitName[0]
            } else{
                let splitCompanyName= name.split(" ")
                firstName = splitCompanyName[0]
                secondName = splitCompanyName[1]

            }

            document.getElementById(imageElement).setAttribute("src",`https://eu.ui-avatars.com/api/?name=${firstName}+${secondName}&background=random`)
        })

        .catch(error => console.log('error', error));
}