(function(){var g,aa=aa||{},l=this;function p(a){return void 0!==a}
function q(a,b,c){a=a.split(".");c=c||l;a[0]in c||!c.execScript||c.execScript("var "+a[0]);for(var d;a.length&&(d=a.shift());)!a.length&&p(b)?c[d]=b:c[d]?c=c[d]:c=c[d]={}}
function r(a,b){for(var c=a.split("."),d=b||l,e;e=c.shift();)if(null!=d[e])d=d[e];else return null;return d}
function t(){}
function ba(a){a.getInstance=function(){return a.P?a.P:a.P=new a}}
function ca(a){var b=typeof a;if("object"==b)if(a){if(a instanceof Array)return"array";if(a instanceof Object)return b;var c=Object.prototype.toString.call(a);if("[object Window]"==c)return"object";if("[object Array]"==c||"number"==typeof a.length&&"undefined"!=typeof a.splice&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("splice"))return"array";if("[object Function]"==c||"undefined"!=typeof a.call&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("call"))return"function"}else return"null";
else if("function"==b&&"undefined"==typeof a.call)return"object";return b}
function da(a){return"array"==ca(a)}
function ea(a){var b=ca(a);return"array"==b||"object"==b&&"number"==typeof a.length}
function u(a){return"string"==typeof a}
function fa(a){return"number"==typeof a}
function ga(a){return"function"==ca(a)}
function ha(a){var b=typeof a;return"object"==b&&null!=a||"function"==b}
function ja(a){return a[ka]||(a[ka]=++la)}
var ka="closure_uid_"+(1E9*Math.random()>>>0),la=0;function ma(a,b,c){return a.call.apply(a.bind,arguments)}
function na(a,b,c){if(!a)throw Error();if(2<arguments.length){var d=Array.prototype.slice.call(arguments,2);return function(){var c=Array.prototype.slice.call(arguments);Array.prototype.unshift.apply(c,d);return a.apply(b,c)}}return function(){return a.apply(b,arguments)}}
function v(a,b,c){v=Function.prototype.bind&&-1!=Function.prototype.bind.toString().indexOf("native code")?ma:na;return v.apply(null,arguments)}
function oa(a,b){var c=Array.prototype.slice.call(arguments,1);return function(){var b=c.slice();b.push.apply(b,arguments);return a.apply(this,b)}}
var w=Date.now||function(){return+new Date};
function x(a,b){function c(){}
c.prototype=b.prototype;a.A=b.prototype;a.prototype=new c;a.prototype.constructor=a;a.Ne=function(a,c,f){for(var h=Array(arguments.length-2),k=2;k<arguments.length;k++)h[k-2]=arguments[k];return b.prototype[c].apply(a,h)}}
;function pa(a){if(Error.captureStackTrace)Error.captureStackTrace(this,pa);else{var b=Error().stack;b&&(this.stack=b)}a&&(this.message=String(a))}
x(pa,Error);pa.prototype.name="CustomError";var qa;var ra=String.prototype.trim?function(a){return a.trim()}:function(a){return a.replace(/^[\s\xa0]+|[\s\xa0]+$/g,"")};
function sa(a){return decodeURIComponent(a.replace(/\+/g," "))}
var ta=/&/g,ua=/</g,va=/>/g,wa=/"/g,xa=/'/g,ya=/\x00/g,za=/[\x00&<>"']/;function Aa(a){var b={"&amp;":"&","&lt;":"<","&gt;":">","&quot;":'"'},c;c=l.document.createElement("div");return a.replace(Ba,function(a,e){var f=b[a];if(f)return f;if("#"==e.charAt(0)){var h=Number("0"+e.substr(1));isNaN(h)||(f=String.fromCharCode(h))}f||(c.innerHTML=a+" ",f=c.firstChild.nodeValue.slice(0,-1));return b[a]=f})}
function Ca(a){return a.replace(/&([^;]+);/g,function(a,c){switch(c){case "amp":return"&";case "lt":return"<";case "gt":return">";case "quot":return'"';default:if("#"==c.charAt(0)){var d=Number("0"+c.substr(1));if(!isNaN(d))return String.fromCharCode(d)}return a}})}
var Ba=/&([^;\s<&]+);?/g,Da={"\x00":"\\0","\b":"\\b","\f":"\\f","\n":"\\n","\r":"\\r","\t":"\\t","\x0B":"\\x0B",'"':'\\"',"\\":"\\\\","<":"<"},Ea={"'":"\\'"};
function Fa(a,b){for(var c=0,d=ra(String(a)).split("."),e=ra(String(b)).split("."),f=Math.max(d.length,e.length),h=0;0==c&&h<f;h++){var k=d[h]||"",m=e[h]||"",n=RegExp("(\\d*)(\\D*)","g"),H=RegExp("(\\d*)(\\D*)","g");do{var R=n.exec(k)||["","",""],ia=H.exec(m)||["","",""];if(0==R[0].length&&0==ia[0].length)break;c=Ga(0==R[1].length?0:parseInt(R[1],10),0==ia[1].length?0:parseInt(ia[1],10))||Ga(0==R[2].length,0==ia[2].length)||Ga(R[2],ia[2])}while(0==c)}return c}
function Ga(a,b){return a<b?-1:a>b?1:0}
function Ha(a){for(var b=0,c=0;c<a.length;++c)b=31*b+a.charCodeAt(c)>>>0;return b}
;var Ia=Array.prototype.indexOf?function(a,b,c){return Array.prototype.indexOf.call(a,b,c)}:function(a,b,c){c=null==c?0:0>c?Math.max(0,a.length+c):c;
if(u(a))return u(b)&&1==b.length?a.indexOf(b,c):-1;for(;c<a.length;c++)if(c in a&&a[c]===b)return c;return-1},y=Array.prototype.forEach?function(a,b,c){Array.prototype.forEach.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=u(a)?a.split(""):a,f=0;f<d;f++)f in e&&b.call(c,e[f],f,a)},Ja=Array.prototype.filter?function(a,b,c){return Array.prototype.filter.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=[],f=0,h=u(a)?a.split(""):a,k=0;k<d;k++)if(k in h){var m=h[k];
b.call(c,m,k,a)&&(e[f++]=m)}return e},Ka=Array.prototype.map?function(a,b,c){return Array.prototype.map.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=Array(d),f=u(a)?a.split(""):a,h=0;h<d;h++)h in f&&(e[h]=b.call(c,f[h],h,a));
return e},La=Array.prototype.some?function(a,b,c){return Array.prototype.some.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=u(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&b.call(c,e[f],f,a))return!0;
return!1},Ma=Array.prototype.every?function(a,b,c){return Array.prototype.every.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=u(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&!b.call(c,e[f],f,a))return!1;
return!0};
function Na(a,b,c){b=Oa(a,b,c);return 0>b?null:u(a)?a.charAt(b):a[b]}
function Oa(a,b,c){for(var d=a.length,e=u(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&b.call(c,e[f],f,a))return f;return-1}
function z(a,b){return 0<=Ia(a,b)}
function Pa(a,b){z(a,b)||a.push(b)}
function Qa(a,b){var c=Ia(a,b),d;(d=0<=c)&&Array.prototype.splice.call(a,c,1);return d}
function Ra(a,b){var c=Oa(a,b,void 0);0<=c&&Array.prototype.splice.call(a,c,1)}
function Sa(a){return Array.prototype.concat.apply(Array.prototype,arguments)}
function Ta(a){var b=a.length;if(0<b){for(var c=Array(b),d=0;d<b;d++)c[d]=a[d];return c}return[]}
function Ua(a,b){for(var c=1;c<arguments.length;c++){var d=arguments[c];if(ea(d)){var e=a.length||0,f=d.length||0;a.length=e+f;for(var h=0;h<f;h++)a[e+h]=d[h]}else a.push(d)}}
function Va(a,b,c,d){return Array.prototype.splice.apply(a,Wa(arguments,1))}
function Wa(a,b,c){return 2>=arguments.length?Array.prototype.slice.call(a,b):Array.prototype.slice.call(a,b,c)}
function Xa(a,b){return a>b?1:a<b?-1:0}
;function Ya(a,b,c){for(var d in a)b.call(c,a[d],d,a)}
function Za(a,b,c){var d={},e;for(e in a)b.call(c,a[e],e,a)&&(d[e]=a[e]);return d}
function $a(a){var b=0,c;for(c in a)b++;return b}
function ab(a,b){return bb(a,b)}
function cb(a){var b=[],c=0,d;for(d in a)b[c++]=a[d];return b}
function db(a){var b=[],c=0,d;for(d in a)b[c++]=d;return b}
function eb(a){return null!==a&&"withCredentials"in a}
function bb(a,b){for(var c in a)if(a[c]==b)return!0;return!1}
function fb(a){var b=gb,c;for(c in b)if(a.call(void 0,b[c],c,b))return c}
function hb(a){for(var b in a)return!1;return!0}
function ib(a,b){if(null!==a&&b in a)throw Error('The object already contains the key "'+b+'"');a[b]=!0}
function jb(a){var b={},c;for(c in a)b[c]=a[c];return b}
function kb(a){var b=ca(a);if("object"==b||"array"==b){if(ga(a.clone))return a.clone();var b="array"==b?[]:{},c;for(c in a)b[c]=kb(a[c]);return b}return a}
var lb="constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");function mb(a,b){for(var c,d,e=1;e<arguments.length;e++){d=arguments[e];for(c in d)a[c]=d[c];for(var f=0;f<lb.length;f++)c=lb[f],Object.prototype.hasOwnProperty.call(d,c)&&(a[c]=d[c])}}
;var nb;a:{var ob=l.navigator;if(ob){var pb=ob.userAgent;if(pb){nb=pb;break a}}nb=""}function A(a){return-1!=nb.indexOf(a)}
;function qb(){return(A("Chrome")||A("CriOS"))&&!A("Edge")}
;function rb(){this.b=""}
rb.prototype.Jb=!0;rb.prototype.Eb=function(){return this.b};
rb.prototype.toString=function(){return"Const{"+this.b+"}"};
function sb(a){var b=new rb;b.b=a;return b}
;function tb(){this.b="";this.f=ub}
tb.prototype.Jb=!0;tb.prototype.Eb=function(){return this.b};
function vb(a){if(a instanceof tb&&a.constructor===tb&&a.f===ub)return a.b;ca(a);return"type_error:SafeUrl"}
var wb=/^(?:(?:https?|mailto|ftp):|[^&:/?#]*(?:[/?#]|$))/i;function xb(a){if(a instanceof tb)return a;a=a.Jb?a.Eb():String(a);wb.test(a)||(a="about:invalid#zClosurez");return yb(a)}
var ub={};function yb(a){var b=new tb;b.b=a;return b}
yb("about:blank");function zb(){this.b="";this.f=Ab;this.g=null}
zb.prototype.Jb=!0;zb.prototype.Eb=function(){return this.b};
function Bb(a){if(a instanceof zb&&a.constructor===zb&&a.f===Ab)return a.b;ca(a);return"type_error:SafeHtml"}
var Ab={};function Cb(a,b){var c=new zb;c.b=a;c.g=b;return c}
Cb("<!DOCTYPE html>",0);Cb("",0);Cb("<br>",0);function Db(a,b){var c;c=b instanceof tb?b:xb(b);a.href=vb(c)}
;function Eb(a,b,c){a&&(a.dataset?a.dataset[Fb(b)]=c:a.setAttribute("data-"+b,c))}
function B(a,b){return a?a.dataset?a.dataset[Fb(b)]:a.getAttribute("data-"+b):null}
function Gb(a,b){a&&(a.dataset?delete a.dataset[Fb(b)]:a.removeAttribute("data-"+b))}
var Hb={};function Fb(a){return Hb[a]||(Hb[a]=String(a).replace(/\-([a-z])/g,function(a,c){return c.toUpperCase()}))}
;function Ib(a){l.setTimeout(function(){throw a;},0)}
var Jb;
function Kb(){var a=l.MessageChannel;"undefined"===typeof a&&"undefined"!==typeof window&&window.postMessage&&window.addEventListener&&!A("Presto")&&(a=function(){var a=document.createElement("IFRAME");a.style.display="none";a.src="";document.documentElement.appendChild(a);var b=a.contentWindow,a=b.document;a.open();a.write("");a.close();var c="callImmediate"+Math.random(),d="file:"==b.location.protocol?"*":b.location.protocol+"//"+b.location.host,a=v(function(a){if(("*"==d||a.origin==d)&&a.data==
c)this.port1.onmessage()},this);
b.addEventListener("message",a,!1);this.port1={};this.port2={postMessage:function(){b.postMessage(c,d)}}});
if("undefined"!==typeof a&&!A("Trident")&&!A("MSIE")){var b=new a,c={},d=c;b.port1.onmessage=function(){if(p(c.next)){c=c.next;var a=c.$b;c.$b=null;a()}};
return function(a){d.next={$b:a};d=d.next;b.port2.postMessage(0)}}return"undefined"!==typeof document&&"onreadystatechange"in document.createElement("SCRIPT")?function(a){var b=document.createElement("SCRIPT");
b.onreadystatechange=function(){b.onreadystatechange=null;b.parentNode.removeChild(b);b=null;a();a=null};
document.documentElement.appendChild(b)}:function(a){l.setTimeout(a,0)}}
;function Lb(a,b,c){this.h=c;this.g=a;this.i=b;this.f=0;this.b=null}
Lb.prototype.get=function(){var a;0<this.f?(this.f--,a=this.b,this.b=a.next,a.next=null):a=this.g();return a};
function Mb(a,b){a.i(b);a.f<a.h&&(a.f++,b.next=a.b,a.b=b)}
;function Nb(){this.f=this.b=null}
var Pb=new Lb(function(){return new Ob},function(a){a.reset()},100);
Nb.prototype.remove=function(){var a=null;this.b&&(a=this.b,this.b=this.b.next,this.b||(this.f=null),a.next=null);return a};
function Ob(){this.next=this.scope=this.b=null}
Ob.prototype.set=function(a,b){this.b=a;this.scope=b;this.next=null};
Ob.prototype.reset=function(){this.next=this.scope=this.b=null};function Qb(a,b){Rb||Sb();Tb||(Rb(),Tb=!0);var c=Ub,d=Pb.get();d.set(a,b);c.f?c.f.next=d:c.b=d;c.f=d}
var Rb;function Sb(){if(l.Promise&&l.Promise.resolve){var a=l.Promise.resolve(void 0);Rb=function(){a.then(Vb)}}else Rb=function(){var a=Vb;
!ga(l.setImmediate)||l.Window&&l.Window.prototype&&!A("Edge")&&l.Window.prototype.setImmediate==l.setImmediate?(Jb||(Jb=Kb()),Jb(a)):l.setImmediate(a)}}
var Tb=!1,Ub=new Nb;function Vb(){for(var a;a=Ub.remove();){try{a.b.call(a.scope)}catch(b){Ib(b)}Mb(Pb,a)}Tb=!1}
;function C(){this.Ea=this.Ea;this.V=this.V}
C.prototype.Ea=!1;C.prototype.isDisposed=function(){return this.Ea};
C.prototype.dispose=function(){this.Ea||(this.Ea=!0,this.w())};
function Wb(a,b){a.Ea?b.call(void 0):(a.V||(a.V=[]),a.V.push(p(void 0)?v(b,void 0):b))}
C.prototype.w=function(){if(this.V)for(;this.V.length;)this.V.shift()()};
function D(a){a&&"function"==typeof a.dispose&&a.dispose()}
function Xb(a){for(var b=0,c=arguments.length;b<c;++b){var d=arguments[b];ea(d)?Xb.apply(null,d):D(d)}}
;function E(a){C.call(this);this.h=1;this.f=[];this.g=0;this.b=[];this.$={};this.i=!!a}
x(E,C);g=E.prototype;g.subscribe=function(a,b,c){var d=this.$[a];d||(d=this.$[a]=[]);var e=this.h;this.b[e]=a;this.b[e+1]=b;this.b[e+2]=c;this.h=e+3;d.push(e);return e};
g.unsubscribe=function(a,b,c){if(a=this.$[a]){var d=this.b;if(a=Na(a,function(a){return d[a+1]==b&&d[a+2]==c}))return this.ga(a)}return!1};
g.ga=function(a){var b=this.b[a];if(b){var c=this.$[b];0!=this.g?(this.f.push(a),this.b[a+1]=t):(c&&Qa(c,a),delete this.b[a],delete this.b[a+1],delete this.b[a+2])}return!!b};
g.u=function(a,b){var c=this.$[a];if(c){for(var d=Array(arguments.length-1),e=1,f=arguments.length;e<f;e++)d[e-1]=arguments[e];if(this.i)for(e=0;e<c.length;e++){var h=c[e];Yb(this.b[h+1],this.b[h+2],d)}else{this.g++;try{for(e=0,f=c.length;e<f;e++)h=c[e],this.b[h+1].apply(this.b[h+2],d)}finally{if(this.g--,0<this.f.length&&0==this.g)for(;c=this.f.pop();)this.ga(c)}}return 0!=e}return!1};
function Yb(a,b,c){Qb(function(){a.apply(b,c)})}
g.clear=function(a){if(a){var b=this.$[a];b&&(y(b,this.ga,this),delete this.$[a])}else this.b.length=0,this.$={}};
g.N=function(a){if(a){var b=this.$[a];return b?b.length:0}a=0;for(b in this.$)a+=this.N(b);return a};
g.w=function(){E.A.w.call(this);this.clear();this.f.length=0};var Zb=window.yt&&window.yt.config_||window.ytcfg&&window.ytcfg.data_||{};q("yt.config_",Zb,void 0);q("yt.tokens_",window.yt&&window.yt.tokens_||{},void 0);var $b=window.yt&&window.yt.msgs_||r("window.ytcfg.msgs")||{};q("yt.msgs_",$b,void 0);function ac(a){bc(Zb,arguments)}
function F(a,b){return a in Zb?Zb[a]:b}
function G(a,b){ga(a)&&(a=cc(a));return window.setTimeout(a,b)}
function I(a){window.clearTimeout(a)}
function cc(a){return a&&window.yterr?function(){try{return a.apply(this,arguments)}catch(b){throw dc(b),b;}}:a}
function dc(a,b){var c=r("yt.logging.errors.log");c?c(a,b,void 0,void 0,void 0):(c=F("ERRORS",[]),c.push([a,b,void 0,void 0,void 0]),ac("ERRORS",c))}
function ec(){var a={},b="FLASH_UPGRADE"in $b?$b.FLASH_UPGRADE:'You need to upgrade your Adobe Flash Player to watchthis video. <br> <a href="http://get.adobe.com/flashplayer/">Download it from Adobe.</a>';if(b)for(var c in a)b=b.replace(new RegExp("\\$"+c,"gi"),function(){return a[c]});
return b}
function bc(a,b){if(1<b.length){var c=b[0];a[c]=b[1]}else{var d=b[0];for(c in d)a[c]=d[c]}}
var fc=window.performance&&window.performance.timing&&window.performance.now&&window.__yt_experimental_now?function(){return window.performance.timing.navigationStart+window.performance.now()}:function(){return(new Date).getTime()},gc="Microsoft Internet Explorer"==navigator.appName;var hc=r("yt.pubsub.instance_")||new E;E.prototype.subscribe=E.prototype.subscribe;E.prototype.unsubscribeByKey=E.prototype.ga;E.prototype.publish=E.prototype.u;E.prototype.clear=E.prototype.clear;q("yt.pubsub.instance_",hc,void 0);var ic=r("yt.pubsub.subscribedKeys_")||{};q("yt.pubsub.subscribedKeys_",ic,void 0);var jc=r("yt.pubsub.topicToKeys_")||{};q("yt.pubsub.topicToKeys_",jc,void 0);var kc=r("yt.pubsub.isSynchronous_")||{};q("yt.pubsub.isSynchronous_",kc,void 0);
var lc=r("yt.pubsub.skipSubId_")||null;q("yt.pubsub.skipSubId_",lc,void 0);function mc(a,b,c){var d=nc();if(d){var e=d.subscribe(a,function(){if(!lc||lc!=e){var d=arguments,h;h=function(){ic[e]&&b.apply(c||window,d)};
try{kc[a]?h():G(h,0)}catch(k){dc(k)}}},c);
ic[e]=!0;jc[a]||(jc[a]=[]);jc[a].push(e);return e}return 0}
function oc(a){var b=nc();b&&("number"==typeof a?a=[a]:"string"==typeof a&&(a=[parseInt(a,10)]),y(a,function(a){b.unsubscribeByKey(a);delete ic[a]}))}
function J(a,b){var c=nc();return c?c.publish.apply(c,arguments):!1}
function pc(a,b){kc[a]=!0;var c=nc();c&&c.publish.apply(c,arguments);kc[a]=!1}
function qc(a){jc[a]&&(a=jc[a],y(a,function(a){ic[a]&&delete ic[a]}),a.length=0)}
function rc(a){var b=nc();if(b)if(b.clear(a),a)qc(a);else for(var c in jc)qc(c)}
function nc(){return r("yt.pubsub.instance_")}
;function sc(a,b){if(window.spf){var c="";if(a){var d=a.indexOf("jsbin/"),e=a.lastIndexOf(".js"),f=d+6;-1<d&&-1<e&&e>f&&(c=a.substring(f,e),c=c.replace(tc,""),c=c.replace(uc,""),c=c.replace("debug-",""),c=c.replace("tracing-",""))}spf.script.load(a,c,b)}else vc(a,b)}
function vc(a,b){var c=wc(a),d=document.getElementById(c),e=d&&B(d,"loaded"),f=d&&!e;if(e)b&&b();else{if(b){var e=mc(c,b),h=""+ja(b);xc[h]=e}f||(d=yc(a,c,function(){B(d,"loaded")||(Eb(d,"loaded","true"),J(c),G(oa(rc,c),0))}))}}
function yc(a,b,c){var d=document.createElement("script");d.id=b;d.onload=function(){c&&setTimeout(c,0)};
d.onreadystatechange=function(){switch(d.readyState){case "loaded":case "complete":d.onload()}};
d.src=a;a=document.getElementsByTagName("head")[0]||document.body;a.insertBefore(d,a.firstChild);return d}
function zc(a,b){if(a&&b){var c=""+ja(b);(c=xc[c])&&oc(c)}}
function wc(a){var b=document.createElement("a");Db(b,a);a=b.href.replace(/^[a-zA-Z]+:\/\//,"//");return"js-"+Ha(a)}
var tc=/\.vflset|-vfl[a-zA-Z0-9_+=-]+/,uc=/-[a-zA-Z]{2,3}_[a-zA-Z]{2,3}(?=(\/|$))/,xc={};var Ac=null;function Bc(){var a=F("BG_I",null),b=F("BG_IU",null),c=F("BG_P",void 0);b?sc(b,function(){Ac=new botguard.bg(c)}):a&&(eval(a),Ac=new botguard.bg(c))}
function Cc(){return null!=Ac}
function Dc(){return Ac?Ac.invoke():null}
;function Ec(a,b){return Cb(b,null)}
;var Fc="StopIteration"in l?l.StopIteration:{message:"StopIteration",stack:""};function Gc(){}
Gc.prototype.next=function(){throw Fc;};
Gc.prototype.la=function(){return this};
function Hc(a){if(a instanceof Gc)return a;if("function"==typeof a.la)return a.la(!1);if(ea(a)){var b=0,c=new Gc;c.next=function(){for(;;){if(b>=a.length)throw Fc;if(b in a)return a[b++];b++}};
return c}throw Error("Not implemented");}
function Ic(a,b,c){if(ea(a))try{y(a,b,c)}catch(d){if(d!==Fc)throw d;}else{a=Hc(a);try{for(;;)b.call(c,a.next(),void 0,a)}catch(d){if(d!==Fc)throw d;}}}
function Jc(a){if(ea(a))return Ta(a);a=Hc(a);var b=[];Ic(a,function(a){b.push(a)});
return b}
;function Kc(a,b){this.f={};this.b=[];this.ua=this.g=0;var c=arguments.length;if(1<c){if(c%2)throw Error("Uneven number of arguments");for(var d=0;d<c;d+=2)this.set(arguments[d],arguments[d+1])}else if(a){a instanceof Kc?(c=a.ha(),d=a.O()):(c=db(a),d=cb(a));for(var e=0;e<c.length;e++)this.set(c[e],d[e])}}
g=Kc.prototype;g.N=function(){return this.g};
g.O=function(){Lc(this);for(var a=[],b=0;b<this.b.length;b++)a.push(this.f[this.b[b]]);return a};
g.ha=function(){Lc(this);return this.b.concat()};
g.Qa=function(a){for(var b=0;b<this.b.length;b++){var c=this.b[b];if(Mc(this.f,c)&&this.f[c]==a)return!0}return!1};
g.equals=function(a,b){if(this===a)return!0;if(this.g!=a.N())return!1;var c=b||Nc;Lc(this);for(var d,e=0;d=this.b[e];e++)if(!c(this.get(d),a.get(d)))return!1;return!0};
function Nc(a,b){return a===b}
g.isEmpty=function(){return 0==this.g};
g.clear=function(){this.f={};this.ua=this.g=this.b.length=0};
g.remove=function(a){return Mc(this.f,a)?(delete this.f[a],this.g--,this.ua++,this.b.length>2*this.g&&Lc(this),!0):!1};
function Lc(a){if(a.g!=a.b.length){for(var b=0,c=0;b<a.b.length;){var d=a.b[b];Mc(a.f,d)&&(a.b[c++]=d);b++}a.b.length=c}if(a.g!=a.b.length){for(var e={},c=b=0;b<a.b.length;)d=a.b[b],Mc(e,d)||(a.b[c++]=d,e[d]=1),b++;a.b.length=c}}
g.get=function(a,b){return Mc(this.f,a)?this.f[a]:b};
g.set=function(a,b){Mc(this.f,a)||(this.g++,this.b.push(a),this.ua++);this.f[a]=b};
g.forEach=function(a,b){for(var c=this.ha(),d=0;d<c.length;d++){var e=c[d],f=this.get(e);a.call(b,f,e,this)}};
g.clone=function(){return new Kc(this)};
g.la=function(a){Lc(this);var b=0,c=this.ua,d=this,e=new Gc;e.next=function(){if(c!=d.ua)throw Error("The map has changed since the iterator was created");if(b>=d.b.length)throw Fc;var e=d.b[b++];return a?e:d.f[e]};
return e};
function Mc(a,b){return Object.prototype.hasOwnProperty.call(a,b)}
;function Oc(a){return a.N&&"function"==typeof a.N?a.N():ea(a)||u(a)?a.length:$a(a)}
function Pc(a){if(a.O&&"function"==typeof a.O)return a.O();if(u(a))return a.split("");if(ea(a)){for(var b=[],c=a.length,d=0;d<c;d++)b.push(a[d]);return b}return cb(a)}
function Qc(a){if(a.ha&&"function"==typeof a.ha)return a.ha();if(!a.O||"function"!=typeof a.O){if(ea(a)||u(a)){var b=[];a=a.length;for(var c=0;c<a;c++)b.push(c);return b}return db(a)}}
function Rc(a,b,c){if(a.forEach&&"function"==typeof a.forEach)a.forEach(b,c);else if(ea(a)||u(a))y(a,b,c);else for(var d=Qc(a),e=Pc(a),f=e.length,h=0;h<f;h++)b.call(c,e[h],d&&d[h],a)}
function Sc(a,b){if("function"==typeof a.every)return a.every(b,void 0);if(ea(a)||u(a))return Ma(a,b,void 0);for(var c=Qc(a),d=Pc(a),e=d.length,f=0;f<e;f++)if(!b.call(void 0,d[f],c&&c[f],a))return!1;return!0}
;function Tc(a){this.b=new Kc;a&&Uc(this,a)}
function Vc(a){var b=typeof a;return"object"==b&&a||"function"==b?"o"+ja(a):b.substr(0,1)+a}
g=Tc.prototype;g.N=function(){return this.b.N()};
function Uc(a,b){for(var c=Pc(b),d=c.length,e=0;e<d;e++){var f=c[e];a.b.set(Vc(f),f)}}
g.remove=function(a){return this.b.remove(Vc(a))};
g.clear=function(){this.b.clear()};
g.isEmpty=function(){return this.b.isEmpty()};
g.contains=function(a){a=Vc(a);return Mc(this.b.f,a)};
g.O=function(){return this.b.O()};
g.clone=function(){return new Tc(this)};
g.equals=function(a){return this.N()==Oc(a)&&Wc(this,a)};
function Wc(a,b){var c=Oc(b);if(a.N()>c)return!1;!(b instanceof Tc)&&5<c&&(b=new Tc(b));return Sc(a,function(a){var c=b;return c.contains&&"function"==typeof c.contains?c.contains(a):c.Qa&&"function"==typeof c.Qa?c.Qa(a):ea(c)||u(c)?z(c,a):bb(c,a)})}
g.la=function(){return this.b.la(!1)};function Xc(){return A("iPhone")&&!A("iPod")&&!A("iPad")}
;var Yc=A("Opera"),K=A("Trident")||A("MSIE"),Zc=A("Edge"),$c=A("Gecko")&&!(-1!=nb.toLowerCase().indexOf("webkit")&&!A("Edge"))&&!(A("Trident")||A("MSIE"))&&!A("Edge"),ad=-1!=nb.toLowerCase().indexOf("webkit")&&!A("Edge"),bd=A("Macintosh"),cd=A("Windows");function dd(){var a=l.document;return a?a.documentMode:void 0}
var ed;a:{var fd="",gd=function(){var a=nb;if($c)return/rv\:([^\);]+)(\)|;)/.exec(a);if(Zc)return/Edge\/([\d\.]+)/.exec(a);if(K)return/\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/.exec(a);if(ad)return/WebKit\/(\S+)/.exec(a);if(Yc)return/(?:Version)[ \/]?(\S+)/.exec(a)}();
gd&&(fd=gd?gd[1]:"");if(K){var hd=dd();if(null!=hd&&hd>parseFloat(fd)){ed=String(hd);break a}}ed=fd}var id=ed,jd={};function kd(a){return jd[a]||(jd[a]=0<=Fa(id,a))}
function ld(a){return Number(md)>=a}
var nd=l.document,md=nd&&K?dd()||("CSS1Compat"==nd.compatMode?parseInt(id,10):5):void 0;function od(a){a=String(a);if(/^\s*$/.test(a)?0:/^[\],:{}\s\u2028\u2029]*$/.test(a.replace(/\\["\\\/bfnrtu]/g,"@").replace(/(?:"[^"\\\n\r\u2028\u2029\x00-\x08\x0a-\x1f]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)[\s\u2028\u2029]*(?=:|,|]|}|$)/g,"]").replace(/(?:^|:|,)(?:[\s\u2028\u2029]*\[)+/g,"")))try{return eval("("+a+")")}catch(b){}throw Error("Invalid JSON string: "+a);}
function pd(a){return eval("("+a+")")}
function L(a){return qd(new rd(void 0),a)}
function rd(a){this.b=a}
function qd(a,b){var c=[];sd(a,b,c);return c.join("")}
function sd(a,b,c){if(null==b)c.push("null");else{if("object"==typeof b){if(da(b)){var d=b;b=d.length;c.push("[");for(var e="",f=0;f<b;f++)c.push(e),e=d[f],sd(a,a.b?a.b.call(d,String(f),e):e,c),e=",";c.push("]");return}if(b instanceof String||b instanceof Number||b instanceof Boolean)b=b.valueOf();else{c.push("{");f="";for(d in b)Object.prototype.hasOwnProperty.call(b,d)&&(e=b[d],"function"!=typeof e&&(c.push(f),td(d,c),c.push(":"),sd(a,a.b?a.b.call(b,d,e):e,c),f=","));c.push("}");return}}switch(typeof b){case "string":td(b,
c);break;case "number":c.push(isFinite(b)&&!isNaN(b)?String(b):"null");break;case "boolean":c.push(String(b));break;case "function":c.push("null");break;default:throw Error("Unknown type: "+typeof b);}}}
var ud={'"':'\\"',"\\":"\\\\","/":"\\/","\b":"\\b","\f":"\\f","\n":"\\n","\r":"\\r","\t":"\\t","\x0B":"\\u000b"},vd=/\uffff/.test("\uffff")?/[\\\"\x00-\x1f\x7f-\uffff]/g:/[\\\"\x00-\x1f\x7f-\xff]/g;function td(a,b){b.push('"',a.replace(vd,function(a){var b=ud[a];b||(b="\\u"+(a.charCodeAt(0)|65536).toString(16).substr(1),ud[a]=b);return b}),'"')}
;var wd=/^(?:([^:/?#.]+):)?(?:\/\/(?:([^/?#]*)@)?([^/#?]*?)(?::([0-9]+))?(?=[/#?]|$))?([^?#]+)?(?:\?([^#]*))?(?:#(.*))?$/;function xd(a){return(a=a.match(wd)[3]||null)?decodeURI(a):a}
function yd(a,b){if(a)for(var c=a.split("&"),d=0;d<c.length;d++){var e=c[d].indexOf("="),f,h=null;0<=e?(f=c[d].substring(0,e),h=c[d].substring(e+1)):f=c[d];b(f,h?sa(h):"")}}
function zd(a){if(a[1]){var b=a[0],c=b.indexOf("#");0<=c&&(a.push(b.substr(c)),a[0]=b=b.substr(0,c));c=b.indexOf("?");0>c?a[1]="?":c==b.length-1&&(a[1]=void 0)}return a.join("")}
function Ad(a,b,c){if(da(b))for(var d=0;d<b.length;d++)Ad(a,String(b[d]),c);else null!=b&&c.push("&",a,""===b?"":"=",encodeURIComponent(String(b)))}
function Bd(a,b,c){for(c=c||0;c<b.length;c+=2)Ad(b[c],b[c+1],a);return a}
function Cd(a,b){for(var c in b)Ad(c,b[c],a);return a}
function Dd(a){a=Cd([],a);a[0]="";return a.join("")}
function Ed(a,b){return zd(2==arguments.length?Bd([a],arguments[1],0):Bd([a],arguments,1))}
function Fd(a,b){return zd(Cd([a],b))}
;function Gd(a){"?"==a.charAt(0)&&(a=a.substr(1));a=a.split("&");for(var b={},c=0,d=a.length;c<d;c++){var e=a[c].split("=");if(1==e.length&&e[0]||2==e.length){var f=sa(e[0]||""),e=sa(e[1]||"");f in b?da(b[f])?Ua(b[f],e):b[f]=[b[f],e]:b[f]=e}}return b}
function Hd(a,b){var c=a.split("#",2);a=c[0];var c=1<c.length?"#"+c[1]:"",d=a.split("?",2);a=d[0];var d=Gd(d[1]||""),e;for(e in b)d[e]=b[e];return Fd(a,d)+c}
function Id(a){a=xd(a);a=null===a?null:a.split(".").reverse();return(null===a?!1:"com"==a[0]&&a[1].match(/^youtube(?:-nocookie)?$/)?!0:!1)||(null===a?!1:"google"==a[1]?!0:"google"==a[2]?"au"==a[0]&&"com"==a[1]?!0:"uk"==a[0]&&"co"==a[1]?!0:!1:!1)}
;var Jd=null;"undefined"!=typeof XMLHttpRequest?Jd=function(){return new XMLHttpRequest}:"undefined"!=typeof ActiveXObject&&(Jd=function(){return new ActiveXObject("Microsoft.XMLHTTP")});
function Kd(a){switch(a&&"status"in a?a.status:-1){case 200:case 201:case 202:case 203:case 204:case 205:case 206:case 304:return!0;default:return!1}}
;function Ld(a,b,c,d,e,f,h){function k(){4==(m&&"readyState"in m?m.readyState:0)&&b&&cc(b)(m)}
var m=Jd&&Jd();if(!("open"in m))return null;"onloadend"in m?m.addEventListener("loadend",k,!1):m.onreadystatechange=k;c=(c||"GET").toUpperCase();d=d||"";m.open(c,a,!0);f&&(m.responseType=f);h&&(m.withCredentials=!0);f="POST"==c;if(e=Md(a,e))for(var n in e)m.setRequestHeader(n,e[n]),"content-type"==n.toLowerCase()&&(f=!1);f&&m.setRequestHeader("Content-Type","application/x-www-form-urlencoded");m.send(d);return m}
function Md(a,b){b=b||{};var c;c||(c=window.location.href);var d=a.match(wd)[1]||null,e=xd(a);d&&e?(d=c,c=a.match(wd),d=d.match(wd),c=c[3]==d[3]&&c[1]==d[1]&&c[4]==d[4]):c=e?xd(c)==e&&(Number(c.match(wd)[4]||null)||null)==(Number(a.match(wd)[4]||null)||null):!0;for(var f in Nd){if((e=d=F(Nd[f]))&&!(e=c)){var e=f,h=F("CORS_HEADER_WHITELIST")||{},k=xd(a);e=k?(h=h[k])?z(h,e):!1:!0}e&&(b[f]=d)}return b}
function Od(a,b){var c=F("XSRF_FIELD_NAME",void 0),d;b.headers&&(d=b.headers["Content-Type"]);return!b.Pe&&(!xd(a)||b.withCredentials||xd(a)==document.location.hostname)&&"POST"==b.method&&(!d||"application/x-www-form-urlencoded"==d)&&!(b.J&&b.J[c])}
function Pd(a,b){var c=b.format||"JSON";b.Qe&&(a=document.location.protocol+"//"+document.location.hostname+(document.location.port?":"+document.location.port:"")+a);var d=F("XSRF_FIELD_NAME",void 0),e=F("XSRF_TOKEN",void 0),f=b.Qb;f&&(f[d]&&delete f[d],a=Hd(a,f||{}));var h=b.postBody||"",f=b.J;Od(a,b)&&(f||(f={}),f[d]=e);f&&u(h)&&(d=Gd(h),mb(d,f),h=b.xc&&"JSON"==b.xc?JSON.stringify(d):Dd(d));var k=!1,m,n=Ld(a,function(a){if(!k){k=!0;m&&I(m);var d=Kd(a),e=null;if(d||400<=a.status&&500>a.status)e=
Qd(c,a,b.Oe);if(d)a:{switch(c){case "XML":d=0==parseInt(e&&e.return_code,10);break a;case "RAW":d=!0;break a}d=!!e}var e=e||{},f=b.context||l;d?b.Z&&b.Z.call(f,a,e):b.onError&&b.onError.call(f,a,e);b.Mb&&b.Mb.call(f,a,e)}},b.method,h,b.headers,b.responseType,b.withCredentials);
b.Ca&&0<b.timeout&&(m=G(function(){k||(k=!0,n.abort(),I(m),b.Ca.call(b.context||l,n))},b.timeout));
return n}
function Qd(a,b,c){var d=null;switch(a){case "JSON":a=b.responseText;b=b.getResponseHeader("Content-Type")||"";a&&0<=b.indexOf("json")&&(d=pd(a));break;case "XML":if(b=(b=b.responseXML)?Rd(b):null)d={},y(b.getElementsByTagName("*"),function(a){d[a.tagName]=Sd(a)})}c&&Td(d);
return d}
function Td(a){if(ha(a))for(var b in a){var c;(c="html_content"==b)||(c=b.length-5,c=0<=c&&b.indexOf("_html",c)==c);c?a[b]=Ec(sb("HTML that is escaped and sanitized server-side and passed through yt.net.ajax"),a[b]):Td(a[b])}}
function Rd(a){return a?(a=("responseXML"in a?a.responseXML:a).getElementsByTagName("root"))&&0<a.length?a[0]:null:null}
function Sd(a){var b="";y(a.childNodes,function(a){b+=a.nodeValue});
return b}
var Nd={"X-YouTube-Client-Name":"INNERTUBE_CONTEXT_CLIENT_NAME","X-YouTube-Client-Version":"INNERTUBE_CONTEXT_CLIENT_VERSION","X-Youtube-Identity-Token":"XSRF_TOKEN","X-YouTube-Page-CL":"PAGE_CL","X-YouTube-Page-Label":"PAGE_BUILD_LABEL","X-YouTube-Variants-Checksum":"VARIANTS_CHECKSUM"};var Ud={},Vd=0;function Wd(a,b){isNaN(b)&&(b=void 0);var c=r("yt.scheduler.instance.addJob");return c?c(a,1,b):void 0===b?(a(),NaN):G(a,b||0)}
;var Xd=[],Yd=!1;function Zd(){function a(){Yd=!0;"google_ad_status"in window?ac("DCLKSTAT",1):ac("DCLKSTAT",2)}
sc("//static.doubleclick.net/instream/ad_status.js",a);Xd.push(Wd(function(){Yd||"google_ad_status"in window||(zc("//static.doubleclick.net/instream/ad_status.js",a),ac("DCLKSTAT",3))},5E3))}
function $d(){return parseInt(F("DCLKSTAT",0),10)}
;function ae(a){if(a.classList)return a.classList;a=a.className;return u(a)&&a.match(/\S+/g)||[]}
function be(a,b){return a.classList?a.classList.contains(b):z(ae(a),b)}
function ce(a,b){a.classList?a.classList.add(b):be(a,b)||(a.className+=0<a.className.length?" "+b:b)}
function de(a,b){a.classList?a.classList.remove(b):be(a,b)&&(a.className=Ja(ae(a),function(a){return a!=b}).join(" "))}
function ee(a,b,c){c?ce(a,b):de(a,b)}
;function fe(a,b){this.x=p(a)?a:0;this.y=p(b)?b:0}
fe.prototype.clone=function(){return new fe(this.x,this.y)};
fe.prototype.ceil=function(){this.x=Math.ceil(this.x);this.y=Math.ceil(this.y);return this};
fe.prototype.floor=function(){this.x=Math.floor(this.x);this.y=Math.floor(this.y);return this};
fe.prototype.round=function(){this.x=Math.round(this.x);this.y=Math.round(this.y);return this};function ge(a,b){this.width=a;this.height=b}
g=ge.prototype;g.clone=function(){return new ge(this.width,this.height)};
g.Xc=function(){return this.width*this.height};
g.aspectRatio=function(){return this.width/this.height};
g.isEmpty=function(){return!this.Xc()};
g.ceil=function(){this.width=Math.ceil(this.width);this.height=Math.ceil(this.height);return this};
g.floor=function(){this.width=Math.floor(this.width);this.height=Math.floor(this.height);return this};
g.round=function(){this.width=Math.round(this.width);this.height=Math.round(this.height);return this};!$c&&!K||K&&ld(9)||$c&&kd("1.9.1");var he=K&&!kd("9");function ie(a){return a?new je(ke(a)):qa||(qa=new je)}
function le(a){return u(a)?document.getElementById(a):a}
function me(a){var b=document;return u(a)?b.getElementById(a):a}
function ne(a){var b=document;return b.querySelectorAll&&b.querySelector?b.querySelectorAll("."+a):oe(a,void 0)}
function oe(a,b){var c,d,e,f;c=document;c=b||c;if(c.querySelectorAll&&c.querySelector&&a)return c.querySelectorAll(""+(a?"."+a:""));if(a&&c.getElementsByClassName){var h=c.getElementsByClassName(a);return h}h=c.getElementsByTagName("*");if(a){f={};for(d=e=0;c=h[d];d++){var k=c.className;"function"==typeof k.split&&z(k.split(/\s+/),a)&&(f[e++]=c)}f.length=e;return f}return h}
function pe(a){var b=a.scrollingElement?a.scrollingElement:!ad&&qe(a)?a.documentElement:a.body||a.documentElement;a=a.parentWindow||a.defaultView;return K&&kd("10")&&a.pageYOffset!=b.scrollTop?new fe(b.scrollLeft,b.scrollTop):new fe(a.pageXOffset||b.scrollLeft,a.pageYOffset||b.scrollTop)}
function qe(a){return"CSS1Compat"==a.compatMode}
function re(a){for(var b;b=a.firstChild;)a.removeChild(b)}
function se(a){if(!a)return null;if(a.firstChild)return a.firstChild;for(;a&&!a.nextSibling;)a=a.parentNode;return a?a.nextSibling:null}
function te(a){if(!a)return null;if(!a.previousSibling)return a.parentNode;for(a=a.previousSibling;a&&a.lastChild;)a=a.lastChild;return a}
function ke(a){return 9==a.nodeType?a:a.ownerDocument||a.document}
function ue(a,b){if("textContent"in a)a.textContent=b;else if(3==a.nodeType)a.data=b;else if(a.firstChild&&3==a.firstChild.nodeType){for(;a.lastChild!=a.firstChild;)a.removeChild(a.lastChild);a.firstChild.data=b}else{re(a);var c=ke(a);a.appendChild(c.createTextNode(String(b)))}}
var ve={SCRIPT:1,STYLE:1,HEAD:1,IFRAME:1,OBJECT:1},we={IMG:" ",BR:"\n"};function xe(a){if(he&&null!==a&&"innerText"in a)a=a.innerText.replace(/(\r\n|\r|\n)/g,"\n");else{var b=[];ye(a,b,!0);a=b.join("")}a=a.replace(/ \xAD /g," ").replace(/\xAD/g,"");a=a.replace(/\u200B/g,"");he||(a=a.replace(/ +/g," "));" "!=a&&(a=a.replace(/^\s*/,""));return a}
function ye(a,b,c){if(!(a.nodeName in ve))if(3==a.nodeType)c?b.push(String(a.nodeValue).replace(/(\r\n|\r|\n)/g,"")):b.push(a.nodeValue);else if(a.nodeName in we)b.push(we[a.nodeName]);else for(a=a.firstChild;a;)ye(a,b,c),a=a.nextSibling}
function ze(a){var b=Ae.Oc;return b?Be(a,function(a){return!b||u(a.className)&&z(a.className.split(/\s+/),b)},!0,void 0):null}
function Be(a,b,c,d){c||(a=a.parentNode);for(c=0;a&&(null==d||c<=d);){if(b(a))return a;a=a.parentNode;c++}return null}
function je(a){this.b=a||l.document||document}
je.prototype.createElement=function(a){return this.b.createElement(a)};
je.prototype.appendChild=function(a,b){a.appendChild(b)};
je.prototype.isElement=function(a){return ha(a)&&1==a.nodeType};
je.prototype.contains=function(a,b){if(!a||!b)return!1;if(a.contains&&1==b.nodeType)return a==b||a.contains(b);if("undefined"!=typeof a.compareDocumentPosition)return a==b||!!(a.compareDocumentPosition(b)&16);for(;b&&a!=b;)b=b.parentNode;return b==a};var Ce=r("yt.dom.getNextId_");if(!Ce){Ce=function(){return++De};
q("yt.dom.getNextId_",Ce,void 0);var De=0}function Ee(){var a=document,b;La(["fullscreenElement","webkitFullscreenElement","mozFullScreenElement","msFullscreenElement"],function(c){b=a[c];return!!b});
return b}
;function Fe(a){this.type="";this.state=this.source=this.data=this.currentTarget=this.relatedTarget=this.target=null;this.charCode=this.keyCode=0;this.shiftKey=this.ctrlKey=this.altKey=!1;this.clientY=this.clientX=0;this.changedTouches=null;if(a=a||window.event){this.event=a;for(var b in a)b in Ge||(this[b]=a[b]);(b=a.target||a.srcElement)&&3==b.nodeType&&(b=b.parentNode);this.target=b;if(b=a.relatedTarget)try{b=b.nodeName?b:null}catch(c){b=null}else"mouseover"==this.type?b=a.fromElement:"mouseout"==
this.type&&(b=a.toElement);this.relatedTarget=b;this.clientX=void 0!=a.clientX?a.clientX:a.pageX;this.clientY=void 0!=a.clientY?a.clientY:a.pageY;this.keyCode=a.keyCode?a.keyCode:a.which;this.charCode=a.charCode||("keypress"==this.type?this.keyCode:0);this.altKey=a.altKey;this.ctrlKey=a.ctrlKey;this.shiftKey=a.shiftKey}}
Fe.prototype.preventDefault=function(){this.event&&(this.event.returnValue=!1,this.event.preventDefault&&this.event.preventDefault())};
Fe.prototype.stopPropagation=function(){this.event&&(this.event.cancelBubble=!0,this.event.stopPropagation&&this.event.stopPropagation())};
Fe.prototype.stopImmediatePropagation=function(){this.event&&(this.event.cancelBubble=!0,this.event.stopImmediatePropagation&&this.event.stopImmediatePropagation())};
var Ge={stopImmediatePropagation:1,stopPropagation:1,preventMouseEvent:1,preventManipulation:1,preventDefault:1,layerX:1,layerY:1,scale:1,rotation:1,webkitMovementX:1,webkitMovementY:1};var gb=r("yt.events.listeners_")||{};q("yt.events.listeners_",gb,void 0);var He=r("yt.events.counter_")||{count:0};q("yt.events.counter_",He,void 0);function Ie(a,b,c,d){a.addEventListener&&("mouseenter"!=b||"onmouseenter"in document?"mouseleave"!=b||"onmouseenter"in document?"mousewheel"==b&&"MozBoxSizing"in document.documentElement.style&&(b="MozMousePixelScroll"):b="mouseout":b="mouseover");return fb(function(e){return e[0]==a&&e[1]==b&&e[2]==c&&e[4]==!!d})}
function M(a,b,c,d){if(!a||!a.addEventListener&&!a.attachEvent)return"";d=!!d;var e=Ie(a,b,c,d);if(e)return e;var e=++He.count+"",f=!("mouseenter"!=b&&"mouseleave"!=b||!a.addEventListener||"onmouseenter"in document),h;h=f?function(d){d=new Fe(d);if(!Be(d.relatedTarget,function(b){return b==a},!0))return d.currentTarget=a,d.type=b,c.call(a,d)}:function(b){b=new Fe(b);
b.currentTarget=a;return c.call(a,b)};
h=cc(h);a.addEventListener?("mouseenter"==b&&f?b="mouseover":"mouseleave"==b&&f?b="mouseout":"mousewheel"==b&&"MozBoxSizing"in document.documentElement.style&&(b="MozMousePixelScroll"),a.addEventListener(b,h,d)):a.attachEvent("on"+b,h);gb[e]=[a,b,c,h,d];return e}
function Je(a){a&&("string"==typeof a&&(a=[a]),y(a,function(a){if(a in gb){var c=gb[a],d=c[0],e=c[1],f=c[3],c=c[4];d.removeEventListener?d.removeEventListener(e,f,c):d.detachEvent&&d.detachEvent("on"+e,f);delete gb[a]}}))}
;function Ke(){if(null==r("_lact",window)){var a=parseInt(F("LACT"),10),a=isFinite(a)?w()-Math.max(a,0):-1;q("_lact",a,window);-1==a&&Le();M(document,"keydown",Le);M(document,"keyup",Le);M(document,"mousedown",Le);M(document,"mouseup",Le);mc("page-mouse",Le);mc("page-scroll",Le);mc("page-resize",Le)}}
function Le(){null==r("_lact",window)&&(Ke(),r("_lact",window));var a=w();q("_lact",a,window);J("USER_ACTIVE")}
function Me(){var a=r("_lact",window);return null==a?-1:Math.max(w()-a,0)}
;function Ne(){}
;function Oe(a){this.b=a||{cookie:""}}
var Pe=/\s*;\s*/;g=Oe.prototype;g.isEnabled=function(){return navigator.cookieEnabled};
g.set=function(a,b,c,d,e,f){if(/[;=\s]/.test(a))throw Error('Invalid cookie name "'+a+'"');if(/[;\r\n]/.test(b))throw Error('Invalid cookie value "'+b+'"');p(c)||(c=-1);e=e?";domain="+e:"";d=d?";path="+d:"";f=f?";secure":"";c=0>c?"":0==c?";expires="+(new Date(1970,1,1)).toUTCString():";expires="+(new Date(w()+1E3*c)).toUTCString();this.b.cookie=a+"="+b+e+d+c+f};
g.get=function(a,b){for(var c=a+"=",d=(this.b.cookie||"").split(Pe),e=0,f;f=d[e];e++){if(0==f.lastIndexOf(c,0))return f.substr(c.length);if(f==a)return""}return b};
g.remove=function(a,b,c){var d=p(this.get(a));this.set(a,"",0,b,c);return d};
g.ha=function(){return Qe(this).keys};
g.O=function(){return Qe(this).values};
g.isEmpty=function(){return!this.b.cookie};
g.N=function(){return this.b.cookie?(this.b.cookie||"").split(Pe).length:0};
g.Qa=function(a){for(var b=Qe(this).values,c=0;c<b.length;c++)if(b[c]==a)return!0;return!1};
g.clear=function(){for(var a=Qe(this).keys,b=a.length-1;0<=b;b--)this.remove(a[b])};
function Qe(a){a=(a.b.cookie||"").split(Pe);for(var b=[],c=[],d,e,f=0;e=a[f];f++)d=e.indexOf("="),-1==d?(b.push(""),c.push(e)):(b.push(e.substring(0,d)),c.push(e.substring(d+1)));return{keys:b,values:c}}
var Re=new Oe("undefined"==typeof document?null:document);Re.f=3950;function Se(a,b,c){Re.set(""+a,b,c,"/","youtube.com")}
;function Te(a,b,c){var d=F("EVENT_ID");d&&(b||(b={}),b.ei||(b.ei=d));if(b){var d=F("VALID_SESSION_TEMPDATA_DOMAINS",[]),e=xd(window.location.href);e&&d.push(e);e=xd(a);if(z(d,e)||!e&&0==a.lastIndexOf("/",0)){var f=a.match(wd),d=f[5],e=f[6],f=f[7],h="";d&&(h+=d);e&&(h+="?"+e);f&&(h+="#"+f);d=h;e=d.indexOf("#");if(d=0>e?d:d.substr(0,e))e=F("ST_BASE36",!0),f=F("ST_SHORT",!0)?"ST-":"s_tempdata-",d=f=e?f+Ha(d).toString(36):f+Ha(d),e=b?Dd(b):"",Se(d,e,5),b&&(b=b.itct||b.ved,d=r("yt.logging.screenreporter.storeParentElement"),
b&&d&&d(new Ne))}}if(c)return!1;(window.ytspf||{}).enabled?spf.navigate(a):(c=window.location,a=Fd(a,{})+"",a=a instanceof tb?a:xb(a),c.href=vb(a));return!0}
;function Ue(a){a=a||{};this.url=a.url||"";this.urlV9As2=a.url_v9as2||"";this.args=a.args||jb(Ve);this.assets=a.assets||{};this.attrs=a.attrs||jb(We);this.params=a.params||jb(Xe);this.minVersion=a.min_version||"8.0.0";this.fallback=a.fallback||null;this.fallbackMessage=a.fallbackMessage||null;this.html5=!!a.html5;this.disable=a.disable||{};this.loaded=!!a.loaded;this.messages=a.messages||{}}
var Ve={enablejsapi:1},We={},Xe={allowscriptaccess:"always",allowfullscreen:"true",bgcolor:"#000000"};function Ye(a){a instanceof Ue||(a=new Ue(a));return a}
Ue.prototype.clone=function(){var a=new Ue,b;for(b in this)if(this.hasOwnProperty(b)){var c=this[b];"object"==ca(c)?a[b]=jb(c):a[b]=c}return a};function Ze(a,b,c,d){this.top=a;this.right=b;this.bottom=c;this.left=d}
g=Ze.prototype;g.getHeight=function(){return this.bottom-this.top};
g.clone=function(){return new Ze(this.top,this.right,this.bottom,this.left)};
g.contains=function(a){return this&&a?a instanceof Ze?a.left>=this.left&&a.right<=this.right&&a.top>=this.top&&a.bottom<=this.bottom:a.x>=this.left&&a.x<=this.right&&a.y>=this.top&&a.y<=this.bottom:!1};
g.ceil=function(){this.top=Math.ceil(this.top);this.right=Math.ceil(this.right);this.bottom=Math.ceil(this.bottom);this.left=Math.ceil(this.left);return this};
g.floor=function(){this.top=Math.floor(this.top);this.right=Math.floor(this.right);this.bottom=Math.floor(this.bottom);this.left=Math.floor(this.left);return this};
g.round=function(){this.top=Math.round(this.top);this.right=Math.round(this.right);this.bottom=Math.round(this.bottom);this.left=Math.round(this.left);return this};function $e(a,b,c,d){this.left=a;this.top=b;this.width=c;this.height=d}
g=$e.prototype;g.clone=function(){return new $e(this.left,this.top,this.width,this.height)};
g.contains=function(a){return a instanceof $e?this.left<=a.left&&this.left+this.width>=a.left+a.width&&this.top<=a.top&&this.top+this.height>=a.top+a.height:a.x>=this.left&&a.x<=this.left+this.width&&a.y>=this.top&&a.y<=this.top+this.height};
g.ceil=function(){this.left=Math.ceil(this.left);this.top=Math.ceil(this.top);this.width=Math.ceil(this.width);this.height=Math.ceil(this.height);return this};
g.floor=function(){this.left=Math.floor(this.left);this.top=Math.floor(this.top);this.width=Math.floor(this.width);this.height=Math.floor(this.height);return this};
g.round=function(){this.left=Math.round(this.left);this.top=Math.round(this.top);this.width=Math.round(this.width);this.height=Math.round(this.height);return this};function af(a){af[" "](a);return a}
af[" "]=t;function bf(a,b){var c=ke(a);return c.defaultView&&c.defaultView.getComputedStyle&&(c=c.defaultView.getComputedStyle(a,null))?c[b]||c.getPropertyValue(b)||"":""}
function cf(a,b){return bf(a,b)||(a.currentStyle?a.currentStyle[b]:null)||a.style&&a.style[b]}
function df(a){var b;try{b=a.getBoundingClientRect()}catch(c){return{left:0,top:0,right:0,bottom:0}}K&&a.ownerDocument.body&&(a=a.ownerDocument,b.left-=a.documentElement.clientLeft+a.body.clientLeft,b.top-=a.documentElement.clientTop+a.body.clientTop);return b}
function ef(a,b){"number"==typeof a&&(a=(b?Math.round(a):a)+"px");return a}
function ff(a){var b=gf;if("none"!=cf(a,"display"))return b(a);var c=a.style,d=c.display,e=c.visibility,f=c.position;c.visibility="hidden";c.position="absolute";c.display="inline";a=b(a);c.display=d;c.position=f;c.visibility=e;return a}
function gf(a){var b=a.offsetWidth,c=a.offsetHeight,d=ad&&!b&&!c;return p(b)&&!d||!a.getBoundingClientRect?new ge(b,c):(a=df(a),new ge(a.right-a.left,a.bottom-a.top))}
function hf(a,b){if(/^\d+px?$/.test(b))return parseInt(b,10);var c=a.style.left,d=a.runtimeStyle.left;a.runtimeStyle.left=a.currentStyle.left;a.style.left=b;var e=a.style.pixelLeft;a.style.left=c;a.runtimeStyle.left=d;return e}
function jf(a,b){var c=a.currentStyle?a.currentStyle[b]:null;return c?hf(a,c):0}
var kf={thin:2,medium:4,thick:6};function lf(a,b){if("none"==(a.currentStyle?a.currentStyle[b+"Style"]:null))return 0;var c=a.currentStyle?a.currentStyle[b+"Width"]:null;return c in kf?kf[c]:hf(a,c)}
;var mf=A("Firefox"),nf=Xc()||A("iPod"),of=A("iPad"),pf=A("Android")&&!(qb()||A("Firefox")||A("Opera")||A("Silk")),qf=qb(),rf=A("Safari")&&!(qb()||A("Coast")||A("Opera")||A("Edge")||A("Silk")||A("Android"))&&!(Xc()||A("iPad")||A("iPod"));function sf(){var a;if(a=Re.get("PREF",void 0)){a=unescape(a).split("&");for(var b=0;b<a.length;b++){var c=a[b].split("="),d=c[0];(c=c[1])&&(tf[d]=c.toString())}}}
ba(sf);var tf=r("yt.prefs.UserPrefs.prefs_")||{};q("yt.prefs.UserPrefs.prefs_",tf,void 0);function uf(a){if(/^f([1-9][0-9]*)$/.test(a))throw"ExpectedRegexMatch: "+a;}
function vf(a){if(!/^\w+$/.test(a))throw"ExpectedRegexMismatch: "+a;}
function wf(a){a=void 0!==tf[a]?tf[a].toString():null;return null!=a&&/^[A-Fa-f0-9]+$/.test(a)?parseInt(a,16):null}
sf.prototype.get=function(a,b){vf(a);uf(a);var c=void 0!==tf[a]?tf[a].toString():null;return null!=c?c:b?b:""};
sf.prototype.set=function(a,b){vf(a);uf(a);if(null==b)throw"ExpectedNotNull";tf[a]=b.toString()};
function xf(a,b){return!!((wf("f"+(Math.floor(b/31)+1))||0)&1<<b%31)}
sf.prototype.remove=function(a){vf(a);uf(a);delete tf[a]};
sf.prototype.clear=function(){tf={}};function yf(a,b){(a=le(a))&&a.style&&(a.style.display=b?"":"none",ee(a,"hid",!b))}
function zf(a){y(arguments,function(a){!ea(a)||a instanceof Element?yf(a,!0):y(a,function(a){zf(a)})})}
function Af(a){y(arguments,function(a){!ea(a)||a instanceof Element?yf(a,!1):y(a,function(a){Af(a)})})}
;function Bf(){this.g=this.f=this.b=0;this.h="";var a=r("window.navigator.plugins"),b=r("window.navigator.mimeTypes"),a=a&&a["Shockwave Flash"],b=b&&b["application/x-shockwave-flash"],b=a&&b&&b.enabledPlugin&&a.description||"";if(a=b){var c=a.indexOf("Shockwave Flash");0<=c&&(a=a.substr(c+15));for(var c=a.split(" "),d="",a="",e=0,f=c.length;e<f;e++)if(d)if(a)break;else a=c[e];else d=c[e];d=d.split(".");c=parseInt(d[0],10)||0;d=parseInt(d[1],10)||0;e=0;if("r"==a.charAt(0)||"d"==a.charAt(0))e=parseInt(a.substr(1),
10)||0;a=[c,d,e]}else a=[0,0,0];this.h=b;b=a;this.b=b[0];this.f=b[1];this.g=b[2];if(0>=this.b){var h,k,m,n;if(gc)try{h=new ActiveXObject("ShockwaveFlash.ShockwaveFlash")}catch(H){h=null}else m=document.body,n=document.createElement("object"),n.setAttribute("type","application/x-shockwave-flash"),h=m.appendChild(n);if(h&&"GetVariable"in h)try{k=h.GetVariable("$version")}catch(H){k=""}m&&n&&m.removeChild(n);(h=k||"")?(h=h.split(" ")[1].split(","),h=[parseInt(h[0],10)||0,parseInt(h[1],10)||0,parseInt(h[2],
10)||0]):h=[0,0,0];this.b=h[0];this.f=h[1];this.g=h[2]}}
ba(Bf);function Cf(a,b,c,d){b="string"==typeof b?b.split("."):[b,c,d];b[0]=parseInt(b[0],10)||0;b[1]=parseInt(b[1],10)||0;b[2]=parseInt(b[2],10)||0;return a.b>b[0]||a.b==b[0]&&a.f>b[1]||a.b==b[0]&&a.f==b[1]&&a.g>=b[2]}
function Df(a){return-1<a.h.indexOf("Gnash")&&-1==a.h.indexOf("AVM2")||9==a.b&&1==a.f||9==a.b&&0==a.f&&1==a.g?!1:9<=a.b}
function Ef(a){return cd?!Cf(a,11,2):bd?!Cf(a,11,3):!Df(a)}
;function Ff(a,b,c){if(b){a=u(a)?me(a):a;var d=jb(c.attrs);d.tabindex=0;var e=jb(c.params);e.flashvars=Dd(c.args);if(gc){d.classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";e.movie=b;b=document.createElement("object");for(var f in d)b.setAttribute(f,d[f]);for(f in e)d=document.createElement("param"),d.setAttribute("name",f),d.setAttribute("value",e[f]),b.appendChild(d)}else{d.type="application/x-shockwave-flash";d.src=b;b=document.createElement("embed");b.setAttribute("name",d.id);for(f in d)b.setAttribute(f,
d[f]);for(f in e)b.setAttribute(f,e[f])}e=document.createElement("div");e.appendChild(b);a.innerHTML=e.innerHTML}}
function Gf(a,b,c){if(a&&a.attrs&&a.attrs.id){a=Ye(a);var d=!!b,e=le(a.attrs.id),f=e?e.parentNode:null;if(e&&f){if(window!=window.top){var h=null;if(document.referrer){var k=document.referrer.substring(0,128);Id(k)||(h=k)}else h="unknown";h&&(d=!0,a.args.framer=h)}h=Bf.getInstance();if(Cf(h,a.minVersion)){var k=Hf(a,h),m="";-1<navigator.userAgent.indexOf("Sony/COM2")||(m=e.getAttribute("src")||e.movie);(m!=k||d)&&Ff(f,k,a);Ef(h)&&If()}else Jf(f,a,h);c&&c()}else G(function(){Gf(a,b,c)},50)}}
function Jf(a,b,c){0==c.b&&b.fallback?b.fallback():0==c.b&&b.fallbackMessage?b.fallbackMessage():a.innerHTML='<div id="flash-upgrade">'+ec()+"</div>"}
function Hf(a,b){return Df(b)&&a.url||(-1<navigator.userAgent.indexOf("Sony/COM2")&&!Cf(b,9,1,58)?!1:!0)&&a.urlV9As2||a.url}
function If(){var a=le("flash10-promo-div"),b=xf(sf.getInstance(),107);a&&!b&&zf(a)}
;function Kf(a){if(window.spf){var b=a.match(Lf);spf.style.load(a,b?b[1]:"",void 0)}else Mf(a)}
function Mf(a){var b=Nf(a),c=document.getElementById(b),d=c&&B(c,"loaded");d||c&&!d||(c=Of(a,b,function(){B(c,"loaded")||(Eb(c,"loaded","true"),J(b),G(oa(rc,b),0))}))}
function Of(a,b,c){var d=document.createElement("link");d.id=b;d.rel="stylesheet";d.onload=function(){c&&setTimeout(c,0)};
Db(d,a);(document.getElementsByTagName("head")[0]||document.body).appendChild(d);return d}
function Nf(a){var b=document.createElement("a");Db(b,a);a=b.href.replace(/^[a-zA-Z]+:\/\//,"//");return"css-"+Ha(a)}
var Lf=/cssbin\/(?:debug-)?([a-zA-Z0-9_-]+?)(?:-2x|-web|-rtl|-vfl|.css)/;var Pf;var Qf=nb,Qf=Qf.toLowerCase();if(-1!=Qf.indexOf("android")){var Rf=Qf.match(/android\D*(\d\.\d)[^\;|\)]*[\;\)]/);if(Rf)Pf=Number(Rf[1]);else{var Sf={cupcake:1.5,donut:1.6,eclair:2,froyo:2.2,gingerbread:2.3,honeycomb:3,"ice cream sandwich":4,jellybean:4.1},Tf=Qf.match("("+db(Sf).join("|")+")");Pf=Tf?Sf[Tf[0]]:0}}else Pf=void 0;var Uf=nf||of;var Vf=['video/mp4; codecs="avc1.42001E, mp4a.40.2"','video/webm; codecs="vp8.0, vorbis"'],Wf=['audio/mp4; codecs="mp4a.40.2"'];function Xf(a){C.call(this);this.b=[];this.f=a||this}
x(Xf,C);function Yf(a,b,c,d){d=cc(v(d,a.f));b.addEventListener(c,d);a.b.push({target:b,name:c,yb:d})}
Xf.prototype.pb=function(a){for(var b=0;b<this.b.length;b++)if(this.b[b]==a){this.b.splice(b,1);a.target.removeEventListener(a.name,a.yb);break}};
function Zf(a){for(;a.b.length;){var b=a.b.pop();b.target.removeEventListener(b.name,b.yb)}}
Xf.prototype.w=function(){Zf(this);Xf.A.w.call(this)};function $f(a){return F("EXPERIMENT_FLAGS",{})[a]}
;function ag(){this.b={apiaryHost:F("APIARY_HOST",void 0),Me:F("APIARY_HOST_FIRSTPARTY",void 0),gapiHintOverride:F("GAPI_HINT_OVERRIDE"),gapiHintParams:F("GAPI_HINT_PARAMS",void 0),innertubeApiKey:F("INNERTUBE_API_KEY",void 0),innertubeApiVersion:F("INNERTUBE_API_VERSION",void 0),nd:F("INNERTUBE_CONTEXT_CLIENT_NAME","WEB"),innertubeContextClientVersion:F("INNERTUBE_CONTEXT_CLIENT_VERSION",void 0),pd:F("INNERTUBE_CONTEXT_HL",void 0),od:F("INNERTUBE_CONTEXT_GL",void 0),pe:F("XHR_APIARY_HOST",void 0)}}
;var bg={log_event:"events",log_interaction:"interactions"},cg={},dg={},eg=0,fg=r("yt.logging.transport.logsQueue_")||{};q("yt.logging.transport.logsQueue_",fg,void 0);
function gg(){I(eg);if(!hb(fg)){for(var a in fg){var b=cg[a];if(!b){var c=dg[a];if(!c)continue;b=new c;cg[a]=b}c=b.b;c={client:{hl:c.pd,gl:c.od,clientName:c.nd,clientVersion:c.innertubeContextClientVersion}};F("DELEGATED_SESSION_ID")&&(c.user={onBehalfOfUser:F("DELEGATED_SESSION_ID")});c={context:c};c.requestTimeMs=Math.round(fc());c[bg[a]]=fg[a];var d=b,b={},d="//"+d.b.pe+("/youtubei/"+d.b.innertubeApiVersion+"/"+a)+"?alt=json&key="+d.b.innertubeApiKey,c={headers:{"Content-Type":"application/json",
"X-Goog-Visitor-Id":F("VISITOR_DATA")},J:c,xc:"JSON",Ca:b.Ca,Z:b.Z,onError:b.onError,timeout:b.timeout,method:"POST"};c.J||(c.J={});Pd(d,c);delete fg[a]}hb(fg)||hg()}}
function hg(){I(eg);eg=G(gg,F("VISIBILITY_TIMEOUT",1E4))}
;function ig(a,b,c){var d={};d.eventTimeMs=Math.round(c||fc());d[a]=b;fg.log_event=fg.log_event||[];a=fg.log_event;a.push(d);dg.log_event=ag;20<=a.length?gg():hg()}
;function jg(a,b){this.f=this.o=this.h="";this.j=null;this.i=this.b="";this.l=!1;var c;a instanceof jg?(this.l=p(b)?b:a.l,kg(this,a.h),this.o=a.o,lg(this,a.f),mg(this,a.j),this.b=a.b,ng(this,a.g.clone()),this.i=a.i):a&&(c=String(a).match(wd))?(this.l=!!b,kg(this,c[1]||"",!0),this.o=og(c[2]||""),lg(this,c[3]||"",!0),mg(this,c[4]),this.b=og(c[5]||"",!0),ng(this,c[6]||"",!0),this.i=og(c[7]||"")):(this.l=!!b,this.g=new pg(null,0,this.l))}
jg.prototype.toString=function(){var a=[],b=this.h;b&&a.push(qg(b,rg,!0),":");var c=this.f;if(c||"file"==b)a.push("//"),(b=this.o)&&a.push(qg(b,rg,!0),"@"),a.push(encodeURIComponent(String(c)).replace(/%25([0-9a-fA-F]{2})/g,"%$1")),c=this.j,null!=c&&a.push(":",String(c));if(c=this.b)this.f&&"/"!=c.charAt(0)&&a.push("/"),a.push(qg(c,"/"==c.charAt(0)?sg:tg,!0));(c=this.g.toString())&&a.push("?",c);(c=this.i)&&a.push("#",qg(c,ug));return a.join("")};
jg.prototype.resolve=function(a){var b=this.clone(),c=!!a.h;c?kg(b,a.h):c=!!a.o;c?b.o=a.o:c=!!a.f;c?lg(b,a.f):c=null!=a.j;var d=a.b;if(c)mg(b,a.j);else if(c=!!a.b){if("/"!=d.charAt(0))if(this.f&&!this.b)d="/"+d;else{var e=b.b.lastIndexOf("/");-1!=e&&(d=b.b.substr(0,e+1)+d)}e=d;if(".."==e||"."==e)d="";else if(-1!=e.indexOf("./")||-1!=e.indexOf("/.")){for(var d=0==e.lastIndexOf("/",0),e=e.split("/"),f=[],h=0;h<e.length;){var k=e[h++];"."==k?d&&h==e.length&&f.push(""):".."==k?((1<f.length||1==f.length&&
""!=f[0])&&f.pop(),d&&h==e.length&&f.push("")):(f.push(k),d=!0)}d=f.join("/")}else d=e}c?b.b=d:c=""!==a.g.toString();c?ng(b,og(a.g.toString())):c=!!a.i;c&&(b.i=a.i);return b};
jg.prototype.clone=function(){return new jg(this)};
function kg(a,b,c){a.h=c?og(b,!0):b;a.h&&(a.h=a.h.replace(/:$/,""))}
function lg(a,b,c){a.f=c?og(b,!0):b}
function mg(a,b){if(b){b=Number(b);if(isNaN(b)||0>b)throw Error("Bad port number "+b);a.j=b}else a.j=null}
function ng(a,b,c){b instanceof pg?(a.g=b,vg(a.g,a.l)):(c||(b=qg(b,wg)),a.g=new pg(b,0,a.l))}
function N(a,b,c){a.g.set(b,c)}
function xg(a,b,c){da(c)||(c=[String(c)]);yg(a.g,b,c)}
function zg(a){N(a,"zx",Math.floor(2147483648*Math.random()).toString(36)+Math.abs(Math.floor(2147483648*Math.random())^w()).toString(36));return a}
function Ag(a){return a instanceof jg?a.clone():new jg(a,void 0)}
function Bg(a,b,c,d){var e=new jg(null,void 0);a&&kg(e,a);b&&lg(e,b);c&&mg(e,c);d&&(e.b=d);return e}
function og(a,b){return a?b?decodeURI(a.replace(/%25/g,"%2525")):decodeURIComponent(a):""}
function qg(a,b,c){return u(a)?(a=encodeURI(a).replace(b,Cg),c&&(a=a.replace(/%25([0-9a-fA-F]{2})/g,"%$1")),a):null}
function Cg(a){a=a.charCodeAt(0);return"%"+(a>>4&15).toString(16)+(a&15).toString(16)}
var rg=/[#\/\?@]/g,tg=/[\#\?:]/g,sg=/[\#\?]/g,wg=/[\#\?@]/g,ug=/#/g;function pg(a,b,c){this.f=this.b=null;this.g=a||null;this.h=!!c}
function Dg(a){a.b||(a.b=new Kc,a.f=0,a.g&&yd(a.g,function(b,c){Eg(a,sa(b),c)}))}
g=pg.prototype;g.N=function(){Dg(this);return this.f};
function Eg(a,b,c){Dg(a);a.g=null;b=Fg(a,b);var d=a.b.get(b);d||a.b.set(b,d=[]);d.push(c);a.f=a.f+1}
g.remove=function(a){Dg(this);a=Fg(this,a);return Mc(this.b.f,a)?(this.g=null,this.f=this.f-this.b.get(a).length,this.b.remove(a)):!1};
g.clear=function(){this.b=this.g=null;this.f=0};
g.isEmpty=function(){Dg(this);return 0==this.f};
function Gg(a,b){Dg(a);b=Fg(a,b);return Mc(a.b.f,b)}
g.Qa=function(a){var b=this.O();return z(b,a)};
g.ha=function(){Dg(this);for(var a=this.b.O(),b=this.b.ha(),c=[],d=0;d<b.length;d++)for(var e=a[d],f=0;f<e.length;f++)c.push(b[d]);return c};
g.O=function(a){Dg(this);var b=[];if(u(a))Gg(this,a)&&(b=Sa(b,this.b.get(Fg(this,a))));else{a=this.b.O();for(var c=0;c<a.length;c++)b=Sa(b,a[c])}return b};
g.set=function(a,b){Dg(this);this.g=null;a=Fg(this,a);Gg(this,a)&&(this.f=this.f-this.b.get(a).length);this.b.set(a,[b]);this.f=this.f+1;return this};
g.get=function(a,b){var c=a?this.O(a):[];return 0<c.length?String(c[0]):b};
function yg(a,b,c){a.remove(b);0<c.length&&(a.g=null,a.b.set(Fg(a,b),Ta(c)),a.f=a.f+c.length)}
g.toString=function(){if(this.g)return this.g;if(!this.b)return"";for(var a=[],b=this.b.ha(),c=0;c<b.length;c++)for(var d=b[c],e=encodeURIComponent(String(d)),d=this.O(d),f=0;f<d.length;f++){var h=e;""!==d[f]&&(h+="="+encodeURIComponent(String(d[f])));a.push(h)}return this.g=a.join("&")};
g.clone=function(){var a=new pg;a.g=this.g;this.b&&(a.b=this.b.clone(),a.f=this.f);return a};
function Fg(a,b){var c=String(b);a.h&&(c=c.toLowerCase());return c}
function vg(a,b){b&&!a.h&&(Dg(a),a.g=null,a.b.forEach(function(a,b){var e=b.toLowerCase();b!=e&&(this.remove(b),yg(this,e,a))},a));
a.h=b}
g.extend=function(a){for(var b=0;b<arguments.length;b++)Rc(arguments[b],function(a,b){Eg(this,b,a)},this)};var Hg="corp.google.com googleplex.com youtube.com youtube-nocookie.com youtubeeducation.com borg.google.com prod.google.com sandbox.google.com books.googleusercontent.com docs.google.com drive.google.com mail.google.com photos.google.com plus.google.com lh2.google.com picasaweb.google.com play.google.com googlevideo.com talkgadget.google.com survey.g.doubleclick.net youtube.googleapis.com vevo.com".split(" "),Ig="";
function Jg(a){return a&&a==Ig?!0:(new RegExp("^(https?:)?//([a-z0-9-]{1,63}\\.)*("+Hg.join("|").replace(/\./g,".")+")(:[0-9]+)?([/?#]|$)","i")).test(a)?(Ig=a,!0):!1}
;var Kg={},Lg=0;function Mg(a){var b=new Image,c=""+Lg++;Kg[c]=b;b.onload=b.onerror=function(){delete Kg[c]};
b.src=a}
;function O(a,b){this.version=a;this.args=b}
function Ng(a){if(!a.ua){var b={};a.call(b);a.ua=b.version}return a.ua}
function Og(a,b){function c(){a.apply(this,b.args)}
if(!b.args||!b.version)throw Error("yt.pubsub2.Data.deserialize(): serializedData is incomplete.");var d;try{d=Ng(a)}catch(e){}if(!d||b.version!=d)throw Error("yt.pubsub2.Data.deserialize(): serializedData version is incompatible.");c.prototype=a.prototype;try{return new c}catch(e){throw e.message="yt.pubsub2.Data.deserialize(): "+e.message,e;}}
function P(a,b){this.topic=a;this.b=b}
P.prototype.toString=function(){return this.topic};var Pg=r("yt.pubsub2.instance_")||new E;E.prototype.subscribe=E.prototype.subscribe;E.prototype.unsubscribeByKey=E.prototype.ga;E.prototype.publish=E.prototype.u;E.prototype.clear=E.prototype.clear;q("yt.pubsub2.instance_",Pg,void 0);var Qg=r("yt.pubsub2.subscribedKeys_")||{};q("yt.pubsub2.subscribedKeys_",Qg,void 0);var Rg=r("yt.pubsub2.topicToKeys_")||{};q("yt.pubsub2.topicToKeys_",Rg,void 0);var Sg=r("yt.pubsub2.isAsync_")||{};q("yt.pubsub2.isAsync_",Sg,void 0);
q("yt.pubsub2.skipSubKey_",null,void 0);function Q(a,b){var c=Tg();c&&c.publish.call(c,a.toString(),a,b)}
function Ug(a,b,c){var d=Tg();if(!d)return 0;var e=d.subscribe(a.toString(),function(d,h){if(!window.yt.pubsub2.skipSubKey_||window.yt.pubsub2.skipSubKey_!=e){var k=function(){if(Qg[e])try{if(h&&a instanceof P&&a!=d)try{h=Og(a.b,h)}catch(k){throw k.message="yt.pubsub2 cross-binary conversion error for "+a.toString()+": "+k.message,k;}b.call(c||window,h)}catch(k){dc(k)}};
Sg[a.toString()]?r("yt.scheduler.instance")?Wd(k,void 0):G(k,0):k()}});
Qg[e]=!0;Rg[a.toString()]||(Rg[a.toString()]=[]);Rg[a.toString()].push(e);return e}
function Vg(a){var b=Tg();b&&(fa(a)&&(a=[a]),y(a,function(a){b.unsubscribeByKey(a);delete Qg[a]}))}
function Tg(){return r("yt.pubsub2.instance_")}
;var Wg=w().toString();var Xg=window.performance||window.mozPerformance||window.msPerformance||window.webkitPerformance||{};function Yg(a){O.call(this,1,arguments)}
x(Yg,O);var Zg=new P("timing-sent",Yg);var $g={ad_at:"ad_type",cpn:"client_playback_nonce",csn:"client_screen_nonce",yt_lt:"load_type",yt_ad:"is_monetized",yt_ad_pr:"preroll_allowed",yt_red:"is_red_subscriber",yt_vis:"is_visible"},ah=["is_monetized","preroll_allowed","is_red_subscriber","is_visible"],bh=v(Xg.clearResourceTimings||Xg.webkitClearResourceTimings||Xg.mozClearResourceTimings||Xg.msClearResourceTimings||Xg.oClearResourceTimings||t,Xg),ch=Xg.mark?v(Xg.mark,Xg):t;Xg.measure&&v(Xg.measure,Xg);
function dh(a){"_"==a[0]||ch(a);eh()[a]=fc();fh()["tick_"+a]=void 0;if($f("csi_on_gel")){var b=gh();"_start"==a?ig("latencyActionBaselined",{clientActionNonce:b},void 0):ig("latencyActionTicked",{tickName:a,clientActionNonce:b},void 0);a=!0}else a=!1;if(!(a||$f("navigation_only_csi_reset")&&r("yt.timing.pingSent_"))&&(b=F("TIMING_ACTION",void 0),a=eh(),r("yt.timing.ready_")&&b&&a._start&&hh())){var b=!0,c=F("TIMING_WAIT",[]);if(c.length)for(var d=0,e=c.length;d<e;++d)if(!(c[d]in a)){b=!1;break}if(b)if(d=
eh(),a=ih().span,e=ih().info,b=r("yt.timing.reportbuilder_")){if(b=b(d,a,e,void 0))jh(b),kh(),bh(),lh(!1)}else{var f=F("CSI_SERVICE_NAME","youtube"),b={v:2,s:f,action:F("TIMING_ACTION",void 0)},c=e.srt;delete e.srt;void 0===d.srt&&(c||0===c||(c=Xg.timing||{},c=Math.max(0,c.responseStart-c.navigationStart),isNaN(c)&&e.pt&&(c=e.pt)),c||0===c)&&(e.srt=Math.round(c));if(e.h5jse){var h=window.location.protocol+r("ytplayer.config.assets.js");(h=Xg.getEntriesByName?Xg.getEntriesByName(h)[0]:null)?e.h5jse=
Math.round(e.h5jse-h.responseEnd):delete e.h5jse}d.aft=hh();var h=d._start,k=eh(),m=k.pbr,n=k.vc,k=k.pbs;m&&n&&k&&m<n&&n<k&&1==ih().info.yt_vis&&"youtube"==f&&(m=ih().info.yt_lt="hot_bg",fh().info_yt_lt=m,$f("csi_on_gel")&&("yt_lt"in $g?(f={},n=$g.yt_lt,n in ah&&(m=!!m),f[n]=m,m=gh(),f.clientActionNonce=m,ig("latencyActionInfo",f)):dc(Error("Unknown label yt_lt logged with GEL CSI."))),f=d.vc,m=d.pbs,delete d.aft,e.aft=Math.round(m-f));for(var H in e)"_"!=H.charAt(0)&&(b[H]=e[H]);d.ps=fc();H={};var e=
[],R;for(R in d)"_"!=R.charAt(0)&&(f=Math.round(d[R]-h),$f("enable_negative_ticks")||(f=Math.max(f,0)),H[R]=f,e.push(R+"."+f));b.rt=e.join(",");R=b;var d=[],ia;for(ia in a)"_"!=ia.charAt(0)&&d.push(ia+"."+Math.round(a[ia]));R.it=d.join(",");(ia=r("ytdebug.logTiming"))&&ia(b,H,a);$f("navigation_only_csi_reset")||(kh(),bh(),lh(!1));F("EXP_DEFER_CSI_PING")?(mh(),q("yt.timing.deferredPingArgs_",b,void 0),ia=G(mh,0),q("yt.timing.deferredPingTimer_",ia,void 0)):jh(b);Q(Zg,new Yg(H.aft+(c||0)))}}}
function hh(){var a=eh();if(a.aft)return a.aft;for(var b=F("TIMING_AFT_KEYS",["ol"]),c=b.length,d=0;d<c;d++){var e=a[b[d]];if(e)return e}return NaN}
function jh(a){if(F("DEBUG_CSI_DATA")){var b=r("yt.timing.csiData");b||(b=[],q("yt.timing.csiData",b,void 0));b.push({page:location.href,time:new Date,args:a})}F("EXP_DEFER_CSI_PING")&&(I(r("yt.timing.deferredPingTimer_")),q("yt.timing.deferredPingArgs_",null,void 0));var b="",c;for(c in a)b+="&"+c+"="+a[c];a="/csi_204?"+b.substring(1);if(window.navigator&&window.navigator.sendBeacon)try{window.navigator&&window.navigator.sendBeacon&&window.navigator.sendBeacon(a,"")||a&&Mg(a)}catch(d){a&&Mg(a)}else a&&
Mg(a);lh(!0)}
function mh(a){if(F("EXP_DEFER_CSI_PING")){var b=r("yt.timing.deferredPingArgs_");b&&(a&&(b.yt_fss=a),jh(b))}}
function gh(){var a=ih().nonce;if(!a){var b;a:{if(window.crypto&&window.crypto.getRandomValues)try{var c=Array(16),d=new Uint8Array(16);window.crypto.getRandomValues(d);for(a=0;a<c.length;a++)c[a]=d[a];b=c;break a}catch(e){}b=Array(16);for(c=0;16>c;c++){d=w();for(a=0;a<d%23;a++)b[c]=Math.random();b[c]=Math.floor(256*Math.random())}if(Wg)for(c=1,d=0;d<Wg.length;d++)b[c%16]=b[c%16]^b[(c-1)%16]/4^Wg.charCodeAt(d),c++}c=[];for(d=0;d<b.length;d++)c.push("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".charAt(b[d]&
63));a=c.join("");ih().nonce=a}return a}
function eh(){return ih().tick}
function fh(){var a=ih();"gel"in a||(a.gel={});return a.gel}
function ih(){return r("ytcsi.data_")||kh()}
function kh(){var a={tick:{},span:{},info:{}};q("ytcsi.data_",a,void 0);return a}
function lh(a){q("yt.timing.pingSent_",a,void 0)}
;var nh={"api.invalidparam":2,auth:150,"drm.auth":150,heartbeat:150,"html5.unsupportedads":5,"fmt.noneavailable":5,"fmt.decode":5,"fmt.unplayable":5,"html5.missingapi":5,"html5.unsupportedlive":5,"drm.unavailable":5};function oh(a,b){C.call(this);this.l=this.i=a;this.aa=b;this.C=!1;this.f={};this.pa=this.W=null;this.ba=new E;Wb(this,oa(D,this.ba));this.h={};this.j=this.Fa=this.g=this.wb=this.b=null;this.ia=!1;this.D=this.o=this.K=this.L=null;this.$a={};this.Uc=["onReady"];this.ja=new Xf(this);Wb(this,oa(D,this.ja));this.xb=null;this.Wb=NaN;this.ka={};ph(this);this.ma("onDetailedError",v(this.Dd,this));this.ma("onTabOrderChange",v(this.Yc,this));this.ma("onTabAnnounce",v(this.Xb,this));this.ma("WATCH_LATER_VIDEO_ADDED",
v(this.Ed,this));this.ma("WATCH_LATER_VIDEO_REMOVED",v(this.Fd,this));mf||(this.ma("onMouseWheelCapture",v(this.Ad,this)),this.ma("onMouseWheelRelease",v(this.Bd,this)));this.ma("onAdAnnounce",v(this.Xb,this));this.G=new Xf(this);Wb(this,oa(D,this.G));this.vb=!1;this.ub=null}
x(oh,C);var qh=["drm.unavailable","fmt.noneavailable","html5.missingapi","html5.unsupportedads","html5.unsupportedlive"];g=oh.prototype;g.Sb=function(a,b){this.isDisposed()||(rh(this,a),sh(this,b),this.C&&th(this))};
function rh(a,b){a.wb=b;a.b=b.clone();a.g=a.b.attrs.id||a.g;"video-player"==a.g&&(a.g=a.aa,a.b.attrs.id=a.aa);a.l.id==a.g&&(a.g+="-player",a.b.attrs.id=a.g);a.b.args.enablejsapi="1";a.b.args.playerapiid=a.aa;a.Fa||(a.Fa=uh(a,a.b.args.jsapicallback||"onYouTubePlayerReady"));a.b.args.jsapicallback=null;var c=a.b.attrs.width;c&&(a.l.style.width=ef(Number(c)||c,!0));if(c=a.b.attrs.height)a.l.style.height=ef(Number(c)||c,!0)}
g.ed=function(){return this.wb};
function th(a){a.b.loaded||(a.b.loaded=!0,"0"!=a.b.args.autoplay?a.f.loadVideoByPlayerVars(a.b.args):a.f.cueVideoByPlayerVars(a.b.args))}
function vh(a){if(!p(a.b.disable.flash)){var b=a.b.disable,c;c=Cf(Bf.getInstance(),a.b.minVersion);b.flash=!c}return!a.b.disable.flash}
function wh(a,b){if((!b||(5!=(nh[b.errorCode]||5)?0:-1!=qh.indexOf(b.errorCode)))&&vh(a)){var c=xh(a);c&&c.stopVideo&&c.stopVideo();var d=a.b;c&&c.getUpdatedConfigurationData&&(c=c.getUpdatedConfigurationData(),d=Ye(c));d.args.autoplay=1;d.args.html5_unavailable="1";rh(a,d);sh(a,"flash")}}
function sh(a,b){if(!a.isDisposed()){if(!b){var c;if(!(c=!a.b.html5&&vh(a))){if(!p(a.b.disable.html5)){var d;c=!0;void 0!=a.b.args.deviceHasDisplay&&(c=a.b.args.deviceHasDisplay);if(2.2==Pf)d=!0;else{a:{var e=c;c=r("yt.player.utils.videoElement_");c||(c=document.createElement("video"),q("yt.player.utils.videoElement_",c,void 0));try{if(c.canPlayType)for(var e=e?Vf:Wf,f=0;f<e.length;f++)if(c.canPlayType(e[f])){d=null;break a}d="fmt.noneavailable"}catch(h){d="html5.missingapi"}}d=!d}d&&(d=yh(a)||a.b.assets.js);
a.b.disable.html5=!d;d||(a.b.args.html5_unavailable="1")}c=!!a.b.disable.html5}b=c?vh(a)?"flash":"unsupported":"html5"}("flash"==b?a.me:"html5"==b?a.ne:a.oe).call(a)}}
function yh(a){var b=!0,c=xh(a);c&&a.b&&(a=a.b,b=B(c,"version")==a.assets.js);return b&&!!r("yt.player.Application.create")}
g.ne=function(){if(!this.ia){var a=yh(this);a&&"html5"==zh(this)?(this.j="html5",this.C||this.Ka()):(Ah(this),this.j="html5",a&&this.K?(this.i.appendChild(this.K),this.Ka()):(this.b.loaded=!0,this.L=v(function(){var a=this.i,c=this.b.clone();r("yt.player.Application.create")(a,c);this.Ka()},this),this.ia=!0,a?this.L():(sc(this.b.assets.js,this.L),Kf(this.b.assets.css))))}};
g.me=function(){var a=this.b.clone();if(!this.o){var b=xh(this);b&&(this.o=document.createElement("span"),this.o.tabIndex=0,Yf(this.ja,this.o,"focus",this.jc),this.D=document.createElement("span"),this.D.tabIndex=0,Yf(this.ja,this.D,"focus",this.jc),b.parentNode&&b.parentNode.insertBefore(this.o,b),b.parentNode&&b.parentNode.insertBefore(this.D,b.nextSibling))}a.attrs.width=a.attrs.width||"100%";a.attrs.height=a.attrs.height||"100%";if("flash"==zh(this))this.j="flash",this.C||Gf(a,!1,v(this.Ka,this));
else{Ah(this);this.j="flash";this.b.loaded=!0;b=this.i;b=u(b)?me(b):b;a=Ye(a);if(window!=window.top){var c=null;document.referrer&&(c=document.referrer.substring(0,128));a.args.framer=c}c=Bf.getInstance();Cf(c,a.minVersion)?(c=Hf(a,c),Ff(b,c,a)):Jf(b,a,c);this.Ka()}};
g.jc=function(){xh(this).focus()};
function xh(a){var b=le(a.g);!b&&a.l&&a.l.querySelector&&(b=a.l.querySelector("#"+a.g));return b}
g.Ka=function(){if(!this.isDisposed()){var a=xh(this),b=!1;try{a&&a.getApiInterface&&a.getApiInterface()&&(b=!0)}catch(f){}if(b)if(this.ia=!1,a.isNotServable&&a.isNotServable(this.b.args.video_id))wh(this);else{ph(this);this.C=!0;a=xh(this);a.addEventListener&&(this.W=Bh(this,a,"addEventListener"));a.removeEventListener&&(this.pa=Bh(this,a,"removeEventListener"));for(var b=a.getApiInterface(),b=b.concat(a.getInternalApiInterface()),c=0;c<b.length;c++){var d=b[c];this.f[d]||(this.f[d]=Bh(this,a,d))}for(var e in this.h)this.W(e,
this.h[e]);th(this);this.Fa&&this.Fa(this.f);this.ba.u("onReady",this.f)}else this.Wb=G(v(this.Ka,this),50)}};
function Bh(a,b,c){var d=b[c];return function(){try{return a.xb=null,d.apply(b,arguments)}catch(e){"Bad NPObject as private data!"!=e.message&&"sendAbandonmentPing"!=c&&(e.message+=" ("+c+")",a.xb=e,dc(e,"WARNING"))}}}
function ph(a){a.C=!1;if(a.pa)for(var b in a.h)a.pa(b,a.h[b]);for(var c in a.ka)I(parseInt(c,10));a.ka={};a.W=null;a.pa=null;for(var d in a.f)a.f[d]=null;a.f.addEventListener=v(a.ma,a);a.f.removeEventListener=v(a.$d,a);a.f.destroy=v(a.dispose,a);a.f.getLastError=v(a.fd,a);a.f.getPlayerType=v(a.gd,a);a.f.getCurrentVideoConfig=v(a.ed,a);a.f.loadNewVideoConfig=v(a.Sb,a);a.f.isReady=v(a.Ae,a)}
g.Ae=function(){return this.C};
g.ma=function(a,b){if(!this.isDisposed()){var c=uh(this,b);if(c){if(!z(this.Uc,a)&&!this.h[a]){var d=Ch(this,a);this.W&&this.W(a,d)}this.ba.subscribe(a,c);"onReady"==a&&this.C&&G(oa(c,this.f),0)}}};
g.$d=function(a,b){if(!this.isDisposed()){var c=uh(this,b);c&&this.ba.unsubscribe(a,c)}};
function uh(a,b){var c=b;if("string"==typeof b){if(a.$a[b])return a.$a[b];c=function(){var a=r(b);a&&a.apply(l,arguments)};
a.$a[b]=c}return c?c:null}
function Ch(a,b){var c="ytPlayer"+b+a.aa;a.h[b]=c;l[c]=function(c){var e=G(function(){if(!a.isDisposed()){a.ba.u(b,c);var f=a.ka,h=String(e);h in f&&delete f[h]}},0);
ib(a.ka,String(e))};
return c}
g.Yc=function(a){a=a?te:se;for(var b=a(document.activeElement);b&&(1!=b.nodeType||b==this.o||b==this.D||(b.focus(),b!=document.activeElement));)b=a(b)};
g.Xb=function(a){J("a11y-announce",a)};
g.Dd=function(a){wh(this,a)};
g.Ed=function(a){J("WATCH_LATER_VIDEO_ADDED",a)};
g.Fd=function(a){J("WATCH_LATER_VIDEO_REMOVED",a)};
g.Ad=function(){this.vb||(qf?(this.ub=pe(document),Yf(this.G,window,"scroll",this.Vd),Yf(this.G,this.i,"touchmove",this.Pd)):(Yf(this.G,this.i,"mousewheel",this.mc),Yf(this.G,this.i,"wheel",this.mc)),this.vb=!0)};
g.Bd=function(){Zf(this.G);this.vb=!1};
g.mc=function(a){a=a||window.event;a.returnValue=!1;a.preventDefault&&a.preventDefault()};
g.Vd=function(){window.scrollTo(this.ub.x,this.ub.y)};
g.Pd=function(a){a.preventDefault()};
g.oe=function(){Ah(this);this.j="unsupported";var a='Adobe Flash Player or an HTML5 supported browser is required for video playback. <br> <a href="http://get.adobe.com/flashplayer/">Get the latest Flash Player</a> <br> <a href="/html5">Learn more about upgrading to an HTML5 browser</a>',b=navigator.userAgent.match(/Version\/(\d).*Safari/);b&&5<=parseInt(b[1],10)&&(a='Adobe Flash Player or QuickTime is required for video playback. <br> <a href="http://get.adobe.com/flashplayer/"> Get the latest Flash Player</a> <br> <a href="http://www.apple.com/quicktime/download/">Get the latest version of QuickTime</a>');
b=this.b.messages.player_fallback||a;a=le("player-unavailable");if(le("unavailable-submessage")&&a){le("unavailable-submessage").innerHTML=b;var b=a||document,c=null;b.getElementsByClassName?c=b.getElementsByClassName("icon")[0]:b.querySelectorAll&&b.querySelector?c=b.querySelector(".icon"):c=oe("icon",a)[0];if(c=b=c||null)c=b?b.dataset?Fb("icon")in b.dataset:b.hasAttribute?!!b.hasAttribute("data-icon"):!!b.getAttribute("data-icon"):!1;c&&(b.src=B(b,"icon"));a.style.display="";ce(le("player"),"off-screen-trigger")}};
g.gd=function(){return this.j||zh(this)};
g.fd=function(){return this.xb};
function zh(a){return(a=xh(a))?"div"==a.tagName.toLowerCase()?"html5":"flash":null}
function Ah(a){dh("dcp");a.cancel();ph(a);a.j=null;a.b&&(a.b.loaded=!1);var b=xh(a);"html5"==zh(a)?a.K=b:b&&b.destroy&&b.destroy();re(a.i);Zf(a.ja);a.o=null;a.D=null}
g.cancel=function(){this.L&&zc(this.b.assets.js,this.L);I(this.Wb);this.ia=!1};
g.w=function(){Ah(this);if(this.K&&this.b)try{this.K.destroy()}catch(b){dc(b)}this.$a=null;for(var a in this.h)l[this.h[a]]=null;this.wb=this.b=this.f=null;delete this.i;delete this.l;oh.A.w.call(this)};var Dh={},Eh="player_uid_"+(1E9*Math.random()>>>0);function Fh(a,b){a=u(a)?me(a):a;b=Ye(b);var c=Eh+"_"+ja(a),d=Dh[c];if(d)return d.Sb(b),d.f;d=new oh(a,c);Dh[c]=d;J("player-added",d.f);Wb(d,oa(Gh,d));G(function(){d.Sb(b)},0);
return d.f}
function Gh(a){Dh[a.aa]=null}
function Hh(a){a=le(a);if(!a)return null;var b=Eh+"_"+ja(a),c=Dh[b];c||(c=new oh(a,b),Dh[b]=c);return c.f}
;var Ih=r("yt.abuse.botguardInitialized")||Cc;q("yt.abuse.botguardInitialized",Ih,void 0);var Jh=r("yt.abuse.invokeBotguard")||Dc;q("yt.abuse.invokeBotguard",Jh,void 0);var Kh=r("yt.abuse.dclkstatus.checkDclkStatus")||$d;q("yt.abuse.dclkstatus.checkDclkStatus",Kh,void 0);var Lh=r("yt.player.exports.navigate")||Te;q("yt.player.exports.navigate",Lh,void 0);var Mh=r("yt.player.embed")||Fh;q("yt.player.embed",Mh,void 0);var Nh=r("yt.player.getPlayerByElement")||Hh;q("yt.player.getPlayerByElement",Nh,void 0);
var Oh=r("yt.util.activity.init")||Ke;q("yt.util.activity.init",Oh,void 0);var Ph=r("yt.util.activity.getTimeSinceActive")||Me;q("yt.util.activity.getTimeSinceActive",Ph,void 0);var Qh=r("yt.util.activity.setTimestamp")||Le;q("yt.util.activity.setTimestamp",Qh,void 0);function Rh(a){O.call(this,1,arguments);this.b=a}
x(Rh,O);function Sh(a){O.call(this,1,arguments);this.b=a}
x(Sh,O);function Th(a,b){O.call(this,1,arguments);this.b=a;this.isEnabled=b}
x(Th,O);function Uh(a,b,c,d,e){O.call(this,2,arguments);this.f=a;this.b=b;this.h=c||null;this.g=d||null;this.source=e||null}
x(Uh,O);function Vh(a,b,c){O.call(this,1,arguments);this.b=a;this.subscriptionId=b}
x(Vh,O);function Wh(a,b,c,d,e,f,h){O.call(this,1,arguments);this.f=a;this.subscriptionId=b;this.b=c;this.i=d||null;this.h=e||null;this.g=f||null;this.source=h||null}
x(Wh,O);
var Xh=new P("subscription-batch-subscribe",Rh),Yh=new P("subscription-batch-unsubscribe",Rh),Zh=new P("subscription-pref-email",Th),$h=new P("subscription-subscribe",Uh),ai=new P("subscription-subscribe-loading",Sh),bi=new P("subscription-subscribe-loaded",Sh),ci=new P("subscription-subscribe-success",Vh),di=new P("subscription-subscribe-external",Uh),ei=new P("subscription-unsubscribe",Wh),fi=new P("subscription-unsubscirbe-loading",Sh),gi=new P("subscription-unsubscribe-loaded",Sh),hi=new P("subscription-unsubscribe-success",
Sh),ii=new P("subscription-external-unsubscribe",Wh),ji=new P("subscription-enable-ypc",Sh),ki=new P("subscription-disable-ypc",Sh);function li(a,b){var c=document.location.protocol+"//"+document.domain+"/post_login";b&&(c=Ed(c,"mode",b));c=Ed("/signin?context=popup","next",c);c=Ed(c,"feature","sub_button");if(c=window.open(c,"loginPopup","width=375,height=440,resizable=yes,scrollbars=yes",!0)){var d=mc("LOGGED_IN",function(b){oc(F("LOGGED_IN_PUBSUB_KEY",void 0));ac("LOGGED_IN",!0);a(b)});
ac("LOGGED_IN_PUBSUB_KEY",d);c.moveTo((screen.width-375)/2,(screen.height-440)/2)}}
q("yt.pubsub.publish",J,void 0);function mi(){var a=F("PLAYER_CONFIG");return a&&a.args&&void 0!==a.args.authuser?!0:!(!F("SESSION_INDEX")&&!F("LOGGED_IN"))}
;function ni(){var a=Ee();return a?a:null}
;var oi={},pi="ontouchstart"in document;function qi(a,b,c){var d;switch(a){case "mouseover":case "mouseout":d=3;break;case "mouseenter":case "mouseleave":d=9}return Be(c,function(a){return be(a,b)},!0,d)}
function ri(a){var b="mouseover"==a.type&&"mouseenter"in oi||"mouseout"==a.type&&"mouseleave"in oi,c=a.type in oi||b;if("HTML"!=a.target.tagName&&c){if(b){var b="mouseover"==a.type?"mouseenter":"mouseleave",c=oi[b],d;for(d in c.$){var e=qi(b,d,a.target);e&&!Be(a.relatedTarget,function(a){return a==e},!0)&&c.u(d,e,b,a)}}if(b=oi[a.type])for(d in b.$)(e=qi(a.type,d,a.target))&&b.u(d,e,a.type,a)}}
M(document,"blur",ri,!0);M(document,"change",ri,!0);M(document,"click",ri);M(document,"focus",ri,!0);M(document,"mouseover",ri);M(document,"mouseout",ri);M(document,"mousedown",ri);M(document,"keydown",ri);M(document,"keyup",ri);M(document,"keypress",ri);M(document,"cut",ri);M(document,"paste",ri);pi&&(M(document,"touchstart",ri),M(document,"touchend",ri),M(document,"touchcancel",ri));function si(a){this.i=a;this.g={};this.lb=[];this.h=[]}
function S(a,b){return"yt-uix"+(a.i?"-"+a.i:"")+(b?"-"+b:"")}
si.prototype.unregister=function(){oc(this.lb);this.lb.length=0;Vg(this.h);this.h.length=0};
si.prototype.init=t;si.prototype.dispose=t;function ti(a,b,c){a.h.push(Ug(b,c,a))}
function ui(a,b,c){var d=S(a,void 0),e=v(c,a);b in oi||(oi[b]=new E);oi[b].subscribe(d,e);a.g[c]=e}
function vi(a,b,c){if(b in oi){var d=oi[b];d.unsubscribe(S(a,void 0),a.g[c]);0>=d.N()&&(d.dispose(),delete oi[b])}delete a.g[c]}
function wi(a,b){Eb(a,"tooltip-text",b)}
;function xi(){si.call(this,"tooltip");this.b=0;this.f={}}
x(xi,si);ba(xi);g=xi.prototype;g.register=function(){ui(this,"mouseover",this.ib);ui(this,"mouseout",this.Ba);ui(this,"focus",this.dc);ui(this,"blur",this.Zb);ui(this,"click",this.Ba);ui(this,"touchstart",this.Ec);ui(this,"touchend",this.ob);ui(this,"touchcancel",this.ob)};
g.unregister=function(){vi(this,"mouseover",this.ib);vi(this,"mouseout",this.Ba);vi(this,"focus",this.dc);vi(this,"blur",this.Zb);vi(this,"click",this.Ba);vi(this,"touchstart",this.Ec);vi(this,"touchend",this.ob);vi(this,"touchcancel",this.ob);this.dispose();xi.A.unregister.call(this)};
g.dispose=function(){for(var a in this.f)this.Ba(this.f[a]);this.f={}};
g.ib=function(a){if(!(this.b&&1E3>w()-this.b)){var b=parseInt(B(a,"tooltip-hide-timer"),10);b&&(Gb(a,"tooltip-hide-timer"),I(b));var b=v(function(){yi(this,a);Gb(a,"tooltip-show-timer")},this),c=parseInt(B(a,"tooltip-show-delay"),10)||0,b=G(b,c);
Eb(a,"tooltip-show-timer",b.toString());a.title&&(wi(a,zi(a)),a.title="");b=ja(a).toString();this.f[b]=a}};
g.Ba=function(a){var b=parseInt(B(a,"tooltip-show-timer"),10);b&&(I(b),Gb(a,"tooltip-show-timer"));b=v(function(){if(a){var b=le(Ai(this,a));b&&(Bi(b),b&&b.parentNode&&b.parentNode.removeChild(b),Gb(a,"content-id"));(b=le(Ai(this,a,"arialabel")))&&b.parentNode&&b.parentNode.removeChild(b)}Gb(a,"tooltip-hide-timer")},this);
b=G(b,50);Eb(a,"tooltip-hide-timer",b.toString());if(b=B(a,"tooltip-text"))a.title=b;b=ja(a).toString();delete this.f[b]};
g.dc=function(a){this.b=0;this.ib(a)};
g.Zb=function(a){this.b=0;this.Ba(a)};
g.Ec=function(a,b,c){c.changedTouches&&(this.b=0,a=qi(b,S(this),c.changedTouches[0].target),this.ib(a))};
g.ob=function(a,b,c){c.changedTouches&&(this.b=w(),a=qi(b,S(this),c.changedTouches[0].target),this.Ba(a))};
function Ci(a,b){wi(a,b);var c=B(a,"content-id");(c=le(c))&&ue(c,b)}
function zi(a){return B(a,"tooltip-text")||a.title}
function yi(a,b){if(b){var c=zi(b);if(c){var d=le(Ai(a,b));if(!d){d=document.createElement("div");d.id=Ai(a,b);d.className=S(a,"tip");var e=document.createElement("div");e.className=S(a,"tip-body");var f=document.createElement("div");f.className=S(a,"tip-arrow");var h=document.createElement("div");h.setAttribute("aria-hidden","true");h.className=S(a,"tip-content");var k=Di(a,b),m=Ai(a,b,"content");h.id=m;Eb(b,"content-id",m);e.appendChild(h);k&&d.appendChild(k);d.appendChild(e);d.appendChild(f);var m=
xe(b),n=Ai(a,b,"arialabel"),f=document.createElement("div");ce(f,S(a,"arialabel"));f.id=n;"rtl"==document.body.getAttribute("dir")?ue(f,c+" "+m):ue(f,m+" "+c);b.setAttribute("aria-labelledby",n);m=ni()||document.body;m.appendChild(f);m.appendChild(d);Ci(b,c);(c=parseInt(B(b,"tooltip-max-width"),10))&&e.offsetWidth>c&&(e.style.width=c+"px",ce(h,S(a,"normal-wrap")));h=be(b,S(a,"reverse"));Ei(a,b,d,e,k,h)||Ei(a,b,d,e,k,!h);var H=S(a,"tip-visible");G(function(){ce(d,H)},0)}}}}
function Ei(a,b,c,d,e,f){ee(c,S(a,"tip-reverse"),f);var h=0;f&&(h=1);a=ff(b);f=new fe((a.width-10)/2,f?a.height:0);var k=ke(b),m=new fe(0,0),n;n=k?ke(k):document;n=!K||ld(9)||qe(ie(n).b)?n.documentElement:n.body;b!=n&&(n=df(b),k=pe(ie(k).b),m.x=n.left+k.x,m.y=n.top+k.y);f=new fe(m.x+f.x,m.y+f.y);f=f.clone();m=(h&8&&"rtl"==cf(c,"direction")?h^4:h)&-9;h=ff(c);k=h.clone();n=f.clone();k=k.clone();0!=m&&(m&4?n.x-=k.width+0:m&2&&(n.x-=k.width/2),m&1&&(n.y-=k.height+0));f=new $e(0,0,0,0);f.left=n.x;f.top=
n.y;f.width=k.width;f.height=k.height;k=new fe(f.left,f.top);k instanceof fe?(m=k.x,k=k.y):(m=k,k=void 0);c.style.left=ef(m,!1);c.style.top=ef(k,!1);k=new ge(f.width,f.height);if(!(h==k||h&&k&&h.width==k.width&&h.height==k.height))if(h=k,f=ke(c),m=qe(ie(f).b),!K||kd("10")||m&&kd("8"))f=c.style,$c?f.MozBoxSizing="border-box":ad?f.WebkitBoxSizing="border-box":f.boxSizing="border-box",f.width=Math.max(h.width,0)+"px",f.height=Math.max(h.height,0)+"px";else if(f=c.style,m){if(K){m=jf(c,"paddingLeft");
k=jf(c,"paddingRight");n=jf(c,"paddingTop");var H=jf(c,"paddingBottom"),m=new Ze(n,k,H,m)}else m=bf(c,"paddingLeft"),k=bf(c,"paddingRight"),n=bf(c,"paddingTop"),H=bf(c,"paddingBottom"),m=new Ze(parseFloat(n),parseFloat(k),parseFloat(H),parseFloat(m));if(K&&!ld(9)){k=lf(c,"borderLeft");n=lf(c,"borderRight");var H=lf(c,"borderTop"),R=lf(c,"borderBottom"),k=new Ze(H,n,R,k)}else k=bf(c,"borderLeftWidth"),n=bf(c,"borderRightWidth"),H=bf(c,"borderTopWidth"),R=bf(c,"borderBottomWidth"),k=new Ze(parseFloat(H),
parseFloat(n),parseFloat(R),parseFloat(k));f.pixelWidth=h.width-k.left-m.left-m.right-k.right;f.pixelHeight=h.height-k.top-m.top-m.bottom-k.bottom}else f.pixelWidth=h.width,f.pixelHeight=h.height;h=window.document;h=qe(h)?h.documentElement:h.body;f=new ge(h.clientWidth,h.clientHeight);1==c.nodeType?(c=df(c),k=new fe(c.left,c.top)):(c=c.changedTouches?c.changedTouches[0]:c,k=new fe(c.clientX,c.clientY));c=ff(d);n=Math.floor(c.width/2);h=!!(f.height<k.y+a.height);a=!!(k.y<a.height);m=!!(k.x<n);f=!!(f.width<
k.x+n);k=(c.width+3)/-2- -5;b=B(b,"force-tooltip-direction");if("left"==b||m)k=-5;else if("right"==b||f)k=20-c.width-3;b=Math.floor(k)+"px";d.style.left=b;e&&(e.style.left=b,e.style.height=c.height+"px",e.style.width=c.width+"px");return!(h||a)}
function Ai(a,b,c){a=S(a);var d=b.__yt_uid_key;d||(d=Ce(),b.__yt_uid_key=d);b=a+d;c&&(b+="-"+c);return b}
function Di(a,b){var c=null;cd&&be(b,S(a,"masked"))&&((c=le("yt-uix-tooltip-shared-mask"))?(c.parentNode.removeChild(c),zf(c)):(c=document.createElement("iframe"),c.src='javascript:""',c.id="yt-uix-tooltip-shared-mask",c.className=S(a,"tip-mask")));return c}
function Bi(a){var b=le("yt-uix-tooltip-shared-mask"),c=b&&Be(b,function(b){return b==a},!1,2);
b&&c&&(b.parentNode.removeChild(b),Af(b),document.body.appendChild(b))}
;function Fi(){si.call(this,"subscription-button")}
x(Fi,si);ba(Fi);Fi.prototype.register=function(){ui(this,"click",this.Ab);ti(this,ai,this.lc);ti(this,bi,this.kc);ti(this,ci,this.Nd);ti(this,fi,this.lc);ti(this,gi,this.kc);ti(this,hi,this.Td);ti(this,ji,this.zd);ti(this,ki,this.yd)};
Fi.prototype.unregister=function(){vi(this,"click",this.Ab);Fi.A.unregister.call(this)};
var Ae={Tb:"hover-enabled",Mc:"yt-uix-button-subscribe",Nc:"yt-uix-button-subscribed",Ce:"ypc-enabled",Oc:"yt-uix-button-subscription-container",Pc:"yt-subscription-button-disabled-mask-container"},Gi={De:"channel-external-id",Qc:"subscriber-count-show-when-subscribed",Rc:"subscriber-count-tooltip",Sc:"subscriber-count-title",Ee:"href",Ub:"is-subscribed",Ge:"parent-url",Ie:"clicktracking",Tc:"style-type",Vb:"subscription-id",Le:"target",Vc:"ypc-enabled"};g=Fi.prototype;
g.Ab=function(a){var b=B(a,"href"),c=mi();if(b)a=B(a,"target")||"_self",window.open(b,a);else if(c){var b=B(a,"channel-external-id"),c=B(a,"clicktracking"),d;if(B(a,"ypc-enabled")){d=B(a,"ypc-item-type");var e=B(a,"ypc-item-id");d={itemType:d,itemId:e,subscriptionElement:a}}else d=null;e=B(a,"parent-url");if(B(a,"is-subscribed")){var f=B(a,"subscription-id");Q(ei,new Wh(b,f,d,a,c,e))}else Q($h,new Uh(b,d,c,e))}else Hi(this,a)};
g.lc=function(a){this.Ga(a.b,this.Bc,!0)};
g.kc=function(a){this.Ga(a.b,this.Bc,!1)};
g.Nd=function(a){this.Ga(a.b,this.Cc,!0,a.subscriptionId)};
g.Td=function(a){this.Ga(a.b,this.Cc,!1)};
g.zd=function(a){this.Ga(a.b,this.ad)};
g.yd=function(a){this.Ga(a.b,this.$c)};
g.Cc=function(a,b,c){b?(Eb(a,Gi.Ub,"true"),c&&Eb(a,Gi.Vb,c)):(Gb(a,Gi.Ub),Gb(a,Gi.Vb));Ii(a)};
g.Bc=function(a,b){var c;c=ze(a);ee(c,Ae.Pc,b);a.setAttribute("aria-busy",b?"true":"false");a.disabled=b};
function Ii(a){var b=B(a,Gi.Tc),c=!!B(a,"is-subscribed"),b="-"+b,d=Ae.Nc+b;ee(a,Ae.Mc+b,!c);ee(a,d,c);B(a,Gi.Rc)&&!B(a,Gi.Qc)&&(b=S(xi.getInstance()),ee(a,b,!c),a.title=c?"":B(a,Gi.Sc));c?G(function(){ce(a,Ae.Tb)},1E3):de(a,Ae.Tb)}
g.ad=function(a){var b=!!B(a,"ypc-item-type"),c=!!B(a,"ypc-item-id");!B(a,"ypc-enabled")&&b&&c&&(ce(a,"ypc-enabled"),Eb(a,Gi.Vc,"true"))};
g.$c=function(a){B(a,"ypc-enabled")&&(de(a,"ypc-enabled"),Gb(a,"ypc-enabled"))};
function Ji(a,b){var c=ne(S(a));return Ja(c,function(a){return b==B(a,"channel-external-id")},a)}
g.Wc=function(a,b,c){var d=Wa(arguments,2);y(a,function(a){b.apply(this,Sa(a,d))},this)};
g.Ga=function(a,b,c){var d=Ji(this,a),d=Sa([d],Wa(arguments,1));this.Wc.apply(this,d)};
function Hi(a,b){var c=v(function(a){a.discoverable_subscriptions&&ac("SUBSCRIBE_EMBED_DISCOVERABLE_SUBSCRIPTIONS",a.discoverable_subscriptions);this.Ab(b)},a);
li(c,"subscribe")}
;var Ki=window.yt&&window.yt.uix&&window.yt.uix.widgets_||{};q("yt.uix.widgets_",Ki,void 0);function Li(a,b){this.source=null;this.i=a||null;this.origin="*";this.o=window.document.location.protocol+"//"+window.document.location.hostname;this.l=b;this.g=this.b=this.f=this.channel=this.h=null;M(window,"message",v(this.j,this))}
Li.prototype.j=function(a){var b=this.l||F("POST_MESSAGE_ORIGIN",void 0)||this.o;if("*"!=b&&a.origin!=b)window.console&&window.console.warn("Untrusted origin: "+a.origin);else if(!this.i||a.source==this.i)if(this.source=a.source,this.origin="null"==a.origin?this.origin:a.origin,a=a.data,u(a)){try{a=od(a)}catch(c){return}this.h=a.id;switch(a.event){case "listening":this.b&&(this.b(),this.b=null);break;case "command":this.f&&(this.g&&!z(this.g,a.func)||this.f(a.func,a.args))}}};
Li.prototype.sendMessage=function(a){this.source&&(a.id=this.h,this.channel&&(a.channel=this.channel),a=L(a),this.source.postMessage(a,this.origin))};function Mi(){}
;function Ni(){}
x(Ni,Mi);Ni.prototype.N=function(){var a=0;Ic(this.la(!0),function(){a++});
return a};
Ni.prototype.clear=function(){var a=Jc(this.la(!0)),b=this;y(a,function(a){b.remove(a)})};function Oi(a){this.b=a}
x(Oi,Ni);g=Oi.prototype;g.isAvailable=function(){if(!this.b)return!1;try{return this.b.setItem("__sak","1"),this.b.removeItem("__sak"),!0}catch(a){return!1}};
g.set=function(a,b){try{this.b.setItem(a,b)}catch(c){if(0==this.b.length)throw"Storage mechanism: Storage disabled";throw"Storage mechanism: Quota exceeded";}};
g.get=function(a){a=this.b.getItem(a);if(!u(a)&&null!==a)throw"Storage mechanism: Invalid value was encountered";return a};
g.remove=function(a){this.b.removeItem(a)};
g.N=function(){return this.b.length};
g.la=function(a){var b=0,c=this.b,d=new Gc;d.next=function(){if(b>=c.length)throw Fc;var d=c.key(b++);if(a)return d;d=c.getItem(d);if(!u(d))throw"Storage mechanism: Invalid value was encountered";return d};
return d};
g.clear=function(){this.b.clear()};
g.key=function(a){return this.b.key(a)};function Pi(){var a=null;try{a=window.localStorage||null}catch(b){}this.b=a}
x(Pi,Oi);function Qi(){var a=null;try{a=window.sessionStorage||null}catch(b){}this.b=a}
x(Qi,Oi);function Ri(a){this.b=a}
Ri.prototype.set=function(a,b){p(b)?this.b.set(a,L(b)):this.b.remove(a)};
Ri.prototype.get=function(a){var b;try{b=this.b.get(a)}catch(c){return}if(null!==b)try{return od(b)}catch(c){throw"Storage: Invalid value was encountered";}};
Ri.prototype.remove=function(a){this.b.remove(a)};function Si(a){this.b=a}
x(Si,Ri);function Ti(a){this.data=a}
function Ui(a){return!p(a)||a instanceof Ti?a:new Ti(a)}
Si.prototype.set=function(a,b){Si.A.set.call(this,a,Ui(b))};
Si.prototype.f=function(a){a=Si.A.get.call(this,a);if(!p(a)||a instanceof Object)return a;throw"Storage: Invalid value was encountered";};
Si.prototype.get=function(a){if(a=this.f(a)){if(a=a.data,!p(a))throw"Storage: Invalid value was encountered";}else a=void 0;return a};function Vi(a){this.b=a}
x(Vi,Si);function Wi(a){var b=a.creation;a=a.expiration;return!!a&&a<w()||!!b&&b>w()}
Vi.prototype.set=function(a,b,c){if(b=Ui(b)){if(c){if(c<w()){Vi.prototype.remove.call(this,a);return}b.expiration=c}b.creation=w()}Vi.A.set.call(this,a,b)};
Vi.prototype.f=function(a,b){var c=Vi.A.f.call(this,a);if(c)if(!b&&Wi(c))Vi.prototype.remove.call(this,a);else return c};function Xi(a){this.b=a}
x(Xi,Vi);function Yi(a,b){var c=[];Ic(b,function(a){var b;try{b=Xi.prototype.f.call(this,a,!0)}catch(f){if("Storage: Invalid value was encountered"==f)return;throw f;}p(b)?Wi(b)&&c.push(a):c.push(a)},a);
return c}
function Zi(a,b){var c=Yi(a,b);y(c,function(a){Xi.prototype.remove.call(this,a)},a)}
function $i(){var a=aj;Zi(a,a.b.la(!0))}
;function T(a,b,c){var d=c&&0<c?c:0;c=d?w()+1E3*d:0;if((d=d?aj:bj)&&window.JSON){u(b)||(b=JSON.stringify(b,void 0));try{d.set(a,b,c)}catch(e){d.remove(a)}}}
function U(a){if(!bj&&!aj||!window.JSON)return null;var b;try{b=bj.get(a)}catch(c){}if(!u(b))try{b=aj.get(a)}catch(c){}if(!u(b))return null;try{b=JSON.parse(b,void 0)}catch(c){}return b}
function cj(a){bj&&bj.remove(a);aj&&aj.remove(a)}
var aj,dj=new Pi;aj=dj.isAvailable()?new Xi(dj):null;var bj,ej=new Qi;bj=ej.isAvailable()?new Xi(ej):null;function fj(a){return(0==a.search("cue")||0==a.search("load"))&&"loadModule"!=a}
function gj(a,b,c){u(a)&&(a={mediaContentUrl:a,startSeconds:b,suggestedQuality:c});b=a;c=/\/([ve]|embed)\/([^#?]+)/.exec(a.mediaContentUrl);b.videoId=c&&c[2]?c[2]:null;return hj(a)}
function hj(a,b,c){if(ha(a)){b="endSeconds startSeconds mediaContentUrl suggestedQuality videoId two_stage_token".split(" ");c={};for(var d=0;d<b.length;d++){var e=b[d];a[e]&&(c[e]=a[e])}return c}return{videoId:a,startSeconds:b,suggestedQuality:c}}
function ij(a,b,c,d){if(ha(a)&&!da(a)){b="playlist list listType index startSeconds suggestedQuality".split(" ");c={};for(d=0;d<b.length;d++){var e=b[d];a[e]&&(c[e]=a[e])}return c}c={index:b,startSeconds:c,suggestedQuality:d};u(a)&&16==a.length?c.list="PL"+a:c.playlist=a;return c}
function jj(a){var b=a.video_id||a.videoId;if(u(b)){var c=U("yt-player-two-stage-token")||{},d=U("yt-player-two-stage-token")||{};p(void 0)?d[b]=void 0:delete d[b];T("yt-player-two-stage-token",d,300);(b=c[b])&&(a.two_stage_token=b)}}
;function kj(){var a=window.navigator.userAgent.match(/Chrome\/([0-9]+)/);return a?50<=parseInt(a[1],10):!1}
var lj=document.currentScript&&-1!=document.currentScript.src.indexOf("?loadGamesSDK")?"/cast_game_sender.js":"/cast_sender.js",mj=["boadgeojelhgndaghljhdicfkmllpafd","dliochdbjfkdbacpmhlcpmleaejidimm","enhhojjnijigcajfphajepfemndkmdlo","fmfcbgogabcbclcofgocippekhfcmgfj"],nj=["pkedcjkdefgpdelpbcmbmeomcjbeemfm","fjhoaacokmgbjemoflkofnenfaiekifl"],oj=kj()?nj.concat(mj):mj.concat(nj);function pj(a,b){var c=new XMLHttpRequest;c.onreadystatechange=function(){4==c.readyState&&200==c.status&&b(!0)};
c.onerror=function(){b(!1)};
try{c.open("GET",a,!0),c.send()}catch(d){b(!1)}}
function qj(a){if(a>=oj.length)rj();else{var b=oj[a],c="chrome-extension://"+b+lj;0<=mj.indexOf(b)?pj(c,function(d){d?(window.chrome.cast=window.chrome.cast||{},window.chrome.cast.extensionId=b,sj(c,rj)):qj(a+1)}):sj(c,function(){qj(a+1)})}}
function sj(a,b){var c=document.createElement("script");c.onerror=b;c.src=a;(document.head||document.documentElement).appendChild(c)}
function rj(){var a=window.__onGCastApiAvailable;a&&"function"==typeof a&&a(!1,"No cast extension found")}
function tj(){if(0<=window.navigator.userAgent.indexOf("CriOS")){var a=window.__gCrWeb&&window.__gCrWeb.message&&window.__gCrWeb.message.invokeOnHost;if(a){a({command:"cast.sender.init"});return}}window.chrome?(a=window.navigator.userAgent,0<=a.indexOf("Android")&&0<=a.indexOf("Chrome/")&&window.navigator.presentation?(a=kj()?"50":"",sj("//www.gstatic.com/eureka/clank"+a+lj,rj)):qj(0)):rj()}
;var uj=w(),vj=null,wj=Array(50),xj=-1,yj=!1;function zj(){var a=Aj;Bj();vj.push(a);Cj(vj)}
function Dj(a,b){Bj();var c=vj,d=Ej(a,String(b));0==c.length?Fj(d):(Cj(c),y(c,function(a){a(d)}))}
function Bj(){vj||(vj=r("yt.mdx.remote.debug.handlers_")||[],q("yt.mdx.remote.debug.handlers_",vj,void 0))}
function Fj(a){var b=(xj+1)%50;xj=b;wj[b]=a;yj||(yj=49==b)}
function Cj(a){var b=wj;if(b[0]){var c=xj,d=yj?c:-1;do{var d=(d+1)%50,e=b[d];y(a,function(a){a(e)})}while(d!=c);
wj=Array(50);xj=-1;yj=!1}}
function Ej(a,b){var c=(w()-uj)/1E3;c.toFixed&&(c=c.toFixed(3));var d=[];d.push("[",c+"s","] ");d.push("[","yt.mdx.remote","] ");d.push(a+": "+b,"\n");return d.join("")}
;function Gj(a){a=a||{};this.name=a.name||"";this.id=a.id||a.screenId||"";this.token=a.token||a.loungeToken||"";this.uuid=a.uuid||a.dialId||""}
function Hj(a,b){return!!b&&(a.id==b||a.uuid==b)}
function Ij(a){return{name:a.name,screenId:a.id,loungeToken:a.token,dialId:a.uuid}}
function Jj(a){return new Gj(a)}
function Kj(a){return da(a)?Ka(a,Jj):[]}
function Lj(a){return a?'{name:"'+a.name+'",id:'+a.id.substr(0,6)+"..,token:"+(a.token?".."+a.token.slice(-6):"-")+",uuid:"+(a.uuid?".."+a.uuid.slice(-6):"-")+"}":"null"}
function Mj(a){return da(a)?"["+Ka(a,Lj).join(",")+"]":"null"}
;var Nj={Be:"atp",Ke:"ska",He:"que",Fe:"mus",Je:"sus"};function Oj(a){this.h=this.g="";this.b="/api/lounge";this.f=!0;a=a||document.location.href;var b=Number(a.match(wd)[4]||null)||null||"";b&&(this.h=":"+b);this.g=xd(a)||"";a=nb;0<=a.search("MSIE")&&(a=a.match(/MSIE ([\d.]+)/)[1],0>Fa(a,"10.0")&&(this.f=!1))}
function Pj(a,b,c,d){var e=a.b;if(p(d)?d:a.f)e="https://"+a.g+a.h+a.b;return Fd(e+b,c||{})}
function Qj(a,b,c,d,e){a={format:"JSON",method:"POST",context:a,timeout:5E3,withCredentials:!1,Z:oa(a.l,d,!0),onError:oa(a.i,e),Ca:oa(a.j,e)};c&&(a.J=c,a.headers={"Content-Type":"application/x-www-form-urlencoded"});return Pd(b,a)}
Oj.prototype.l=function(a,b,c,d){b?a(d):a({text:c.responseText})};
Oj.prototype.i=function(a,b){a(Error("Request error: "+b.status))};
Oj.prototype.j=function(a){a(Error("request timed out"))};function Rj(){return"xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,function(a){var b=16*Math.random()|0;return("x"==a?b:b&3|8).toString(16)})}
function Sj(a,b){return Na(a,function(a){return a.key==b})}
function Tj(a){return Ka(a,function(a){return{key:a.id,name:a.name}})}
function Uj(a,b){return Na(a,function(a){return a||b?!a!=!b?!1:a.id==b.id:!0})}
function Vj(a,b){return Na(a,function(a){return Hj(a,b)})}
;function V(){C.call(this);this.l=new E;Wb(this,oa(D,this.l))}
x(V,C);V.prototype.subscribe=function(a,b,c){return this.isDisposed()?0:this.l.subscribe(a,b,c)};
V.prototype.unsubscribe=function(a,b,c){return this.isDisposed()?!1:this.l.unsubscribe(a,b,c)};
V.prototype.ga=function(a){return this.isDisposed()?!1:this.l.ga(a)};
V.prototype.u=function(a,b){return this.isDisposed()?!1:this.l.u.apply(this.l,arguments)};function Wj(a){V.call(this);this.j=a;this.screens=[]}
x(Wj,V);Wj.prototype.S=function(){return this.screens};
Wj.prototype.contains=function(a){return!!Uj(this.screens,a)};
Wj.prototype.get=function(a){return a?Vj(this.screens,a):null};
function Xj(a,b){var c=a.get(b.uuid)||a.get(b.id);if(c){var d=c.name;c.id=b.id||c.id;c.name=b.name;c.token=b.token;c.uuid=b.uuid||c.uuid;return c.name!=d}a.screens.push(b);return!0}
function Yj(a,b){var c=a.screens.length!=b.length;a.screens=Ja(a.screens,function(a){return!!Uj(b,a)});
for(var d=0,e=b.length;d<e;d++)c=Xj(a,b[d])||c;return c}
function Zj(a,b){var c=a.screens.length;a.screens=Ja(a.screens,function(a){return!(a||b?!a!=!b?0:a.id==b.id:1)});
return a.screens.length<c}
Wj.prototype.info=function(a){Dj(this.j,a)};function ak(a,b,c,d){V.call(this);this.o=a;this.j=b;this.h=c;this.i=d;this.g=0;this.b=null;this.f=NaN}
x(ak,V);var bk=[2E3,2E3,1E3,1E3,1E3,2E3,2E3,5E3,5E3,1E4];g=ak.prototype;g.start=function(){!this.b&&isNaN(this.f)&&this.wc()};
g.stop=function(){this.b&&(this.b.abort(),this.b=null);isNaN(this.f)||(I(this.f),this.f=NaN)};
g.w=function(){this.stop();ak.A.w.call(this)};
g.wc=function(){this.f=NaN;this.b=Pd(Pj(this.o,"/pairing/get_screen"),{method:"POST",J:{pairing_code:this.j},timeout:5E3,Z:v(this.se,this),onError:v(this.re,this),Ca:v(this.te,this)})};
g.se=function(a,b){this.b=null;var c=b.screen||{};c.dialId=this.h;c.name=this.i;this.u("pairingComplete",new Gj(c))};
g.re=function(a){this.b=null;a.status&&404==a.status?this.g>=bk.length?this.u("pairingFailed",Error("DIAL polling timed out")):(a=bk[this.g],this.f=G(v(this.wc,this),a),this.g++):this.u("pairingFailed",Error("Server error "+a.status))};
g.te=function(){this.b=null;this.u("pairingFailed",Error("Server not responding"))};function ck(a){this.app=this.name=this.id="";this.type="REMOTE_CONTROL";this.avatar=this.username="";this.capabilities=new Tc;this.experiments=new Tc;this.theme="u";if(a){this.id=a.id||a.name;this.name=a.name;this.app=a.app;this.type=a.type||"REMOTE_CONTROL";this.username=a.user||"";this.avatar=a.userAvatarUri||"";this.theme=a.theme||"u";var b=a.capabilities||"";this.capabilities.clear();Uc(this.capabilities,Ja(b.split(","),oa(ab,Nj)));a=a.experiments||"";this.experiments.clear();Uc(this.experiments,
a.split(","))}}
ck.prototype.equals=function(a){return a?this.id==a.id:!1};var dk;function ek(){var a=fk(),b=gk();z(a,b);if(hk()){var c=a,d;d=0;for(var e=c.length,f;d<e;){var h=d+e>>1,k;k=Xa(b,c[h]);0<k?d=h+1:(e=h,f=!k)}d=f?d:~d;0>d&&Va(c,-(d+1),0,b)}a=ik(a);if(0==a.length)try{Re.remove("remote_sid","/","youtube.com")}catch(m){}else try{Se("remote_sid",a.join(","),-1)}catch(m){}}
function fk(){var a=U("yt-remote-connected-devices")||[];a.sort(Xa);return a}
function ik(a){if(0==a.length)return[];var b=a[0].indexOf("#"),c=-1==b?a[0]:a[0].substring(0,b);return Ka(a,function(a,b){return 0==b?a:a.substring(c.length)})}
function jk(a){T("yt-remote-connected-devices",a,86400)}
function gk(){if(kk)return kk;var a=U("yt-remote-device-id");a||(a=Rj(),T("yt-remote-device-id",a,31536E3));for(var b=fk(),c=1,d=a;z(b,d);)c++,d=a+"#"+c;return kk=d}
function lk(){return U("yt-remote-session-browser-channel")}
function hk(){return U("yt-remote-session-screen-id")}
function mk(a){5<a.length&&(a=a.slice(a.length-5));var b=Ka(nk(),function(a){return a.loungeToken}),c=Ka(a,function(a){return a.loungeToken});
Ma(c,function(a){return!z(b,a)})&&ok();
T("yt-remote-local-screens",a,31536E3)}
function nk(){return U("yt-remote-local-screens")||[]}
function ok(){T("yt-remote-lounge-token-expiration",!0,86400)}
function pk(a,b){T("yt-remote-session-browser-channel",a);T("yt-remote-session-screen-id",b);var c=fk(),d=gk();z(c,d)||c.push(d);jk(c);ek()}
function qk(a){a||(cj("yt-remote-session-screen-id"),cj("yt-remote-session-video-id"));ek();a=fk();Qa(a,gk());jk(a)}
function rk(){if(!dk){var a;a=new Pi;(a=a.isAvailable()?a:null)&&(dk=new Ri(a))}return dk?!!dk.get("yt-remote-use-staging-server"):!1}
var kk="";function sk(a){Wj.call(this,"LocalScreenService");this.f=a;this.b=NaN;tk(this);this.info("Initializing with "+Mj(this.screens))}
x(sk,Wj);g=sk.prototype;g.start=function(){tk(this)&&this.u("screenChange");!U("yt-remote-lounge-token-expiration")&&uk(this);I(this.b);this.b=G(v(this.start,this),1E4)};
g.tb=function(a,b){tk(this);Xj(this,a);vk(this,!1);this.u("screenChange");b(a);a.token||uk(this)};
g.remove=function(a,b){var c=tk(this);Zj(this,a)&&(vk(this,!1),c=!0);b(a);c&&this.u("screenChange")};
g.qb=function(a,b,c,d){var e=tk(this),f=this.get(a.id);f?(f.name!=b&&(f.name=b,vk(this,!1),e=!0),c(a)):d(Error("no such local screen."));e&&this.u("screenChange")};
g.w=function(){I(this.b);sk.A.w.call(this)};
function uk(a){if(a.screens.length){var b=Ka(a.screens,function(a){return a.id}),c=Pj(a.f,"/pairing/get_lounge_token_batch");
Qj(a.f,c,{screen_ids:b.join(",")},v(a.kd,a),v(a.jd,a))}}
g.kd=function(a){tk(this);var b=this.screens.length;a=a&&a.screens||[];for(var c=0,d=a.length;c<d;++c){var e=a[c],f=this.get(e.screenId);f&&(f.token=e.loungeToken,--b)}vk(this,!b);b&&Dj(this.j,"Missed "+b+" lounge tokens.")};
g.jd=function(a){Dj(this.j,"Requesting lounge tokens failed: "+a)};
function tk(a){var b=Kj(nk()),b=Ja(b,function(a){return!a.uuid});
return Yj(a,b)}
function vk(a,b){mk(Ka(a.screens,Ij));b&&ok()}
;function wk(a,b){V.call(this);this.j=b;for(var c=U("yt-remote-online-screen-ids")||"",c=c?c.split(","):[],d={},e=this.j(),f=0,h=e.length;f<h;++f){var k=e[f].id;d[k]=z(c,k)}this.b=d;this.i=a;this.g=this.h=NaN;this.f=null;xk("Initialized with "+L(this.b))}
x(wk,V);g=wk.prototype;g.start=function(){var a=parseInt(U("yt-remote-fast-check-period")||"0",10);(this.h=w()-144E5<a?0:a)?yk(this):(this.h=w()+3E5,T("yt-remote-fast-check-period",this.h),this.Nb())};
g.isEmpty=function(){return hb(this.b)};
g.update=function(){xk("Updating availability on schedule.");var a=this.j(),b=Za(this.b,function(b,d){return b&&!!Vj(a,d)},this);
zk(this,b)};
function Ak(a,b,c){var d=Pj(a.i,"/pairing/get_screen_availability");Qj(a.i,d,{lounge_token:b.token},v(function(a){a=a.screens||[];for(var d=0,h=a.length;d<h;++d)if(a[d].loungeToken==b.token){c("online"==a[d].status);return}c(!1)},a),v(function(){c(!1)},a))}
g.w=function(){I(this.g);this.g=NaN;this.f&&(this.f.abort(),this.f=null);wk.A.w.call(this)};
function zk(a,b){var c;a:if($a(b)!=$a(a.b))c=!1;else{c=db(b);for(var d=0,e=c.length;d<e;++d)if(!a.b[c[d]]){c=!1;break a}c=!0}c||(xk("Updated online screens: "+L(a.b)),a.b=b,a.u("screenChange"));Bk(a)}
function yk(a){isNaN(a.g)||I(a.g);a.g=G(v(a.Nb,a),0<a.h&&a.h<w()?2E4:1E4)}
g.Nb=function(){I(this.g);this.g=NaN;this.f&&this.f.abort();var a=Ck(this);if($a(a)){var b=Pj(this.i,"/pairing/get_screen_availability");this.f=Qj(this.i,b,{lounge_token:db(a).join(",")},v(this.Ld,this,a),v(this.Kd,this))}else zk(this,{}),yk(this)};
g.Ld=function(a,b){this.f=null;var c;a:{c=db(Ck(this));var d=db(a);if(ea(c)&&ea(d)&&c.length==d.length){for(var e=c.length,f=0;f<e;f++)if(c[f]!==d[f]){c=!1;break a}c=!0}else c=!1}if(c){c=b.screens||[];d={};e=0;for(f=c.length;e<f;++e)d[a[c[e].loungeToken]]="online"==c[e].status;zk(this,d);yk(this)}else this.H("Changing Screen set during request."),this.Nb()};
g.Kd=function(a){this.H("Screen availability failed: "+a);this.f=null;yk(this)};
function xk(a){Dj("OnlineScreenService",a)}
g.H=function(a){Dj("OnlineScreenService",a)};
function Ck(a){var b={};y(a.j(),function(a){a.token?b[a.token]=a.id:this.H("Requesting availability of screen w/o lounge token.")});
return b}
function Bk(a){a=db(Za(a.b,function(a){return a}));
a.sort(Xa);a.length?T("yt-remote-online-screen-ids",a.join(","),60):cj("yt-remote-online-screen-ids")}
;function W(a){Wj.call(this,"ScreenService");this.i=a;this.b=this.f=null;this.g=[];this.h={};Dk(this)}
x(W,Wj);g=W.prototype;g.start=function(){this.f.start();this.b.start();this.screens.length&&(this.u("screenChange"),this.b.isEmpty()||this.u("onlineScreenChange"))};
g.tb=function(a,b,c){this.f.tb(a,b,c)};
g.remove=function(a,b,c){this.f.remove(a,b,c);this.b.update()};
g.qb=function(a,b,c,d){this.f.contains(a)?this.f.qb(a,b,c,d):(a="Updating name of unknown screen: "+a.name,Dj(this.j,a),d(Error(a)))};
g.S=function(a){return a?this.screens:Sa(this.screens,Ja(this.g,function(a){return!this.contains(a)},this))};
g.Gc=function(){return Ja(this.S(!0),function(a){return!!this.b.b[a.id]},this)};
function Ek(a,b,c,d,e,f){a.info("getAutomaticScreenByIds "+c+" / "+b);c||(c=a.h[b]);var h=a.S();if(h=(c?Vj(h,c):null)||Vj(h,b)){h.uuid=b;var k=Fk(a,h);Ak(a.b,k,function(a){e(a?k:null)})}else c?Gk(a,c,v(function(a){var f=Fk(this,new Gj({name:d,
screenId:c,loungeToken:a,dialId:b||""}));Ak(this.b,f,function(a){e(a?f:null)})},a),f):e(null)}
g.Hc=function(a,b,c,d,e){this.info("getDialScreenByPairingCode "+a+" / "+b);var f=new ak(this.i,a,b,c);f.subscribe("pairingComplete",v(function(a){D(f);d(Fk(this,a))},this));
f.subscribe("pairingFailed",function(a){D(f);e(a)});
f.start();return v(f.stop,f)};
function Hk(a,b){for(var c=0,d=a.screens.length;c<d;++c)if(a.screens[c].name==b)return a.screens[c];return null}
g.ve=function(a,b,c,d){Pd(Pj(this.i,"/pairing/get_screen"),{method:"POST",J:{pairing_code:a},timeout:5E3,Z:v(function(a,d){var h=new Gj(d.screen||{});if(!h.name||Hk(this,h.name)){var k;a:{k=h.name;for(var m=2,n=b(k,m);Hk(this,n);){m++;if(20<m)break a;n=b(k,m)}k=n}h.name=k}c(Fk(this,h))},this),
onError:v(function(a){d(Error("pairing request failed: "+a.status))},this),
Ca:v(function(){d(Error("pairing request timed out."))},this)})};
g.w=function(){D(this.f);D(this.b);W.A.w.call(this)};
function Gk(a,b,c,d){a.info("requestLoungeToken_ for "+b);var e={J:{screen_ids:b},method:"POST",context:a,Z:function(a,e){var k=e&&e.screens||[];k[0]&&k[0].screenId==b?c(k[0].loungeToken):d(Error("Missing lounge token in token response"))},
onError:function(){d(Error("Request screen lounge token failed"))}};
Pd(Pj(a.i,"/pairing/get_lounge_token_batch"),e)}
function Ik(a){a.screens=a.f.S();var b=a.h,c={},d;for(d in b)c[b[d]]=d;b=0;for(d=a.screens.length;b<d;++b){var e=a.screens[b];e.uuid=c[e.id]||""}a.info("Updated manual screens: "+Mj(a.screens))}
g.ld=function(){Ik(this);this.u("screenChange");this.b.update()};
function Dk(a){Jk(a);a.f=new sk(a.i);a.f.subscribe("screenChange",v(a.ld,a));Ik(a);a.g=Kj(U("yt-remote-automatic-screen-cache")||[]);Jk(a);a.info("Initializing automatic screens: "+Mj(a.g));a.b=new wk(a.i,v(a.S,a,!0));a.b.subscribe("screenChange",v(function(){this.u("onlineScreenChange")},a))}
function Fk(a,b){var c=a.get(b.id);c?(c.uuid=b.uuid,b=c):((c=Vj(a.g,b.uuid))?(c.id=b.id,c.token=b.token,b=c):a.g.push(b),T("yt-remote-automatic-screen-cache",Ka(a.g,Ij)));Jk(a);a.h[b.uuid]=b.id;T("yt-remote-device-id-map",a.h,31536E3);return b}
function Jk(a){a.h=U("yt-remote-device-id-map")||{}}
W.prototype.dispose=W.prototype.dispose;function Kk(a,b,c){V.call(this);this.L=c;this.D=a;this.f=b;this.g=null}
x(Kk,V);g=Kk.prototype;g.kb=function(a){this.g=a;this.u("sessionScreen",this.g)};
g.T=function(a){this.isDisposed()||(a&&Lk(this,""+a),this.g=null,this.u("sessionScreen",null))};
g.info=function(a){Dj(this.L,a)};
function Lk(a,b){Dj(a.L,b)}
g.Jc=function(){return null};
g.Pb=function(a){var b=this.f;a?(b.displayStatus=new chrome.cast.ReceiverDisplayStatus(a,[]),b.displayStatus.showStop=!0):b.displayStatus=null;chrome.cast.setReceiverDisplayStatus(b,v(function(){this.info("Updated receiver status for "+b.friendlyName+": "+a)},this),v(function(){Lk(this,"Failed to update receiver status for: "+b.friendlyName)},this))};
g.w=function(){this.Pb("");Kk.A.w.call(this)};function Mk(a,b){Kk.call(this,a,b,"CastSession");this.b=null;this.i=0;this.h=null;this.o=v(this.we,this);this.j=v(this.Wd,this);this.i=G(v(function(){Nk(this,null)},this),12E4)}
x(Mk,Kk);g=Mk.prototype;g.Ob=function(a){if(this.b){if(this.b==a)return;Lk(this,"Overriding cast sesison with new session object");this.b.removeUpdateListener(this.o);this.b.removeMessageListener("urn:x-cast:com.google.youtube.mdx",this.j)}this.b=a;this.b.addUpdateListener(this.o);this.b.addMessageListener("urn:x-cast:com.google.youtube.mdx",this.j);this.h&&Ok(this);Pk(this,"getMdxSessionStatus")};
g.Ja=function(a){this.info("launchWithParams: "+L(a));this.h=a;this.b&&Ok(this)};
g.stop=function(){this.b?this.b.stop(v(function(){this.T()},this),v(function(){this.T(Error("Failed to stop receiver app."))},this)):this.T(Error("Stopping cast device witout session."))};
g.Pb=t;g.w=function(){this.info("disposeInternal");I(this.i);this.i=0;this.b&&(this.b.removeUpdateListener(this.o),this.b.removeMessageListener("urn:x-cast:com.google.youtube.mdx",this.j));this.b=null;Mk.A.w.call(this)};
function Ok(a){var b=a.h.videoId;b&&Pk(a,"flingVideo",{videoId:b,currentTime:a.h.currentTime||0});a.h=null}
function Pk(a,b,c){a.info("sendYoutubeMessage_: "+b+" "+L(c));var d={};d.type=b;c&&(d.data=c);a.b?a.b.sendMessage("urn:x-cast:com.google.youtube.mdx",d,t,v(function(){Lk(this,"Failed to send message: "+b+".")},a)):Lk(a,"Sending yt message without session: "+L(d))}
g.Wd=function(a,b){if(!this.isDisposed())if(b){var c=pd(b);if(c){var d=""+c.type,c=c.data||{};this.info("onYoutubeMessage_: "+d+" "+L(c));switch(d){case "mdxSessionStatus":Nk(this,c.screenId);break;default:Lk(this,"Unknown youtube message: "+d)}}else Lk(this,"Unable to parse message.")}else Lk(this,"No data in message.")};
function Nk(a,b){I(a.i);if(b){if(a.info("onConnectedScreenId_: Received screenId: "+b),!a.g||a.g.id!=b){var c=v(a.kb,a),d=v(a.T,a);a.fc(b,c,d,5)}}else a.T(Error("Waiting for session status timed out."))}
g.fc=function(a,b,c,d){Ek(this.D,this.f.label,a,this.f.friendlyName,v(function(e){e?b(e):0<=d?(Lk(this,"Screen "+a+" appears to be offline. "+d+" retries left."),G(v(this.fc,this,a,b,c,d-1),300)):c(Error("Unable to fetch screen."))},this),c)};
g.Jc=function(){return this.b};
g.we=function(a){this.isDisposed()||a||(Lk(this,"Cast session died."),this.T())};function Qk(a,b){Kk.call(this,a,b,"DialSession");this.h=this.C=null;this.G="";this.i=null;this.o=t;this.j=NaN;this.K=v(this.ze,this);this.b=t}
x(Qk,Kk);g=Qk.prototype;g.Ob=function(a){this.h=a;this.h.addUpdateListener(this.K)};
g.Ja=function(a){this.i=a;this.o()};
g.stop=function(){this.b();this.b=t;I(this.j);this.h?this.h.stop(v(this.T,this,null),v(this.T,this,"Failed to stop DIAL device.")):this.T()};
g.w=function(){this.b();this.b=t;I(this.j);this.h&&this.h.removeUpdateListener(this.K);this.h=null;Qk.A.w.call(this)};
function Rk(a){a.b=a.D.Hc(a.G,a.f.label,a.f.friendlyName,v(function(a){this.b=t;this.kb(a)},a),v(function(a){this.b=t;
this.T(a)},a))}
g.ze=function(a){this.isDisposed()||a||(Lk(this,"DIAL session died."),this.b(),this.b=t,this.T())};
function Sk(a){var b={};b.pairingCode=a.G;if(a.i){var c=a.i.currentTime||0;b.v=a.i.videoId;b.t=c}rk()&&(b.env_useStageMdx=1);return Dd(b)}
g.Kb=function(a){this.G=Rj();if(this.i){var b=new chrome.cast.DialLaunchResponse(!0,Sk(this));a(b);Rk(this)}else this.o=v(function(){I(this.j);this.o=t;this.j=NaN;var b=new chrome.cast.DialLaunchResponse(!0,Sk(this));a(b);Rk(this)},this),this.j=G(v(function(){this.o()},this),100)};
g.md=function(a,b){Ek(this.D,this.C.receiver.label,a,this.f.friendlyName,v(function(a){a&&a.token?(this.kb(a),b(new chrome.cast.DialLaunchResponse(!1))):this.Kb(b)},this),v(function(a){Lk(this,"Failed to get DIAL screen: "+a);
this.Kb(b)},this))};function Tk(a,b){Kk.call(this,a,b,"ManualSession");this.b=G(v(this.Ja,this,null),150)}
x(Tk,Kk);Tk.prototype.stop=function(){this.T()};
Tk.prototype.Ob=t;Tk.prototype.Ja=function(){I(this.b);this.b=NaN;var a=Vj(this.D.S(),this.f.label);a?this.kb(a):this.T(Error("No such screen"))};
Tk.prototype.w=function(){I(this.b);this.b=NaN;Tk.A.w.call(this)};function X(a){V.call(this);this.f=a;this.b=null;this.i=!1;this.g=[];this.h=v(this.Id,this)}
x(X,V);g=X.prototype;
g.init=function(a,b){chrome.cast.timeout.requestSession=3E4;var c=new chrome.cast.SessionRequest("233637DE");c.dialRequest=new chrome.cast.DialRequest("YouTube");var d=chrome.cast.AutoJoinPolicy.TAB_AND_ORIGIN_SCOPED,e=a?chrome.cast.DefaultActionPolicy.CAST_THIS_TAB:chrome.cast.DefaultActionPolicy.CREATE_SESSION,c=new chrome.cast.ApiConfig(c,v(this.pc,this),v(this.Jd,this),d,e);c.customDialLaunchCallback=v(this.xd,this);chrome.cast.initialize(c,v(function(){this.isDisposed()||(chrome.cast.addReceiverActionListener(this.h),
zj(),this.f.subscribe("onlineScreenChange",v(this.Ic,this)),this.g=Uk(this),chrome.cast.setCustomReceivers(this.g,t,v(function(a){this.H("Failed to set initial custom receivers: "+L(a))},this)),this.u("yt-remote-cast2-availability-change",Vk(this)),b(!0))},this),v(function(a){this.H("Failed to initialize API: "+L(a));
b(!1)},this))};
g.ie=function(a,b){Wk("Setting connected screen ID: "+a+" -> "+b);if(this.b){var c=this.b.g;if(!a||c&&c.id!=a)Wk("Unsetting old screen status: "+this.b.f.friendlyName),D(this.b),this.b=null}if(a&&b){if(!this.b){c=Vj(this.f.S(),a);if(!c){Wk("setConnectedScreenStatus: Unknown screen.");return}var d=Xk(this,c);d||(Wk("setConnectedScreenStatus: Connected receiver not custom..."),d=new chrome.cast.Receiver(c.uuid?c.uuid:c.id,c.name),d.receiverType=chrome.cast.ReceiverType.CUSTOM,this.g.push(d),chrome.cast.setCustomReceivers(this.g,
t,v(function(a){this.H("Failed to set initial custom receivers: "+L(a))},this)));
Wk("setConnectedScreenStatus: new active receiver: "+d.friendlyName);Yk(this,new Tk(this.f,d),!0)}this.b.Pb(b)}else Wk("setConnectedScreenStatus: no screen.")};
function Xk(a,b){return b?Na(a.g,function(a){return Hj(b,a.label)},a):null}
g.je=function(a){this.isDisposed()?this.H("Setting connection data on disposed cast v2"):this.b?this.b.Ja(a):this.H("Setting connection data without a session")};
g.ye=function(){this.isDisposed()?this.H("Stopping session on disposed cast v2"):this.b?(this.b.stop(),D(this.b),this.b=null):Wk("Stopping non-existing session")};
g.requestSession=function(){chrome.cast.requestSession(v(this.pc,this),v(this.Md,this))};
g.w=function(){this.f.unsubscribe("onlineScreenChange",v(this.Ic,this));window.chrome&&chrome.cast&&chrome.cast.removeReceiverActionListener(this.h);var a=Aj,b=r("yt.mdx.remote.debug.handlers_");Qa(b||[],a);D(this.b);X.A.w.call(this)};
function Wk(a){Dj("Controller",a)}
g.H=function(a){Dj("Controller",a)};
function Aj(a){window.chrome&&chrome.cast&&chrome.cast.logMessage&&chrome.cast.logMessage(a)}
function Vk(a){return a.i||!!a.g.length||!!a.b}
function Yk(a,b,c){D(a.b);(a.b=b)?(c?a.u("yt-remote-cast2-receiver-resumed",b.f):a.u("yt-remote-cast2-receiver-selected",b.f),b.subscribe("sessionScreen",v(a.qc,a,b)),b.g?a.u("yt-remote-cast2-session-change",b.g):c&&a.b.Ja(null)):a.u("yt-remote-cast2-session-change",null)}
g.qc=function(a,b){this.b==a&&(b||Yk(this,null),this.u("yt-remote-cast2-session-change",b))};
g.Id=function(a,b){if(!this.isDisposed())if(a)switch(Wk("onReceiverAction_ "+a.label+" / "+a.friendlyName+"-- "+b),b){case chrome.cast.ReceiverAction.CAST:if(this.b)if(this.b.f.label!=a.label)Wk("onReceiverAction_: Stopping active receiver: "+this.b.f.friendlyName),this.b.stop();else{Wk("onReceiverAction_: Casting to active receiver.");this.b.g&&this.u("yt-remote-cast2-session-change",this.b.g);break}switch(a.receiverType){case chrome.cast.ReceiverType.CUSTOM:Yk(this,new Tk(this.f,a));break;case chrome.cast.ReceiverType.DIAL:Yk(this,
new Qk(this.f,a));break;case chrome.cast.ReceiverType.CAST:Yk(this,new Mk(this.f,a));break;default:this.H("Unknown receiver type: "+a.receiverType)}break;case chrome.cast.ReceiverAction.STOP:this.b&&this.b.f.label==a.label?this.b.stop():this.H("Stopping receiver w/o session: "+a.friendlyName)}else this.H("onReceiverAction_ called without receiver.")};
g.xd=function(a){if(this.isDisposed())return Promise.reject(Error("disposed"));var b=a.receiver;b.receiverType!=chrome.cast.ReceiverType.DIAL&&(this.H("Not DIAL receiver: "+b.friendlyName),b.receiverType=chrome.cast.ReceiverType.DIAL);var c=this.b?this.b.f:null;if(!c||c.label!=b.label)return this.H("Receiving DIAL launch request for non-clicked DIAL receiver: "+b.friendlyName),Promise.reject(Error("illegal DIAL launch"));if(c&&c.label==b.label&&c.receiverType!=chrome.cast.ReceiverType.DIAL){if(this.b.g)return Wk("Reselecting dial screen."),
this.u("yt-remote-cast2-session-change",this.b.g),Promise.resolve(new chrome.cast.DialLaunchResponse(!1));this.H('Changing CAST intent from "'+c.receiverType+'" to "dial" for '+b.friendlyName);Yk(this,new Qk(this.f,b))}b=this.b;b.C=a;return b.C.appState==chrome.cast.DialAppState.RUNNING?new Promise(v(b.md,b,(b.C.extraData||{}).screenId||null)):new Promise(v(b.Kb,b))};
g.pc=function(a){if(!this.isDisposed()){Wk("New cast session ID: "+a.sessionId);var b=a.receiver;if(b.receiverType!=chrome.cast.ReceiverType.CUSTOM){if(!this.b)if(b.receiverType==chrome.cast.ReceiverType.CAST)Wk("Got resumed cast session before resumed mdx connection."),Yk(this,new Mk(this.f,b),!0);else{this.H("Got non-cast session without previous mdx receiver event, or mdx resume.");return}var c=this.b.f,d=Vj(this.f.S(),c.label);d&&Hj(d,b.label)&&c.receiverType!=chrome.cast.ReceiverType.CAST&&b.receiverType==
chrome.cast.ReceiverType.CAST&&(Wk("onSessionEstablished_: manual to cast session change "+b.friendlyName),D(this.b),this.b=new Mk(this.f,b),this.b.subscribe("sessionScreen",v(this.qc,this,this.b)),this.b.Ja(null));this.b.Ob(a)}}};
g.xe=function(){return this.b?this.b.Jc():null};
g.Md=function(a){this.isDisposed()||(this.H("Failed to estabilish a session: "+L(a)),a.code!=chrome.cast.ErrorCode.CANCEL&&Yk(this,null))};
g.Jd=function(a){Wk("Receiver availability updated: "+a);if(!this.isDisposed()){var b=Vk(this);this.i=a==chrome.cast.ReceiverAvailability.AVAILABLE;Vk(this)!=b&&this.u("yt-remote-cast2-availability-change",Vk(this))}};
function Uk(a){var b=a.f.Gc(),c=a.b&&a.b.f;a=Ka(b,function(a){c&&Hj(a,c.label)&&(c=null);var b=a.uuid?a.uuid:a.id,f=Xk(this,a);f?(f.label=b,f.friendlyName=a.name):(f=new chrome.cast.Receiver(b,a.name),f.receiverType=chrome.cast.ReceiverType.CUSTOM);return f},a);
c&&(c.receiverType!=chrome.cast.ReceiverType.CUSTOM&&(c=new chrome.cast.Receiver(c.label,c.friendlyName),c.receiverType=chrome.cast.ReceiverType.CUSTOM),a.push(c));return a}
g.Ic=function(){if(!this.isDisposed()){var a=Vk(this);this.g=Uk(this);Wk("Updating custom receivers: "+L(this.g));chrome.cast.setCustomReceivers(this.g,t,v(function(){this.H("Failed to set custom receivers.")},this));
var b=Vk(this);b!=a&&this.u("yt-remote-cast2-availability-change",b)}};
X.prototype.setLaunchParams=X.prototype.je;X.prototype.setConnectedScreenStatus=X.prototype.ie;X.prototype.stopSession=X.prototype.ye;X.prototype.getCastSession=X.prototype.xe;X.prototype.requestSession=X.prototype.requestSession;X.prototype.init=X.prototype.init;X.prototype.dispose=X.prototype.dispose;function Zk(a,b,c){$k()?bl(a)&&(cl(!0),window.chrome&&chrome.cast&&chrome.cast.isAvailable?dl(b):(window.__onGCastApiAvailable=function(a,c){a?dl(b):(el("Failed to load cast API: "+c),fl(!1),cl(!1),cj("yt-remote-cast-available"),cj("yt-remote-cast-receiver"),gl(),b(!1))},c?sc("https://www.gstatic.com/cv/js/sender/v1/cast_sender.js"):tj())):al("Cannot initialize because not running Chrome")}
function gl(){al("dispose");var a=hl();a&&a.dispose();il=null;q("yt.mdx.remote.cloudview.instance_",null,void 0);jl(!1);oc(kl);kl.length=0}
function ll(){return!!U("yt-remote-cast-installed")}
function ml(){var a=U("yt-remote-cast-receiver");a?(a=a.friendlyName,a=-1!=a.indexOf("&")?"document"in l?Aa(a):Ca(a):a):a=null;return a}
function nl(){al("clearCurrentReciever");cj("yt-remote-cast-receiver")}
function ol(){ll()?hl()?pl()?(al("Requesting cast selector."),il.requestSession()):(al("Wait for cast API to be ready to request the session."),kl.push(mc("yt-remote-cast2-api-ready",ol))):el("requestCastSelector: Cast is not initialized."):el("requestCastSelector: Cast API is not installed!")}
function ql(a){pl()?hl().setLaunchParams(a):el("setLaunchParams called before ready.")}
function rl(a,b){pl()?hl().setConnectedScreenStatus(a,b):el("setConnectedScreenStatus called before ready.")}
var il=null;function $k(){var a;a=0<=nb.search(/\ (CrMo|Chrome|CriOS)\//);return qf||a}
function bl(a){var b=!1;if(!il){var c=r("yt.mdx.remote.cloudview.instance_");c||(c=new X(a),c.subscribe("yt-remote-cast2-availability-change",function(a){T("yt-remote-cast-available",a);J("yt-remote-cast2-availability-change",a)}),c.subscribe("yt-remote-cast2-receiver-selected",function(a){al("onReceiverSelected: "+a.friendlyName);
T("yt-remote-cast-receiver",a);J("yt-remote-cast2-receiver-selected",a)}),c.subscribe("yt-remote-cast2-receiver-resumed",function(a){al("onReceiverResumed: "+a.friendlyName);
T("yt-remote-cast-receiver",a)}),c.subscribe("yt-remote-cast2-session-change",function(a){al("onSessionChange: "+Lj(a));
a||cj("yt-remote-cast-receiver");J("yt-remote-cast2-session-change",a)}),q("yt.mdx.remote.cloudview.instance_",c,void 0),b=!0);
il=c}al("cloudview.createSingleton_: "+b);return b}
function hl(){il||(il=r("yt.mdx.remote.cloudview.instance_"));return il}
function dl(a){fl(!0);cl(!1);il.init(!0,function(b){b?(jl(!0),J("yt-remote-cast2-api-ready")):(el("Failed to initialize cast API."),fl(!1),cj("yt-remote-cast-available"),cj("yt-remote-cast-receiver"),gl());a(b)})}
function al(a){Dj("cloudview",a)}
function el(a){Dj("cloudview",a)}
function fl(a){al("setCastInstalled_ "+a);T("yt-remote-cast-installed",a)}
function pl(){return!!r("yt.mdx.remote.cloudview.apiReady_")}
function jl(a){al("setApiReady_ "+a);q("yt.mdx.remote.cloudview.apiReady_",a,void 0)}
function cl(a){q("yt.mdx.remote.cloudview.initializing_",a,void 0)}
var kl=[];function sl(){}
;function tl(a,b){this.action=a;this.params=b||null}
;function ul(){this.b=w()}
new ul;ul.prototype.set=function(a){this.b=a};
ul.prototype.reset=function(){this.set(w())};
ul.prototype.get=function(){return this.b};function vl(a,b){this.type=a;this.currentTarget=this.target=b;this.defaultPrevented=this.b=!1;this.zc=!0}
vl.prototype.stopPropagation=function(){this.b=!0};
vl.prototype.preventDefault=function(){this.defaultPrevented=!0;this.zc=!1};var wl=!K||ld(9),xl=K&&!kd("9");!ad||kd("528");$c&&kd("1.9b")||K&&kd("8")||Yc&&kd("9.5")||ad&&kd("528");$c&&!kd("8")||K&&kd("9");function yl(a,b){vl.call(this,a?a.type:"");this.relatedTarget=this.currentTarget=this.target=null;this.charCode=this.keyCode=this.button=this.screenY=this.screenX=this.clientY=this.clientX=0;this.metaKey=this.shiftKey=this.altKey=this.ctrlKey=!1;this.f=this.state=null;a&&this.init(a,b)}
x(yl,vl);
yl.prototype.init=function(a,b){var c=this.type=a.type,d=a.changedTouches?a.changedTouches[0]:null;this.target=a.target||a.srcElement;this.currentTarget=b;var e=a.relatedTarget;if(e){if($c){var f;a:{try{af(e.nodeName);f=!0;break a}catch(h){}f=!1}f||(e=null)}}else"mouseover"==c?e=a.fromElement:"mouseout"==c&&(e=a.toElement);this.relatedTarget=e;null===d?(this.clientX=void 0!==a.clientX?a.clientX:a.pageX,this.clientY=void 0!==a.clientY?a.clientY:a.pageY,this.screenX=a.screenX||0,this.screenY=a.screenY||
0):(this.clientX=void 0!==d.clientX?d.clientX:d.pageX,this.clientY=void 0!==d.clientY?d.clientY:d.pageY,this.screenX=d.screenX||0,this.screenY=d.screenY||0);this.button=a.button;this.keyCode=a.keyCode||0;this.charCode=a.charCode||("keypress"==c?a.keyCode:0);this.ctrlKey=a.ctrlKey;this.altKey=a.altKey;this.shiftKey=a.shiftKey;this.metaKey=a.metaKey;this.state=a.state;this.f=a;a.defaultPrevented&&this.preventDefault()};
yl.prototype.stopPropagation=function(){yl.A.stopPropagation.call(this);this.f.stopPropagation?this.f.stopPropagation():this.f.cancelBubble=!0};
yl.prototype.preventDefault=function(){yl.A.preventDefault.call(this);var a=this.f;if(a.preventDefault)a.preventDefault();else if(a.returnValue=!1,xl)try{if(a.ctrlKey||112<=a.keyCode&&123>=a.keyCode)a.keyCode=-1}catch(b){}};var zl="closure_listenable_"+(1E6*Math.random()|0),Al=0;function Bl(a,b,c,d,e){this.listener=a;this.b=null;this.src=b;this.type=c;this.bb=!!d;this.gb=e;this.key=++Al;this.La=this.ab=!1}
function Cl(a){a.La=!0;a.listener=null;a.b=null;a.src=null;a.gb=null}
;function Dl(a){this.src=a;this.b={};this.f=0}
function El(a,b,c,d,e){var f=b.toString();b=a.b[f];b||(b=a.b[f]=[],a.f++);var h=Fl(b,c,d,e);-1<h?(a=b[h],a.ab=!1):(a=new Bl(c,a.src,f,!!d,e),a.ab=!1,b.push(a));return a}
Dl.prototype.remove=function(a,b,c,d){a=a.toString();if(!(a in this.b))return!1;var e=this.b[a];b=Fl(e,b,c,d);return-1<b?(Cl(e[b]),Array.prototype.splice.call(e,b,1),0==e.length&&(delete this.b[a],this.f--),!0):!1};
function Gl(a,b){var c=b.type;c in a.b&&Qa(a.b[c],b)&&(Cl(b),0==a.b[c].length&&(delete a.b[c],a.f--))}
function Hl(a,b,c,d,e){a=a.b[b.toString()];b=-1;a&&(b=Fl(a,c,d,e));return-1<b?a[b]:null}
function Fl(a,b,c,d){for(var e=0;e<a.length;++e){var f=a[e];if(!f.La&&f.listener==b&&f.bb==!!c&&f.gb==d)return e}return-1}
;var Il="closure_lm_"+(1E6*Math.random()|0),Jl={},Kl=0;
function Ll(a,b,c,d,e){if(da(b)){for(var f=0;f<b.length;f++)Ll(a,b[f],c,d,e);return null}c=Ml(c);if(a&&a[zl])a=a.hb(b,c,d,e);else{if(!b)throw Error("Invalid event type");var f=!!d,h=Nl(a);h||(a[Il]=h=new Dl(a));c=El(h,b,c,d,e);if(!c.b){d=Ol();c.b=d;d.src=a;d.listener=c;if(a.addEventListener)a.addEventListener(b.toString(),d,f);else if(a.attachEvent)a.attachEvent(Pl(b.toString()),d);else throw Error("addEventListener and attachEvent are unavailable.");Kl++}a=c}return a}
function Ol(){var a=Ql,b=wl?function(c){return a.call(b.src,b.listener,c)}:function(c){c=a.call(b.src,b.listener,c);
if(!c)return c};
return b}
function Rl(a,b,c,d,e){if(da(b))for(var f=0;f<b.length;f++)Rl(a,b[f],c,d,e);else c=Ml(c),a&&a[zl]?a.pb(b,c,d,e):a&&(a=Nl(a))&&(b=Hl(a,b,c,!!d,e))&&Sl(b)}
function Sl(a){if(!fa(a)&&a&&!a.La){var b=a.src;if(b&&b[zl])Gl(b.g,a);else{var c=a.type,d=a.b;b.removeEventListener?b.removeEventListener(c,d,a.bb):b.detachEvent&&b.detachEvent(Pl(c),d);Kl--;(c=Nl(b))?(Gl(c,a),0==c.f&&(c.src=null,b[Il]=null)):Cl(a)}}}
function Pl(a){return a in Jl?Jl[a]:Jl[a]="on"+a}
function Tl(a,b,c,d){var e=!0;if(a=Nl(a))if(b=a.b[b.toString()])for(b=b.concat(),a=0;a<b.length;a++){var f=b[a];f&&f.bb==c&&!f.La&&(f=Ul(f,d),e=e&&!1!==f)}return e}
function Ul(a,b){var c=a.listener,d=a.gb||a.src;a.ab&&Sl(a);return c.call(d,b)}
function Ql(a,b){if(a.La)return!0;if(!wl){var c=b||r("window.event"),d=new yl(c,this),e=!0;if(!(0>c.keyCode||void 0!=c.returnValue)){a:{var f=!1;if(0==c.keyCode)try{c.keyCode=-1;break a}catch(m){f=!0}if(f||void 0==c.returnValue)c.returnValue=!0}c=[];for(f=d.currentTarget;f;f=f.parentNode)c.push(f);for(var f=a.type,h=c.length-1;!d.b&&0<=h;h--){d.currentTarget=c[h];var k=Tl(c[h],f,!0,d),e=e&&k}for(h=0;!d.b&&h<c.length;h++)d.currentTarget=c[h],k=Tl(c[h],f,!1,d),e=e&&k}return e}return Ul(a,new yl(b,this))}
function Nl(a){a=a[Il];return a instanceof Dl?a:null}
var Vl="__closure_events_fn_"+(1E9*Math.random()>>>0);function Ml(a){if(ga(a))return a;a[Vl]||(a[Vl]=function(b){return a.handleEvent(b)});
return a[Vl]}
;function Wl(){C.call(this);this.g=new Dl(this);this.pa=this;this.ba=null}
x(Wl,C);Wl.prototype[zl]=!0;g=Wl.prototype;g.addEventListener=function(a,b,c,d){Ll(this,a,b,c,d)};
g.removeEventListener=function(a,b,c,d){Rl(this,a,b,c,d)};
function Xl(a,b){var c,d=a.ba;if(d){c=[];for(var e=1;d;d=d.ba)c.push(d),++e}var d=a.pa,e=b,f=e.type||e;if(u(e))e=new vl(e,d);else if(e instanceof vl)e.target=e.target||d;else{var h=e,e=new vl(f,d);mb(e,h)}var h=!0,k;if(c)for(var m=c.length-1;!e.b&&0<=m;m--)k=e.currentTarget=c[m],h=Yl(k,f,!0,e)&&h;e.b||(k=e.currentTarget=d,h=Yl(k,f,!0,e)&&h,e.b||(h=Yl(k,f,!1,e)&&h));if(c)for(m=0;!e.b&&m<c.length;m++)k=e.currentTarget=c[m],h=Yl(k,f,!1,e)&&h}
g.w=function(){Wl.A.w.call(this);if(this.g){var a=this.g,b=0,c;for(c in a.b){for(var d=a.b[c],e=0;e<d.length;e++)++b,Cl(d[e]);delete a.b[c];a.f--}}this.ba=null};
g.hb=function(a,b,c,d){return El(this.g,String(a),b,c,d)};
g.pb=function(a,b,c,d){return this.g.remove(String(a),b,c,d)};
function Yl(a,b,c,d){b=a.g.b[String(b)];if(!b)return!0;b=b.concat();for(var e=!0,f=0;f<b.length;++f){var h=b[f];if(h&&!h.La&&h.bb==c){var k=h.listener,m=h.gb||h.src;h.ab&&Gl(a.g,h);e=!1!==k.call(m,d)&&e}}return e&&0!=d.zc}
;function Zl(a,b){this.f=new rd(a);this.b=b?pd:od}
Zl.prototype.stringify=function(a){return qd(this.f,a)};
Zl.prototype.parse=function(a){return this.b(a)};function $l(a,b){this.b=0;this.j=void 0;this.h=this.f=this.g=null;this.i=this.l=!1;if(a!=t)try{var c=this;a.call(b,function(a){am(c,2,a)},function(a){am(c,3,a)})}catch(d){am(this,3,d)}}
function bm(){this.next=this.context=this.f=this.h=this.b=null;this.g=!1}
bm.prototype.reset=function(){this.context=this.f=this.h=this.b=null;this.g=!1};
var cm=new Lb(function(){return new bm},function(a){a.reset()},100);
function dm(a,b,c){var d=cm.get();d.h=a;d.f=b;d.context=c;return d}
function em(a){return new $l(function(b,c){c(a)})}
$l.prototype.then=function(a,b,c){return fm(this,ga(a)?a:null,ga(b)?b:null,c)};
$l.prototype.then=$l.prototype.then;$l.prototype.$goog_Thenable=!0;$l.prototype.cancel=function(a){0==this.b&&Qb(function(){var b=new gm(a);hm(this,b)},this)};
function hm(a,b){if(0==a.b)if(a.g){var c=a.g;if(c.f){for(var d=0,e=null,f=null,h=c.f;h&&(h.g||(d++,h.b==a&&(e=h),!(e&&1<d)));h=h.next)e||(f=h);e&&(0==c.b&&1==d?hm(c,b):(f?(d=f,d.next==c.h&&(c.h=d),d.next=d.next.next):im(c),jm(c,e,3,b)))}a.g=null}else am(a,3,b)}
function km(a,b){a.f||2!=a.b&&3!=a.b||lm(a);a.h?a.h.next=b:a.f=b;a.h=b}
function fm(a,b,c,d){var e=dm(null,null,null);e.b=new $l(function(a,h){e.h=b?function(c){try{var e=b.call(d,c);a(e)}catch(n){h(n)}}:a;
e.f=c?function(b){try{var e=c.call(d,b);!p(e)&&b instanceof gm?h(b):a(e)}catch(n){h(n)}}:h});
e.b.g=a;km(a,e);return e.b}
$l.prototype.C=function(a){this.b=0;am(this,2,a)};
$l.prototype.V=function(a){this.b=0;am(this,3,a)};
function am(a,b,c){if(0==a.b){a==c&&(b=3,c=new TypeError("Promise cannot resolve to itself"));a.b=1;var d;a:{var e=c,f=a.C,h=a.V;if(e instanceof $l)km(e,dm(f||t,h||null,a)),d=!0;else{var k;if(e)try{k=!!e.$goog_Thenable}catch(n){k=!1}else k=!1;if(k)e.then(f,h,a),d=!0;else{if(ha(e))try{var m=e.then;if(ga(m)){mm(e,m,f,h,a);d=!0;break a}}catch(n){h.call(a,n);d=!0;break a}d=!1}}}d||(a.j=c,a.b=b,a.g=null,lm(a),3!=b||c instanceof gm||nm(a,c))}}
function mm(a,b,c,d,e){function f(a){k||(k=!0,d.call(e,a))}
function h(a){k||(k=!0,c.call(e,a))}
var k=!1;try{b.call(a,h,f)}catch(m){f(m)}}
function lm(a){a.l||(a.l=!0,Qb(a.o,a))}
function im(a){var b=null;a.f&&(b=a.f,a.f=b.next,b.next=null);a.f||(a.h=null);return b}
$l.prototype.o=function(){for(var a;a=im(this);)jm(this,a,this.b,this.j);this.l=!1};
function jm(a,b,c,d){if(3==c&&b.f&&!b.g)for(;a&&a.i;a=a.g)a.i=!1;if(b.b)b.b.g=null,om(b,c,d);else try{b.g?b.h.call(b.context):om(b,c,d)}catch(e){pm.call(null,e)}Mb(cm,b)}
function om(a,b,c){2==b?a.h.call(a.context,c):a.f&&a.f.call(a.context,c)}
function nm(a,b){a.i=!0;Qb(function(){a.i&&pm.call(null,b)})}
var pm=Ib;function gm(a){pa.call(this,a)}
x(gm,pa);gm.prototype.name="cancel";function qm(a,b){Wl.call(this);this.b=a||1;this.f=b||l;this.h=v(this.le,this);this.i=w()}
x(qm,Wl);g=qm.prototype;g.enabled=!1;g.X=null;function rm(a,b){a.b=b;a.X&&a.enabled?(a.stop(),a.start()):a.X&&a.stop()}
g.le=function(){if(this.enabled){var a=w()-this.i;0<a&&a<.8*this.b?this.X=this.f.setTimeout(this.h,this.b-a):(this.X&&(this.f.clearTimeout(this.X),this.X=null),Xl(this,"tick"),this.enabled&&(this.X=this.f.setTimeout(this.h,this.b),this.i=w()))}};
g.start=function(){this.enabled=!0;this.X||(this.X=this.f.setTimeout(this.h,this.b),this.i=w())};
g.stop=function(){this.enabled=!1;this.X&&(this.f.clearTimeout(this.X),this.X=null)};
g.w=function(){qm.A.w.call(this);this.stop();delete this.f};
function sm(a,b,c){if(ga(a))c&&(a=v(a,c));else if(a&&"function"==typeof a.handleEvent)a=v(a.handleEvent,a);else throw Error("Invalid listener argument");return 2147483647<Number(b)?-1:l.setTimeout(a,b||0)}
;function tm(a,b,c){C.call(this);this.h=null!=c?v(a,c):a;this.g=b;this.f=v(this.Od,this);this.b=[]}
x(tm,C);g=tm.prototype;g.Ma=!1;g.Wa=0;g.ya=null;g.cd=function(a){this.b=arguments;this.ya||this.Wa?this.Ma=!0:um(this)};
g.stop=function(){this.ya&&(l.clearTimeout(this.ya),this.ya=null,this.Ma=!1,this.b=[])};
g.pause=function(){this.Wa++};
g.resume=function(){this.Wa--;this.Wa||!this.Ma||this.ya||(this.Ma=!1,um(this))};
g.w=function(){tm.A.w.call(this);this.stop()};
g.Od=function(){this.ya=null;this.Ma&&!this.Wa&&(this.Ma=!1,um(this))};
function um(a){a.ya=sm(a.f,a.g);a.h.apply(null,a.b)}
;function vm(a){C.call(this);this.f=a;this.b={}}
x(vm,C);var wm=[];vm.prototype.hb=function(a,b,c,d){da(b)||(b&&(wm[0]=b.toString()),b=wm);for(var e=0;e<b.length;e++){var f=Ll(a,b[e],c||this.handleEvent,d||!1,this.f||this);if(!f)break;this.b[f.key]=f}return this};
vm.prototype.pb=function(a,b,c,d,e){if(da(b))for(var f=0;f<b.length;f++)this.pb(a,b[f],c,d,e);else c=c||this.handleEvent,e=e||this.f||this,c=Ml(c),d=!!d,b=a&&a[zl]?Hl(a.g,String(b),c,d,e):a?(a=Nl(a))?Hl(a,b,c,d,e):null:null,b&&(Sl(b),delete this.b[b.key]);return this};
function xm(a){Ya(a.b,function(a,c){this.b.hasOwnProperty(c)&&Sl(a)},a);
a.b={}}
vm.prototype.w=function(){vm.A.w.call(this);xm(this)};
vm.prototype.handleEvent=function(){throw Error("EventHandler.handleEvent not implemented");};function ym(){}
ym.prototype.b=null;function zm(a){var b;(b=a.b)||(b={},Am(a)&&(b[0]=!0,b[1]=!0),b=a.b=b);return b}
;var Bm;function Cm(){}
x(Cm,ym);function Dm(a){return(a=Am(a))?new ActiveXObject(a):new XMLHttpRequest}
function Am(a){if(!a.f&&"undefined"==typeof XMLHttpRequest&&"undefined"!=typeof ActiveXObject){for(var b=["MSXML2.XMLHTTP.6.0","MSXML2.XMLHTTP.3.0","MSXML2.XMLHTTP","Microsoft.XMLHTTP"],c=0;c<b.length;c++){var d=b[c];try{return new ActiveXObject(d),a.f=d}catch(e){}}throw Error("Could not create ActiveXObject. ActiveX might be disabled, or MSXML might not be installed");}return a.f}
Bm=new Cm;function Em(a,b,c,d,e){this.b=a;this.g=c;this.o=d;this.j=e||1;this.i=45E3;this.h=new vm(this);this.f=new qm;rm(this.f,250)}
g=Em.prototype;g.za=null;g.ea=!1;g.Oa=null;g.Rb=null;g.Xa=null;g.Na=null;g.qa=null;g.ta=null;g.Da=null;g.I=null;g.Za=0;g.fa=null;g.sb=null;g.Aa=null;g.Ua=-1;g.Ac=!0;g.va=!1;g.Ib=0;g.mb=null;var Fm={},Gm={};g=Em.prototype;g.setTimeout=function(a){this.i=a};
function Hm(a,b,c){a.Na=1;a.qa=zg(b.clone());a.Da=c;a.l=!0;Im(a,null)}
function Jm(a,b,c,d,e){a.Na=1;a.qa=zg(b.clone());a.Da=null;a.l=c;e&&(a.Ac=!1);Im(a,d)}
function Im(a,b){a.Xa=w();Km(a);a.ta=a.qa.clone();xg(a.ta,"t",a.j);a.Za=0;a.I=a.b.Bb(a.b.Ya()?b:null);0<a.Ib&&(a.mb=new tm(v(a.Fc,a,a.I),a.Ib));a.h.hb(a.I,"readystatechange",a.Yd);var c=a.za?jb(a.za):{};a.Da?(a.sb="POST",c["Content-Type"]="application/x-www-form-urlencoded",a.I.send(a.ta,a.sb,a.Da,c)):(a.sb="GET",a.Ac&&!ad&&(c.Connection="close"),a.I.send(a.ta,a.sb,null,c));a.b.da(1)}
g.Yd=function(a){a=a.target;var b=this.mb;b&&3==Lm(a)?b.cd():this.Fc(a)};
g.Fc=function(a){try{if(a==this.I)a:{var b=Lm(this.I),c=this.I.i,d=this.I.getStatus();if(K&&!ld(10)||ad&&!kd("420+")){if(4>b)break a}else if(3>b||3==b&&!Yc&&!Mm(this.I))break a;this.va||4!=b||7==c||(8==c||0>=d?this.b.da(3):this.b.da(2));Nm(this);var e=this.I.getStatus();this.Ua=e;var f=Mm(this.I);(this.ea=200==e)?(4==b&&Om(this),this.l?(Pm(this,b,f),Yc&&this.ea&&3==b&&(this.h.hb(this.f,"tick",this.Xd),this.f.start())):Qm(this,f),this.ea&&!this.va&&(4==b?this.b.jb(this):(this.ea=!1,Km(this)))):(this.Aa=
400==e&&0<f.indexOf("Unknown SID")?3:0,Y(),Om(this),Rm(this))}}catch(h){this.I&&Mm(this.I)}finally{}};
function Pm(a,b,c){for(var d=!0;!a.va&&a.Za<c.length;){var e=Sm(a,c);if(e==Gm){4==b&&(a.Aa=4,Y(),d=!1);break}else if(e==Fm){a.Aa=4;Y();d=!1;break}else Qm(a,e)}4==b&&0==c.length&&(a.Aa=1,Y(),d=!1);a.ea=a.ea&&d;d||(Om(a),Rm(a))}
g.Xd=function(){var a=Lm(this.I),b=Mm(this.I);this.Za<b.length&&(Nm(this),Pm(this,a,b),this.ea&&4!=a&&Km(this))};
function Sm(a,b){var c=a.Za,d=b.indexOf("\n",c);if(-1==d)return Gm;c=Number(b.substring(c,d));if(isNaN(c))return Fm;d+=1;if(d+c>b.length)return Gm;var e=b.substr(d,c);a.Za=d+c;return e}
function Tm(a,b){a.Xa=w();Km(a);var c=b?window.location.hostname:"";a.ta=a.qa.clone();N(a.ta,"DOMAIN",c);N(a.ta,"t",a.j);try{a.fa=new ActiveXObject("htmlfile")}catch(n){Om(a);a.Aa=7;Y();Rm(a);return}var d="<html><body>";if(b){for(var e="",f=0;f<c.length;f++){var h=c.charAt(f);if("<"==h)e+="\\x3c";else if(">"==h)e+="\\x3e";else{if(h in Ea)h=Ea[h];else if(h in Da)h=Ea[h]=Da[h];else{var k,m=h.charCodeAt(0);if(31<m&&127>m)k=h;else{if(256>m){if(k="\\x",16>m||256<m)k+="0"}else k="\\u",4096>m&&(k+="0");
k+=m.toString(16).toUpperCase()}h=Ea[h]=k}e+=h}}d+='<script>document.domain="'+e+'"\x3c/script>'}d+="</body></html>";c=Ec(sb("b/12014412"),d);a.fa.open();a.fa.write(Bb(c));a.fa.close();a.fa.parentWindow.m=v(a.Sd,a);a.fa.parentWindow.d=v(a.uc,a,!0);a.fa.parentWindow.rpcClose=v(a.uc,a,!1);c=a.fa.createElement("DIV");a.fa.parentWindow.document.body.appendChild(c);d=xb(a.ta.toString());d=vb(d);za.test(d)&&(-1!=d.indexOf("&")&&(d=d.replace(ta,"&amp;")),-1!=d.indexOf("<")&&(d=d.replace(ua,"&lt;")),-1!=
d.indexOf(">")&&(d=d.replace(va,"&gt;")),-1!=d.indexOf('"')&&(d=d.replace(wa,"&quot;")),-1!=d.indexOf("'")&&(d=d.replace(xa,"&#39;")),-1!=d.indexOf("\x00")&&(d=d.replace(ya,"&#0;")));d=Ec(sb("b/12014412"),'<iframe src="'+d+'"></iframe>');c.innerHTML=Bb(d);a.b.da(1)}
g.Sd=function(a){Um(v(this.Rd,this,a),0)};
g.Rd=function(a){this.va||(Nm(this),Qm(this,a),Km(this))};
g.uc=function(a){Um(v(this.Qd,this,a),0)};
g.Qd=function(a){this.va||(Om(this),this.ea=a,this.b.jb(this),this.b.da(4))};
g.cancel=function(){this.va=!0;Om(this)};
function Km(a){a.Rb=w()+a.i;Vm(a,a.i)}
function Vm(a,b){if(null!=a.Oa)throw Error("WatchDog timer not null");a.Oa=Um(v(a.Ud,a),b)}
function Nm(a){a.Oa&&(l.clearTimeout(a.Oa),a.Oa=null)}
g.Ud=function(){this.Oa=null;var a=w();0<=a-this.Rb?(2!=this.Na&&this.b.da(3),Om(this),this.Aa=2,Y(),Rm(this)):Vm(this,this.Rb-a)};
function Rm(a){a.b.hc()||a.va||a.b.jb(a)}
function Om(a){Nm(a);D(a.mb);a.mb=null;a.f.stop();xm(a.h);if(a.I){var b=a.I;a.I=null;Wm(b);b.dispose()}a.fa&&(a.fa=null)}
function Qm(a,b){try{a.b.oc(a,b),a.b.da(4)}catch(c){}}
;function Xm(a,b,c,d,e){if(0==d)c(!1);else{var f=e||0;d--;Ym(a,b,function(e){e?c(!0):l.setTimeout(function(){Xm(a,b,c,d,f)},f)})}}
function Ym(a,b,c){var d=new Image;d.onload=function(){try{Zm(d),c(!0)}catch(a){}};
d.onerror=function(){try{Zm(d),c(!1)}catch(a){}};
d.onabort=function(){try{Zm(d),c(!1)}catch(a){}};
d.ontimeout=function(){try{Zm(d),c(!1)}catch(a){}};
l.setTimeout(function(){if(d.ontimeout)d.ontimeout()},b);
d.src=a}
function Zm(a){a.onload=null;a.onerror=null;a.onabort=null;a.ontimeout=null}
;function $m(a){this.b=a;this.f=new Zl(null,!0)}
g=$m.prototype;g.Gb=null;g.U=null;g.nb=!1;g.Dc=null;g.cb=null;g.Lb=null;g.Hb=null;g.Y=null;g.oa=-1;g.Ta=null;g.Pa=null;g.connect=function(a){this.Hb=a;a=an(this.b,null,this.Hb);Y();this.Dc=w();var b=this.b.o;null!=b?(this.Ta=b[0],(this.Pa=b[1])?(this.Y=1,bn(this)):(this.Y=2,cn(this))):(xg(a,"MODE","init"),this.U=new Em(this,0,void 0,void 0,void 0),this.U.za=this.Gb,Jm(this.U,a,!1,null,!0),this.Y=0)};
function bn(a){var b=an(a.b,a.Pa,"/mail/images/cleardot.gif");zg(b);Xm(b.toString(),5E3,v(a.Zc,a),3,2E3);a.da(1)}
g.Zc=function(a){if(a)this.Y=2,cn(this);else{Y();var b=this.b;b.ca=b.ra.oa;dn(b,9)}a&&this.da(2)};
function cn(a){var b=a.b.C;if(null!=b)Y(),b?(Y(),en(a.b,a,!1)):(Y(),en(a.b,a,!0));else if(a.U=new Em(a,0,void 0,void 0,void 0),a.U.za=a.Gb,b=a.b,b=an(b,b.Ya()?a.Ta:null,a.Hb),Y(),!K||ld(10))xg(b,"TYPE","xmlhttp"),Jm(a.U,b,!1,a.Ta,!1);else{xg(b,"TYPE","html");var c=a.U;a=!!a.Ta;c.Na=3;c.qa=zg(b.clone());Tm(c,a)}}
g.Bb=function(a){return this.b.Bb(a)};
g.hc=function(){return!1};
g.oc=function(a,b){this.oa=a.Ua;if(0==this.Y)if(b){try{var c=this.f.parse(b)}catch(d){c=this.b;c.ca=this.oa;dn(c,2);return}this.Ta=c[0];this.Pa=c[1]}else c=this.b,c.ca=this.oa,dn(c,2);else if(2==this.Y)if(this.nb)Y(),this.Lb=w();else if("11111"==b){if(Y(),this.nb=!0,this.cb=w(),c=this.cb-this.Dc,!K||ld(10)||500>c)this.oa=200,this.U.cancel(),Y(),en(this.b,this,!0)}else Y(),this.cb=this.Lb=w(),this.nb=!1};
g.jb=function(){this.oa=this.U.Ua;if(this.U.ea)0==this.Y?this.Pa?(this.Y=1,bn(this)):(this.Y=2,cn(this)):2==this.Y&&((!K||ld(10)?!this.nb:200>this.Lb-this.cb)?(Y(),en(this.b,this,!1)):(Y(),en(this.b,this,!0)));else{0==this.Y?Y():2==this.Y&&Y();var a=this.b;a.ca=this.oa;dn(a,2)}};
g.Ya=function(){return this.b.Ya()};
g.isActive=function(){return this.b.isActive()};
g.da=function(a){this.b.da(a)};function fn(a){Wl.call(this);this.headers=new Kc;this.L=a||null;this.f=!1;this.K=this.b=null;this.ia=this.C="";this.i=0;this.l="";this.h=this.aa=this.o=this.W=!1;this.j=0;this.D=null;this.ja="";this.G=this.ka=!1}
x(fn,Wl);var gn=/^https?$/i,hn=["POST","PUT"];g=fn.prototype;
g.send=function(a,b,c,d){if(this.b)throw Error("[goog.net.XhrIo] Object is active with another request="+this.C+"; newUri="+a);b=b?b.toUpperCase():"GET";this.C=a;this.l="";this.i=0;this.ia=b;this.W=!1;this.f=!0;this.b=this.L?Dm(this.L):Dm(Bm);this.K=this.L?zm(this.L):zm(Bm);this.b.onreadystatechange=v(this.nc,this);try{sl(jn(this,"Opening Xhr")),this.aa=!0,this.b.open(b,String(a),!0),this.aa=!1}catch(f){sl(jn(this,"Error opening Xhr: "+f.message));kn(this,f);return}a=c||"";var e=this.headers.clone();
d&&Rc(d,function(a,b){e.set(b,a)});
d=Na(e.ha(),ln);c=l.FormData&&a instanceof l.FormData;!z(hn,b)||d||c||e.set("Content-Type","application/x-www-form-urlencoded;charset=utf-8");e.forEach(function(a,b){this.b.setRequestHeader(b,a)},this);
this.ja&&(this.b.responseType=this.ja);eb(this.b)&&(this.b.withCredentials=this.ka);try{mn(this),0<this.j&&(this.G=nn(this.b),sl(jn(this,"Will abort after "+this.j+"ms if incomplete, xhr2 "+this.G)),this.G?(this.b.timeout=this.j,this.b.ontimeout=v(this.gc,this)):this.D=sm(this.gc,this.j,this)),sl(jn(this,"Sending request")),this.o=!0,this.b.send(a),this.o=!1}catch(f){sl(jn(this,"Send error: "+f.message)),kn(this,f)}};
function nn(a){return K&&kd(9)&&fa(a.timeout)&&p(a.ontimeout)}
function ln(a){return"content-type"==a.toLowerCase()}
g.gc=function(){"undefined"!=typeof aa&&this.b&&(this.l="Timed out after "+this.j+"ms, aborting",this.i=8,jn(this,this.l),Xl(this,"timeout"),Wm(this,8))};
function kn(a,b){a.f=!1;a.b&&(a.h=!0,a.b.abort(),a.h=!1);a.l=b;a.i=5;on(a);pn(a)}
function on(a){a.W||(a.W=!0,Xl(a,"complete"),Xl(a,"error"))}
function Wm(a,b){a.b&&a.f&&(jn(a,"Aborting"),a.f=!1,a.h=!0,a.b.abort(),a.h=!1,a.i=b||7,Xl(a,"complete"),Xl(a,"abort"),pn(a))}
g.w=function(){this.b&&(this.f&&(this.f=!1,this.h=!0,this.b.abort(),this.h=!1),pn(this,!0));fn.A.w.call(this)};
g.nc=function(){this.isDisposed()||(this.aa||this.o||this.h?qn(this):this.Gd())};
g.Gd=function(){qn(this)};
function qn(a){if(a.f&&"undefined"!=typeof aa)if(a.K[1]&&4==Lm(a)&&2==a.getStatus())jn(a,"Local request error detected and ignored");else if(a.o&&4==Lm(a))sm(a.nc,0,a);else if(Xl(a,"readystatechange"),4==Lm(a)){jn(a,"Request complete");a.f=!1;try{var b=a.getStatus(),c;a:switch(b){case 200:case 201:case 202:case 204:case 206:case 304:case 1223:c=!0;break a;default:c=!1}var d;if(!(d=c)){var e;if(e=0===b){var f=String(a.C).match(wd)[1]||null;if(!f&&l.self&&l.self.location)var h=l.self.location.protocol,
f=h.substr(0,h.length-1);e=!gn.test(f?f.toLowerCase():"")}d=e}if(d)Xl(a,"complete"),Xl(a,"success");else{a.i=6;var k;try{k=2<Lm(a)?a.b.statusText:""}catch(m){k=""}a.l=k+" ["+a.getStatus()+"]";on(a)}}finally{pn(a)}}}
function pn(a,b){if(a.b){mn(a);var c=a.b,d=a.K[0]?t:null;a.b=null;a.K=null;b||Xl(a,"ready");try{c.onreadystatechange=d}catch(e){}}}
function mn(a){a.b&&a.G&&(a.b.ontimeout=null);fa(a.D)&&(l.clearTimeout(a.D),a.D=null)}
g.isActive=function(){return!!this.b};
function Lm(a){return a.b?a.b.readyState:0}
g.getStatus=function(){try{return 2<Lm(this)?this.b.status:-1}catch(a){return-1}};
function Mm(a){try{return a.b?a.b.responseText:""}catch(b){return""}}
function jn(a,b){return b+" ["+a.ia+" "+a.C+" "+a.getStatus()+"]"}
;function rn(a,b,c){this.j=a||null;this.b=1;this.f=[];this.h=[];this.i=new Zl(null,!0);this.o=b||null;this.C=null!=c?c:null}
function sn(a,b){this.b=a;this.map=b;this.context=null}
g=rn.prototype;g.Ra=null;g.R=null;g.F=null;g.Fb=null;g.eb=null;g.Yb=null;g.fb=null;g.Va=0;g.rd=0;g.M=null;g.sa=null;g.na=null;g.xa=null;g.ra=null;g.rb=null;g.Ia=-1;g.ic=-1;g.ca=-1;g.Sa=0;g.Ha=0;g.wa=8;var tn=new Wl;function un(a){vl.call(this,"statevent",a)}
x(un,vl);function vn(a,b){vl.call(this,"timingevent",a);this.size=b}
x(vn,vl);function wn(a){vl.call(this,"serverreachability",a)}
x(wn,vl);g=rn.prototype;g.connect=function(a,b,c,d,e){Y();this.Fb=b;this.Ra=c||{};d&&p(e)&&(this.Ra.OSID=d,this.Ra.OAID=e);this.ra=new $m(this);this.ra.Gb=null;this.ra.f=this.i;this.ra.connect(a)};
function xn(a){yn(a);if(3==a.b){var b=a.Va++,c=a.eb.clone();N(c,"SID",a.g);N(c,"RID",b);N(c,"TYPE","terminate");zn(a,c);b=new Em(a,0,a.g,b,void 0);b.Na=2;b.qa=zg(c.clone());(new Image).src=b.qa;b.Xa=w();Km(b)}An(a)}
function yn(a){if(a.ra){var b=a.ra;b.U&&(b.U.cancel(),b.U=null);b.oa=-1;a.ra=null}a.F&&(a.F.cancel(),a.F=null);a.na&&(l.clearTimeout(a.na),a.na=null);Bn(a);a.R&&(a.R.cancel(),a.R=null);a.sa&&(l.clearTimeout(a.sa),a.sa=null)}
function Cn(a,b){if(0==a.b)throw Error("Invalid operation: sending map when state is closed");a.f.push(new sn(a.rd++,b));2!=a.b&&3!=a.b||Dn(a)}
g.hc=function(){return 0==this.b};
function Dn(a){a.R||a.sa||(a.sa=Um(v(a.tc,a),0),a.Sa=0)}
g.tc=function(a){this.sa=null;En(this,a)};
function En(a,b){if(1==a.b){if(!b){a.Va=Math.floor(1E5*Math.random());var c=a.Va++,d=new Em(a,0,"",c,void 0);d.za=null;var e=Fn(a),f=a.eb.clone();N(f,"RID",c);a.j&&N(f,"CVER",a.j);zn(a,f);Hm(d,f,e);a.R=d;a.b=2}}else 3==a.b&&(b?Gn(a,b):0!=a.f.length&&(a.R||Gn(a)))}
function Gn(a,b){var c,d;b?6<a.wa?(a.f=a.h.concat(a.f),a.h.length=0,c=a.Va-1,d=Fn(a)):(c=b.o,d=b.Da):(c=a.Va++,d=Fn(a));var e=a.eb.clone();N(e,"SID",a.g);N(e,"RID",c);N(e,"AID",a.Ia);zn(a,e);c=new Em(a,0,a.g,c,a.Sa+1);c.za=null;c.setTimeout(Math.round(1E4)+Math.round(1E4*Math.random()));a.R=c;Hm(c,e,d)}
function zn(a,b){if(a.M){var c=a.M.ec(a);c&&Ya(c,function(a,c){N(b,c,a)})}}
function Fn(a){var b=Math.min(a.f.length,1E3),c=["count="+b],d;6<a.wa&&0<b?(d=a.f[0].b,c.push("ofs="+d)):d=0;for(var e=0;e<b;e++){var f=a.f[e].b,h=a.f[e].map,f=6>=a.wa?e:f-d;try{Rc(h,function(a,b){c.push("req"+f+"_"+b+"="+encodeURIComponent(a))})}catch(k){c.push("req"+f+"_type="+encodeURIComponent("_badmap"))}}a.h=a.h.concat(a.f.splice(0,b));
return c.join("&")}
function Hn(a){a.F||a.na||(a.l=1,a.na=Um(v(a.sc,a),0),a.Ha=0)}
function In(a){if(a.F||a.na||3<=a.Ha)return!1;a.l++;a.na=Um(v(a.sc,a),Jn(a,a.Ha));a.Ha++;return!0}
g.sc=function(){this.na=null;this.F=new Em(this,0,this.g,"rpc",this.l);this.F.za=null;this.F.Ib=0;var a=this.Yb.clone();N(a,"RID","rpc");N(a,"SID",this.g);N(a,"CI",this.rb?"0":"1");N(a,"AID",this.Ia);zn(this,a);if(!K||ld(10))N(a,"TYPE","xmlhttp"),Jm(this.F,a,!0,this.fb,!1);else{N(a,"TYPE","html");var b=this.F,c=!!this.fb;b.Na=3;b.qa=zg(a.clone());Tm(b,c)}};
function en(a,b,c){a.rb=c;a.ca=b.oa;a.bd(1,0);a.eb=an(a,null,a.Fb);Dn(a)}
g.oc=function(a,b){if(0!=this.b&&(this.F==a||this.R==a))if(this.ca=a.Ua,this.R==a&&3==this.b)if(7<this.wa){var c;try{c=this.i.parse(b)}catch(f){c=null}if(da(c)&&3==c.length)if(0==c[0])a:{if(!this.na){if(this.F)if(this.F.Xa+3E3<this.R.Xa)Bn(this),this.F.cancel(),this.F=null;else break a;In(this);Y()}}else this.ic=c[1],0<this.ic-this.Ia&&37500>c[2]&&this.rb&&0==this.Ha&&!this.xa&&(this.xa=Um(v(this.sd,this),6E3));else dn(this,11)}else"y2f%"!=b&&dn(this,11);else if(this.F==a&&Bn(this),!/^[\s\xa0]*$/.test(b)){c=
this.i.parse(b);da(c);for(var d=0;d<c.length;d++){var e=c[d];this.Ia=e[0];e=e[1];2==this.b?"c"==e[0]?(this.g=e[1],this.fb=e[2],e=e[3],null!=e?this.wa=e:this.wa=6,this.b=3,this.M&&this.M.cc(this),this.Yb=an(this,this.Ya()?this.fb:null,this.Fb),Hn(this)):"stop"==e[0]&&dn(this,7):3==this.b&&("stop"==e[0]?dn(this,7):"noop"!=e[0]&&this.M&&this.M.bc(this,e),this.Ha=0)}}};
g.sd=function(){null!=this.xa&&(this.xa=null,this.F.cancel(),this.F=null,In(this),Y())};
function Bn(a){null!=a.xa&&(l.clearTimeout(a.xa),a.xa=null)}
g.jb=function(a){var b;if(this.F==a)Bn(this),this.F=null,b=2;else if(this.R==a)this.R=null,b=1;else return;this.ca=a.Ua;if(0!=this.b)if(a.ea)1==b?(w(),Xl(tn,new vn(tn,a.Da?a.Da.length:0)),Dn(this),this.h.length=0):Hn(this);else{var c=a.Aa,d;if(!(d=3==c||7==c||0==c&&0<this.ca)){if(d=1==b)this.R||this.sa||1==this.b||2<=this.Sa?d=!1:(this.sa=Um(v(this.tc,this,a),Jn(this,this.Sa)),this.Sa++,d=!0);d=!(d||2==b&&In(this))}if(d)switch(c){case 1:dn(this,5);break;case 4:dn(this,10);break;case 3:dn(this,6);
break;case 7:dn(this,12);break;default:dn(this,2)}}};
function Jn(a,b){var c=5E3+Math.floor(1E4*Math.random());a.isActive()||(c*=2);return c*b}
g.bd=function(a){if(!z(arguments,this.b))throw Error("Unexpected channel state: "+this.b);};
function dn(a,b){if(2==b||9==b){var c=null;a.M&&(c=null);var d=v(a.ke,a);c||(c=new jg("//www.google.com/images/cleardot.gif"),zg(c));Ym(c.toString(),1E4,d)}else Y();Kn(a,b)}
g.ke=function(a){a?Y():(Y(),Kn(this,8))};
function Kn(a,b){a.b=0;a.M&&a.M.ac(a,b);An(a);yn(a)}
function An(a){a.b=0;a.ca=-1;if(a.M)if(0==a.h.length&&0==a.f.length)a.M.zb(a);else{var b=Ta(a.h),c=Ta(a.f);a.h.length=0;a.f.length=0;a.M.zb(a,b,c)}}
function an(a,b,c){var d=Ag(c);if(""!=d.f)b&&lg(d,b+"."+d.f),mg(d,d.j);else var e=window.location,d=Bg(e.protocol,b?b+"."+e.hostname:e.hostname,e.port,c);a.Ra&&Ya(a.Ra,function(a,b){N(d,b,a)});
N(d,"VER",a.wa);zn(a,d);return d}
g.Bb=function(a){if(a)throw Error("Can't create secondary domain capable XhrIo object.");a=new fn;a.ka=!1;return a};
g.isActive=function(){return!!this.M&&this.M.isActive(this)};
function Um(a,b){if(!ga(a))throw Error("Fn must not be null and must be a function");return l.setTimeout(function(){a()},b)}
g.da=function(){Xl(tn,new wn(tn))};
function Y(){Xl(tn,new un(tn))}
g.Ya=function(){return!(!K||ld(10))};
function Ln(){}
g=Ln.prototype;g.cc=function(){};
g.bc=function(){};
g.ac=function(){};
g.zb=function(){};
g.ec=function(){return{}};
g.isActive=function(){return!0};function Mn(a,b){qm.call(this);this.l=0;if(ga(a))b&&(a=v(a,b));else if(a&&ga(a.handleEvent))a=v(a.handleEvent,a);else throw Error("Invalid listener argument");this.o=a;Ll(this,"tick",v(this.j,this));Nn(this)}
x(Mn,qm);Mn.prototype.j=function(){if(500<this.b){var a=this.b;24E4>2*a&&(a*=2);rm(this,a)}this.o()};
Mn.prototype.start=function(){Mn.A.start.call(this);this.l=w()+this.b};
Mn.prototype.stop=function(){this.l=0;Mn.A.stop.call(this)};
function Nn(a){a.stop();rm(a,5E3+2E4*Math.random())}
;function On(a,b){this.D=a;this.l=b;this.g=new E;this.f=new Mn(this.qe,this);this.b=null;this.V=!1;this.i=null;this.C="";this.o=this.h=0;this.j=[]}
x(On,Ln);g=On.prototype;g.subscribe=function(a,b,c){return this.g.subscribe(a,b,c)};
g.unsubscribe=function(a,b,c){return this.g.unsubscribe(a,b,c)};
g.ga=function(a){return this.g.ga(a)};
g.u=function(a,b){return this.g.u.apply(this.g,arguments)};
g.dispose=function(){this.V||(this.V=!0,this.g.clear(),Pn(this),D(this.g))};
g.isDisposed=function(){return this.V};
function Qn(a){return{firstTestResults:[""],secondTestResults:!a.b.rb,sessionId:a.b.g,arrayId:a.b.Ia}}
g.connect=function(a,b,c){if(!this.b||2!=this.b.b){this.C="";this.f.stop();this.i=a||null;this.h=b||0;a=this.D+"/test";b=this.D+"/bind";var d=new rn("1",c?c.firstTestResults:null,c?c.secondTestResults:null),e=this.b;e&&(e.M=null);d.M=this;this.b=d;e?this.b.connect(a,b,this.l,e.g,e.Ia):c?this.b.connect(a,b,this.l,c.sessionId,c.arrayId):this.b.connect(a,b,this.l)}};
function Pn(a,b){a.o=b||0;a.f.stop();a.b&&(3==a.b.b&&En(a.b),xn(a.b));a.o=0}
g.sendMessage=function(a,b){var c={_sc:a};b&&mb(c,b);this.f.enabled||2==(this.b?this.b.b:0)?this.j.push(c):Rn(this)&&Cn(this.b,c)};
g.cc=function(){Nn(this.f);this.i=null;this.h=0;if(this.j.length){var a=this.j;this.j=[];for(var b=0,c=a.length;b<c;++b)Cn(this.b,a[b])}this.u("handlerOpened")};
g.ac=function(a,b){var c=2==b&&401==this.b.ca;if(4!=b&&!c){if(6==b||410==this.b.ca)c=this.f,c.stop(),rm(c,500);this.f.start()}this.u("handlerError",b)};
g.zb=function(a,b,c){if(!this.f.enabled)this.u("handlerClosed");else if(c)for(a=0,b=c.length;a<b;++a){var d=c[a].map;d&&this.j.push(d)}};
g.ec=function(){var a={v:2};this.C&&(a.gsessionid=this.C);0!=this.h&&(a.ui=""+this.h);0!=this.o&&(a.ui=""+this.o);this.i&&mb(a,this.i);return a};
g.bc=function(a,b){"S"==b[0]?this.C=b[1]:"gracefulReconnect"==b[0]?(Nn(this.f),this.f.start(),xn(this.b)):this.u("handlerMessage",new tl(b[0],b[1]))};
function Rn(a){return!!a.b&&3==a.b.b}
function Sn(a,b){(a.l.loungeIdToken=b)||a.f.stop()}
g.qe=function(){this.f.stop();var a=this.b,b=0;a.F&&b++;a.R&&b++;0!=b?this.f.start():this.connect(this.i,this.h)};function Tn(a){this.index=-1;this.videoId=this.f="";this.volume=this.b=-1;this.l=!1;this.audioTrackId=null;this.i=this.g=0;this.h=null;this.reset(a)}
function Un(a){a.audioTrackId=null;a.h=null;a.b=-1;a.g=0;a.i=w()}
Tn.prototype.reset=function(a){this.f="";this.index=-1;this.videoId="";Un(this);this.volume=-1;this.l=!1;a&&(this.index=a.index,this.f=a.listId,this.videoId=a.videoId,this.b=a.playerState,this.volume=a.volume,this.l=a.muted,this.audioTrackId=a.audioTrackId,this.h=a.trackData,this.g=a.playerTime,this.i=a.playerTimeAt)};
function Vn(a){switch(a.b){case 1:return(w()-a.i)/1E3+a.g;case -1E3:return 0}return a.g}
function Wn(a){var b={};b.index=a.index;b.listId=a.f;b.videoId=a.videoId;b.playerState=a.b;b.volume=a.volume;b.muted=a.l;b.audioTrackId=a.audioTrackId;b.trackData=kb(a.h);b.playerTime=a.g;b.playerTimeAt=a.i;return b}
Tn.prototype.clone=function(){return new Tn(Wn(this))};function Z(a,b,c){V.call(this);this.h=NaN;this.K=!1;this.C=this.o=this.D=this.G=NaN;this.L=[];this.g=this.B=this.b=null;this.pa=a;this.L.push(M(window,"beforeunload",v(this.hd,this)));this.f=[];this.B=new Tn;this.aa=b.id;this.b=Xn(this,c);this.b.subscribe("handlerOpened",this.wd,this);this.b.subscribe("handlerClosed",this.td,this);this.b.subscribe("handlerError",this.ud,this);this.b.subscribe("handlerMessage",this.vd,this);Sn(this.b,b.token);this.subscribe("remoteQueueChange",function(){var a=this.B.videoId;
hk()&&T("yt-remote-session-video-id",a)},this)}
x(Z,V);g=Z.prototype;g.connect=function(a,b){if(b){var c=b.listId,d=b.videoId,e=b.index,f=b.currentTime||0;5>=f&&(f=0);var h={videoId:d,currentTime:f};c&&(h.listId=c);p(e)&&(h.currentIndex=e);c&&(this.B.f=c);this.B.videoId=d;this.B.index=e||0;this.B.state=3;c=this.B;c.g=f;c.i=w();Yn("Connecting with setPlaylist and params: "+L(h));this.b.connect({method:"setPlaylist",params:L(h)},a,lk())}else Yn("Connecting without params"),this.b.connect({},a,lk());Zn(this)};
g.dispose=function(){this.isDisposed()||(this.u("beforeDispose"),$n(this,3));Z.A.dispose.call(this)};
g.w=function(){ao(this);bo(this);co(this);I(this.o);this.o=NaN;I(this.C);this.C=NaN;this.g=null;Je(this.L);this.L.length=0;this.b.dispose();Z.A.w.call(this);this.f=this.B=this.b=null};
function Yn(a){Dj("conn",a)}
g.hd=function(){this.i(2)};
function Xn(a,b){return new On(Pj(a.pa,"/bc",void 0,!1),b)}
function $n(a,b){a.u("proxyStateChange",b)}
function Zn(a){a.h=G(v(function(){Yn("Connecting timeout");this.i(1)},a),2E4)}
function ao(a){I(a.h);a.h=NaN}
function co(a){I(a.G);a.G=NaN}
function eo(a){bo(a);a.D=G(v(function(){fo(this,"getNowPlaying")},a),2E4)}
function bo(a){I(a.D);a.D=NaN}
g.wd=function(){Yn("Channel opened");this.K&&(this.K=!1,co(this),this.G=G(v(function(){Yn("Timing out waiting for a screen.");this.i(1)},this),15E3));
pk(Qn(this.b),this.aa)};
g.td=function(){Yn("Channel closed");isNaN(this.h)?qk(!0):qk();this.dispose()};
g.ud=function(a){qk();isNaN(this.j())?(Yn("Channel error: "+a+" without reconnection"),this.dispose()):(this.K=!0,Yn("Channel error: "+a+" with reconnection in "+this.j()+" ms"),$n(this,2))};
function go(a,b){b&&(ao(a),co(a));b==(Rn(a.b)&&isNaN(a.h))?b&&($n(a,1),fo(a,"getSubtitlesTrack")):b?(a.W()&&a.B.reset(),$n(a,1),fo(a,"getNowPlaying"),ho(a)):a.i(1)}
function io(a,b){var c=b.params.videoId;delete b.params.videoId;c==a.B.videoId&&(hb(b.params)?a.B.h=null:a.B.h=b.params,a.u("remotePlayerChange"))}
function jo(a,b){var c=b.params.videoId||b.params.video_id,d=parseInt(b.params.currentIndex,10);a.B.f=b.params.listId||a.B.f;var e=a.B,f=e.videoId;e.videoId=c;e.index=d;c!=f&&Un(e);a.u("remoteQueueChange")}
function ko(a,b){b.params=b.params||{};jo(a,b);lo(a,b)}
function lo(a,b){var c=parseInt(b.params.currentTime||b.params.current_time,10),d=a.B;d.g=isNaN(c)?0:c;d.i=w();c=parseInt(b.params.state,10);c=isNaN(c)?-1:c;-1==c&&-1E3==a.B.b&&(c=-1E3);a.B.b=c;1==a.B.b?eo(a):bo(a);a.u("remotePlayerChange")}
function mo(a,b){var c="true"==b.params.muted;a.B.volume=parseInt(b.params.volume,10);a.B.l=c;a.u("remotePlayerChange")}
g.vd=function(a){a.params?Yn("Received: action="+a.action+", params="+L(a.params)):Yn("Received: action="+a.action+" {}");switch(a.action){case "loungeStatus":a=od(a.params.devices);this.f=Ka(a,function(a){return new ck(a)});
a=!!Na(this.f,function(a){return"LOUNGE_SCREEN"==a.type});
go(this,a);break;case "loungeScreenConnected":go(this,!0);break;case "loungeScreenDisconnected":Ra(this.f,function(a){return"LOUNGE_SCREEN"==a.type});
go(this,!1);break;case "remoteConnected":var b=new ck(od(a.params.device));Na(this.f,function(a){return a.equals(b)})||Pa(this.f,b);
break;case "remoteDisconnected":b=new ck(od(a.params.device));Ra(this.f,function(a){return a.equals(b)});
break;case "gracefulDisconnect":break;case "playlistModified":jo(this,a);break;case "nowPlaying":ko(this,a);break;case "onStateChange":lo(this,a);break;case "onVolumeChanged":mo(this,a);break;case "onSubtitlesTrackChanged":io(this,a);break;default:Yn("Unrecognized action: "+a.action)}};
g.ae=function(){if(this.g){var a=this.g;this.g=null;this.B.videoId!=a&&fo(this,"getNowPlaying")}};
Z.prototype.subscribe=Z.prototype.subscribe;Z.prototype.unsubscribeByKey=Z.prototype.ga;Z.prototype.ia=function(){var a=3;this.isDisposed()||(a=0,isNaN(this.j())?Rn(this.b)&&isNaN(this.h)&&(a=1):a=2);return a};
Z.prototype.getProxyState=Z.prototype.ia;Z.prototype.i=function(a){Yn("Disconnecting with "+a);ao(this);this.u("beforeDisconnect",a);1==a&&qk();Pn(this.b,a);this.dispose()};
Z.prototype.disconnect=Z.prototype.i;Z.prototype.ba=function(){var a=this.B;if(this.g){var b=a=this.B.clone(),c=this.g,d=a.index,e=b.videoId;b.videoId=c;b.index=d;c!=e&&Un(b)}return Wn(a)};
Z.prototype.getPlayerContextData=Z.prototype.ba;Z.prototype.ka=function(a){var b=new Tn(a);b.videoId&&b.videoId!=this.B.videoId&&(this.g=b.videoId,I(this.o),this.o=G(v(this.ae,this),5E3));var c=[];this.B.f==b.f&&this.B.videoId==b.videoId&&this.B.index==b.index||c.push("remoteQueueChange");this.B.b==b.b&&this.B.volume==b.volume&&this.B.l==b.l&&Vn(this.B)==Vn(b)&&L(this.B.h)==L(b.h)||c.push("remotePlayerChange");this.B.reset(a);y(c,function(a){this.u(a)},this)};
Z.prototype.setPlayerContextData=Z.prototype.ka;Z.prototype.W=function(){var a=this.b.l.id,b=Na(this.f,function(b){return"REMOTE_CONTROL"==b.type&&b.id!=a});
return b?b.id:""};
Z.prototype.getOtherConnectedRemoteId=Z.prototype.W;Z.prototype.j=function(){var a=this.b;return a.f.enabled?a.f.l-w():NaN};
Z.prototype.getReconnectTimeout=Z.prototype.j;Z.prototype.Fa=function(){if(!isNaN(this.j())){var a=this.b.f;a.enabled&&(a.stop(),a.start(),a.j())}};
Z.prototype.reconnect=Z.prototype.Fa;function ho(a){I(a.C);a.C=G(v(a.i,a,1),864E5)}
function fo(a,b,c){c?Yn("Sending: action="+b+", params="+L(c)):Yn("Sending: action="+b);a.b.sendMessage(b,c)}
Z.prototype.ja=function(a,b){fo(this,a,b);ho(this)};
Z.prototype.sendMessage=Z.prototype.ja;function no(a){Wj.call(this,"ScreenServiceProxy");this.P=a;this.b=[];this.b.push(this.P.$_s("screenChange",v(this.ue,this)));this.b.push(this.P.$_s("onlineScreenChange",v(this.Cd,this)))}
x(no,Wj);g=no.prototype;g.S=function(a){return this.P.$_gs(a)};
g.contains=function(a){return!!this.P.$_c(a)};
g.get=function(a){return this.P.$_g(a)};
g.start=function(){this.P.$_st()};
g.tb=function(a,b,c){this.P.$_a(a,b,c)};
g.remove=function(a,b,c){this.P.$_r(a,b,c)};
g.qb=function(a,b,c,d){this.P.$_un(a,b,c,d)};
g.w=function(){for(var a=0,b=this.b.length;a<b;++a)this.P.$_ubk(this.b[a]);this.b.length=0;this.P=null;no.A.w.call(this)};
g.ue=function(){this.u("screenChange")};
g.Cd=function(){this.u("onlineScreenChange")};
W.prototype.$_st=W.prototype.start;W.prototype.$_gspc=W.prototype.ve;W.prototype.$_gsppc=W.prototype.Hc;W.prototype.$_c=W.prototype.contains;W.prototype.$_g=W.prototype.get;W.prototype.$_a=W.prototype.tb;W.prototype.$_un=W.prototype.qb;W.prototype.$_r=W.prototype.remove;W.prototype.$_gs=W.prototype.S;W.prototype.$_gos=W.prototype.Gc;W.prototype.$_s=W.prototype.subscribe;W.prototype.$_ubk=W.prototype.ga;function oo(){var a={device:"Desktop",app:"youtube-desktop"};aj&&$i();ek();po||(po=new Oj,rk()&&(po.b="/api/loungedev"));qo||(qo=r("yt.mdx.remote.deferredProxies_")||[],q("yt.mdx.remote.deferredProxies_",qo,void 0));ro();var b=so();if(!b){var c=new W(po);q("yt.mdx.remote.screenService_",c,void 0);b=so();Zk(c,function(a){a?to()&&rl(to(),"YouTube TV"):c.subscribe("onlineScreenChange",function(){J("yt-remote-receiver-availability-change")})},!(!a||!a.loadCastApiSetupScript))}if(a&&!r("yt.mdx.remote.initialized_")){q("yt.mdx.remote.initialized_",
!0,void 0);
uo("Initializing: "+L(a));vo.push(mc("yt-remote-cast2-availability-change",function(){J("yt-remote-receiver-availability-change")}));
vo.push(mc("yt-remote-cast2-receiver-selected",function(){wo(null);J("yt-remote-auto-connect","cast-selector-receiver")}));
vo.push(mc("yt-remote-cast2-session-change",xo));vo.push(mc("yt-remote-connection-change",function(a){a?rl(to(),"YouTube TV"):yo()||(rl(null,null),nl())}));
var d=zo();a.isAuto&&(d.id+="#dial");d.name=a.device;d.app=a.app;uo(" -- with channel params: "+L(d));Ao(d);b.start();to()||Bo()}}
function Co(){oc(vo);vo.length=0;D(Do);Do=null;qo&&(y(qo,function(a){a(null)}),qo.length=0,qo=null,q("yt.mdx.remote.deferredProxies_",null,void 0));
po=null}
function Eo(){if(ll()){var a=[];if(U("yt-remote-cast-available")||r("yt.mdx.remote.cloudview.castButtonShown_")||Fo())a.push({key:"cast-selector-receiver",name:Go()}),q("yt.mdx.remote.cloudview.castButtonShown_",!0,void 0);return a}return r("yt.mdx.remote.cloudview.initializing_")?[]:Ho()}
function Ho(){var a;a=so().P.$_gos();var b=Io();b&&Fo()&&(Uj(a,b)||a.push(b));return Tj(a)}
function Jo(){if(ll()){var a=ml();return a?{key:"cast-selector-receiver",name:a}:null}return Ko()}
function Ko(){var a=Ho(),b=Io();b||(b=yo());return Na(a,function(a){return b&&Hj(b,a.key)?!0:!1})}
function Go(){if(ll())return ml();var a=Io();return a?a.name:null}
function Io(){var a=to();if(!a)return null;var b=so().S();return Vj(b,a)}
function xo(a){uo("remote.onCastSessionChange_: "+Lj(a));if(a){var b=Io();b&&b.id==a.id?rl(b.id,"YouTube TV"):(b&&Lo(),Mo(a,1))}else Lo()}
function No(a,b){uo("Connecting to: "+L(a));if("cast-selector-receiver"==a.key)wo(b||null),ql(b||null);else{Lo();wo(b||null);var c=so().S();(c=Vj(c,a.key))?Mo(c,1):G(function(){Oo(null)},0)}}
function Lo(){pl()?hl().stopSession():el("stopSession called before API ready.");var a=Fo();a?a.disconnect(1):(pc("yt-remote-before-disconnect",1),pc("yt-remote-connection-change",!1));Oo(null)}
function uo(a){Dj("remote",a)}
function so(){if(!Do){var a=r("yt.mdx.remote.screenService_");Do=a?new no(a):null}return Do}
function to(){return r("yt.mdx.remote.currentScreenId_")}
function Po(a){q("yt.mdx.remote.currentScreenId_",a,void 0)}
function wo(a){q("yt.mdx.remote.connectData_",a,void 0)}
function Fo(){return r("yt.mdx.remote.connection_")}
function Oo(a){var b=Fo();wo(null);a?Fo():Po("");q("yt.mdx.remote.connection_",a,void 0);qo&&(y(qo,function(b){b(a)}),qo.length=0);
b&&!a?pc("yt-remote-connection-change",!1):!b&&a&&J("yt-remote-connection-change",!0)}
function yo(){var a=hk();if(!a)return null;var b=so().S();return Vj(b,a)}
function Mo(a,b){to();Po(a.id);var c=new Z(po,a,zo());c.connect(b,r("yt.mdx.remote.connectData_"));c.subscribe("beforeDisconnect",function(a){pc("yt-remote-before-disconnect",a)});
c.subscribe("beforeDispose",function(){Fo()&&(Fo(),Oo(null))});
Oo(c)}
function Bo(){var a=yo();a?(uo("Resume connection to: "+Lj(a)),Mo(a,0)):(qk(),nl(),uo("Skipping connecting because no session screen found."))}
var po=null,qo=null,Do=null;function ro(){var a=zo();if(hb(a)){var a=gk(),b=U("yt-remote-session-name")||"",c=U("yt-remote-session-app")||"",a={device:"REMOTE_CONTROL",id:a,name:b,app:c,"mdx-version":3};q("yt.mdx.remote.channelParams_",a,void 0)}}
function zo(){return r("yt.mdx.remote.channelParams_")||{}}
function Ao(a){a?(T("yt-remote-session-app",a.app),T("yt-remote-session-name",a.name)):(cj("yt-remote-session-app"),cj("yt-remote-session-name"));q("yt.mdx.remote.channelParams_",a,void 0)}
var vo=[];var Qo=null,Ro=[];function So(){To();if(Jo()){var a=Qo;"html5"!=a.getPlayerType()&&a.loadNewVideoConfig(a.getCurrentVideoConfig(),"html5")}}
function Uo(a){"cast-selector-receiver"==a?ol():Vo(a)}
function Vo(a){var b=Eo();if(a=Sj(b,a)){var c=Qo;No(a,{listId:c.getVideoData().list,videoId:c.getVideoData().video_id,currentTime:c.getCurrentTime()});"html5"!=c.getPlayerType()?c.loadNewVideoConfig(c.getCurrentVideoConfig(),"html5"):c.updateRemoteReceivers&&c.updateRemoteReceivers(b,a)}}
function To(){var a=Qo;a&&a.updateRemoteReceivers&&a.updateRemoteReceivers(Eo(),Jo())}
;var Wo=null,Xo=[];function Yo(a){return{externalChannelId:a.externalChannelId,qd:!!a.isChannelPaid,source:a.source,subscriptionId:a.subscriptionId}}
function Zo(a){$o(Yo(a))}
function $o(a){mi()?(Q($h,new Uh(a.externalChannelId,a.qd?{itemType:"U",itemId:a.externalChannelId}:null)),(a="/gen_204?"+Dd({event:"subscribe",source:a.source}))&&Mg(a)):ap(a)}
function ap(a){li(function(b){b.subscription_ajax&&$o(a)},null)}
function bp(a){a=Yo(a);Q(ei,new Wh(a.externalChannelId,a.subscriptionId,null));(a="/gen_204?"+Dd({event:"unsubscribe",source:a.source}))&&Mg(a)}
function cp(a){Wo&&Wo.channelSubscribed(a.b,a.subscriptionId)}
function dp(a){Wo&&Wo.channelUnsubscribed(a.b)}
;function ep(a){C.call(this);this.f=a;this.f.subscribe("command",this.yc,this);this.g={};this.h=!1}
x(ep,C);g=ep.prototype;g.start=function(){this.h||this.isDisposed()||(this.h=!0,fp(this.f,"RECEIVING"))};
g.yc=function(a,b){if(this.h&&!this.isDisposed()){var c=b||{};switch(a){case "addEventListener":if(u(c.event)&&(c=c.event,!(c in this.g))){var d=v(this.ce,this,c);this.g[c]=d;this.addEventListener(c,d)}break;case "removeEventListener":u(c.event)&&gp(this,c.event);break;default:this.b.isReady()&&this.b[a]&&(c=hp(a,b||{}),c=this.b[a].apply(this.b,c),(c=ip(a,c))&&this.h&&!this.isDisposed()&&fp(this.f,a,c))}}};
g.ce=function(a,b){this.h&&!this.isDisposed()&&fp(this.f,a,this.Cb(a,b))};
g.Cb=function(a,b){if(null!=b)return{value:b}};
function gp(a,b){b in a.g&&(a.removeEventListener(b,a.g[b]),delete a.g[b])}
g.w=function(){this.f.unsubscribe("command",this.yc,this);this.f=null;for(var a in this.g)gp(this,a);ep.A.w.call(this)};function jp(a,b){ep.call(this,b);this.b=a;this.start()}
x(jp,ep);jp.prototype.addEventListener=function(a,b){this.b.addEventListener(a,b)};
jp.prototype.removeEventListener=function(a,b){this.b.removeEventListener(a,b)};
function hp(a,b){switch(a){case "loadVideoById":return b=hj(b),jj(b),[b];case "cueVideoById":return b=hj(b),jj(b),[b];case "loadVideoByPlayerVars":return jj(b),[b];case "cueVideoByPlayerVars":return jj(b),[b];case "loadPlaylist":return b=ij(b),jj(b),[b];case "cuePlaylist":return b=ij(b),jj(b),[b];case "seekTo":return[b.seconds,b.allowSeekAhead];case "playVideoAt":return[b.index];case "setVolume":return[b.volume];case "setPlaybackQuality":return[b.suggestedQuality];case "setPlaybackRate":return[b.suggestedRate];
case "setLoop":return[b.loopPlaylists];case "setShuffle":return[b.shufflePlaylist];case "getOptions":return[b.module];case "getOption":return[b.module,b.option];case "setOption":return[b.module,b.option,b.value];case "handleGlobalKeyDown":return[b.keyCode,b.shiftKey]}return[]}
function ip(a,b){switch(a){case "isMuted":return{muted:b};case "getVolume":return{volume:b};case "getPlaybackRate":return{playbackRate:b};case "getAvailablePlaybackRates":return{availablePlaybackRates:b};case "getVideoLoadedFraction":return{videoLoadedFraction:b};case "getPlayerState":return{playerState:b};case "getCurrentTime":return{currentTime:b};case "getPlaybackQuality":return{playbackQuality:b};case "getAvailableQualityLevels":return{availableQualityLevels:b};case "getDuration":return{duration:b};
case "getVideoUrl":return{videoUrl:b};case "getVideoEmbedCode":return{videoEmbedCode:b};case "getPlaylist":return{playlist:b};case "getPlaylistIndex":return{playlistIndex:b};case "getOptions":return{options:b};case "getOption":return{option:b}}}
jp.prototype.Cb=function(a,b){switch(a){case "onReady":return;case "onStateChange":return{playerState:b};case "onPlaybackQualityChange":return{playbackQuality:b};case "onPlaybackRateChange":return{playbackRate:b};case "onError":return{errorCode:b}}return jp.A.Cb.call(this,a,b)};
jp.prototype.w=function(){jp.A.w.call(this);delete this.b};function kp(){var a=this.f=new Li,b=v(this.Zd,this);a.f=b;a.g=null;this.f.channel="widget";if(a=F("WIDGET_ID"))this.f.h=a;this.h=[];this.l=!1;this.i={}}
g=kp.prototype;g.Zd=function(a,b){if("addEventListener"==a&&b){var c=b[0];this.i[c]||"onReady"==c||(this.addEventListener(c,lp(this,c)),this.i[c]=!0)}else this.Kc(a,b)};
g.Kc=function(){};
function lp(a,b){return v(function(a){this.sendMessage(b,a)},a)}
g.addEventListener=function(){};
g.dd=function(){this.l=!0;this.sendMessage("initialDelivery",this.Db());this.sendMessage("onReady");y(this.h,this.Lc,this);this.h=[]};
g.Db=function(){return null};
function mp(a,b){a.sendMessage("infoDelivery",b)}
g.Lc=function(a){this.l?this.f.sendMessage(a):this.h.push(a)};
g.sendMessage=function(a,b){this.Lc({event:a,info:void 0==b?null:b})};
g.dispose=function(){this.f=null};function np(a){kp.call(this);this.b=a;this.g=[];this.addEventListener("onReady",v(this.Hd,this));this.addEventListener("onVideoProgress",v(this.ge,this));this.addEventListener("onVolumeChange",v(this.he,this));this.addEventListener("onApiChange",v(this.be,this));this.addEventListener("onPlaybackQualityChange",v(this.de,this));this.addEventListener("onPlaybackRateChange",v(this.ee,this));this.addEventListener("onStateChange",v(this.fe,this))}
x(np,kp);g=np.prototype;g.Kc=function(a,b){if(this.b[a]){b=b||[];if(0<b.length&&fj(a)){var c;c=b;if(ha(c[0])&&!da(c[0]))c=c[0];else{var d={};switch(a){case "loadVideoById":case "cueVideoById":d=hj.apply(window,c);break;case "loadVideoByUrl":case "cueVideoByUrl":d=gj.apply(window,c);break;case "loadPlaylist":case "cuePlaylist":d=ij.apply(window,c)}c=d}jj(c);b.length=1;b[0]=c}this.b[a].apply(this.b,b);fj(a)&&mp(this,this.Db())}};
g.Hd=function(){var a=v(this.dd,this);this.f.b=a};
g.addEventListener=function(a,b){this.g.push({eventType:a,listener:b});this.b.addEventListener(a,b)};
g.Db=function(){if(!this.b)return null;var a=this.b.getApiInterface();Qa(a,"getVideoData");for(var b={apiInterface:a},c=0,d=a.length;c<d;c++){var e=a[c],f=e;if(0==f.search("get")||0==f.search("is")){var f=e,h=0;0==f.search("get")?h=3:0==f.search("is")&&(h=2);f=f.charAt(h).toLowerCase()+f.substr(h+1);try{var k=this.b[e]();b[f]=k}catch(m){}}}b.videoData=this.b.getVideoData();return b};
g.fe=function(a){a={playerState:a,currentTime:this.b.getCurrentTime(),duration:this.b.getDuration(),videoData:this.b.getVideoData(),videoStartBytes:0,videoBytesTotal:this.b.getVideoBytesTotal(),videoLoadedFraction:this.b.getVideoLoadedFraction(),playbackQuality:this.b.getPlaybackQuality(),availableQualityLevels:this.b.getAvailableQualityLevels(),videoUrl:this.b.getVideoUrl(),playlist:this.b.getPlaylist(),playlistIndex:this.b.getPlaylistIndex()};this.b.getProgressState&&(a.progressState=this.b.getProgressState());
this.b.getStoryboardFormat&&(a.storyboardFormat=this.b.getStoryboardFormat());mp(this,a)};
g.de=function(a){mp(this,{playbackQuality:a})};
g.ee=function(a){mp(this,{playbackRate:a})};
g.be=function(){for(var a=this.b.getOptions(),b={namespaces:a},c=0,d=a.length;c<d;c++){var e=a[c],f=this.b.getOptions(e);b[e]={options:f};for(var h=0,k=f.length;h<k;h++){var m=f[h],n=this.b.getOption(e,m);b[e][m]=n}}this.sendMessage("apiInfoDelivery",b)};
g.he=function(){mp(this,{muted:this.b.isMuted(),volume:this.b.getVolume()})};
g.ge=function(a){a={currentTime:a,videoBytesLoaded:this.b.getVideoBytesLoaded(),videoLoadedFraction:this.b.getVideoLoadedFraction()};this.b.getProgressState&&(a.progressState=this.b.getProgressState());mp(this,a)};
g.dispose=function(){np.A.dispose.call(this);for(var a=0;a<this.g.length;a++){var b=this.g[a];this.b.removeEventListener(b.eventType,b.listener)}this.g=[]};function op(a,b,c){V.call(this);this.b=a;this.f=b;this.g=c}
x(op,V);function fp(a,b,c){if(!a.isDisposed()){var d=a.b,e=a.f;a=a.g;d.isDisposed()||e!=d.b||(b={id:a,command:b},c&&(b.data=c),d.b.postMessage(L(b),d.g))}}
op.prototype.w=function(){this.f=this.b=null;op.A.w.call(this)};function pp(a,b,c){C.call(this);this.b=a;this.g=c;this.h=M(window,"message",v(this.i,this));this.f=new op(this,a,b);Wb(this,oa(D,this.f))}
x(pp,C);pp.prototype.i=function(a){var b;if(b=!this.isDisposed())if(b=a.origin==this.g)a:{b=this.b;do{var c;b:{c=a.source;do{if(c==b){c=!0;break b}if(c==c.parent)break;c=c.parent}while(null!=c);c=!1}if(c){b=!0;break a}b=b.opener}while(null!=b);b=!1}if(b&&(c=a.data,u(c))){try{c=od(c)}catch(d){return}c.command&&(a=this.f,b=c.command,c=c.data,a.isDisposed()||a.u("command",b,c))}};
pp.prototype.w=function(){Je(this.h);this.b=null;pp.A.w.call(this)};var qp=!1;function rp(a){if(a=a.match(/[\d]+/g))a.length=3}
(function(){if(navigator.plugins&&navigator.plugins.length){var a=navigator.plugins["Shockwave Flash"];if(a&&(qp=!0,a.description)){rp(a.description);return}if(navigator.plugins["Shockwave Flash 2.0"]){qp=!0;return}}if(navigator.mimeTypes&&navigator.mimeTypes.length&&(a=navigator.mimeTypes["application/x-shockwave-flash"],qp=!!a&&a.enabledPlugin)){rp(a.enabledPlugin.description);return}try{var b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");qp=!0;rp(b.GetVariable("$version"));return}catch(c){}try{b=
new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");qp=!0;return}catch(c){}try{b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash"),qp=!0,rp(b.GetVariable("$version"))}catch(c){}})();function sp(a){return(a=a.exec(nb))?a[1]:""}
(function(){if(mf)return sp(/Firefox\/([0-9.]+)/);if(K||Zc||Yc)return id;if(qf)return sp(/Chrome\/([0-9.]+)/);if(rf&&!(Xc()||A("iPad")||A("iPod")))return sp(/Version\/([0-9.]+)/);if(nf||of){var a=/Version\/(\S+).*Mobile\/(\S+)/.exec(nb);if(a)return a[1]+"."+a[2]}else if(pf)return(a=sp(/Android\s+([0-9.]+)/))?a:sp(/Version\/([0-9.]+)/);return""})();function tp(){var a={format:"RAW",method:"GET",timeout:5E3,withCredentials:!0};return new $l(function(b,c){a.Z=function(a){Kd(a)?b(a):c(a)};
a.onError=c;a.Ca=c;Pd("//googleads.g.doubleclick.net/pagead/id",a)})}
;var up=null;function vp(a){a=a.responseText;if(0!=a.lastIndexOf(")]}'",0))return wp(""),"";a=JSON.parse(a.substr(4)).id;wp(a);return a}
function xp(){G(function(){up=null},3E5)}
function wp(a){q("yt.www.ads.biscotti.lastId_",a,void 0)}
;function yp(){}
;function zp(a){for(var b=0;b<a.length;b++){var c=a[b];"send_follow_on_ping_action"==c.name&&c.data&&c.data.follow_on_url&&(c=c.data.follow_on_url)&&Mg(c)}}
;function Ap(a){O.call(this,1,arguments);this.yb=a}
x(Ap,O);function Bp(a,b){O.call(this,2,arguments);this.f=a;this.b=b}
x(Bp,O);function Cp(a,b,c,d){O.call(this,1,arguments);this.b=b;this.g=c||null;this.f=d||null}
x(Cp,O);function Dp(a,b){O.call(this,1,arguments);this.f=a;this.b=b||null}
x(Dp,O);function Ep(a){O.call(this,1,arguments)}
x(Ep,O);var Fp=new P("ypc-core-load",Ap),Gp=new P("ypc-guide-sync-success",Bp),Hp=new P("ypc-purchase-success",Cp),Ip=new P("ypc-subscription-cancel",Ep),Jp=new P("ypc-subscription-cancel-success",Dp),Kp=new P("ypc-init-subscription",Ep);var Lp=!1,Mp=[],Np=[];function Op(a){a.b?Lp?Q(di,a):Q(Fp,new Ap(function(){Q(Kp,new Ep(a.b))})):Pp(a.f,a.h,a.g,a.source)}
function Qp(a){a.b?Lp?Q(ii,a):Q(Fp,new Ap(function(){Q(Ip,new Ep(a.b))})):Rp(a.f,a.subscriptionId,a.h,a.g,a.source)}
function Sp(a){Tp(Ta(a.b))}
function Up(a){Vp(Ta(a.b))}
function Wp(a){Xp(a.b,a.isEnabled,null)}
function Yp(a,b,c,d){Xp(a,b,c,d)}
function Zp(a){var b=a.f,c=a.b.subscriptionId;b&&c&&Q(ci,new Vh(b,c,a.b.channelInfo))}
function $p(a){var b=a.b;Ya(a.f,function(a,d){Q(ci,new Vh(d,a,b[d]))})}
function aq(a){Q(hi,new Sh(a.f.itemId));a.b&&a.b.length&&(bq(a.b,hi),bq(a.b,ji))}
function Pp(a,b,c,d){var e=new Sh(a);Q(ai,e);var f={};f.c=a;c&&(f.eurl=c);d&&(f.source=d);c={};(d=F("PLAYBACK_ID"))&&(c.plid=d);(d=F("EVENT_ID"))&&(c.ei=d);b&&cq(b,c);Pd("/subscription_ajax?action_create_subscription_to_channel=1",{method:"POST",Qb:f,J:c,Z:function(b,c){var d=c.response;Q(ci,new Vh(a,d.id,d.channel_info));d.show_feed_privacy_dialog&&J("SHOW-FEED-PRIVACY-SUBSCRIBE-DIALOG",a);d.actions&&zp(d.actions)},
Mb:function(){Q(bi,e)}})}
function Rp(a,b,c,d,e){var f=new Sh(a);Q(fi,f);var h={};d&&(h.eurl=d);e&&(h.source=e);d={};d.c=a;d.s=b;(a=F("PLAYBACK_ID"))&&(d.plid=a);(a=F("EVENT_ID"))&&(d.ei=a);c&&cq(c,d);Pd("/subscription_ajax?action_remove_subscriptions=1",{method:"POST",Qb:h,J:d,Z:function(a,b){var c=b.response;Q(hi,f);c.actions&&zp(c.actions)},
Mb:function(){Q(gi,f)}})}
function Xp(a,b,c,d){if(null!==b||null!==c){var e={};a&&(e.channel_id=a);null===b||(e.email_on_upload=b);null===c||(e.receive_no_updates=c);Pd("/subscription_ajax?action_update_subscription_preferences=1",{method:"POST",J:e,onError:function(){d&&d()}})}}
function Tp(a){if(a.length){var b=Va(a,0,40);Q("subscription-batch-subscribe-loading");bq(b,ai);var c={};c.a=b.join(",");var d=function(){Q("subscription-batch-subscribe-loaded");bq(b,bi)};
Pd("/subscription_ajax?action_create_subscription_to_all=1",{method:"POST",J:c,Z:function(c,f){d();var h=f.response,k=h.id;if(da(k)&&k.length==b.length){var m=h.channel_info_map;y(k,function(a,c){var d=b[c];Q(ci,new Vh(d,a,m[d]))});
a.length?Tp(a):Q("subscription-batch-subscribe-finished")}},
onError:function(){d();Q("subscription-batch-subscribe-failure")}})}}
function Vp(a){if(a.length){var b=Va(a,0,40);Q("subscription-batch-unsubscribe-loading");bq(b,fi);var c={};c.c=b.join(",");var d=function(){Q("subscription-batch-unsubscribe-loaded");bq(b,gi)};
Pd("/subscription_ajax?action_remove_subscriptions=1",{method:"POST",J:c,Z:function(){d();bq(b,hi);a.length&&Vp(a)},
onError:function(){d()}})}}
function bq(a,b){y(a,function(a){Q(b,new Sh(a))})}
function cq(a,b){var c=Gd(a),d;for(d in c)b[d]=c[d]}
;var dq,eq=null,fq=null,gq=null,hq=!1;
function iq(){var a=F("PLAYER_CONFIG",void 0),b=F("REVERSE_MOBIUS_PERCENT",void 0);if("1"!=a.privembed&&Uf&&100*Math.random()<b||$f("enable_server_side_ad_request_for_embeds"))try{var c;try{var d=r("yt.www.ads.biscotti.getId_"),e;if(d)e=d();else{if(!up){var f=tp().then(vp),h=dm(xp,xp,void 0);h.g=!0;km(f,h);up=f}e=up}c=e}catch(k){c=em(k)}fm(c,null,yp,void 0)}catch(k){dc(k)}if(F("REQUEST_POST_MESSAGE_ORIGIN")){if(!dq){dq=new Li;dq.b=iq;return}dq.origin&&"*"!=dq.origin&&(a.args.post_message_origin=dq.origin)}d=
document.referrer;b=F("POST_MESSAGE_ORIGIN");c=!1;$f("legacy_cast")&&u(d)&&u(b)&&-1<d.indexOf(b)&&Jg(b)&&Jg(d)&&(c=!0);window!=window.top&&d&&d!=document.URL&&(a.args.loaderUrl=d);F("LIGHTWEIGHT_AUTOPLAY")&&(a.args.autoplay="1");a.args.autoplay&&jj(a.args);eq=Fh("player",a);d=F("POST_MESSAGE_ID","player");F("ENABLE_JS_API")?gq=new np(eq):F("ENABLE_POST_API")&&u(d)&&u(b)&&(fq=new pp(window.parent,d,b),gq=new jp(eq,fq.f));$f("legacy_cast")&&((hq=c&&!F("ENABLE_CAST_API"))?a.args.disableCast="1":(a=eq,
oo(),Qo=a,Qo.addEventListener("onReady",So),Qo.addEventListener("onRemoteReceiverSelected",Uo),Ro.push(mc("yt-remote-receiver-availability-change",To)),Ro.push(mc("yt-remote-auto-connect",Vo))));F("BG_P")&&(F("BG_I")||F("BG_IU"))&&Bc();Zd();Wo=eq;Wo.addEventListener("SUBSCRIBE",Zo);Wo.addEventListener("UNSUBSCRIBE",bp);Xo.push(Ug(ci,cp),Ug(hi,dp))}
;q("yt.setConfig",ac,void 0);q("yt.setMsg",function(a){bc($b,arguments)},void 0);
q("yt.logging.errors.log",function(a,b,c,d,e){c={name:c||"WEB",version:d||F("INNERTUBE_CONTEXT_CLIENT_VERSION",void 0)};e=window&&window.yterr||e;if(a&&e&&!(5<=Vd)){e=a.stacktrace;d=a.columnNumber;var f=r("window.location.href");if(u(a))a={message:a,name:"Unknown error",lineNumber:"Not available",fileName:f,stack:"Not available"};else{var h,k,m=!1;try{h=a.lineNumber||a.Re||"Not available"}catch(R){h="Not available",m=!0}try{k=a.fileName||a.filename||a.sourceURL||l.$googDebugFname||f}catch(R){k="Not available",
m=!0}a=!m&&a.lineNumber&&a.fileName&&a.stack&&a.message&&a.name?a:{message:a.message||"Not available",name:a.name||"UnknownError",lineNumber:h,fileName:k,stack:a.stack||"Not available"}}e=e||a.stack;h=a.lineNumber.toString();isNaN(h)||isNaN(d)||(h=h+":"+d);if(!(Ud[a.message]||0<=e.indexOf("/YouTubeCenter.js")||0<=e.indexOf("/mytube.js"))){b={Qb:{a:"logerror",t:"jserror",type:a.name,msg:a.message.substr(0,1E3),line:h,level:b||"ERROR"},J:{url:F("PAGE_NAME",window.location.href),file:a.fileName},method:"POST"};
e&&(b.J.stack=e);for(var n in c)b.J["client."+n]=c[n];if(n=F("LATEST_ECATCHER_SERVICE_TRACKING_PARAMS",void 0))for(var H in n)b.J[H]=n[H];Pd("/error_204",b);Ud[a.message]=!0;Vd++}}},void 0);
q("writeEmbed",iq,void 0);q("yt.www.watch.ads.restrictioncookie.spr",function(a){(a+="mac_204?action_fcts=1")&&Mg(a);return!0},void 0);
var jq=cc(function(){dh("ol");Lp=!0;Np.push(Ug($h,Op),Ug(ei,Qp));Lp||(Np.push(Ug(di,Op),Ug(ii,Qp),Ug(Xh,Sp),Ug(Yh,Up),Ug(Zh,Wp)),Mp.push(mc("subscription-prefs",Yp)),Np.push(Ug(Hp,Zp),Ug(Jp,aq),Ug(Gp,$p)));sf.getInstance();var a=1<window.devicePixelRatio;if(xf(0,119)!=a){var b="f"+(Math.floor(119/31)+1),c=wf(b)||0,c=a?c|67108864:c&-67108865;0==c?delete tf[b]:(a=c.toString(16),tf[b]=a.toString());var b=[],d;for(d in tf)b.push(d+"="+escape(tf[d]));Se("PREF",b.join("&"),63072E3)}}),kq=cc(function(){var a=
eq;
a&&a.sendAbandonmentPing&&a.sendAbandonmentPing();F("PL_ATT")&&(Ac=null);for(var a=0,b=Xd.length;a<b;a++){var c=Xd[a];if(!isNaN(c)){var d=r("yt.scheduler.instance.cancelJob");d?d(c):I(c)}}Xd.length=0;a=wc("//static.doubleclick.net/instream/ad_status.js");if(b=document.getElementById(a))rc(a),b.parentNode.removeChild(b);Yd=!1;ac("DCLKSTAT",0);oc(Mp);Mp.length=0;Vg(Np);Np.length=0;Lp=!1;Wo&&(Wo.removeEventListener("SUBSCRIBE",$o),Wo.removeEventListener("UNSUBSCRIBE",bp));Wo=null;Vg(Xo);Xo.length=0;
$f("legacy_cast")&&!hq&&(oc(Ro),Ro.length=0,Qo&&(Qo.removeEventListener("onRemoteReceiverSelected",Uo),Qo.removeEventListener("onReady",So),Qo=null),Co());Xb(gq,fq);eq&&eq.destroy()});
window.addEventListener?(window.addEventListener("load",jq),window.addEventListener("unload",kq)):window.attachEvent&&(window.attachEvent("onload",jq),window.attachEvent("onunload",kq));var lq=Fi.getInstance(),mq=S(lq);mq in Ki||(lq.register(),lq.lb.push(mc("yt-uix-init-"+mq,lq.init,lq)),lq.lb.push(mc("yt-uix-dispose-"+mq,lq.dispose,lq)),Ki[mq]=lq);})();
