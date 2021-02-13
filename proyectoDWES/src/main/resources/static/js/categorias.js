function anadirCarrito(id,pos){
	var path='/anadirProductos';
	path+='?id='+id;
	var cant =Number(document.getElementsByName('cantidad')[pos].value);
	
	if(Number(document.getElementsByTagName('tr')[pos].getElementsByTagName('td')[4].textContent)<cant){
		alert('No hay tantas unidades');
		cant=0;
	}else{
		alert('Se han añadido al carrito '+cant+' unidades');	
	}
	

	path+='&cantidad='+cant;
	window.location.href=path;
}