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

public class qrforscanact extends Activity implements B4AActivity{
	public static qrforscanact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.qrforscanact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (qrforscanact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.snd1400", "ir.saelozahra.snd1400.qrforscanact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.snd1400.qrforscanact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (qrforscanact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (qrforscanact) Resume **");
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
		return qrforscanact.class;
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
            BA.LogInfo("** Activity (qrforscanact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (qrforscanact) Pause event (activity is not paused). **");
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
            qrforscanact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (qrforscanact) Resume **");
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
public anywheresoftware.b4a.object.XmlLayoutBuilder _x1 = null;
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public de.amberhome.objects.appcompat.ACToolbarLightWrapper _toolbar = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public static int _stopint = 0;
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
de.amberhome.objects.appcompat.ACMenuItemWrapper _mi = null;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
 //BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"SVLayout\")";
mostCurrent._activity.LoadLayout("SVLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 24;BA.debugLine="SaeloZahra.SetToolbarStyle(ToolBar,\"نمایه شناسایی";
mostCurrent._saelozahra._settoolbarstyle /*String*/ (mostCurrent.activityBA,mostCurrent._toolbar,"نمایه شناسایی شما",anywheresoftware.b4a.keywords.Common.True,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._scrollview1.getObject())));
 //BA.debugLineNum = 26;BA.debugLine="Dim mi As ACMenuItem";
_mi = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 27;BA.debugLine="ToolBar.Menu.Add2(1,1,\"اصلاح اطلاعات\",X1.GetDrawa";
mostCurrent._toolbar.getMenu().Add2((int) (1),(int) (1),BA.ObjectToCharSequence("اصلاح اطلاعات"),mostCurrent._x1.GetDrawable("baseline_text_snippet_white_24")).setShowAsAction(_mi.SHOW_AS_ACTION_IF_ROOM);
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
 //BA.debugLineNum = 44;BA.debugLine="For i = 0 To 2";
{
final int step15 = 1;
final int limit15 = (int) (2);
_i = (int) (0) ;
for (;_i <= limit15 ;_i = _i + step15 ) {
 //BA.debugLineNum = 45;BA.debugLine="Dim P1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="P1 = AddProfileBox(i,File.ReadString(SaeloZahra.";
_p1 = _addprofilebox(BA.NumberToString(_i),anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"melli"),anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._saelozahra._dir /*String*/ ,"time"));
 //BA.debugLineNum = 48;BA.debugLine="ScrollView1.Panel.AddView( P1, 20dip, Stopint, A";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(_p1.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),_stopint,(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),_p1.getHeight());
 //BA.debugLineNum = 49;BA.debugLine="Stopint = Stopint+P1.Height + 24dip";
_stopint = (int) (_stopint+_p1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)));
 //BA.debugLineNum = 50;BA.debugLine="ScrollView1.Panel.Height = Stopint +22dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (_stopint+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22))));
 }
};
 //BA.debugLineNum = 53;BA.debugLine="ScrollView1.Panel.Height=Stopint+22dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (_stopint+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22))));
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _addprofilebox(String _index,String _text,String _time) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _cdpanel = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
ir.hitexroid.material.x.Label _timelbl = null;
ir.saelozahra.snd1400.qrgenerator _qr = null;
ir.hitexroid.material.x.ImageView _imgv = null;
ir.hitexroid.material.x.Label _lbl = null;
 //BA.debugLineNum = 65;BA.debugLine="Sub AddProfileBox(Index As String, Text As String,";
 //BA.debugLineNum = 70;BA.debugLine="Dim cdPanel As GradientDrawable";
_cdpanel = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 71;BA.debugLine="cdPanel.Initialize(\"TR_BL\", Array As Int(Colors.W";
_cdpanel.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TR_BL"),new int[]{anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.White});
 //BA.debugLineNum = 72;BA.debugLine="cdPanel.CornerRadius = 12dip";
_cdpanel.setCornerRadius((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))));
 //BA.debugLineNum = 74;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 76;BA.debugLine="pnl.Tag = Index";
_pnl.setTag((Object)(_index));
 //BA.debugLineNum = 77;BA.debugLine="pnl.Background=cdPanel";
_pnl.setBackground((android.graphics.drawable.Drawable)(_cdpanel.getObject()));
 //BA.debugLineNum = 78;BA.debugLine="pnl.Elevation=12dip";
_pnl.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))));
 //BA.debugLineNum = 80;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 81;BA.debugLine="cd.Initialize(SaeloZahra.ColorDark,7dip)";
