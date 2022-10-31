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

public class candidalistact extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static candidalistact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.candidalistact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (candidalistact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.candidalistact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.candidalistact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (candidalistact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (candidalistact) Resume **");
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
		return candidalistact.class;
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
            BA.LogInfo("** Activity (candidalistact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (candidalistact) Pause event (activity is not paused). **");
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
            candidalistact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (candidalistact) Resume **");
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
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public static anywheresoftware.b4a.sql.SQL.CursorWrapper _cu1 = null;
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public de.amberhome.objects.appcompat.ACToolbarLightWrapper _toolbar = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelcandidate = null;
public de.amberhome.objects.appcompat.ACSearchViewWrapper _sv = null;
public de.amberhome.objects.appcompat.ACMenuItemWrapper _si = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _x1 = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _statespin = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cityspin = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.starter _starter = null;
public ir.saelozahra.snd1400.homeact _homeact = null;
public ir.saelozahra.snd1400.saelozahra _saelozahra = null;
public ir.saelozahra.snd1400.adminsact _adminsact = null;
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
 //BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"CandidateListLayout\")";
mostCurrent._activity.LoadLayout("CandidateListLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 26;BA.debugLine="SaeloZahra.SetToolbarStyle(ToolBar,\"\",True,PanelC";
mostCurrent._saelozahra._settoolbarstyle /*String*/ (mostCurrent.activityBA,mostCurrent._toolbar,"",anywheresoftware.b4a.keywords.Common.True,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._panelcandidate.getObject())));
 //BA.debugLineNum = 27;BA.debugLine="PanelCandidate.SetBackgroundImage(LoadBitmapResiz";
mostCurrent._panelcandidate.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"oskol.jpg",mostCurrent._panelcandidate.getWidth(),mostCurrent._panelcandidate.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 29;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
 //BA.debugLineNum = 30;BA.debugLine="Config.Initialize";
mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 31;BA.debugLine="Config.position(Config.POSITION_LEFT)";
mostCurrent._config.position(mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 32;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
mostCurrent._config.primaryColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 33;BA.debugLine="Config.edge(True)";
mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 34;BA.debugLine="Config.sensitivity(100)";
mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 35;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 37;BA.debugLine="Show.convertActivityToTranslucent";
mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 38;BA.debugLine="Show.attachActivity(Config)";
mostCurrent._show.attachActivity(mostCurrent.activityBA,mostCurrent._config);
 };
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 101;BA.debugLine="Log(\"Create Menu\")";
anywheresoftware.b4a.keywords.Common.LogImpl("65177346","Create Menu",0);
 //BA.debugLineNum = 103;BA.debugLine="sv.Initialize2(\"search\", sv.THEME_DARK)";
mostCurrent._sv.Initialize2(mostCurrent.activityBA,"search",mostCurrent._sv.THEME_DARK);
 //BA.debugLineNum = 104;BA.debugLine="sv.IconifiedByDefault = True";
mostCurrent._sv.setIconifiedByDefault(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 106;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 107;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 108;BA.debugLine="sv.QueryHint=SaeloZahra.csb(\"نام کاندیدای را وارد";
mostCurrent._sv.setQueryHint(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"نام کاندیدای را وارد کنید").getObject()));
 //BA.debugLineNum = 109;BA.debugLine="SI = Menu.Add2(1, 3, \"جستجو\",X1.GetDrawable(\"roun";
mostCurrent._si = _menu.Add2((int) (1),(int) (3),BA.ObjectToCharSequence("جستجو"),mostCurrent._x1.GetDrawable("round_search_white_24"));
 //BA.debugLineNum = 110;BA.debugLine="SI.SearchView = sv";
mostCurrent._si.setSearchView(mostCurrent._sv);
 //BA.debugLineNum = 118;BA.debugLine="StateSpin.Initialize(\"StateSpin\")";
mostCurrent._statespin.Initialize(mostCurrent.activityBA,"StateSpin");
 //BA.debugLineNum = 119;BA.debugLine="StateSpin.TextColor=SaeloZahra.ColorLight";
mostCurrent._statespin.setTextColor(mostCurrent._saelozahra._colorlight /*int*/ );
 //BA.debugLineNum = 120;BA.debugLine="StateSpin.DropdownBackgroundColor=SaeloZahra.Colo";
mostCurrent._statespin.setDropdownBackgroundColor(mostCurrent._saelozahra._colorlight /*int*/ );
 //BA.debugLineNum = 121;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"فارس\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"فارس").getObject()));
 //BA.debugLineNum = 122;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"البرز\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"البرز").getObject()));
 //BA.debugLineNum = 123;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"اصفهان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"اصفهان").getObject()));
 //BA.debugLineNum = 124;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"اردبيل\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"اردبيل").getObject()));
 //BA.debugLineNum = 125;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"ايلام\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ايلام").getObject()));
 //BA.debugLineNum = 126;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"آذربايجان شرقي";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"آذربايجان شرقي").getObject()));
 //BA.debugLineNum = 127;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"آذربايجان غربي";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"آذربايجان غربي").getObject()));
 //BA.debugLineNum = 128;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"بوشهر\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"بوشهر").getObject()));
 //BA.debugLineNum = 129;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"تهران\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"تهران").getObject()));
 //BA.debugLineNum = 130;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"چهارمحال وبختي";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"چهارمحال وبختياري").getObject()));
 //BA.debugLineNum = 131;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خراسان جنوبي\")";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خراسان جنوبي").getObject()));
 //BA.debugLineNum = 132;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خراسان رضوي\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خراسان رضوي").getObject()));
 //BA.debugLineNum = 133;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خراسان شمالي\")";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خراسان شمالي").getObject()));
 //BA.debugLineNum = 134;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خوزستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خوزستان").getObject()));
 //BA.debugLineNum = 135;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"زنجان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"زنجان").getObject()));
 //BA.debugLineNum = 136;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"سمنان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سمنان").getObject()));
 //BA.debugLineNum = 137;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"سيستان وبلوچست";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سيستان وبلوچستان").getObject()));
 //BA.debugLineNum = 138;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"قزوين\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"قزوين").getObject()));
 //BA.debugLineNum = 139;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"قم\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"قم").getObject()));
 //BA.debugLineNum = 140;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كردستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كردستان").getObject()));
 //BA.debugLineNum = 141;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كرمان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كرمان").getObject()));
 //BA.debugLineNum = 142;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كرمانشاه\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كرمانشاه").getObject()));
 //BA.debugLineNum = 143;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كهگيلويه وبوير";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كهگيلويه وبويراحمد").getObject()));
 //BA.debugLineNum = 144;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"گلستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"گلستان").getObject()));
 //BA.debugLineNum = 145;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"گيلان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"گيلان").getObject()));
 //BA.debugLineNum = 146;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"لرستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"لرستان").getObject()));
 //BA.debugLineNum = 147;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"مازندران\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"مازندران").getObject()));
 //BA.debugLineNum = 148;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"مركزي\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"مركزي").getObject()));
 //BA.debugLineNum = 149;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"هرمزگان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"هرمزگان").getObject()));
 //BA.debugLineNum = 150;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"همدان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"همدان").getObject()));
 //BA.debugLineNum = 151;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"يزد\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"يزد").getObject()));
 //BA.debugLineNum = 153;BA.debugLine="ToolBar.AddView(StateSpin,SaeloZahra.MaterialActi";
mostCurrent._toolbar.AddView((android.view.View)(mostCurrent._statespin.getObject()),(int) (mostCurrent._saelozahra._materialactionbarheight /*int*/ *2.2),mostCurrent._saelozahra._materialactionbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 155;BA.debugLine="CitySpin.Initialize(\"CitySpin\")";
mostCurrent._cityspin.Initialize(mostCurrent.activityBA,"CitySpin");
 //BA.debugLineNum = 156;BA.debugLine="CitySpin.DropdownBackgroundColor=SaeloZahra.Color";
mostCurrent._cityspin.setDropdownBackgroundColor(mostCurrent._saelozahra._colorlight /*int*/ );
 //BA.debugLineNum = 157;BA.debugLine="CitySpin.Visible=False";
mostCurrent._cityspin.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 158;BA.debugLine="ToolBar.AddView(CitySpin,SaeloZahra.MaterialActio";
mostCurrent._toolbar.AddView((android.view.View)(mostCurrent._cityspin.getObject()),(int) (mostCurrent._saelozahra._materialactionbarheight /*int*/ *2.2),mostCurrent._saelozahra._materialactionbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 60;BA.debugLine="If KeyCode==KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 61;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 62;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 64;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _cityspin_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 222;BA.debugLine="Sub CitySpin_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 223;BA.debugLine="ToastMessageShow(\"پیدا کردن نامزدهای \"&Value,True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("پیدا کردن نامزدهای "+BA.ObjectToString(_value)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim Config 	As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 14;BA.debugLine="Dim Show 	As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 15;BA.debugLine="Private ToolBar As ACToolBarLight";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarLightWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private PanelCandidate As Panel";
mostCurrent._panelcandidate = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim sv As ACSearchView";
mostCurrent._sv = new de.amberhome.objects.appcompat.ACSearchViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim SI As ACMenuItem";
mostCurrent._si = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim X1 As XmlLayoutBuilder";
mostCurrent._x1 = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 20;BA.debugLine="Dim StateSpin,CitySpin As ACSpinner";
mostCurrent._statespin = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
mostCurrent._cityspin = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _panelcandidate_click() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub PanelCandidate_Click";
 //BA.debugLineNum = 52;BA.debugLine="SingleCandidateAct.CandidateID=6";
mostCurrent._singlecandidateact._candidateid /*String*/  = BA.NumberToString(6);
 //BA.debugLineNum = 53;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 54;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 55;BA.debugLine="StartActivity(SingleCandidateAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._singlecandidateact.getObject()));
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim SQL1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 9;BA.debugLine="Dim CU1 As Cursor";
_cu1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static void  _search_querysubmitted(String _query) throws Exception{
ResumableSub_Search_QuerySubmitted rsub = new ResumableSub_Search_QuerySubmitted(null,_query);
rsub.resume(processBA, null);
}
public static class ResumableSub_Search_QuerySubmitted extends BA.ResumableSub {
public ResumableSub_Search_QuerySubmitted(ir.saelozahra.snd1400.candidalistact parent,String _query) {
this.parent = parent;
this._query = _query;
}
ir.saelozahra.snd1400.candidalistact parent;
String _query;
String _keyword = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 166;BA.debugLine="SaeloZahra.P.HideKeyboard(Activity)";
parent.mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .HideKeyboard(parent.mostCurrent._activity);
 //BA.debugLineNum = 167;BA.debugLine="Sleep(110)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (110));
this.state = 5;
return;
case 5:
//C
this.state = 1;
;
 //BA.debugLineNum = 168;BA.debugLine="sv.Iconfied = True";
parent.mostCurrent._sv.setIconfied(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 169;BA.debugLine="SI.ItemCollapsed = True";
parent.mostCurrent._si.setItemCollapsed(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 171;BA.debugLine="Log(\"Search for '\" & Query & \"'\")";
anywheresoftware.b4a.keywords.Common.LogImpl("65242887","Search for '"+_query+"'",0);
 //BA.debugLineNum = 173;BA.debugLine="Dim KeyWord As String = Query.Trim";
_keyword = _query.trim();
 //BA.debugLineNum = 174;BA.debugLine="If KeyWord = \"\" Then";
if (true) break;

case 1:
//if
this.state = 4;
if ((_keyword).equals("")) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 175;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 176;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 183;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
parent.mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 184;BA.debugLine="Show.convertActivityFromTranslucent";
parent.mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 186;BA.debugLine="ToastMessageShow(KeyWord,False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_keyword),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _statespin_itemclick(int _position,Object _value) throws Exception{
String _txt = "";
int _i = 0;
 //BA.debugLineNum = 203;BA.debugLine="Sub StateSpin_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 204;BA.debugLine="CitySpin.Clear";
mostCurrent._cityspin.Clear();
 //BA.debugLineNum = 205;BA.debugLine="CitySpin.SetVisibleAnimated(313,True)";
mostCurrent._cityspin.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 206;BA.debugLine="Dim txt As String = SaeloZahra.CSBWhite(Value).To";
_txt = mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,BA.ObjectToString(_value)).ToString();
 //BA.debugLineNum = 207;BA.debugLine="Try";
try { //BA.debugLineNum = 208;BA.debugLine="If SQL1.IsInitialized = False Then";
if (_sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 209;BA.debugLine="SQL1.Initialize(SaeloZahra.dir,\"db.db\",False)";
_sql1.Initialize(mostCurrent._saelozahra._dir /*String*/ ,"db.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 211;BA.debugLine="CU1 = SQL1.ExecQuery(\"SELECT * FROM ostanha WHER";
_cu1 = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery("SELECT * FROM ostanha WHERE ostanha.state = '"+_txt+"'")));
 //BA.debugLineNum = 212;BA.debugLine="For i = 0 To CU1.RowCount-1";
{
final int step9 = 1;
final int limit9 = (int) (_cu1.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
 //BA.debugLineNum = 213;BA.debugLine="CU1.Position = i";
_cu1.setPosition(_i);
 //BA.debugLineNum = 214;BA.debugLine="CitySpin.Add(SaeloZahra.CSBWhite(CU1.GetString(";
mostCurrent._cityspin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,_cu1.GetString("city")).getObject()));
 }
};
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 217;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("65373966",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 194;BA.debugLine="Log(Item.Id)";
anywheresoftware.b4a.keywords.Common.LogImpl("65308417",BA.NumberToString(_item.getId()),0);
 //BA.debugLineNum = 195;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (0))) {
case 0: {
 break; }
}
;
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 69;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 70;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
	public boolean _onCreateOptionsMenu(android.view.Menu menu) {
		if (processBA.subExists("activity_createmenu")) {
			processBA.raiseEvent2(null, true, "activity_createmenu", false, new de.amberhome.objects.appcompat.ACMenuWrapper(menu));
			return true;
		}
		else
			return false;
	}
}
