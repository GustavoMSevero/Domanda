<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();

$data = file_get_contents("php://input");
$data = json_decode($data);

if($data){
	$opcao = $data->opcao;
}else{
	$opcao = $_GET['opcao'];
}



switch ($opcao) {

	case 1: //Buscar estabelecimento

		$idestabelecimento = $_GET['idestabel'];
		
		$getCia=$pdo->prepare("SELECT * FROM estabelecimento WHERE idestabelecimento=:idestabelecimento");
		$getCia->bindValue(":idestabelecimento", $idestabelecimento);
		$getCia->execute();

		while ($linha=$getCia->fetch(PDO::FETCH_ASSOC)) {

			$idestabelecimento = $linha['idestabelecimento'];
			$nome = $linha['nome'];

			$return[] = array(
				'idestabelecimento'	=> $idestabelecimento,
				'nome'	=> $nome
			);

		}

		echo json_encode($return);

		break;

	case 2: //Buscar unidade por estabelecimento

		$idestabelecimento = $_GET['idestabel'];

		$getUnitByCia=$pdo->prepare("SELECT * FROM unidade WHERE idestabelecimento=:idestabelecimento");
		$getUnitByCia->bindValue(":idestabelecimento", $idestabelecimento);
		$getUnitByCia->execute();

		while ($linha=$getUnitByCia->fetch(PDO::FETCH_ASSOC)) {

			$idunidade = $linha['idunidade'];
			$unidade = $linha['unidade'];

			$return[] = array(
				'idunidade'	=> $idunidade,
				'unidade'	=> $unidade
			);

		}

		echo json_encode($return);

		break;

	case 3: //Cadastra Mesa
		
		$idunidade = $data->unidade;
		$numeroMesa = $data->numero;

		$return = array();

		$getUnitByCia=$pdo->prepare("INSERT INTO mesa (idmesa, idunidade, mesa) VALUES (?, ?, ?)");
		$getUnitByCia->bindValue(1, NULL);
		$getUnitByCia->bindValue(2, $idunidade);
		$getUnitByCia->bindValue(3, $numeroMesa);
		$getUnitByCia->execute();

		$return = array(
				'msgSuccess' => "Mesa adicionada com sucesso!"
			);

		echo json_encode($return);

		break;
	
	default:
		# code...
		break;
}




?>