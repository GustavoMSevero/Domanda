<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();


$idprofissional = $_GET['idpro'];


$getProForApp=$pdo->prepare("SELECT * FROM profissional WHERE idprofissional=:idprofissional");
$getProForApp->bindValue(":idprofissional", $idprofissional);
$getProForApp->execute();

$return = array();

while ($linha=$getProForApp->fetch(PDO::FETCH_ASSOC)) {

	$idprofissional = $linha['idprofissional'];
	$nome = $linha['nome'];
	$funcao = $linha['funcao'];

	$return[] = array(
		'idprofissional'	=> $idprofissional,
		'nome'	=> $nome,
		'funcao'	=> $funcao
	);

}


echo json_encode($return);

?>