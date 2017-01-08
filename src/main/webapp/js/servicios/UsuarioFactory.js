angular.module('chat').factory('UsuarioResource', function($resource){
    var resource = $resource('rest/usuario/:UsuarioId',
    	{UsuarioId:'@id'},
    	{
    		'queryAll':{method:'GET',isArray:true},
    		'query':{method:'GET',isArray:false},
    		'update':{method:'PUT'}
    	});
    return resource;
});