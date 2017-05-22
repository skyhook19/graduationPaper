$(function () {

    google.charts.load('current', {'packages': ['line', 'corechart']});
    google.charts.setOnLoadCallback(updateChart); 

    $('#showNewChart').on('click', updateChart);

    function updateChart(){
        var diseaseName = $("#diseaseSelect option:selected").text();
        var year = $("#yearSelect option:selected").text();
        var weather = $("#weatherSelect option:selected").text();
        var diseaseData = getAmbulanceCallData(diseaseName, year);
        var weatherData = getWeatherDataFromServer(weather, year);

        drawChart(diseaseName, year, weather, diseaseData, weatherData);
    }

    function getAmbulanceCallData(diseaseName, year) {
        var tmp = null;
        $.ajax({
            'async': false,
            'type': "POST",
            'global': false,
            'dataType': 'json',
            'contentType' : "application/json",
            'url': "/getAmbulanceCallStatsByYearAndDisease",
            'data': JSON.stringify({
                year: year,
                diseaseName: diseaseName
            }),
            'success': function (data) {
                tmp = data;
            }
        });
        return tmp;
    }

    function getWeatherDataFromServer(weather, year) {
        var tmp = null;
        $.ajax({
            'async': false,
            'type': "POST",
            'global': false,
            'dataType': 'json',
            'contentType' : "application/json",
            'url': "/getWeatherDataForYear",
            'data': JSON.stringify({
                weatherName : weather,
                year : year
            }),
            'success': function (data) {
                tmp = data;
            }
        });
        return tmp;
    }

    function drawChart(diseaseName, year, weatherCondition, diseaseData, weatherData) {

        var chartDiv = document.getElementById('chart_div');
        var data = new google.visualization.DataTable();

        data.addColumn('string', 'Месяц');
        data.addColumn('number', weatherCondition);
        data.addColumn('number', "Количество вызовов");

        data.addRows([
            ['Январь', weatherData[0], diseaseData[0]],
            ['Февраль', weatherData[1], diseaseData[1]],
            ['Март', weatherData[2], diseaseData[2]],
            ['Апрель', weatherData[3], diseaseData[3]],
            ['Май', weatherData[4], diseaseData[4]],
            ['Июнь', weatherData[5], diseaseData[5]],
            ['Июль', weatherData[6], diseaseData[6]],
            ['Август', weatherData[7], diseaseData[7]],
            ['Сентябрь', weatherData[8], diseaseData[8]],
            ['Октябрь', weatherData[9], diseaseData[9]],
            ['Ноябрь', weatherData[10], diseaseData[10]],
            ['Декабрь', weatherData[11], diseaseData[11]]
        ]);

        var materialOptions = {
            chart: {
            title: 'Соотношение между показателем "' + weatherCondition + '" и количеством вызовов скорой помощи по диагнозу "' + diseaseName + '" в течение ' + year + ' года'
            },
            width: 1000,
            height: 500,
            series: {
                // Gives each series an axis name that matches the Y-axis below.
                0: {axis: 'Disease'},
                1: {axis: 'Weather'}
            },
            axes: {
                // Adds labels to each axis; they don't have to match the axis names.
                y: {
                    Disease: {label: weatherCondition},
                    Weather: {label: 'Количество вызовов скорой помощи по диагнозу "' + diseaseName + '"'}
                }
            }
        };

        var materialChart = new google.charts.Line(chartDiv);
        materialChart.draw(data, materialOptions);        
    }
});