function logout(event) {
    event.preventDefault(); // 기본 동작(링크 이동) 중단

    fetch('/member/logout', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (response.ok) {
                // 로그아웃 성공 시 Swal 표시
                Swal.fire({
                    icon: 'success',
                    title: '로그아웃 성공',
                    text: '성공적으로 로그아웃되었습니다.',
                }).then(() => {
                    // 확인 버튼 클릭 후 메인 페이지로 이동
                    window.location.href = '/';
                });
            } else {
                // 오류 처리
                console.error('Logout failed');
                Swal.fire({
                    icon: 'error',
                    title: '오류',
                    text: '로그아웃에 실패했습니다. 다시 시도해주세요.',
                });
            }
        })
        .catch(error => {
            console.error('Error during logout:', error);
            Swal.fire({
                icon: 'error',
                title: '오류',
                text: '서버와 연결에 실패했습니다.',
            });
        });
}
