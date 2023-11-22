document.addEventListener("DOMContentLoaded", function () {
    let emailCheck = false;

    /**
     * 이메일 인증버튼 클릭시 호출
     */

    document.getElementById("sendEmail-button")
        .addEventListener(
            "click", function (event) {
                event.preventDefault(); //기본 동작 폼 방지

                var email = document.getElementById("userEmail").value;

                //이메일 중복체크확인
                fetch("/user/email/check?email=" + email, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                    },
                }).then(function (response) {
                    if (response.status === 409) {
                        alert("이미 사용중인 이메일입니다.");
                    } else {
                        fetch("/sendEmail", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded",
                            },
                            body: "email=" + email, //POST 요청의 본문 데이터 설정
                        }).then(function (response) {
                            if (response.status === 200) {
                                // 이메일 전송 성공
                                alert("이메일이 전송되었습니다.")
                                // 사용자로부터 인증 코드 입력을 받기 위한 입력 필드 표시
                                document.getElementById("verification-section").style.display = "block";
                                // "인증 코드 확인" 버튼을 활성화
                            } else {
                                // 이메일 전송 실패
                                alert("이메일 전송에 실패했습니다.");
                            }
                        })
                    }
                })
            })


    /**
     * 인증코드 확인 버튼 클릭시 호출
     */
    document.getElementById("verify-button")
        .addEventListener("click", function (event) {

            event.preventDefault(); //기본 동작 폼 방지

            var code = document.getElementById("verificationCode").value;

            // 서버로 인증 코드 확인 요청 보내기
            fetch("/user/verification-code", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    code: code,
                }), // POST 요청의 본문 데이터 설정
            })
                .then(function (response) {
                    if (response.status === 200) {
                        // 인증 성공
                        emailCheck = true;
                        alert("이메일 인증이 완료되었습니다.");
                    } else {
                        // 인증 실패
                        alert("인증 코드가 올바르지 않습니다.");
                    }
                });

        })


    document.getElementById("signup-button")
        .addEventListener("click", function (event) {
            event.preventDefault(); //기본 동작 폼 방지




            if(!emailCheck){
                alert("이메일 인증을 먼저 완료해주세요.")
            }else{
                let formData = {
                    userEmail: document.getElementById('userEmail').value,
                    userPassword: document.getElementById('userPassword').value,
                    userName: document.getElementById('userName').value,
                    userNickname: document.getElementById('userNickname').value
                };

               fetch("/user/signup",{
                   method: "POST",
                   headers: {
                       "Content-Type": "application/json",
                   },
                   body: JSON.stringify({
                       userEmail: formData.userEmail,
                       userPassword: formData.userPassword,
                       userName: formData.userName,
                       userNickname: formData.userNickname
                   }),
               }).then()
                alert("회원가입이 완료되었습니다.")
                window.location.href = "/user/signin";
            }


        })


})