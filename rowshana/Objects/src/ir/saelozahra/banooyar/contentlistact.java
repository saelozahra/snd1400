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

public class contentlistact extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static contentlistact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.contentlistact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (contentlistact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.contentlistact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.banooyar.contentlistact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (contentlistact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (contentlistact) Resume **");
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
		return contentlistact.class;
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
            BA.LogInfo("** Activity (contentlistact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (contentlistact) Pause event (activity is not paused). **");
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
            contentlistact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (contentlistact) Resume **");
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
public static anywheresoftware.b4a.objects.IntentWrapper _share = null;
public static String _type_str = "";
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public anywheresoftware.b4a.objects.StringUtils _su = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _x1 = null;
public de.amberhome.objects.appcompat.ACToolbarLightWrapper _toolbar = null;
public static String _css_start = "";
public static String _css_end = "";
public anywheresoftware.b4a.phone.Phone.PhoneIntents _web = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public static int _stopintnotif = 0;
public de.amberhome.objects.appcompat.ACSearchViewWrapper _sv = null;
public de.amberhome.objects.appcompat.ACMenuItemWrapper _si = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _statespin = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cityspin = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.banooyar.main _main = null;
public ir.saelozahra.banooyar.homeact _homeact = null;
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
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 44;BA.debugLine="css_start	= \"<html><body style='white-space: pre-";
mostCurrent._css_start = "<html><body style='white-space: pre-wrap;box-sizing: border-box; padding:2%; margin: auto;text-align: right; width:100%;max-width:100%;direction:rtl;line-height:1.4;font-family:samim;font-size:110%;color:#777;'>           <style>@import url(https://cdn.rawgit.com/rastikerdar/samim-font/v3.1.0/dist/font-face.css); h1{font-size:130%;margin:3% auto;} hr, .hr {background: rgba(0, 0, 0, 0) url('file:///android_asset/sp.png') repeat-x scroll center center; border: 0 none;clear: both;height: 19px;margin: 8px auto;width: 100%;} a{color:#111;font-weight: bold;} .img{max-width:100%;}</style>       <pre style='white-space: pre-wrap;width:100%;font-family:Samim;'>";
 //BA.debugLineNum = 45;BA.debugLine="css_end		= \"</pre><br style='clear:both;' > </bod";
mostCurrent._css_end = "</pre><br style='clear:both;' > </body></html>";
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"SVLayout\")";
mostCurrent._activity.LoadLayout("SVLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 50;BA.debugLine="SaeloZahra.SetToolbarStyle(ToolBar,\"راهنمای زائر\"";
mostCurrent._saelozahra._settoolbarstyle /*String*/ (mostCurrent.activityBA,mostCurrent._toolbar,"راهنمای زائر",anywheresoftware.b4a.keywords.Common.True,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._scrollview1.getObject())));
 //BA.debugLineNum = 51;BA.debugLine="ToolBar.TitleTextColor=Colors.White";
mostCurrent._toolbar.setTitleTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 53;BA.debugLine="If File.Exists(SaeloZahra.dir,\"db.db\") = False Th";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"db.db")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 54;BA.debugLine="File.Copy(File.DirAssets,\"db.db\",SaeloZahra.dir,";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"db.db",mostCurrent._saelozahra._dir /*String*/ ,"db.db");
 };
 //BA.debugLineNum = 58;BA.debugLine="If sql1.IsInitialized = False Then";
