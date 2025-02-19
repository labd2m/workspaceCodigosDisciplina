<?php
	include_once 'acesso_bd.php';
	include_once 'GCM.class.php';
	/*
	http://android.fornut.com.br/gcm/teste.php
	
	SCRIPT DE CRIAÇÃO DO BANCO CHAMADO "fornut_android_teste_gcm"
	
	CREATE TABLE device (
		gcmDeviceID VARCHAR(500) NOT NULL,
		nomeDevice VARCHAR(100) NOT NULL,
		PRIMARY KEY(gcmDeviceID)
	)
	TYPE=InnoDB;	
	*/
	
	function montaOpcoes(){
		conecta();
		$resultado = seleciona("device", "gcmDeviceID, nomeDevice", "", "");
		
		for($i=0; $i < mysql_num_rows($resultado);$i++){
			$vetor = mysql_fetch_array($resultado);
			$gcmDeviceID = $vetor["gcmDeviceID"];
			$nomeDevice = $vetor["nomeDevice"];	
			echo("<option value='".$gcmDeviceID."'>".$nomeDevice."</option>");
		}
	}
		
	//ENVIA MENSAGEM PARA O SERVIDOR DO GOOGLE ENDEREÇADA PARA UM DEVICE ESPECÍFICO
	if( ($_POST["mensagem"] != "" && $_POST["aparelho"] != "")){
		 $gcm = new GCM();
		 $gcm->enviaMsgDevice($_POST["mensagem"], $_POST["aparelho"]);
	}
	
?>

<html>
	<head>
		<title>Gerenciar mensagens</title>
	</head>
	<body>
		<form method="POST" action="teste.php">
			MENSAGEM: <input type="text" name="mensagem"/><br>
			APARELHO: <select name="aparelho">
						<option value="">ESCOLHA UM APARELHO...</option>
						<?php
							montaOpcoes();
						?>
					  </select><br>
					  <input type="submit" value="ENVIAR"/>
		</form>
	</body>
</html>