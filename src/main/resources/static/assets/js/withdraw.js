function withdraw() {
    Swal.fire({
        title: '회원 탈퇴',
        text: '정말 회원 탈퇴를 진행하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '탈퇴',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            // 사용자가 확인 버튼을 클릭했을 때 탈퇴 처리
            fetch('/member/withdraw', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => response.json())
                .then(data => {
                    if (data.status === 'success') {
                        Swal.fire({
                            icon: 'success',
                            title: '회원 탈퇴 완료',
                            text: data.message,
                        }).then(() => {
                            window.location.href = '/'; // 메인 페이지로 리다이렉트
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: '회원 탈퇴 실패',
                            text: data.message,
                        });
                    }
                })
                .catch(error => {
                    console.error('Error during withdrawal:', error);
                    Swal.fire({
                        icon: 'error',
                        title: '서버 오류',
                        text: '서버와 통신 중 오류가 발생했습니다. 다시 시도해주세요.',
                    });
                });
        }
    });
}
