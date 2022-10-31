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

public class selectmapact extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static selectmapact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.selectmapact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (selectmapact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.selectmapact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.selectmapact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (selectmapact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (selectmapact) Resume **");
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
		return selectmapact.class;
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
            BA.LogInfo("** Activity (selectmapact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (selectmapact) Pause event (activity is not paused). **");
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
            selectmapact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (selectmapact) Resume **");
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
public static String _selectedmakanlatlng = "";
public static anywheresoftware.b4a.gps.LocationWrapper _mylocation = null;
public static anywheresoftware.b4a.objects.Timer _t = null;
public static anywheresoftware.b4a.objects.Timer _t2 = null;
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public anywheresoftware.b4a.objects.MapFragmentWrapper.GoogleMapWrapper _gmap = null;
public anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper _cp = null;
public anywheresoftware.b4a.objects.MapFragmentWrapper _mapfragment1 = null;
public uk.co.martinpearman.b4a.googlemapsextras.GoogleMapsExtras _gme = null;
public uk.co.martinpearman.b4a.com.google.android.gms.maps.googlemap.OnMyLocationChangeListener _onmylocationchangelistener1 = null;
public anywheresoftware.b4a.objects.MapFragmentWrapper.LatLngWrapper _latlng_old = null;
public ir.saelozahra.snd1400.doubletaptoclose _dttc = null;
public ir.saelozahra.snd1400.httpjob _get_my_address = null;
public static boolean _started = false;
public ir.hitexroid.material.x.Label _address_bar_lbl = null;
public static int _time = 0;
public static int _time2 = 0;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public ir.hitexroid.material.x.ImageView _markeriv = null;
public anywheresoftware.b4a.objects.ButtonWrapper _selectlocationbtn = null;
public de.amberhome.objects.appcompat.ACToolbarLightWrapper _toolbar = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.main _main = null;
public ir.saelozahra.snd1400.starter _starter = null;
public ir.saelozahra.snd1400.homeact _homeact = null;
public ir.saelozahra.snd1400.saelozahra _saelozahra = null;
public ir.saelozahra.snd1400.adminsact _adminsact = null;
public ir.saelozahra.snd1400.candidalistact _candidalistact = null;
public ir.saelozahra.snd1400.electionact _electionact = null;
public ir.saelozahra.snd1400.firebasemessaging _firebasemessaging = null;
public ir.saelozahra.snd1400.notificationact _notificationact = null;
public ir.saelozahra.snd1400.qrforscanact _qrforscanact = null;
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
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 42;BA.debugLine="Try";
try { //BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"MapLayout\")";
mostCurrent._activity.LoadLayout("MapLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="ToolBar.subTitleTextColor=SaeloZahra.ColorLightT";
mostCurrent._toolbar.setSubTitleTextColor(mostCurrent._saelozahra._colorlighttransparent /*int*/ );
 //BA.debugLineNum = 48;BA.debugLine="ToolBar.subTitle=SaeloZahra.CSB(\"موقعیت خود را ب";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"موقعیت خود را برای یافتن نزدیک‌ترین شعبه انتخاب کنید").getObject()));
 //BA.debugLineNum = 50;BA.debugLine="SaeloZahra.SetToolbarStyle(ToolBar,\"موقعیت مکانی";
mostCurrent._saelozahra._settoolbarstyle /*String*/ (mostCurrent.activityBA,mostCurrent._toolbar,"موقعیت مکانی",anywheresoftware.b4a.keywords.Common.True,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._panel1.getObject())));
 //BA.debugLineNum = 51;BA.debugLine="SelectLocationBTN.SetLayout(SelectLocationBTN.Le";
mostCurrent._selectlocationbtn.SetLayout(mostCurrent._selectlocationbtn.getLeft(),(int) (mostCurrent._selectlocationbtn.getTop()-mostCurrent._saelozahra._statusbarheight /*int*/ ),mostCurrent._selectlocationbtn.getWidth(),mostCurrent._selectlocationbtn.getHeight());
 //BA.debugLineNum = 53;BA.debugLine="address_bar_lbl.Color		= SaeloZahra.Color";
mostCurrent._address_bar_lbl.setColor(mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 54;BA.debugLine="address_bar_lbl.TextColor	= Colors.White";
mostCurrent._address_bar_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 55;BA.debugLine="address_bar_lbl.Typeface= SaeloZahra.font";
mostCurrent._address_bar_lbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 58;BA.debugLine="If MapFragment1.IsGooglePlayServicesAvailable =";
if (mostCurrent._mapfragment1.IsGooglePlayServicesAvailable(mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 59;BA.debugLine="ToastMessageShow(\"لطفا گوگل پلی را نصب کنید.\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا گوگل پلی را نصب کنید."),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 60;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 63;BA.debugLine="Activity.Title = SaeloZahra.CSBTitle(\"موقعیت مکا";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"موقعیت مکانی خود را انتخاب کنید").getObject()));
 //BA.debugLineNum = 64;BA.debugLine="myLocation.Initialize";
_mylocation.Initialize();
 //BA.debugLineNum = 66;BA.debugLine="If Not(File.Exists(SaeloZahra.dir,\"lat\")) Then";
if (anywheresoftware.b4a.keywords.Common.Not(anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"lat"))) { 
 //BA.debugLineNum = 67;BA.debugLine="File.WriteString(SaeloZahra.dir,\"lat\" ,\"29.6095";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"lat","29.609503");
 //BA.debugLineNum = 68;BA.debugLine="File.WriteString(SaeloZahra.dir,\"lng\",\"52.54280";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"lng","52.542800");
 };
 //BA.debugLineNum = 71;BA.debugLine="myLocation.Latitude =File.ReadString(SaeloZahra.";
