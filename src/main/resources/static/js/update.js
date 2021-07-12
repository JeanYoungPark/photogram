// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault();
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type:"put",
        url:`/api/user/${userId}`,
        data:data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{ //HttpStatus 상태코드 200
        location.href = `/user/${userId}`;
    }).fail(error=>{ //HttpStatus 상태코드 200이 아닐 때
        if(error.data == null){
            alert(error.responseJSON.message);
        }else {
            alert(JSON.stringify(error.responseJSON.data));
        }
    });
}