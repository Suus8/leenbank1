//region Global Variables
// Keys local storage
const typeOfAccount = "typeOfAccount",
    sector = "sector",
    sectorAsEnum = "sectorAsEnum";
    username = "username",
    replyFromApi = "replyFromApi";
//endregion

//region Onload/Onclose function
function loadCorrectDetailsOpenAccount() {
    // set today's date + prevent from input after today
    let today = new Date().toISOString().split('T')[0];
    document.getElementsByName("setTodaysDate")[0].setAttribute('max', today);
}

function clearLocalStorageOpenAccount() {
    localStorage.clear();
}

//endregion

//region Functions regarding Tabs
function showCorrectTabsForAccountType() {
    // show only company or consumer tabs & buttons
    if (checkIfAccountTypeIs("Particulier")) {
        showElement("personalDetailsTab")
        hideElement("companyDetailsTab")
        showElement("previousButtonConsumerAccount")
        hideElement("previousButtonSmallBusinessAccount")
    } else if (checkIfAccountTypeIs("Zakelijk")) {
        showElement("companyDetailsTab")
        hideElement("personalDetailsTab");
        showElement("previousButtonSmallBusinessAccount");
        hideElement("previousButtonConsumerAccount");
    }

    // highlight account tab details
    changeColorsToBlue("accountDetailsTab");
}

function showTabContentOf(tabContentToShow) {
    hideAllTabContent();

    // Show content of the selected tab
    document.getElementById(tabContentToShow).style.display = "block";
}

function showNextTab(nextTabId, tabContentToShow) {
    showTabContentOf(tabContentToShow);

    // Highlight next tab
    changeColorsToBlue(nextTabId);
}

function showPreviousTab(currentTabId, tabContentToShow) {
    showTabContentOf(tabContentToShow);

    // Reset current lay out
    resetColors(currentTabId);
}

function hideAllTabContent() {
    let i, tabContent = document.getElementsByClassName("tabContent");
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }
}

//endregion