if (_sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 59;BA.debugLine="sql1.Initialize(SaeloZahra.dir,\"db.db\",False)";
_sql1.Initialize(mostCurrent._saelozahra._dir /*String*/ ,"db.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 63;BA.debugLine="LoadMsgs";
_loadmsgs();
 //BA.debugLineNum = 66;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
 //BA.debugLineNum = 67;BA.debugLine="Config.Initialize";
mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 68;BA.debugLine="Config.position(Config.POSITION_LEFT)";
mostCurrent._config.position(mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 69;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
mostCurrent._config.primaryColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 70;BA.debugLine="Config.edge(True)";
mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 71;BA.debugLine="Config.sensitivity(100)";
mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 72;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 74;BA.debugLine="Show.convertActivityToTranslucent";
mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 75;BA.debugLine="Show.attachActivity(Config)";
mostCurrent._show.attachActivity(mostCurrent.activityBA,mostCurrent._config);
 };
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 331;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 333;BA.debugLine="Log(\"Create Menu\")";
anywheresoftware.b4a.keywords.Common.LogImpl("42097154","Create Menu",0);
 //BA.debugLineNum = 335;BA.debugLine="sv.Initialize2(\"search\", sv.THEME_DARK)";
mostCurrent._sv.Initialize2(mostCurrent.activityBA,"search",mostCurrent._sv.THEME_DARK);
 //BA.debugLineNum = 336;BA.debugLine="sv.IconifiedByDefault = True";
mostCurrent._sv.setIconifiedByDefault(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 338;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 339;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 340;BA.debugLine="sv.QueryHint=SaeloZahra.csb(\"نام کاندیدای را وارد";
mostCurrent._sv.setQueryHint(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"نام کاندیدای را وارد کنید").getObject()));
 //BA.debugLineNum = 341;BA.debugLine="SI = Menu.Add2(1, 3, \"جستجو\",X1.GetDrawable(\"roun";
mostCurrent._si = _menu.Add2((int) (1),(int) (3),BA.ObjectToCharSequence("جستجو"),mostCurrent._x1.GetDrawable("round_search_white_24"));
 //BA.debugLineNum = 342;BA.debugLine="SI.SearchView = sv";
mostCurrent._si.setSearchView(mostCurrent._sv);
 //BA.debugLineNum = 350;BA.debugLine="StateSpin.Initialize(\"StateSpin\")";
mostCurrent._statespin.Initialize(mostCurrent.activityBA,"StateSpin");
 //BA.debugLineNum = 351;BA.debugLine="StateSpin.TextColor=SaeloZahra.ColorLight";
mostCurrent._statespin.setTextColor(mostCurrent._saelozahra._colorlight /*int*/ );
 //BA.debugLineNum = 352;BA.debugLine="StateSpin.DropdownBackgroundColor=SaeloZahra.Colo";
mostCurrent._statespin.setDropdownBackgroundColor(mostCurrent._saelozahra._colorlight /*int*/ );
 //BA.debugLineNum = 353;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"فارس\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"فارس").getObject()));
 //BA.debugLineNum = 354;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"البرز\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"البرز").getObject()));
 //BA.debugLineNum = 355;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"اصفهان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"اصفهان").getObject()));
 //BA.debugLineNum = 356;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"اردبيل\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"اردبيل").getObject()));
 //BA.debugLineNum = 357;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"ايلام\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"ايلام").getObject()));
 //BA.debugLineNum = 358;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"آذربايجان شرقي";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"آذربايجان شرقي").getObject()));
 //BA.debugLineNum = 359;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"آذربايجان غربي";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"آذربايجان غربي").getObject()));
 //BA.debugLineNum = 360;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"بوشهر\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"بوشهر").getObject()));
 //BA.debugLineNum = 361;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"تهران\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"تهران").getObject()));
 //BA.debugLineNum = 362;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"چهارمحال وبختي";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"چهارمحال وبختياري").getObject()));
 //BA.debugLineNum = 363;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خراسان جنوبي\")";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خراسان جنوبي").getObject()));
 //BA.debugLineNum = 364;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خراسان رضوي\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خراسان رضوي").getObject()));
 //BA.debugLineNum = 365;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خراسان شمالي\")";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خراسان شمالي").getObject()));
 //BA.debugLineNum = 366;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"خوزستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خوزستان").getObject()));
 //BA.debugLineNum = 367;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"زنجان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"زنجان").getObject()));
 //BA.debugLineNum = 368;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"سمنان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سمنان").getObject()));
 //BA.debugLineNum = 369;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"سيستان وبلوچست";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"سيستان وبلوچستان").getObject()));
 //BA.debugLineNum = 370;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"قزوين\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"قزوين").getObject()));
 //BA.debugLineNum = 371;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"قم\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"قم").getObject()));
 //BA.debugLineNum = 372;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كردستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كردستان").getObject()));
 //BA.debugLineNum = 373;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كرمان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كرمان").getObject()));
 //BA.debugLineNum = 374;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كرمانشاه\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كرمانشاه").getObject()));
 //BA.debugLineNum = 375;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"كهگيلويه وبوير";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"كهگيلويه وبويراحمد").getObject()));
 //BA.debugLineNum = 376;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"گلستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"گلستان").getObject()));
 //BA.debugLineNum = 377;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"گيلان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"گيلان").getObject()));
 //BA.debugLineNum = 378;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"لرستان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"لرستان").getObject()));
 //BA.debugLineNum = 379;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"مازندران\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"مازندران").getObject()));
 //BA.debugLineNum = 380;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"مركزي\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"مركزي").getObject()));
 //BA.debugLineNum = 381;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"هرمزگان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"هرمزگان").getObject()));
 //BA.debugLineNum = 382;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"همدان\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"همدان").getObject()));
 //BA.debugLineNum = 383;BA.debugLine="StateSpin.Add(SaeloZahra.CSBWhite(\"يزد\"))";
mostCurrent._statespin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"يزد").getObject()));
 //BA.debugLineNum = 385;BA.debugLine="ToolBar.AddView(StateSpin,SaeloZahra.MaterialActi";
