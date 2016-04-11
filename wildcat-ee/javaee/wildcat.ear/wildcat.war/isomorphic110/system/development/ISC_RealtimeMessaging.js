
/*

  SmartClient Ajax RIA system
  Version v11.0p_2016-03-31/LGPL Development Only (2016-03-31)

  Copyright 2000 and beyond Isomorphic Software, Inc. All rights reserved.
  "SmartClient" is a trademark of Isomorphic Software, Inc.

  LICENSE NOTICE
     INSTALLATION OR USE OF THIS SOFTWARE INDICATES YOUR ACCEPTANCE OF
     ISOMORPHIC SOFTWARE LICENSE TERMS. If you have received this file
     without an accompanying Isomorphic Software license file, please
     contact licensing@isomorphic.com for details. Unauthorized copying and
     use of this software is a violation of international copyright law.

  DEVELOPMENT ONLY - DO NOT DEPLOY
     This software is provided for evaluation, training, and development
     purposes only. It may include supplementary components that are not
     licensed for deployment. The separate DEPLOY package for this release
     contains SmartClient components that are licensed for deployment.

  PROPRIETARY & PROTECTED MATERIAL
     This software contains proprietary materials that are protected by
     contract and intellectual property law. You are expressly prohibited
     from attempting to reverse engineer this software or modify this
     software for human readability.

  CONTACT ISOMORPHIC
     For more information regarding license rights and restrictions, or to
     report possible license violations, please contact Isomorphic Software
     by email (licensing@isomorphic.com) or web (www.isomorphic.com).

*/

