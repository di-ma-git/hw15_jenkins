const url = window.location.host

function loadCitizens(url) {
    console.log("pressed")
    fetch(url)
        .then(response => response.json())
        .then(json => drawTable(json));



    // drawTable()
    // const xhr = new XMLHttpRequest();
    // const method = "GET";
    // const url = "http://localhost:8080/citizens/";

    xhr.open(method, url, true);
    xhr.send()
    xhr.onreadystatechange = (e) => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            const status = xhr.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                console.log(xhr.responseText);
                var listOfCitizens = JSON.parse(xhr.responseText);

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

                    var birthDate = document.createElement("td");
                    birthDate.innerHTML = citizen['birthDate'];

                    trElement.append(tdIdElement)
                    trElement.append(birthDate)
                    trElement.append(address)
                    trElement.append(country)
                    trElement.append(tdIdElementFirstName)

                    tbodyElement.append(trElement)

                })
            } else {
                console.log("didn't work! " + status)
            }
        }
    }
}

function drawTable(listOfCitizens) {
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

        var birthDate = document.createElement("td");
        birthDate.innerHTML = citizen['birthDate'];

        trElement.append(tdIdElement)
        trElement.append(birthDate)
        trElement.append(address)
        trElement.append(country)
        trElement.append(tdIdElementFirstName)

        tbodyElement.append(trElement)

    })
}