mostCurrent._toolbar.AddView((android.view.View)(mostCurrent._statespin.getObject()),(int) (mostCurrent._saelozahra._materialactionbarheight /*int*/ *2.2),mostCurrent._saelozahra._materialactionbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 387;BA.debugLine="CitySpin.Initialize(\"CitySpin\")";
mostCurrent._cityspin.Initialize(mostCurrent.activityBA,"CitySpin");
 //BA.debugLineNum = 388;BA.debugLine="CitySpin.DropdownBackgroundColor=SaeloZahra.Color";
mostCurrent._cityspin.setDropdownBackgroundColor(mostCurrent._saelozahra._colorlight /*int*/ );
 //BA.debugLineNum = 389;BA.debugLine="CitySpin.Visible=False";
mostCurrent._cityspin.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 390;BA.debugLine="ToolBar.AddView(CitySpin,SaeloZahra.MaterialActio";
mostCurrent._toolbar.AddView((android.view.View)(mostCurrent._cityspin.getObject()),(int) (mostCurrent._saelozahra._materialactionbarheight /*int*/ *2.2),mostCurrent._saelozahra._materialactionbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 392;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 200;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 201;BA.debugLine="If KeyCode==KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 202;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 203;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 205;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 188;BA.debugLine="If cu1.IsInitialized Then cu1.Close";
if (_cu1.IsInitialized()) { 
_cu1.Close();};
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in1 = null;
int _i = 0;
 //BA.debugLineNum = 113;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 116;BA.debugLine="Dim in1 As Intent";
_in1 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 118;BA.debugLine="in1 = Activity.GetStartingIntent";
_in1 = mostCurrent._activity.GetStartingIntent();
 //BA.debugLineNum = 119;BA.debugLine="If in1.HasExtra(\"Notification_Tag\") Then";
if (_in1.HasExtra("Notification_Tag")) { 
 //BA.debugLineNum = 120;BA.debugLine="Log(in1.GetExtra(\"Notification_Tag\")) 'Will log";
anywheresoftware.b4a.keywords.Common.LogImpl("41441799",BA.ObjectToString(_in1.GetExtra("Notification_Tag")),0);
 //BA.debugLineNum = 121;BA.debugLine="If SaeloZahra.Debug Then ToastMessageShow(in1.Ge";
if (mostCurrent._saelozahra._debug /*boolean*/ ) { 
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_in1.GetExtra("Notification_Tag")),anywheresoftware.b4a.keywords.Common.True);};
 }else {
 //BA.debugLineNum = 123;BA.debugLine="If SaeloZahra.Debug Then ToastMessageShow(\"Dont";
if (mostCurrent._saelozahra._debug /*boolean*/ ) { 
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Dont Has Extra"),anywheresoftware.b4a.keywords.Common.True);};
 };
 //BA.debugLineNum = 126;BA.debugLine="If in1.HasExtra(\"Notification_Tag\") Then";
if (_in1.HasExtra("Notification_Tag")) { 
 //BA.debugLineNum = 130;BA.debugLine="cu1 = sql1.ExecQuery(\"SELECT * FROM notification";
_cu1 = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery("SELECT * FROM notification ORDER BY id DESC limit 1")));
 //BA.debugLineNum = 133;BA.debugLine="For i = 0 To cu1.RowCount-1";
{
final int step11 = 1;
final int limit11 = (int) (_cu1.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 135;BA.debugLine="cu1.Position = i";
_cu1.setPosition(_i);
 //BA.debugLineNum = 137;BA.debugLine="type_str = cu1.GetString(\"type\")";
_type_str = _cu1.GetString("type");
 //BA.debugLineNum = 140;BA.debugLine="If type_str == \"telegram\" Then";
if ((_type_str).equals("telegram")) { 
 //BA.debugLineNum = 141;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 142;BA.debugLine="open_telegram(cu1.GetString(\"value\"))";
_open_telegram(_cu1.GetString("value"));
 };
 //BA.debugLineNum = 145;BA.debugLine="If type_str == \"url\" Then";
if ((_type_str).equals("url")) { 
 //BA.debugLineNum = 146;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 147;BA.debugLine="StartActivity(Web.OpenBrowser(cu1.GetString(\"v";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._web.OpenBrowser(_cu1.GetString("value"))));
 };
 //BA.debugLineNum = 150;BA.debugLine="If type_str == \"activity\" Then";
if ((_type_str).equals("activity")) { 
 //BA.debugLineNum = 151;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 152;BA.debugLine="StartActivity(cu1.GetString(\"value\"))";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_cu1.GetString("value")));
 };
 }
};
 }else {
 };
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _addalertbox(String _index,anywheresoftware.b4a.objects.CSBuilder _text,long _time) throws Exception{
int _mycolor = 0;
anywheresoftware.b4a.objects.drawable.GradientDrawable _cdpanel = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
ir.hitexroid.material.x.Label _lbl = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
ir.hitexroid.material.x.Label _timelbl = null;
ir.hitexroid.material.x.Label _linelbl = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdbubble = null;
ir.hitexroid.material.x.Label _bubblelbl = null;
ir.hitexroid.material.x.ImageView _imgv = null;
int _minheight = 0;
 //BA.debugLineNum = 225;BA.debugLine="Sub addAlertBox(Index As String, Text As CSBuilder";
 //BA.debugLineNum = 230;BA.debugLine="Dim MyColor As Int = Colors.ARGB(255,Rnd(0,255),R";
_mycolor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (255)),anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (255)),anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (255)));
 //BA.debugLineNum = 232;BA.debugLine="Dim cdPanel As GradientDrawable";
