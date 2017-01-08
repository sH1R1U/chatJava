angular.module('chat').factory('MensajeResource', function($resource){
    var resource = $resource('rest/mensaje/:MensajeId',
    	{MensajeId:'@id'},
    	{
    		'queryAll':{method:'GET',isArray:true},
    		'query':{method:'GET',isArray:false},
    		'update':{method:'PUT'}
    	});
    return resource;
});