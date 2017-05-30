$(function () {

    $('#getPrognosis').on('click', getPrognosis);

    function getPrognosis(diseaseName, year) {
        var tmp = null;
        $.ajax({
            async: false,
            type: "POST",
            global: false,
            dataType: 'json',
            contentType: "application/json",
            url: "/getResultsOfNetworkWork",
            success: function (data) {
                tmp = data;
            }
        });
        return tmp;
    }
});