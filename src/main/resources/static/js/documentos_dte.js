// let contribuyente = [];
let id_empresa;
let id_doc;
let sw_cambio_folio=0;


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
    $("#floatingInput").val(dataCabecera.folio);
    $("#fecha_doc").text(dataCabecera.fecha_emision);

    $("#cliente_nombre").text(dataCabecera.contribuyente.razonSocial);
    $("#cliente_rut").text("Rut: "+ dataCabecera.contribuyente.rut);
    $("#fecha_venc").text("fecha_venc");

    $("#cliente_giro").text(dataCabecera.contribuyente.giro);
    $("#monto_iva").text("Iva: "+dataCabecera.montoIva.split(".")[0]);
    $("#total_doc").text("Total: "+dataCabecera.monto_total);
    $("#estado_doc").text("Estado: "+procesaEstadoDoc(dataCabecera.estado));

    $('#btnCambiofolio').prop("disabled", false);
    $('#btnDtePackSII').prop("disabled", false);
    $('#btnCrearXMLString').prop("disabled", false);
    $('#btnCrearPDFString').prop("disabled", false);
    $('#btnEnviarPACK').prop("disabled", false);

    id_doc = dataCabecera.idDteCabecera;

}
const procesaEstadoDoc=(estado)=>{
    switch (estado) {
        case "1":
            return "Aceptado";
            break;
        default:
            return "No Aceptado";
    }
}

const onlyNumberKey=(evt)=>{
    // Only ASCII character in that range allowed
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
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

const buscarDispoFolio=()=>{
    var nuevo_folio = $("#newFloatingInput").val();
    var urlDest = "/documentos_folio_disp/"+nuevo_folio+"/"+id_empresa;
    // console.log(urlDest);
    $.ajax({
      type: "GET",
      url: urlDest,
      async: true,
      data: {},
      success: function (data) {
        if( data ){
            //console.log("success data");
            //console.log(data);
            if( data == "0" ){
                //console.log("Folio disponible");
                $('#folioDisponible').attr("style", "visibility:visible;position: relative");
                $('#folioOcupado').attr("style", "visibility:hidden;position: absolute");
                sw_cambio_folio=1;
            }else{
                //console.log("Folio ocupado");
                $('#folioDisponible').attr("style", "visibility:hidden;position: absolute");
                $('#folioOcupado').attr("style", "visibility:visible;position: relative");
                sw_cambio_folio=0;
            }
        }
      },
      error: function(err){
        console.log("Error");
        console.log(err);
        alert("Documento no encontrado");
      }
    });
}

const cambiarFolio=()=>{
    if(sw_cambio_folio==0){
        alert("Folio ocupado");
        return;
    }

    let folio_nuevo = $("#newFloatingInput").val();
    console.log( id_doc );
    console.log( folio_nuevo );
    var urlDest = "/documentos_folio_nuevo/"+folio_nuevo+"/"+id_doc;
    $.ajax({
      type: "GET",
      url: urlDest,
      async: true,
      data: {},
      success: function (data) {
        if( data ){
            console.log("cambiarFolio success data");
            console.log(data);
            if( data == 1 ){
                alert("Folio cambiado");
                $('#folio').val(folio_nuevo);
                $('#folio_emisor').text("Folio: "+folio_nuevo);
            }
        }
      },
      error: function(err){
        console.log("cambiarFolio Error");
        console.log(err);
        alert("Documento no encontrado");
      }
    });
}

const llamada=(opcion)=>{

   let urlDest = "/dtePack_limpiar/"+id_doc;
   console.log("opcion:" + opcion);
   console.log("id_doc:" + id_doc);
   console.log("id_empresa:" + id_empresa);
   //return;
   $.ajax({
      type: "GET",
      url: urlDest,
      async: true,
      data: {},
      success: function (data) {
        console.log("llamada: success data");
        console.log(data);
        if( data == 1 ){
            alert("Echo...");
        }else{
            alert("No se pudo procesar el req...");
        }
      },
      error: function(err){
        console.log("llamada: Error");
        console.log(err);
        alert("Documento no encontrado");
      }
   });
}

$( function() {
    changeEmpresas();
});
