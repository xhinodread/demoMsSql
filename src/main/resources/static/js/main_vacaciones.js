let contribuyente = [];

const tabla = ()=>{
    var tb_vaca =  document.getElementById("tbVacaciones");
    //console.log(tb_vaca);
    deleteRow("tbVacaciones");

    /****
    postData('http://localhost:8080/ver_vacaciones/9', { answer: 42 })
      .then(data => {
        console.log("postData");
        console.log(data); // JSON data parsed by `data.json()` call
      });
    *****/
    var id_contrato = $( "#id_contrato" ).val();
    if( id_contrato.length < 1 ){
        return null;
    }

    let cnt=1;
    request('http://localhost:8080/ver_vacaciones/'+id_contrato)
     .then(result => {
        //console.log("fetch: ", result)
        result.forEach((element) => {
            //console.log(element)
           // console.log(element.idVacacion)

            var newRow = tb_vaca.insertRow();

            var newCell0 = newRow.insertCell();
            var newCell1 = newRow.insertCell();
            var newCell2 = newRow.insertCell();
            var newCell3 = newRow.insertCell();
            var newCell4 = newRow.insertCell();
            var newCell5 = newRow.insertCell();
            var newCell6 = newRow.insertCell();

            var newText_id = document.createTextNode(cnt);
            var newText_periodo = document.createTextNode(element.periodo);
            var newText_desde = document.createTextNode(formatFecha(element.desde));
            var newText_hasta = document.createTextNode(formatFecha(element.hasta));
            var newText_dias = document.createTextNode(element.dias);

            //var newText_eliminar = document.createTextNode("Eliminar");
            var buttonEliminar = document.createElement("button");
            buttonEliminar.innerHTML = "Eliminar";
            buttonEliminar.setAttribute("class", 'btn btn-danger');
            buttonEliminar.setAttribute("onclick", 'eliminarVacacion('+element.idVacacion+')');

            var newText_pdf = document.createTextNode("PDF");
            var buttonPdf = document.createElement("button");
            buttonPdf.innerHTML = "Ver";
            buttonPdf.setAttribute("class", 'btn btn-info');
            buttonPdf.setAttribute("onclick", 'verComprobante('+element.idVacacion+')');

            newCell0.appendChild(newText_id);
            newCell1.appendChild(newText_periodo);
            newCell2.appendChild(newText_desde);
            newCell3.appendChild(newText_hasta);
            newCell4.appendChild(newText_dias);
            newCell5.appendChild(buttonEliminar);
            newCell6.appendChild(buttonPdf);

            cnt++;

            /*****
            for (var i = 0; i <= 6; i++) {
               // Inserta una celda en la fila, en el índice 0
               var newCell = newRow.insertCell();
               // Añade un nodo de texto a la celda
               if(i == 0){
                   var newText = document.createTextNode(i);
               }else{
                   var newText = document.createTextNode("Nueva fila superior "+ i);
               }
               newCell.appendChild(newText);
            }
            *****/

        });
     })
     .catch(err => console.error("Ha ocurrido algo: ", err.message));
}

const deleteRow=(tableID)=>{
   try {
       var table = document.getElementById(tableID);
       var rowCount = table.rows.length;
       // console.log("rowCount: " + rowCount);

       for(var i=1; i<rowCount; i++) {
           // var row = table.rows[i];
           // var chkbox = row.cells[0].childNodes[0];
            table.deleteRow(i);
            rowCount--;
            i--;
       }
   }catch(e) {
        alert(e);
   }
}

const request = async (url) => {
  const response = await fetch(url);
  if (!response.ok)
    throw new Error("WARN", response.status);
  const data = await response.json();
  return data;
}

async function postData(url = '', data = {}) {
  // Opciones por defecto estan marcadas con un *
  const response = await fetch(url, {
    method: 'POST', // *GET, POST, PUT, DELETE, etc.
    mode: 'same-origin', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json'
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: 'follow', // manual, *follow, error
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: JSON.stringify(data) // body data type must match "Content-Type" header
  });
  return response.json(); // parses JSON response into native JavaScript objects
}

const eliminarVacacion = (idVacacion)=>{
    alert("eliminarVacacion"+ idVacacion);
}