//region Functions regarding checks
function checkIfAccountTypeIs(accountType) {
    return localStorage.getItem(typeOfAccount) === accountType;
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

//region Functions regarding saves before next tab/step
function saveAccountTypeAndShowStep2() {
    // get chosen type of account & save to local device
    localStorage.setItem(typeOfAccount, getRadioButtonChoice())

    // if account type is smallBusiness, also save sector to local device
    if (getRadioButtonChoice() === "Zakelijk") {
        localStorage.setItem(sector, getUserInput('sectorDropdownOptions'))
        localStorage.setItem(sectorAsEnum, apiGetSectorAsEnum(getUserInput('sectorDropdownOptions')))
    } else {
        // clear sector should there have been saves before
        localStorage.setItem(sector, null)
        localStorage.setItem(sectorAsEnum, null)
    }

    // lock input
    lockInputField("consumerRadioButton")
    lockInputField("smallBusinessRadioButton")
    lockInputField("sectorDropdownOptions")

    // show step 2 + correct tabs
    showElement("fillInDetailsCard")
    showTabContentOf("accountDetails")
    showCorrectTabsForAccountType()
}

function checkLoginInDetailsAndShowNextTab(usernameInput, passwordInput, repeatPasswordInput) {
    // boolean check if all filled details are correct
    let completeAndChecked = ((localStorage.getItem(username) === getUserInput(usernameInput))
        && updateOnInputCheckPassword(passwordInput)
        && updateOnInputCheckRepeatPassword(passwordInput, repeatPasswordInput));

    // save password
    // localStorage.setItem(password, getUserInput('password')); //TODO

    // if all details are filled in correctly, lock input fields + go to next tab
    if (completeAndChecked) {
        // change appearance of input fields + prevent from changing input
        lockInputField(usernameInput)
        lockInputField(passwordInput)
        lockInputField(repeatPasswordInput)

        // check account type + show correct content
        if (checkIfAccountTypeIs("Particulier")) {
            showNextTab('personalDetailsTab', 'personalDetails')
        } else if (checkIfAccountTypeIs("Zakelijk")) {
            showNextTab('companyDetailsTab', 'companyDetails')
        }
    }
    //move cursor to field(s) that are not correctly filled in
    if (!(localStorage.getItem(username) === getUserInput(usernameInput))) {
        selectElementById(usernameInput)
    } else if (!updateOnInputCheckPassword(passwordInput)) {
        selectElementById(passwordInput)
    } else if (!updateOnInputCheckRepeatPassword(passwordInput, repeatPasswordInput)) {
        selectElementById(repeatPasswordInput)
    }
}

function checkIdAndShowContactDetailsTab(inputId, amountOfChars) {
    if (checkIfInputFieldIsHighlighted(inputId) && checkExactAmountOfChars(inputId, amountOfChars)) {
        lockInputField(inputId)
        showNextTab('contactDetailsTab', 'contactDetails')
    } else {
        // move cursor to id field if input is incorrect
        selectElementById(inputId)
    }
}

function checkContactDetailsAndShowStep3() {
    // check if all obligatory fields are filled in (only 1 or more of the 3 contact methods is needed)
    let emailFilledIn, phoneFilledIn, addressFilledIn;

    emailFilledIn = checkIfFieldIsFilledIn("emailInput")
    phoneFilledIn = checkIfFieldIsFilledIn("telephoneNumberInput")
    addressFilledIn = checkIfFieldIsFilledIn("streetInput") && checkIfFieldIsFilledIn("houseNumberInput") &&
        checkIfFieldIsFilledIn("zipCodeInput") && checkIfFieldIsFilledIn("cityInput")

    if (emailFilledIn || phoneFilledIn || addressFilledIn) {
        hideElement("errorMessage")

        // if all details are complete, save to checkDetailsCard + hide step 1&2 + show step 3
        saveAllDetailsToCheckDetailsCard()
        hideElement("typeOfAccountCard")
        hideElement("fillInDetailsCard")
        showElement("checkDetailsCard")
    } else {
        // if not, give message
        showElement("errorMessage")
    }
}

function saveAllDetailsToCheckDetailsCard() {
    // copy account type
    setInnerHTMLOfElement("typeOfAccount", localStorage.getItem(typeOfAccount));

    // copy login details
    setInnerHTMLOfElement("username", localStorage.getItem(username));
    setInnerHTMLOfElement("password", getUserInput("passwordInput"));

    // copy + show personal details (if needed)
    if (checkIfAccountTypeIs("Particulier")) {
        showElement("consumer")
        setInnerHTMLOfElement("ssn", getUserInput("ssnInput"));
        setInnerHTMLOfElement("firstName", getUserInput("firstNameInput"));
        setInnerHTMLOfElement("lastName", getUserInput("lastNameInput"));
        setInnerHTMLOfElement("dateOfBirth", getUserInput("dateOfBirthInput"));
    }

    // copy + show company details (if needed)
    if (checkIfAccountTypeIs("Zakelijk")) {
        showElement("smallBusiness")
        setInnerHTMLOfElement("coC", getUserInput("coCInput"));
        setInnerHTMLOfElement("companyName", getUserInput("companyNameInput"));
        setInnerHTMLOfElement("sector", localStorage.getItem(sector));
    }

    // copy contact details
    setInnerHTMLOfElement("email", getUserInput("emailInput"));
    setInnerHTMLOfElement("telephoneNumber", getUserInput("telephoneNumberInput"));
    setInnerHTMLOfElement("street", getUserInput("streetInput"));
    setInnerHTMLOfElement("houseNumber", getUserInput("houseNumberInput"));
    setInnerHTMLOfElement("houseNumberAddition", getUserInput("houseNumberAdditionInput"));
    setInnerHTMLOfElement("zipCode", getUserInput("zipCodeInput"));
    setInnerHTMLOfElement("city", getUserInput("cityInput"));
}

function changeFilledInDetails() {
    // show step 1 again + hide step 3
    showElement("typeOfAccountCard")
    hideElement("checkDetailsCard")

    // unlock locked field step 1 & 2
    unlockInputField("consumerRadioButton")
    unlockInputField("smallBusinessRadioButton")
    unlockInputField("sectorDropdownOptions")

    unlockInputField("usernameInput")
    unlockInputField("passwordInput")
    unlockInputField("repeatPasswordInput")

    unlockInputField("ssnInput")
    unlockInputField("coCInput")

    // change colors tabs step 2
    resetColors("personalDetailsTab")
    resetColors("companyDetailsTab")
    resetColors("contactDetailsTab")
}

function saveAccount() {
    // save account in DB
    apiCreateAccount()

    // retrieve reply & username from local storage + pass to next page
    let userInfo = {
        user: retrieveElementFromLocalStorage(username),
        text: retrieveElementFromLocalStorage(replyFromApi)
    }

    // clear local storage
    localStorage.clear()

    // go to result page + set welcome text
    goToPageWithSavedItems("openAccountSucceeded.html", "userInfo", userInfo)
    setInnerHTMLOfElement("welcomeText", userInfo.text)
}

//endregion

//region Realtime update checks
function updateOnInputCheckUsername(usernameId) {
    let charsAmount, charsType;

    apiCheckIfUsernameExists(getUserInput(usernameId));
    charsAmount = checkRequirement('userReq3Chars', checkMinimalAmountOfChars(usernameId, 3));
    charsType = checkRequirement('userReqOnlyNumbersAndLetters', checkOnlyLettersAndNumbers(usernameId));

    return (charsAmount && charsType);
}

function updateOnInputCheckPassword(passwordId) {
    let charsAmount, number, capital, lowerCase, special;

    charsAmount = checkRequirement('pwReq8Chars', checkMinimalAmountOfChars(passwordId, 8));
    number = checkRequirement('pwReq1Number', check1Number(passwordId));
    capital = checkRequirement('pwReq1Capital', check1Capital(passwordId));
    lowerCase = checkRequirement('pwReq1LowerCase', check1LowerCase(passwordId));
    special = checkRequirement('pwReq1Special', check1Special(passwordId));

    return (charsAmount && number && capital && lowerCase && special);
}

function updateOnInputCheckRepeatPassword(passwordId, repeatPasswordId) {
    return checkRequirement('repeatPwSame', checkIfPasswordIsSameAsRepeatPassword(passwordId, repeatPasswordId));

}

function updateOnInputCheckSsn(ssnId) {
    let charsAmount = checkRequirement('ssnReqChars', checkExactAmountOfChars(ssnId, 9));

    apiCheckIfSsnExists(getUserInput(ssnId));

    return charsAmount;
}

function updateOnInputCheckCoC(coCId) {
    let charsAmount = checkRequirement('coCReqChars', checkExactAmountOfChars(coCId, 8));

    apiCheckIfCoCExists(getUserInput(coCId));

    return charsAmount;
}

//endregion

//region Functions regarding API
function apiGetSectorAsEnum(sectorAsString){

}

function apiCheckIfUsernameExists(usernameString) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/checkUsername?username=" + usernameString;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            checkRequirement('userReqUnique', !(result === 'true'));
            changeInputFieldAfterCheckWithAPI(result, 'usernameInput');
            if (result === 'false') {
                localStorage.setItem(username, usernameString);
            }
        })
        .catch(error => console.log('error', error));
}

