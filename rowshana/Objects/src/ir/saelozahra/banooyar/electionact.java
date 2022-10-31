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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.electionact");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.electionact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.banooyar.electionact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
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
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.IME _ime1 = null;
public ir.hitexroid.material.x.Hitex_Snackbar _snake = null;
public com.jokar.materialdaterangepicker.wrapper.TimePickerDialogWrapper _time = null;
public anywheresoftware.b4a.objects.EditTextWrapper _telet = null;
public anywheresoftware.b4a.objects.ButtonWrapper _selectlocationbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _panellocation = null;
public anywheresoftware.b4a.objects.ButtonWrapper _savebtn = null;
public ir.hitexroid.material.x.Label _llocation = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmoshavere = null;
public ir.hitexroid.material.x.Label _lmoshavere = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmoshavere2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _moshavereet = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.banooyar.main _main = null;
public ir.saelozahra.banooyar.homeact _homeact = null;
public ir.saelozahra.banooyar.contentlistact _contentlistact = null;
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
 //BA.debugLineNum = 35;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="IME1.Initialize(\"IME1\")";
mostCurrent._ime1.Initialize("IME1");
 //BA.debugLineNum = 38;BA.debugLine="IME1.AddHeightChangedEvent";
mostCurrent._ime1.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 40;BA.debugLine="Activity.LoadLayout(\"SVLayout\")";
mostCurrent._activity.LoadLayout("SVLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 41;BA.debugLine="ScrollView1.Panel.LoadLayout(\"ElectionLayout\")";
mostCurrent._scrollview1.getPanel().LoadLayout("ElectionLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 43;BA.debugLine="SaeloZahra.SetToolbarStyle(ToolBar,\"فرم مشاوره\",T";
mostCurrent._saelozahra._settoolbarstyle /*String*/ (mostCurrent.activityBA,mostCurrent._toolbar,"فرم مشاوره",anywheresoftware.b4a.keywords.Common.True,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._scrollview1.getObject())));
 //BA.debugLineNum = 45;BA.debugLine="SetLayout";
_setlayout();
 //BA.debugLineNum = 46;BA.debugLine="SaeloZahra.SetStatusBarColor(0xFF493B34)";
mostCurrent._saelozahra._setstatusbarcolor /*String*/ (mostCurrent.activityBA,(int) (0xff493b34));
 //BA.debugLineNum = 50;BA.debugLine="ToolBar.Padding=Array As Int(0,1dip,0,0dip)";
mostCurrent._toolbar.setPadding(new int[]{(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 51;BA.debugLine="ToolBar.Height = SaeloZahra.MaterialActionBarHeig";
mostCurrent._toolbar.setHeight(mostCurrent._saelozahra._materialactionbarheight /*int*/ );
 //BA.debugLineNum = 53;BA.debugLine="time.Initialize(\"Time\",13, 0, 14, 0, True)";
mostCurrent._time.Initialize(mostCurrent.activityBA,"Time",(int) (13),(int) (0),(int) (14),(int) (0),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 54;BA.debugLine="time.CancelButtonText = \"انصراف\"";
mostCurrent._time.setCancelButtonText("انصراف");
 //BA.debugLineNum = 55;BA.debugLine="time.OkButtonText = \"تایید\"";
mostCurrent._time.setOkButtonText("تایید");
 //BA.debugLineNum = 56;BA.debugLine="time.StartTitle = \"از\"";
mostCurrent._time.setStartTitle("از");
 //BA.debugLineNum = 57;BA.debugLine="time.EndTitle = \"تا\"";
mostCurrent._time.setEndTitle("تا");
 //BA.debugLineNum = 58;BA.debugLine="time.Typeface = SaeloZahra.Font";
mostCurrent._time.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 59;BA.debugLine="time.AmText = \"ق.ظ\"";
mostCurrent._time.setAmText("ق.ظ");
 //BA.debugLineNum = 60;BA.debugLine="time.PmText = \"ب.ظ\"";
mostCurrent._time.setPmText("ب.ظ");
 //BA.debugLineNum = 61;BA.debugLine="time.AccentColor = SaeloZahra.ColorDark";
mostCurrent._time.setAccentColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 77;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
 //BA.debugLineNum = 78;BA.debugLine="Config.Initialize";
mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 79;BA.debugLine="Config.position(Config.POSITION_LEFT)";
mostCurrent._config.position(mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 80;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
mostCurrent._config.primaryColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 81;BA.debugLine="Config.edge(True)";
mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 82;BA.debugLine="Config.sensitivity(100)";
mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 83;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 84;BA.debugLine="Show.convertActivityToTranslucent";
mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 85;BA.debugLine="Show.attachActivity(Config)";
mostCurrent._show.attachActivity(mostCurrent.activityBA,mostCurrent._config);
 };
 //BA.debugLineNum = 88;BA.debugLine="AC.SetElevation(SaveBtn,7dip)";
mostCurrent._ac.SetElevation((android.view.View)(mostCurrent._savebtn.getObject()),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 //BA.debugLineNum = 90;BA.debugLine="ScrollView1.SetLayout(0,ToolBar.Height,100%x,100%";
mostCurrent._scrollview1.SetLayout((int) (0),mostCurrent._toolbar.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._saelozahra._materialactionbarheight /*int*/ ));
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 160;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 161;BA.debugLine="Select KeyCode";
switch (BA.switchObjectToInt(_keycode,anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK)) {
case 0: {
 //BA.debugLineNum = 163;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 164;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
default: {
 //BA.debugLineNum = 166;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 break; }
}
;
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csbadd = null;
 //BA.debugLineNum = 111;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 113;BA.debugLine="If Not(SaeloZahra.CheckConnection) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._saelozahra._checkconnection /*boolean*/ (mostCurrent.activityBA))) { 
 //BA.debugLineNum = 114;BA.debugLine="ToastMessageShow( SaeloZahra.CSB(\"حتما به اینترن";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"حتما به اینترنت متصل شوید").getObject()),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 117;BA.debugLine="LLocation.Text = SaeloZahra.CSB(\"موقعیت مکانی خود";
mostCurrent._llocation.setText(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"موقعیت مکانی خود را برای یافتن نزدیک ترین مرکز مشاوره وارد کنید.").getObject()));
 //BA.debugLineNum = 118;BA.debugLine="If File.Exists(SaeloZahra.dir,\"address\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"address")) { 
 //BA.debugLineNum = 119;BA.debugLine="Dim CsbAdd As CSBuilder";
_csbadd = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 120;BA.debugLine="CsbAdd.Initialize.Append(LLocation.Text).Append(";
_csbadd.Initialize().Append(BA.ObjectToCharSequence(mostCurrent._llocation.getText())).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (10)).Color(mostCurrent._saelozahra._coloraccent /*int*/ ).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"address"))).PopAll();
 //BA.debugLineNum = 121;BA.debugLine="LLocation.Text = CsbAdd";
mostCurrent._llocation.setText(BA.ObjectToCharSequence(_csbadd.getObject()));
 };
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 17;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim IME1 As IME";
mostCurrent._ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 19;BA.debugLine="Dim Snake As Hitex_Snackbar";
mostCurrent._snake = new ir.hitexroid.material.x.Hitex_Snackbar();
 //BA.debugLineNum = 21;BA.debugLine="Dim time As JK_MaterialTimePicker";
mostCurrent._time = new com.jokar.materialdaterangepicker.wrapper.TimePickerDialogWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private TelEt As EditText";
mostCurrent._telet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private SelectLocationBtn As Button";
mostCurrent._selectlocationbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private PanelLocation As Panel";
mostCurrent._panellocation = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private SaveBtn As Button";
mostCurrent._savebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private LLocation As Label";
mostCurrent._llocation = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 29;BA.debugLine="Private PanelMoshavere As Panel";
mostCurrent._panelmoshavere = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private LMoshavere As Label";
mostCurrent._lmoshavere = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 31;BA.debugLine="Private PanelMoshavere2 As Panel";
mostCurrent._panelmoshavere2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private MoshavereET As EditText";
mostCurrent._moshavereet = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _ime1_heightchanged(int _newheight,int _oldheight) throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Sub IME1_HeightChanged (NewHeight As Int, OldHeigh";
 //BA.debugLineNum = 107;BA.debugLine="Log(NewHeight)";
anywheresoftware.b4a.keywords.Common.LogImpl("42752513",BA.NumberToString(_newheight),0);
 //BA.debugLineNum = 108;BA.debugLine="ScrollView1.SetLayout(0,ToolBar.Height,100%x,NewH";
mostCurrent._scrollview1.SetLayout((int) (0),mostCurrent._toolbar.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (_newheight-mostCurrent._saelozahra._materialactionbarheight /*int*/ ));
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
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
public ResumableSub_Responsive(ir.saelozahra.banooyar.electionact parent) {
this.parent = parent;
}
ir.saelozahra.banooyar.electionact parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 213;BA.debugLine="Sleep(313)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (313));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 214;BA.debugLine="ScrollView1.Panel.Height = SaveBtn.Top + SaveBtn.";
parent.mostCurrent._scrollview1.getPanel().setHeight((int) (parent.mostCurrent._savebtn.getTop()+parent.mostCurrent._savebtn.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
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
public ResumableSub_SaveBtn_Click(ir.saelozahra.banooyar.electionact parent) {
this.parent = parent;
}
ir.saelozahra.banooyar.electionact parent;
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
 //BA.debugLineNum = 182;BA.debugLine="Log(SelectMapACT.SelectedMakanlatlng)";
anywheresoftware.b4a.keywords.Common.LogImpl("43145729",parent.mostCurrent._selectmapact._selectedmakanlatlng /*String*/ ,0);
 //BA.debugLineNum = 184;BA.debugLine="If SelectMapACT.SelectedMakanlatlng.Length<5 Then";
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
 //BA.debugLineNum = 185;BA.debugLine="Snake.Initialize(Activity,SaeloZahra.CSB(\"ابتدا";
parent.mostCurrent._snake.Initialize((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._activity.getObject())),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ابتدا موقعیت مکانی را انتخاب کنید").getObject()),parent.mostCurrent._snake.LENGTH_LONG);
 //BA.debugLineNum = 186;BA.debugLine="Snake.SetAction(\"SelectLocationBtn_Click\",SaeloZ";
parent.mostCurrent._snake.SetAction(mostCurrent.activityBA,"SelectLocationBtn_Click",BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"انتخاب مکان").getObject()));
 //BA.debugLineNum = 187;BA.debugLine="Snake.Show";
parent.mostCurrent._snake.Show();
 //BA.debugLineNum = 188;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = 5;
;
 //BA.debugLineNum = 192;BA.debugLine="Msgbox2Async(SaeloZahra.CSB(\"\"&CRLF&\"پیشاپیش از ص";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,""+anywheresoftware.b4a.keywords.Common.CRLF+"پیشاپیش از صبوری شما متشکریم").getObject()),BA.ObjectToCharSequence(parent.mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"منتظر بمانید").getObject()),BA.ObjectToString(parent.mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"با تشکر")),"","",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 193;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 9;
return;
case 9:
//C
this.state = 5;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 194;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 5:
//if
this.state = 8;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 7;
}if (true) break;

case 7:
//C
this.state = 8;
 //BA.debugLineNum = 195;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
}
public static void  _selectlocationbtn_click() throws Exception{
ResumableSub_SelectLocationBtn_Click rsub = new ResumableSub_SelectLocationBtn_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_SelectLocationBtn_Click extends BA.ResumableSub {
public ResumableSub_SelectLocationBtn_Click(ir.saelozahra.banooyar.electionact parent) {
this.parent = parent;
}
ir.saelozahra.banooyar.electionact parent;
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
 //BA.debugLineNum = 203;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
parent.mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 204;BA.debugLine="Show.convertActivityFromTranslucent";
parent.mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 206;BA.debugLine="Starter.rp.CheckAndRequest(Starter.rp.PERMISSION_";
parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 207;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 208;BA.debugLine="If Result Then StartActivity(SelectMapACT)";
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
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _selecttimebtn_click() throws Exception{
 //BA.debugLineNum = 171;BA.debugLine="Private Sub SelectTimeBtn_Click";
 //BA.debugLineNum = 172;BA.debugLine="time.show(\"time\")";
mostCurrent._time.Show("time");
 //BA.debugLineNum = 173;BA.debugLine="End Sub";
return "";
}
public static String  _setlayout() throws Exception{
 //BA.debugLineNum = 131;BA.debugLine="Sub SetLayout";
 //BA.debugLineNum = 133;BA.debugLine="ScrollView1.Panel.Height=SaveBtn.Top+110dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._savebtn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110))));
 //BA.debugLineNum = 136;BA.debugLine="LMoshavere.Typeface=SaeloZahra.fontBold";
mostCurrent._lmoshavere.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 137;BA.debugLine="LTel.Typeface=SaeloZahra.fontBold";
mostCurrent._ltel.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 138;BA.debugLine="LLocation.Typeface=SaeloZahra.fontBold";
mostCurrent._llocation.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 140;BA.debugLine="TelEt.Typeface=SaeloZahra.Font";
mostCurrent._telet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 141;BA.debugLine="TelEt.Background=Null";
mostCurrent._telet.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 144;BA.debugLine="MoshavereET.Typeface=SaeloZahra.Font";
mostCurrent._moshavereet.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 145;BA.debugLine="MoshavereET.Background=Null";
mostCurrent._moshavereet.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 148;BA.debugLine="SelectLocationBtn.Typeface=SaeloZahra.Font";
mostCurrent._selectlocationbtn.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 149;BA.debugLine="SelectLocationBtn.Color=SaeloZahra.Color";
mostCurrent._selectlocationbtn.setColor(mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 151;BA.debugLine="SaveBtn.Typeface=SaeloZahra.fontBold";
mostCurrent._savebtn.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 155;BA.debugLine="Responsive";
_responsive();
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 95;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (2))) {
case 0: {
 break; }
}
;
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 101;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 102;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
}
