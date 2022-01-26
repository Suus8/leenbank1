//region Global Variables
const typeOfAccount = "typeOfAccount",
    sector = "sector",
    username = "username",
    password = "password",
    clientId = "clientId",
    firstName = "firstName",
    lastName = "lastName",
    dateOfBirth = "dateOfBirth",
    companyName = "companyName",
    email = "email",
    telephoneNumber = "telephoneNumber",
    street = "street",
    houseNumber = "houseNumber",
    houseNumberAddition = "houseNumberAddition",
    zipCode = "zipCode",
    city = "city";

//endregion

//region Onload/Onclose function
function loadCorrectDetailsOpenAccount() {
    // set today's date + prevent from input after today
    let today = new Date().toISOString().split('T')[0];
    document.getElementsByName("setTodaysDate")[0].setAttribute('max', today);

    // show correct tabs
    showCorrectTabsForAccountType();
}

function clearLocalStorageOpenAccount(){
    localStorage.clear();
}
//endregion

//region Functions regarding Tabs
function showCorrectTabsForAccountType() {
    // show only company or consumer tabs & buttons
    if (checkAccountTypeConsumer()) {
        hideElement("companyDetailsTab");
        hideElement("previousButtonSmallBusinessAccount");
        // showElement('nextButtonConsumerAccount')
    } else {
        hideElement("personalDetailsTab");
        hideElement("previousButtonConsumerAccount");
        // showElement('nextButtonSmallBusinessAccount')
    }

    // show & highlight account tab details
    changeColors("accountDetailsTab");
    showTabDetails("accountDetails");
}

function showTabDetails(tabContentToShow) {
    hideAllTabContent();

    // Show content of the selected tab
    document.getElementById(tabContentToShow).style.display = "block";
}

function showNextTab(currentTabId, nextTabId, tabContentToShow) {
    showTabDetails(tabContentToShow);

    // Highlight next tab & reset current
    changeColors(nextTabId);
    resetColors(currentTabId);
}

function showPreviousTab(currentTabId, previousTabId, tabContentToShow) {
    showTabDetails(tabContentToShow);

    // Highlight previous tab & reset current
    changeColors(previousTabId);
    resetColors(currentTabId);
}

function hideAllTabContent() {
    let i, tabContent = document.getElementsByClassName("tabContent");
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }
}

//endregion

//region Functions regarding saves & checks
function saveAccountType() {
    let chosenTypeOfAccount, selectedSector;

    // get chosen type of account & save to local device
    chosenTypeOfAccount = getRadioButtonChoice();
    localStorage.setItem(typeOfAccount, chosenTypeOfAccount)
    console.log("radiobutton: "+chosenTypeOfAccount)

    // if account type is smallBusiness, also save sector to local device
    if (getRadioButtonChoice() === "Zakelijk") {
        selectedSector = getUserInput('sectorDropdownOptions');
        localStorage.setItem(sector, selectedSector);
    } else {
        // clear sector should there have been saves before
        localStorage.setItem(sector, null);
    }
    console.log("sector: "+selectedSector)

}

function checkIdBeforeNextTab() {
    if (checkIfInputFieldIsHighlighted('ssn') || checkIfInputFieldIsHighlighted('coC') || updateRepeatPasswordCheckOnInput('password','repeatPassword')) {
        showNextTab('personalDetailsTab', 'contactDetailsTab', 'contactDetails');
    }
}

function saveAllDetails() {
    if (checkAccountTypeConsumer()) {
        savePersonalDetails();
    } else {
        saveCompanyDetails();
    }
    saveContactDetails();
}

function savePersonalDetails() {
    localStorage.setItem(clientId, getUserInput("ssn"));
    localStorage.setItem(firstName, getUserInput(firstName));
    localStorage.setItem(lastName, getUserInput(lastName));
    localStorage.setItem(dateOfBirth, getUserInput(dateOfBirth));
}

function saveCompanyDetails() {
    localStorage.setItem(clientId, getUserInput("coC"));
    localStorage.setItem(companyName, getUserInput(companyName));
}

function saveContactDetails() {
    localStorage.setItem(email, getUserInput(email));
    localStorage.setItem(telephoneNumber, getUserInput(telephoneNumber));
    localStorage.setItem(street, getUserInput(street));
    localStorage.setItem(houseNumber, getUserInput(houseNumber));
    localStorage.setItem(houseNumberAddition, getUserInput(houseNumberAddition));
    localStorage.setItem(zipCode, getUserInput(zipCode));
    localStorage.setItem(city, getUserInput(city));

}

function checkAccountTypeConsumer() {
    return localStorage.getItem(typeOfAccount) === "consumer";
}

