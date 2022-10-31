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

public class electionact extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static electionact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.electionact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (electionact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.electionact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.electionact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (electionact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (electionact) Resume **");
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
		return electionact.class;
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
            BA.LogInfo("** Activity (electionact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (electionact) Pause event (activity is not paused). **");
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
            electionact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (electionact) Resume **");
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
public de.amberhome.objects.appcompat.AppCompatBase _ac = null;
public de.amberhome.objects.appcompat.ACToolbarLightWrapper _toolbar = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.hitexroid.material.x.Label _ltel = null;
public ir.hitexroid.material.x.Label _lmelli = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.IME _ime1 = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xml = null;
public ir.hitexroid.material.x.Hitex_Snackbar _snake = null;
public com.jokar.materialdaterangepicker.wrapper.TimePickerDialogWrapper _time = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _selecttimebtn = null;
public anywheresoftware.b4a.objects.EditTextWrapper _timeet = null;
public anywheresoftware.b4a.objects.EditTextWrapper _telet = null;
public anywheresoftware.b4a.objects.EditTextWrapper _melliet = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m1et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m2et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m3et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m4et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m5et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m6et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m7et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m8et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m9et = null;
public anywheresoftware.b4a.objects.EditTextWrapper _m10et = null;
public ir.hitexroid.material.x.Label _cmelli1 = null;
public ir.hitexroid.material.x.Label _cmelli2 = null;
public ir.hitexroid.material.x.Label _cmelli3 = null;
public ir.hitexroid.material.x.Label _cmelli4 = null;
public ir.hitexroid.material.x.Label _cmelli5 = null;
public ir.hitexroid.material.x.Label _cmelli6 = null;
public ir.hitexroid.material.x.Label _cmelli7 = null;
public ir.hitexroid.material.x.Label _cmelli8 = null;
public ir.hitexroid.material.x.Label _cmelli9 = null;
public ir.hitexroid.material.x.Label _cmelli10 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _selectlocationbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _panellocation = null;
public anywheresoftware.b4a.objects.ButtonWrapper _savebtn = null;
public ir.hitexroid.material.x.Label _llocation = null;
public ir.hitexroid.material.x.Label _ldatetime = null;
public ir.hitexroid.material.x.Label _lhamrah = null;
public ir.hitexroid.material.x.Label _lsayar = null;
public ir.hitexroid.material.x.Label _sayarjavablbl = null;
public de.amberhome.objects.appcompat.ACSwitchCompatWrapper _sayarswitch = null;
public ir.hitexroid.material.x.Label _sayerdalillbl = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _sayardalilspinner = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelsayar = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelsayardalil = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelhamrah = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _hamrahspinner = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.starter _starter = null;
public ir.saelozahra.snd1400.homeact _homeact = null;
public ir.saelozahra.snd1400.saelozahra _saelozahra = null;
public ir.saelozahra.snd1400.adminsact _adminsact = null;
public ir.saelozahra.snd1400.candidalistact _candidalistact = null;
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
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="IME1.Initialize(\"IME1\")";
mostCurrent._ime1.Initialize("IME1");
 //BA.debugLineNum = 49;BA.debugLine="IME1.AddHeightChangedEvent";
mostCurrent._ime1.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 51;BA.debugLine="Activity.LoadLayout(\"SVLayout\")";
mostCurrent._activity.LoadLayout("SVLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 52;BA.debugLine="ScrollView1.Panel.LoadLayout(\"ElectionLayout\")";
mostCurrent._scrollview1.getPanel().LoadLayout("ElectionLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 54;BA.debugLine="SaeloZahra.SetToolbarStyle(ToolBar,\"ثبت اطلاعات ف";
mostCurrent._saelozahra._settoolbarstyle /*String*/ (mostCurrent.activityBA,mostCurrent._toolbar,"ثبت اطلاعات فردی",anywheresoftware.b4a.keywords.Common.True,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._scrollview1.getObject())));
 //BA.debugLineNum = 56;BA.debugLine="SetLayout";
_setlayout();
 //BA.debugLineNum = 57;BA.debugLine="SaeloZahra.SetStatusBarColor(0xFF493B34)";
mostCurrent._saelozahra._setstatusbarcolor /*String*/ (mostCurrent.activityBA,(int) (0xff493b34));
 //BA.debugLineNum = 60;BA.debugLine="ToolBar.Menu.Add2(2,2,\"ورود ناظران\",XML.GetDrawab";
mostCurrent._toolbar.getMenu().Add2((int) (2),(int) (2),BA.ObjectToCharSequence("ورود ناظران"),mostCurrent._xml.GetDrawable("round_login_white_24")).setShowAsAction((int) (2));
 //BA.debugLineNum = 61;BA.debugLine="ToolBar.Padding=Array As Int(0,1dip,0,0dip)";
mostCurrent._toolbar.setPadding(new int[]{(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 62;BA.debugLine="ToolBar.Height = SaeloZahra.MaterialActionBarHeig";
mostCurrent._toolbar.setHeight(mostCurrent._saelozahra._materialactionbarheight /*int*/ );
 //BA.debugLineNum = 64;BA.debugLine="time.Initialize(\"Time\",13, 0, 14, 0, True)";
mostCurrent._time.Initialize(mostCurrent.activityBA,"Time",(int) (13),(int) (0),(int) (14),(int) (0),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 65;BA.debugLine="time.CancelButtonText = \"انصراف\"";
mostCurrent._time.setCancelButtonText("انصراف");
 //BA.debugLineNum = 66;BA.debugLine="time.OkButtonText = \"تایید\"";
mostCurrent._time.setOkButtonText("تایید");
 //BA.debugLineNum = 67;BA.debugLine="time.StartTitle = \"از\"";
mostCurrent._time.setStartTitle("از");
 //BA.debugLineNum = 68;BA.debugLine="time.EndTitle = \"تا\"";
mostCurrent._time.setEndTitle("تا");
 //BA.debugLineNum = 69;BA.debugLine="time.Typeface = SaeloZahra.Font";
mostCurrent._time.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 70;BA.debugLine="time.AmText = \"ق.ظ\"";
mostCurrent._time.setAmText("ق.ظ");
 //BA.debugLineNum = 71;BA.debugLine="time.PmText = \"ب.ظ\"";
mostCurrent._time.setPmText("ب.ظ");
 //BA.debugLineNum = 72;BA.debugLine="time.AccentColor = SaeloZahra.ColorDark";
mostCurrent._time.setAccentColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 88;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
 //BA.debugLineNum = 89;BA.debugLine="Config.Initialize";
mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 90;BA.debugLine="Config.position(Config.POSITION_LEFT)";
mostCurrent._config.position(mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 91;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
mostCurrent._config.primaryColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 92;BA.debugLine="Config.edge(True)";
mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 93;BA.debugLine="Config.sensitivity(100)";
mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 94;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 95;BA.debugLine="Show.convertActivityToTranslucent";
mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 96;BA.debugLine="Show.attachActivity(Config)";
mostCurrent._show.attachActivity(mostCurrent.activityBA,mostCurrent._config);
 };
 //BA.debugLineNum = 99;BA.debugLine="AC.SetElevation(SaveBtn,7dip)";
mostCurrent._ac.SetElevation((android.view.View)(mostCurrent._savebtn.getObject()),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 //BA.debugLineNum = 101;BA.debugLine="ScrollView1.SetLayout(0,ToolBar.Height,100%x,100%";
mostCurrent._scrollview1.SetLayout((int) (0),mostCurrent._toolbar.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._saelozahra._materialactionbarheight /*int*/ ));
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 234;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 235;BA.debugLine="Select KeyCode";
switch (BA.switchObjectToInt(_keycode,anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK)) {
case 0: {
 //BA.debugLineNum = 237;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 238;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
default: {
 //BA.debugLineNum = 240;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 break; }
}
;
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csbadd = null;
 //BA.debugLineNum = 123;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 125;BA.debugLine="If Not(SaeloZahra.CheckConnection) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._saelozahra._checkconnection /*boolean*/ (mostCurrent.activityBA))) { 
 //BA.debugLineNum = 126;BA.debugLine="ToastMessageShow( SaeloZahra.CSB(\"حتما به اینترن";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"حتما به اینترنت متصل شوید").getObject()),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 129;BA.debugLine="LLocation.Text = SaeloZahra.CSB(\"موقعیت مکانی خود";
mostCurrent._llocation.setText(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"موقعیت مکانی خود را برای پیدا کردن نزدیک ترین شعبه خلوت، وارد کنید.").getObject()));
 //BA.debugLineNum = 130;BA.debugLine="If File.Exists(SaeloZahra.dir,\"address\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"address")) { 
 //BA.debugLineNum = 131;BA.debugLine="Dim CsbAdd As CSBuilder";
_csbadd = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 132;BA.debugLine="CsbAdd.Initialize.Append(LLocation.Text).Append(";
_csbadd.Initialize().Append(BA.ObjectToCharSequence(mostCurrent._llocation.getText())).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (10)).Color(mostCurrent._saelozahra._coloraccent /*int*/ ).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"address"))).PopAll();
 //BA.debugLineNum = 133;BA.debugLine="LLocation.Text = CsbAdd";
mostCurrent._llocation.setText(BA.ObjectToCharSequence(_csbadd.getObject()));
 };
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim AC As AppCompat";
mostCurrent._ac = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 13;BA.debugLine="Private ToolBar As ACToolBarLight";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarLightWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Dim Show As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 15;BA.debugLine="Dim Config As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 16;BA.debugLine="Private LTel As Label";
mostCurrent._ltel = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 17;BA.debugLine="Private LMelli As Label";
mostCurrent._lmelli = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 18;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim IME1 As IME";
mostCurrent._ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 20;BA.debugLine="Dim XML As XmlLayoutBuilder";
mostCurrent._xml = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 21;BA.debugLine="Dim Snake As Hitex_Snackbar";
mostCurrent._snake = new ir.hitexroid.material.x.Hitex_Snackbar();
 //BA.debugLineNum = 23;BA.debugLine="Dim time As JK_MaterialTimePicker";
mostCurrent._time = new com.jokar.materialdaterangepicker.wrapper.TimePickerDialogWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private SelectTimeBtn As ACButton";
mostCurrent._selecttimebtn = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private TimeEt,TelEt,MelliET As EditText";
mostCurrent._timeet = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._telet = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._melliet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private M1ET,M2ET,M3ET,M4ET,M5ET,M6ET,M7ET,M8ET,M";
mostCurrent._m1et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m2et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m3et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m4et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m5et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m6et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m7et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m8et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m9et = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._m10et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private cmelli1,cmelli2,cmelli3,cmelli4,cmelli5,c";
mostCurrent._cmelli1 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli2 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli3 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli4 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli5 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli6 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli7 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli8 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli9 = new ir.hitexroid.material.x.Label();
mostCurrent._cmelli10 = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 29;BA.debugLine="Private SelectLocationBtn As Button";
mostCurrent._selectlocationbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private PanelLocation As Panel";
mostCurrent._panellocation = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private SaveBtn As Button";
mostCurrent._savebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private LLocation As Label";
mostCurrent._llocation = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 33;BA.debugLine="Private LDateTime As Label";
mostCurrent._ldatetime = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 34;BA.debugLine="Private LHamrah As Label";
mostCurrent._lhamrah = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 36;BA.debugLine="Private LSayar As Label";
mostCurrent._lsayar = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 37;BA.debugLine="Private SayarJavabLbl As Label";
mostCurrent._sayarjavablbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 38;BA.debugLine="Private SayarSwitch As ACSwitch";
mostCurrent._sayarswitch = new de.amberhome.objects.appcompat.ACSwitchCompatWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private SayerDalilLbl As Label";
mostCurrent._sayerdalillbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 40;BA.debugLine="Private SayarDalilSpinner As ACSpinner";
mostCurrent._sayardalilspinner = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private PanelSayar,PanelSayarDalil As Panel";
mostCurrent._panelsayar = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panelsayardalil = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private PanelHamrah As Panel";
mostCurrent._panelhamrah = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private HamrahSpinner As ACSpinner";
mostCurrent._hamrahspinner = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static void  _hamrahspinner_itemclick(int _position,Object _value) throws Exception{
ResumableSub_HamrahSpinner_ItemClick rsub = new ResumableSub_HamrahSpinner_ItemClick(null,_position,_value);
rsub.resume(processBA, null);
}
public static class ResumableSub_HamrahSpinner_ItemClick extends BA.ResumableSub {
public ResumableSub_HamrahSpinner_ItemClick(ir.saelozahra.snd1400.electionact parent,int _position,Object _value) {
this.parent = parent;
this._position = _position;
this._value = _value;
}
ir.saelozahra.snd1400.electionact parent;
int _position;
Object _value;
int _height = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 360;BA.debugLine="Dim Height As Int = 120dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120));
 //BA.debugLineNum = 362;BA.debugLine="Select Position";
if (true) break;

case 1:
//select
this.state = 24;
switch (_position) {
case 0: {
this.state = 3;
if (true) break;
}
case 1: {
this.state = 5;
if (true) break;
}
case 2: {
this.state = 7;
if (true) break;
}
case 3: {
this.state = 9;
if (true) break;
}
case 4: {
this.state = 11;
if (true) break;
}
case 5: {
this.state = 13;
if (true) break;
}
case 6: {
this.state = 15;
if (true) break;
}
case 7: {
this.state = 17;
if (true) break;
}
case 8: {
this.state = 19;
if (true) break;
}
case 9: {
this.state = 21;
if (true) break;
}
case 10: {
this.state = 23;
if (true) break;
}
}
if (true) break;

case 3:
//C
this.state = 24;
 //BA.debugLineNum = 364;BA.debugLine="Height = 120dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120));
 if (true) break;

case 5:
//C
this.state = 24;
 //BA.debugLineNum = 366;BA.debugLine="Height = 190dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (190));
 if (true) break;

case 7:
//C
this.state = 24;
 //BA.debugLineNum = 368;BA.debugLine="Height = 260dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260));
 if (true) break;

case 9:
//C
this.state = 24;
 //BA.debugLineNum = 370;BA.debugLine="Height = 333dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (333));
 if (true) break;

case 11:
//C
this.state = 24;
 //BA.debugLineNum = 372;BA.debugLine="Height = 410dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (410));
 if (true) break;

case 13:
//C
this.state = 24;
 //BA.debugLineNum = 374;BA.debugLine="Height = 480dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (480));
 if (true) break;

case 15:
//C
this.state = 24;
 //BA.debugLineNum = 376;BA.debugLine="Height = 560dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (560));
 if (true) break;

case 17:
//C
this.state = 24;
 //BA.debugLineNum = 378;BA.debugLine="Height = 635dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (635));
 if (true) break;

case 19:
//C
this.state = 24;
 //BA.debugLineNum = 380;BA.debugLine="Height = 708dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (708));
 if (true) break;

case 21:
//C
this.state = 24;
 //BA.debugLineNum = 382;BA.debugLine="Height = 780dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (780));
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 384;BA.debugLine="Height = 860dip";
_height = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (860));
 if (true) break;

case 24:
//C
this.state = -1;
;
 //BA.debugLineNum = 387;BA.debugLine="PanelHamrah.SetLayoutAnimated(313,PanelHamrah.Lef";
parent.mostCurrent._panelhamrah.SetLayoutAnimated((int) (313),parent.mostCurrent._panelhamrah.getLeft(),parent.mostCurrent._panelhamrah.getTop(),parent.mostCurrent._panelhamrah.getWidth(),_height);
 //BA.debugLineNum = 389;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 25;
return;
case 25:
//C
this.state = -1;
;
 //BA.debugLineNum = 391;BA.debugLine="Responsive";
_responsive();
 //BA.debugLineNum = 393;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _ime1_heightchanged(int _newheight,int _oldheight) throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Sub IME1_HeightChanged (NewHeight As Int, OldHeigh";
 //BA.debugLineNum = 119;BA.debugLine="Log(NewHeight)";
anywheresoftware.b4a.keywords.Common.LogImpl("66160385",BA.NumberToString(_newheight),0);
 //BA.debugLineNum = 120;BA.debugLine="ScrollView1.SetLayout(0,ToolBar.Height,100%x,NewH";
mostCurrent._scrollview1.SetLayout((int) (0),mostCurrent._toolbar.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (_newheight-mostCurrent._saelozahra._materialactionbarheight /*int*/ ));
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _loginkon() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _plogin = null;
anywheresoftware.b4a.objects.EditTextWrapper _usernameet = null;
anywheresoftware.b4a.objects.EditTextWrapper _passwordet = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2 _logindialog = null;
int _logindialogresult = 0;
 //BA.debugLineNum = 397;BA.debugLine="Sub LoginKon";
 //BA.debugLineNum = 398;BA.debugLine="Dim PLogin As Panel";
_plogin = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 399;BA.debugLine="PLogin.Initialize(\"PLogin\")";
_plogin.Initialize(mostCurrent.activityBA,"PLogin");
 //BA.debugLineNum = 400;BA.debugLine="Dim UserNameET As EditText";
_usernameet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 401;BA.debugLine="UserNameET.Initialize(\"UserNameET\")";
_usernameet.Initialize(mostCurrent.activityBA,"UserNameET");
 //BA.debugLineNum = 402;BA.debugLine="UserNameET.InputType=UserNameET.INPUT_TYPE_PHONE";
_usernameet.setInputType(_usernameet.INPUT_TYPE_PHONE);
 //BA.debugLineNum = 403;BA.debugLine="UserNameET.Typeface=SaeloZahra.Font";
_usernameet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 404;BA.debugLine="UserNameET.Text = \"demo\"";
_usernameet.setText(BA.ObjectToCharSequence("demo"));
 //BA.debugLineNum = 405;BA.debugLine="UserNameET.Hint = \"شماره تماس\"";
_usernameet.setHint("شماره تماس");
 //BA.debugLineNum = 406;BA.debugLine="PLogin.AddView(UserNameET,5%x,5%x,60%x,14%x)";
_plogin.AddView((android.view.View)(_usernameet.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 408;BA.debugLine="Dim PasswordET As EditText";
_passwordet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 409;BA.debugLine="PasswordET.Initialize(\"PasswordET\")";
_passwordet.Initialize(mostCurrent.activityBA,"PasswordET");
 //BA.debugLineNum = 410;BA.debugLine="PasswordET.Typeface=SaeloZahra.Font";
_passwordet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 411;BA.debugLine="PasswordET.Hint = \"کلمه عبور\"";
_passwordet.setHint("کلمه عبور");
 //BA.debugLineNum = 412;BA.debugLine="PasswordET.Text = \"demo\"";
_passwordet.setText(BA.ObjectToCharSequence("demo"));
 //BA.debugLineNum = 413;BA.debugLine="PasswordET.PasswordMode=True";
_passwordet.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 414;BA.debugLine="PLogin.AddView(PasswordET,5%x,25%x,60%x,14%x)";
_plogin.AddView((android.view.View)(_passwordet.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 416;BA.debugLine="If File.Exists(SaeloZahra.Dir,\"username\") And Fil";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"username") && anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"password")) { 
 //BA.debugLineNum = 417;BA.debugLine="UserNameET.Text= File.ReadString(SaeloZahra.Dir,";
_usernameet.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"username")));
 //BA.debugLineNum = 418;BA.debugLine="PasswordET.Text= File.ReadString(SaeloZahra.Dir,";
_passwordet.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"password")));
 };
 //BA.debugLineNum = 421;BA.debugLine="Dim LoginDialog As CustomDialog2";
_logindialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2();
 //BA.debugLineNum = 422;BA.debugLine="LoginDialog.AddView(PLogin,72%x,45%x)";
_logindialog.AddView((android.view.View)(_plogin.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (72),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA));
 //BA.debugLineNum = 423;BA.debugLine="Dim loginDialogResult As Int = LoginDialog.Show(";
_logindialogresult = _logindialog.Show(BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ورود به بخش مجریان")),BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ورود")),BA.ObjectToString(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"لغو")),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 424;BA.debugLine="If loginDialogResult == DialogResponse.POSITIVE T";
if (_logindialogresult==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 435;BA.debugLine="If UserNameET.Text==\"demo\" And PasswordET.Text==";
if ((_usernameet.getText()).equals("demo") && (_passwordet.getText()).equals("demo")) { 
 //BA.debugLineNum = 436;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 437;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 438;BA.debugLine="StartActivity(AdminsAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._adminsact.getObject()));
 }else {
 //BA.debugLineNum = 440;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"نام ک";
mostCurrent._snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._activity.getObject())),BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"نام کاربری یا کلمه عبور غلط است").getObject()),mostCurrent._snake.LENGTH_LONG);
 //BA.debugLineNum = 441;BA.debugLine="Snake.Show";
mostCurrent._snake.Show();
 };
 };
 //BA.debugLineNum = 444;BA.debugLine="End Sub";