function apiCheckIfSsnExists(ssnLong) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/checkSsn?ssn=" + ssnLong;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            checkRequirement('ssnReqUnique', !(result === 'true'));
            changeInputFieldAfterCheckWithAPI(result, 'ssnInput');
        })
        .catch(error => console.log('error', error));
}

function apiCheckIfCoCExists(coCLong) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/checkCoC?coC=" + coCLong;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            checkRequirement('coCReqUnique', !(result === 'true'));
            changeInputFieldAfterCheckWithAPI(result, 'coC');
        })
        .catch(error => console.log('error', error));
}

function apiCreateAccount() {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw;
    if (checkIfAccountTypeIs("Particulier")) {
        raw = createJSONConsumerAccount();
    } else if (checkIfAccountTypeIs("Zakelijk")) {
        raw = createJSONSmallBusinessAccount();
    }

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    if (checkIfAccountTypeIs("Particulier")) {
        fetchConsumerAccount(requestOptions);
    } else if (checkIfAccountTypeIs("Zakelijk")) {
        fetchSmallBusinessAccount(requestOptions);
    }
}

function createJSONConsumerAccount() {
    return JSON.stringify({
        "id": getUserInput("ssnInput"),
        "login": {
            "username": localStorage.getItem(username),
            "password": getUserInput("passwordInput")
        },
        "clientDetails": {
            "email": getUserInput("emailInput"),
            "telephoneNumber": getUserInput("telephoneNumberInput"),
            "street": getUserInput("streetInput"),
            "houseNumber": getUserInput("houseNumberInput"),
            "houseNumberAddition": getUserInput("houseNumberAdditionInput"),
            "zipCode": getUserInput("zipCodeInput"),
            "city": getUserInput("cityInput")
        },
        "personDetails": {
            "fullName": {
                "firstName": getUserInput("firstNameInput"),
                "lastName": getUserInput("lastNameInput")
            },
            "dateOfBirth": getUserInput("dateOfBirthInput")
        }
    });
}