_cdpanel = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 233;BA.debugLine="cdPanel.Initialize(\"TR_BL\", Array As Int(Colors.W";
_cdpanel.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TR_BL"),new int[]{anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.White});
 //BA.debugLineNum = 234;BA.debugLine="cdPanel.CornerRadius = 12dip";
_cdpanel.setCornerRadius((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))));
 //BA.debugLineNum = 236;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 237;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 238;BA.debugLine="pnl.Tag = Index";
_pnl.setTag((Object)(_index));
 //BA.debugLineNum = 239;BA.debugLine="pnl.Background=cdPanel";
_pnl.setBackground((android.graphics.drawable.Drawable)(_cdpanel.getObject()));
 //BA.debugLineNum = 240;BA.debugLine="pnl.Elevation=12dip";
_pnl.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))));
 //BA.debugLineNum = 242;BA.debugLine="Dim lbl As Label";
_lbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 243;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(processBA,"lbl");
 //BA.debugLineNum = 244;BA.debugLine="lbl.Tag  = Text";
_lbl.setTag((Object)(_text.getObject()));
 //BA.debugLineNum = 245;BA.debugLine="lbl.Text = Text";
_lbl.setText(BA.ObjectToCharSequence(_text.getObject()));
 //BA.debugLineNum = 246;BA.debugLine="lbl.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 249;BA.debugLine="lbl.Typeface = SaeloZahra.font";
_lbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 250;BA.debugLine="pnl.AddView(lbl, 10dip, 10dip, Activity.Width - (";
_pnl.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._activity.getWidth()-(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA))-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 253;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 254;BA.debugLine="cd.Initialize(MyColor,7dip)";
_cd.Initialize(_mycolor,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
 //BA.debugLineNum = 256;BA.debugLine="Dim timeLbl As Label";
_timelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 257;BA.debugLine="timeLbl.Initialize(\"\")";
_timelbl.Initialize(processBA,"");
 //BA.debugLineNum = 258;BA.debugLine="timeLbl.Background	= cd";
_timelbl.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 260;BA.debugLine="timeLbl.Text	 	= SaeloZahra.mohasebe_tarikh(Time)";
_timelbl.setText(BA.ObjectToCharSequence(mostCurrent._saelozahra._mohasebe_tarikh /*String*/ (mostCurrent.activityBA,_time)));
 //BA.debugLineNum = 264;BA.debugLine="timeLbl.TextSize	= 12";
_timelbl.setTextSize((float) (12));
 //BA.debugLineNum = 265;BA.debugLine="timeLbl.SingleLine=True";
_timelbl.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 266;BA.debugLine="timeLbl.Ellipsize = \"END\"";
_timelbl.setEllipsize("END");
 //BA.debugLineNum = 267;BA.debugLine="timeLbl.Gravity		= Bit.Or(Gravity.CENTER_VERTICAL";
_timelbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL));
 //BA.debugLineNum = 268;BA.debugLine="timeLbl.textColor	= Colors.White";
_timelbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 269;BA.debugLine="pnl.AddView(timeLbl,12dip,12dip,88dip,18dip)";
_pnl.AddView((android.view.View)(_timelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18)));
 //BA.debugLineNum = 272;BA.debugLine="Dim LineLbl As Label";
_linelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 273;BA.debugLine="LineLbl.Initialize(\"\")";
_linelbl.Initialize(processBA,"");
 //BA.debugLineNum = 274;BA.debugLine="LineLbl.Color=Colors.LightGray";
_linelbl.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 275;BA.debugLine="pnl.AddView(LineLbl, Activity.Width - (25%x)+7dip";
_pnl.AddView((android.view.View)(_linelbl.getObject()),(int) (mostCurrent._activity.getWidth()-(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 277;BA.debugLine="Dim cdBubble As ColorDrawable";
_cdbubble = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 278;BA.debugLine="cdBubble.Initialize2(MyColor,14dip,2dip,SaeloZahr";
_cdbubble.Initialize2(_mycolor,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 280;BA.debugLine="Dim bubbleLbl As Label";
_bubblelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 281;BA.debugLine="bubbleLbl.Initialize(\"\")";
_bubblelbl.Initialize(processBA,"");
 //BA.debugLineNum = 282;BA.debugLine="bubbleLbl.Background=cdBubble";
_bubblelbl.setBackground((android.graphics.drawable.Drawable)(_cdbubble.getObject()));
 //BA.debugLineNum = 283;BA.debugLine="pnl.AddView(bubbleLbl, Activity.Width - (25%x), 1";
_pnl.AddView((android.view.View)(_bubblelbl.getObject()),(int) (mostCurrent._activity.getWidth()-(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (25),mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)));
 //BA.debugLineNum = 286;BA.debugLine="Dim ImgV As ImageView";
_imgv = new ir.hitexroid.material.x.ImageView();
 //BA.debugLineNum = 287;BA.debugLine="ImgV.Initialize(\"\")";
_imgv.Initialize(processBA,"");
 //BA.debugLineNum = 288;BA.debugLine="ImgV.Gravity = Gravity.CENTER";
_imgv.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 289;BA.debugLine="ImgV.Background = X1.GetDrawable(\"baseline_text_s";
_imgv.setBackground(mostCurrent._x1.GetDrawable("baseline_text_snippet_white_24"));
 //BA.debugLineNum = 290;BA.debugLine="pnl.AddView(ImgV, Activity.Width - 26%x,16dip,20%";
_pnl.AddView((android.view.View)(_imgv.getObject()),(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (26),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (16)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 293;BA.debugLine="Dim minHeight As Int";
_minheight = 0;
 //BA.debugLineNum = 294;BA.debugLine="minHeight 		= su.MeasureMultilineTextHeight(lbl,";
_minheight = (int) (mostCurrent._su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),BA.ObjectToCharSequence(_text.getObject()))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 295;BA.debugLine="lbl.Height  	= Max(50dip, minHeight)";
_lbl.setHeight((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_minheight)));
 //BA.debugLineNum = 296;BA.debugLine="ImgV.Height 	= Max(50dip, minHeight)";
_imgv.setHeight((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_minheight)));
 //BA.debugLineNum = 297;BA.debugLine="LineLbl.Height	= Max(50dip, minHeight)";
_linelbl.setHeight((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_minheight)));
 //BA.debugLineNum = 298;BA.debugLine="pnl.Height 		= Max(50dip, minHeight)+18dip";
_pnl.setHeight((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_minheight)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18))));
 //BA.debugLineNum = 300;BA.debugLine="Return pnl";
