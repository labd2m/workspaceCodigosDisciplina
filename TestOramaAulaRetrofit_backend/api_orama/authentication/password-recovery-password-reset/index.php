<?php
	include_once "../../classes/passwordRecoveryPasswordReset.class.php";

	//HACK PARA COMPENSAR PROBLEMA COM POST NO POSTMAN
	/*$_POST["new_password"] = "";
	$_POST["new_password_confirmation"] = "";
	$_POST["recovery_authorization"] = "";*/
	
	//echo json_encode(array($_POST['username'],$_POST['birth_date']));
	
	if ($_POST["new_password"] == "111111" && $_POST["new_password_confirmation"] == "111111" && $_POST["recovery_authorization"] == "d0e5e4b347110d2ab4eae0b858adda3517ef40b2"){
		$pr = new passwordRecoveryPasswordReset();
		
		//OK----200
		$pr->success = true;
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($pr);
		
		//Recovery autorization em branco----400
		/*$pr->recovery_authorization = array("Este campo não pode ser em branco.");
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//Campos de senha em branco----400
		/*$pr->new_password = array("Este campo não pode ser em branco.");
		$pr->new_password_confirmation = array("Este campo não pode ser em branco.");
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		
		//Formato da senha inválido----400
		/*$pr->validation_type = "invalid_password_format";
		$pr->validation_message = "Formato de senha inválido.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//Formato da senha inválido----400 -- sem objeto completo
		/*header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode(array("validation_field" => null, "validation_type" => "invalid_password_format", "validation_message" => "Formato de senha inválido." ));*/
		
		
		//Código de recuperação expirado----400
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