angular.module('chat').controller('ChatController', function($scope, $http, $filter, flash, $location, UsuarioResource, MensajeResource, ChatResource, $rootScope) {

       $scope.get = function() {
        var successCallback = function(data){
            self.original = data;

            $scope.chat = new ChatResource(self.original);
            
            MensajeResource.queryAll(function(items) {
                $scope.mensajeSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.mensaje
                    };
                    if($scope.chat.mensaje && item.id == $scope.chat.mensaje.id) {
                        $scope.mensajeSelection = labelObject;
                        $scope.chat.mensaje = wrappedObject;
                        self.original.mensaje = $scope.chat.mensaje;
                    }
                    return labelObject;
                });
            });
            
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'No se han podido encontrar datos'});
            $location.path("/Provincias");
        };
        CharResource.get({UsuarioId:$routeParams.ChatId}, successCallback, errorCallback);
    };

    $scope.$watchCollection("mensajeSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.chat) {
            $scope.chat.mensaje = [];
            $scope.requiredCentroResponsabilidad = true;
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;

                if(selectedItem.seleccionado){
                    $scope.chat.mensaje.push(collectionItem); 
                }
            });
        }
    });

    $scope.get();

});