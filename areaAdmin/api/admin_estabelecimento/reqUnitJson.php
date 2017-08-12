<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();


$idestabelecimento = $_GET['idestabelecimento']; //Ex: Restaurante


$getUnitForApp=$pdo->prepare("SELECT * FROM unidade WHERE idestabelecimento=:idestabelecimento");
$getUnitForApp->bindValue(":idestabelecimento", $idestabelecimento);

$getUnitForApp->execute();

$return = array();

while ($linha=$getUnitForApp->fetch(PDO::FETCH_ASSOC)) {

	$idestabelecimento = $linha['idestabelecimento'];
	$idunidade = $linha['idunidade'];
	$unidade = $linha['unidade'];
	$endereco = $linha['endereco'];
	$numero = $linha['numero'];

	$return[] = array(
		'idestabelecimento'	=> $idestabelecimento,
		'idunidade'	=> $idunidade,
		'unidade'	=> $unidade,
		'endereco'	=> $endereco,
		'numero'	=> $numero
	);

}

echo json_encode($return);






?>