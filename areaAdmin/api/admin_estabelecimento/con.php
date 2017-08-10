<?php
ini_set('display_errors', true);
error_reporting(E_ALL);


function conectar(){
	
	try {
		$opcoes = array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION);
		//echo $local = $_SERVER['SERVER_NAME'];
		//if($local == "localhost"){
			$pdo = new PDO("mysql:host=localhost;dbname=domanda;", "root", "root", $opcoes);
		//}else{
			// $pdo = new PDO("mysql:host=mysql.reservacomdomanda.com;dbname=reservacomdoma;", "reservacomdoma", 
			//  	"Domanda1976", $opcoes);
		//}

	} catch (Exception $e) {
		echo $e->getMessage();
	}

	return $pdo;

}

conectar();

?>