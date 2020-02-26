//js的拦截器，针对引入改文件的所有页面
hookAjax({
    //拦截回调
    onreadystatechange:function(xhr){
        console.log("onreadystatechange called: %O",xhr);
    },
    onload:function(xhr){
        console.log("onload called: %O",xhr)
    },
    //拦截函数
    open:function(arg){
        console.log("open called: method:%s,url:%s,async:%s",arg[0],arg[1],arg[2]);
        /*放行
        return false;
        拦截
        return true;*/
        debugger;
        //请求方式
        var method = arg[0];
        //请求路径
        var url = arg[1];
        //是否异步
        var async = arg[2];
        //将登录和退出放入白名单中,拦截其他方法
        var loginPath = new RegExp(projectName+"/login");
        var inValidPath = new RegExp(projectName+"/inValid");
        if(!(loginPath.test(url)) && !(inValidPath.test(url))) {
            console.log("js拦截器最终效果----》刷新token");
        }
    },
    send:function(arg){
        console.log("send called: %O",arg[0]);
    }
});