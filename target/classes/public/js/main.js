var app = angular.module("springDemo", []);

app.controller("AppCtrl", function ($scope, $http) {
    //$scope.websites = [];
    $http({
        method: 'GET',
        url: 'http://localhost:8090/api/stackoverflow'
    }).then(successCallback, errorCallback);

    function successCallback(response){
       $scope.websites = response.data
    }
    function errorCallback(error){

    }
});
