//js的拦截器，针对引入改文件的所有页面
hookAjax({
    //拦截回调
    onreadystatechange:function(xhr){
        console.log("onreadystatechange called: %O",xhr);
    },
    onload:function(xhr){
        console.log("onload called: %O",xhr);
        var url = xhr.responseURL;
        //响应成功后调用
        //将登录和退出放入白名单中,拦截其他方法
        var loginPath = new RegExp(projectName+"/login");
        var inValidPath = new RegExp(projectName+"/inValid");
        var refreshJwtPath = new RegExp(projectName+"/refreshJwt");
        if(!(loginPath.test(url)) && !(inValidPath.test(url)) && !(refreshJwtPath.test(url))) {
            console.log("方法调用成功后--》执行");
            //获取响应结果
            var result = JSON.parse(xhr.response);
            var code = result["code"];
            console.log("响应返回码--->"+code);
            if(code == -1){
                //未登录，1.删除本地所有的localStorage
                localStorage.clear();
                //2.跳至登录页
                window.location.href = projectName+"/loginPage";
            }
        }
    },
    //拦截函数
    open:function(arg){
        console.log("open called: method:%s,url:%s,async:%s",arg[0],arg[1],arg[2]);
        /*放行
        return false;
        拦截
        return true;*/

        //请求方式
        var method = arg[0];
        //请求路径
        var url = arg[1];
        //是否异步
        var async = arg[2];
        //将登录和退出放入白名单中,拦截其他方法
        var loginPath = new RegExp(projectName+"/login");
        var inValidPath = new RegExp(projectName+"/inValid");
        var refreshJwtPath = new RegExp(projectName+"/refreshJwt");

        if(!(loginPath.test(url)) && !(inValidPath.test(url)) && !(refreshJwtPath.test(url))) {
            console.log("js拦截器最终效果----》刷新token");
            //1.获取token
            var token = getLocalStorage("token",tokenExpTime);
            //2.判断本地浏览器有没有token,有则进行token刷新
            if(token != null && token != "" && token != undefined){
                console.log("当前浏览器存在token");
                $.ajax({
                    url: projectName+"/refreshJwt",
                    //data:"token="+token,
                    type: "GET",
                    dataType: "json",
                    beforeSend: setHeader,
                    success: function(result) {
                        console.log("返回结果：code="+result.code+"---data="+result.data);
                        if(result.code == 1){
                            //效验并刷新token成功
                            if(token != result.data){
                                //替换本地token
                                setLocalStorage("token",result.data);
                                //删除远程redis-token
                                clearRedisToken(token);
                            }else{
                                console.log("token时效大于半小时，无需刷新！");
                            }
                        }else{
                            alert("刷新token失败");
                        }
                    }
                });
            }
        }
    },
    send:function(arg){
        console.log("send called: %O",arg[0]);
    }
});

//清除redis中的密匙和token
function clearRedisToken(token) {
    $.ajax({
        url: projectName+"/inValid",
        data:"token="+token,
        type: "GET",
        dataType: "json",
        success: function(result) {
            console.log("返回结果：code="+result.code+"---data="+result.data);
            if(result.code == 1){
                console.log("清除redis-token成功");
            }else{
                alert("清除redis-token失败");
            }
        }
    });
}

//将token放入请求头中
function setHeader(xhr){ // XmlHttpRequest
    var token = getLocalStorage("token",tokenExpTime);
    xhr.setRequestHeader("Authorization",token);
}
