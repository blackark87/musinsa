$(document).ready(
    function(){
        $("#q1").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            $.ajax({
                url: "/api/lowest-price-items",
                method: "GET",
                dataType: "JSON",
                error: function(xhr, status, error){
                    console.error(status, error);
                    $("content").html("<p>error</p>")
                }
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            })
        })

        $("#q2").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            $.ajax({
                url: "/api/lowest-price-brand-items",
                method: "GET",
                dataType: "JSON",
                error: function(xhr, status, error){
                    console.error(status, error);
                    $("content").html("<p>error</p>")
                }
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            })
        })

        $("#q3").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            let category = prompt("카테고리를 입력해 주세요")
            $.ajax({
                url: "/api/get-high-low-item/" + category,
                method: "GET",
                dataType: "JSON",
                error: function(xhr, status, error){
                    console.error(status, error);
                    $("content").html("<p>error</p>")
                }
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            })
        })

        $("#q4").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            $("#content").text("q4")
        })
    }
)