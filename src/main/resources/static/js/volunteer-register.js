document.addEventListener("DOMContentLoaded", function () {


    document.getElementById("write-button")
        .addEventListener("click", function (event) {
            event.preventDefault();
            let title = document.getElementById("postTitle").value;
            let content = document.getElementById("postContent").value;


            fetch("/post/volunteer-register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    postTitle: title,
                    postContent: content
                }), // POST 요청의 본문 데이터 설정
            })
                .then(function (response) {
                    if (response.status === 200) {
                        alert("글 작성완료")
                        window.location.href ="/post/volunteer-search";
                    }
                });

        })


})