return "";
}
public static String  _melliet_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 244;BA.debugLine="Private Sub MelliET_FocusChanged (HasFocus As Bool";
 //BA.debugLineNum = 245;BA.debugLine="If Not(HasFocus) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_hasfocus)) { 
 //BA.debugLineNum = 246;BA.debugLine="If Not(SaeloZahra.CheckMelliCode(MelliET.text))";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._saelozahra._checkmellicode /*boolean*/ (mostCurrent.activityBA,mostCurrent._melliet.getText()))) { 
 //BA.debugLineNum = 247;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\"کد ملی وارد شد";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"کد ملی وارد شده اشتباه است").getObject()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 248;BA.debugLine="MelliET.RequestFocus";
mostCurrent._melliet.RequestFocus();
 };
 };
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static void  _responsive() throws Exception{
ResumableSub_Responsive rsub = new ResumableSub_Responsive(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_Responsive extends BA.ResumableSub {
public ResumableSub_Responsive(ir.saelozahra.snd1400.electionact parent) {
this.parent = parent;
}
ir.saelozahra.snd1400.electionact parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 349;BA.debugLine="PanelSayar.SetLayoutAnimated(313,PanelSayar.Left,";
parent.mostCurrent._panelsayar.SetLayoutAnimated((int) (313),parent.mostCurrent._panelsayar.getLeft(),(int) (parent.mostCurrent._panelhamrah.getTop()+parent.mostCurrent._panelhamrah.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),parent.mostCurrent._panelsayar.getWidth(),parent.mostCurrent._panelsayar.getHeight());
 //BA.debugLineNum = 350;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 351;BA.debugLine="SayarSwitch_CheckedChange(SayarSwitch.Checked)";
_sayarswitch_checkedchange(parent.mostCurrent._sayarswitch.getChecked());
 //BA.debugLineNum = 352;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 2;
return;
case 2:
//C
this.state = -1;
;
 //BA.debugLineNum = 353;BA.debugLine="SaveBtn.SetLayoutAnimated(313,SaveBtn.Left,PanelS";
parent.mostCurrent._savebtn.SetLayoutAnimated((int) (313),parent.mostCurrent._savebtn.getLeft(),(int) (parent.mostCurrent._panelsayar.getTop()+parent.mostCurrent._panelsayar.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (36))),parent.mostCurrent._savebtn.getWidth(),parent.mostCurrent._savebtn.getHeight());
 //BA.debugLineNum = 354;BA.debugLine="Sleep(313)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (313));
this.state = 3;
return;
case 3:
//C
this.state = -1;
;
 //BA.debugLineNum = 355;BA.debugLine="ScrollView1.Panel.Height = SaveBtn.Top + SaveBtn.";
parent.mostCurrent._scrollview1.getPanel().setHeight((int) (parent.mostCurrent._savebtn.getTop()+parent.mostCurrent._savebtn.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 356;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _savebtn_click() throws Exception{
ResumableSub_SaveBtn_Click rsub = new ResumableSub_SaveBtn_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_SaveBtn_Click extends BA.ResumableSub {
public ResumableSub_SaveBtn_Click(ir.saelozahra.snd1400.electionact parent) {
this.parent = parent;
}
ir.saelozahra.snd1400.electionact parent;
int _result = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 283;BA.debugLine="Log(SelectMapACT.SelectedMakanlatlng)";
anywheresoftware.b4a.keywords.Common.LogImpl("66815745",parent.mostCurrent._selectmapact._selectedmakanlatlng /*String*/ ,0);
 //BA.debugLineNum = 285;BA.debugLine="If SelectMapACT.SelectedMakanlatlng.Length<5 Then";
if (true) break;

case 1:
//if
this.state = 4;
if (parent.mostCurrent._selectmapact._selectedmakanlatlng /*String*/ .length()<5) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 286;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"ابتدا";
parent.mostCurrent._snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._activity.getObject())),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ابتدا موقعیت مکانی را انتخاب کنید").getObject()),parent.mostCurrent._snake.LENGTH_LONG);
 //BA.debugLineNum = 287;BA.debugLine="Snake.SetAction(\"SelectLocationBtn_Click\",SaeloZ";
parent.mostCurrent._snake.SetAction(mostCurrent.activityBA,"SelectLocationBtn_Click",BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"انتخاب مکان").getObject()));
 //BA.debugLineNum = 288;BA.debugLine="Snake.Show";
parent.mostCurrent._snake.Show();
 //BA.debugLineNum = 289;BA.debugLine="Return";
if (true) return ;
 if (true) break;
;
 //BA.debugLineNum = 292;BA.debugLine="If MelliET.Text.Length<5 Then";

case 4:
//if
this.state = 7;
if (parent.mostCurrent._melliet.getText().length()<5) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 293;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"ابتدا";
parent.mostCurrent._snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._activity.getObject())),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ابتدا کد ملی خود را وارد کنید").getObject()),parent.mostCurrent._snake.LENGTH_LONG);
 //BA.debugLineNum = 294;BA.debugLine="Snake.Show";
