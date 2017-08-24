app.controller("InicialCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	//localStorage.getItem('idestabelecimento');
	$rootScope.idestabelecimento = localStorage.getItem('idestabelecimento');
	//console.log($rootScope.idestabelecimento)
	
	$scope.cia = {};
	$scope.cia.cep;
	$scope.msgSucesso;

	$scope.pegaCep =  function(){
		if(!$scope.cia.cep){
	    	return;
	    }
	    var cep = $scope.cia.cep;

	    if(cep.length < 8){
	    	return;
	    }
	    $http.get("https://viacep.com.br/ws/"+$scope.cia.cep+"/json/").success(function (data){
	    	if(data.erro == true){
	    		alert("CEP inexistente!");
	    	}
	    	$http.get("https://viacep.com.br/ws/"+$scope.cia.cep+"/json/").success(function (cia){
				$scope.cia = cia;
			});
	    });

	}

	$scope.$watch('cia.cep', $scope.pegaCep);

	$scope.register = function(cia){
		cia.idestabelecimento = $scope.idestabelecimento;
		cia.opcao = 3;

		//console.log(cia)
		
		$http.post("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/estabelecimento.php", cia).success(function(data){
		//$http.post("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/estabelecimento.php", cia).success(function(data){
			$scope.cia = {};
			$scope.msgSucesso = data.msgRegisterSuccess;
			getAddress();
		})
	}

	var getAddress = function(){
		idestabel = $scope.idestabelecimento;
		var opcao = 5; //Buscar Endereço de cadastro
		$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/estabelecimento.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
		//$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/estabelecimento.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
			$scope.enderecos = response;
		})
	}

	getAddress();
	
}]);



