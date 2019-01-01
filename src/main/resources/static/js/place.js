function searchPlace() {
    var searchText = $('#searchText').val();
    var searchApi = $('#apiName option:selected').val();
    if (searchApi === 'ps') {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/place/search/"+searchText,
            async:true
        }).then(function(data) {
            $('#searchResult').val(data);
        });
    } else if (searchApi === 'pd') {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/place/"+searchText,
            async:true
        }).then(function(data) {
            $('#searchResult').val(data);
        });
    } else {
        alert('Select the api');
    }

}