if (true) return _pnl;
 //BA.debugLineNum = 302;BA.debugLine="End Sub";
return null;
}
public static String  _cityspin_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 454;BA.debugLine="Sub CitySpin_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 455;BA.debugLine="ToastMessageShow(\"پیدا کردن نامزدهای \"&Value,True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("پیدا کردن نامزدهای "+BA.ObjectToString(_value)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 456;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim Config 	As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 18;BA.debugLine="Dim Show 	As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 19;BA.debugLine="Dim su 		As StringUtils";
mostCurrent._su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 20;BA.debugLine="Dim X1	 	As XmlLayoutBuilder";
mostCurrent._x1 = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 21;BA.debugLine="Dim ToolBar As ACToolBarLight";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarLightWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Public css_start,css_end As String";
mostCurrent._css_start = "";
mostCurrent._css_end = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim Web 	As PhoneIntents";
mostCurrent._web = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 24;BA.debugLine="Dim ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim StopintNotif As Int=18dip";
_stopintnotif = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18));
 //BA.debugLineNum = 32;BA.debugLine="Dim sv As ACSearchView";
mostCurrent._sv = new de.amberhome.objects.appcompat.ACSearchViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim SI As ACMenuItem";
mostCurrent._si = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim X1 As XmlLayoutBuilder";
mostCurrent._x1 = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 35;BA.debugLine="Dim StateSpin,CitySpin As ACSpinner";
mostCurrent._statespin = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
mostCurrent._cityspin = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_click() throws Exception{
 //BA.debugLineNum = 304;BA.debugLine="Sub lbl_Click";
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return "";
}
public static String  _loadmsgs() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.CSBuilder _csbtxt = null;
String _thisicon = "";
anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
 //BA.debugLineNum = 80;BA.debugLine="Sub LoadMsgs";
 //BA.debugLineNum = 82;BA.debugLine="cu1 = sql1.ExecQuery(\"SELECT * FROM notification";
_cu1 = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery("SELECT * FROM notification WHERE type='content' ORDER BY id DESC limit 110")));
 //BA.debugLineNum = 85;BA.debugLine="For i = 0 To cu1.RowCount-1";
{
final int step2 = 1;
final int limit2 = (int) (_cu1.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 87;BA.debugLine="cu1.Position = i";
_cu1.setPosition(_i);
 //BA.debugLineNum = 88;BA.debugLine="Dim CsbTxt As CSBuilder";
_csbtxt = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 89;BA.debugLine="CsbTxt.Initialize";
_csbtxt.Initialize();
 //BA.debugLineNum = 90;BA.debugLine="CsbTxt.Bold.Typeface(SaeloZahra.font).Size(16).C";
_csbtxt.Bold().Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (16)).Color(anywheresoftware.b4a.keywords.Common.Colors.DarkGray).Append(BA.ObjectToCharSequence(_cu1.GetString("title"))).PopAll();
 //BA.debugLineNum = 91;BA.debugLine="CsbTxt.Append(CRLF)";
_csbtxt.Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF));
 //BA.debugLineNum = 92;BA.debugLine="CsbTxt.Append(CRLF)";
