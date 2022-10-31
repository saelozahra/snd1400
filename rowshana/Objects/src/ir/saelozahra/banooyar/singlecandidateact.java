package ir.saelozahra.banooyar;

  import ir.hitexroid.material.x.others.Hi_Menu;
  import android.view.Menu;

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

public class singlecandidateact extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static singlecandidateact mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.singlecandidateact");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (singlecandidateact).");
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
		activityBA = new BA(this, layout, processBA, "ir.saelozahra.banooyar", "ir.saelozahra.banooyar.singlecandidateact");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.saelozahra.banooyar.singlecandidateact", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (singlecandidateact) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (singlecandidateact) Resume **");
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
		return singlecandidateact.class;
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
            BA.LogInfo("** Activity (singlecandidateact) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (singlecandidateact) Pause event (activity is not paused). **");
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
            singlecandidateact mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (singlecandidateact) Resume **");
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
public ir.hitex.coordinator.layout.Hitex_FlexibleSpace _cf = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xml = null;
public ir.aghajari.slider.Amir_SlisderConfig _config = null;
public ir.aghajari.slider.Amir_SliderShow _show = null;
public ir.hitexroid.material.x.Hitex_Toolbar _toolbar = null;
public ir.saelozahra.banooyar.httpjob _imagejob = null;
public static String _tel = "";
public ir.hitexroid.material.x.Label _textlbl = null;
public anywheresoftware.b4a.objects.PanelWrapper _textpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _imagessliderpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _propertypanel = null;
public ir.hitexroid.material.x.Label _categorylbl = null;
public ir.hitexroid.material.x.Label _taglbl = null;
public ir.hitexroid.material.x.Label _p1lbl = null;
public ir.hitexroid.material.x.Label _p2lbl = null;
public ir.hitexroid.material.x.Label _p3lbl = null;
public ir.hitexroid.material.x.Label _p4lbl = null;
public ir.hitexroid.material.x.Label _p5lbl = null;
public ir.hitexroid.material.x.Label _p6lbl = null;
public anywheresoftware.b4a.objects.collections.List _piclist = null;
public static int _propertynumber = 0;
public anywheresoftware.b4a.objects.WebViewWrapper _sliderwv = null;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.banooyar.main _main = null;
public ir.saelozahra.banooyar.homeact _homeact = null;
public ir.saelozahra.banooyar.contentlistact _contentlistact = null;
public ir.saelozahra.banooyar.electionact _electionact = null;
public ir.saelozahra.banooyar.firebasemessaging _firebasemessaging = null;
public ir.saelozahra.banooyar.notificationact _notificationact = null;
public ir.saelozahra.banooyar.mapact _mapact = null;
public ir.saelozahra.banooyar.translateact _translateact = null;
public ir.saelozahra.banooyar.saelozahra _saelozahra = null;
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
ir.hitexroid.material.x.Hitex_Toolbar _t = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 32;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"SingleCandidateLayout\")";
mostCurrent._activity.LoadLayout("SingleCandidateLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 35;BA.debugLine="CF.Panel.LoadLayout(\"CandidatePanelLayout\")";
mostCurrent._cf.getPanel().LoadLayout("CandidatePanelLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 37;BA.debugLine="CF.CardBackgroundColor=Colors.Transparent";
mostCurrent._cf.setCardBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 38;BA.debugLine="CF.ImageBitmap = LoadBitmap(File.DirAssets,\"box.j";
mostCurrent._cf.setImageBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"box.jpg").getObject()));
 //BA.debugLineNum = 39;BA.debugLine="CF.ImageScaleType = CF.SCALE_CENTER_CROP";
mostCurrent._cf.setImageScaleType(mostCurrent._cf.SCALE_CENTER_CROP);
 //BA.debugLineNum = 40;BA.debugLine="CF.Icon = XML.GetDrawable(\"twotone_perm_phone_msg";
mostCurrent._cf.setIcon(mostCurrent._xml.GetDrawable("twotone_perm_phone_msg_white_24"));
 //BA.debugLineNum = 41;BA.debugLine="CF.SetToolbarColor(SaeloZahra.ColorDark,SaeloZahr";
mostCurrent._cf.SetToolbarColor(mostCurrent._saelozahra._colordark /*int*/ ,mostCurrent._saelozahra._colorlighttransparent /*int*/ );
 //BA.debugLineNum = 42;BA.debugLine="CF.SetFabColor(SaeloZahra.ColorAccent,SaeloZahra.";
mostCurrent._cf.SetFabColor(mostCurrent._saelozahra._coloraccent /*int*/ ,mostCurrent._saelozahra._colorlighttransparent /*int*/ );
 //BA.debugLineNum = 43;BA.debugLine="CF.Title=\"     \"&Application.LabelName";
mostCurrent._cf.setTitle(BA.ObjectToCharSequence("     "+anywheresoftware.b4a.keywords.Common.Application.getLabelName()));
 //BA.debugLineNum = 46;BA.debugLine="Dim T As Hitex_Toolbar = CF.ToolbarDark";
_t = new ir.hitexroid.material.x.Hitex_Toolbar();
_t = (ir.hitexroid.material.x.Hitex_Toolbar) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new ir.hitexroid.material.x.Hitex_Toolbar(), (androidx.appcompat.widget.Toolbar)(mostCurrent._cf.getToolbarDark().getObject()));
 //BA.debugLineNum = 48;BA.debugLine="ToolBar.Initialize(\"Toolbar\")";
