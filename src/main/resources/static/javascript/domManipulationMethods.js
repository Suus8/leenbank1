//region Functions regarding showing/hiding elements
function showElement(elementId) {
    document.getElementById(elementId).style.display = "block";
}

function hideElement(elementId) {
    document.getElementById(elementId).style.display = "none";
}
//endregion

//region Functions for getting, selecting & checking user input
// get input from selected radiobutton
function getRadioButtonChoice() {
    return document.querySelector('input[name="type"]:checked').value;
}

// get input from text/number field
function getUserInput(id) {
    return document.getElementById(id).value;
}

// selected a certain text/number field (this means the cursor is put inside this field)
function selectElementById(inputId){
    document.getElementById(inputId).focus()
}

// return true if input is correct
function checkIfInputFieldIsHighlighted(inputId) {
    return document.getElementById(inputId).style.border !== "thick solid red";
}

// return true if field is filled in
function checkIfFieldIsFilledIn(fieldId){
    return getUserInput(fieldId) !== "";
}
//endregion

//region Functions about (un)locking (input) fields
function lockInputField(inputId){
    document.getElementById(inputId).setAttribute("disabled","true");
}

function unlockInputField(inputId){
    document.getElementById(inputId).removeAttribute("disabled");
}
//endregion

//region Functions regarding setting innerHTML
function setInnerHTMLOfElement(labelId, interHTMLText) {
    document.getElementById(labelId).innerHTML = interHTMLText;
}
//endregion

//region Functions regarding changing appearance/lay out
function changeColorsToBlue(buttonId) {
    document.getElementById(buttonId).classList.add("cmc-background-blue")
}

function resetColors(buttonId) {
    document.getElementById(buttonId).classList.remove("cmc-background-blue")
    document.getElementById(buttonId).classList.add("cmc-background-standard")
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

//region Functions for navigating between pages
function goToPage(pageFile) {
    window.location.href = pageFile
}

function goToPageWithSavedItems(pageFile,key,objectWithItems) {
    window.location.href = pageFile
    storeObjectInLocalStorage(key,objectWithItems)
}

function openPageInNewWindow(url){
    window.open(url)
}
//endregion
