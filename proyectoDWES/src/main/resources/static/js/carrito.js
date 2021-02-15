function anadirUnidad(index,incremento){
	index+=1;
	
	var cantidad = document.getElementsByTagName('tr')[index].getElementsByTagName("td")[4];
	if(incremento=="-1" && cantidad.textContent=="0"){
		incremento=0;
	}
	cantidad.textContent= String( Number(cantidad.textContent)+incremento);
}

function guardarCambios(){
	
	var trs =document.getElementsByTagName("tr");
	var cantidades= new Array();
	var idProductos= new Array();
	
	for(var i=1;i<trs.length-1;i++){
		cantidades[i]=trs[i].getElementsByTagName("td")[4].textContent;
		document.getElementById('cantidades').value+=cantidades[i]+",";
		idProductos[i]=trs[i].getElementsByTagName("td")[6].textContent;
		document.getElementById('idProductos').value+=idProductos[i]+",";
	}
		
	
	
	console.log(document.getElementById('idProductos').value);
	console.log(document.getElementById('cantidades').value);
	document.getElementById('formCarrito').submit();
}

function eliminarProducto(index){
	console.log("PRUEBA");
	document.getElementsByTagName('tbody')[0].getElementsByTagName('tr')[index].getElementsByTagName('td')[4].textContent="0";
	document.getElementsByTagName('tbody')[0].getElementsByTagName('tr')[index].style.display="none";
}

function comprar(){
	var productos="";
		
	for(var i=1; i<document.getElementsByTagName('tr').length-1;i++){
		productos+= document.getElementsByTagName('tr')[i].lastElementChild.textContent + ",";
		productos+= document.getElementsByTagName('tr')[i].getElementsByTagName("td")[4].textContent+";";
	}

	productos+="-1,"+document.getElementsByTagName('tbody')[0].lastElementChild.lastElementChild.textContent.split('€')[0];
	window.location="/comprar?productos="+productos;
}

function actualizarTotal(){
	var precioTotal=0;
	
	for(var i=1; i<document.getElementsByTagName('tr').length;i++){
		precioStr=document.getElementsByTagName('tr')[i].getElementsByTagName("td")[3].textContent;
		precioTotal+= Number(precioStr.split("€")[0]);
	}
	console.log(precioTotal);
	document.getElementById('total').textContent=precioTotal;
}