parent.mostCurrent._snake.Show();
 //BA.debugLineNum = 295;BA.debugLine="MelliET.RequestFocus";
parent.mostCurrent._melliet.RequestFocus();
 //BA.debugLineNum = 296;BA.debugLine="Return";
if (true) return ;
 if (true) break;
;
 //BA.debugLineNum = 299;BA.debugLine="If Not(SaeloZahra.CheckMelliCode(MelliET.Text)) T";

case 7:
//if
this.state = 10;
if (anywheresoftware.b4a.keywords.Common.Not(parent.mostCurrent._saelozahra._checkmellicode /*boolean*/ (mostCurrent.activityBA,parent.mostCurrent._melliet.getText()))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 300;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"کد ملی";
parent.mostCurrent._snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._activity.getObject())),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"کد ملی وارد شده صحیح نیست").getObject()),parent.mostCurrent._snake.LENGTH_LONG);
 //BA.debugLineNum = 301;BA.debugLine="MelliET.RequestFocus";
parent.mostCurrent._melliet.RequestFocus();
 //BA.debugLineNum = 302;BA.debugLine="Snake.Show";
parent.mostCurrent._snake.Show();
 //BA.debugLineNum = 303;BA.debugLine="Return";
if (true) return ;
 if (true) break;
;
 //BA.debugLineNum = 306;BA.debugLine="If SayarSwitch.Checked Then";

