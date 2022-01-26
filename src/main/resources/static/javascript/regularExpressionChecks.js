//region Checks on input field (regEx's)
function checkMinimalAmountOfChars(stringId, amountOfChars) {
    return getUserInput(stringId).length >= amountOfChars;
}

function checkExactAmountOfChars(stringId, amountOfChars) {
    return getUserInput(stringId).length === amountOfChars;
}

function check1Number(stringId) {
    return getUserInput(stringId).match(/[0-9]/);
}

function check1Capital(stringId) {
    return getUserInput(stringId).match(/[A-Z]/)
}

function check1LowerCase(stringId) {
    return getUserInput(stringId).match(/[a-z]/)
}

function checkOnlyLettersAndNumbers(stringId) {
    return getUserInput(stringId).match(/^[a-zA-Z0-9_-]+$/); // underscore & dash also allowed!
}

function check1Special(stringId) {
    return getUserInput(stringId).match(/[!-\/:-@[-`{-~]/);
}

function checkIfPasswordIsSameAsRepeatPassword(passwordId, repeatPasswordId) {
    return (getUserInput(passwordId) === getUserInput(repeatPasswordId));
}
//endregion

// region Functions regarding

//endregion

//region Functions regarding

//endregion

//region Functions regarding

//endregion
