//document.addEventListener('DOMContentLoaded', () => {
//    const mugshotsList = document.getElementById('mugshots');
//    const addForm = document.getElementById('add-mugshot-form');
//    const editForm = document.getElementById('edit-mugshot-form');
//    const deleteForm = document.getElementById('delete-mugshot-form');
//    const mugshotDetail = document.getElementById('mugshot-detail');
//    const mugshotId = document.getElementById('mugshot-id');
//    const mugshotName = document.getElementById('mugshot-name');
//    const mugshotDescription = document.getElementById('mugshot-description');
//
//    // Fetch and display all mugshots
//    function fetchMugshots() {
//        fetch('/mugshots')
//            .then(response => response.json())
//            .then(mugshots => {
//                mugshotsList.innerHTML = '';
//                mugshots.forEach(mugshot => {
//                    const li = document.createElement('li');
//                    li.textContent = `ID: ${mugshot.id}, Name: ${mugshot.name}, Description: ${mugshot.description}`;
//                    li.addEventListener('click', () => {
//                        window.history.pushState({}, '', `/localApp/mugshots/${mugshot.id}`);
//                        handleRouting();
//                    });
//                    mugshotsList.appendChild(li);
//                });
//            });
//    }
//
//    // Fetch and display a single mugshot
//    function fetchMugshot(id) {
//        fetch(`/mugshots/${id}`)
//            .then(response => response.json())
//            .then(mugshot => {
//                mugshotId.textContent = `ID: ${mugshot.id}`;
//                mugshotName.textContent = `Name: ${mugshot.name}`;
//                mugshotDescription.textContent = `Description: ${mugshot.description}`;
//                mugshotDetail.classList.add('active');
//                document.getElementById('mugshots-list').style.display = 'none';
//            })
//            .catch(error => {
//                alert(`Error: ${error.message}`);
//            });
//    }
//
//    // Go back to the mugshot list
//    window.goBack = function() {
//        window.history.pushState({}, '', '/mugshots');
//        handleRouting();
//    }
//
//    // Handle routing based on URL
//    function handleRouting() {
//        const path = window.location.pathname;
//        const pathSegments = path.split('/');
//
//        if (pathSegments.length === 4 && pathSegments[2] === 'mugshots') {
//            const id = pathSegments[3];
//            fetchMugshot(id);
//        } else {
//            mugshotDetail.classList.remove('active');
//            document.getElementById('mugshots-list').style.display = 'block';
//            fetchMugshots();
//        }
//    }
//
//    // Handle adding a new mugshot
//    addForm.addEventListener('submit', event => {
//        event.preventDefault();
//        const formData = new FormData(addForm);
//        const mugshot = {
//            name: formData.get('name'),
//            description: formData.get('description')
//        };
//
//        fetch('/mugshots/', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(mugshot)
//        })
//            .then(response => response.text())
//            .then(message => {
//                alert(message);
//                fetchMugshots();
//            });
//    });
//
//    // Handle editing an existing mugshot
//    editForm.addEventListener('submit', event => {
//        event.preventDefault();
//        const formData = new FormData(editForm);
//        const id = formData.get('id');
//        const mugshot = {
//            name: formData.get('name'),
//            description: formData.get('description')
//        };
//
//        fetch(`/mugshots/${id}`, {
//            method: 'PUT',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(mugshot)
//        })
//            .then(response => response.text())
//            .then(message => {
//                alert(message);
//                fetchMugshots();
//            });
//    });
//
//    // Handle deleting a mugshot
//    deleteForm.addEventListener('submit', event => {
//        event.preventDefault();
//        const formData = new FormData(deleteForm);
//        const id = formData.get('id');
//
//        fetch(`/mugshots/${id}`, {
//            method: 'DELETE'
//        })
//            .then(response => response.text())
//            .then(message => {
//                alert(message);
//                fetchMugshots();
//            });
//    });
//
//    // Initial fetch and routing
//    fetchMugshots();
//    window.addEventListener('popstate', handleRouting);
//    handleRouting();
//});