case 10:
//if
this.state = 19;
if (parent.mostCurrent._sayarswitch.getChecked()) { 
this.state = 12;
}else {
this.state = 18;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 308;BA.debugLine="Msgbox2Async(SaeloZahra.CSB(\"\"&CRLF&\"پیشاپیش از";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,""+anywheresoftware.b4a.keywords.Common.CRLF+"پیشاپیش از صبوری شما متشکریم").getObject()),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"منتظر بمانید").getObject()),BA.ObjectToString(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"با تشکر")),"","",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 309;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 20;
return;
case 20:
//C
this.state = 13;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 310;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 13:
//if
this.state = 16;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 15;
}if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 311;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;

case 16:
//C
this.state = 19;
;
 if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 314;BA.debugLine="Msgbox2Async(SaeloZahra.CSB(\"در تاریخ پیشنهادی ش";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"در تاریخ پیشنهادی شما ، شعبه مسجد ابوالفضل پیشنهاد میشود"+anywheresoftware.b4a.keywords.Common.CRLF+"آیا مایل به رفتن به این شعبه در ساعت پیشنهادی خودتان هستید؟").getObject()),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"نتیجه").getObject()),BA.ObjectToString(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"بله، نام بنده را ثبت کنید")),BA.ObjectToString(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خیر، شعبه دیگری پیشنهاد کنید")),BA.ObjectToString(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"شاید بعدا ساعت را تغییر دهم")),(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 19:
//C
this.state = -1;
;
 //BA.debugLineNum = 317;BA.debugLine="File.WriteString(SaeloZahra.Dir,\"melli\",MelliET.T";
anywheresoftware.b4a.keywords.Common.File.WriteString(parent.mostCurrent._saelozahra._dir /*String*/ ,"melli",parent.mostCurrent._melliet.getText());
 //BA.debugLineNum = 318;BA.debugLine="File.WriteString(SaeloZahra.Dir,\"tel\",TelEt.Text)";
anywheresoftware.b4a.keywords.Common.File.WriteString(parent.mostCurrent._saelozahra._dir /*String*/ ,"tel",parent.mostCurrent._telet.getText());
 //BA.debugLineNum = 319;BA.debugLine="File.WriteString(SaeloZahra.Dir,\"time\",TimeEt.Tex";
anywheresoftware.b4a.keywords.Common.File.WriteString(parent.mostCurrent._saelozahra._dir /*String*/ ,"time",parent.mostCurrent._timeet.getText());
 //BA.debugLineNum = 321;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
}
public static String  _sayarswitch_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 334;BA.debugLine="Private Sub SayarSwitch_CheckedChange(Checked As B";
 //BA.debugLineNum = 335;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 336;BA.debugLine="SayarJavabLbl.Text = SaeloZahra.CSB(\"بله نیاز دا";
