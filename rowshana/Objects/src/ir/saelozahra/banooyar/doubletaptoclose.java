package ir.saelozahra.banooyar;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class doubletaptoclose extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "ir.saelozahra.banooyar.doubletaptoclose");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", ir.saelozahra.banooyar.doubletaptoclose.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.Timer _ti = null;
public anywheresoftware.b4a.objects.Timer _timr = null;
public float _i = 0f;
public String _tt = "";
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
public ir.saelozahra.banooyar.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.banooyar.selectmapact _selectmapact = null;
public ir.saelozahra.banooyar.starter _starter = null;
public ir.saelozahra.banooyar.b4xcollections _b4xcollections = null;
public ir.saelozahra.banooyar.httputils2service _httputils2service = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Private Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Private Ti As Timer";
_ti = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 5;BA.debugLine="Private Timr As Timer";
_timr = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 6;BA.debugLine="Private I As Float";
_i = 0f;
 //BA.debugLineNum = 7;BA.debugLine="Private TT As String";
_tt = "";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.CSBuilder  _csb(String _text) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csb1 = null;
 //BA.debugLineNum = 37;BA.debugLine="Public Sub CSB(Text As String) As CSBuilder";
 //BA.debugLineNum = 38;BA.debugLine="Dim Csb1 As CSBuilder";
_csb1 = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 39;BA.debugLine="Csb1.Initialize";
_csb1.Initialize();
 //BA.debugLineNum = 40;BA.debugLine="Csb1.Typeface(SaeloZahra.font).Alignment(\"ALIGN_C";
_csb1.Typeface((android.graphics.Typeface)(_saelozahra._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Alignment(BA.getEnumFromString(android.text.Layout.Alignment.class,"ALIGN_CENTER")).Append(BA.ObjectToCharSequence(_text)).PopAll();
 //BA.debugLineNum = 41;BA.debugLine="Return Csb1";
if (true) return _csb1;
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,String _toasttext) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 12;BA.debugLine="Public Sub InItIaLiZe (ToastText As String)";
 //BA.debugLineNum = 13;BA.debugLine="I = 0";
_i = (float) (0);
 //BA.debugLineNum = 14;BA.debugLine="Ti.Initialize (\"Ti\",1000)";
_ti.Initialize(ba,"Ti",(long) (1000));
 //BA.debugLineNum = 15;BA.debugLine="Ti.Enabled = True";
_ti.setEnabled(__c.True);
 //BA.debugLineNum = 16;BA.debugLine="Timr.Initialize (\"Timr\",100)";
_timr.Initialize(ba,"Timr",(long) (100));
 //BA.debugLineNum = 17;BA.debugLine="Timr.Enabled = True";
_timr.setEnabled(__c.True);
 //BA.debugLineNum = 18;BA.debugLine="TT = ToastText";
_tt = _toasttext;
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public String  _taptoclose() throws Exception{
 //BA.debugLineNum = 45;BA.debugLine="Public Sub TapToClose";
 //BA.debugLineNum = 47;BA.debugLine="ToastMessageShow (CSB(TT),False)";
__c.ToastMessageShow(BA.ObjectToCharSequence(_csb(_tt).getObject()),__c.False);
 //BA.debugLineNum = 49;BA.debugLine="I = I+1.3";
_i = (float) (_i+1.3);
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public String  _ti_tick() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Private Sub Ti_Tick";
 //BA.debugLineNum = 23;BA.debugLine="If I > 0 Then";
if (_i>0) { 
 //BA.debugLineNum = 24;BA.debugLine="I = I-0.49";
_i = (float) (_i-0.49);
 };
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public String  _timr_tick() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Private Sub Timr_Tick";
 //BA.debugLineNum = 31;BA.debugLine="If I >= 2 Then";
if (_i>=2) { 
 //BA.debugLineNum = 32;BA.debugLine="ExitApplication";
__c.ExitApplication();
 };
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