mostCurrent._toolbar.Initialize(mostCurrent.activityBA,"Toolbar");
 //BA.debugLineNum = 49;BA.debugLine="Dim XML As XmlLayoutBuilder";
mostCurrent._xml = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 50;BA.debugLine="Dim Jo = T.Parent As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_t.getParent()));
 //BA.debugLineNum = 51;BA.debugLine="T.RemoveView";
_t.RemoveView();
 //BA.debugLineNum = 52;BA.debugLine="Jo.RunMethod(\"addView\",Array(ToolBar))";
_jo.RunMethod("addView",new Object[]{(Object)(mostCurrent._toolbar.getObject())});
 //BA.debugLineNum = 53;BA.debugLine="ToolBar.Height = 56dip";
mostCurrent._toolbar.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (56)));
 //BA.debugLineNum = 54;BA.debugLine="ToolBar.SetSupportActionBar";
mostCurrent._toolbar.SetSupportActionBar();
 //BA.debugLineNum = 55;BA.debugLine="ToolBar.Title = \"     \"&Application.LabelName";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence("     "+anywheresoftware.b4a.keywords.Common.Application.getLabelName()));
 //BA.debugLineNum = 56;BA.debugLine="ToolBar.Color = Colors.Transparent";
mostCurrent._toolbar.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 57;BA.debugLine="ToolBar.NavigationIcon = XML.GetDrawable(\"round_a";
mostCurrent._toolbar.setNavigationIcon(mostCurrent._xml.GetDrawable("round_arrow_back_white_24"));
 //BA.debugLineNum = 58;BA.debugLine="ToolBar.Padding=Array As Int(0,SaeloZahra.StatusB";
mostCurrent._toolbar.setPadding(new int[]{(int) (0),mostCurrent._saelozahra._statusbarheight /*int*/ ,(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 59;BA.debugLine="ToolBar.Height=SaeloZahra.MaterialActionBarHeight";
mostCurrent._toolbar.setHeight((int) (mostCurrent._saelozahra._materialactionbarheight /*int*/ +mostCurrent._saelozahra._statusbarheight /*int*/ ));
 //BA.debugLineNum = 61;BA.debugLine="PicList.Initialize";
mostCurrent._piclist.Initialize();
 //BA.debugLineNum = 62;BA.debugLine="ImageJob.Initialize(\"ImageJob\",Me)";
mostCurrent._imagejob._initialize /*String*/ (processBA,"ImageJob",singlecandidateact.getObject());
 //BA.debugLineNum = 66;BA.debugLine="CategoryLbl.Typeface = SaeloZahra.fontBold : Cate";
mostCurrent._categorylbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 66;BA.debugLine="CategoryLbl.Typeface = SaeloZahra.fontBold : Cate";
mostCurrent._categorylbl.setColor(mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 67;BA.debugLine="TagLbl.SetLayout((CategoryLbl.Left+CategoryLbl.Wi";
mostCurrent._taglbl.SetLayout((int) ((mostCurrent._categorylbl.getLeft()+mostCurrent._categorylbl.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)))),mostCurrent._taglbl.getTop(),mostCurrent._taglbl.getWidth(),mostCurrent._taglbl.getHeight());
 //BA.debugLineNum = 67;BA.debugLine="TagLbl.SetLayout((CategoryLbl.Left+CategoryLbl.Wi";
mostCurrent._taglbl.setColor(mostCurrent._saelozahra._color /*int*/ );
 //BA.debugLineNum = 67;BA.debugLine="TagLbl.SetLayout((CategoryLbl.Left+CategoryLbl.Wi";
mostCurrent._taglbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 68;BA.debugLine="TextLbl.Typeface = SaeloZahra.font";
mostCurrent._textlbl.setTypeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 69;BA.debugLine="TextLbl.Gravity = Gravity.RIGHT";
mostCurrent._textlbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 72;BA.debugLine="If SaeloZahra.P.SdkVersion>23 Then";
if (mostCurrent._saelozahra._p /*anywheresoftware.b4a.phone.Phone*/ .getSdkVersion()>23) { 
 //BA.debugLineNum = 73;BA.debugLine="Config.Initialize";
mostCurrent._config.Initialize(processBA);
 //BA.debugLineNum = 74;BA.debugLine="Config.position(Config.POSITION_LEFT)";
mostCurrent._config.position(mostCurrent._config.POSITION_LEFT);
 //BA.debugLineNum = 75;BA.debugLine="Config.primaryColor(SaeloZahra.ColorDark)";
mostCurrent._config.primaryColor(mostCurrent._saelozahra._colordark /*int*/ );
 //BA.debugLineNum = 76;BA.debugLine="Config.edge(True)";
mostCurrent._config.edge(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 77;BA.debugLine="Config.sensitivity(100)";
mostCurrent._config.sensitivity((float) (100));
 //BA.debugLineNum = 78;BA.debugLine="Config.scrimColor(Colors.ARGB(180,0,0,0))";
mostCurrent._config.scrimColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (180),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 79;BA.debugLine="Show.convertActivityToTranslucent";
mostCurrent._show.convertActivityToTranslucent(mostCurrent.activityBA);
 //BA.debugLineNum = 80;BA.debugLine="Show.attachActivity(Config)";
mostCurrent._show.attachActivity(mostCurrent.activityBA,mostCurrent._config);
 };
 //BA.debugLineNum = 83;BA.debugLine="TextPanel.SetLayout(3%x,TextPanel.Top,83%x,TextPa";
mostCurrent._textpanel.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA),mostCurrent._textpanel.getTop(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (83),mostCurrent.activityBA),mostCurrent._textpanel.getHeight());
 //BA.debugLineNum = 84;BA.debugLine="TextLbl.SetLayout(1%x,TextLbl.Top,78%x,TextLbl.He";
mostCurrent._textlbl.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),mostCurrent._textlbl.getTop(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (78),mostCurrent.activityBA),mostCurrent._textlbl.getHeight());
 //BA.debugLineNum = 85;BA.debugLine="PropertyPanel.SetLayout(3%x,PropertyPanel.Top,83%";
