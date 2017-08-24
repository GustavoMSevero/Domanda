app.controller("ProfissionaisCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	$rootScope.idestabelecimento = localStorage.getItem('idestabelecimento');

	var getUnit = function(){
		var idestabel = $rootScope.idestabelecimento;
		var opcao = 1; //Buscar unidades
		$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/profissionais.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
		//$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/profissionais.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
			$scope.unidades = response;
		})
	}

	getUnit();

  	$scope.pro = {};
	$scope.setPro = function(pro){
		var opcao = 2; //Cadastra profissional
		pro.opcao = opcao;
		$http.post("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/profissionais.php", pro).success(function(data){
		//$http.post("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/profissionais.php", pro).success(function(data){
			$scope.pro = {};
			$scope.msgSuccess = data.msgSuccess;
			getPro();
			
		})
	}

	var getPro = function(){
		var idestabel = $rootScope.idestabelecimento;
		var opcao = 3 // Buscar profissionais
		$http.get("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/profissionais.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
		//$http.get("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/profissionais.php?opcao="+opcao+"&idestabel="+idestabel).success(function(response){
			console.log(response)
			$scope.profissionais = response;
		})
	}

	getPro();
	
}]);



