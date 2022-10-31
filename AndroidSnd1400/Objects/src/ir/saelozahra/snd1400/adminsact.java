package ir.saelozahra.snd1400;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class adminsact extends Activity implements B4AActivity{
	public static adminsact mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.adminsact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (adminsact).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.adminsact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.adminsact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (adminsact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (adminsact) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return adminsact.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (adminsact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (adminsact) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            adminsact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (adminsact) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Timer _t = null;
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public anywheresoftware.b4a.objects.CameraW _camera = null;
public ir.hitexroid.material.x.Label _mosaicbglbl = null;
public ir.hitexroid.material.x.Label _titlelbl = null;
public anywheresoftware.b4a.objects.PanelWrapper _camerapanel = null;
public com.AB.ABZxing.ABZxing _ab = null;
public anywheresoftware.b4a.objects.ButtonWrapper _backbtn = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xml = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2 _aghlamdialog = null;
public ir.hitexroid.material.x.Label _btnreqthing = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.starter _starter = null;
public ir.saelozahra.snd1400.homeact _homeact = null;
public ir.saelozahra.snd1400.saelozahra _saelozahra = null;
public ir.saelozahra.snd1400.candidalistact _candidalistact = null;
public ir.saelozahra.snd1400.electionact _electionact = null;
public ir.saelozahra.snd1400.firebasemessaging _firebasemessaging = null;
public ir.saelozahra.snd1400.notificationact _notificationact = null;
public ir.saelozahra.snd1400.qrforscanact _qrforscanact = null;
public ir.saelozahra.snd1400.selectmapact _selectmapact = null;
public ir.saelozahra.snd1400.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.snd1400.b4xcollections _b4xcollections = null;
public ir.saelozahra.snd1400.httputils2service _httputils2service = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_click() throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneIntents _pi = null;
boolean _ok = false;
anywheresoftware.b4a.phone.PackageManagerWrapper _pm = null;
anywheresoftware.b4a.objects.collections.List _packages = null;
int _i = 0;
String _sss = "";
 //BA.debugLineNum = 135;BA.debugLine="Sub Activity_Click";
 //BA.debugLineNum = 136;BA.debugLine="Log(\"Activity_Click\")";
anywheresoftware.b4a.keywords.Common.LogImpl("64325377","Activity_Click",0);
 //BA.debugLineNum = 137;BA.debugLine="Dim PI As PhoneIntents";
_pi = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 138;BA.debugLine="Dim Ok As Boolean";
_ok = false;
 //BA.debugLineNum = 139;BA.debugLine="Dim pm As PackageManager";
_pm = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 140;BA.debugLine="Dim packages As List";
_packages = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 141;BA.debugLine="packages = pm.GetInstalledPackages";
_packages = _pm.GetInstalledPackages();
 //BA.debugLineNum = 142;BA.debugLine="For i = 0 To packages.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_packages.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 143;BA.debugLine="Dim sss As String = packages.Get(i)";
_sss = BA.ObjectToString(_packages.Get(_i));
 //BA.debugLineNum = 144;BA.debugLine="If sss.Contains(\"com.google.zxing.client.android";
if (_sss.contains("com.google.zxing.client.android")) { 
 //BA.debugLineNum = 145;BA.debugLine="Ok = True";
_ok = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 146;BA.debugLine="Log(packages.Get(i))";
anywheresoftware.b4a.keywords.Common.LogImpl("64325387",BA.ObjectToString(_packages.Get(_i)),0);
 };
 }
};
 //BA.debugLineNum = 150;BA.debugLine="If Ok Then";
if (_ok) { 
 //BA.debugLineNum = 151;BA.debugLine="ab.ABGetBarcode(\"qr\",\"QR_CODE_TYPES\")";
mostCurrent._ab.ABGetBarcode(mostCurrent.activityBA,"qr","QR_CODE_TYPES");
 }else {
 //BA.debugLineNum = 153;BA.debugLine="StartActivity (PI.OpenBrowser(\"https://play.goog";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_pi.OpenBrowser("https://play.google.com/store/apps/details?id=com.google.zxing.client.android")));
 };
 //BA.debugLineNum = 156;BA.debugLine="Log(\"Activity_Click shod\")";
anywheresoftware.b4a.keywords.Common.LogImpl("64325397","Activity_Click shod",0);
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(ir.saelozahra.snd1400.adminsact parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
ir.saelozahra.snd1400.adminsact parent;
boolean _firsttime;
String _permission = "";
boolean _result = false;
ir.hitexroid.material.x.Label _lblhover = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout(\"AdminsLayout\")";
parent.mostCurrent._activity.LoadLayout("AdminsLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 28;BA.debugLine="BackBtn.Color=Colors.Transparent";
parent.mostCurrent._backbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 31;BA.debugLine="Starter.RP.CheckAndRequest(Starter.RP.PERMISSION_";
parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_CAMERA);
 //BA.debugLineNum = 32;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 11;
return;
case 11:
//C
this.state = 1;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 33;BA.debugLine="If Not(Result) Then";
if (true) break;

case 1:
//if
this.state = 6;
if (anywheresoftware.b4a.keywords.Common.Not(_result)) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 34;BA.debugLine="ToastMessageShow(\"شما اجازه دسترسی به دوربین را";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("شما اجازه دسترسی به دوربین را ندارید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 35;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 38;BA.debugLine="Camera.Initialize(CameraPanel,\"camera\")";
parent.mostCurrent._camera.Initialize(mostCurrent.activityBA,(android.view.ViewGroup)(parent.mostCurrent._camerapanel.getObject()),"camera");
 //BA.debugLineNum = 40;BA.debugLine="Dim LblHover As Label";
_lblhover = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 41;BA.debugLine="LblHover.Initialize(\"LblHover\")";
_lblhover.Initialize(processBA,"LblHover");
 //BA.debugLineNum = 42;BA.debugLine="LblHover.SetBackgroundImage(LoadBitmapResize(Fil";
_lblhover.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"camera.png",parent.mostCurrent._camerapanel.getWidth(),parent.mostCurrent._camerapanel.getHeight(),anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 43;BA.debugLine="CameraPanel.AddView(LblHover,0,0,-2,-2)";
parent.mostCurrent._camerapanel.AddView((android.view.View)(_lblhover.getObject()),(int) (0),(int) (0),(int) (-2),(int) (-2));
 //BA.debugLineNum = 44;BA.debugLine="SaeloZahra.SetCornerRadii(CameraPanel,14dip,14di";
parent.mostCurrent._saelozahra._setcornerradii /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._camerapanel.getObject())),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14))));
 if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 48;BA.debugLine="BtnReqThing.SetBackgroundImage(LoadBitmapResize(F";
