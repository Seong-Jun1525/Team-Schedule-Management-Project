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

function acceptTeamApplier(button) {
    const applier = button.getAttribute('data-applier'); // 버튼의 data-applier 값 가져오기

    Swal.fire({
        title: '팀원 신청 수락',
        text: '해당 신청을 수락하시겠습니까?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '수락',
        cancelButtonText: '취소',
    }).then((result) => {
        if (result.isConfirmed) {
            fetch('/team-apply/accept', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({ applier }), // applier 데이터 전송
            })
                .then((response) => response.json())
                .then((data) => {
                    if (data.status === 'success') {
                        Swal.fire({
                            icon: 'success',
                            title: '수락 완료',
                            text: data.message,
                        }).then(() => {
                            location.reload(); // 페이지 새로고침
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: '오류',
                            text: data.message,
                        });
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                    Swal.fire({
                        icon: 'error',
                        title: '서버 오류',
                        text: '서버와의 통신 중 문제가 발생했습니다.',
                    });
                });
        }
    });
}

function rejectTeamApplier(button) {
    const applier = button.getAttribute('data-applier'); // 버튼의 data-applier 값 가져오기

    Swal.fire({
        title: '팀원 신청 거절',
        text: '해당 신청을 거절하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '거절',
        cancelButtonText: '취소',
    }).then((result) => {
        if (result.isConfirmed) {
            fetch('/team-apply/reject', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({ applier }), // applier 데이터 전송
            })
                .then((response) => response.json())
                .then((data) => {
                    if (data.status === 'success') {
                        Swal.fire({
                            icon: 'success',
                            title: '거절 완료',
                            text: data.message,
                        }).then(() => {
                            location.reload(); // 페이지 새로고침
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: '오류',
                            text: data.message,
                        });
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                    Swal.fire({
                        icon: 'error',
                        title: '서버 오류',
                        text: '서버와의 통신 중 문제가 발생했습니다.',
                    });
                });
        }
    });
}

