function mobileNativeCall(func, value)
{
	if(Android == null && window.navigator.userAgent.indexOf("Windows Phone") == -1)
		return -1;
	else if(Android != null)
		return AndroidNative(func, value);
	else
		return WP7NativeCall(func, value);
}

function AndroidNativeCall(func, value)
{
	switch(func)
	{
	case "alert":
			Android.showToast(value);
			return 0;
		break;
	case "confirm":
		return window.confirm(value);
	break;
	}
}

function WP7NativeCall(func, value)
{
	switch(func)
	{
	case "alert":
		alert(value);
		return 0;
		break;
	case "confirm":
		return window.confirm(value);
	break;
	}
}