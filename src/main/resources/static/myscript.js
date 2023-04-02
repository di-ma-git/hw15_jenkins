let uniqCountries = [];
function loadCitizens() {
    const CURRENT_URL = window.location.host;
    console.log("pressed");
    fetch('http://' + CURRENT_URL + '/citizens/')
        .then(response => response.json())
        .then(json => drawTable(json));

}
function drawTable(listOfCitizens) {
    let countriesArray = [];

    listOfCitizens.forEach(function (citizen){

        var tbodyElement = document.getElementById("citizenBody");

        var trElement = document.createElement("tr");

        var tdIdElement = document.createElement("td");
        tdIdElement.innerHTML = citizen['id'];

        var tdIdElementFirstName = document.createElement("td");
        tdIdElementFirstName.innerHTML = citizen['firstname'];

        var address = document.createElement("td");
        address.innerHTML = citizen['address'];

        var country = document.createElement("td");
        country.innerHTML = citizen['country'];
        countriesArray.push(citizen['country']);

        var birthDate = document.createElement("td");
        birthDate.innerHTML = citizen['birthDate'];

        trElement.append(tdIdElement);
        trElement.append(birthDate);
        trElement.append(address);
        trElement.append(country);
        trElement.append(tdIdElementFirstName);

        tbodyElement.append(trElement);

    })
    uniqCountries = Array.from(new Set(countriesArray));
}
function fillSelectWithCountries(uniqCountries) {
    console.log("pressed2");
    const selectElement = document.getElementById("selectCountry");
    uniqCountries.forEach(function (country) {
        var optionElement = document.createElement("option");
        optionElement.value = country
        optionElement.innerHTML = country
        selectElement.append(optionElement)
    })

}
function loadCitizensByCountry(country) {
    const CURRENT_URL = window.location.host;
    var tbodyElement = document.getElementById("citizenBody");
    tbodyElement.innerHTML = ''
    fetch('http://' + CURRENT_URL + '/citizens?country=' + country.toString())
        .then(response => response.json())
        .then(json => drawTable(json));

}