const verComprobante = (idVacacion)=>{
    alert("verComprobante"+ idVacacion);
}

const formatFecha=(val)=> moment(val).format('DD-MM-YYYY');

const limpiarPantalla =()=>{
    $( "#nombre_funcionario" ).val("");
    $( "#id_contribuyente" ).val("");
    $( "#rut_funcionario" ).val("");
    $( "#id_contrato" ).val("");
    $( "#dias_vacaciones_funcionario" ).val("");
    evaluaId_contrato();
}

const autoCompleteNombreFunc=()=>{
    $( "#nombre_funcionario" ).autocomplete({
      minLength: 3,
      source: contribuyente,
      focus: function( event, ui ) {
        $( "#nombre_funcionario" ).val( ui.item.label );
        return false;
      },
      select: function( event, ui ) {
        $( "#nombre_funcionario" ).val( ui.item.label );
        $( "#id_contribuyente" ).val( ui.item.value );
        $( "#rut_funcionario" ).val( ui.item.rut );
        $( "#id_contrato" ).val( ui.item.idContratoPersonal );
        $( "#dias_vacaciones_funcionario" ).val( ui.item.dias );
        tabla();
        evaluaId_contrato();
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li class='list-group-item list-group-item-action list-group-item-primary' >" )
        .append( "<div>" + item.label + "<br>" + item.desc + "</div>" )
        .appendTo( ul );
    };
}

const cargarFuncionarios =(dataFuncionarios)=>{
    dataFuncionarios.forEach( (contrato) => {
        let elContrato = {
           value: contrato.contribuyente.idContribuyente,
           label: contrato.contribuyente.razonSocial,
           desc: contrato.fechaIngreso,
           idContratoPersonal: contrato.idContratoPersonal,
           rut: contrato.contribuyente.rut,
           dias: contrato.diasVacaciones
        };
        contribuyente.push(elContrato);
    });
}

const getFuncionarios=(id_empresa)=>{
    if(!$.isNumeric( id_empresa )){
        return null;
    }
    $.ajax({
      type: "GET",
      url: "/contratoPersonal/"+id_empresa,
      async: true,
      data: {},
      success: function (data) {
        cargarFuncionarios(data);
      }
    });
}

const changeEmpresas = ()=>{
    $("#empresa").on( "change", ()=> {
        //console.log( "Handler for `change` called." );
        limpiarPantalla();
        deleteRow("tbVacaciones");
        // let id_empresa = $(this).find(":selected").val() ; /// solo funciona en onDocumentReady
        let id_empresa;
        //console.log( "select option:selected" );
        $( "select option:selected" ).each( function() { id_empresa = $(this).val() });
        contribuyente.splice(0,contribuyente.length);
        getFuncionarios(id_empresa);
        //console.log(contribuyente);
    });
}

const popAddVacacion=()=>{
    alert("popAddVacacion");
}

const evaluaId_contrato=()=>{
    if( $('#id_contrato').val().length <=0 ){
        $('#btnAddVacacion').prop("disabled", true);
        return;
    }
    $('#btnAddVacacion').prop("disabled", false);
}

const datePickerAddVacacion = ()=>{
    $('#datepickerDesde').val("");
    $('#datepickerDesde').datepicker();

    $('#datepickerHasta').val("");
    $('#datepickerHasta').datepicker();
}

const reordenarFecha=(string_fecha)=>{
    let array_string = string_fecha.split("/");
    return array_string[1]+"/"+array_string[0]+"/"+array_string["2"];
}

const calcularDias =()=>{
    var desde = $('#datepickerDesde').val().replaceAll("/","-");
    var hasta = $('#datepickerHasta').val().replaceAll("/","-");
    console.log(desde+" "+hasta);

    $.ajax({
      type: "GET",
      url: "/calcularDias/"+desde+"/"+hasta,
      async: true,
      data: {},
      success: function (data) {
        console.log(data);
      }
    });

}

$( function() {
    changeEmpresas();
    autoCompleteNombreFunc();
    evaluaId_contrato();

    $('#datepickerDesde').on( "change", function() {
        $(this).val( reordenarFecha($(this).val()) );
    });

    $('#datepickerHasta').on( "change", function() {
        $(this).val( reordenarFecha($(this).val()) );
    });

});

