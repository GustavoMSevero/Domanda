app.controller("AgendaCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	$rootScope.idestabelecimento = $scope.idestabelecimento = localStorage.getItem('idestabelecimento');

	var buscarTodosProfissionais = function(){
		var idestabel = localStorage.getItem('idestabelecimento');
		var opcao = 1; //Buscar todos os profissionais
		var urlTodos = "http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/agenda.php?opcao="+opcao+"&idestabel="+idestabel;

		$http.get(urlTodos).success(function(response){
			//console.log(response)
			$scope.profs = response;
		})
	}

	buscarTodosProfissionais();

	
	$scope.pro = {};
	$scope.buscar = function(pro){
		console.log(pro)
		//"http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/estabelecimento.php"
		var urlNome = "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/agenda.php?nome=";
		var urlFuncao = "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/agenda.php?funcao=";
		var nome = pro.nome;
		var funcao = pro.funcao;

		if(nome){
			console.log(urlNome+" - "+nome)
			var opcao = "nome";
			//$scope.pro = {};
			// $http.get().success(function(response){

			// });
		}else{
			console.log(urlFuncao+" - "+funcao)
			var opcao = "funcao";
			//$scope.pro = {};
			// $http.get().success(function(response){

			// });
		}
	}

	
	
}]);



