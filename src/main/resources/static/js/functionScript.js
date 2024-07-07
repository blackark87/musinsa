$(document).ready(
    function(){
        $("#q1").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            $.ajax({
                url: "/api/lowest-price-items",
                method: "GET",
                dataType: "JSON",
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            }).fail(function(xhr, status, error){
                console.error(status, error);
                $("#content").html("<pre>" + JSON.stringify(xhr.responseJSON, null, 2) + "</pre>")
            })
        })

        $("#q2").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            $.ajax({
                url: "/api/lowest-price-brand-items",
                method: "GET",
                dataType: "JSON",
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            }).fail(function(xhr, status, error){
                console.error(status, error);
                $("#content").html("<pre>" + JSON.stringify(xhr.responseJSON, null, 2) + "</pre>")
            })
        })

        $("#q3").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $(this).addClass("active")
            let category = prompt("카테고리를 입력해 주세요")
            if(category == null || category === ""){
                alert("카테고리를 입력해 주세요")
                return
            }
            $.ajax({
                url: "/api/high-low-item/" + category,
                method: "GET",
                dataType: "JSON",
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            }).fail(function(xhr, status, error){
                console.error(status, error);
                $("#content").html("<pre>" + JSON.stringify(xhr.responseJSON, null, 2) + "</pre>")
            })
        })

        $("#q4-add").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $("#q4").addClass("active")
            let brand = prompt("브랜드를 입력해 주세요")
            let category = prompt("카테고리를 입력해 주세요")
            let price = prompt("가격을 입력해 주세요")
            let item = {
                brand: brand,
                category: category,
                price: price
            }
            $.ajax({
                url: "/api/item",
                method: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(item),
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            }).fail(function(xhr, status, error){
                console.error(status, error);
                $("#content").html("<pre>" + JSON.stringify(xhr.responseJSON, null, 2) + "</pre>")
            })

        })

        $("#q4-delete").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $("#q4").addClass("active")

            let brand = prompt("브랜드를 입력해 주세요")
            let category = prompt("카테고리를 입력해 주세요")
            let item = {
                brand: brand,
                category: category
            }
            $.ajax({
                url: "/api/item",
                method: "DELETE",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(item),
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            }).fail(function(xhr, status, error){
                console.error(status, error);
                $("#content").html("<pre>" + JSON.stringify(xhr.responseJSON, null, 2) + "</pre>")
            })

        })

        $("#q4-update").off("click").on("click", function() {
            $(".quest-button").removeClass("active")
            $("#q4").addClass("active")

            let originBrand = prompt("수정할 브랜드를 입력해 주세요")
            let originCategory = prompt("수정할 카테고리를 입력해 주세요")

            let newBrand = prompt("새로운 브랜드를 입력해 주세요")
            let newCategory = prompt("새로운 카테고리를 입력해 주세요")
            let newPrice = prompt("새로운 가격을 입력해 주세요")
            let item = {
                brand: newBrand,
                category: newCategory,
                price: newPrice
            }
            $.ajax({
                url: "/api/item/" + originBrand + "/" + originCategory,
                method: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(item),
                error: function(xhr, status, error){
                    console.error(status, error);
                    $("content").html("<p>error</p>")
                }
            }).done(function(response){
                let content = "<pre>" + JSON.stringify(response, null, 2) + "</pre>"
                $("#content").html(content)
            }).fail(function(xhr, status, error){
                console.error(status, error);
                $("#content").html("<pre>" + JSON.stringify(xhr.responseJSON, null, 2) + "</pre>")
            })

        })
    }
)