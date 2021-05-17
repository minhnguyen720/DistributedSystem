$(document).ready(function () {
    if (Cookies.get("isLogin") == null)
		window.location.replace("/Login/index.html");
})

function sumbitData() {
  $.ajax({
    type: "GET",
    async: false,
    url: "http://localhost:8080/Login?input=" + encodeURIComponent($("#answer").val()),
    success: (data) => {
        console.log(data);
        $("#answer").val(data)
    },
    error: (xhr, ajaxOptions, thrownError) =>
        console.log("wrong")
  });
}