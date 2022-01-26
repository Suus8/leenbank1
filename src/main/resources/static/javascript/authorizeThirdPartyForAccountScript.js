//region Global values
const codeId = "code",
    usernameId = "username",
    accountNumberId = "accountNumber",
    usernameDto = "username",
    ibanDto = "iban",
    codeDto = "code",
    dto = "clientAuthorizationDTO",
    infoAfterSubmit = "infoAfterSubmit",
    completeFormButton = "completeFormButton",
    selectedIban = "SelectedIban";
//endregion

//region Functions onload
function setIBAN() {
    // Get IBAN from page
    // let iban = retrieveElementFromLocalStorage('SelectedIban'); This only works when an account is selected. From
    // dashboard only clientId & user are known
    let iban = retrieveElementFromLocalStorage(selectedIban);
    // Create dto + save iban
    const clientAuthorizationDTO = {
        username: "",
        iban: iban,
        code: 12345
    };
    storeObjectInLocalStorage(dto, clientAuthorizationDTO);
}

function setUsername(){
    // Get username from page
    let username = retrieveObjectFromLocalStorage('user').username;
    // Create dto + save username
    const clientAuthorizationDTO = {
        username: username,
        iban: 1234567890,
        code: 12345
    };
    storeObjectInLocalStorage(dto, clientAuthorizationDTO);
}
//endregion

//region Functions from html page
function updateUsernameRequirementsOnInput(usernameId) {
    checkRequirement('userReq3Chars', checkMinimalAmountOfChars(usernameId, 3));
    checkRequirement('userReqOnlyNumbersAndLetters', checkOnlyLettersAndNumbers(usernameId));
}

function getAccountHolderDetails() {
    // get username + save locally
    updateDto(usernameId,usernameDto);

    // check if username exists + show full name through API call
    checkIfAccountHolderExists(getUserInput(usernameId));
}

function checkCodeRequirements() {
    return checkRequirement('codeReq5Chars', checkExactAmountOfChars(codeId, 5));
}

function checkAccountRequirements(){
    return checkRequirement('accountReq10Chars', checkExactAmountOfChars(accountNumberId,10));
}

function sendAuthorization() {
    // check if code requirements are met
    let checkReqs = checkCodeRequirements();

    // reset input field
    resetHighlightInputField(codeId);

    if (checkReqs) {
        // save code locally
        updateDto(codeId,codeDto);

        // save in DB
        saveAuthorizationInDB();

        // change reset button to finished
        setInnerHTMLOfElement(completeFormButton, "Klaar");
    } else {
        // if not saved, give error
        highlightInputField(codeId)
    }
}

function acceptAuthorization() {
    // check if field requirements are met
    let checkReqs = checkCodeRequirements() && checkAccountRequirements();

    // reset input fields
    resetHighlightInputField(codeId);
    resetHighlightInputField(accountNumberId);

    if (checkReqs){
        // save dto locally
        updateDto(accountNumberId,ibanDto)
        updateDto(codeId,codeDto);

        // verify input with DB
        verifyAuthorizationInDB();

        // change reset button to finished
        setInnerHTMLOfElement(completeFormButton, "Klaar");

    } else if (!checkCodeRequirements()){
        // if code reqs not met, give error
        highlightInputField(codeId);
    } else if(!checkAccountRequirements()){
        // if account reqs not met, give error
        highlightInputField(accountNumberId);
    }
}

function clearLocalStorage(){
    clearItemFromLocalStorage(selectedIban);
    clearItemFromLocalStorage(dto);
}

//endregion

//region Function using API/endpoints
function checkIfAccountHolderExists(username) {
    let requestOptions = {
            method: 'GET',
            redirect: 'follow'
        },
        url = "http://localhost:8888/getClientNameByUsername?username=" + username;

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            let clientName = "clientName";
            let username = "username";

            // Message field is shown
            showElement(clientName)

            if (result !== "unknown user") {
                // if user exists, show details in message field + save locally + lock field from changing input
                setInnerHTMLOfElement(clientName, result);
                lockInputField(username);
                resetHighlightInputField(username);
            } else {
                // if user doesn't exist, highlight field
                highlightInputField(username);
            }
        })
        .catch(error => console.log('error', error));
}

function saveAuthorizationInDB() {
    //TODO: create error handling when
    // a) post is done with non existing account
    // b) post is done 2nd time (combi iban-user should be unique and can only be done once unless timestand is expired)
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw = retrieveElementFromLocalStorage(dto);

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8888/authorizeThirdParty", requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result)
            // instruct client to share code + authorize person to log in when successfully saved
            showElement(infoAfterSubmit);
            setInnerHTMLOfElement(infoAfterSubmit,result);
        })
        .catch(error => console.log('error', error));
}

function verifyAuthorizationInDB() {
    console.log("verifyAuthorizationInDB activated")
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = retrieveElementFromLocalStorage(dto);

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8888/acceptAuthorizationForAccount", requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result)
            // give information about where authorized account is to be found
            showElement(infoAfterSubmit,result);
            setInnerHTMLOfElement(infoAfterSubmit,result);
        })
        .catch(error => console.log('error', error));
}
//endregion

//region Helper Methods
function updateDto(elementIdOfInput, nameOfAttribute) {
    // retrieve dto
    const object = retrieveObjectFromLocalStorage(dto);
    // verify object to be updated
    if (nameOfAttribute === usernameDto) {
        object.username = getUserInput(elementIdOfInput);
    } else if (nameOfAttribute === codeDto) {
        object.code = getUserInput(elementIdOfInput);
    } else if(nameOfAttribute === ibanDto){
        object.iban = getUserInput(elementIdOfInput);
    }
    // store dto
    storeObjectInLocalStorage(dto, object);
}
//endregion