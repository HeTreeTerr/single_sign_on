//js获取项目的路径
var pathName=window.document.location.pathname;
//截取项目的名称
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
