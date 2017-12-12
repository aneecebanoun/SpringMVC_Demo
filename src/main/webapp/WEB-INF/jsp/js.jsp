<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script >

$( document ).ready(function() {     
	$(':checkbox').change(function() {
         checkboxValue = $(this).val();
         checkboxValueId = checkboxValue.replace(/\s+/g, '_');
         isChecked = $(this).is(":checked");

         if(isChecked){
	         element = "<label id='"+checkboxValueId +"'><br/>-"+checkboxValue+"</label>"
	         $('#basketLabel').append(element);
          }else{

	      $('#'+checkboxValueId).remove();
          }
    });
});

</script>