function checkAccountDetails() {
    console.log("checkAccountDetails clicked");
    console.log("localStorage.getItem(username) = "+localStorage.getItem(username));
    console.log("getUserInput('username') = "+getUserInput('username'));
    console.log("password ="+getUserInput(password));
    console.log("repeatPassword ="+getUserInput('repeatPassword'));

    // boolean check if all filled details are correct
    let completeAndChecked = ((localStorage.getItem(username) === getUserInput('username'))
        && updatePasswordCheckOnInput('password')
        && updateRepeatPasswordCheckOnInput('password', 'repeatPassword'));
    console.log(completeAndChecked);

    // save password
    localStorage.setItem(password, getUserInput('password'));

    // if all details are filled in correctly, go to next tab
    if (completeAndChecked) {
        if (checkAccountTypeConsumer()) {
            showNextTab('accountDetailsTab', 'personalDetailsTab', 'personalDetails')
        } else {
            showNextTab('accountDetailsTab', 'companyDetailsTab', 'companyDetails')
        }
    }
}

function checkAllDetailsComplete() {
    // check if all obligatory fields are filled in
    // TODO

    // if not, jump to tab with missing details + highlight text field
    // TODO

    // if so save all to local device
    saveAllDetails();

    // if all details are filled in, show 'volgende' button
    showElement("saveButton");
}

//endregion

//region Realtime update checks
function updateUsernameCheckOnInput(usernameId) {
    let charsAmount, charsType;

    checkIfUsernameExists(getUserInput(usernameId));
    charsAmount = checkRequirement('userReq3Chars', checkMinimalAmountOfChars(usernameId, 3));
    charsType = checkRequirement('userReqOnlyNumbersAndLetters', checkOnlyLettersAndNumbers(usernameId));

    return (charsAmount && charsType);
}

function updatePasswordCheckOnInput(passwordId) {
    console.log("updatePasswordCheckOnInput activated")
    let charsAmount, number, capital, lowerCase, special;

    charsAmount = checkRequirement('pwReq8Chars', checkMinimalAmountOfChars(passwordId, 8));
    number = checkRequirement('pwReq1Number', check1Number(passwordId));
    capital = checkRequirement('pwReq1Capital', check1Capital(passwordId));
    lowerCase = checkRequirement('pwReq1LowerCase', check1LowerCase(passwordId));
    special = checkRequirement('pwReq1Special', check1Special(passwordId));

    return (charsAmount && number && capital && lowerCase && special);
}

function updateRepeatPasswordCheckOnInput(passwordId, repeatPasswordId) {
    console.log("updatePasswordCheckOnInput activated")

    return checkRequirement('repeatPwSame', checkIfPasswordIsSameAsRepeatPassword(passwordId, repeatPasswordId));

}

function updateSsnCheckOnInput(ssnId) {
    let charsAmount = checkRequirement('ssnReqChars', checkExactAmountOfChars(ssnId, 9));

    checkIfSsnExists(getUserInput(ssnId));

    return charsAmount;
}

function updateCoCCheckOnInput(coCId) {
    let charsAmount = checkRequirement('coCReqChars', checkExactAmountOfChars(coCId, 8));

    checkIfCoCExists(getUserInput(coCId));

    return charsAmount;
}

function checkRequirement(reqId, reqCheck) {
    if (reqCheck) {
        changeTextColorOfElement(reqId, "green");
        return true;
    } else {
        changeTextColorOfElement(reqId, "red");
        return false;
    }
}

//endregion

//region Functions for showing details
function showAllDetails() {
    showAccountDetails();

    // show consumer or smallBusiness details + hide the other
    if (checkAccountTypeConsumer()) {
        showConsumerDetails();
        hideElement("smallBusiness");
    } else {
        showSmallBusinessDetails();
        hideElement("consumer");
    }

    showContactDetails();
}

function showAccountDetails() {
    setInnerHTMLOfElement(typeOfAccount, localStorage.getItem(typeOfAccount));
    setInnerHTMLOfElement(username, localStorage.getItem(username));
    setInnerHTMLOfElement(password, localStorage.getItem(password));
}

function showConsumerDetails() {
    setInnerHTMLOfElement("ssn", localStorage.getItem(clientId));
    setInnerHTMLOfElement(firstName, localStorage.getItem(firstName));
    setInnerHTMLOfElement(lastName, localStorage.getItem(lastName));
    setInnerHTMLOfElement(dateOfBirth, localStorage.getItem(dateOfBirth));
}

function showSmallBusinessDetails() {
    setInnerHTMLOfElement("coC", localStorage.getItem(clientId));
    setInnerHTMLOfElement(companyName, localStorage.getItem(companyName));
    setInnerHTMLOfElement(sector, localStorage.getItem(sector));
}

