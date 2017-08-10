app.controller("MesasCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	$rootScope.idestabelecimento = localStorage.getItem('idestabelecimento');
	$scope.idestabel;
	$scope.msgSuccess;
	$scope.mesa = {};

	//console.log($rootScope.idestabelecimento)
	var getCia = function(){
		var idestabelecimento = localStorage.getItem('idestabelecimento');
		var opcao = 1; //Buscar estabelecimento
		//console.log('id '+idestabelecimento+'- opcao '+opcao)
		//$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/mesa.php?opcao="+opcao+"&idestabel="+idestabelecimento).success(function(response){
		$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/unidade.php?opcao="+opcao+"&idestabel="+idestabelecimento).success(function(response){
			$scope.cia = response;

		})
	}

	getCia();

	// $scope.getUnidade = function(mesa){

	// 	$scope.idestabel = mesa.idestabelecimento;

		var buscarUnidadePorEstabelecimento = function(){

			var idestabel = localStorage.getItem('idestabelecimento');//$scope.idestabel;
			var opcao = 2; // Buscar unidade por estabelecimento
			//console.log('idestabel: '+idestabel+' opcao: '+opcao)
			//$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/mesa.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
			$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/mesa.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
				$scope.unidades = response;
			})
			
		}

		buscarUnidadePorEstabelecimento();
	//}

	$scope.adicionar = function(mesa){

		$scope.idunidade = mesa.unidade;
		var idestabel = $scope.idestabel;
		mesa.idestabel = idestabel;
		mesa.opcao = 3; // Cadastra mesa
		
		//$http.post("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/mesa.php", mesa).success(function(data){
		$http.post("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/mesa.php", mesa).success(function(data){
			$scope.msgSuccess = data.msgSuccess;
			$scope.mesa = {};
		})

	}

	// var getMesa = function(){
	// 	var idunidade = $scope.idunidade;
		
	// }
	

	
	
}]);



