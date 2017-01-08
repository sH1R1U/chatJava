angular.module('chat').controller('EditUsuarioController', function($scope, $http, $filter, flash, $location, UsuarioResource, $rootScope) {

    $scope.performSearch = function() {
            
        var successCallback = function(data){
        };

        $scope.searchResults = PerfilResource.queryAll(function(){
            $scope.filteredResults = $filter('searchFilter')($scope.searchResults, $scope);
            $scope.total = $scope.filteredResults.length;
            $scope.currentPage = 1;
        });
    };

    $scope.save = function() {
      var successCallback = function(data,responseHeaders){
          var id = locationParser(responseHeaders);
          flash.setMessage({'type':'success','text':'El usuario ha sido editado satisfactoriamente.'});
          $location.path('/Login');
      };
      var errorCallback = function(response) {
          if(response && response.data && response.data.message) {
              flash.setMessage({'type': 'error', 'text': response.data.message}, true);
          } else {
              flash.setMessage({'type': 'error', 'text': 'Ocurrio algún Problema. Reintentar más tarde.'}, true);
          }
      };
      UsuarioResource.save(successCallback, errorCallback);
  };

    $scope.performSearch();

});