parent.mostCurrent._btnreqthing.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"thing_for_shoabe.png",parent.mostCurrent._btnreqthing.getWidth(),parent.mostCurrent._btnreqthing.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 50;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (true) break;

case 7:
//if
this.state = 10;
if (parent.mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 51;BA.debugLine="Config.Initialize";
parent.mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 52;BA.debugLine="Config.position(Config.POSITION_LEFT)";
parent.mostCurrent._config.position(parent.mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 53;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
parent.mostCurrent._config.primaryColor(parent.mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 54;BA.debugLine="Config.edge(True)";
parent.mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 55;BA.debugLine="Config.sensitivity(100)";
parent.mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 56;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
parent.mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 58;BA.debugLine="Show.convertActivityToTranslucent";
parent.mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 59;BA.debugLine="Show.attachActivity(Config)";
parent.mostCurrent._show.attachActivity(mostCurrent.activityBA,parent.mostCurrent._config);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 62;BA.debugLine="T.Initialize(\"t\",1000)";
parent._t.Initialize(processBA,"t",(long) (1000));
 //BA.debugLineNum = 63;BA.debugLine="T.Enabled=True";
parent._t.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 65;BA.debugLine="ToastMessageShow(\"برای شناسایی فرد روی تصویر کلیک";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("برای شناسایی فرد روی تصویر کلیک کنید"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 106;BA.debugLine="Select KeyCode";
switch (BA.switchObjectToInt(_keycode,anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK)) {
case 0: {
 //BA.debugLineNum = 108;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 109;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
default: {
 //BA.debugLineNum = 111;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 break; }
}
;
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 96;BA.debugLine="Try";
try { //BA.debugLineNum = 97;BA.debugLine="Camera.StopPreview";
mostCurrent._camera.StopPreview();
 //BA.debugLineNum = 98;BA.debugLine="Camera.Release";
mostCurrent._camera.Release();
 } 
       catch (Exception e5) {
			processBA.setLastException(e5); //BA.debugLineNum = 100;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("63997702",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 175;BA.debugLine="Private Sub BackBtn_Click";
 //BA.debugLineNum = 176;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _btnreqthing_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _plogin = null;
de.amberhome.objects.appcompat.ACSpinnerWrapper _namespinn = null;
anywheresoftware.b4a.objects.EditTextWrapper _tedadet = null;
int _aghlamdialogresult = 0;
ir.hitexroid.material.x.Hitex_Snackbar _snake = null;
 //BA.debugLineNum = 182;BA.debugLine="Sub BtnReqThing_click";
 //BA.debugLineNum = 183;BA.debugLine="Dim PLogin As Panel";
_plogin = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 184;BA.debugLine="PLogin.Initialize(\"PLogin\")";
_plogin.Initialize(mostCurrent.activityBA,"PLogin");
 //BA.debugLineNum = 186;BA.debugLine="Dim NameSpinn As ACSpinner";
_namespinn = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 187;BA.debugLine="NameSpinn.Initialize(\"NameSpinn\")";
_namespinn.Initialize(mostCurrent.activityBA,"NameSpinn");
 //BA.debugLineNum = 188;BA.debugLine="NameSpinn.Add2(SaeloZahra.CSB(\"تعرفه رای‌دهی\"),XM";
_namespinn.Add2(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"تعرفه رای‌دهی").getObject()),mostCurrent._xml.GetDrawable("baseline_text_snippet_white_24"));
 //BA.debugLineNum = 189;BA.debugLine="NameSpinn.Add2(SaeloZahra.CSB(\"مواد ضد عفونی کنند";
_namespinn.Add2(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"مواد ضد عفونی کننده").getObject()),mostCurrent._xml.GetDrawable("baseline_coronavirus_black_36dp"));
 //BA.debugLineNum = 190;BA.debugLine="NameSpinn.Add2(SaeloZahra.CSB(\"لوازم تحریر و استا";
_namespinn.Add2(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"لوازم تحریر و استامپ").getObject()),mostCurrent._xml.GetDrawable("round_search_white_24"));
 //BA.debugLineNum = 191;BA.debugLine="PLogin.AddView(NameSpinn,5%x,5%x,60%x,14%x)";
_plogin.AddView((android.view.View)(_namespinn.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 193;BA.debugLine="Dim TedadET As EditText";
_tedadet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 194;BA.debugLine="TedadET.Initialize(\"TedadET\")";
_tedadet.Initialize(mostCurrent.activityBA,"TedadET");
 //BA.debugLineNum = 195;BA.debugLine="TedadET.InputType=TedadET.INPUT_TYPE_NUMBERS";
_tedadet.setInputType(_tedadet.INPUT_TYPE_NUMBERS);
 //BA.debugLineNum = 196;BA.debugLine="TedadET.Typeface=SaeloZahra.Font";
_tedadet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 197;BA.debugLine="TedadET.Text = \"12\"";
_tedadet.setText(BA.ObjectToCharSequence("12"));
 //BA.debugLineNum = 198;BA.debugLine="TedadET.Hint = \"چه تعدادی میخواین؟\"";
_tedadet.setHint("چه تعدادی میخواین؟");
 //BA.debugLineNum = 199;BA.debugLine="PLogin.AddView(TedadET,5%x,25%x,60%x,14%x)";
_plogin.AddView((android.view.View)(_tedadet.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 202;BA.debugLine="AghlamDialog.AddView(PLogin,72%x,45%x)";
mostCurrent._aghlamdialog.AddView((android.view.View)(_plogin.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (72),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA));
 //BA.debugLineNum = 203;BA.debugLine="Dim AghlamDialogResult As Int = AghlamDialog.Show";
_aghlamdialogresult = mostCurrent._aghlamdialog.Show(BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"چه چیزی برای شعبه نیاز دارید؟")),BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ثبت درخواست")),"","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 204;BA.debugLine="If AghlamDialogResult == DialogResponse.POSITIVE";
if (_aghlamdialogresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 205;BA.debugLine="Dim Snake As Hitex_Snackbar";
_snake = new ir.hitexroid.material.x.Hitex_Snackbar();
 //BA.debugLineNum = 206;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"درخواس";
_snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._activity.getObject())),BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"درخواست شما ثبت شد").getObject()),_snake.LENGTH_LONG);
 //BA.debugLineNum = 207;BA.debugLine="Snake.Show";
