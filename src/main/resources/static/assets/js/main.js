/*
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
*/
function loadContent(event) {
    event.preventDefault();
    const anchor = event.target.closest('a');
    const url = anchor ? anchor.getAttribute('data-url') : null;
/*
    if (!url) {
        console.error("URL이 유효하지 않습니다.");
        alert("요청을 처리할 수 없습니다. 올바른 링크를 클릭했는지 확인하세요.");
        return;
    }
*/
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'text/html',
        },
    })
        .then((response) => {
			/*
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            */
            return response.text();
        })
        .then((data) => {
            document.getElementById('main-content').innerHTML = data;
        })
        /*
        .catch((error) => {
            console.error('Content fetch error:', error);
            alert('컨텐츠를 불러오는 중 문제가 발생했습니다. 다시 시도해주세요.');
        });
        */
}

function loadSideContent(event) {
    event.preventDefault();
    const anchor = event.target.closest('a');
    const url = anchor ? anchor.getAttribute('data-url') : null;

    if (!url) {
        console.error("URL이 유효하지 않습니다.");
        alert("요청을 처리할 수 없습니다. 올바른 링크를 클릭했는지 확인하세요.");
        return;
    }

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'text/html',
        },
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.text();
        })
        .then((data) => {
            document.getElementById('side-content').innerHTML = data;
        })
        .catch((error) => {
            console.error('Content fetch error:', error);
            alert('컨텐츠를 불러오는 중 문제가 발생했습니다. 다시 시도해주세요.');
        });
}

function addSelectedTechStack() {
    const select = document.querySelector("#availableTechStacks");
    const selectedValue = select.options[select.selectedIndex]?.value; // 선택된 값 가져오기
    const ul = document.querySelector("#techStackList");

    console.log("Selected Value:", selectedValue); // 선택된 값 출력

    if (!selectedValue) {
        alert("추가할 기술 스택을 선택해주세요.");
        return;
    }

    const existingItems = Array.from(ul.children).map(li => li.querySelector('.tech-stack').textContent.trim());
    const formattedValue = `#${selectedValue}`;  // `selectedValue`에 `#` 추가

    if (existingItems.includes(formattedValue)) {
        alert("이미 추가된 기술 스택입니다.");
        return;
    }

    // 새로운 리스트 항목 추가
    const li = document.createElement("li");

    // 기술 스택을 표시할 span 생성
    const techStackSpan = document.createElement("span");
    techStackSpan.classList.add("tech-stack");
    techStackSpan.setAttribute("name", "projectTechStacks");  // 실제 name 속성 추가
    techStackSpan.setAttribute("value", selectedValue);  // 실제 value 속성 추가
    techStackSpan.innerHTML = `#${selectedValue}`;

    // 삭제 버튼 추가
    const removeBtn = document.createElement("span");
    removeBtn.classList.add("delete-tech-stack");
    removeBtn.innerHTML = '<i class="fa-solid fa-rectangle-xmark"></i>';
    removeBtn.onclick = () => li.remove();

    // li에 기술 스택과 삭제 버튼 추가
    li.appendChild(techStackSpan);
    techStackSpan.appendChild(removeBtn);

    // ul에 새로운 항목 추가
    ul.appendChild(li);
    
    const input = document.createElement("input");
    input.type = "hidden";  // 보이지 않는 <input> 태그
    input.name = "techStacks";  // 서버로 전송될 name
    input.value = selectedValue;  // 선택된 값
    document.querySelector("form").appendChild(input);

    // 선택된 항목을 비워서 다시 선택할 수 있도록 함
    select.value = "";
}