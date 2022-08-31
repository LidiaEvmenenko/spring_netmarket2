angular.module('app-front').controller('storeController', function ($routeParams, $scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

let pageElements=0;
let currentPageIndex = 1;
let categoryTitle = null;
let currentLengthPage=1;
let categoryAll = "Все продукты";

var objTable = document.getElementById("tableProduct");
//var objNav = document.getElementById("navStore");


  $scope.init = function(){
     objTable.style.display = "none";
  //   objNav.style.visibility = "hidden";
  }

  $scope.init();

 $scope.loadProducts = function(pageIndex=1){

     currentPageIndex = pageIndex;
      $http({
         url: contextPath + 'products',
         method: 'GET',
         params: {
          p: pageIndex
         }
      }).then(function(response) {
     if (response.data.length != 0) {
                     let n = 1;
                     for(let i = 0; i < response.data.productsViewList.length; i++){
                        response.data.productsViewList[i].nom = n;  n++;
                     }

                     }
         $scope.productsPage = response.data.productsViewList;

         $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);


           if (response.data.productsViewList.length != 0) {
              objTable.style.display = "table";
          //    objNav.style.visibility = "visible";
           }else {
              objTable.style.display = "none";
           //   objNav.style.visibility = "hidden";
                       }
      });
      }

           $scope.loadProductsPage = function(pageIndex) {

           if(categoryTitle == categoryAll) {
                 $scope.loadProducts();
                 return;
              }
              $scope.loadProductsByCategory(pageIndex, categoryTitle);
           }

  $scope.loadProductsByCategory = function(pageIndex = 1, title){
  categoryTitle = title;
   if(title == categoryAll) {
      $scope.loadProducts();
      return;
   }

       currentPageIndex = pageIndex;
        $http({
           url: contextPath + 'category/page',
           method: 'GET',
           params: {
            p: pageIndex,
            title: title
           }
        }).then(function(response) {
        console.log(response.data);
        if (response.data.totalElements != 0) {
           let n = (currentPageIndex - 1) * 5;
           response.data.content[0].nom = n;
           for(let i = 0; i < response.data.content.length; i++){
               n++;
               response.data.content[i].nom = n;
           }
           currentLengthPage = response.data.content.length;
        }

           $scope.productsPage = response.data;

           $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);

           if (response.data.totalElements != 0) {
              objTable.style.display = "table";
           //   objNav.style.visibility = "visible";
           }else {
              objTable.style.display = "none";
           //   objNav.style.visibility = "hidden";
           }
        });
        }

 $scope.deleteProduct = function (product) {
     $http.delete(contextPath + 'products/'+ product.id)
                 .then(function successCallback(response) {

                    //  if(categoryTitle == categoryAll) {
                         $scope.loadProducts();
//                         return;
//                      }

                     }, function failCallback(response) {
                          alert(response.data.messages);
                 });
    }


 $scope.navToEditProductPage = function (productId) {
        $location.path('edit_product/' + productId);

     }

 $scope.showInfo=function(product){
    alert("Категория: " + product.categoryTitle);

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
                if (currentPageIndex > $scope.productsPage.totalPages) {
                    currentPageIndex = $scope.productsPage.totalPages;
                }
                if(categoryTitle == categoryAll) {
                      $scope.loadProducts();
                      return;
                   }

 }

 $scope.prevPage = function () {
                currentPageIndex--;
                if (currentPageIndex < 1) {
                    currentPageIndex = 1;
                }
                if(categoryTitle == categoryAll) {
                                      $scope.loadProducts();
                                      return;
                                   }
             //   $scope.loadProducts(currentPageIndex);
 }
  $scope.addCartProduct = function(product) {

            $http.post(contextPath + 'cart/' + product.id +','+product.count)
             .then(function successCallback(response) {
                     alert("Продукт добавлен в корзину");
                 }, function failCallback(response) {
                     alert(response.data.messages);
                   //  $location.path('store');
                 }
             );
      }
         $scope.loadCategory = function(){
                $http({
                   url: contextPath + 'category',
                   method: 'GET',

                }).then(function(response) {
           $scope.category = response.data.categoryViews;
console.log( $scope.category);

                });
                }
      $scope.loadCategory();



});