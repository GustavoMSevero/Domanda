app.controller("LoginCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	$scope.admin = {};
	//$scope.name = localStorage.setItem("nome", response.nome);

	$scope.cadastrar = function(admin){
		var local = location.hostname;
		var opcao = 1; // Cadastro de estabelecimento
		admin.opcao = opcao;

		$http.post("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/estabelecimento.php", admin).success(function(data){
		//$http.post("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/estabelecimento.php", admin).success(function(data){
		 	//console.log(data);
		 	$scope.admin = {};
		})

	}
	
	$scope.logar = function(admin){

		var opcao = 2; // Logar de estabelecimento
		admin.opcao = opcao;

		$http.post("http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/estabelecimento.php", admin).success(function(response){
		//$http.post("http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/estabelecimento.php", admin).success(function(response){
			//console.log(response)
			if(response == '' || response == [] || response == 'null'){
				$scope.msgErro = "E-mail ou senha inválido";
				return;

			}else if(typeof(Storage) !== "undefined") {

				$scope.idestabelecimento = $rootScope.idestabelecimento = response.idestabelecimento;
				$scope.nome = $rootScope.nome = response.nome;
				$scope.name = $rootScope.nome = response.nome;

				localStorage.setItem("idestabelecimento", response.idestabelecimento);
				localStorage.setItem("nome", response.nome);

				$scope.name = localStorage.setItem("nome", response.nome);
				
				if(response.tipo == "Restaurante"){
					//console.log(response.tipo)
					$location.path('/inicialRestaurantes');
				}

				if(response.tipo == "Cabeleireiro"){
					//console.log(response.tipo)
					$location.path('/inicialCabeleireiros');
				}
	
			}


		})

		
	}


	
}]);