

$.ajax({
    url: "/get/user/icon",
    type: "GET",
    success: function onSuccess(data){
        console.log("유저 이미지 가져옴")
        console.log(data)
        $('#nav-avatar').attr("src",data)
    },
    error: function onError(error) {
        console.log(error)
    }
})
