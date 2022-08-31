(function () {
    angular
        .module('app-front', ['ngRoute','ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',

                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartProductController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authProductController'
            })
            .when('/stock', {
                templateUrl: 'stock/stock.html',
                controller: 'stockController'
            })
            .when('/statistic', {
                templateUrl: 'statistic/statistic.html',
                controller: 'statisticController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {//если в локальном хранилище есть юзер, то он восстанавливается при входе
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }
})();

angular.module('app-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

var aaa = document.getElementById("userName");

   $scope.tryToAuth = function () {

        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
            console.log("response.data.token = "+response.data.token);

                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    aaa.value = $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                  //  alert("Вошли");


                }
            }, function errorCallback(response) {
             alert(response.data.messages);
            });
    };

    $scope.tryToLogout = function () {
console.log("$scope.user = "+$scope.user);

        $scope.clearUser();
        aaa.value = '';
        $location.path('welcome');
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };
});
