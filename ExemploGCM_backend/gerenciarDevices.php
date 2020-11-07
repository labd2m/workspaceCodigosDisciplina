<?php
	include_once 'acesso_bd.php';
	
	/*
	SCRIPT DE CRIAÇÃO DO BANCO CHAMADO "fornut_android_teste_gcm"
	
	CREATE TABLE device (
		gcmDeviceID VARCHAR(500) NOT NULL,
		nomeDevice VARCHAR(100) NOT NULL,
		PRIMARY KEY(gcmDeviceID)
	)
	TYPE=InnoDB;	
	*/
		
	function registrar($gcmDeviceID, $nomeDevice){
			conecta();
			$resultado = insere("device", "gcmDeviceID, nomeDevice", "'".$gcmDeviceID."','".$nomeDevice."'");
			if($resultado == true)
				echo("ok");
			else
				echo("erro");
	}
	
	function cancelaRegistro($gcmDeviceID){
			conecta();
			$resultado = remove("device", "gcmDeviceID = '".$gcmDeviceID."'");
			if($resultado == true)
				echo("ok");
			else
				echo("erro");
	}
	
	//registra ou cancela registro de devices no server
	if( ($_POST["gcm_id"] != "" && $_POST["cadastrar"] == "NAO")){
		 cancelaRegistro($_POST["gcm_id"]);
	}else{
	
		if( ($_POST["gcm_id"] != "" && $_POST["cadastrar"] == "SIM" && $_POST["nome_device"] != "")){
			 registrar($_POST["gcm_id"], $_POST["nome_device"]);
		}
	}
	
?>
