<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();

$opcao = 1;

switch ($opcao) {

	case 1: // Buscar Estabelecimentos para o app

		$tipo = $_GET['tipo']; //Ex: Restaurante


		$getCiaForApp=$pdo->prepare("SELECT * FROM estabelecimento WHERE tipo=:tipo");
		$getCiaForApp->bindValue(":tipo", $tipo);

		$getCiaForApp->execute();

		$return = array();

		while ($linha=$getCiaForApp->fetch(PDO::FETCH_ASSOC)) {

			$idestabelecimento = $linha['idestabelecimento'];
			$nome = $linha['nome'];

			$getCiaAddress=$pdo->prepare("SELECT * FROM endereco WHERE idcliente=:idestabelecimento");
			$getCiaAddress->bindValue(":idestabelecimento", $idestabelecimento);

			$getCiaAddress->execute();

			while ($linha=$getCiaAddress->fetch(PDO::FETCH_ASSOC)) {

				$idestabelecimento = $linha['idcliente'];
				$end = $linha['endereco'];
				$num = $linha['numero'];
				$cid = $linha['cidade'];
				$uf = $linha['uf'];

				$return[] = array(
					'idestabelecimento'	=> $idestabelecimento,
					'nome'	=> $nome,
					'end'	=> $end,
					'num'	=> $num,
					'cid'	=> $cid,
					'uf'	=> $uf
				);

			}

		}

		echo json_encode($return);

		break;
	
	default:
		# code...
		break;
}




?>