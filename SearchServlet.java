<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guliko Search</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        #search-container { display: flex; align-items: center; margin-bottom: 20px; }
        #search-label { margin-right: 10px; }
        #results { margin-top: 20px; }
        .result-item { margin: 5px 0; }
    </style>
</head>
<body>

    <div id="search-container">
        <label id="search-label" for="search-input">Search:</label>
        <input type="text" id="search-input" placeholder="Enter your search query...">
        <button id="search-button">Search</button>
    </div>

    <div id="results"></div>

    <script>
        document.getElementById('search-button').onclick = function() {
            const query = document.getElementById('search-input').value.toLowerCase();
            const resultsDiv = document.getElementById('results');
            resultsDiv.innerHTML = ''; // Clear previous results

            // Make AJAX call to fetch the registry.json file
            fetch('/path/to/your/registry.json') // Update with the correct path
                .then(response => response.json())
                .then(data => {
                    const results = data.filter(item => item.name.toLowerCase().includes(query));
                    if (results.length > 0) {
                        results.forEach(result => {
                            const resultItem = document.createElement('div');
                            resultItem.className = 'result-item';
                            resultItem.innerHTML = `<a href="${result.url}" target="_blank">${result.name}</a>`;
                            resultsDiv.appendChild(resultItem);
                        });
                    } else {
                        resultsDiv.innerHTML = '<p>No results found.</p>';
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    resultsDiv.innerHTML = '<p>Error fetching results.</p>';
                });
        };
    </script>

</body>
</html>