if(window.isc&&window.isc.module_Core&&!window.isc.module_RealtimeMessaging){isc.module_RealtimeMessaging=1;isc._moduleStart=isc._RealtimeMessaging_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log&&isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={message:'RealtimeMessaging load/parse time: '+(isc._moduleStart-isc._moduleEnd)+'ms',category:'loadTime'};if(isc.Log&&isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime');else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM;else isc._preLog=[isc._pTM]}isc.definingFramework=true;if(window.isc&&isc.version!="v11.0p_2016-03-31/LGPL Development Only"&&!isc.DevUtil){isc.$228=function(){var _1=false;if(isc.version.toLowerCase().contains("pro")||isc.version.toLowerCase().contains("lgpl")){_1=true}else{var _2=isc.version;if(_2.indexOf("/")!=-1){_2=_2.substring(0,_2.indexOf("/"))}
var _3="v11.0p_2016-03-31/LGPL Development Only";if(_3.indexOf("/")!=-1){_3=_3.substring(0,_3.indexOf("/"))}
if(_2!=_3){_1=true}}
if(_1){isc.logWarn("SmartClient module version mismatch detected: This application is loading the core module from SmartClient version '"+isc.version+"' and additional modules from 'v11.0p_2016-03-31/LGPL Development Only'. Mixing resources from different SmartClient packages is not supported and may lead to unpredictable behavior. If you are deploying resources from a single package you may need to clear your browser cache, or restart your browser."+(isc.Browser.isSGWT?" SmartGWT developers may also need to clear the gwt-unitCache and run a GWT Compile.":""))}}
isc.$228()}
isc.ClassFactory.defineClass("Messaging");isc.A=isc.Messaging;isc.A.messagingURL="[ISOMORPHIC]/messaging";isc.A.useEventSource=(!isc.Browser.isSafari||parseFloat(isc.Browser.rawSafariVersion)>=534.29)&&!!window.EventSource;isc.A.$229=1;isc.A._channels={};isc.A.$23a=[];isc.A.$23b=20;isc.A.connectTimeout=4000;isc.A.legacyCommHTTPMethod="GET";isc.A=isc.Messaging;isc.A.useAJAX=!isc.Messaging.useEventSource&&((isc.Browser.isFirefox&&isc.Browser.minorVersion<4)||isc.Browser.isSafari);isc.A.$23c=!isc.Messaging.useEventSource&&isc.Browser.isSafari;isc.A=isc.Messaging;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$23d=[];isc.B.push(isc.A.$23e=function isc_c_Messaging__handleEventSourceError(_1){},isc.A.send=function isc_c_Messaging_send(_1,_2,_3,_4){if(!isc.hasOptionalModules("RealtimeMessaging")&&!this.isRemoteDebug){this.logWarn("RealtimeMessaging not licensed - refusing to send()");return}
if(!isc.isAn.Array(_1))_1=[_1];if(_4&&_4.sequenced){this.$23d.add({channels:_1,data:_2,callback:_3,requestProperties:_4});if(this.$23d.length>1)return}
this.$yo(_1,_2,_3,_4)},isc.A.$yo=function isc_c_Messaging__send(_1,_2,_3,_4){isc.DMI.callBuiltin({methodName:"messagingSend",callback:"isc.Messaging.$23f(data, rpcRequest)",arguments:[{type:"send",sendToChannels:_1,subscribedChannels:this._channels,data:_2}],requestParams:isc.addProperties({showPrompt:false,callback:_3,willHandleError:_3!=null},_4)})},isc.A.$23f=function isc_c_Messaging__sendCallback(_1,_2){if(_2&&_2.sequenced){this.$23d.removeAt(0);var _3=this.$23d[0];if(_3){this.$yo(_3.channels,_3.data,_3.callback,_3.requestProperties)}}},isc.A.getSubscribedChannels=function isc_c_Messaging_getSubscribedChannels(){return isc.getKeys(this._channels)},isc.A.subscribe=function isc_c_Messaging_subscribe(_1,_2,_3,_4,_5){if(!isc.hasOptionalModules("RealtimeMessaging")&&!this.isRemoteDebug){this.logWarn("RealtimeMessaging not licensed - refusing to subscribe()");return}
var _6=true;if(!this._channels[_1]){this._channels[_1]={};if(_5)this._channels[_1].data=_5;if(_4)this._channels[_1].selector=_4;this._channels[_1].subscriptionCallback=_3;this.$23g();_6=false}
this._channels[_1].callback=_2;if(_6){this.fireCallback(_3,null,null,null,true)}
return},isc.A.unsubscribe=function isc_c_Messaging_unsubscribe(_1){if(!this._channels[_1])return;delete this._channels[_1];this.$23g();if(isc.isAn.emptyObject(this._channels))this.disconnect()},isc.A.connected=function isc_c_Messaging_connected(){return this._channels&&isc.getKeys(this._channels).length>0&&this.$23h},isc.A.disconnect=function isc_c_Messaging_disconnect(){this._channels={};this.$23i();this.$23j=null;isc.Timer.clear(this.$23k);this.$23k=null;isc.Timer.clear(this.$23l);this.$23l=null;this.connectionDown()},isc.A.$23g=function isc_c_Messaging__reconnect(){if(!isc.Page.isLoaded()){if(!this.$23m){isc.Page.setEvent("load","isc.Messaging.$23g()");this.$23m=true}
return}
if(!this.$23k)
this.$23k=isc.Timer.setTimeout("isc.Messaging.$23n()",this.$229,isc.Timer.MSEC)},isc.A.$23o=function isc_c_Messaging__connectRetry(){if(this.$23p&&window[this.$23p])window[this.$23p].destroy();this.$23p=null;this.logDebug("connect failed, retrying in: "+this.connectTimeout+"ms");this.$23g()},isc.A._serverConnTerminate=function isc_c_Messaging__serverConnTerminate(){this.$23g()},isc.A.$23n=function isc_c_Messaging__connect(){if(this.usingAJAX&&!isc.Page.isLoaded()){if(!this.$23m){isc.Page.setEvent("load","isc.Messaging.$23g()");this.$23m=true}
return}
isc.Timer.clear(this.$23k);this.$23k=null;if(this.$23p){this.$23q=true;this.logDebug("connect pending - deferring openConnection request.");return}
if(this.getSubscribedChannels().length==0)return;this.$23h=false;var _1=isc.HiddenFrame.create({useHtmlfile:isc.Browser.isIE});this.$23p=_1.getID();this.$23r();var _2={type:"connect",connectionID:this.$23p,subscribedChannels:isc.Comm.serialize(this._channels)};var _3=isc.URIBuilder.create(isc.Page.getURL(this.messagingURL));_3.setQueryParam("ts",isc.timestamp());_3.setQueryParam("isc_noLog","1");if(isc.Messaging.$23c){isc.Messaging.$23c=false;_3.setQueryParam("disconnectUponConnect","true")}
if(this.useEventSource){_1.$qg();for(var _4 in _2){if(!_2.hasOwnProperty(_4))continue;_3.setQueryParam(_4,String(_2[_4]))}
_3.setQueryParam("eventStream","true");var _5=this.$23s=new EventSource(_3.uri);_5.onerror=isc.Messaging.$23e;var _6=function createEventListener(_13){var _7=function eventListenerFun(_14){var _8=location.origin;if(_8==null){_8=location.protocol+"//"+location.host}
if(_14.origin==null||_14.origin!=_8){isc.Messaging.logWarn("'"+_13+"' event received with wrong origin: "+_14.origin+" (should be "+_8+")");return}
if(_1.$qi!=null){_1.$qi.document.write("<SCRIPT>"+_14.data+"</SCRIPT>")}else{_5.removeEventListener(_13,_7,false)}};return _7};_5.addEventListener("connectCallback",this.$23t=_6("connectCallback"),false);_5.addEventListener("establishAck",this.$23u=_6("establishAck"),false);_5.addEventListener("keepalive",this.$23v=_6("keepalive"),false);_5.addEventListener("message",this.$23w=_6("message"),false);_5.addEventListener("serverConnTerminate",this.$23x=_6("serverConnTerminate"),false)}else if(this.useAJAX){_1.$qg();var _9=0;var _10=this.$23y=function(){if(_10!=isc.Messaging.$23y)return;var _11=isc.Messaging.$23z;if(!_11)return;if(_11.readyState==3||_11.readyState==4||(isc.Browser.isOpera&&_11.readyState==2))
{var _12=_11.responseText.substring(_9);_9=_11.responseText.length;_1.$qi.document.write(_12)}};this.$23z=isc.Comm.sendXmlHttpRequest({URL:_3.uri,fields:_2,httpMethod:this.legacyCommHTTPMethod,transaction:{changed:function(){},requestData:_2},onreadystatechange:_10})}else{isc.Comm.sendHiddenFrame({URL:_3.uri,fields:_2,httpMethod:this.legacyCommHTTPMethod,transaction:{changed:function(){},requestData:_2},frame:_1})}
this.$230=isc.Timer.setTimeout("isc.Messaging.$23o()",this.connectTimeout,isc.Timer.MSEC)},isc.A._connectCallback=function isc_c_Messaging__connectCallback(_1,_2){if(_1!=this.$23p)return;this.$231=_2.keepaliveInterval;this.$232=_2.keepaliveReestablishDelay;this.$233=this.$231+this.$232;this.$234=_2.connectionTTL;this.connectTimeout=_2.connectTimeout;if(this.$235&&window[this.$235])window[this.$235].destroy();this.$235=this.$23p;this.$23p=null;isc.Timer.clear(this.$230);this.$236();this.$237();this.logDebug("persistent server connection open - ttl: "+this.$234+"ms, keepaliveDelay: "+this.$233+"ms, connectTimeout: "+this.connectTimeout+"ms.")
this.connectionUp();if(this.$23q){this.$23q=false;this.$23g();return}
this.$23h=true;for(var _3 in this._channels){var _4=this._channels[_3];if(_4.subscriptionCallback){this.fireCallback(_4.subscriptionCallback,null,null,null,true);delete _4.subscriptionCallback}}},isc.A.connectionUp=function isc_c_Messaging_connectionUp(){},isc.A.connectionDown=function isc_c_Messaging_connectionDown(){},isc.A.$236=function isc_c_Messaging__resetStatusBar(){var _1=isc.Browser.isIE?"Done":"Stopped";isc.Timer.setTimeout("window.status='"+_1+"'",0)},isc.A._establishAck=function isc_c_Messaging__establishAck(_1){if(_1&&window[_1])window[_1].destroy();this.$238=true},isc.A._keepalive=function isc_c_Messaging__keepalive(_1){this.$236();if(_1!=this.$235)return;this.$237();this.logDebug("keepalive on conn: "+_1)},isc.A.$239=function isc_c_Messaging__keepaliveWatchdog(){this.logDebug("connection to server lost, re-establishing...");this.$23g();this.connectionDown()},isc.A.$237=function isc_c_Messaging__resetKeepaliveTimer(){isc.Timer.clear(this.$23l);this.$23l=isc.Timer.setTimeout("isc.Messaging.$239()",this.$233,isc.Timer.MSEC)},isc.A._message=function(message){message=isc.eval("var message = "+message+";message;");var conn=message.conn,channels=message.channels,id=message.id,data=message.data;this.$236();if(conn!=this.$235)return;this.$237();if(this.$23a.contains(id)){this.logDebug("ignoring duplicate messageID: "+id);return}
this.$23a.push(id);if(this.$23a.length>this.$23b)this.$23a.shift();for(var i=0;i<channels.length;i++){var channel=channels[i];if(!this._channels[channel])continue;var channel=this._channels[channel],callback=channel.callback
if(callback)this.fireCallback(callback,"data",[data],channel,true)}},isc.A.$23r=function isc_c_Messaging__cleanupAlt(){if(this.$23s!=null){var _1=this.$23s;_1.close();_1.removeEventListener("connectCallback",this.$23t,false);delete this.$23t;_1.removeEventListener("establishAck",this.$23u,false);delete this.$23u;_1.removeEventListener("keepalive",this.$23v,false);delete this.$23v;_1.removeEventListener("message",this.$23w,false);delete this.$23w;_1.removeEventListener("serverConnTerminate",this.$23x,false);delete this.$23x;_1=null;delete this.$23s}
if(this.$23z!=null){this.$23z.abort();delete this.$23z}},isc.A.$23i=function isc_c_Messaging__destroyConns(){if(this.$23p!=null&&window[this.$23p]!=null){window[this.$23p].destroy()}
if(this.$235!=null&&window[this.$235]!=null&&this.$235!=this.$23p){window[this.$235].destroy()}
delete this.$235;delete this.$23p;this.$23r()});isc.B._maxIndex=isc.C+24;isc.Page.setEvent("unload",function(){isc.Messaging.$23i()});isc.defineClass("MessagingDMIDiscoveryDS","DataSource");isc.A=isc.MessagingDMIDiscoveryDS.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.clientOnly=true;isc.A.fields=[{name:"GUID",primaryKey:true},{name:"userAgent",title:"User Agent"},{name:"lastContact",title:"Last Contact",type:"datetime"}];isc.B.push(isc.A.init=function isc_MessagingDMIDiscoveryDS_init(){this.Super("init",arguments);this.cacheData=[];this.discover()},isc.A.invalidateCache=function isc_MessagingDMIDiscoveryDS_invalidateCache(){var _1=this;var _2=this.getCacheData();while(_2.length)_1.removeData(_2[0]);this.delayCall("discover")},isc.A.discover=function isc_MessagingDMIDiscoveryDS_discover(){var _1=this;if(!this.client){this.client=isc.MessagingDMIClient.create({socketProperties:{doNotTrackRPC:true,isRemoteDebug:this.isRemoteDebug}})}
this.client.call({sendChannel:this.discoverOnChannel,methodName:"discover",timeout:this.discoveryTimeout,callback:function(_2){_1.updateServer(_2)}})},isc.A.updateServer=function isc_MessagingDMIDiscoveryDS_updateServer(_1){_1.lastContact=new Date();var _2=this;this.fetchData({GUID:_1.GUID},function(_3){if(_3.data&&_3.data.getLength()==0){_2.addData(_1)}else{_2.updateData(_1)}})});isc.B._maxIndex=isc.C+4;isc._nonDebugModules=(isc._nonDebugModules!=null?isc._nonDebugModules:[]);isc._nonDebugModules.push('RealtimeMessaging');isc.checkForDebugAndNonDebugModules();isc._moduleEnd=isc._RealtimeMessaging_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('RealtimeMessaging module init time: '+(isc._moduleEnd-isc._moduleStart)+'ms','loadTime');delete isc.definingFramework;if(isc.Page)isc.Page.handleEvent(null,"moduleLoaded",{moduleName:'RealtimeMessaging',loadTime:(isc._moduleEnd-isc._moduleStart)});}else{if(window.isc&&isc.Log&&isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'RealtimeMessaging'.");}/** RealtimeMessagingModule End **/