mostCurrent._propertypanel.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA),mostCurrent._propertypanel.getTop(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (83),mostCurrent.activityBA),mostCurrent._propertypanel.getHeight());
 //BA.debugLineNum = 86;BA.debugLine="ImagesSliderPanel.SetLayout(3%x,ImagesSliderPanel";
mostCurrent._imagessliderpanel.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA),mostCurrent._imagessliderpanel.getTop(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (83),mostCurrent.activityBA),mostCurrent._imagessliderpanel.getHeight());
 //BA.debugLineNum = 87;BA.debugLine="AddImageSlider(ImagesSliderPanel)";
_addimageslider(mostCurrent._imagessliderpanel);
 //BA.debugLineNum = 89;BA.debugLine="SimulateAddItem";
_simulateadditem();
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createoptionsmenu(ir.hitexroid.material.x.others.Hi_Menu _menu) throws Exception{
 //BA.debugLineNum = 311;BA.debugLine="Sub Activity_CreateOptionsMenu (Menu As Hi_Menu)";
 //BA.debugLineNum = 313;BA.debugLine="Menu.Add2(1,1,\"وب سایت یا شبکه‌های اجتماعی\",XML.G";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("وب سایت یا شبکه‌های اجتماعی"),mostCurrent._xml.GetDrawable("twotone_link_white_24")).SetShowAsAction((int) (2));
 //BA.debugLineNum = 314;BA.debugLine="Menu.Add2(2,2,\"صحبت با کاندیدا\",XML.GetDrawable(\"";
_menu.Add2((int) (2),(int) (2),BA.ObjectToCharSequence("صحبت با کاندیدا"),mostCurrent._xml.GetDrawable("twotone_perm_phone_msg_white_24")).SetShowAsAction((int) (2));
 //BA.debugLineNum = 315;BA.debugLine="Menu.Add2(2,3,\"تبلیغات انتخاباتی\",XML.GetDrawable";
_menu.Add2((int) (2),(int) (3),BA.ObjectToCharSequence("تبلیغات انتخاباتی"),mostCurrent._xml.GetDrawable("baseline_text_snippet_white_24")).SetShowAsAction((int) (2));
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 291;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 292;BA.debugLine="If KeyCode==KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 293;BA.debugLine="CF_NavigationOnClick";
_cf_navigationonclick();
 //BA.debugLineNum = 294;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 296;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 298;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 98;BA.debugLine="ImageJob.Release";
mostCurrent._imagejob._release /*String*/ ();
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _addimageslider(anywheresoftware.b4a.objects.PanelWrapper _viewparent) throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub AddImageSlider(ViewParent As Panel)";
 //BA.debugLineNum = 107;BA.debugLine="ViewParent.Visible=False";
_viewparent.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 109;BA.debugLine="SliderWV.Initialize(\"SliderWV\")";
mostCurrent._sliderwv.Initialize(mostCurrent.activityBA,"SliderWV");
 //BA.debugLineNum = 110;BA.debugLine="SliderWV.Color=Colors.Transparent";
mostCurrent._sliderwv.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 111;BA.debugLine="SliderWV.ZoomEnabled=False";
mostCurrent._sliderwv.setZoomEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 113;BA.debugLine="ViewParent.AddView(SliderWV,0,0,ViewParent.Width,";
_viewparent.AddView((android.view.View)(mostCurrent._sliderwv.getObject()),(int) (0),(int) (0),_viewparent.getWidth(),_viewparent.getHeight());
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _buildpropertyitemstyle(String _varname,String _varvalue) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csp = null;
 //BA.debugLineNum = 301;BA.debugLine="Sub BuildPropertyItemStyle(VarName As String,VarVa";
 //BA.debugLineNum = 302;BA.debugLine="If VarValue.Length>2 Then PropertyNumber = Proper";
if (_varvalue.length()>2) { 
_propertynumber = (int) (_propertynumber+1);};
 //BA.debugLineNum = 303;BA.debugLine="Log(\"LVProductSlider: \"&PropertyNumber&\"   VarNam";
anywheresoftware.b4a.keywords.Common.LogImpl("48388610","LVProductSlider: "+BA.NumberToString(_propertynumber)+"   VarName: "+_varname+"   VarValue: "+_varvalue,0);
 //BA.debugLineNum = 304;BA.debugLine="Dim CSP As CSBuilder";
_csp = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 305;BA.debugLine="CSP.Initialize.Typeface(SaeloZahra.Font).color(Co";
_csp.Initialize().Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(_varname)).Append(BA.ObjectToCharSequence("	 	")).Typeface((android.graphics.Typeface)(mostCurrent._saelozahra._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.DarkGray).Append(BA.ObjectToCharSequence(_varvalue)).PopAll();
 //BA.debugLineNum = 306;BA.debugLine="Return CSP";
if (true) return _csp;
 //BA.debugLineNum = 307;BA.debugLine="End Sub";
return null;
}
public static String  _cf_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _intent1 = null;
 //BA.debugLineNum = 268;BA.debugLine="Sub CF_Click";
 //BA.debugLineNum = 269;BA.debugLine="Dim intent1 As Intent";
