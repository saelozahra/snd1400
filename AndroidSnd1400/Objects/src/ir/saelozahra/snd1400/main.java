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

public class main extends com.onboarder.AhoyOnboarderActivity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public static int _appopencount = 0;
public de.amberhome.objects.appcompat.AppCompatBase _ac = null;
public ir.saelozahra.snd1400.doubletaptoclose _dttc = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.snd1400.starter _starter = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (homeact.mostCurrent != null);
vis = vis | (adminsact.mostCurrent != null);
vis = vis | (candidalistact.mostCurrent != null);
vis = vis | (electionact.mostCurrent != null);
vis = vis | (notificationact.mostCurrent != null);
vis = vis | (qrforscanact.mostCurrent != null);
vis = vis | (selectmapact.mostCurrent != null);
vis = vis | (singlecandidateact.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
com.porya.ahoyonboarding.AhoyOnboardingWrapper.AhoyOnboarderCardWrapper _c = null;
com.porya.ahoyonboarding.AhoyOnboardingWrapper.AhoyOnboarderCardWrapper _c1 = null;
com.porya.ahoyonboarding.AhoyOnboardingWrapper.AhoyOnboarderCardWrapper _c2 = null;
com.porya.ahoyonboarding.AhoyOnboardingWrapper _p = null;
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 36;BA.debugLine="DTTC.InItIaLiZe(\"مجددا دکمه خروج را بزنید\")";
mostCurrent._dttc._initialize /*String*/ (processBA,"مجددا دکمه خروج را بزنید");
 //BA.debugLineNum = 38;BA.debugLine="SaeloZahra.MaterialActionBarHeight 	=	AC.GetMater";
mostCurrent._saelozahra._materialactionbarheight /*int*/  = mostCurrent._ac.GetMaterialActionBarHeight(mostCurrent.activityBA);
 //BA.debugLineNum = 39;BA.debugLine="SaeloZahra.StatusBarHeight 			=	AC.GetStatusBarHe";
mostCurrent._saelozahra._statusbarheight /*int*/  = mostCurrent._ac.GetStatusBarHeight(mostCurrent.activityBA);
 //BA.debugLineNum = 41;BA.debugLine="SaeloZahra.Color		 			=	AC.GetThemeAttribute(\"col";
mostCurrent._saelozahra._color /*int*/  = mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorPrimary");
 //BA.debugLineNum = 42;BA.debugLine="SaeloZahra.ColorDark	 			=	AC.GetThemeAttribute(\"";
mostCurrent._saelozahra._colordark /*int*/  = mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorPrimaryDark");
 //BA.debugLineNum = 43;BA.debugLine="SaeloZahra.ColorAccent	 			=	AC.GetThemeAttribute";
mostCurrent._saelozahra._coloraccent /*int*/  = mostCurrent._ac.GetThemeAttribute(mostCurrent.activityBA,"colorAccent");
 //BA.debugLineNum = 45;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 46;BA.debugLine="l.Initialize";
_l.Initialize();
 //BA.debugLineNum = 48;BA.debugLine="Dim c,c1,c2 As AhoyOnboarderCard";
_c = new com.porya.ahoyonboarding.AhoyOnboardingWrapper.AhoyOnboarderCardWrapper();
_c1 = new com.porya.ahoyonboarding.AhoyOnboardingWrapper.AhoyOnboarderCardWrapper();
_c2 = new com.porya.ahoyonboarding.AhoyOnboardingWrapper.AhoyOnboarderCardWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Dim p As AhoyOnboarding";
_p = new com.porya.ahoyonboarding.AhoyOnboardingWrapper();
 //BA.debugLineNum = 50;BA.debugLine="p.Initialize(\"p\")";
_p.Initialize(mostCurrent.activityBA,"p");
 //BA.debugLineNum = 52;BA.debugLine="l.Add(c.AddCard(\"SND1400\" , \"سامانه جامع نوبت دهی";
_l.Add((Object)(_c.AddCard("SND1400","سامانه جامع نوبت دهی انتخابات 1400","wallet")));
 //BA.debugLineNum = 53;BA.debugLine="c.setBackgroundColor(Colors.ARGB(40,0,0,0))";
_c.setBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (40),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 54;BA.debugLine="c.setTitleColor(Colors.White)";
_c.setTitleColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 55;BA.debugLine="c.setDescriptionColor(Colors.rgb(238,238,238))";
_c.setDescriptionColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (238),(int) (238),(int) (238)));
 //BA.debugLineNum = 56;BA.debugLine="c.setTitleTextSize(p.dpToPixels(12))";
_c.setTitleTextSize(_p.dpToPixels((int) (12)));
 //BA.debugLineNum = 57;BA.debugLine="c.setDescriptionTextSize(p.dpToPixels(8))";
_c.setDescriptionTextSize(_p.dpToPixels((int) (8)));
 //BA.debugLineNum = 59;BA.debugLine="l.Add(c1.AddCard(\"عملکرد\",\"برای خودتان و خانواده";
_l.Add((Object)(_c1.AddCard("عملکرد","برای خودتان و خانواده تان"+anywheresoftware.b4a.keywords.Common.CRLF+"نزدیک ترین شعبه را در خلوت ترین ساعت رزرو کنید.","chalk")));
 //BA.debugLineNum = 60;BA.debugLine="c1.setBackgroundColor(Colors.ARGB(40,0,0,0))";
