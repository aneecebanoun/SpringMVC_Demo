<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery.matchHeight/0.7.0/jquery.matchHeight-min.js"></script>


<script >

$( document ).ready(function() {    
	$(function() {
	    $("div[class^='col-xs-']").matchHeight();
	});
	$(':checkbox').change(function() {
         checkboxValue = $(this).val();
         checkboxValueId = checkboxValue.replace(/\s+/g, '_');
         isChecked = $(this).is(":checked");
         if(isChecked){
	         element = "<div id='"+checkboxValueId +"'><br/><li>"+checkboxValue+"</li></div>"
	         $('#basketLabel').append(element);
	         $("div[class^='col-xs-']").matchHeight();
          }else{
	         $('#'+checkboxValueId).remove();
	         $("div[class^='col-xs-']").matchHeight();
          }
    });
});

</script>
