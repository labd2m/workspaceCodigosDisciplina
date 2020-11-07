<?php
	include_once "../../classes/passwordRecoveryNotification.class.php";

	//HACK PARA COMPENSAR PROBLEMA COM POST NO POSTMAN
	//$_POST["recovery_notification_authorization"] = "27ccdbe61b61249ba81e64604e358ef0da0347bb";
	//$_POST["notification_channel"] = "2";

	
	//echo json_encode(array($_POST['username'],$_POST['birth_date']));
	
	//padrão do exemplo API está no POST comentado acima
	if ($_POST["recovery_notification_authorization"] == "d1b2fd8c5ef5acedbd63779cbdebae17e12593f4" && $_POST["notification_channel"] == "2"){
		$pr = new passwordRecoveryNotification();
		
		//OK----200
		$pr->success = true;
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($pr);
		
		//recovery_notification_authorization não informado----400
		/*$pr->recovery_notification_authorization = array("Este campo não pode ser em branco.");
		$pr->notification_channel = array("\"\" não é um escolha válido.");
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//Codigo de recuperação expirado----400
		/*$pr->validation_type = "authentication_method_recovery_code_has_expired";
		$pr->validation_message = "Código para recuperação de acesso expirado.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
	}
	else{
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(500);
		echo json_encode(array("erro" => "usuario inexistente"));
	}
?>