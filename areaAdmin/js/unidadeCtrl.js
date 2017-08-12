app.controller("UnidadeCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	$rootScope.idestabelecimento = localStorage.getItem('idestabelecimento');
	//console.log($rootScope.idestabelecimento)
	$scope.nome;

	//Busca estabelecimento para cadastrar unidade
	var buscarEstabeleciento = function(){
		var idestabelecimento = $rootScope.idestabelecimento;
		var opcao = 4; //Buscar Estabelecimento;
		$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/estabelecimento.php?idestabelecimento="+idestabelecimento+"&opcao="+opcao).success(function(response){
		//$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/estabelecimento.php?idestabelecimento="+idestabelecimento+"&opcao="+opcao).success(function(response){
			$scope.estabele = response;
			$scope.nome = response[0].nome;
		})
	}

	buscarEstabeleciento();

	$scope.pegaCep =  function(){
		if(!$scope.unidade.cep){
	    	return;
	    }
	    var cep = $scope.unidade.cep;

	    if(cep.length < 8){
	    	return;
	    }
	    $http.get("https://viacep.com.br/ws/"+$scope.unidade.cep+"/json/").success(function (data){
	    	if(data.erro == true){
	    		alert("CEP inexistente!");
	    	}
	    	$http.get("https://viacep.com.br/ws/"+$scope.unidade.cep+"/json/").success(function (unidade){
				$scope.unidade = unidade;
			});
	    });

	}

	$scope.$watch('unidade.cep', $scope.pegaCep);

	$scope.unidade = {};
	$scope.adicionar = function(unidade){
		var opcao = 1; //Cadastro de unidade
		unidade.idestabelecimento = $rootScope.idestabelecimento;
		unidade.opcao = opcao;
		//$http.post("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/unidade.php", unidade).success(function(data){
		$http.post("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/unidade.php", unidade).success(function(data){
			$scope.unidade = {};
			$scope.msgSuccess = data.msgRegisterSuccess;
			exibeUnidades();
		})
	}

	var exibeUnidades = function(){
		var idestabel = localStorage.getItem('idestabelecimento');
		var opcao = 2 // Buscar Unidades
		//console.log(idestabel)
		//$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/unidade.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
		$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/unidade.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
			//console.log(response)
			$scope.unidades = response;
		})
	}

	exibeUnidades();
	
}]);



