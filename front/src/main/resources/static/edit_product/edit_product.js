angular.module('app-front').controller('editProductController', function ($routeParams, $scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

var objSel = document.getElementById("selectCategory");
console.log("objSel = "+objSel);
     $scope.putFormProduct = function () {
           $http.get(contextPath + 'products/' + $routeParams.productId)
            .then(function successCallback(response) {
                    $scope.put_product = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                    $location.path('store');
                }
            );
     }


      $scope.putProduct = function () {
              if ( objSel.selectedIndex != -1) {
                  $scope.put_product.categoryTitle = objSel.options[objSel.selectedIndex].value;
               }
                    $http.put(contextPath + 'products', $scope.put_product)
              .then(function successCallback(response) {
                      $scope.put_product = null;
                      alert("Продукт успешно обновлен");
                      $location.path('store');
                  }, function failCallback(response) {
                      alert(response.data.messages);
                  }
              );
                         }
           $scope.loadCategory = function(){
                 $http({
                    url: contextPath + 'category',
                    method: 'GET',
                 }).then(function(response) {
                 objSel.options.length=1;
                 for (let i = 0; i < response.data.length; i++) {
                     objSel.options[i+1] = new Option(response.data[i].title, response.data[i].title);
                 }
                 });
                 }

      $scope.loadCategory();
      $scope.putFormProduct();

});