function showContactDetails() {
    setInnerHTMLOfElement(email, localStorage.getItem(email));
    setInnerHTMLOfElement(telephoneNumber, localStorage.getItem(telephoneNumber));
    setInnerHTMLOfElement(street, localStorage.getItem(street));
    setInnerHTMLOfElement(houseNumber, localStorage.getItem(houseNumber));
    setInnerHTMLOfElement(houseNumberAddition, localStorage.getItem(houseNumberAddition));
    setInnerHTMLOfElement(zipCode, localStorage.getItem(zipCode));
    setInnerHTMLOfElement(city, localStorage.getItem(city));
}

//endregion

//region Functions regarding API
function createAccount() {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw;
    if (checkAccountTypeConsumer()) {
        raw = createJSONConsumerAccount();
    } else {
        raw = createJSONSmallBusinessAccount();
    }

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    if (checkAccountTypeConsumer()) {
        fetchConsumerAccount(requestOptions);
    } else {
        fetchSmallBusinessAccount(requestOptions);
    }

    localStorage.clear();
}

function createJSONConsumerAccount() {
    return JSON.stringify({
        "id": localStorage.getItem(clientId),
        "login": {
            "username": localStorage.getItem(username),
            "password": localStorage.getItem(password)
        },
        "clientDetails": {
            "email": localStorage.getItem(email),
            "telephoneNumber": localStorage.getItem(telephoneNumber),
            "street": localStorage.getItem(street),
            "houseNumber": localStorage.getItem(houseNumber),
            "houseNumberAddition": localStorage.getItem(houseNumberAddition),
            "zipCode": localStorage.getItem(zipCode),
            "city": localStorage.getItem(city)
        },
        "personDetails": {
            "fullName": {
                "firstName": localStorage.getItem(firstName),
                "lastName": localStorage.getItem(lastName)
            },
            "dateOfBirth": localStorage.getItem(dateOfBirth)
        }
    });
}

function createJSONSmallBusinessAccount() {
    return JSON.stringify({
        "id": localStorage.getItem(clientId),
        "login": {
            "username": localStorage.getItem(username),
            "password": localStorage.getItem(password)
        },
        "clientDetails": {
            "email": localStorage.getItem(email),
            "telephoneNumber": localStorage.getItem(telephoneNumber),
            "street": localStorage.getItem(street),
            "houseNumber": localStorage.getItem(houseNumber),
            "houseNumberAddition": localStorage.getItem(houseNumberAddition),
            "zipCode": localStorage.getItem(zipCode),
            "city": localStorage.getItem(city)
        },
        "companyDetails": {
            "name": localStorage.getItem(companyName),
            "sector": localStorage.getItem(sector)
        }
    });
}

function fetchConsumerAccount(requestOptions) {
    fetch("http://localhost:8888/createNewConsumerAccount", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

function fetchSmallBusinessAccount(requestOptions) {
    fetch("http://localhost:8888/createNewSmallBusinessAccount", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

function checkIfUsernameExists(usernameString) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/checkUsername?username=" + usernameString;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result);
            checkRequirement('userReqUnique', !(result === 'true'));
            changeInputFieldAfterCheckWithAPI(result, 'username');
            if(result === 'false'){
                localStorage.setItem(username, usernameString);
            }
        })
        .catch(error => console.log('error', error));
}

function checkIfSsnExists(ssnLong) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/checkSsn?ssn=" + ssnLong;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result);
            checkRequirement('ssnReqUnique', !(result === 'true'));
            changeInputFieldAfterCheckWithAPI(result, 'ssn');
            localStorage.setItem(clientId, ssnLong);
        })
        .catch(error => console.log('error', error));
}

function checkIfCoCExists(coCLong) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/checkCoC?coC=" + coCLong;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result);
            checkRequirement('coCReqUnique', !(result === 'true'));
            changeInputFieldAfterCheckWithAPI(result, 'coC');
            localStorage.setItem(clientId, coCLong);
        })
        .catch(error => console.log('error', error));
}

//endregion

//region Helper functions
function showDetails(currentParagraph) {
    document.getElementById(currentParagraph).style.display = "block";
}

function hideDetails(previousParagraph) {
    document.getElementById(previousParagraph).style.display = "none";
}

function changeInputFieldAfterCheckWithAPI(result, inputId) {
    if (result === "true") {
        highlightInputField(inputId);
    } else {
        resetHighlightInputField(inputId);
    }
}

function showCorrectNextButtonAfterPasswordCheck() {
    if (checkAccountTypeConsumer()) {
        showElement("nextButtonConsumerAccount");
    } else {
        showElement("nextButtonSmallBusinessAccount");
    }
}

// return true if input is correct
function checkIfInputFieldIsHighlighted(inputId) {
    document.getElementById(inputId).style.border !== "thick solid red";
}

//endregion

