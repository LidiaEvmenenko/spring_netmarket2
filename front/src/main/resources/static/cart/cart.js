angular.module('app-front').controller('cartProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/cart/';
let a;
let totalPagesCart;
let itog;
let idUser= null;
  $scope.loadProducts = function(pageIndex = 1){
               currentPageIndex = pageIndex;
               $http({
               url: contextPath,
               method: 'GET',
              params: {
             p: pageIndex
           }
       }).then(function(response) {

          $scope.productsCart = response.data;
//          console.log("id = "+response.data.content.get(0).userId);
//          console.log(response.data.content);
        //  a = response.data.content;
          totalPagesCart = $scope.productsCart.totalPages;
         $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsCart.totalPages);
         $scope.itog();
        });
  }
 $scope.itog = function(){
       $http.get(contextPath + 'sum')
          .then(function successCallback(response) {
          itog = response.data;
         //  console.log(response.data);
           if(currentPageIndex == totalPagesCart && response.data != 0){
           document.getElementById('trItog').style.display = 'table-row';
            document.getElementById("sumItog").innerHTML = response.data;
           }else{
             document.getElementById('trItog').style.display = 'none';
           }
     }, function failCallback(response) {
                alert(response.data.messages);
       });
 }

     $scope.deleteProduct = function (productId) {
         $http.delete(contextPath + productId)
                     .then(function successCallback(response) {
                         $scope.productsCart = response.data;
                         $scope.loadProducts(currentPageIndex);
                         }, function failCallback(response) {
                              alert(response.data.messages);
                     });
     }

     $scope.deleteProductAll = function() {
      $http.delete(contextPath)
                          .then(function successCallback(response) {
                              $scope.productsCart = response.data;
                               $location.path('store');
                            //  $scope.loadProducts(currentPageIndex);
                              }, function failCallback(response) {
                                   alert(response.data.messages);
                          });
     }


     $scope.generatePagesIndexes = function (startPage, endPage) {
                     let arr = [];
                     for (let i = startPage; i < endPage + 1; i++) {
                         arr.push(i);
                     }
                     return arr;
                 }

      $scope.nextPage = function () {
                     currentPageIndex++;
                     if (currentPageIndex > $scope.productsCart.totalPages) {
                         currentPageIndex = $scope.productsCart.totalPages;
                     }
                     $scope.loadProducts(currentPageIndex);
      }

      $scope.prevPage = function () {
                     currentPageIndex--;
                     if (currentPageIndex < 1) {
                         currentPageIndex = 1;
                     }
                     $scope.loadProducts(currentPageIndex);
      }
      $scope.incAmount = function(product){
         $http.get(contextPath + 'inc/' + product.id)
                   .then(function successCallback(response) {
                    $scope.loadProducts();
              }, function failCallback(response) {
                    //     alert(response.data.messages);
                });
      }
      $scope.subAmount = function(product){
               $http.get(contextPath + 'sub/' + product.id)
                         .then(function successCallback(response) {
                          $scope.loadProducts();
                    }, function failCallback(response) {
                          //     alert(response.data.messages);
                      });
            }
   $scope.buyProductAll = function(){
    //  $http.get(contextPath + 'balance/' + $scope.productsCart.content.userId)
      $http.get(contextPath + 'balance/' + 1)
           .then(function successCallback(response) {
           if (response.data < itog){
              alert("Для оплаты не хватает средств.")
           }else{
              $http.get(contextPath + 'buy/' + 1 + ',' + itog)
                        .then(function successCallback(response) {
                        alert("Поздравляем. Вы совершили покупку!");
                        }, function failCallback(response) {
                                alert(alert("Покупка не прошла."));
                                               });
           }

           }, function failCallback(response) {
                  alert("Не читается баланс покупателя."+response.data);
                                  });
           }


      $scope.loadProducts();

});