angular.module('app-front').controller('statisticController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1/statistic';

     $scope.printMap = function(){

             $http.get(contextPath)
             .then(function(response) {


console.log(response.data);
              $scope.mapp = response.data;


      }, function errorCallback(response) {
                    alert(response.data.messages);
                   });
                   }
                   $scope.printMap();
});