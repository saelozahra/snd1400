package ir.saelozahra.snd1400;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class firebasemessaging extends  android.app.Service{
	public static class firebasemessaging_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (firebasemessaging) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, firebasemessaging.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, BA.class);
		}

	}
    static firebasemessaging mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return firebasemessaging.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.firebasemessaging");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.firebasemessaging", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (firebasemessaging) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (firebasemessaging) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (firebasemessaging) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (false) {
            BA.LogInfo("** Service (firebasemessaging) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (firebasemessaging) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper _fm = null;
public static String _msg_type = "";
public static String _msg_title = "";
public static String _msg_body = "";
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public static anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
public static barxdroid.NotificationBuilder.NotificationBuilder _nb = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.starter _starter = null;
public ir.saelozahra.snd1400.homeact _homeact = null;
public ir.saelozahra.snd1400.saelozahra _saelozahra = null;
public ir.saelozahra.snd1400.adminsact _adminsact = null;
public ir.saelozahra.snd1400.candidalistact _candidalistact = null;
public ir.saelozahra.snd1400.electionact _electionact = null;
public ir.saelozahra.snd1400.notificationact _notificationact = null;
public ir.saelozahra.snd1400.qrforscanact _qrforscanact = null;
public ir.saelozahra.snd1400.selectmapact _selectmapact = null;
public ir.saelozahra.snd1400.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.snd1400.b4xcollections _b4xcollections = null;
public ir.saelozahra.snd1400.httputils2service _httputils2service = null;
public static anywheresoftware.b4a.objects.NotificationWrapper  _createnotification(String _title,String _contentstr,String _icon,Object _targetactivity,boolean _sound,boolean _vibrate) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _javaobjectinstance = null;
anywheresoftware.b4j.object.JavaObject _ctxt = null;
anywheresoftware.b4j.object.JavaObject _manager = null;
anywheresoftware.b4j.object.JavaObject _channel = null;
String _importance = "";
String _channelvisiblename = "";
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.objects.NotificationWrapper _n = null;
 //BA.debugLineNum = 132;BA.debugLine="Public Sub CreateNotification(Title As String, Con";
 //BA.debugLineNum = 134;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 135;BA.debugLine="If p.SdkVersion >= 21 Then";
if (_p.getSdkVersion()>=21) { 
 //BA.debugLineNum = 136;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 137;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 138;BA.debugLine="nb.DefaultSound = Sound";
_nb.setDefaultSound(_sound);
 //BA.debugLineNum = 139;BA.debugLine="nb.DefaultVibrate = Vibrate";
_nb.setDefaultVibrate(_vibrate);
 //BA.debugLineNum = 140;BA.debugLine="nb.AutoCancel = True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 141;BA.debugLine="nb.ContentTitle = Title";
_nb.setContentTitle(_title);
 //BA.debugLineNum = 142;BA.debugLine="nb.ContentText = ContentStr";
_nb.setContentText(_contentstr);
 //BA.debugLineNum = 143;BA.debugLine="nb.setActivity(TargetActivity)";
_nb.setActivity(processBA,_targetactivity);
 //BA.debugLineNum = 144;BA.debugLine="nb.AddAction(\"ic_done_white_24dp\",\"نمایش اطلاعیه";
_nb.AddAction(processBA,"ic_done_white_24dp","نمایش اطلاعیه","full",_targetactivity);
 //BA.debugLineNum = 145;BA.debugLine="nb.AddAction(\"baseline_my_location_white_24\",\"کل";
_nb.AddAction(processBA,"baseline_my_location_white_24","کل اطلاعیه ها","full",(Object)(mostCurrent._notificationact.getObject()));
 //BA.debugLineNum = 146;BA.debugLine="nb.SmallIcon = Icon";
_nb.setSmallIcon(_icon);
 //BA.debugLineNum = 148;BA.debugLine="Dim javaobjectInstance As JavaObject";
_javaobjectinstance = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 149;BA.debugLine="javaobjectInstance = nb";
_javaobjectinstance = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_nb.getObject()));
 //BA.debugLineNum = 150;BA.debugLine="javaobjectInstance.RunMethod (\"setColor\", Array";
_javaobjectinstance.RunMethod("setColor",new Object[]{(Object)(mostCurrent._saelozahra._color /*int*/ )});
 //BA.debugLineNum = 152;BA.debugLine="If p.SdkVersion >= 26 Then";
if (_p.getSdkVersion()>=26) { 
 //BA.debugLineNum = 153;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 154;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext(processBA);
 //BA.debugLineNum = 155;BA.debugLine="Dim manager As JavaObject";
_manager = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 156;BA.debugLine="manager.InitializeStatic(\"android.app.Notificat";
_manager.InitializeStatic("android.app.NotificationManager");
 //BA.debugLineNum = 157;BA.debugLine="Dim Channel As JavaObject";
_channel = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 158;BA.debugLine="Dim importance As String";
_importance = "";
 //BA.debugLineNum = 159;BA.debugLine="If Sound Then importance = \"IMPORTANCE_DEFAULT\"";
if (_sound) { 
_importance = "IMPORTANCE_DEFAULT";}
else {
_importance = "IMPORTANCE_LOW";};
 //BA.debugLineNum = 160;BA.debugLine="Dim ChannelVisibleName As String = Application.";
_channelvisiblename = anywheresoftware.b4a.keywords.Common.Application.getLabelName();
 //BA.debugLineNum = 161;BA.debugLine="Channel.InitializeNewInstance(\"android.app.Noti";
_channel.InitializeNewInstance("android.app.NotificationChannel",new Object[]{(Object)("MyChannelId1"),(Object)(_channelvisiblename),_manager.GetField(_importance)});
 //BA.debugLineNum = 163;BA.debugLine="manager = ctxt.RunMethod(\"getSystemService\", Ar";
_manager = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_ctxt.RunMethod("getSystemService",new Object[]{(Object)("notification")})));
 //BA.debugLineNum = 164;BA.debugLine="manager.RunMethod(\"createNotificationChannel\",";