mostCurrent._sayarjavablbl.setText(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"بله نیاز دارم").getObject()));
 //BA.debugLineNum = 337;BA.debugLine="PanelSayarDalil.SetVisibleAnimated(313,True)";
mostCurrent._panelsayardalil.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 338;BA.debugLine="PanelSayar.SetLayoutAnimated(313,PanelSayar.Left";
mostCurrent._panelsayar.SetLayoutAnimated((int) (313),mostCurrent._panelsayar.getLeft(),mostCurrent._panelsayar.getTop(),mostCurrent._panelsayar.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (222)));
 }else {
 //BA.debugLineNum = 340;BA.debugLine="SayarJavabLbl.Text = SaeloZahra.CSB(\"خیر، نیاز ن";
mostCurrent._sayarjavablbl.setText(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خیر، نیاز ندارم").getObject()));
 //BA.debugLineNum = 341;BA.debugLine="PanelSayarDalil.SetVisibleAnimated(313,False)";
mostCurrent._panelsayardalil.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 342;BA.debugLine="PanelSayar.SetLayoutAnimated(313,PanelSayar.Left";
mostCurrent._panelsayar.SetLayoutAnimated((int) (313),mostCurrent._panelsayar.getLeft(),mostCurrent._panelsayar.getTop(),mostCurrent._panelsayar.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 };
 //BA.debugLineNum = 344;BA.debugLine="SaveBtn.SetLayoutAnimated(313,SaveBtn.Left,PanelS";
mostCurrent._savebtn.SetLayoutAnimated((int) (313),mostCurrent._savebtn.getLeft(),(int) (mostCurrent._panelsayar.getTop()+mostCurrent._panelsayar.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (33))),mostCurrent._savebtn.getWidth(),mostCurrent._savebtn.getHeight());
 //BA.debugLineNum = 345;BA.debugLine="Responsive";
_responsive();
 //BA.debugLineNum = 346;BA.debugLine="End Sub";
return "";
}
public static void  _selectlocationbtn_click() throws Exception{
ResumableSub_SelectLocationBtn_Click rsub = new ResumableSub_SelectLocationBtn_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_SelectLocationBtn_Click extends BA.ResumableSub {
public ResumableSub_SelectLocationBtn_Click(ir.saelozahra.snd1400.electionact parent) {
this.parent = parent;
}
ir.saelozahra.snd1400.electionact parent;
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
 //BA.debugLineNum = 325;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
parent.mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 326;BA.debugLine="Show.convertActivityFromTranslucent";
parent.mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 328;BA.debugLine="Starter.rp.CheckAndRequest(Starter.rp.PERMISSION_";
parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 329;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 330;BA.debugLine="If Result Then StartActivity(SelectMapACT)";
if (true) break;

case 1:
//if
this.state = 6;
if (_result) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._selectmapact.getObject()));
if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 332;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _selecttimebtn_click() throws Exception{
 //BA.debugLineNum = 253;BA.debugLine="Private Sub SelectTimeBtn_Click";
 //BA.debugLineNum = 254;BA.debugLine="time.show(\"time\")";
mostCurrent._time.Show("time");
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
return "";
}
public static String  _setlayout() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csbsayer = null;
 //BA.debugLineNum = 143;BA.debugLine="Sub SetLayout";
 //BA.debugLineNum = 145;BA.debugLine="ScrollView1.Panel.Height=SaveBtn.Top+110dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._savebtn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110))));
 //BA.debugLineNum = 148;BA.debugLine="LMelli.Typeface=SaeloZahra.fontBold";
