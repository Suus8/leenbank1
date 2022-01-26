//region local storage maintenance
function clearLocalStorage(){
    localStorage.clear();
}

function resetLocalStorageToDashboard(){
    const userInfo = localStorage.getItem("user");
    const id = localStorage.getItem("clientId")
    localStorage.clear()
    localStorage.setItem("user", userInfo)
    localStorage.setItem("clientId", id)
}
//endregion

//region get/put/post functions
async function fetchData(URL,method) {
    let requestOptions = {
        method: method,
        redirect: 'follow'
    };
    const response = await fetch(URL, requestOptions)
    return await response.json()
}
//endregion


//region presenting money functions
function convertAmountInCentsToReadableString(amountInCents) {
    let paddedAmount = String(amountInCents).padStart(3,'0')
    return "€ " + paddedAmount.slice(0, -2) + "," + paddedAmount.slice(-2)
}
//endregion