_manager.RunMethod("createNotificationChannel",new Object[]{(Object)(_channel.getObject())});
 //BA.debugLineNum = 165;BA.debugLine="Dim jo As JavaObject = nb";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_nb.getObject()));
 //BA.debugLineNum = 166;BA.debugLine="jo.RunMethod(\"setChannelId\", Array(\"MyChannelId";
_jo.RunMethod("setChannelId",new Object[]{(Object)("MyChannelId1")});
 };
 //BA.debugLineNum = 168;BA.debugLine="Return  nb.GetNotification";
if (true) return (anywheresoftware.b4a.objects.NotificationWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.NotificationWrapper(), (java.lang.Object)(_nb.GetNotification(processBA)));
 }else {
 //BA.debugLineNum = 170;BA.debugLine="Dim n As Notification";
_n = new anywheresoftware.b4a.objects.NotificationWrapper();
 //BA.debugLineNum = 171;BA.debugLine="n.Initialize";
_n.Initialize();
 //BA.debugLineNum = 173;BA.debugLine="n.AutoCancel=True";
_n.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 174;BA.debugLine="n.Vibrate = Vibrate";
_n.setVibrate(_vibrate);
 //BA.debugLineNum = 175;BA.debugLine="n.Icon = Icon";
_n.setIcon(_icon);
 //BA.debugLineNum = 177;BA.debugLine="n.SetInfo(Title, ContentStr, TargetActivity)";
_n.SetInfoNew(processBA,BA.ObjectToCharSequence(_title),BA.ObjectToCharSequence(_contentstr),_targetactivity);
 //BA.debugLineNum = 178;BA.debugLine="Return n";
if (true) return _n;
 };
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return null;
}
public static String  _fm_messagearrived(anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message) throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub fm_MessageArrived (Message As RemoteMessage)";
 //BA.debugLineNum = 72;BA.debugLine="Log(\"Message arrived\")";
anywheresoftware.b4a.keywords.Common.LogImpl("67536642","Message arrived",0);
 //BA.debugLineNum = 73;BA.debugLine="Log(\"Message data: \"&Message.GetData)";
anywheresoftware.b4a.keywords.Common.LogImpl("67536643","Message data: "+BA.ObjectToString(_message.GetData()),0);
 //BA.debugLineNum = 75;BA.debugLine="If Message.GetData.ContainsKey(\"type\") Then";
if (_message.GetData().ContainsKey((Object)("type"))) { 
 //BA.debugLineNum = 76;BA.debugLine="msg_type = Message.GetData.Get(\"type\")";
_msg_type = BA.ObjectToString(_message.GetData().Get((Object)("type")));
 //BA.debugLineNum = 77;BA.debugLine="msg_body = Message.GetData.Get(\"body\")";
_msg_body = BA.ObjectToString(_message.GetData().Get((Object)("body")));
 }else {
 //BA.debugLineNum = 79;BA.debugLine="msg_type = \"text\"";
_msg_type = "text";
 //BA.debugLineNum = 80;BA.debugLine="If Message.GetData.ContainsKey(\"body\") Then";
if (_message.GetData().ContainsKey((Object)("body"))) { 
 //BA.debugLineNum = 81;BA.debugLine="msg_body = Message.GetData.Get(\"body\")";
_msg_body = BA.ObjectToString(_message.GetData().Get((Object)("body")));
 }else if(_message.GetData().ContainsKey((Object)("val"))) { 
 //BA.debugLineNum = 83;BA.debugLine="msg_body = Message.GetData.Get(\"val\")";
_msg_body = BA.ObjectToString(_message.GetData().Get((Object)("val")));
 };
 };
 //BA.debugLineNum = 87;BA.debugLine="If Message.GetData.Get(\"title\") == Null Then";