function createJSONSmallBusinessAccount() {
    return JSON.stringify({
        "id": getUserInput("coCInput"),
        "login": {
            "username": localStorage.getItem(username),
            "password": getUserInput("passwordInput")
        },
        "clientDetails": {
            "email": getUserInput("emailInput"),
            "telephoneNumber": getUserInput("telephoneNumberInput"),
            "street": getUserInput("streetInput"),
            "houseNumber": getUserInput("houseNumberInput"),
            "houseNumberAddition": getUserInput("houseNumberAdditionInput"),
            "zipCode": getUserInput("zipCodeInput"),
            "city": getUserInput("cityInput")
        },
        "companyDetails": {
            "name": getUserInput("companyNameInput"),
            "sector": localStorage.getItem(sectorAsEnum)
        }
    });
}

async function fetchConsumerAccount(requestOptions) {
    await fetch("http://localhost:8888/createNewConsumerAccount", requestOptions)
        .then(response => response.text())
        .then(result => {
            storeElementInLocalStorage(replyFromApi, result)
        })
        .catch(error => {
            console.log('error', error)
            storeElementInLocalStorage(replyFromApi, "Het is helaas niet gelukt om uw account aan te maken")
        });
}

async function fetchSmallBusinessAccount(requestOptions) {
    await fetch("http://localhost:8888/createNewSmallBusinessAccount", requestOptions)
        .then(response => response.text())
        .then(result => {
            storeElementInLocalStorage(replyFromApi, result)
        })
        .catch(error => {
            console.log('error', error)
            storeElementInLocalStorage(replyFromApi, "Het is helaas niet gelukt om uw account aan te maken")
        });
}

//endregion

//region Log In function
function logInWithUserName() {
    //TODO ask Abuzer why this doesn't work
    document.getElementById('username').value = retrieveObjectFromLocalStorage('userInfo').user
}

//endregion

//region Helper functions
function changeInputFieldAfterCheckWithAPI(result, inputId) {
    if (result === "true") {
        highlightInputField(inputId)
    } else {
        resetHighlightInputField(inputId)
    }
}

function changeInputFieldAppearance(inputId) {
    resetHighlightInputField(inputId)
    lockInputField(inputId)
}

//endregion

