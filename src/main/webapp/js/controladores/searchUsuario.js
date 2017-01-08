angular.module('chat').controller('SearchUsuarioController', function($scope, $http, $filter, flash, $location, UsuarioResource, $rootScope) {

    $scope.performSearch = function() {
            
        var successCallback = function(data){
        };

        $scope.searchResults = PerfilResource.queryAll(function(){
            $scope.filteredResults = $filter('searchFilter')($scope.searchResults, $scope);
            $scope.total = $scope.filteredResults.length;
            $scope.currentPage = 1;
        });
    };

    $scope.performSearch();

});