function requete(url, div){
	var requete = new XMLHttpRequest();
	requete.open('GET', url, false);
	requete.send(null);
	var contenu = requete.responseText;
	/*if($(div) != null) {
		$(div).innerHTML = contenu;
	}*/
	return contenu;
}

function runHideEffect(effet, div)
{
	if(effet != "fade")
	{
		var options = {};
		$( "#"+div).hide(effet, options, 1000);
	}
	else
	{
		$("#"+div).fadeOut('fast');
	}
}
function runShowEffect(effet, div)
{
	if(effet != "fade")
	{
		var options = {};
		$( "#"+div).show(effet, options, 1000);
	}
	else
		$("#"+div).fadeIn('slow');
}
function runAddClassEffect(classe, div)
{
	$( "#"+div).addClass(classe, 500);
}
function runRemoveClassEffect(classe, div)
{
	$( "#"+div).removeClass(classe, 500);
}
function runUIEffect(effect, div,duration)
{
	if(effect != "shake")
		var options = {};
	else
		var options = {distance:10};
	$("#"+div).effect(effect, options, duration);
}