_csbtxt.Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF));
 //BA.debugLineNum = 93;BA.debugLine="CsbTxt.Typeface(SaeloZahra.font).Color(0xFF7C797";
_csbtxt.Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color((int) (0xff7c7979)).Size((int) (12)).Append(BA.ObjectToCharSequence(_cu1.GetString("value").replace("<br>",anywheresoftware.b4a.keywords.Common.CRLF).replace("<hr>","___________________________________"+anywheresoftware.b4a.keywords.Common.CRLF))).PopAll();
 //BA.debugLineNum = 95;BA.debugLine="Log(cu1.GetString(\"id\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("41376271",_cu1.GetString("id"),0);
 //BA.debugLineNum = 97;BA.debugLine="Dim ThisIcon As String = cu1.GetString(\"icon\")";
_thisicon = _cu1.GetString("icon");
 //BA.debugLineNum = 98;BA.debugLine="If ThisIcon==\"\" Then ThisIcon = 0";
if ((_thisicon).equals("")) { 
_thisicon = BA.NumberToString(0);};
 //BA.debugLineNum = 100;BA.debugLine="Dim P1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 101;BA.debugLine="P1 = addAlertBox(cu1.GetString(\"id\"),CsbTxt,cu1.";
_p1 = _addalertbox(_cu1.GetString("id"),_csbtxt,(long)(Double.parseDouble(_cu1.GetString("time"))));
 //BA.debugLineNum = 103;BA.debugLine="ScrollView1.Panel.AddView( P1, 20dip, StopintNot";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(_p1.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),_stopintnotif,(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),_p1.getHeight());
 //BA.debugLineNum = 104;BA.debugLine="StopintNotif = StopintNotif+P1.Height + 24dip";
_stopintnotif = (int) (_stopintnotif+_p1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)));
 //BA.debugLineNum = 105;BA.debugLine="ScrollView1.Panel.Height = StopintNotif +22dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (_stopintnotif+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22))));
 }
};
 //BA.debugLineNum = 109;BA.debugLine="ScrollView1.Panel.Height=StopintNotif+22dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (_stopintnotif+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22))));
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static boolean  _matn_overrideurl(String _url) throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub matn_OverrideUrl (Url As String) As Boolean";
 //BA.debugLineNum = 175;BA.debugLine="ProgressDialogShow2(\"چند لحظه صبر کنید\",True)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("چند لحظه صبر کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 176;BA.debugLine="StartActivity(Web.OpenBrowser(Url))";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._web.OpenBrowser(_url)));
 //BA.debugLineNum = 177;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return false;
}
public static String  _matn_pagefinished(String _url) throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Sub matn_PageFinished (Url As String)";
 //BA.debugLineNum = 182;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public static String  _open_telegram(String _tid) throws Exception{
 //BA.debugLineNum = 212;BA.debugLine="Sub open_telegram(tid As String)";
 //BA.debugLineNum = 213;BA.debugLine="Log(\"Open Telegram\")";
anywheresoftware.b4a.keywords.Common.LogImpl("41835009","Open Telegram",0);
 //BA.debugLineNum = 214;BA.debugLine="Try";