_intent1 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 270;BA.debugLine="intent1.Initialize(intent1.ACTION_VIEW, \"tel:\"&te";
_intent1.Initialize(_intent1.ACTION_VIEW,"tel:"+mostCurrent._tel);
 //BA.debugLineNum = 271;BA.debugLine="StartActivity(intent1)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_intent1.getObject()));
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static boolean  _cf_longclick() throws Exception{
 //BA.debugLineNum = 273;BA.debugLine="Sub CF_LongClick As Boolean";
 //BA.debugLineNum = 274;BA.debugLine="ToastMessageShow(\"تماس با فروشنده\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("تماس با فروشنده"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 275;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 276;BA.debugLine="End Sub";
return false;
}
public static String  _cf_navigationonclick() throws Exception{
 //BA.debugLineNum = 277;BA.debugLine="Sub CF_NavigationOnClick";
 //BA.debugLineNum = 278;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 279;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 280;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private CF As Hitex_FlexibleSpace";
mostCurrent._cf = new ir.hitex.coordinator.layout.Hitex_FlexibleSpace();
 //BA.debugLineNum = 13;BA.debugLine="Private XML As XmlLayoutBuilder";
mostCurrent._xml = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 14;BA.debugLine="Dim Config 	As Amir_SliderConfig";
mostCurrent._config = new ir.aghajari.slider.Amir_SlisderConfig();
 //BA.debugLineNum = 15;BA.debugLine="Dim Show 	As Amir_SliderShow";
mostCurrent._show = new ir.aghajari.slider.Amir_SliderShow();
 //BA.debugLineNum = 16;BA.debugLine="Dim ToolBar As Hitex_Toolbar";
mostCurrent._toolbar = new ir.hitexroid.material.x.Hitex_Toolbar();
 //BA.debugLineNum = 17;BA.debugLine="Dim ImageJob As HttpJob";
mostCurrent._imagejob = new ir.saelozahra.banooyar.httpjob();
 //BA.debugLineNum = 20;BA.debugLine="Dim tel As String";
mostCurrent._tel = "";
 //BA.debugLineNum = 21;BA.debugLine="Dim TextLbl As Label";
mostCurrent._textlbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 22;BA.debugLine="Private TextPanel,ImagesSliderPanel,PropertyPanel";
mostCurrent._textpanel = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._imagessliderpanel = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._propertypanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private CategoryLbl,TagLbl As Label";
mostCurrent._categorylbl = new ir.hitexroid.material.x.Label();
mostCurrent._taglbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 24;BA.debugLine="Private P1Lbl,P2Lbl,P3Lbl,P4Lbl,P5Lbl,P6Lbl As La";
mostCurrent._p1lbl = new ir.hitexroid.material.x.Label();
mostCurrent._p2lbl = new ir.hitexroid.material.x.Label();
mostCurrent._p3lbl = new ir.hitexroid.material.x.Label();
mostCurrent._p4lbl = new ir.hitexroid.material.x.Label();
mostCurrent._p5lbl = new ir.hitexroid.material.x.Label();
mostCurrent._p6lbl = new ir.hitexroid.material.x.Label();
 //BA.debugLineNum = 25;BA.debugLine="Dim PicList As List";
mostCurrent._piclist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 26;BA.debugLine="Dim PropertyNumber As Int";
_propertynumber = 0;
 //BA.debugLineNum = 27;BA.debugLine="Dim SliderWV As WebView";
mostCurrent._sliderwv = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ir.saelozahra.banooyar.httpjob _j) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
anywheresoftware.b4a.objects.collections.Map _colroot = null;
String _sliderhtml = "";
String _picurl = "";
String _cat_title = "";
String _viewcount = "";
anywheresoftware.b4a.objects.CSBuilder _csbtext = null;
String _city = "";
anywheresoftware.b4a.objects.collections.Map _properties = null;
anywheresoftware.b4a.objects.collections.Map _property1 = null;
anywheresoftware.b4a.objects.collections.Map _property2 = null;
anywheresoftware.b4a.objects.collections.Map _property3 = null;
anywheresoftware.b4a.objects.collections.Map _property4 = null;
anywheresoftware.b4a.objects.collections.Map _property5 = null;
anywheresoftware.b4a.objects.collections.Map _property6 = null;
 //BA.debugLineNum = 122;BA.debugLine="Sub jobDone(J As HttpJob)";
 //BA.debugLineNum = 123;BA.debugLine="Log(j.JobName&\" | \"&j.Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("47798785",_j._jobname /*String*/ +" | "+BA.ObjectToString(_j._success /*boolean*/ ),0);
 //BA.debugLineNum = 124;BA.debugLine="If J.Success Then";
if (_j._success /*boolean*/ ) { 
 //BA.debugLineNum = 125;BA.debugLine="Select j.JobName";
switch (BA.switchObjectToInt(_j._jobname /*String*/ ,"CandidateJob","ImageJob")) {
case 0: {
 //BA.debugLineNum = 128;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 129;BA.debugLine="parser.Initialize(j.GetString)";
_parser.Initialize(_j._getstring /*String*/ ());
 //BA.debugLineNum = 130;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 131;BA.debugLine="For Each colroot As Map In root";
_colroot = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _root;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_colroot = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(group8.Get(index8)));
 //BA.debugLineNum = 132;BA.debugLine="PicList = colroot.Get(\"pic\")";
mostCurrent._piclist = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_colroot.Get((Object)("pic"))));
 //BA.debugLineNum = 133;BA.debugLine="ImageJob.Download(PicList.Get(0))";