mostCurrent._lmelli.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 149;BA.debugLine="LTel.Typeface=SaeloZahra.fontBold";
mostCurrent._ltel.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 150;BA.debugLine="LDateTime.Typeface=SaeloZahra.fontBold";
mostCurrent._ldatetime.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 151;BA.debugLine="LHamrah.Typeface=SaeloZahra.fontBold";
mostCurrent._lhamrah.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 152;BA.debugLine="LLocation.Typeface=SaeloZahra.fontBold";
mostCurrent._llocation.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 154;BA.debugLine="TelEt.Typeface=SaeloZahra.Font";
mostCurrent._telet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 155;BA.debugLine="TelEt.Background=Null";
mostCurrent._telet.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 157;BA.debugLine="cmelli1.Typeface=SaeloZahra.Font";
mostCurrent._cmelli1.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 158;BA.debugLine="cmelli2.Typeface=SaeloZahra.Font";
mostCurrent._cmelli2.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 159;BA.debugLine="cmelli3.Typeface=SaeloZahra.Font";
mostCurrent._cmelli3.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 160;BA.debugLine="cmelli4.Typeface=SaeloZahra.Font";
mostCurrent._cmelli4.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 161;BA.debugLine="cmelli5.Typeface=SaeloZahra.Font";
mostCurrent._cmelli5.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 162;BA.debugLine="cmelli6.Typeface=SaeloZahra.Font";
mostCurrent._cmelli6.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 163;BA.debugLine="cmelli7.Typeface=SaeloZahra.Font";
mostCurrent._cmelli7.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 164;BA.debugLine="cmelli8.Typeface=SaeloZahra.Font";
mostCurrent._cmelli8.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 165;BA.debugLine="cmelli9.Typeface=SaeloZahra.Font";
mostCurrent._cmelli9.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 166;BA.debugLine="cmelli10.Typeface=SaeloZahra.Font";
mostCurrent._cmelli10.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 167;BA.debugLine="M1ET.Background=Null";
mostCurrent._m1et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 168;BA.debugLine="M2ET.Background=Null";
mostCurrent._m2et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 169;BA.debugLine="M3ET.Background=Null";
mostCurrent._m3et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 170;BA.debugLine="M4ET.Background=Null";
mostCurrent._m4et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 171;BA.debugLine="M5ET.Background=Null";
mostCurrent._m5et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 172;BA.debugLine="M6ET.Background=Null";
mostCurrent._m6et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 173;BA.debugLine="M7ET.Background=Null";
mostCurrent._m7et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 174;BA.debugLine="M8ET.Background=Null";
mostCurrent._m8et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 175;BA.debugLine="M9ET.Background=Null";
mostCurrent._m9et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 176;BA.debugLine="M10ET.Background=Null";
mostCurrent._m10et.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 177;BA.debugLine="M1ET.Typeface=SaeloZahra.Font";
mostCurrent._m1et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 178;BA.debugLine="M2ET.Typeface=SaeloZahra.Font";
mostCurrent._m2et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 179;BA.debugLine="M3ET.Typeface=SaeloZahra.Font";
mostCurrent._m3et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 180;BA.debugLine="M4ET.Typeface=SaeloZahra.Font";
mostCurrent._m4et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 181;BA.debugLine="M5ET.Typeface=SaeloZahra.Font";
mostCurrent._m5et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 182;BA.debugLine="M6ET.Typeface=SaeloZahra.Font";
mostCurrent._m6et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 183;BA.debugLine="M7ET.Typeface=SaeloZahra.Font";
mostCurrent._m7et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 184;BA.debugLine="M8ET.Typeface=SaeloZahra.Font";
mostCurrent._m8et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 185;BA.debugLine="M9ET.Typeface=SaeloZahra.Font";
mostCurrent._m9et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 186;BA.debugLine="M10ET.Typeface=SaeloZahra.Font";
mostCurrent._m10et.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 188;BA.debugLine="TimeEt.Typeface=SaeloZahra.Font";
mostCurrent._timeet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 189;BA.debugLine="TimeEt.Background=Null";
mostCurrent._timeet.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 194;BA.debugLine="MelliET.Typeface=SaeloZahra.Font";
mostCurrent._melliet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 195;BA.debugLine="MelliET.Background=Null";
mostCurrent._melliet.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 198;BA.debugLine="SelectTimeBtn.Typeface=SaeloZahra.Font";
mostCurrent._selecttimebtn.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 199;BA.debugLine="SelectTimeBtn.ButtonColor=SaeloZahra.Color";
mostCurrent._selecttimebtn.setButtonColor(mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 200;BA.debugLine="SelectLocationBtn.Typeface=SaeloZahra.Font";
mostCurrent._selectlocationbtn.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 201;BA.debugLine="SelectLocationBtn.Color=SaeloZahra.Color";
mostCurrent._selectlocationbtn.setColor(mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 203;BA.debugLine="SaveBtn.Typeface=SaeloZahra.fontBold";
mostCurrent._savebtn.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 206;BA.debugLine="Dim CsbSayer As CSBuilder";
_csbsayer = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 207;BA.debugLine="CsbSayer.Initialize.Typeface(SaeloZahra.fontBold)";
_csbsayer.Initialize().Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (16)).Append(BA.ObjectToCharSequence("درخواست شعبه سیار دارید؟")).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (14)).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(mostCurrent._saelozahra._coloraccent /*int*/ ).Append(BA.ObjectToCharSequence("ویژه سالمندان و بیماران خاص")).PopAll();
 //BA.debugLineNum = 208;BA.debugLine="LSayar.Text = CsbSayer";
