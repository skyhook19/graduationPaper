$(function () {

    $('#getPrognosis').on('click', getResult);

    function getResult() {
        var networkWorkResult = getPrognosis();
        var diseases = ['ОРВИ', 'Инфаркт миокарда', 'Инфаркт миокарда с госпитализацией',
                'Инфаркт миокарда со смертельным исходом', 'Стенокардия', 'Аритмия сердца',
                'Сердечно-сосудистые заболевания', 'Сердечно-сосудистые заболевания со смертельным исходом', 'Инсульт',
                'Инсульт со смертельным исходом', 'Пневмония', 'Бронхиальная астма', 'Бронхит', 'Язвенная болезнь', 'Гастрит'];

        var tbody = $('#tbody');
        for (var i = 0; i < networkWorkResult.length; i++) {
            var tr = $('<tr/>').appendTo(tbody);
            tr.append('<td>' + diseases[i] + '</td>');
            tr.append('<td>' + networkWorkResult[i] + '</td>');
        }

        $('#resultTable').css('display', 'table');
    }

    function getPrognosis() {
        var temp = $("#temp").val();
        var press = $("#press").val();
        var humid = $("#humid").val();
        var wind = $("#wind").val();
        var cloud = $("#cloud").val();
        var minTemp = $("#minTemp").val();
        var maxTemp = $("#maxTemp").val();
        var downfall = $("#downfall").val();

        var weatherArr = [temp, press, humid, wind, cloud, minTemp, maxTemp, downfall];
        var serializedWeatherArr = JSON.stringify(weatherArr);

        console.log(serializedWeatherArr);

        var tmp = null;
        $.ajax({
            async: false,
            type: "POST",
            global: false,
            dataType: 'json',
            contentType: "application/json",
            url: "/getResultsOfNetworkWork",
            data: serializedWeatherArr,
            success: function (data) {
                tmp = data;
            }
        });
        return tmp;
    }
});