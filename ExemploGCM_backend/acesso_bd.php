<?php

/************** Função de conexão ao banco de dados **************/
 function conecta(){
	$conexao = mysql_connect("localhost", "fornut_gcm", "123456");
	mysql_select_db("fornut_android_teste_gcm");
	return $conexao;
}

/************** Função de pesquisa em tabelas do banco de dados **************/
 function seleciona ($Tabela, $Campos, $Restricao, $Ordem ){
	   if ($Restricao == ""){
			$Sentenca_sql = "select ".$Campos." from ".$Tabela." ".$Ordem;
	   }
	   else{
			$Sentenca_sql = "select ".$Campos." from ".$Tabela." where ".$Restricao." ".$Ordem;
	   }
	   $Query = mysql_query($Sentenca_sql);
	   return $Query;
  }

/************** Função de inserção de dados em tabelas do banco **************/
 function insere ($Tabela, $Campos, $Valores){
		$Sentenca_sql = "insert into ".$Tabela."(".$Campos.")"." values(".$Valores.")";
		$Query = mysql_query($Sentenca_sql);
		return $Query;
 }
 
 /************** Função de remoção de dados em tabelas do banco **************/
 function remove ($Tabela, $Restricao){
		$Sentenca_sql = "delete from ".$Tabela." where ".$Restricao;
		$Query = mysql_query($Sentenca_sql);
		return $Query;
 }
 
  /************** Função de alteração de dados em tabelas do banco **************/
 function altera ($Tabela, $Campo, $Valor ,$Restricao){
		$Sentenca_sql = "update ".$Tabela." set ".$Campo." = ".$Valor." where ".$Restricao;
		$Query = mysql_query($Sentenca_sql);
		return $Query;
 }
?>