_snake.Show();
 };
 //BA.debugLineNum = 209;BA.debugLine="End Sub";
return "";
}
public static String  _camera_ready(boolean _success) throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Sub camera_Ready (Success As Boolean)";
 //BA.debugLineNum = 123;BA.debugLine="Try";
try { //BA.debugLineNum = 124;BA.debugLine="If Success Then Camera.StartPreview";
if (_success) { 
mostCurrent._camera.StartPreview();};
 } 
       catch (Exception e4) {
			processBA.setLastException(e4); //BA.debugLineNum = 126;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("64194309",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim Config 	As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 12;BA.debugLine="Dim Show 	As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 13;BA.debugLine="Dim Camera  As Camera";
mostCurrent._camera = new anywheresoftware.b4a.objects.CameraW();
 //BA.debugLineNum = 14;BA.debugLine="Private MosaicBgLbl As Label";
mostCurrent._mosaicbglbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 15;BA.debugLine="Private TitleLbl As Label";
mostCurrent._titlelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 16;BA.debugLine="Private CameraPanel As Panel";
mostCurrent._camerapanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim ab As ABZxing";
mostCurrent._ab = new com.AB.ABZxing.ABZxing();
 //BA.debugLineNum = 18;BA.debugLine="Private BackBtn As Button";
mostCurrent._backbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim XML As XmlLayoutBuilder";
mostCurrent._xml = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 20;BA.debugLine="Dim AghlamDialog As CustomDialog2";
mostCurrent._aghlamdialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2();
 //BA.debugLineNum = 21;BA.debugLine="Private BtnReqThing As Label";
mostCurrent._btnreqthing = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _lblhover_click() throws Exception{
 //BA.debugLineNum = 131;BA.debugLine="Sub LblHover_Click";
 //BA.debugLineNum = 132;BA.debugLine="Activity_Click";
_activity_click();
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim T As Timer";
_t = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _qr_barcodefound(String _barcode,String _formatname) throws Exception{
 //BA.debugLineNum = 159;BA.debugLine="Sub qr_BarcodeFound (barCode As String, formatName";
 //BA.debugLineNum = 161;BA.debugLine="Log(barCode)";
anywheresoftware.b4a.keywords.Common.LogImpl("64390914",_barcode,0);
 //BA.debugLineNum = 162;BA.debugLine="Log(formatName)";
anywheresoftware.b4a.keywords.Common.LogImpl("64390915",_formatname,0);
 //BA.debugLineNum = 164;BA.debugLine="Log(formatName&\":  \"&barCode)";
anywheresoftware.b4a.keywords.Common.LogImpl("64390917",_formatname+":  "+_barcode,0);
 //BA.debugLineNum = 166;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\"کدملی \"&barCode&";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"کدملی "+_barcode+" در این ساعت اجازه ورود به شعبه را دارد").getObject()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _qr_canceled() throws Exception{
 //BA.debugLineNum = 170;BA.debugLine="Sub qr_Canceled()";
 //BA.debugLineNum = 171;BA.debugLine="ToastMessageShow(\"لغو شد\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لغو شد"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _t_tick() throws Exception{
int _timetoclean = 0;
int _sectoclean = 0;
anywheresoftware.b4a.objects.CSBuilder _csbt = null;
 //BA.debugLineNum = 69;BA.debugLine="Sub t_Tick";
 //BA.debugLineNum = 71;BA.debugLine="Dim TimeToClean As Int";
_timetoclean = 0;
 //BA.debugLineNum = 72;BA.debugLine="If DateTime.GetMinute(DateTime.Now)>30 Then";
if (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow())>30) { 
 //BA.debugLineNum = 73;BA.debugLine="TimeToClean = 60-DateTime.GetMinute(DateTime.Now";
_timetoclean = (int) (60-anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 }else {
 //BA.debugLineNum = 75;BA.debugLine="TimeToClean = 30-DateTime.GetMinute(DateTime.Now";
_timetoclean = (int) (30-anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 };
 //BA.debugLineNum = 78;BA.debugLine="Dim SecToClean As Int";
_sectoclean = 0;
 //BA.debugLineNum = 79;BA.debugLine="SecToClean = 60-DateTime.GetSecond(DateTime.Now)";
_sectoclean = (int) (60-anywheresoftware.b4a.keywords.Common.DateTime.GetSecond(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 82;BA.debugLine="Dim CsbT As CSBuilder";
_csbt = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 83;BA.debugLine="CsbT.Initialize.Color(Colors.White).Typeface(Sael";
_csbt.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (60)).Append(BA.ObjectToCharSequence(BA.NumberToString(_timetoclean)+":"+BA.NumberToString(_sectoclean))).Color((int) (0xfffce8df)).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (33)).Append(BA.ObjectToCharSequence(" دقیقه"+anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (28)).Append(BA.ObjectToCharSequence(" تا ضدعفونی مجدد شعبه")).PopAll();
 //BA.debugLineNum = 84;BA.debugLine="TitleLbl.Text=CsbT";
mostCurrent._titlelbl.setText(BA.ObjectToCharSequence(_csbt.getObject()));
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 115;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 116;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 117;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
}