_cd.Initialize(mostCurrent._saelozahra._colordark /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
 //BA.debugLineNum = 83;BA.debugLine="Dim timeLbl As Label";
_timelbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 84;BA.debugLine="timeLbl.Initialize(\"\")";
_timelbl.Initialize(processBA,"");
 //BA.debugLineNum = 85;BA.debugLine="timeLbl.Background	= cd";
_timelbl.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="timeLbl.Text	 	= SaeloZahra.CSB(Time)";
_timelbl.setText(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,_time).getObject()));
 //BA.debugLineNum = 87;BA.debugLine="timeLbl.TextSize	= 14";
_timelbl.setTextSize((float) (14));
 //BA.debugLineNum = 88;BA.debugLine="timeLbl.SingleLine=True";
_timelbl.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 89;BA.debugLine="timeLbl.Ellipsize = \"END\"";
_timelbl.setEllipsize("END");
 //BA.debugLineNum = 90;BA.debugLine="timeLbl.Gravity		= Bit.Or(Gravity.CENTER_VERTICAL";
_timelbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL));
 //BA.debugLineNum = 91;BA.debugLine="timeLbl.textColor	= Colors.White";
_timelbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 92;BA.debugLine="pnl.AddView(timeLbl,10dip,10dip,133dip,22dip)";
_pnl.AddView((android.view.View)(_timelbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (133)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
 //BA.debugLineNum = 96;BA.debugLine="Dim QR As QRGenerator";
_qr = new ir.saelozahra.snd1400.qrgenerator();
 //BA.debugLineNum = 97;BA.debugLine="QR.Initialize(ScrollView1.Width)";
_qr._initialize /*String*/ (processBA,mostCurrent._scrollview1.getWidth());
 //BA.debugLineNum = 100;BA.debugLine="Dim ImgV As ImageView";
_imgv = new ir.hitexroid.material.x.ImageView();
 //BA.debugLineNum = 101;BA.debugLine="ImgV.Initialize(\"\")";
_imgv.Initialize(processBA,"");
 //BA.debugLineNum = 102;BA.debugLine="ImgV.Gravity = Gravity.CENTER";
_imgv.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 103;BA.debugLine="ImgV.SetBackgroundImage(QR.Create(Text).Resize(Ac";
_imgv.SetBackgroundImageNew((android.graphics.Bitmap)(_qr._create /*anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper*/ (_text).Resize((int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))),(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))),anywheresoftware.b4a.keywords.Common.True).getObject())).setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 104;BA.debugLine="pnl.AddView(ImgV, 35dip,35dip,Activity.Width-60di";
_pnl.AddView((android.view.View)(_imgv.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)),(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))),(int) (mostCurrent._activity.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 //BA.debugLineNum = 107;BA.debugLine="Dim lbl As Label";
_lbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 108;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(processBA,"lbl");
 //BA.debugLineNum = 109;BA.debugLine="lbl.Tag  = Text";
_lbl.setTag((Object)(_text));
 //BA.debugLineNum = 110;BA.debugLine="lbl.Text = Text";
_lbl.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 111;BA.debugLine="lbl.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 113;BA.debugLine="lbl.TextColor = SaeloZahra.ColorDark";
_lbl.setTextColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 114;BA.debugLine="lbl.Typeface = SaeloZahra.fontBold";
_lbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 115;BA.debugLine="lbl.Gravity=Bit.Or(Gravity.CENTER_HORIZONTAL,Grav";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 116;BA.debugLine="pnl.AddView(lbl, 0, Activity.Width, -1  , 60dip)";
_pnl.AddView((android.view.View)(_lbl.getObject()),(int) (0),mostCurrent._activity.getWidth(),(int) (-1),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 119;BA.debugLine="pnl.Height = Activity.Width+60dip";
_pnl.setHeight((int) (mostCurrent._activity.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 //BA.debugLineNum = 121;BA.debugLine="Return pnl";
if (true) return _pnl;
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim X1 As XmlLayoutBuilder";
mostCurrent._x1 = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 13;BA.debugLine="Dim Config 	As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 14;BA.debugLine="Dim Show 	As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 15;BA.debugLine="Dim ToolBar As ACToolBarLight";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarLightWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Dim ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim Stopint As Int=18dip";
_stopint = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18));
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 127;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (1))) {
case 0: {
 //BA.debugLineNum = 129;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_exit\",\"zoom_enter";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_exit","zoom_enter");
 //BA.debugLineNum = 130;BA.debugLine="Show.convertActivityFromTranslucent";
mostCurrent._show.convertActivityFromTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 131;BA.debugLine="StartActivity(ElectionAct)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._electionact.getObject()));
 break; }
}
;
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
}