mostCurrent._imagejob._download /*String*/ (BA.ObjectToString(mostCurrent._piclist.Get((int) (0))));
 //BA.debugLineNum = 134;BA.debugLine="ImagesSliderPanel.SetVisibleAnimated(313,True";
mostCurrent._imagessliderpanel.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 136;BA.debugLine="Dim SliderHTML As String = File.ReadString(Fi";
_sliderhtml = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"slider_1.html");
 //BA.debugLineNum = 137;BA.debugLine="For Each PicUrl As String In PicList";
{
final anywheresoftware.b4a.BA.IterableList group13 = mostCurrent._piclist;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_picurl = BA.ObjectToString(group13.Get(index13));
 //BA.debugLineNum = 138;BA.debugLine="SliderHTML = SliderHTML & \" <div class='item";
_sliderhtml = _sliderhtml+" <div class='item'><img src='"+_picurl+"'></div>";
 }
};
 //BA.debugLineNum = 140;BA.debugLine="SliderHTML = SliderHTML & File.ReadString(Fil";
_sliderhtml = _sliderhtml+anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"slider_2.html")+"<style>#owl-demo .item img{width: 100%; height: 100%; top: 0; left: 0; max-width:none; max-height:none; object-fit:cover; } .owl-buttons {top: 27%;} </style>";
 //BA.debugLineNum = 141;BA.debugLine="SliderWV.LoadHtml(SliderHTML)";
mostCurrent._sliderwv.LoadHtml(_sliderhtml);
 //BA.debugLineNum = 142;BA.debugLine="ImagesSliderPanel.Visible=True";
mostCurrent._imagessliderpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 144;BA.debugLine="CF.Title=SaeloZahra.CSBTitle(\"     \"&colroot.";
mostCurrent._cf.setTitle(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"     "+BA.ObjectToString(_colroot.Get((Object)("title")))).getObject()));
 //BA.debugLineNum = 147;BA.debugLine="tel 		= colroot.Get(\"tel\")";
mostCurrent._tel = BA.ObjectToString(_colroot.Get((Object)("tel")));
 //BA.debugLineNum = 150;BA.debugLine="Dim cat_title As String = colroot.Get(\"cat_ti";
_cat_title = BA.ObjectToString(_colroot.Get((Object)("cat_title")));
 //BA.debugLineNum = 151;BA.debugLine="CategoryLbl.Text = cat_title";
mostCurrent._categorylbl.setText(BA.ObjectToCharSequence(_cat_title));
 //BA.debugLineNum = 152;BA.debugLine="Dim viewcount As String = colroot.Get(\"viewco";
_viewcount = BA.ObjectToString(_colroot.Get((Object)("viewcount")));
 //BA.debugLineNum = 153;BA.debugLine="Dim CSBText As CSBuilder";
_csbtext = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 154;BA.debugLine="CSBText.Initialize.Append(colroot.Get(\"text\")";
_csbtext.Initialize().Append(BA.ObjectToCharSequence(_colroot.Get((Object)("text")))).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (10)).Color(anywheresoftware.b4a.keywords.Common.Colors.DarkGray).Append(BA.ObjectToCharSequence("تعداد بازدید: ")).Bold().Append(BA.ObjectToCharSequence(_viewcount)).PopAll();
 //BA.debugLineNum = 155;BA.debugLine="TextLbl.Text= CSBText";
mostCurrent._textlbl.setText(BA.ObjectToCharSequence(_csbtext.getObject()));
 //BA.debugLineNum = 156;BA.debugLine="Dim city As String = colroot.Get(\"city\")";
_city = BA.ObjectToString(_colroot.Get((Object)("city")));
 //BA.debugLineNum = 157;BA.debugLine="TagLbl.Text = city";
mostCurrent._taglbl.setText(BA.ObjectToCharSequence(_city));
 //BA.debugLineNum = 165;BA.debugLine="PropertyNumber = 0";
_propertynumber = (int) (0);
 //BA.debugLineNum = 166;BA.debugLine="Dim Properties As Map = colroot.Get(\"properti";
_properties = new anywheresoftware.b4a.objects.collections.Map();
_properties = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_colroot.Get((Object)("properties"))));
 //BA.debugLineNum = 167;BA.debugLine="Dim Property1 As Map = Properties.Get(\"proper";
_property1 = new anywheresoftware.b4a.objects.collections.Map();
_property1 = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_properties.Get((Object)("property1"))));
 //BA.debugLineNum = 168;BA.debugLine="P1Lbl.Text=BuildPropertyItemStyle(Property1.G";
mostCurrent._p1lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle(BA.ObjectToString(_property1.Get((Object)("name"))),BA.ObjectToString(_property1.Get((Object)("value")))).getObject()));
 //BA.debugLineNum = 169;BA.debugLine="P1Lbl.Width=-1";
mostCurrent._p1lbl.setWidth((int) (-1));
 //BA.debugLineNum = 170;BA.debugLine="Dim Property2 As Map = Properties.Get(\"proper";
_property2 = new anywheresoftware.b4a.objects.collections.Map();
_property2 = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_properties.Get((Object)("property2"))));
 //BA.debugLineNum = 171;BA.debugLine="P2Lbl.Text=BuildPropertyItemStyle(Property2.G";
mostCurrent._p2lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle(BA.ObjectToString(_property2.Get((Object)("name"))),BA.ObjectToString(_property2.Get((Object)("value")))).getObject()));
 //BA.debugLineNum = 172;BA.debugLine="P2Lbl.Width=-1";