if (_message.GetData().Get((Object)("title"))== null) { 
 //BA.debugLineNum = 88;BA.debugLine="msg_title=Application.LabelName";
_msg_title = anywheresoftware.b4a.keywords.Common.Application.getLabelName();
 }else {
 //BA.debugLineNum = 90;BA.debugLine="msg_title=Message.GetData.Get(\"title\")";
_msg_title = BA.ObjectToString(_message.GetData().Get((Object)("title")));
 };
 //BA.debugLineNum = 94;BA.debugLine="If Message.GetData.Get(\"body\") == Null Then";
if (_message.GetData().Get((Object)("body"))== null) { 
 //BA.debugLineNum = 95;BA.debugLine="msg_body=Application.LabelName";
_msg_body = anywheresoftware.b4a.keywords.Common.Application.getLabelName();
 }else {
 //BA.debugLineNum = 97;BA.debugLine="msg_body=Message.GetData.Get(\"body\")";
_msg_body = BA.ObjectToString(_message.GetData().Get((Object)("body")));
 };
 //BA.debugLineNum = 100;BA.debugLine="If Message.GetData.Get(\"title\")==Null And Message";
if (_message.GetData().Get((Object)("title"))== null && _message.GetData().Get((Object)("body"))== null) { 
if (true) return "";};
 //BA.debugLineNum = 102;BA.debugLine="If msg_type == \"expire\" And msg_body==\"yes\" Then";
if ((_msg_type).equals("expire") && (_msg_body).equals("yes")) { 
 //BA.debugLineNum = 103;BA.debugLine="File.WriteString(saelozahra.dir,\"expire\",True)";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"expire",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True));
 }else if((_msg_type).equals("expire") && (_msg_body).equals("no")) { 
 //BA.debugLineNum = 105;BA.debugLine="File.Delete(saelozahra.dir,\"expire\")";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._saelozahra._dir /*String*/ ,"expire");
 };
 //BA.debugLineNum = 109;BA.debugLine="Log(msg_title&\"  :  \"&msg_type&\"  :  \"&msg_body)";
anywheresoftware.b4a.keywords.Common.LogImpl("67536679",_msg_title+"  :  "+_msg_type+"  :  "+_msg_body,0);
 //BA.debugLineNum = 111;BA.debugLine="saveAndAlert(msg_title, msg_body, True)";
_saveandalert(_msg_title,_msg_body,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _fm_tokenrefresh(String _token) throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub fm_TokenRefresh (Token As String)";
 //BA.debugLineNum = 67;BA.debugLine="Log(\"Token: \"&Token)";
anywheresoftware.b4a.keywords.Common.LogImpl("67471105","Token: "+_token,0);
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim fm As FirebaseMessaging";
_fm = new anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Dim msg_type,msg_title,msg_body As String";
_msg_type = "";
_msg_title = "";
_msg_body = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim sql1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 12;BA.debugLine="Dim R As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 13;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _saveandalert(String _title_str,String _content_str,boolean _notifbool) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub saveAndAlert(title_str As String, content_str";
 //BA.debugLineNum = 120;BA.debugLine="Try";
try { //BA.debugLineNum = 121;BA.debugLine="sql1.ExecNonQuery($\"insert into notification (ti";
_sql1.ExecNonQuery(("insert into notification (title,type,value,time) VALUES ('"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_msg_title))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_msg_type))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_msg_body))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))+"')"));
 //BA.debugLineNum = 122;BA.debugLine="Log(\"Inserted\")";
anywheresoftware.b4a.keywords.Common.LogImpl("67602181","Inserted",0);
 } 
       catch (Exception e5) {
			processBA.setLastException(e5); //BA.debugLineNum = 124;BA.debugLine="Log($\" ${LastException.Message} error to insert";
anywheresoftware.b4a.keywords.Common.LogImpl("67602183",(" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage()))+" error to insert this id to notification = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_msg_title))+""),0);
 };
 //BA.debugLineNum = 128;BA.debugLine="If NotifBool Then CreateNotification(title_str ,";
if (_notifbool) { 
_createnotification(_title_str,_content_str,"icon",(Object)(mostCurrent._notificationact.getObject()),anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True).Notify((int) (1));};
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 19;BA.debugLine="fm.Initialize(\"fm\")";
_fm.Initialize(processBA,"fm");
 //BA.debugLineNum = 23;BA.debugLine="fm.SubscribeToTopic(\"shirazzist\")";
