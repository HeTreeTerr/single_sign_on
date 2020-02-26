//js获取项目的路径
var pathName=window.document.location.pathname;
//截取项目的名称
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

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
        //放行
        //return false;
        //拦截
        //return true;
    },
    send:function(arg){
        console.log("send called: %O",arg[0]);
    }
});