try { //BA.debugLineNum = 215;BA.debugLine="share.Initialize(share.ACTION_EDIT,\"tg://\"&tid)";
_share.Initialize(_share.ACTION_EDIT,"tg://"+_tid);
 //BA.debugLineNum = 216;BA.debugLine="StartActivity(share)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_share.getObject()));
 } 
       catch (Exception e6) {
			processBA.setLastException(e6); //BA.debugLineNum = 218;BA.debugLine="StartActivity(Web.OpenBrowser(\"https://t.me/\"&ti";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._web.OpenBrowser("https://t.me/"+_tid)));
 };
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _panelcandidate_click() throws Exception{
 //BA.debugLineNum = 309;BA.debugLine="Sub PanelCandidate_Click";
 //BA.debugLineNum = 311;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 312;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 313;BA.debugLine="StartActivity(SingleCandidateAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._singlecandidateact.getObject()));
 //BA.debugLineNum = 314;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim sql1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 10;BA.debugLine="Dim cu1 As Cursor";
_cu1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Dim share As Intent";
_share = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Dim type_str As String";
_type_str = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static void  _search_querysubmitted(String _query) throws Exception{
ResumableSub_Search_QuerySubmitted rsub = new ResumableSub_Search_QuerySubmitted(null,_query);
rsub.resume(processBA, null);
}
public static class ResumableSub_Search_QuerySubmitted extends BA.ResumableSub {
public ResumableSub_Search_QuerySubmitted(ir.saelozahra.banooyar.contentlistact parent,String _query) {
this.parent = parent;
this._query = _query;
}
ir.saelozahra.banooyar.contentlistact parent;
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
 //BA.debugLineNum = 398;BA.debugLine="SaeloZahra.P.HideKeyboard(Activity)";
parent.mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .HideKeyboard(parent.mostCurrent._activity);
 //BA.debugLineNum = 399;BA.debugLine="Sleep(110)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (110));
this.state = 5;
return;
case 5:
//C
this.state = 1;
;
 //BA.debugLineNum = 400;BA.debugLine="sv.Iconfied = True";
parent.mostCurrent._sv.setIconfied(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 401;BA.debugLine="SI.ItemCollapsed = True";
parent.mostCurrent._si.setItemCollapsed(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 403;BA.debugLine="Log(\"Search for '\" & Query & \"'\")";
anywheresoftware.b4a.keywords.Common.LogImpl("42162695","Search for '"+_query+"'",0);
 //BA.debugLineNum = 405;BA.debugLine="Dim KeyWord As String = Query.Trim";
_keyword = _query.trim();
 //BA.debugLineNum = 406;BA.debugLine="If KeyWord = \"\" Then";
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
 //BA.debugLineNum = 407;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 408;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 415;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
parent.mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 416;BA.debugLine="Show.convertActivityFromTranslucent";
parent.mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 418;BA.debugLine="ToastMessageShow(KeyWord,False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_keyword),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 420;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _statespin_itemclick(int _position,Object _value) throws Exception{
String _txt = "";
int _i = 0;
 //BA.debugLineNum = 435;BA.debugLine="Sub StateSpin_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 436;BA.debugLine="CitySpin.Clear";
mostCurrent._cityspin.Clear();
 //BA.debugLineNum = 437;BA.debugLine="CitySpin.SetVisibleAnimated(313,True)";
mostCurrent._cityspin.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 438;BA.debugLine="Dim txt As String = SaeloZahra.CSBWhite(Value).To";
_txt = mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,BA.ObjectToString(_value)).ToString();
 //BA.debugLineNum = 439;BA.debugLine="Try";
try { //BA.debugLineNum = 440;BA.debugLine="If SQL1.IsInitialized = False Then";
if (_sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 441;BA.debugLine="SQL1.Initialize(SaeloZahra.dir,\"db.db\",False)";
_sql1.Initialize(mostCurrent._saelozahra._dir /*String*/ ,"db.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 443;BA.debugLine="CU1 = SQL1.ExecQuery(\"SELECT * FROM ostanha WHER";
_cu1 = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery("SELECT * FROM ostanha WHERE ostanha.state = '"+_txt+"'")));
 //BA.debugLineNum = 444;BA.debugLine="For i = 0 To CU1.RowCount-1";
{
final int step9 = 1;
final int limit9 = (int) (_cu1.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
 //BA.debugLineNum = 445;BA.debugLine="CU1.Position = i";
_cu1.setPosition(_i);
 //BA.debugLineNum = 446;BA.debugLine="CitySpin.Add(SaeloZahra.CSBWhite(CU1.GetString(";
mostCurrent._cityspin.Add(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbwhite /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,_cu1.GetString("city")).getObject()));
 }
};
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 449;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("42293774",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 451;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 425;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 426;BA.debugLine="Log(Item.Id)";
anywheresoftware.b4a.keywords.Common.LogImpl("42228225",BA.NumberToString(_item.getId()),0);
 //BA.debugLineNum = 427;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (0))) {
case 0: {
 break; }
}
;
 //BA.debugLineNum = 431;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 195;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 196;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
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