_mylocation.setLatitude((double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"lat"))));
 //BA.debugLineNum = 72;BA.debugLine="myLocation.Longitude=File.ReadString(SaeloZahra.";
_mylocation.setLongitude((double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"lng"))));
 //BA.debugLineNum = 77;BA.debugLine="get_my_address.Initialize(	\"get_my_address\", Sta";
mostCurrent._get_my_address._initialize /*String*/ (processBA,"get_my_address",(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 80;BA.debugLine="t.Initialize(\"t\",100)";
_t.Initialize(processBA,"t",(long) (100));
 //BA.debugLineNum = 81;BA.debugLine="t.Enabled=True";
_t.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 82;BA.debugLine="T2.Initialize(\"T2\",100)";
_t2.Initialize(processBA,"T2",(long) (100));
 //BA.debugLineNum = 83;BA.debugLine="T2.Enabled=True";
_t2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 85;BA.debugLine="SelectLocationBTN.Typeface=SaeloZahra.fontBold";
mostCurrent._selectlocationbtn.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 86;BA.debugLine="SelectLocationBTN.TextColor=SaeloZahra.ColorDark";
mostCurrent._selectlocationbtn.setTextColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 89;BA.debugLine="MarkerIV.SetBackgroundImage(LoadBitmapResize(Fil";
mostCurrent._markeriv.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"location.png",mostCurrent._markeriv.getWidth(),mostCurrent._markeriv.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 91;BA.debugLine="DTTC.InItIaLiZe(\"مجددا دکمه بازگشت را فشار دهید\"";
mostCurrent._dttc._initialize /*String*/ (processBA,"مجددا دکمه بازگشت را فشار دهید");
 } 
       catch (Exception e32) {
			processBA.setLastException(e32); //BA.debugLineNum = 95;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("616646198",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 99;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
 //BA.debugLineNum = 100;BA.debugLine="Config.Initialize";
mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 101;BA.debugLine="Config.position(Config.POSITION_LEFT)";
mostCurrent._config.position(mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 102;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
mostCurrent._config.primaryColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 103;BA.debugLine="Config.edge(True)";
mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 104;BA.debugLine="Config.sensitivity(100)";
mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 105;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 106;BA.debugLine="Show.convertActivityToTranslucent";
mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 107;BA.debugLine="Show.attachActivity(Config)";
mostCurrent._show.attachActivity(mostCurrent.activityBA,mostCurrent._config);
 };
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 351;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 352;BA.debugLine="If KeyCode==KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 353;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 354;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 356;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 358;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 132;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 133;BA.debugLine="get_my_address.Release";
mostCurrent._get_my_address._release /*String*/ ();
 //BA.debugLineNum = 135;BA.debugLine="CallSubDelayed(Starter, \"StopGPS\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._starter.getObject()),"StopGPS");
 //BA.debugLineNum = 136;BA.debugLine="SaeloZahra.ActivePage=\"other\"";
mostCurrent._saelozahra._activepage /*String*/  = "other";
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static void  _activity_resume() throws Exception{
ResumableSub_Activity_Resume rsub = new ResumableSub_Activity_Resume(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Resume extends BA.ResumableSub {
public ResumableSub_Activity_Resume(ir.saelozahra.snd1400.selectmapact parent) {
this.parent = parent;
}
ir.saelozahra.snd1400.selectmapact parent;
String _permission = "";
boolean _result = false;

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
 //BA.debugLineNum = 114;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 22;
this.catchState = 21;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 21;
 //BA.debugLineNum = 115;BA.debugLine="If Starter.GPS1.GPSEnabled = False Then";
if (true) break;

case 4:
//if
this.state = 19;
if (parent.mostCurrent._starter._gps1 /*anywheresoftware.b4a.gps.GPS*/ .getGPSEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}else {
this.state = 12;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 116;BA.debugLine="ToastMessageShow(\"لطفا جی پی اس را فعال کنید\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا جی پی اس را فعال کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 117;BA.debugLine="If DialogResponse.POSITIVE == Msgbox2Async(\"لطف";
if (true) break;

case 7:
//if
this.state = 10;
if (anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE==(double)(BA.ObjectToNumber(anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("لطفا موقعیت یاب خود را روشن کنید"),BA.ObjectToCharSequence("موقعیت"),"روشن کن","لغو","",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False)))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 118;BA.debugLine="StartActivity(Starter.GPS1.LocationSettingsInt";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._starter._gps1 /*anywheresoftware.b4a.gps.GPS*/ .getLocationSettingsIntent()));
 if (true) break;

case 10:
//C
this.state = 19;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 121;BA.debugLine="Starter.rp.CheckAndRequest(Starter.rp.PERMISSIO";
parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 122;BA.debugLine="Wait For Activity_PermissionResult (Permission";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 23;
return;
case 23:
//C
this.state = 13;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 123;BA.debugLine="If Result Then CallSubDelayed(Starter, \"StartGP";
if (true) break;

case 13:
//if
this.state = 18;
if (_result) { 
this.state = 15;
;}if (true) break;

case 15:
//C
this.state = 18;
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(parent.mostCurrent._starter.getObject()),"StartGPS");
if (true) break;

case 18:
//C
this.state = 19;
;
 if (true) break;

case 19:
//C
this.state = 22;
;
 //BA.debugLineNum = 125;BA.debugLine="SaeloZahra.ActivePage=\"selectmap\"";
parent.mostCurrent._saelozahra._activepage /*String*/  = "selectmap";
 if (true) break;

case 21:
//C
this.state = 22;
this.catchState = 0;
 //BA.debugLineNum = 127;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("616711694",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 22:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
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
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _change_addressbar_text(String _text) throws Exception{
 //BA.debugLineNum = 177;BA.debugLine="Sub change_addressbar_text(text As String)";
 //BA.debugLineNum = 178;BA.debugLine="address_bar_lbl.Text=text";
mostCurrent._address_bar_lbl.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 179;BA.debugLine="address_bar_lbl.SetVisibleAnimated(313,True)";
mostCurrent._address_bar_lbl.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 181;BA.debugLine="time=0";
_time = (int) (0);
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim Config As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 16;BA.debugLine="Dim Show As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 17;BA.debugLine="Dim gmap As GoogleMap";
mostCurrent._gmap = new anywheresoftware.b4a.objects.MapFragmentWrapper.GoogleMapWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim cp As CameraPosition";
mostCurrent._cp = new anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim MapFragment1 As MapFragment";
mostCurrent._mapfragment1 = new anywheresoftware.b4a.objects.MapFragmentWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim gme As GoogleMapsExtras";
mostCurrent._gme = new uk.co.martinpearman.b4a.googlemapsextras.GoogleMapsExtras();
 //BA.debugLineNum = 21;BA.debugLine="Dim OnMyLocationChangeListener1 As OnMyLocationCh";
mostCurrent._onmylocationchangelistener1 = new uk.co.martinpearman.b4a.com.google.android.gms.maps.googlemap.OnMyLocationChangeListener();
 //BA.debugLineNum = 22;BA.debugLine="Dim latlng_old As LatLng";
mostCurrent._latlng_old = new anywheresoftware.b4a.objects.MapFragmentWrapper.LatLngWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim DTTC As DoubleTaptoClose";
mostCurrent._dttc = new ir.saelozahra.snd1400.doubletaptoclose();
 //BA.debugLineNum = 25;BA.debugLine="Dim get_my_address As HttpJob";
mostCurrent._get_my_address = new ir.saelozahra.snd1400.httpjob();
 //BA.debugLineNum = 26;BA.debugLine="Dim Started As Boolean = True";
_started = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 27;BA.debugLine="Private address_bar_lbl As Label";
mostCurrent._address_bar_lbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 28;BA.debugLine="Dim time,Time2 As Int";
_time = 0;
_time2 = 0;
 //BA.debugLineNum = 29;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private MarkerIV As ImageView";
mostCurrent._markeriv = new ir.hitexroid.material.x.ImageView();
 //BA.debugLineNum = 31;BA.debugLine="Private SelectLocationBTN As Button";
mostCurrent._selectlocationbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private ToolBar As ACToolBarLight";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarLightWrapper();
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _gotomylocation() throws Exception{
 //BA.debugLineNum = 153;BA.debugLine="Sub goToMyLocation";
 //BA.debugLineNum = 154;BA.debugLine="Try";
try { //BA.debugLineNum = 156;BA.debugLine="If Starter.gpsStarted Then";
if (mostCurrent._starter._gpsstarted /*boolean*/ ) { 
 //BA.debugLineNum = 157;BA.debugLine="cp.Initialize( myLocation.Latitude, myLocation.";
mostCurrent._cp.Initialize(_mylocation.getLatitude(),_mylocation.getLongitude(),mostCurrent._gme.GetMaxZoomLevel((com.google.android.gms.maps.GoogleMap)(mostCurrent._gmap.getObject())));
 //BA.debugLineNum = 158;BA.debugLine="gmap.AnimateCamera(cp)";
mostCurrent._gmap.AnimateCamera((com.google.android.gms.maps.model.CameraPosition)(mostCurrent._cp.getObject()));
 //BA.debugLineNum = 159;BA.debugLine="Log(\"Go To My Location\")";
anywheresoftware.b4a.keywords.Common.LogImpl("616842758","Go To My Location",0);
 //BA.debugLineNum = 160;BA.debugLine="CallSubDelayed(Starter, \"StopGps\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._starter.getObject()),"StopGps");
 };
 //BA.debugLineNum = 163;BA.debugLine="If Started Then";
if (_started) { 
 //BA.debugLineNum = 164;BA.debugLine="Started=False";
_started = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 167;BA.debugLine="If Starter.GPS1.GPSEnabled Then";
if (mostCurrent._starter._gps1 /*anywheresoftware.b4a.gps.GPS*/ .getGPSEnabled()) { 
 //BA.debugLineNum = 168;BA.debugLine="gmap.MyLocationEnabled=True";
mostCurrent._gmap.setMyLocationEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 } 
       catch (Exception e15) {
			processBA.setLastException(e15); //BA.debugLineNum = 172;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("616842771",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ir.saelozahra.snd1400.httpjob _job) throws Exception{
 //BA.debugLineNum = 302;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 303;BA.debugLine="Log(Job.JobName&\" | \"&Job.Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("617301505",_job._jobname /*String*/ +" | "+BA.ObjectToString(_job._success /*boolean*/ ),0);
 //BA.debugLineNum = 304;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 305;BA.debugLine="If Job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 306;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname /*String*/ )) {
}
;
 }else {
 //BA.debugLineNum = 310;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\"خطا در تکمیل در";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خطا در تکمیل درخواست...").getObject()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static void  _mapfragment1_camerachange(anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper _position) throws Exception{
ResumableSub_MapFragment1_CameraChange rsub = new ResumableSub_MapFragment1_CameraChange(null,_position);
rsub.resume(processBA, null);
}
public static class ResumableSub_MapFragment1_CameraChange extends BA.ResumableSub {
public ResumableSub_MapFragment1_CameraChange(ir.saelozahra.snd1400.selectmapact parent,anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper _position) {
this.parent = parent;
this._position = _position;
}
ir.saelozahra.snd1400.selectmapact parent;
anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper _position;

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
 //BA.debugLineNum = 196;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 16;
this.catchState = 15;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 15;
 //BA.debugLineNum = 198;BA.debugLine="If Time2>33 Then";
if (true) break;

case 4:
//if
this.state = 13;
if (parent._time2>33) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 200;BA.debugLine="Time2 = 0";
parent._time2 = (int) (0);
 //BA.debugLineNum = 201;BA.debugLine="MarkerIV.SetLayoutAnimated(10,MarkerIV.Left+5di";
parent.mostCurrent._markeriv.SetLayoutAnimated((int) (10),(int) (parent.mostCurrent._markeriv.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (parent.mostCurrent._markeriv.getTop()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18))),(int) (parent.mostCurrent._markeriv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (parent.mostCurrent._markeriv.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 202;BA.debugLine="MarkerIV.SetBackgroundImage(LoadBitmapResize(Fi";
parent.mostCurrent._markeriv.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"location.png",parent.mostCurrent._markeriv.getWidth(),parent.mostCurrent._markeriv.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 204;BA.debugLine="If SaeloZahra.CheckConnection Then";
if (true) break;

case 7:
//if
this.state = 12;
if (parent.mostCurrent._saelozahra._checkconnection /*boolean*/ (mostCurrent.activityBA)) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 205;BA.debugLine="get_my_address.Download(\"https://us1.locationi";
parent.mostCurrent._get_my_address._download /*String*/ ("https://us1.locationiq.com/v1/reverse.php?key=50f886904c60e7&lat="+BA.NumberToString(_position.getTarget().getLatitude())+"&lon="+BA.NumberToString(_position.getTarget().getLongitude())+"&accept-language=fa&format=json");
 //BA.debugLineNum = 207;BA.debugLine="LogColor(\"https://us1.locationiq.com/v1/revers";
anywheresoftware.b4a.keywords.Common.LogImpl("617104908","https://us1.locationiq.com/v1/reverse.php?key=50f886904c60e7&lat="+BA.NumberToString(_position.getTarget().getLatitude())+"&lon="+BA.NumberToString(_position.getTarget().getLongitude())+"&accept-language=fa&format=json",anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 209;BA.debugLine="address_bar_lbl.SetVisibleAnimated(313,False)";
parent.mostCurrent._address_bar_lbl.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 212;BA.debugLine="Sleep(50)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (50));
this.state = 17;
return;
case 17:
//C
this.state = 13;
;
 //BA.debugLineNum = 213;BA.debugLine="MarkerIV.SetLayoutAnimated(202,MarkerIV.Left-5d";
parent.mostCurrent._markeriv.SetLayoutAnimated((int) (202),(int) (parent.mostCurrent._markeriv.getLeft()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (parent.mostCurrent._markeriv.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18))),(int) (parent.mostCurrent._markeriv.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (parent.mostCurrent._markeriv.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 214;BA.debugLine="MarkerIV.SetBackgroundImage(LoadBitmapResize(Fi";
parent.mostCurrent._markeriv.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"location.png",parent.mostCurrent._markeriv.getWidth(),parent.mostCurrent._markeriv.getHeight(),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 216;BA.debugLine="MarkerIV.Tag=CreateMap(\"lat\":Position.Target.La";
parent.mostCurrent._markeriv.setTag((Object)(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("lat"),(Object)(_position.getTarget().getLatitude()),(Object)("lng"),(Object)(_position.getTarget().getLongitude())}).getObject()));
 if (true) break;

case 13:
//C
this.state = 16;
;
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 220;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("617104921",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
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
public static void  _mapfragment1_ready() throws Exception{
ResumableSub_MapFragment1_Ready rsub = new ResumableSub_MapFragment1_Ready(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_MapFragment1_Ready extends BA.ResumableSub {
public ResumableSub_MapFragment1_Ready(ir.saelozahra.snd1400.selectmapact parent) {
this.parent = parent;
}
ir.saelozahra.snd1400.selectmapact parent;
String _permission = "";
boolean _result = false;

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
 //BA.debugLineNum = 226;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 17;
this.catchState = 16;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 16;
 //BA.debugLineNum = 228;BA.debugLine="Log(\"map ready\")";
anywheresoftware.b4a.keywords.Common.LogImpl("617170435","map ready",0);
 //BA.debugLineNum = 230;BA.debugLine="gmap = MapFragment1.GetMap";
parent.mostCurrent._gmap = parent.mostCurrent._mapfragment1.GetMap();
 //BA.debugLineNum = 232;BA.debugLine="Starter.rp.CheckAndRequest(Starter.rp.PERMISSION";
parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 233;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 18;
return;
case 18:
//C
this.state = 4;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 234;BA.debugLine="If Result Then";
if (true) break;

case 4:
//if
this.state = 9;
if (_result) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 235;BA.debugLine="gmap.MyLocationEnabled = True";
parent.mostCurrent._gmap.setMyLocationEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 237;BA.debugLine="gmap.MyLocationEnabled = False";
parent.mostCurrent._gmap.setMyLocationEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 238;BA.debugLine="Log(\"No permission!\")";
anywheresoftware.b4a.keywords.Common.LogImpl("617170445","No permission!",0);
 if (true) break;
;
 //BA.debugLineNum = 242;BA.debugLine="If Not(gmap.IsInitialized) Then";

case 9:
//if
this.state = 14;
if (anywheresoftware.b4a.keywords.Common.Not(parent.mostCurrent._gmap.IsInitialized())) { 
this.state = 11;
}else {
this.state = 13;
}if (true) break;

case 11:
//C
this.state = 14;
 //BA.debugLineNum = 243;BA.debugLine="ToastMessageShow(\"نتوانستیم نقشه را نصب کنیم\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("نتوانستیم نقشه را نصب کنیم"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 245;BA.debugLine="latlng_old.Initialize(myLocation.Latitude,myLoc";
parent.mostCurrent._latlng_old.Initialize(parent._mylocation.getLatitude(),parent._mylocation.getLongitude());
 //BA.debugLineNum = 246;BA.debugLine="cp.Initialize( myLocation.Latitude, myLocation.";
parent.mostCurrent._cp.Initialize(parent._mylocation.getLatitude(),parent._mylocation.getLongitude(),(float) (17));
 //BA.debugLineNum = 247;BA.debugLine="gmap.AnimateCamera(cp)";
parent.mostCurrent._gmap.AnimateCamera((com.google.android.gms.maps.model.CameraPosition)(parent.mostCurrent._cp.getObject()));
 //BA.debugLineNum = 248;BA.debugLine="latlng_old.Initialize(myLocation.Latitude,myLoc";
parent.mostCurrent._latlng_old.Initialize(parent._mylocation.getLatitude(),parent._mylocation.getLongitude());
 //BA.debugLineNum = 250;BA.debugLine="Log(\"Go To My Location\")";
anywheresoftware.b4a.keywords.Common.LogImpl("617170457","Go To My Location",0);
 if (true) break;

case 14:
//C
this.state = 17;
;
 //BA.debugLineNum = 253;BA.debugLine="SaeloZahra.ChangeGooglemapStyle(File.ReadString(";
parent.mostCurrent._saelozahra._changegooglemapstyle /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"googlemapstyle.txt"),parent.mostCurrent._gmap);
 //BA.debugLineNum = 255;BA.debugLine="OnMyLocationChangeListener1.Initialize(\"OnMyLoca";
parent.mostCurrent._onmylocationchangelistener1.Initialize(processBA,"OnMyLocationChangeListener1");
 //BA.debugLineNum = 256;BA.debugLine="gme.SetOnMyLocationChangeListener( gmap , OnMyLo";
parent.mostCurrent._gme.SetOnMyLocationChangeListener((com.google.android.gms.maps.GoogleMap)(parent.mostCurrent._gmap.getObject()),(com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener)(parent.mostCurrent._onmylocationchangelistener1.getObject()));
 //BA.debugLineNum = 258;BA.debugLine="MarkerIV.Tag=CreateMap(\"lat\":myLocation.Latitude";
parent.mostCurrent._markeriv.setTag((Object)(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("lat"),(Object)(parent._mylocation.getLatitude()),(Object)("lng"),(Object)(parent._mylocation.getLongitude())}).getObject()));
 if (true) break;

case 16:
//C
this.state = 17;
this.catchState = 0;
 //BA.debugLineNum = 275;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("617170482",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 17:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 278;BA.debugLine="End Sub";
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
public static String  _onmylocationchangelistener1_mylocationchange(anywheresoftware.b4a.gps.LocationWrapper _location1) throws Exception{
 //BA.debugLineNum = 280;BA.debugLine="Sub OnMyLocationChangeListener1_MyLocationChange(L";
 //BA.debugLineNum = 281;BA.debugLine="myLocation = Location1";
_mylocation = _location1;
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim SelectedMakanlatlng As String";
_selectedmakanlatlng = "";
 //BA.debugLineNum = 10;BA.debugLine="Dim myLocation As Location";
_mylocation = new anywheresoftware.b4a.gps.LocationWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Dim t,T2 As Timer";
_t = new anywheresoftware.b4a.objects.Timer();
_t2 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _selectlocationbtn_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _point = null;
 //BA.debugLineNum = 314;BA.debugLine="Sub SelectLocationBTN_Click";
 //BA.debugLineNum = 315;BA.debugLine="Try";
try { //BA.debugLineNum = 317;BA.debugLine="Dim point As Map = MarkerIV.Tag";
_point = new anywheresoftware.b4a.objects.collections.Map();
_point = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(mostCurrent._markeriv.getTag()));
 //BA.debugLineNum = 319;BA.debugLine="If gmap.IsInitialized Then";
if (mostCurrent._gmap.IsInitialized()) { 
 //BA.debugLineNum = 321;BA.debugLine="File.WriteString(SaeloZahra.dir,\"place_lat\",poi";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"place_lat",BA.ObjectToString(_point.Get((Object)("lat"))));
 //BA.debugLineNum = 322;BA.debugLine="File.WriteString(SaeloZahra.dir,\"place_lng\",poi";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"place_lng",BA.ObjectToString(_point.Get((Object)("lng"))));
 //BA.debugLineNum = 324;BA.debugLine="SelectedMakanlatlng = point.Get(\"lat\")&\",\"&poin";
_selectedmakanlatlng = BA.ObjectToString(_point.Get((Object)("lat")))+","+BA.ObjectToString(_point.Get((Object)("lng")));
 //BA.debugLineNum = 330;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 335;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("617367061",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 336;BA.debugLine="ToastMessageShow(\"خطا در خواندن موقعیت مکانی\",Tr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("خطا در خواندن موقعیت مکانی"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 337;BA.debugLine="MsgboxAsync(LastException.Message,\"خطای ثبت موقع";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence("خطای ثبت موقعیت"),processBA);
 //BA.debugLineNum = 338;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 340;BA.debugLine="End Sub";
return "";
}
public static String  _t_tick() throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Sub t_Tick";
 //BA.debugLineNum = 185;BA.debugLine="time=time+72";
_time = (int) (_time+72);
 //BA.debugLineNum = 186;BA.debugLine="If time>9999 Then";
if (_time>9999) { 
 //BA.debugLineNum = 187;BA.debugLine="address_bar_lbl.SetVisibleAnimated(1000,False)";
mostCurrent._address_bar_lbl.SetVisibleAnimated((int) (1000),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _t2_tick() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Sub T2_Tick";
 //BA.debugLineNum = 192;BA.debugLine="Time2=Time2+1";
_time2 = (int) (_time2+1);
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 345;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 346;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 347;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 348;BA.debugLine="End Sub";
return "";
}
}
