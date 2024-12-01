function applyToTeam(button) {
    // 버튼의 data-project-leader 속성에서 팀 리더 정보 가져오기
    const projectLeader = button.getAttribute('data-project-leader');

    Swal.fire({
        title: '팀원 신청',
        text: '팀원 신청을 진행하시겠습니까?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '신청',
        cancelButtonText: '취소',
    }).then((result) => {
        if (result.isConfirmed) {
            fetch('/team-apply/apply', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    projectLeader: projectLeader, // 팀 리더 정보 전송
                }),
            })
                .then((response) => response.json())
                .then((data) => {
                    if (data.status === 'success') {
                        Swal.fire({
                            icon: 'success',
                            title: '신청 완료',
                            text: data.message,
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: '신청 실패',
                            text: data.message,
                        });
                    }
                })
                .catch((error) => {
                    console.error('Error during team apply:', error);
                    Swal.fire({
                        icon: 'error',
                        title: '오류',
                        text: '서버와의 통신 중 문제가 발생했습니다.',
                    });
                });
        }
    });
}
