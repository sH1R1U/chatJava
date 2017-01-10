'use strict';

angular.module('chat',['ngRoute','ngResource', 'ngAnimate', 'ui.bootstrap'])
  .config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = true;
  })
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'vistas/login.html',controller:'InicioController'})
      .when('/Usuario/nuevo',{templateUrl:'vistas/nuevoUsuario.html',controller:'NewUsuarioController'})
      .when('/Usuario/editar/:UsuarioId',{templateUrl:'vistas/editarUsuario.html',controller:'EditUsuarioController'})
      .when('/Chat/:UsuarioId',{templateUrl:'vistas/chat.html',controller:'ChatController'})
       
      .otherwise({
      redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController($rootScope) {
      $rootScope.mostrarPagina = false;
  })
  .controller('NavController', function NavController($scope, $location, $rootScope) {
    $rootScope.mostrarPagina = false;
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
