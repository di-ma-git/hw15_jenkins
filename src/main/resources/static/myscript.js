window.onload = function() {
    showBooks();
};
const CURRENT_URL = window.location.host
function showBooks() {

    fetch('http://' + CURRENT_URL + '/books')
        .then(response => response.json())
        .then(listOfBooks => fillBooksTable(listOfBooks));
}


function fillBooksTable(listOfBooks) {
    listOfBooks.forEach(function (book) {
        var tbodyElement = $('#booksTable');
        var newRow = $('<tr>');
        newRow.attr('id', book.authorId);
        newRow.attr('onclick', 'showAuthor(this.id)');
        var newCellId = $('<td>').text(book.id);
        newRow.append(newCellId);
        var newCellName = $('<td>').text(book.bookName);
        newRow.append(newCellName);
        var newCellAuthor = $('<td>').text(book.author);
        newRow.append(newCellAuthor);
        var newCellDescription = $('<td>').text(book.description);
        newRow.append(newCellDescription);
        var newCellDate = $('<td>').text(book.publishDate);
        newRow.append(newCellDate);

        tbodyElement.append(newRow);
    });
}

function showAuthor(id) {
    fetch('http://' + CURRENT_URL + '/books/author/' + id)
        .then(response => response.json())
        .then(author => fillAuthorTable(author));
}

function fillAuthorTable(author) {
    var tbodyElement = $('#authorTable');
    tbodyElement.empty();
    var newRow = $('<tr>');
    var newCellId = $('<td>').text(author.id);
    newRow.append(newCellId);
    var newCellName = $('<td>').text(author.name);
    newRow.append(newCellName);
    var newCellBirth = $('<td>').text(author.birthDate);
    newRow.append(newCellBirth);

    tbodyElement.append(newRow);
}