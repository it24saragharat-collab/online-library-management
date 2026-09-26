<!DOCTYPE html>
<html>
<head>
    <title>Online Library Management System</title>
</head>

<body>

<h1>Online Library Management System</h1>

<h2>Search Book</h2>

<input type="text" id="bookName" placeholder="Enter book name">

<button onclick="searchBook()">Search</button>

<p id="result"></p>

<script>
function searchBook() {

    let book = document.getElementById("bookName").value;

    if (book === "") {
        document.getElementById("result").innerHTML =
        "Please enter a book name.";
    }
    else {
        document.getElementById("result").innerHTML =
        "Book searched: " + book;
    }
}
</script>

</body>
</html>
