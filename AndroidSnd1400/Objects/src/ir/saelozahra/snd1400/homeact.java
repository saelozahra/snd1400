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

public class homeact extends Activity implements B4AActivity{
	public static homeact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.homeact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (homeact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.homeact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.homeact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (homeact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (homeact) Resume **");
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
		return homeact.class;
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
            BA.LogInfo("** Activity (homeact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (homeact) Pause event (activity is not paused). **");
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
            homeact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (homeact) Resume **");
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
public static ir.saelozahra.snd1400.doubletaptoclose _dttc = null;
public static int _counter = 0;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public ir.hitexroid.material.x.Label _hometitlelbl = null;
public ir.hitexroid.material.x.Label _homebtn1 = null;
public ir.hitexroid.material.x.Label _homebtn2 = null;
public ir.hitexroid.material.x.Label _homebtn3 = null;
public ir.hitexroid.material.x.Hitex_Snackbar _snake = null;
public anywheresoftware.b4a.objects.EditTextWrapper _usernameet = null;
public anywheresoftware.b4a.objects.EditTextWrapper _passwordet = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2 _logindialog = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.starter _starter = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
long _diftime = 0L;
anywheresoftware.b4a.objects.CSBuilder _csbt = null;
 //BA.debugLineNum = 21;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"HomeLayout\")";
mostCurrent._activity.LoadLayout("HomeLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 25;BA.debugLine="Dim DifTime As Long = Round2((DateTime.DateParse(";
_diftime = (long) (anywheresoftware.b4a.keywords.Common.Round2((anywheresoftware.b4a.keywords.Common.DateTime.DateParse("06/18/2021")-anywheresoftware.b4a.keywords.Common.DateTime.getNow())/(double)1000/(double)60/(double)60/(double)24,(int) (0)));
 //BA.debugLineNum = 26;BA.debugLine="Dim CsbT As CSBuilder";
_csbt = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 27;BA.debugLine="CsbT.Initialize.Color(Colors.White).Typeface(Sael";
_csbt.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (60)).Append(BA.ObjectToCharSequence(_diftime)).Color((int) (0xfffce8df)).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (33)).Append(BA.ObjectToCharSequence(" روز"+anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (28)).Append(BA.ObjectToCharSequence(" تا انتخابات ریاست جمهوری 1400")).PopAll();
 //BA.debugLineNum = 28;BA.debugLine="HomeTitleLbl.Text=CsbT";
mostCurrent._hometitlelbl.setText(BA.ObjectToCharSequence(_csbt.getObject()));
 //BA.debugLineNum = 30;BA.debugLine="DTTC.InItIaLiZe(\"مجددا دکمه خروج را بزنید\")";
_dttc._initialize /*String*/ (processBA,"مجددا دکمه خروج را بزنید");
 //BA.debugLineNum = 32;BA.debugLine="Homebtn1.SetBackgroundImage(LoadBitmapResize(File";
mostCurrent._homebtn1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn1.png",mostCurrent._homebtn1.getWidth(),mostCurrent._homebtn1.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 33;BA.debugLine="Homebtn2.SetBackgroundImage(LoadBitmapResize(File";
mostCurrent._homebtn2.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn2.png",mostCurrent._homebtn2.getWidth(),mostCurrent._homebtn2.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 35;BA.debugLine="If File.Exists(SaeloZahra.Dir,\"melli\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"melli")) { 
 //BA.debugLineNum = 36;BA.debugLine="Homebtn3.SetBackgroundImage(LoadBitmapResize(Fil";
mostCurrent._homebtn3.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn33.png",mostCurrent._homebtn3.getWidth(),mostCurrent._homebtn3.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 }else {
 //BA.debugLineNum = 38;BA.debugLine="Homebtn3.SetBackgroundImage(LoadBitmapResize(Fil";
mostCurrent._homebtn3.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn3.png",mostCurrent._homebtn3.getWidth(),mostCurrent._homebtn3.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 };
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 77;BA.debugLine="Select KeyCode";
switch (BA.switchObjectToInt(_keycode,anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK)) {
case 0: {
 //BA.debugLineNum = 79;BA.debugLine="DTTC.TapToClose";
_dttc._taptoclose /*String*/ ();
 //BA.debugLineNum = 80;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
default: {
 //BA.debugLineNum = 82;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 break; }
}
;
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static void  _activity_touch(int _action,float _x,float _y) throws Exception{
ResumableSub_Activity_Touch rsub = new ResumableSub_Activity_Touch(null,_action,_x,_y);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Touch extends BA.ResumableSub {
public ResumableSub_Activity_Touch(ir.saelozahra.snd1400.homeact parent,int _action,float _x,float _y) {
this.parent = parent;
this._action = _action;
this._x = _x;
this._y = _y;
}
ir.saelozahra.snd1400.homeact parent;
int _action;
float _x;
float _y;
String _permission = "";
boolean _result = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 47;BA.debugLine="Log(\"Counter: \"&Counter&\"  Action: \"&Action&\" X:";
anywheresoftware.b4a.keywords.Common.LogImpl("61179651","Counter: "+BA.NumberToString(parent._counter)+"  Action: "+BA.NumberToString(_action)+" X: "+BA.NumberToString(_x)+" y: "+BA.NumberToString(_y),0);
 //BA.debugLineNum = 50;BA.debugLine="If Action == Activity.ACTION_DOWN Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_action==parent.mostCurrent._activity.ACTION_DOWN) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 51;BA.debugLine="Counter=0";
parent._counter = (int) (0);
 if (true) break;
;
 //BA.debugLineNum = 54;BA.debugLine="If Action == Activity.ACTION_MOVE Then";

case 4:
//if
this.state = 7;
if (_action==parent.mostCurrent._activity.ACTION_MOVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 55;BA.debugLine="Counter=Counter+Y";
parent._counter = (int) (parent._counter+_y);
 if (true) break;
;
 //BA.debugLineNum = 58;BA.debugLine="If Action == Activity.ACTION_UP Then";

case 7:
//if
this.state = 20;
if (_action==parent.mostCurrent._activity.ACTION_UP) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 59;BA.debugLine="If Counter > 12000 Then";
if (true) break;

case 10:
//if
this.state = 19;
if (parent._counter>12000) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 60;BA.debugLine="LogColor(\"Hi\",Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("61179664","Hi",anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 62;BA.debugLine="Starter.RP.CheckAndRequest(Starter.RP.PERMISSIO";
parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_CAMERA);
 //BA.debugLineNum = 63;BA.debugLine="Wait For Activity_PermissionResult (Permission";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 21;
return;
case 21:
//C
this.state = 13;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 64;BA.debugLine="If Not(Result) Then";
if (true) break;

case 13:
//if
this.state = 18;
if (anywheresoftware.b4a.keywords.Common.Not(_result)) { 
this.state = 15;
}else {
this.state = 17;
}if (true) break;

case 15:
//C
this.state = 18;
 //BA.debugLineNum = 65;BA.debugLine="ToastMessageShow(\"شما اجازه دسترسی به دوربین ر";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("شما اجازه دسترسی به دوربین را ندارید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 66;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 68;BA.debugLine="LoginKon";
_loginkon();
 if (true) break;

case 18:
//C
this.state = 19;
;
 if (true) break;

case 19:
//C
this.state = 20;
;
 if (true) break;

case 20:
//C
this.state = -1;
;
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim Counter As Int";
_counter = 0;
 //BA.debugLineNum = 12;BA.debugLine="Dim Show As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 13;BA.debugLine="Private HomeTitleLbl As Label";
mostCurrent._hometitlelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 14;BA.debugLine="Private Homebtn1,Homebtn2,Homebtn3 As Label";
mostCurrent._homebtn1 = new ir.hitexroid.material.x.Label();
mostCurrent._homebtn2 = new ir.hitexroid.material.x.Label();
mostCurrent._homebtn3 = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 15;BA.debugLine="Dim Snake As Hitex_Snackbar";
mostCurrent._snake = new ir.hitexroid.material.x.Hitex_Snackbar();
 //BA.debugLineNum = 17;BA.debugLine="Dim UserNameET,PasswordET As EditText";
mostCurrent._usernameet = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._passwordet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim LoginDialog As CustomDialog2";
mostCurrent._logindialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn1_click() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Private Sub Homebtn1_Click";
 //BA.debugLineNum = 102;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 103;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 104;BA.debugLine="StartActivity(NotificationAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._notificationact.getObject()));
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn2_click() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Private Sub Homebtn2_Click";
 //BA.debugLineNum = 96;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 97;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="StartActivity(CandidaListAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._candidalistact.getObject()));
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn3_click() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Private Sub Homebtn3_Click";
 //BA.debugLineNum = 108;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 109;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 111;BA.debugLine="If File.Exists(SaeloZahra.Dir,\"melli\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"melli")) { 
 //BA.debugLineNum = 112;BA.debugLine="StartActivity(QRForScanAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._qrforscanact.getObject()));
 }else {
 //BA.debugLineNum = 114;BA.debugLine="StartActivity(ElectionAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._electionact.getObject()));
 };
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _loginkon() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _plogin = null;
int _logindialogresult = 0;
 //BA.debugLineNum = 121;BA.debugLine="Sub LoginKon";
 //BA.debugLineNum = 122;BA.debugLine="Dim PLogin As Panel";
_plogin = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 123;BA.debugLine="PLogin.Initialize(\"PLogin\")";
_plogin.Initialize(mostCurrent.activityBA,"PLogin");
 //BA.debugLineNum = 124;BA.debugLine="UserNameET.Initialize(\"UserNameET\")";
mostCurrent._usernameet.Initialize(mostCurrent.activityBA,"UserNameET");
 //BA.debugLineNum = 125;BA.debugLine="UserNameET.InputType=UserNameET.INPUT_TYPE_PHONE";
mostCurrent._usernameet.setInputType(mostCurrent._usernameet.INPUT_TYPE_PHONE);
 //BA.debugLineNum = 126;BA.debugLine="UserNameET.Typeface=SaeloZahra.Font";
mostCurrent._usernameet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 127;BA.debugLine="UserNameET.Text = \"demo\"";
mostCurrent._usernameet.setText(BA.ObjectToCharSequence("demo"));
 //BA.debugLineNum = 128;BA.debugLine="UserNameET.Hint = \"شماره تماس\"";
mostCurrent._usernameet.setHint("شماره تماس");
 //BA.debugLineNum = 129;BA.debugLine="PLogin.AddView(UserNameET,5%x,5%x,60%x,14%x)";
_plogin.AddView((android.view.View)(mostCurrent._usernameet.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 131;BA.debugLine="PasswordET.Initialize(\"PasswordET\")";
mostCurrent._passwordet.Initialize(mostCurrent.activityBA,"PasswordET");
 //BA.debugLineNum = 132;BA.debugLine="PasswordET.Typeface=SaeloZahra.Font";
mostCurrent._passwordet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 133;BA.debugLine="PasswordET.Hint = \"کلمه عبور\"";
mostCurrent._passwordet.setHint("کلمه عبور");
 //BA.debugLineNum = 134;BA.debugLine="PasswordET.Text = \"demo\"";
mostCurrent._passwordet.setText(BA.ObjectToCharSequence("demo"));
 //BA.debugLineNum = 135;BA.debugLine="PasswordET.PasswordMode=True";
mostCurrent._passwordet.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 136;BA.debugLine="PLogin.AddView(PasswordET,5%x,25%x,60%x,14%x)";
_plogin.AddView((android.view.View)(mostCurrent._passwordet.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 138;BA.debugLine="If File.Exists(SaeloZahra.Dir,\"username\") And Fil";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"username") && anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"password")) { 
 //BA.debugLineNum = 139;BA.debugLine="UserNameET.Text= File.ReadString(SaeloZahra.Dir,";
mostCurrent._usernameet.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"username")));
 //BA.debugLineNum = 140;BA.debugLine="PasswordET.Text= File.ReadString(SaeloZahra.Dir,";
mostCurrent._passwordet.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"password")));
 };
 //BA.debugLineNum = 143;BA.debugLine="LoginDialog.AddView(PLogin,72%x,45%x)";
mostCurrent._logindialog.AddView((android.view.View)(_plogin.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (72),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA));
 //BA.debugLineNum = 144;BA.debugLine="Dim loginDialogResult As Int = LoginDialog.Show(";
_logindialogresult = mostCurrent._logindialog.Show(BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ورود به بخش مجریان")),BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ورود")),BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"لغو")),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 145;BA.debugLine="If loginDialogResult == DialogResponse.POSITIVE T";
if (_logindialogresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 156;BA.debugLine="If UserNameET.Text==\"demo\" And PasswordET.Text==";
if ((mostCurrent._usernameet.getText()).equals("demo") && (mostCurrent._passwordet.getText()).equals("demo")) { 
 //BA.debugLineNum = 157;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 158;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 159;BA.debugLine="StartActivity(AdminsAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._adminsact.getObject()));
 }else {
 //BA.debugLineNum = 161;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"نام ک";
mostCurrent._snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._activity.getObject())),BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"نام کاربری یا کلمه عبور غلط است").getObject()),mostCurrent._snake.LENGTH_LONG);
 //BA.debugLineNum = 162;BA.debugLine="Snake.Show";
mostCurrent._snake.Show();
 };
 };
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim DTTC As DoubleTaptoClose";
_dttc = new ir.saelozahra.snd1400.doubletaptoclose();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
