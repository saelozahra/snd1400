package ir.saelozahra.snd1400;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends  android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.starter");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
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
                    BA.LogInfo("** Service (starter) Create **");
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
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
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
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
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
public static anywheresoftware.b4a.gps.GPS _gps1 = null;
public static boolean _gpsstarted = false;
public static anywheresoftware.b4a.gps.LocationWrapper _mylocation = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.homeact _homeact = null;
public ir.saelozahra.snd1400.saelozahra _saelozahra = null;
public ir.saelozahra.snd1400.adminsact _adminsact = null;
public ir.saelozahra.snd1400.candidalistact _candidalistact = null;
public ir.saelozahra.snd1400.electionact _electionact = null;
public ir.saelozahra.snd1400.firebasemessaging _firebasemessaging = null;
public ir.saelozahra.snd1400.notificationact _notificationact = null;
public ir.saelozahra.snd1400.qrforscanact _qrforscanact = null;
public ir.saelozahra.snd1400.selectmapact _selectmapact = null;
public ir.saelozahra.snd1400.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.snd1400.b4xcollections _b4xcollections = null;
public ir.saelozahra.snd1400.httputils2service _httputils2service = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 31;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return false;
}
public static String  _gps_locationchanged(anywheresoftware.b4a.gps.LocationWrapper _location1) throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub GPS_LocationChanged (Location1 As Location)";
 //BA.debugLineNum = 42;BA.debugLine="MyLocation = Location1";
_mylocation = _location1;
 //BA.debugLineNum = 44;BA.debugLine="If SaeloZahra.ActivePage ==\"selectmap\" Then";
if ((mostCurrent._saelozahra._activepage /*String*/ ).equals("selectmap")) { 
 //BA.debugLineNum = 46;BA.debugLine="CallSub(SelectMapACT,\"goToMyLocation\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,(Object)(mostCurrent._selectmapact.getObject()),"goToMyLocation");
 //BA.debugLineNum = 47;BA.debugLine="SelectMapACT.myLocation=MyLocation";
mostCurrent._selectmapact._mylocation /*anywheresoftware.b4a.gps.LocationWrapper*/  = _mylocation;
 //BA.debugLineNum = 49;BA.debugLine="File.WriteString(SaeloZahra.dir,\"lat\",Location1.";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"lat",BA.NumberToString(_location1.getLatitude()));
 //BA.debugLineNum = 50;BA.debugLine="File.WriteString(SaeloZahra.dir,\"lng\",Location1.";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"lng",BA.NumberToString(_location1.getLongitude()));
 };
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ir.saelozahra.snd1400.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
String _display_name = "";
anywheresoftware.b4a.objects.collections.Map _address = null;
String _postcode = "";
 //BA.debugLineNum = 80;BA.debugLine="Sub JobDone (Job As HttpJob)";
 //BA.debugLineNum = 82;BA.debugLine="Log(Job.JobName&\"  \"&Job.Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("6917506",_job._jobname /*String*/ +"  "+BA.ObjectToString(_job._success /*boolean*/ ),0);
 //BA.debugLineNum = 84;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 87;BA.debugLine="If Job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 88;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname /*String*/ ,"get_my_address")) {
case 0: {
 //BA.debugLineNum = 90;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 91;BA.debugLine="parser.Initialize(Job.GetString)";
_parser.Initialize(_job._getstring /*String*/ ());
 //BA.debugLineNum = 92;BA.debugLine="Dim root As Map = parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 93;BA.debugLine="Dim display_name As String = root.Get(\"display";
_display_name = BA.ObjectToString(_root.Get((Object)("display_name")));
 //BA.debugLineNum = 94;BA.debugLine="Dim address As Map = root.Get(\"address\")";
_address = new anywheresoftware.b4a.objects.collections.Map();
_address = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_root.Get((Object)("address"))));
 //BA.debugLineNum = 95;BA.debugLine="Dim postcode As String = address.Get(\"postcode";
_postcode = BA.ObjectToString(_address.Get((Object)("postcode")));
 //BA.debugLineNum = 97;BA.debugLine="File.WriteString(SaeloZahra.dir,\"postcode\",pos";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"postcode",_postcode);
 //BA.debugLineNum = 98;BA.debugLine="File.WriteString(SaeloZahra.dir,\"address\",disp";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"address",_display_name);
 //BA.debugLineNum = 99;BA.debugLine="CallSubDelayed2(SelectMapACT,\"change_addressba";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._selectmapact.getObject()),"change_addressbar_text",(Object)(_display_name));
 break; }
}
;
 }else {
 //BA.debugLineNum = 103;BA.debugLine="If Not(SaeloZahra.CheckConnection) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._saelozahra._checkconnection /*boolean*/ (processBA))) { 
 //BA.debugLineNum = 104;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\"اینترنت شما قط";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (processBA,"اینترنت شما قطع است").getObject()),anywheresoftware.b4a.keywords.Common.True);
 }else if(anywheresoftware.b4a.keywords.Common.Not(mostCurrent._saelozahra._checksite /*boolean*/ (processBA))) { 
 //BA.debugLineNum = 106;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\"اتصال به سایت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (processBA,"اتصال به سایت برقرار نشد").getObject()),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 108;BA.debugLine="Log(\"Error: \" & Job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("6917532","Error: "+_job._errormessage /*String*/ ,0);
 //BA.debugLineNum = 110;BA.debugLine="If Job.ErrorMessage.Contains(\"Unable to resolve";
if (_job._errormessage /*String*/ .contains("Unable to resolve host")) { 
 //BA.debugLineNum = 111;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\" اینترنتتون و";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (processBA," اینترنتتون وصل نیست ").getObject()),anywheresoftware.b4a.keywords.Common.True);
 }else if(_job._errormessage /*String*/ .contains("too_many_requests")) { 
 //BA.debugLineNum = 113;BA.debugLine="Job.Release";
_job._release /*String*/ ();
 }else {
 //BA.debugLineNum = 115;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\" خطا در ورود";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (processBA," خطا در ورود "))+anywheresoftware.b4a.keywords.Common.CRLF+_job._errormessage /*String*/ ),anywheresoftware.b4a.keywords.Common.True);
 };
 };
 };
 //BA.debugLineNum = 121;BA.debugLine="Job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Public GPS1 As GPS";
_gps1 = new anywheresoftware.b4a.gps.GPS();
 //BA.debugLineNum = 9;BA.debugLine="Public gpsStarted As Boolean";
_gpsstarted = false;
 //BA.debugLineNum = 10;BA.debugLine="Dim MyLocation As Location";
_mylocation = new anywheresoftware.b4a.gps.LocationWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Public RP As RuntimePermissions";
_rp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 37;BA.debugLine="StopGps";
_stopgps();
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 22;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _startgps() throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Public Sub StartGps";
 //BA.debugLineNum = 58;BA.debugLine="If gpsStarted = False Then";
if (_gpsstarted==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 59;BA.debugLine="GPS1.Start(0, 0)";
_gps1.Start(processBA,(long) (0),(float) (0));
 //BA.debugLineNum = 60;BA.debugLine="gpsStarted = True";
_gpsstarted = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _stopgps() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Public Sub StopGps";
 //BA.debugLineNum = 65;BA.debugLine="If gpsStarted Then";
if (_gpsstarted) { 
 //BA.debugLineNum = 66;BA.debugLine="GPS1.Stop";
_gps1.Stop();
 //BA.debugLineNum = 67;BA.debugLine="gpsStarted = False";
_gpsstarted = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
}
