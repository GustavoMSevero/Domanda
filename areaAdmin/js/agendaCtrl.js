app.controller("AgendaCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', function ($scope, $http, $window, $location, $rootScope) {

	$rootScope.idestabelecimento = $scope.idestabelecimento = localStorage.getItem('idestabelecimento');

	var buscarTodosProfissionais = function(){
		var idestabel = localStorage.getItem('idestabelecimento');
		var opcao = 1; //Buscar todos os profissionais
		var urlTodos = "http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/agenda.php?opcao="+opcao+"&idestabel="+idestabel;
		//var urlTodos = "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/agenda.php?opcao="+opcao+"&idestabel="+idestabel;

		$http.get(urlTodos).success(function(response){
			//console.log(response)
			$scope.profs = response;
		})
	}

	buscarTodosProfissionais();

	
	
}]);



