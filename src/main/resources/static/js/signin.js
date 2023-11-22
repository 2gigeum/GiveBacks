document.addEventListener("DOMContentLoaded", function () {


    document.getElementById("signin-button")
        .addEventListener("click", function (event) {
            event.preventDefault(); //기본 동작 폼 방지
            let email = document.getElementById("userEmail").value;
            let userPassword = document.getElementById("userPassword").value;
            fetch("/user/signin", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    userEmail: email,
                    userPassword: userPassword
                }),
            }).then(function (response) {
                if (response.status === 200) {
                    alert("로그인 성공");
                    window.location.href = "/";
                } else {
                    alert("로그인 실패");
                }
            });


        })


})