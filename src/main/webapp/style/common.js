/**
 * 添加到收藏夹 
 */ 
function AddFavorite(sURL, sTitle) {
	try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("您所使用的浏览器暂不支持此功能，建议使用Ctrl+D进行添加.");
        }
    }
}

/**
 * 获取get参数
 */ 
function request_get()
{
	var Url=window.location.href;
	var u, g, StrBack = '';
	
	if(arguments[arguments.length - 1] == "#") {
		u = Url.split("#");
	}else {
		u = Url.split("?");
	}
	
	if(u.length == 1) {
		g = '';
	}else {
		g = u[1];
	}
	
	if(g != '') {
		gg = g.split("&");
		var MaxI = gg.length;
		str = arguments[0]+ "=" ;
		for(i = 0; i < MaxI; i++) {
			if(gg[i].indexOf(str)==0) {
				StrBack=gg[i].replace(str,"");
				break;
			}
		}
	}
	return StrBack;
}	

/**
 * 获取Cookies
 */
function getCookie(name) { 
 var arr = document.cookie.match(new RegExp("(^| )" + basePath + name + "=([^;]*)(;|$)"));  
 if (arr != null) {  
      return decodeURIComponent(arr[2]);  
 }  
  return null;
}

 
/**
 * 设置Cookies
 */ 
function setCookie(name, value, expires, path, domain, secure) {
    var sCookie = basePath + name + "=" + encodeURIComponent(value);
    if (expires) {
        var date = new Date();
        expires = expires * 1000 * 60 * 60 * 24;
        date.setTime(date.getTime() + expires);
        sCookie += ";expires=" + date.toGMTString();
    }
    if (path) {
        sCookie += ";path=" + path;
    }
    if (domain) {
        sCookie += ";domain=" + domain;
    }
    if (secure) {
        sCookie += ";secure=" + secure;
    }
    document.cookie = sCookie;
}
 
 
 
/**
 * 获取Cookies
 */
function getSessionCookie() { 
	var value1=getCookie("jianghuUserName");
	var value2=getCookie("jianghuPassword");
	var value3=getCookie("jianghuSessionId");
	//alert(value3);
	if ( "undefined"==value3) 
		thisMovie("JianghuGame").sendSessionCookie(null,null,null);
	else
		thisMovie("JianghuGame").sendSessionCookie(value1,value2,value3);
}

/**
 * 设置Cookies
 * value1-用户名
 * value2-密码
 * value3-session ID
 */ 
function setSessionCookie(value1,value2,value3) {
	//alert("set"+value3);
    setCookie("jianghuUserName", value1);
    setCookie("jianghuPassword", value2);
    setCookie("jianghuSessionId", value3);
}


/**
 * 删除Cookie
 */
function deleteCookie(name, path, domain, secure) {
    setCookie(name, '', 0, path, domain, secure)
}

//定时器对象
var timerIntervalObj;

/**
 * 处理js定时器（处理flash播放帧数）
 * flag 标识 0:停止定时器的工作 1:启动定时器的工作
 * millisecond 毫秒数 启动定时器时的必要参数 标识定时器多久执行一次
 */
function exeJsTimerInterval(flag, millisecond) {
	if (1==flag) {
		timerIntervalObj = setInterval(sendToFlash, millisecond);
	} else {
		if (timerIntervalObj) {
			clearInterval(timerIntervalObj);
		}
	}
}

/**
 *给flash发消息调用swf里的 sendToFlash 方法
 */
function sendToFlash() {
	thisMovie("JianghuGame").sendToFlash();
}

/**
 * 获得flash影片对象
 */
function thisMovie(movieName) {
	 if (navigator.appName.indexOf("Microsoft") != -1) {
		 return window[movieName];
	 } else {
		 return document[movieName];
	 }
 }

