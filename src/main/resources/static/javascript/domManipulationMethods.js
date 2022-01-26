//region Functions regarding showing/hiding elements
function showElement(elementId) {
    document.getElementById(elementId).style.display = "block";
}

function hideElement(elementId) {
    document.getElementById(elementId).style.display = "none";
}
//endregion

//region Functions for getting user input
function getRadioButtonChoice() {
    return document.querySelector('input[name="type"]:checked').value;
}

function getUserInput(id) {
    return document.getElementById(id).value;
}
//endregion

//region Functions about (un)locking (input) fields
function lockInputField(inputId){
    document.getElementById(inputId).setAttribute("disabled","true");
}
//endregion

//region Functions regarding setting innerHTML
function setInnerHTMLOfElement(labelId, interHTMLText) {
    document.getElementById(labelId).innerHTML = interHTMLText;
}
//endregion

//region Functions regarding changing appearance/lay out
function changeColors(buttonId) {
    document.getElementById(buttonId).style.backgroundColor = "#4d4d50";
    document.getElementById(buttonId).style.color = "white";
}

function resetColors(buttonId) {
    document.getElementById(buttonId).style.backgroundColor = "lightgrey";
    document.getElementById(buttonId).style.color = "black";
}

function highlightInputField(inputId) {
    document.getElementById(inputId).style.border = "thick solid red";
}

function resetHighlightInputField(inputId) {
    document.getElementById(inputId).style.border = "initial";
}

function changeTextColorOfElement(elementId, color) {
    document.getElementById(elementId).style.color = color;
}

function changeTextColorOfAmountInTable(tableId) {
    document.getElementById(tableId).style.color = "red";
    console.log("change textcolor accomplished")
}
//endregion

//region Functions regarding local storage
function storeElementInLocalStorage(key,value){
    localStorage.setItem(key,value);
}

function retrieveElementFromLocalStorage(key){
    return localStorage.getItem(key);
}

function storeObjectInLocalStorage(key,object){
    let value = JSON.stringify(object);
    localStorage.setItem(key,value);
}

function retrieveObjectFromLocalStorage(key){
    let value = localStorage.getItem(key);
    return JSON.parse(value);
}

function storeElementInSessionStorage(key,value){
    sessionStorage.setItem(key,value);
}

function retrieveElementFromSessionStorage(key){
    return sessionStorage.getItem(key);
}

function clearItemFromLocalStorage(key){
    localStorage.removeItem(key);
}
//endregion