mostCurrent._p2lbl.setWidth((int) (-1));
 //BA.debugLineNum = 173;BA.debugLine="Dim Property3 As Map = Properties.Get(\"proper";
_property3 = new anywheresoftware.b4a.objects.collections.Map();
_property3 = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_properties.Get((Object)("property3"))));
 //BA.debugLineNum = 174;BA.debugLine="P3Lbl.Text=BuildPropertyItemStyle(Property3.G";
mostCurrent._p3lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle(BA.ObjectToString(_property3.Get((Object)("name"))),BA.ObjectToString(_property3.Get((Object)("value")))).getObject()));
 //BA.debugLineNum = 175;BA.debugLine="P3Lbl.Width=-1";
mostCurrent._p3lbl.setWidth((int) (-1));
 //BA.debugLineNum = 176;BA.debugLine="Dim property4 As Map = Properties.Get(\"proper";
_property4 = new anywheresoftware.b4a.objects.collections.Map();
_property4 = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_properties.Get((Object)("property4"))));
 //BA.debugLineNum = 177;BA.debugLine="P4Lbl.Text=BuildPropertyItemStyle(property4.G";
mostCurrent._p4lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle(BA.ObjectToString(_property4.Get((Object)("name"))),BA.ObjectToString(_property4.Get((Object)("value")))).getObject()));
 //BA.debugLineNum = 178;BA.debugLine="P4Lbl.Width=-1";
mostCurrent._p4lbl.setWidth((int) (-1));
 //BA.debugLineNum = 179;BA.debugLine="Dim property5 As Map = Properties.Get(\"proper";
_property5 = new anywheresoftware.b4a.objects.collections.Map();
_property5 = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_properties.Get((Object)("property5"))));
 //BA.debugLineNum = 180;BA.debugLine="P5Lbl.Text=BuildPropertyItemStyle(property5.G";
mostCurrent._p5lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle(BA.ObjectToString(_property5.Get((Object)("name"))),BA.ObjectToString(_property5.Get((Object)("value")))).getObject()));
 //BA.debugLineNum = 181;BA.debugLine="P5Lbl.Width=-1";
mostCurrent._p5lbl.setWidth((int) (-1));
 //BA.debugLineNum = 182;BA.debugLine="Dim property6 As Map = Properties.Get(\"proper";
_property6 = new anywheresoftware.b4a.objects.collections.Map();
_property6 = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(_properties.Get((Object)("property6"))));
 //BA.debugLineNum = 183;BA.debugLine="P6Lbl.Text=BuildPropertyItemStyle(property6.G";
mostCurrent._p6lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle(BA.ObjectToString(_property6.Get((Object)("name"))),BA.ObjectToString(_property6.Get((Object)("value")))).getObject()));
 //BA.debugLineNum = 184;BA.debugLine="P6Lbl.Width=-1";
mostCurrent._p6lbl.setWidth((int) (-1));
 //BA.debugLineNum = 185;BA.debugLine="Responsive";
_responsive();
 }
};
 //BA.debugLineNum = 190;BA.debugLine="Responsive";
_responsive();
 break; }
case 1: {
 //BA.debugLineNum = 193;BA.debugLine="Try";
try { //BA.debugLineNum = 194;BA.debugLine="CF.ImageBitmap = J.GetBitmap";
mostCurrent._cf.setImageBitmap((android.graphics.Bitmap)(_j._getbitmap /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ ().getObject()));
 } 
       catch (Exception e56) {
			processBA.setLastException(e56); //BA.debugLineNum = 196;BA.debugLine="CF.ImageBitmap = LoadBitmap(File.DirAssets,\"b";
mostCurrent._cf.setImageBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"box.jpg").getObject()));
 //BA.debugLineNum = 197;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("47798859",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 break; }
}
;
 }else {
 //BA.debugLineNum = 201;BA.debugLine="ToastMessageShow(SaeloZahra.CSB(\"خطا در بارگزاری";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._saelozahra._csb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"خطا در بارگزاری").getObject()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 202;BA.debugLine="ToastMessageShow(J.ErrorMessage,False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_j._errormessage /*String*/ ),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 204;BA.debugLine="J.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _responsive() throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 249;BA.debugLine="Sub Responsive";
 //BA.debugLineNum = 251;BA.debugLine="Dim SU As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 252;BA.debugLine="TextLbl.Height = SU.MeasureMultilineTextHeight(Te";
mostCurrent._textlbl.setHeight((int) (_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._textlbl.getObject()),BA.ObjectToCharSequence(mostCurrent._textlbl.getText()))+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA)));
 //BA.debugLineNum = 253;BA.debugLine="TextPanel.Height = TextLbl.Height";
mostCurrent._textpanel.setHeight(mostCurrent._textlbl.getHeight());
 //BA.debugLineNum = 254;BA.debugLine="TagLbl.Top = TextLbl.Height-8%y";
mostCurrent._taglbl.setTop((int) (mostCurrent._textlbl.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA)));
 //BA.debugLineNum = 255;BA.debugLine="CategoryLbl.Top = TextLbl.Height-8%y";
mostCurrent._categorylbl.setTop((int) (mostCurrent._textlbl.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA)));
 //BA.debugLineNum = 256;BA.debugLine="ImagesSliderPanel.Top=TextPanel.top+TextPanel.Hei";
