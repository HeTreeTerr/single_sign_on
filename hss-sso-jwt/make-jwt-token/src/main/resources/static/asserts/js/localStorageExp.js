//封装过期控制代码
var tokenExpTime = 1000*60*60;
//写入
function setLocalStorage(key,value){
    var curTime = new Date().getTime();
    localStorage.setItem(key,JSON.stringify({data:value,time:curTime}));
}
//获取
function getLocalStorage(key,exp){
    var data = localStorage.getItem(key);
    if(data == null || data == "" || data == undefined){
        return null;
    }
    var dataObj = JSON.parse(data);
    if (new Date().getTime() - dataObj.time>exp) {
        console.log('信息已过期');
        localStorage.removeItem(key);
    }else{
        // console.log("data="+dataObj.data);
        // console.log(JSON.parse(dataObj.data));
        var dataObjDatatoJson = dataObj.data;
        return dataObjDatatoJson;
    }
}
//移除
function removeLocalStorage(key) {
    localStorage.removeItem(key);
}