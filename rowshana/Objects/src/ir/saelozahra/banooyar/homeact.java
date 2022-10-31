package ir.saelozahra.banooyar;


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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.homeact");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.homeact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.banooyar.homeact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
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
public static ir.saelozahra.banooyar.doubletaptoclose _dttc = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public ir.hitexroid.material.x.Label _hometitlelbl = null;
public ir.hitexroid.material.x.Label _homebtn1 = null;
public ir.hitexroid.material.x.Label _homebtn2 = null;
public ir.hitexroid.material.x.Label _homebtn3 = null;
public ir.hitexroid.material.x.Label _homebtn4 = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.banooyar.main _main = null;
public ir.saelozahra.banooyar.contentlistact _contentlistact = null;
public ir.saelozahra.banooyar.electionact _electionact = null;
public ir.saelozahra.banooyar.firebasemessaging _firebasemessaging = null;
public ir.saelozahra.banooyar.notificationact _notificationact = null;
public ir.saelozahra.banooyar.mapact _mapact = null;
public ir.saelozahra.banooyar.translateact _translateact = null;
public ir.saelozahra.banooyar.saelozahra _saelozahra = null;
public ir.saelozahra.banooyar.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.banooyar.selectmapact _selectmapact = null;
public ir.saelozahra.banooyar.starter _starter = null;
public ir.saelozahra.banooyar.b4xcollections _b4xcollections = null;
public ir.saelozahra.banooyar.httputils2service _httputils2service = null;

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
 //BA.debugLineNum = 17;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 19;BA.debugLine="Activity.LoadLayout(\"HomeLayout\")";
mostCurrent._activity.LoadLayout("HomeLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 21;BA.debugLine="Dim DifTime As Long = Round2((DateTime.DateParse(";
_diftime = (long) (anywheresoftware.b4a.keywords.Common.Round2((anywheresoftware.b4a.keywords.Common.DateTime.DateParse("09/27/2021")-anywheresoftware.b4a.keywords.Common.DateTime.getNow())/(double)1000/(double)60/(double)60/(double)24,(int) (0)));
 //BA.debugLineNum = 22;BA.debugLine="Dim CsbT As CSBuilder";
_csbt = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 23;BA.debugLine="CsbT.Initialize.Color(Colors.White).Typeface(Sael";
_csbt.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (60)).Append(BA.ObjectToCharSequence(_diftime)).Color((int) (0xfffce8df)).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (33)).Append(BA.ObjectToCharSequence(" روز"+anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (28)).Append(BA.ObjectToCharSequence(" تا اربعین حسینی")).PopAll();
 //BA.debugLineNum = 24;BA.debugLine="HomeTitleLbl.Text=CsbT";
mostCurrent._hometitlelbl.setText(BA.ObjectToCharSequence(_csbt.getObject()));
 //BA.debugLineNum = 26;BA.debugLine="DTTC.InItIaLiZe(\"مجددا دکمه خروج را بزنید\")";
_dttc._initialize /*String*/ (processBA,"مجددا دکمه خروج را بزنید");
 //BA.debugLineNum = 28;BA.debugLine="Homebtn1.SetBackgroundImage(LoadBitmapResize(File";
mostCurrent._homebtn1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn1.png",mostCurrent._homebtn1.getWidth(),mostCurrent._homebtn1.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 29;BA.debugLine="Homebtn2.SetBackgroundImage(LoadBitmapResize(File";
mostCurrent._homebtn2.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn2.png",mostCurrent._homebtn2.getWidth(),mostCurrent._homebtn2.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 30;BA.debugLine="Homebtn3.SetBackgroundImage(LoadBitmapResize(File";
mostCurrent._homebtn3.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn3.png",mostCurrent._homebtn3.getWidth(),mostCurrent._homebtn3.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 31;BA.debugLine="HomeBTN4.SetBackgroundImage(LoadBitmapResize(File";
mostCurrent._homebtn4.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"btn4.png",mostCurrent._homebtn4.getWidth(),mostCurrent._homebtn4.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 37;BA.debugLine="Select KeyCode";
switch (BA.switchObjectToInt(_keycode,anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK)) {
case 0: {
 //BA.debugLineNum = 39;BA.debugLine="DTTC.TapToClose";
_dttc._taptoclose /*String*/ ();
 //BA.debugLineNum = 40;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
default: {
 //BA.debugLineNum = 42;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 break; }
}
;
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim Show As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 12;BA.debugLine="Private HomeTitleLbl As Label";
mostCurrent._hometitlelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 13;BA.debugLine="Private Homebtn1,Homebtn2,Homebtn3,HomeBTN4 As La";
mostCurrent._homebtn1 = new ir.hitexroid.material.x.Label();
mostCurrent._homebtn2 = new ir.hitexroid.material.x.Label();
mostCurrent._homebtn3 = new ir.hitexroid.material.x.Label();
mostCurrent._homebtn4 = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn1_click() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Private Sub Homebtn1_Click";
 //BA.debugLineNum = 56;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 57;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 58;BA.debugLine="StartActivity(NotificationAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._notificationact.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn2_click() throws Exception{
 //BA.debugLineNum = 61;BA.debugLine="Private Sub Homebtn2_Click";
 //BA.debugLineNum = 62;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 63;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 64;BA.debugLine="StartActivity(ContentListAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._contentlistact.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn3_click() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Private Sub Homebtn3_Click";
 //BA.debugLineNum = 68;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 69;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 70;BA.debugLine="StartActivity(MapAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._mapact.getObject()));
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _homebtn4_click() throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Private Sub Homebtn4_Click";
 //BA.debugLineNum = 74;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 75;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 76;BA.debugLine="StartActivity(ElectionAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._electionact.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim DTTC As DoubleTaptoClose";
_dttc = new ir.saelozahra.banooyar.doubletaptoclose();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
