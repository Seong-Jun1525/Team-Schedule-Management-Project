function initializeValid() {
	// 회원가입 폼 유효성 검사
    const signUpform = document.getElementById('sign-up-form');
    if (signUpform) {
        signUpform.addEventListener('submit', function (e) {
            e.preventDefault(); // 기본 제출 동작 중단

            let isValid = true;
            const errors = document.querySelectorAll('.error-msg');

            // 기존 오류 메시지를 초기화
            errors.forEach((error) => {
                error.textContent = '';
            });

            // 유효성 검사 함수
            function showError(name, message) {
                const input = document.querySelector(`[name="${name}"]`);
                const errorDiv = input.closest('.form-item').querySelector('.error-msg');
                errorDiv.textContent = message;
                errorDiv.style.color = 'red';
                isValid = false; // 유효성 검사 실패

                // 입력 이벤트 추가
                input.addEventListener('input', function () {
                    errorDiv.textContent = ''; // 입력 시 오류 메시지 제거
                }, { once: true }); // 이벤트는 한 번만 실행
            }

            // 간단한 입력 여부 검사
            const userId = document.querySelector('input[name="userId"]').value.trim();
            if (!userId) {
                showError('userId', '아이디를 입력해주세요.');
            }

            const userPw = document.querySelector('input[name="userPw"]').value.trim();
            if (!userPw) {
                showError('userPw', '비밀번호를 입력해주세요.');
            }

            const userName = document.querySelector('input[name="userName"]').value.trim();
            if (!userName) {
                showError('userName', '이름을 입력해주세요.');
            }

            const userEmail = document.querySelector('input[name="userEmail"]').value.trim();
            if (!userEmail) {
                showError('userEmail', '이메일을 입력해주세요.');
            }

            const userGender = document.querySelector('input[name="userGender"]:checked');
            if (!userGender) {
                showError('userGender', '성별을 선택해주세요.');
            }

            const userJob = document.querySelector('select[name="userJob"]').value;
            if (!userJob) {
                showError('userJob', '직업을 선택해주세요.');
            }

            // 유효성 검사가 성공하면 AJAX 요청
            if (isValid) {
                const formData = new FormData(signUpform);
                const formObject = Object.fromEntries(formData.entries());

                fetch('/member/sign-up', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formObject),
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.status === 'error') {
                            Swal.fire({
                                icon: 'error',
                                title: '오류',
                                text: data.message,
                            });
                        } else if (data.status === 'success') {
                            Swal.fire({
                                icon: 'success',
                                title: '회원가입 성공',
                                text: data.message,
                            }).then(() => {
                                window.location.href = '/'; // 성공 시 리디렉션
                            });
                        }
                    })
					.catch(error => {
					    console.error('Error during login:', error);
					    Swal.fire({
					        icon: 'error',
					        title: '서버 오류',
					        text: '서버와의 통신 중 문제가 발생했습니다. 다시 시도해주세요.',
					    });
					});
            }
        });
    }
	
	// 로그인 폼 유효성 검사
	const signInForm = document.getElementById('sign-in-form');
	if (signInForm) {
	    signInForm.addEventListener('submit', function (e) {
	        e.preventDefault(); // 기본 제출 동작 중단

	        let isValid = true;
	        const errors = document.querySelectorAll('.error-msg');

	        // 기존 오류 메시지를 초기화
	        errors.forEach((error) => {
	            error.textContent = '';
	        });

	        // 유효성 검사 함수
	        function showError(name, message) {
	            const input = document.querySelector(`[name="${name}"]`);
	            const errorDiv = input.closest('.form-item').querySelector('.error-msg');
	            errorDiv.textContent = message;
	            errorDiv.style.color = 'red';
	            isValid = false; // 유효성 검사 실패

	            // 입력 이벤트 추가
	            input.addEventListener('input', function () {
	                errorDiv.textContent = ''; // 입력 시 오류 메시지 제거
	            }, { once: true }); // 이벤트는 한 번만 실행
	        }

	        // 간단한 입력 여부 검사
	        const userId = document.querySelector('input[name="userId"]').value.trim();
	        if (!userId) {
	            showError('userId', '아이디를 입력해주세요.');
	        }

	        const userPw = document.querySelector('input[name="userPw"]').value.trim();
	        if (!userPw) {
	            showError('userPw', '비밀번호를 입력해주세요.');
	        }

	        // 유효성 검사가 성공하면 AJAX 요청
	        if (isValid) {
	            const formData = new FormData(signInForm);
	            const formObject = Object.fromEntries(formData.entries());

	            fetch('/member/sign-in', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json',
	                },
	                body: JSON.stringify(formObject),
	            })
	                .then(response => response.json())
	                .then(data => {
	                    if (data.status === 'error') {
	                        Swal.fire({
	                            icon: 'error',
	                            title: '로그인 실패',
	                            text: data.message,
	                        });
	                    } else if (data.status === 'success') {
							window.location.href = '/'; // 성공 시 대시보드로 리디렉션
	                    }
	                })
	                .catch(error => {
	                    console.error('Error during login:', error);
	                    Swal.fire({
	                        icon: 'error',
	                        title: '서버 오류',
	                        text: '서버와의 통신 중 문제가 발생했습니다. 다시 시도해주세요.',
	                    });
	                });
	        }
	    });
	}
	
	// 회원정보 수정 폼 유효성 검사
	const myPageForm = document.getElementById('my-page-form');
	if (myPageForm) {
	    myPageForm.addEventListener('submit', function (e) {
	        e.preventDefault(); // 기본 제출 동작 중단

	        let isValid = true;
	        const errors = document.querySelectorAll('.error-msg');

	        // 기존 오류 메시지를 초기화
	        errors.forEach((error) => {
	            error.textContent = '';
	        });

	        // 유효성 검사 함수
	        function showError(name, message) {
	            const input = document.querySelector(`[name="${name}"]`);
	            const errorDiv = input.closest('.form-item').querySelector('.error-msg');
	            errorDiv.textContent = message;
	            errorDiv.style.color = 'red';
	            isValid = false; // 유효성 검사 실패

	            // 입력 이벤트 추가
	            input.addEventListener('input', function () {
	                errorDiv.textContent = ''; // 입력 시 오류 메시지 제거
	            }, { once: true }); // 이벤트는 한 번만 실행
	        }

	        // 간단한 입력 여부 검사
	        const userName = document.querySelector('input[name="userName"]').value.trim();
	        if (!userName) {
	            showError('userName', '이름을 입력해주세요.');
	        }

	        const userEmail = document.querySelector('input[name="userEmail"]').value.trim();
	        if (!userEmail) {
	            showError('userEmail', '이메일을 입력해주세요.');
	        }

	        const userGender = document.querySelector('input[name="userGender"]:checked');
	        if (!userGender) {
	            showError('userGender', '성별을 선택해주세요.');
	        }

	        const userJob = document.querySelector('select[name="userJob"]').value;
	        if (!userJob) {
	            showError('userJob', '직업을 선택해주세요.');
	        }

	        // 유효성 검사가 성공하면 AJAX 요청
	        if (isValid) {
	            const formData = new FormData(myPageForm);
	            const formObject = Object.fromEntries(formData.entries());

	            fetch('/member/update-user-info', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json',
	                },
	                body: JSON.stringify(formObject),
	            })
	                .then(response => response.json())
	                .then(data => {
	                    if (data.status === 'error') {
	                        Swal.fire({
	                            icon: 'error',
	                            title: '수정 실패',
	                            text: data.message,
	                        });
	                    } else if (data.status === 'success') {
	                        Swal.fire({
	                            icon: 'success',
	                            title: '수정 성공',
	                            text: '회원정보가 성공적으로 수정되었습니다.',
	                        }).then(() => {
	                            window.location.reload(); // 성공 시 페이지 새로고침
	                        });
	                    }
	                })
	                .catch(error => {
	                    console.error('Error during info update:', error);
	                    Swal.fire({
	                        icon: 'error',
	                        title: '서버 오류',
	                        text: '서버와의 통신 중 문제가 발생했습니다. 다시 시도해주세요.',
	                    });
	                });
	        }
	    });
	}
	
}
