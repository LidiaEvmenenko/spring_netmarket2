angular.module('app-front').controller('authController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

 $scope.tryToAuth = function () {

        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
            alert("$scope.user.username = "+$scope.user.username);
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
                $location.path('welcome');
            }, function errorCallback(response) {
            console.log(response);
          //   alert(response.data.messages);
            });
    };
 $scope.tryToReg = function() {
 console.log("$scope.user = "+$scope.user);
        $http.post(contextPath + 'auth/registration', $scope.user)
            .then(function successCallback(response) {
              alert("Регистрация прошла успешно");
              $location.path('welcome');
            }, function errorCallback(response) {
              alert(response.data.messages);
              $scope.user.username = null;
              $scope.user.password = null;
              $scope.user.email = null;
            });
 }

});