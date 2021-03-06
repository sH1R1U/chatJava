angular.module('chat').controller('NewUsuarioController', function ($scope, $location, locationParser, flash, UsuarioResource , $rootScope, $interval, $log) {

  
  $scope.save = function() {
    $scope.usuario.conectado = 1;
      var successCallback = function(data,responseHeaders){
          var id = locationParser(responseHeaders);
          flash.setMessage({'type':'success','text':'El usuario ha sido creado satisfactoriamente.'});
          $location.path('/Login');
      };
      var errorCallback = function(response) {
          if(response && response.data && response.data.message) {
              flash.setMessage({'type': 'error', 'text': response.data.message}, true);
          } else {
              flash.setMessage({'type': 'error', 'text': 'Ocurrio algún Problema. Reintentar más tarde.'}, true);
          }
      };
      UsuarioResource.save($scope.usuario.conectado,successCallback, errorCallback);
  };
  
  $scope.cancel = function() {
      $location.path("/Login");
  };  
});