mostCurrent._imagessliderpanel.setTop((int) (mostCurrent._textpanel.getTop()+mostCurrent._textpanel.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 257;BA.debugLine="PropertyPanel.Top=ImagesSliderPanel.top+ImagesSli";
mostCurrent._propertypanel.setTop((int) (mostCurrent._imagessliderpanel.getTop()+mostCurrent._imagessliderpanel.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 258;BA.debugLine="PropertyPanel.Height=(PropertyNumber+1)*56dip";
mostCurrent._propertypanel.setHeight((int) ((_propertynumber+1)*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (56))));
 //BA.debugLineNum = 259;BA.debugLine="CF.Panel.Height = PropertyPanel.top+PropertyPanel";
mostCurrent._cf.getPanel().setHeight((int) (mostCurrent._propertypanel.getTop()+mostCurrent._propertypanel.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 261;BA.debugLine="If PropertyNumber==0 Then";
if (_propertynumber==0) { 
 //BA.debugLineNum = 262;BA.debugLine="PropertyPanel.Visible=False";
mostCurrent._propertypanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 263;BA.debugLine="CF.Panel.Height = ImagesSliderPanel.top+ImagesSl";
mostCurrent._cf.getPanel().setHeight((int) (mostCurrent._imagessliderpanel.getTop()+mostCurrent._imagessliderpanel.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 };
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return "";
}
public static String  _simulateadditem() throws Exception{
String _sliderhtml = "";
String _cat_title = "";
String _viewcount = "";
anywheresoftware.b4a.objects.CSBuilder _csbtext = null;
String _city = "";
 //BA.debugLineNum = 208;BA.debugLine="Sub SimulateAddItem";
 //BA.debugLineNum = 210;BA.debugLine="ImageJob.Download(\"https://saelozahra.ir/wp-conte";
mostCurrent._imagejob._download /*String*/ ("https://saelozahra.ir/wp-content/uploads/2021/01/4_5866090371808756967-scaled.jpg");
 //BA.debugLineNum = 211;BA.debugLine="ImagesSliderPanel.SetVisibleAnimated(313,True)";
mostCurrent._imagessliderpanel.SetVisibleAnimated((int) (313),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 213;BA.debugLine="Dim SliderHTML As String = File.ReadString(File.D";
_sliderhtml = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"slider_1.html");
 //BA.debugLineNum = 214;BA.debugLine="SliderHTML = SliderHTML & \" <div class='item'><im";
_sliderhtml = _sliderhtml+" <div class='item'><img src='https://defapress.ir/files/fa/news/1395/9/11/51398_996.jpg'></div>";
 //BA.debugLineNum = 215;BA.debugLine="SliderHTML = SliderHTML & \" <div class='item'><im";
_sliderhtml = _sliderhtml+" <div class='item'><img src='https://saelozahra.ir/wp-content/uploads/2020/12/photo_%DB%B2%DB%B0%DB%B2%DB%B0-%DB%B1%DB%B1-%DB%B1%DB%B4_%DB%B0%DB%B3-%DB%B3%DB%B9-%DB%B5%DB%B5-1-e1607809180436.jpg'></div>";
 //BA.debugLineNum = 216;BA.debugLine="SliderHTML = SliderHTML & \" <div class='item'><im";
_sliderhtml = _sliderhtml+" <div class='item'><img src='https://saelozahra.ir/wp-content/uploads/2021/01/4_5866090371808756967-scaled.jpg'></div>";
 //BA.debugLineNum = 217;BA.debugLine="SliderHTML = SliderHTML & File.ReadString(File.Di";
_sliderhtml = _sliderhtml+anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"slider_2.html")+"<style>#owl-demo .item img{width: 100%; height: 100%; top: 0; left: 0; max-width:none; max-height:none; object-fit:cover; } .owl-buttons {top: 35%;} </style>";
 //BA.debugLineNum = 218;BA.debugLine="SliderWV.LoadHtml(SliderHTML)";
mostCurrent._sliderwv.LoadHtml(_sliderhtml);
 //BA.debugLineNum = 220;BA.debugLine="CF.Title=SaeloZahra.CSBTitle(\" محمدامیر لطفی\")";
mostCurrent._cf.setTitle(BA.ObjectToCharSequence(mostCurrent._saelozahra._csbtitle /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA," محمدامیر لطفی").getObject()));
 //BA.debugLineNum = 221;BA.debugLine="tel 		= \"09206208105\"";
mostCurrent._tel = "09206208105";
 //BA.debugLineNum = 222;BA.debugLine="Dim cat_title As String = \"کمیسیون فرهنگی\"";
_cat_title = "کمیسیون فرهنگی";
 //BA.debugLineNum = 223;BA.debugLine="CategoryLbl.Text = cat_title";
mostCurrent._categorylbl.setText(BA.ObjectToCharSequence(_cat_title));
 //BA.debugLineNum = 224;BA.debugLine="Dim viewcount As String = 110+Rnd(88,313)";
_viewcount = BA.NumberToString(110+anywheresoftware.b4a.keywords.Common.Rnd((int) (88),(int) (313)));
 //BA.debugLineNum = 225;BA.debugLine="Dim CSBText As CSBuilder";
_csbtext = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 226;BA.debugLine="CSBText.Initialize.Append(\"دکتر محمد امیر لطفی 30";
_csbtext.Initialize().Append(BA.ObjectToCharSequence("دکتر محمد امیر لطفی 30 سال پیش در چنین روزی در خانواده ای مذهبی و در شهر شیراز چشم به جهان گشود "+anywheresoftware.b4a.keywords.Common.CRLF+"دیری نپائید که دکتر لطفی علاقه مند به مباحث الکترونیک و کامپیوتر شد و به فراگیری این علوم در دارالمکتب باهنر نمود"+anywheresoftware.b4a.keywords.Common.CRLF+"هم اکنون او راننده اسنپ اسنپ است و در تپ‌سی امرار معاش می‌کند...")).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF)).Size((int) (10)).Color(anywheresoftware.b4a.keywords.Common.Colors.DarkGray).Append(BA.ObjectToCharSequence("افراد آنلاین در این صفحه: ")).Bold().Append(BA.ObjectToCharSequence(_viewcount)).Pop().Append(BA.ObjectToCharSequence(" نفر ")).PopAll();
 //BA.debugLineNum = 227;BA.debugLine="TextLbl.Text= CSBText";
mostCurrent._textlbl.setText(BA.ObjectToCharSequence(_csbtext.getObject()));
 //BA.debugLineNum = 228;BA.debugLine="Dim city As String = \"شیراز\"";
_city = "شیراز";
 //BA.debugLineNum = 229;BA.debugLine="TagLbl.Text = city";
mostCurrent._taglbl.setText(BA.ObjectToCharSequence(_city));
 //BA.debugLineNum = 231;BA.debugLine="PropertyNumber = 0";
_propertynumber = (int) (0);
 //BA.debugLineNum = 232;BA.debugLine="P1Lbl.Text=BuildPropertyItemStyle(\"نام\",\"محمد امی";
mostCurrent._p1lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle("نام","محمد امیر لطفی").getObject()));
 //BA.debugLineNum = 233;BA.debugLine="P1Lbl.Width=-1";
mostCurrent._p1lbl.setWidth((int) (-1));
 //BA.debugLineNum = 234;BA.debugLine="P2Lbl.Text=BuildPropertyItemStyle(\"تحصیلات\",\"لیسا";
mostCurrent._p2lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle("تحصیلات","لیسانس الهیات").getObject()));
 //BA.debugLineNum = 235;BA.debugLine="P2Lbl.Width=-1";