_fm.SubscribeToTopic("shirazzist");
 //BA.debugLineNum = 25;BA.debugLine="If Not(sql1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_sql1.IsInitialized())) { 
 //BA.debugLineNum = 26;BA.debugLine="If Not(File.Exists(SaeloZahra.dir,\"db.db\")) Then";
if (anywheresoftware.b4a.keywords.Common.Not(anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"db.db"))) { 
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"db.db",mostCurrent._saelozahra._dir /*String*/ ,"db.db");};
 //BA.debugLineNum = 27;BA.debugLine="sql1.Initialize(SaeloZahra.dir,\"db.db\",False)";
_sql1.Initialize(mostCurrent._saelozahra._dir /*String*/ ,"db.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 182;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static void  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
ResumableSub_Service_Start rsub = new ResumableSub_Service_Start(null,_startingintent);
rsub.resume(processBA, null);
}
public static class ResumableSub_Service_Start extends BA.ResumableSub {
public ResumableSub_Service_Start(ir.saelozahra.snd1400.firebasemessaging parent,anywheresoftware.b4a.objects.IntentWrapper _startingintent) {
this.parent = parent;
this._startingintent = _startingintent;
}
ir.saelozahra.snd1400.firebasemessaging parent;
anywheresoftware.b4a.objects.IntentWrapper _startingintent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 61;BA.debugLine="If StartingIntent.IsInitialized Then fm.HandleInt";
if (true) break;

case 1:
//if
this.state = 6;
if (_startingintent.IsInitialized()) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
parent._fm.HandleIntent((android.content.Intent)(_startingintent.getObject()));
if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 62;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 7;
return;
case 7:
//C
this.state = -1;
;
 //BA.debugLineNum = 63;BA.debugLine="Service.StopAutomaticForeground 'remove if not us";
parent.mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _subscribetotopic() throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Public Sub SubscribeToTopic";
 //BA.debugLineNum = 34;BA.debugLine="fm.SubscribeToTopic(\"general\")";
_fm.SubscribeToTopic("general");
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static void  _subscribetotopics(String _topicname) throws Exception{
ResumableSub_SubscribeToTopics rsub = new ResumableSub_SubscribeToTopics(null,_topicname);
rsub.resume(processBA, null);
}
public static class ResumableSub_SubscribeToTopics extends BA.ResumableSub {
public ResumableSub_SubscribeToTopics(ir.saelozahra.snd1400.firebasemessaging parent,String _topicname) {
this.parent = parent;
this._topicname = _topicname;
}
ir.saelozahra.snd1400.firebasemessaging parent;
String _topicname;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 41;BA.debugLine="TopicName=TopicName.Replace(\" \",\"_\")";
_topicname = _topicname.replace(" ","_");
 //BA.debugLineNum = 42;BA.debugLine="TopicName=TopicName.Replace(\":\",\"_\")";
_topicname = _topicname.replace(":","_");
 //BA.debugLineNum = 43;BA.debugLine="TopicName=TopicName.Replace(\"\\n\",\"\")";
_topicname = _topicname.replace("\\n","");
 //BA.debugLineNum = 45;BA.debugLine="If TopicName==\"\" Then TopicName=\"shirazzist\"";
if (true) break;

case 1:
//if
this.state = 6;
if ((_topicname).equals("")) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
_topicname = "shirazzist";
if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 47;BA.debugLine="Try";
if (true) break;

case 7:
//try
this.state = 12;
this.catchState = 11;
this.state = 9;
if (true) break;

case 9:
//C
this.state = 12;
this.catchState = 11;
 //BA.debugLineNum = 48;BA.debugLine="fm.SubscribeToTopic( TopicName )";
parent._fm.SubscribeToTopic(_topicname);
 //BA.debugLineNum = 49;BA.debugLine="Log(\"adding topic \"&TopicName)";
anywheresoftware.b4a.keywords.Common.LogImpl("67340044","adding topic "+_topicname,0);
 if (true) break;

case 11:
//C
this.state = 12;
this.catchState = 0;
 //BA.debugLineNum = 51;BA.debugLine="Log(\"error1: \"&LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("67340046","error1: "+anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage(),0);
 //BA.debugLineNum = 52;BA.debugLine="R.Target = fm";
parent._r.Target = (Object)(parent._fm.getObject());
 //BA.debugLineNum = 53;BA.debugLine="R.RunMethod2(\"subscribeToTopic\",TopicName,\"java.";
parent._r.RunMethod2("subscribeToTopic",_topicname,"java.lang.String");
 if (true) break;
if (true) break;

case 12:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 56;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 13;
return;
case 13:
//C
this.state = -1;
;
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
}