_c1.setBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (40),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 61;BA.debugLine="c1.setTitleColor(Colors.White)";
_c1.setTitleColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 62;BA.debugLine="c1.setDescriptionColor(Colors.rgb(238,238,238))";
_c1.setDescriptionColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (238),(int) (238),(int) (238)));
 //BA.debugLineNum = 63;BA.debugLine="c1.setTitleTextSize(p.dpToPixels(12))";
_c1.setTitleTextSize(_p.dpToPixels((int) (12)));
 //BA.debugLineNum = 64;BA.debugLine="c1.setDescriptionTextSize(p.dpToPixels(8))";
_c1.setDescriptionTextSize(_p.dpToPixels((int) (8)));
 //BA.debugLineNum = 66;BA.debugLine="l.Add(c2.AddCard(\"ناتوانی\",\"اگه به هر دلیلی مثل ب";
_l.Add((Object)(_c2.AddCard("ناتوانی","اگه به هر دلیلی مثل بیماری، نمیتونین به شعب اخذ رای برین، به ما اطلاع بدین تا صندوق صیار رو بیاریم درب منزل شما"+anywheresoftware.b4a.keywords.Common.CRLF+"در عرض چند دقیقه "+anywheresoftware.b4a.keywords.Common.CRLF+"یه فروشنده بشید و محصولاتتون رو اینجا بفروشید","blindpng")));
 //BA.debugLineNum = 67;BA.debugLine="c2.setBackgroundColor(Colors.ARGB(40,0,0,0))";
_c2.setBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (40),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 68;BA.debugLine="c2.setTitleColor(Colors.White)";
_c2.setTitleColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 69;BA.debugLine="c2.setDescriptionColor(Colors.rgb(238,238,238))";
_c2.setDescriptionColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (238),(int) (238),(int) (238)));
 //BA.debugLineNum = 70;BA.debugLine="c2.setTitleTextSize(p.dpToPixels(12))";
_c2.setTitleTextSize(_p.dpToPixels((int) (12)));
 //BA.debugLineNum = 71;BA.debugLine="c2.setDescriptionTextSize(p.dpToPixels(8))";
_c2.setDescriptionTextSize(_p.dpToPixels((int) (8)));
 //BA.debugLineNum = 73;BA.debugLine="p.FinishButtonTitle= \"شروع به کار\"";
_p.setFinishButtonTitle("شروع به کار");
 //BA.debugLineNum = 74;BA.debugLine="p.showNavigationControls(True)";
_p.showNavigationControls(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 75;BA.debugLine="p.setGradientBackground";
_p.setGradientBackground();
 //BA.debugLineNum = 77;BA.debugLine="p.Font = Typeface.LoadFromAssets(\"samim-fd-wol.tt";
_p.setFont(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("samim-fd-wol.ttf"));
 //BA.debugLineNum = 79;BA.debugLine="p.StartOnboardPages = l";
_p.setStartOnboardPages((java.util.ArrayList)(_l.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="OpenAppCounter";
_openappcounter();
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 118;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 119;BA.debugLine="DTTC.TapToClose";
mostCurrent._dttc._taptoclose /*String*/ ();
 //BA.debugLineNum = 120;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 122;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 29;BA.debugLine="Dim AC As AppCompat";
mostCurrent._ac = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 30;BA.debugLine="Dim DTTC As DoubleTaptoClose";
mostCurrent._dttc = new ir.saelozahra.snd1400.doubletaptoclose();
 //BA.debugLineNum = 31;BA.debugLine="Dim Show 	As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _openappcounter() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub OpenAppCounter";
 //BA.debugLineNum = 95;BA.debugLine="If File.Exists(SaeloZahra.Dir,\"AppOpenCount\") The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._saelozahra._dir /*String*/ ,"AppOpenCount")) { 
 //BA.debugLineNum = 96;BA.debugLine="AppOpenCount = File.ReadString(SaeloZahra.Dir,\"A";
_appopencount = (int)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"AppOpenCount")));
 };
 //BA.debugLineNum = 99;BA.debugLine="File.WriteString(SaeloZahra.Dir,\"AppOpenCount\",Ap";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._saelozahra._dir /*String*/ ,"AppOpenCount",BA.NumberToString(_appopencount+1));
 //BA.debugLineNum = 101;BA.debugLine="Log(\"AppOpenCount: \"&AppOpenCount)";
anywheresoftware.b4a.keywords.Common.LogImpl("613434888","AppOpenCount: "+BA.NumberToString(_appopencount),0);
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _p_onfinishbuttonpressed() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub p_onFinishButtonPressed";
 //BA.debugLineNum = 87;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 88;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 89;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 90;BA.debugLine="StartActivity(HomeAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._homeact.getObject()));
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
main._process_globals();
starter._process_globals();
homeact._process_globals();
saelozahra._process_globals();
adminsact._process_globals();
candidalistact._process_globals();
electionact._process_globals();
firebasemessaging._process_globals();
notificationact._process_globals();
qrforscanact._process_globals();
selectmapact._process_globals();
singlecandidateact._process_globals();
b4xcollections._process_globals();
httputils2service._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim AppOpenCount As Int = 0";
_appopencount = (int) (0);
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
}