mostCurrent._p2lbl.setWidth((int) (-1));
 //BA.debugLineNum = 236;BA.debugLine="P3Lbl.Text=BuildPropertyItemStyle(\"حوزه تخصصی\",\"ک";
mostCurrent._p3lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle("حوزه تخصصی","کمیسیون فرهنگی").getObject()));
 //BA.debugLineNum = 237;BA.debugLine="P3Lbl.Width=-1";
mostCurrent._p3lbl.setWidth((int) (-1));
 //BA.debugLineNum = 238;BA.debugLine="P4Lbl.Text=BuildPropertyItemStyle(\"شهر\",\"شیراز\")";
mostCurrent._p4lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle("شهر","شیراز").getObject()));
 //BA.debugLineNum = 239;BA.debugLine="P4Lbl.Width=-1";
mostCurrent._p4lbl.setWidth((int) (-1));
 //BA.debugLineNum = 240;BA.debugLine="P5Lbl.Text=BuildPropertyItemStyle(\"موافق با شفافی";
mostCurrent._p5lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle("موافق با شفافیت اموال مسئولین","بله").getObject()));
 //BA.debugLineNum = 241;BA.debugLine="P5Lbl.Width=-1";
mostCurrent._p5lbl.setWidth((int) (-1));
 //BA.debugLineNum = 242;BA.debugLine="P6Lbl.Text=BuildPropertyItemStyle(\"سابقه مسئولیت\"";
mostCurrent._p6lbl.setText(BA.ObjectToCharSequence(_buildpropertyitemstyle("سابقه مسئولیت","مسئول گروه سرود مدرسه").getObject()));
 //BA.debugLineNum = 243;BA.debugLine="P6Lbl.Width=-1";
mostCurrent._p6lbl.setWidth((int) (-1));
 //BA.debugLineNum = 244;BA.debugLine="Responsive";
_responsive();
 //BA.debugLineNum = 246;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(ir.hitexroid.material.x.others.Hi_MenuItem _item) throws Exception{
 //BA.debugLineNum = 320;BA.debugLine="Sub ToolBar_MenuItemClick (Item As Hi_MenuItem)";
 //BA.debugLineNum = 321;BA.debugLine="Log(Item.ItemId)";
anywheresoftware.b4a.keywords.Common.LogImpl("48519681",BA.NumberToString(_item.getItemId()),0);
 //BA.debugLineNum = 322;BA.debugLine="Select Item.ItemId";
switch (BA.switchObjectToInt(_item.getItemId(),(int) (0),(int) (1))) {
case 0: {
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationclick() throws Exception{
 //BA.debugLineNum = 282;BA.debugLine="Sub ToolBar_NavigationClick";
 //BA.debugLineNum = 283;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 284;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 285;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationonclick() throws Exception{
 //BA.debugLineNum = 286;BA.debugLine="Sub ToolBar_NavigationOnClick";
 //BA.debugLineNum = 287;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 288;BA.debugLine="SaeloZahra.SetAnimation(\"zoom_enter\",\"zoom_exit\")";
mostCurrent._saelozahra._setanimation /*String*/ (mostCurrent.activityBA,"zoom_enter","zoom_exit");
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}


   public boolean _onCreateOptionsMenu(Menu menu) {
     if (processBA.subExists("activity_createoptionsmenu")) {
       processBA.raiseEvent2(this, true, "activity_createoptionsmenu", false, new Hi_Menu(menu));
       return true;
     } else {
       return false;
     }   
    }

}
