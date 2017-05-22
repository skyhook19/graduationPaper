$(function () {

    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(updateChart);

    $('#showNewChart').on('click', updateChart);

    function updateChart(){
        var year = $("#yearSelect option:selected").text();
        var ambulanceCallStats = getAmbulanceCallStatsForYear(year);

        drawChart(year, ambulanceCallStats);
    }

    function getAmbulanceCallStatsForYear(year) {
        var tmp = null;
        $.ajax({
            'async': false,
            'type': "POST",
            'global': false,
            'dataType': 'json',
            'contentType' : "application/json",
            'url': "/getAmbulanceCallStatsByYear",
            'data': JSON.stringify({
                year: year,
            }),
            'success': function (data) {
                tmp = data;
            }
        });
        return tmp;
    }

    function drawChart(year, ambulanceCallStats) {
        var data = new google.visualization.DataTable();


        var data = google.visualization.arrayToDataTable([
            ['Диагноз', 'Количество вызовов'],
            ['ОРВИ', ambulanceCallStats['ОРВИ']],
            ['Инфаркт миокарда', ambulanceCallStats['Инфаркт миокарда']],
            ['Инфаркт миокарда с госпитализацией', ambulanceCallStats['Инфаркт миокарда с госпитализацией']],
            ['Инфаркт миокарда со смертельным исходом', ambulanceCallStats['Инфаркт миокарда со смертельным исходом']],
            ['Стенокардия', ambulanceCallStats['Стенокардия']],
            ['Аритмия сердца', ambulanceCallStats['Аритмия сердца']],
            ['Сердечно-сосудистые заболевания', ambulanceCallStats['Сердечно-сосудистые заболевания']],
            ['Сердечно-сосудистые заболевания со смертельным исходом', ambulanceCallStats['Сердечно-сосудистые заболевания со смертельным исходом']],
            ['Инсульт', ambulanceCallStats['Инсульт']],
            ['Инсульт со смертельным исходом', ambulanceCallStats['Инсульт со смертельным исходом']],
            ['Пневмония', ambulanceCallStats['Пневмония']],
            ['Бронхиальная астма', ambulanceCallStats['Бронхиальная астма']],
            ['Бронхит', ambulanceCallStats['Бронхит']],
            ['Язвенная болезнь', ambulanceCallStats['Язвенная болезнь']],
            ['Гастрит', ambulanceCallStats['Гастрит']],
        ]);


        var options = {
            title: 'Сравнительная диаграмма всех случаев вызова скорой помощи за ' + year + " год.",
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }
})