mostCurrent._lsayar.setText(BA.ObjectToCharSequence(_csbsayer.getObject()));
 //BA.debugLineNum = 210;BA.debugLine="SayarDalilSpinner.Add2(SaeloZahra.CSB(\"درگیر ویرو";
mostCurrent._sayardalilspinner.Add2(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"درگیر ویروس کرونا").getObject()),mostCurrent._xml.GetDrawable("baseline_coronavirus_black_36dp"));
 //BA.debugLineNum = 211;BA.debugLine="SayarDalilSpinner.Add2(SaeloZahra.CSB(\"سالمندان و";
mostCurrent._sayardalilspinner.Add2(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سالمندان و ناتوانان").getObject()),mostCurrent._xml.GetDrawable("baseline_elderly_black_36dp"));
 //BA.debugLineNum = 212;BA.debugLine="SayarDalilSpinner.Add2(SaeloZahra.CSB(\"سایر\"),XML";
mostCurrent._sayardalilspinner.Add2(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سایر").getObject()),mostCurrent._xml.GetDrawable("baseline_sick_black_36dp"));
 //BA.debugLineNum = 214;BA.debugLine="SayerDalilLbl.Typeface=SaeloZahra.Font";
mostCurrent._sayerdalillbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 217;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"بدون همراه";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"بدون همراه").getObject()));
 //BA.debugLineNum = 218;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"یک نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"یک نفر").getObject()));
 //BA.debugLineNum = 219;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"دو نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"دو نفر").getObject()));
 //BA.debugLineNum = 220;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"سه نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سه نفر").getObject()));
 //BA.debugLineNum = 221;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"چهار نفر\")";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"چهار نفر").getObject()));
 //BA.debugLineNum = 222;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"پنج نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"پنج نفر").getObject()));
 //BA.debugLineNum = 223;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"شش نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"شش نفر").getObject()));
 //BA.debugLineNum = 224;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"هفت نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"هفت نفر").getObject()));
 //BA.debugLineNum = 225;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"هشت نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"هشت نفر").getObject()));
 //BA.debugLineNum = 226;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"نه نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"نه نفر").getObject()));
 //BA.debugLineNum = 227;BA.debugLine="HamrahSpinner.Add(SaeloZahra.CSBTitle(\"ده نفر\"))";
mostCurrent._hamrahspinner.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ده نفر").getObject()));
 //BA.debugLineNum = 229;BA.debugLine="Responsive";
_responsive();
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
return "";
}
public static String  _time_oncancel() throws Exception{
 //BA.debugLineNum = 272;BA.debugLine="Sub Time_onCancel";
 //BA.debugLineNum = 273;BA.debugLine="Log(\"Time_onCancel\")";
anywheresoftware.b4a.keywords.Common.LogImpl("66684673","Time_onCancel",0);
 //BA.debugLineNum = 274;BA.debugLine="End Sub";
return "";
}
public static String  _time_ondismiss() throws Exception{
 //BA.debugLineNum = 276;BA.debugLine="Sub time_onDismiss";
 //BA.debugLineNum = 277;BA.debugLine="Log(\"Time_onDismiss\")";
anywheresoftware.b4a.keywords.Common.LogImpl("66750209","Time_onDismiss",0);
 //BA.debugLineNum = 278;BA.debugLine="End Sub";
return "";
}
public static String  _time_ontimeset(int _hourstart,int _minutestart,int _hourend,int _minuteend) throws Exception{
long _starttime = 0L;
long _endtime = 0L;
 //BA.debugLineNum = 262;BA.debugLine="Sub Time_onTimeSet(hourStart As Int, minuteStart A";
 //BA.debugLineNum = 263;BA.debugLine="Dim StartTime, EndTime As Long";
_starttime = 0L;
_endtime = 0L;
 //BA.debugLineNum = 264;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 265;BA.debugLine="StartTime = DateTime.TimeParse(hourStart&\":\"&minu";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(BA.NumberToString(_hourstart)+":"+BA.NumberToString(_minutestart));
 //BA.debugLineNum = 266;BA.debugLine="EndTime = DateTime.TimeParse(hourEnd&\":\"&minuteEn";
_endtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(BA.NumberToString(_hourend)+":"+BA.NumberToString(_minuteend));
 //BA.debugLineNum = 267;BA.debugLine="TimeEt.Text = DateTime.Time(StartTime) & \"  __  \"";
mostCurrent._timeet.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(_starttime)+"  __  "+anywheresoftware.b4a.keywords.Common.DateTime.Time(_endtime)));
 //BA.debugLineNum = 268;BA.debugLine="TimeEt.Tag = StartTime&\"|\"&EndTime";
mostCurrent._timeet.setTag((Object)(BA.NumberToString(_starttime)+"|"+BA.NumberToString(_endtime)));
 //BA.debugLineNum = 269;BA.debugLine="Log(StartTime)";
anywheresoftware.b4a.keywords.Common.LogImpl("66619143",BA.NumberToString(_starttime),0);
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 106;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (2))) {
case 0: {
 //BA.debugLineNum = 108;BA.debugLine="LoginKon";
_loginkon();
 break; }
}
;
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 113;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 114;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
}
