// let contribuyente = [];
let id_empresa;

const changeEmpresas = ()=>{
    $("#empresa").on( "change", (valSeleccion)=> {
        id_empresa = valSeleccion.currentTarget.selectedOptions[0].value;
    });
}

const traerDocumento =()=>{

    var folio = $("#folio").val();
    var urlDest = "/documentos_folio/"+folio+"/"+id_empresa;

    $.ajax({
      type: "GET",
      url: urlDest,
      async: true,
      data: {},
      success: function (data) {
        if( data ){
           // console.log(data);
            poblarCabecera(data);
            poblarDetalle(data.documentoDetalles)
        }
      },
      error: function(err){
        console.log("Error");
        console.log(err);
        alert("Documento no encontrado");
      }
    });
}

const poblarCabecera = (dataCabecera)=>{
    console.log(dataCabecera);
    //console.log(dataCabecera.empresa);
    //console.log(dataCabecera.empresa.rut);

    $("#rut_emisor").text("Rut: "+ dataCabecera.empresa.rut);
    $("#folio_emisor").text("Folio: "+ dataCabecera.folio);
    $("#fecha_doc").text(dataCabecera.fecha_emision);

    $("#cliente_nombre").text(dataCabecera.contribuyente.razonSocial);
    $("#cliente_rut").text("Rut: "+ dataCabecera.contribuyente.rut);
    $("#fecha_venc").text("fecha_venc");

    $("#cliente_giro").text(dataCabecera.contribuyente.giro);
    $("#monto_iva").text("Iva: "+dataCabecera.montoIva.split(".")[0]);
    $("#total_doc").text("Total: "+dataCabecera.monto_total);
}

const limpiarDetalle=()=>{
   try {
       var table = document.getElementById("tbDetalleDocumento");
       var rowCount = table.rows.length;
       for(var i=1; i<rowCount; i++) {
            table.deleteRow(i);
            rowCount--;
            i--;
       }
   }catch(e) {
        alert(e);
   }
}

const poblarDetalle = (dataDetalle)=>{
    console.log("dataDetalle");
    console.log(dataDetalle);

    limpiarDetalle();
    let cnt=1;
    dataDetalle.forEach( (element) => {
        //console.log(element);
        var newRow = document.getElementById("tbDetalleDocumento").insertRow();
        var newCell0 = newRow.insertCell();
        var newCell1 = newRow.insertCell();
        var newCell2 = newRow.insertCell();
        var newCell3 = newRow.insertCell();
        var newCell4 = newRow.insertCell();
        var newCell5 = newRow.insertCell();
        var newCell6 = newRow.insertCell();

        newCell0.appendChild(document.createTextNode(cnt));
        newCell1.appendChild(document.createTextNode(element.cantidad));
        newCell2.appendChild(document.createTextNode(element.unidadMedida));
        newCell3.appendChild(document.createTextNode(element.nombreItem));
        newCell4.appendChild(document.createTextNode(element.montoDescuento));
        newCell5.appendChild(document.createTextNode(element.precioUnitario));
        newCell6.appendChild(document.createTextNode(element.montoItem));

        cnt++;
    });
}


$( function() {
    changeEmpresas();

});
