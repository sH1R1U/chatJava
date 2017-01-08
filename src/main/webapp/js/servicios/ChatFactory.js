angular.module('chat').factory('ChatResource', function($resource){
    var resource = $resource('rest/chat/:ChatId',
    	{ChatId:'@id'},
    	{
    		'queryAll':{method:'GET',isArray:true},
    		'query':{method:'GET',isArray:false},
    		'update':{method:'PUT'}
    	});
    return resource;
});