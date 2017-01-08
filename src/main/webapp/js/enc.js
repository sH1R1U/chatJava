/* 
 * @author	Ricardo Ramirez F.
 * @version	1.0.0
 * 
 * Función para encontrar una coincidencia de un valor en un array.
 * 
 * @param Array obj: Array con datos
 */

Array.prototype.in_array = function ( obj ) {
		var len = this.length;
		for ( var x = 0 ; x <= len ; x++ ) {
			if ( this[x] == obj ) return true;
		}
		return false;
	}   

/*
 * @author Patricio Cifuentes Ithal
	 * @since 20-05-2008
	 * @version 1.0.2
	 *
 * Protitpo que retorna el indice de un arreglo segun un valor
 *
 * @param mixed $obj	:	arreglo de entrada
 * @return mixed
 *
 */
Array.prototype.array_key = function ( obj ) {
		var len = this.length;
		for ( var x = 0 ; x <= len ; x++ ) {
			if ( this[x] == obj ) return x;
		}
		return false;
	}


/* 
 * @author	Ricardo Ramirez F.
 * @version	1.0.0
 * 
 * Función para encontrar una coincidencia de un valor en un Objeto.
 * 
 * @param Integer value : valor a encontrar
 * @param Objeto obj : objeto con los datos
 * @param String col : columna a buscar
 */
$.inObject = function(value, obj, col) {
	   		var est = false;
		   $.each(obj, function(key, val) {

		        if (value == val[col]) {
		        	est = true;
		            return;
		        }
		    });
		   
		    return est;
	};
/*
 * @author	Ricardo Ramirez F.
 * @since	18-02-2016
 * @version	1.0.0
 *
 * Funcion que lista las constantes Globales y de la clase desea ocupar
 *
 * @param String par_nom_cla : Nombre de la clase que decea listar las constante
 */
$.ListarConstantes = function($scope,$http,par_nom_cla){
	
	if(par_nom_cla!=null){
		par_nom_cla = "'"+par_nom_cla+"'";
	}
	
	$.ajax({
		  url: "rest/siscons/listar",
		  type: 'GET',
		  async:false,
		  data: par_nom_cla,
		  success: function(data) {
			  angular.forEach(data, function(val,nom){
		    		$scope[nom]=val;
		    	});
		  },
		  error: function(e) {
			  console.log(e);
		  }
		});

};
/*
 * @author	Ricardo Ramirez F.
 * @since	18-02-2016
 * @version	1.0.0
 *
 * funcion para generar las constante desde un listado para ocupar en angular
 *
 */
$.generalConstantesListado = function($scope,lisCont){
	angular.forEach(lisCont, function(val,nom){
		$scope[nom]=val;
	});
};

/*
 * @author		Ricardo Ramirez F.
 * @since		  24-08-2015
 * @version		1.3.0
 *
 * Funcion que permite validar los caracteres
 * 
 * @param Event $_evet = Event para obtener el caracter 
 * @param Int $tip = 1->numero, 2->letras, 3->letras, numeros ._- 4->numero, -+ 5->numero y k
 * @param Objeto $_this = Objeto del input
 */
	function enc_validarCaracter(_evet,tip,_this) {
   
    key = _evet.keyCode || _evet.which;
    tecla = String.fromCharCode(key).toLowerCase();
   
    //numero
    if(tip==1){
      var enc_valida = tecla.match(/^[0-9]+$/);
      if(enc_valida == null){
        return false;
      }
    //letras    
    }else if(tip==2){
      var enc_valida = tecla.match(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/);
      if(enc_valida == null){
        return false;
      }
      //letras, numeros ._-
    }else if(tip==3){
      var enc_valida = tecla.match(/^[0-9a-zA-Z._-\s]+$/);
      if(enc_valida == null){
        return false;
      }
      //numero, -+ 
    }else if(tip==4){
      var enc_valida = tecla.match(/^[0-9-.\s]+$/);
      if(enc_noRepetirCaracter($(_this).val(),".",tecla)==false){
        return false;
      }
      if(enc_noRepetirCaracter($(_this).val(),"-",tecla) == false){
        return false;
      }
      
      if(enc_valida == null){
        return false;
      }
      //numero y k
    }else if(tip==5){
      var enc_valida = tecla.match(/^[0-9Kk\s]+$/);
      if(enc_valida == null){
        return false;
      }
    }

	}
  /*
 * @author		Ricardo Ramirez F.
 * @since		  24-08-2015
 * @version		1.3.0
 *
 * Funcion que permite no repetir un caracter
 * 
 * @param String $_cadena = cadena con el texto
 * @param String $val = caracter que no se repita
 */
  function enc_noRepetirCaracter(_cadena,val,tec){
    var car_no_per=0;  
      for (var i=0; i<_cadena.length; i++){
          if(_cadena[i]==val){ car_no_per++; }
        }

      if(car_no_per > 0 && val == tec){  return false;  }
  }
  /*17-03-2016*/
  function enc_setReproyectar(GeoStr,SisRefOri,SisRefDes){
	  
	  var res_reproyeccion;
	  
	  GeoStr = Base64.encode(GeoStr);
	  
	  $.ajax({
		  url: "rest/geotabs/setCoordenadas",
		  type: 'GET',
		  async:false,
		  data: {Geo:GeoStr,
				SisRefOri:SisRefOri,
				SisRefDes:SisRefDes},
		  success: function(data) {
			  res_reproyeccion = Base64.decode(data);
			  
		  },
		  error: function(e) {
			  console.log(e);
		  }
		});
	  
	  return res_reproyeccion;
  }

  
  
  function groupArray(array1,groupkey){
	  if (array1.length > 0){
  	
	  	  var keys = Object.keys(array1[0]);

	      var removekey = keys.array_key(groupkey);

	      if (removekey === false){
	      		return Array("Clave "+ groupkey+" no existe");
	      }else{

	         delete keys[removekey];
	      	 var groupcriteria = Array();
	  		 var return1 = Array();

	         $.each(array1, function(index, value){
	        	 
	           	var item=[];
	            $.each(keys, function(index2, value2){
	            	if(typeof(value2)!="undefined"){
	            		item[value2] = value[value2];
	            	}
	            });

	            var busca = groupcriteria.array_key(value[groupkey]);
	            if (busca === false){

	              groupcriteria.push(value[groupkey]);
	              
	              var datos=[];
	              datos[groupkey] = value[groupkey];
	              datos["grupo"] = Array();

	              return1.push(datos);
	              
	              busca =  return1.length-1;
	          
	            }
	         
	            return1[busca]["grupo"].push(item);
	   
	         });
	         
	         return return1;
	      }

	  	

	  }else{
	  	return array();
	  }
	   	
	  }
  
String.prototype.beforeLastIndex = function (delimiter) {
	    return this.substr(0,this.lastIndexOf(delimiter)) || this + "";
	}