function loadContent(event) {
    event.preventDefault();
    const url = event.target.closest('a').getAttribute('data-url');
    
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'text/html'
        }
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('main-content').innerHTML = data;
		
		initializeValid();
    })
    .catch(error => {
        console.error('Error fetching content:', error);
    });
}

function loadSideContent(event) {
    event.preventDefault();
    const url = event.target.closest('a').getAttribute('data-url');
    
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'text/html'
        }
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('side-content').innerHTML = data;
    })
    .catch(error => {
        console.error('Error fetching content:', error);
    });
}