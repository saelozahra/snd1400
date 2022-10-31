package ir.saelozahra.banooyar;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class saelozahra {
private static saelozahra mostCurrent = new saelozahra();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static String _dir = "";
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public static anywheresoftware.b4a.phone.Phone _p = null;
public static boolean _debug = false;
public static anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
public static anywheresoftware.b4a.phone.Phone.PhoneIntents _pi = null;
public static anywheresoftware.b4a.objects.IntentWrapper _intent = null;
public static anywheresoftware.b4j.object.JavaObject _jo = null;
public static int _materialactionbarheight = 0;
public static int _statusbarheight = 0;
public static String _activepage = "";
public static int _color = 0;
public static int _colordark = 0;
public static int _colorlight = 0;
public static int _colorlighttransparent = 0;
public static int _coloraccent = 0;
public b4a.example.dateutils _dateutils = null;
public ir.saelozahra.banooyar.main _main = null;
public ir.saelozahra.banooyar.homeact _homeact = null;
public ir.saelozahra.banooyar.contentlistact _contentlistact = null;
public ir.saelozahra.banooyar.electionact _electionact = null;
public ir.saelozahra.banooyar.firebasemessaging _firebasemessaging = null;
public ir.saelozahra.banooyar.notificationact _notificationact = null;
public ir.saelozahra.banooyar.mapact _mapact = null;
public ir.saelozahra.banooyar.translateact _translateact = null;
public ir.saelozahra.banooyar.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.banooyar.selectmapact _selectmapact = null;
public ir.saelozahra.banooyar.starter _starter = null;
public ir.saelozahra.banooyar.b4xcollections _b4xcollections = null;
public ir.saelozahra.banooyar.httputils2service _httputils2service = null;
public static anywheresoftware.b4a.objects.drawable.ColorDrawable  _cd(anywheresoftware.b4a.BA _ba,int _color1) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd2 = null;
 //BA.debugLineNum = 215;BA.debugLine="Sub CD( Color1 As Int ) As ColorDrawable";
 //BA.debugLineNum = 216;BA.debugLine="Dim cd2 As ColorDrawable";
_cd2 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 217;BA.debugLine="cd2.Initialize2(Color1,12dip,1dip,ColorLight)";
_cd2.Initialize2(_color1,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),_colorlight);
 //BA.debugLineNum = 218;BA.debugLine="Return cd2";
if (true) return _cd2;
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return null;
}
public static String  _changegooglemapstyle(anywheresoftware.b4a.BA _ba,String _stylecode,anywheresoftware.b4a.objects.MapFragmentWrapper.GoogleMapWrapper _map) throws Exception{
anywheresoftware.b4j.object.JavaObject _style = null;
 //BA.debugLineNum = 673;BA.debugLine="Sub ChangeGooglemapStyle(StyleCode As String,Map A";
 //BA.debugLineNum = 674;BA.debugLine="Dim JO As JavaObject = Map";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_map.getObject()));
 //BA.debugLineNum = 675;BA.debugLine="Dim Style As JavaObject";
_style = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 676;BA.debugLine="Style.InitializeNewInstance(\"com.google.android.g";
_style.InitializeNewInstance("com.google.android.gms.maps.model.MapStyleOptions",new Object[]{(Object)(_stylecode)});
 //BA.debugLineNum = 677;BA.debugLine="Return JO.RunMethod(\"setMapStyle\", Array(Style))";
if (true) return BA.ObjectToString(_jo.RunMethod("setMapStyle",new Object[]{(Object)(_style.getObject())}));
 //BA.debugLineNum = 678;BA.debugLine="End Sub";
return "";
}
public static boolean  _checkconnection(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 620;BA.debugLine="Sub CheckConnection As Boolean";
 //BA.debugLineNum = 622;BA.debugLine="If (p.GetDataState == \"CONNECTED\") Or (p.GetSetti";
if (((_p.GetDataState()).equals("CONNECTED")) || ((_p.GetSettings("wifi_on")).equals(BA.NumberToString(1)))) { 
 //BA.debugLineNum = 623;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(((_p.GetDataState()).equals("DISCONNECTED")) || ((_p.GetDataState()).equals("SUSPENDED"))) { 
 //BA.debugLineNum = 625;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 627;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 630;BA.debugLine="End Sub";
return false;
}
public static boolean  _checkmellicode(anywheresoftware.b4a.BA _ba,String _mellicode) throws Exception{
boolean _b = false;
String[] _scodeposition = null;
String[] _icodeposition = null;
int _resultstep1 = 0;
int _resultstep2 = 0;
int _resultstep3 = 0;
int _ic = 0;
 //BA.debugLineNum = 56;BA.debugLine="Sub CheckMelliCode(MelliCode As String)As Boolean";
 //BA.debugLineNum = 57;BA.debugLine="Dim b As Boolean";
_b = false;
 //BA.debugLineNum = 58;BA.debugLine="Dim sCodePosition(10) As String";
_scodeposition = new String[(int) (10)];
java.util.Arrays.fill(_scodeposition,"");
 //BA.debugLineNum = 59;BA.debugLine="Dim iCodePosition(10) As String";
_icodeposition = new String[(int) (10)];
java.util.Arrays.fill(_icodeposition,"");
 //BA.debugLineNum = 61;BA.debugLine="Dim resultStep1,resultStep2,resultStep3 As Int";
_resultstep1 = 0;
_resultstep2 = 0;
_resultstep3 = 0;
 //BA.debugLineNum = 63;BA.debugLine="If MelliCode.Length = 10 Then";
if (_mellicode.length()==10) { 
 //BA.debugLineNum = 65;BA.debugLine="For ic = 0 To 9";
{
final int step6 = 1;
final int limit6 = (int) (9);
_ic = (int) (0) ;
for (;_ic <= limit6 ;_ic = _ic + step6 ) {
 //BA.debugLineNum = 66;BA.debugLine="sCodePosition(ic) = MelliCode.CharAt(ic)";
_scodeposition[_ic] = BA.ObjectToString(_mellicode.charAt(_ic));
 //BA.debugLineNum = 67;BA.debugLine="iCodePosition(ic) = sCodePosition(ic)";
_icodeposition[_ic] = _scodeposition[_ic];
 }
};
 //BA.debugLineNum = 70;BA.debugLine="resultStep1 = (iCodePosition(0)*10)+(iCodePositi";
_resultstep1 = (int) (((double)(Double.parseDouble(_icodeposition[(int) (0)]))*10)+((double)(Double.parseDouble(_icodeposition[(int) (1)]))*9)+((double)(Double.parseDouble(_icodeposition[(int) (2)]))*8)+((double)(Double.parseDouble(_icodeposition[(int) (3)]))*7)+((double)(Double.parseDouble(_icodeposition[(int) (4)]))*6)+((double)(Double.parseDouble(_icodeposition[(int) (5)]))*5)+((double)(Double.parseDouble(_icodeposition[(int) (6)]))*4)+((double)(Double.parseDouble(_icodeposition[(int) (7)]))*3)+((double)(Double.parseDouble(_icodeposition[(int) (8)]))*2));
 //BA.debugLineNum = 71;BA.debugLine="resultStep2 = (resultStep1 Mod 11)";
_resultstep2 = (int) ((_resultstep1%11));
 //BA.debugLineNum = 72;BA.debugLine="resultStep3 = 11 - resultStep2";
_resultstep3 = (int) (11-_resultstep2);
 //BA.debugLineNum = 73;BA.debugLine="If resultStep2 < 2 Then";
if (_resultstep2<2) { 
 //BA.debugLineNum = 74;BA.debugLine="If resultStep2 = iCodePosition(9) Then";
if (_resultstep2==(double)(Double.parseDouble(_icodeposition[(int) (9)]))) { 
 //BA.debugLineNum = 75;BA.debugLine="b = True";
_b = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 76;BA.debugLine="Return b";
if (true) return _b;
 }else {
 //BA.debugLineNum = 78;BA.debugLine="b = False";
_b = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 79;BA.debugLine="Return b";
if (true) return _b;
 };
 }else {
 //BA.debugLineNum = 82;BA.debugLine="If resultStep3 = iCodePosition(9) Then";
if (_resultstep3==(double)(Double.parseDouble(_icodeposition[(int) (9)]))) { 
 //BA.debugLineNum = 83;BA.debugLine="b = True";
_b = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 84;BA.debugLine="Return b";
if (true) return _b;
 }else {
 //BA.debugLineNum = 86;BA.debugLine="b = False";
_b = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 87;BA.debugLine="Return b";
if (true) return _b;
 };
 };
 }else {
 //BA.debugLineNum = 91;BA.debugLine="b = False";
_b = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 92;BA.debugLine="Return b";
if (true) return _b;
 };
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return false;
}
public static boolean  _checksite(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
 //BA.debugLineNum = 642;BA.debugLine="Sub CheckSite As Boolean";
 //BA.debugLineNum = 643;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 644;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 645;BA.debugLine="p.Shell(\"ping -c 2 -W 10 -v google.com\", Null, sb";
_p.Shell("ping -c 2 -W 10 -v google.com",(String[])(anywheresoftware.b4a.keywords.Common.Null),(java.lang.StringBuilder)(_sb.getObject()),(java.lang.StringBuilder)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 646;BA.debugLine="If sb.Length = 0 Then";
if (_sb.getLength()==0) { 
 //BA.debugLineNum = 647;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 649;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 651;BA.debugLine="End Sub";
return false;
}
public static String  _convertenglish(anywheresoftware.b4a.BA _ba,String _text) throws Exception{
 //BA.debugLineNum = 250;BA.debugLine="Sub ConvertEnglish( Text As String ) As String";
 //BA.debugLineNum = 251;BA.debugLine="Return Text.Replace( \"۰\" , \"0\" ).Replace( \"۱\" , \"";
if (true) return _text.replace("۰","0").replace("۱","1").replace("۲","2").replace("۳","3").replace("۴","4").replace("۵","5").replace("۶","6").replace("۷","7").replace("۸","8").replace("۹","9");
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _convertpersian(anywheresoftware.b4a.BA _ba,String _text) throws Exception{
 //BA.debugLineNum = 246;BA.debugLine="Sub ConvertPersian( Text As String ) As String";
 //BA.debugLineNum = 247;BA.debugLine="Return Text.Replace( \"0\" , \"۰\" ).Replace( \"1\" , \"";
if (true) return _text.replace("0","۰").replace("1","۱").replace("2","۲").replace("3","۳").replace("4","۴").replace("5","۵").replace("6","۶").replace("7","۷").replace("8","۸").replace("9","۹");
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _csb(anywheresoftware.b4a.BA _ba,String _text) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 655;BA.debugLine="Sub CSB(text As String) As CSBuilder";
 //BA.debugLineNum = 656;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 657;BA.debugLine="CS.Initialize.Typeface(Font).Append(text).PopAll";
_cs.Initialize().Typeface((android.graphics.Typeface)(_font.getObject())).Append(BA.ObjectToCharSequence(_text)).PopAll();
 //BA.debugLineNum = 658;BA.debugLine="Return CS";
if (true) return _cs;
 //BA.debugLineNum = 659;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _csbtitle(anywheresoftware.b4a.BA _ba,String _text) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 667;BA.debugLine="Sub CSBTitle(text As String) As CSBuilder";
 //BA.debugLineNum = 668;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 669;BA.debugLine="CS.Initialize.Typeface(fontBold).Append(text).Pop";
_cs.Initialize().Typeface((android.graphics.Typeface)(_fontbold.getObject())).Append(BA.ObjectToCharSequence(_text)).PopAll();
 //BA.debugLineNum = 670;BA.debugLine="Return CS";
if (true) return _cs;
 //BA.debugLineNum = 671;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _csbwhite(anywheresoftware.b4a.BA _ba,String _text) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 661;BA.debugLine="Sub CSBWhite(text As String) As CSBuilder";
 //BA.debugLineNum = 662;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 663;BA.debugLine="CS.Initialize.Typeface(Font).Color(Colors.White).";
_cs.Initialize().Typeface((android.graphics.Typeface)(_font.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Size((int) (12)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 //BA.debugLineNum = 664;BA.debugLine="Return CS";
if (true) return _cs;
 //BA.debugLineNum = 665;BA.debugLine="End Sub";
return null;
}
public static String  _esmemah(anywheresoftware.b4a.BA _ba,int _input) throws Exception{
 //BA.debugLineNum = 255;BA.debugLine="Sub esmeMah(input As Int)As String";
 //BA.debugLineNum = 257;BA.debugLine="Select input";
switch (_input) {
case 1: {
 //BA.debugLineNum = 260;BA.debugLine="Return \"فروردین\"";
if (true) return "فروردین";
 break; }
case 2: {
 //BA.debugLineNum = 262;BA.debugLine="Return \"اردیبهشت\"";
if (true) return "اردیبهشت";
 break; }
case 3: {
 //BA.debugLineNum = 264;BA.debugLine="Return \"خرداد\"";
if (true) return "خرداد";
 break; }
case 4: {
 //BA.debugLineNum = 266;BA.debugLine="Return \"تیر\"";
if (true) return "تیر";
 break; }
case 5: {
 //BA.debugLineNum = 268;BA.debugLine="Return \"مرداد\"";
if (true) return "مرداد";
 break; }
case 6: {
 //BA.debugLineNum = 270;BA.debugLine="Return \"شهریور\"";
if (true) return "شهریور";
 break; }
case 7: {
 //BA.debugLineNum = 272;BA.debugLine="Return \"مهر\"";
if (true) return "مهر";
 break; }
case 8: {
 //BA.debugLineNum = 274;BA.debugLine="Return \"آبان\"";
if (true) return "آبان";
 break; }
case 9: {
 //BA.debugLineNum = 276;BA.debugLine="Return \"آذر\"";
if (true) return "آذر";
 break; }
case 10: {
 //BA.debugLineNum = 278;BA.debugLine="Return \"دی\"";
if (true) return "دی";
 break; }
case 11: {
 //BA.debugLineNum = 280;BA.debugLine="Return \"بهمن\"";
if (true) return "بهمن";
 break; }
case 12: {
 //BA.debugLineNum = 282;BA.debugLine="Return \"اسفند\"";
if (true) return "اسفند";
 break; }
default: {
 //BA.debugLineNum = 284;BA.debugLine="Return input";
if (true) return BA.NumberToString(_input);
 break; }
}
;
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
return "";
}
public static String  _esmeroozehafte(anywheresoftware.b4a.BA _ba,long _timestamp) throws Exception{
 //BA.debugLineNum = 401;BA.debugLine="Sub esmeRoozeHafte(timestamp As Long) As String";
 //BA.debugLineNum = 406;BA.debugLine="If timestamp == 0 Then timestamp = DateTime.Now";
if (_timestamp==0) { 
_timestamp = anywheresoftware.b4a.keywords.Common.DateTime.getNow();};
 //BA.debugLineNum = 408;BA.debugLine="Select DateTime.GetDayOfWeek(timestamp)";
switch (BA.switchObjectToInt(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(_timestamp),(int) (7),(int) (1),(int) (2),(int) (3),(int) (4),(int) (5),(int) (6))) {
case 0: {
 //BA.debugLineNum = 410;BA.debugLine="Return \" شنبه \"";
if (true) return " شنبه ";
 break; }
case 1: {
 //BA.debugLineNum = 412;BA.debugLine="Return \" یکشنبه \"";
if (true) return " یکشنبه ";
 break; }
case 2: {
 //BA.debugLineNum = 414;BA.debugLine="Return \" دوشنبه \"";
if (true) return " دوشنبه ";
 break; }
case 3: {
 //BA.debugLineNum = 416;BA.debugLine="Return \" سه شنبه \"";
if (true) return " سه شنبه ";
 break; }
case 4: {
 //BA.debugLineNum = 418;BA.debugLine="Return \" چهارشنبه \"";
if (true) return " چهارشنبه ";
 break; }
case 5: {
 //BA.debugLineNum = 420;BA.debugLine="Return \" پنجشنبه \"";
if (true) return " پنجشنبه ";
 break; }
case 6: {
 //BA.debugLineNum = 422;BA.debugLine="Return \" جمعه \"";
if (true) return " جمعه ";
 break; }
default: {
 //BA.debugLineNum = 424;BA.debugLine="Return \" امروز \"";
if (true) return " امروز ";
 break; }
}
;
 //BA.debugLineNum = 427;BA.debugLine="End Sub";
return "";
}
public static String  _fitcenterbitmap(anywheresoftware.b4a.BA _ba,ir.hitexroid.material.x.ImageView _imv,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rectdest = null;
int _delta = 0;
 //BA.debugLineNum = 532;BA.debugLine="Sub FitCenterBitmap( Imv As ImageView , bmp As Bit";
 //BA.debugLineNum = 535;BA.debugLine="Private cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 536;BA.debugLine="cvs.Initialize(Imv)";
_cvs.Initialize((android.view.View)(_imv.getObject()));
 //BA.debugLineNum = 538;BA.debugLine="Dim rectDest As Rect";
_rectdest = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 539;BA.debugLine="Dim delta As Int";
_delta = 0;
 //BA.debugLineNum = 540;BA.debugLine="If bmp.Width / bmp.Height > Imv.Width / Imv.Heigh";
if (_bmp.getWidth()/(double)_bmp.getHeight()>_imv.getWidth()/(double)_imv.getHeight()) { 
 //BA.debugLineNum = 541;BA.debugLine="delta = (Imv.Height - bmp.Height / bmp.Width * I";
_delta = (int) ((_imv.getHeight()-_bmp.getHeight()/(double)_bmp.getWidth()*_imv.getWidth())/(double)2);
 //BA.debugLineNum = 542;BA.debugLine="rectDest.Initialize(0, delta,Imv.Width, Imv.Heig";
_rectdest.Initialize((int) (0),_delta,_imv.getWidth(),(int) (_imv.getHeight()-_delta));
 }else {
 //BA.debugLineNum = 544;BA.debugLine="delta = (Imv.Width - bmp.Width / bmp.Height * Im";
_delta = (int) ((_imv.getWidth()-_bmp.getWidth()/(double)_bmp.getHeight()*_imv.getHeight())/(double)2);
 //BA.debugLineNum = 545;BA.debugLine="rectDest.Initialize(delta, 0, Imv.Width - delta,";
_rectdest.Initialize(_delta,(int) (0),(int) (_imv.getWidth()-_delta),_imv.getHeight());
 };
 //BA.debugLineNum = 547;BA.debugLine="cvs.DrawBitmap(bmp, Null, rectDest)";
_cvs.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_rectdest.getObject()));
 //BA.debugLineNum = 548;BA.debugLine="Imv.Invalidate";
_imv.Invalidate();
 //BA.debugLineNum = 550;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.ConcreteViewWrapper  _getviewbytag(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.PanelWrapper _parent,String _tag) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 996;BA.debugLine="Sub GetViewByTag(Parent As Panel,Tag As String) As";
 //BA.debugLineNum = 997;BA.debugLine="For Each v As View In Parent.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group1 = _parent.GetAllViewsRecursive();
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 998;BA.debugLine="If v.Tag == Tag Then Return v";
if ((_v.getTag()).equals((Object)(_tag))) { 
if (true) return _v;};
 }
};
 //BA.debugLineNum = 1001;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.GradientDrawable  _gradiant(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _gradient1 = null;
int[] _clrs = null;
 //BA.debugLineNum = 25;BA.debugLine="Sub Gradiant As GradientDrawable";
 //BA.debugLineNum = 26;BA.debugLine="Dim Gradient1 As GradientDrawable";
_gradient1 = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 27;BA.debugLine="Dim Clrs(2) As Int";
_clrs = new int[(int) (2)];
;
 //BA.debugLineNum = 28;BA.debugLine="Clrs(0) = 0x9EE5EF7B";
_clrs[(int) (0)] = (int) (0x9ee5ef7b);
 //BA.debugLineNum = 29;BA.debugLine="Clrs(1) = 0x9EA1B300";
_clrs[(int) (1)] = (int) (0x9ea1b300);
 //BA.debugLineNum = 30;BA.debugLine="Gradient1.Initialize(\"TR_BL\", Clrs)";
_gradient1.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TR_BL"),_clrs);
 //BA.debugLineNum = 31;BA.debugLine="Return Gradient1";
if (true) return _gradient1;
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return null;
}
public static String  _inventory(anywheresoftware.b4a.BA _ba,String _str) throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Sub inventory(str As String)As String";
 //BA.debugLineNum = 157;BA.debugLine="Select str";
switch (BA.switchObjectToInt(_str,"available","comingsoon","unavailable")) {
case 0: {
 //BA.debugLineNum = 159;BA.debugLine="Return \"موجود\"";
if (true) return "موجود";
 break; }
case 1: {
 //BA.debugLineNum = 161;BA.debugLine="Return \"بزودی می‌آید\"";
if (true) return "بزودی می‌آید";
 break; }
case 2: {
 //BA.debugLineNum = 163;BA.debugLine="Return \"ناموجود\"";
if (true) return "ناموجود";
 break; }
}
;
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static String  _mohasebe_tarikh(anywheresoftware.b4a.BA _ba,long _time_time) throws Exception{
String _mohasebe_daghighe = "";
String _mohasebe_saat = "";
String _mohasebe_rooz = "";
String _mohasebe_months = "";
 //BA.debugLineNum = 347;BA.debugLine="Sub mohasebe_tarikh(time_time As Long) As String";
 //BA.debugLineNum = 349;BA.debugLine="Dim mohasebe_daghighe As String";
_mohasebe_daghighe = "";
 //BA.debugLineNum = 350;BA.debugLine="mohasebe_daghighe = DateUtils.PeriodBetween(time_";
_mohasebe_daghighe = BA.NumberToString(mostCurrent._dateutils._periodbetween(_ba,_time_time,anywheresoftware.b4a.keywords.Common.DateTime.getNow()).Minutes);
 //BA.debugLineNum = 352;BA.debugLine="Dim mohasebe_saat As String";
_mohasebe_saat = "";
 //BA.debugLineNum = 353;BA.debugLine="mohasebe_saat = DateUtils.PeriodBetween(time_time";
_mohasebe_saat = BA.NumberToString(mostCurrent._dateutils._periodbetween(_ba,_time_time,anywheresoftware.b4a.keywords.Common.DateTime.getNow()).Hours);
 //BA.debugLineNum = 358;BA.debugLine="Dim mohasebe_rooz As String";
_mohasebe_rooz = "";
 //BA.debugLineNum = 359;BA.debugLine="mohasebe_rooz = DateUtils.PeriodBetween(time_time";
_mohasebe_rooz = BA.NumberToString(mostCurrent._dateutils._periodbetween(_ba,_time_time,anywheresoftware.b4a.keywords.Common.DateTime.getNow()).Days);
 //BA.debugLineNum = 361;BA.debugLine="Dim mohasebe_Months As String";
_mohasebe_months = "";
 //BA.debugLineNum = 362;BA.debugLine="mohasebe_Months = DateUtils.PeriodBetween(time_ti";
_mohasebe_months = BA.NumberToString(mostCurrent._dateutils._periodbetween(_ba,_time_time,anywheresoftware.b4a.keywords.Common.DateTime.getNow()).Months);
 //BA.debugLineNum = 365;BA.debugLine="If mohasebe_Months > 1 Then";
if ((double)(Double.parseDouble(_mohasebe_months))>1) { 
 //BA.debugLineNum = 366;BA.debugLine="Return mohasebe_Months &\" ماه و \"&mohasebe_rooz&";
if (true) return _mohasebe_months+" ماه و "+_mohasebe_rooz+" روز پیش ";
 }else if((double)(Double.parseDouble(_mohasebe_rooz))>1) { 
 //BA.debugLineNum = 368;BA.debugLine="Return mohasebe_rooz&\" روز پیش \"";
if (true) return _mohasebe_rooz+" روز پیش ";
 }else if((double)(Double.parseDouble(_mohasebe_saat))>1) { 
 //BA.debugLineNum = 370;BA.debugLine="Return mohasebe_saat & \" ساعت پیش \"";
if (true) return _mohasebe_saat+" ساعت پیش ";
 }else {
 //BA.debugLineNum = 372;BA.debugLine="Return mohasebe_daghighe & \" دقیقه پیش \"";
if (true) return _mohasebe_daghighe+" دقیقه پیش ";
 };
 //BA.debugLineNum = 375;BA.debugLine="End Sub";
return "";
}
public static int  _parseint(anywheresoftware.b4a.BA _ba,String _in1) throws Exception{
int _adadesh = 0;
 //BA.debugLineNum = 167;BA.debugLine="Sub ParseInt(in1 As String) As Int";
 //BA.debugLineNum = 168;BA.debugLine="If in1 == Null Or in1 == \"null\" Or in1 == \"\" Then";
if (_in1== null || (_in1).equals("null") || (_in1).equals("")) { 
_in1 = BA.NumberToString(0);};
 //BA.debugLineNum = 169;BA.debugLine="If in1.Contains(\".\") Then";
if (_in1.contains(".")) { 
 //BA.debugLineNum = 170;BA.debugLine="in1 = in1.SubString2(0,in1.IndexOf(\".\"))";
_in1 = _in1.substring((int) (0),_in1.indexOf("."));
 };
 //BA.debugLineNum = 172;BA.debugLine="Dim Adadesh As Int";
_adadesh = 0;
 //BA.debugLineNum = 173;BA.debugLine="Adadesh = Bit.ParseInt(in1,10)";
_adadesh = anywheresoftware.b4a.keywords.Common.Bit.ParseInt(_in1,(int) (10));
 //BA.debugLineNum = 174;BA.debugLine="Return Adadesh";
if (true) return _adadesh;
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Dim Dir As String = File.DirInternalCache";
_dir = anywheresoftware.b4a.keywords.Common.File.getDirInternalCache();
 //BA.debugLineNum = 6;BA.debugLine="Dim Font As Typeface=Typeface.LoadFromAssets(\"Sam";
_font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("Samim-FD-WOL.ttf")));
 //BA.debugLineNum = 7;BA.debugLine="Dim fontBold As Typeface=Typeface.LoadFromAssets(";
_fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("Samim-Bold-FD-WOL.ttf")));
 //BA.debugLineNum = 9;BA.debugLine="Dim P As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 10;BA.debugLine="Dim Debug As Boolean = False";
_debug = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 11;BA.debugLine="Dim R As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 12;BA.debugLine="Dim PI As PhoneIntents";
_pi = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 13;BA.debugLine="Dim Intent As Intent";
_intent = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Dim JO As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 15;BA.debugLine="Dim MaterialActionBarHeight,StatusBarHeight As In";
_materialactionbarheight = 0;
_statusbarheight = 0;
 //BA.debugLineNum = 16;BA.debugLine="Dim ActivePage As String = \"\"";
_activepage = "";
 //BA.debugLineNum = 17;BA.debugLine="Dim Color 		As Int";
_color = 0;
 //BA.debugLineNum = 18;BA.debugLine="Dim ColorDark 	As Int";
_colordark = 0;
 //BA.debugLineNum = 19;BA.debugLine="Dim ColorLight 	As Int = 0xFFEFD7AD";
_colorlight = (int) (0xffefd7ad);
 //BA.debugLineNum = 20;BA.debugLine="Dim ColorLightTransparent As Int = 0xDDEFD7AD";
_colorlighttransparent = (int) (0xddefd7ad);
 //BA.debugLineNum = 21;BA.debugLine="Dim ColorAccent As Int";
_coloraccent = 0;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _replace0with98(anywheresoftware.b4a.BA _ba,String _tel) throws Exception{
 //BA.debugLineNum = 206;BA.debugLine="Sub Replace0With98(tel As String) As String";
 //BA.debugLineNum = 207;BA.debugLine="If tel.StartsWith(\"98\") Then";
if (_tel.startsWith("98")) { 
 //BA.debugLineNum = 208;BA.debugLine="Log(\"09\"&tel.SubString2(1,tel.Length))";
anywheresoftware.b4a.keywords.Common.LogImpl("45963778","09"+_tel.substring((int) (1),_tel.length()),0);
 //BA.debugLineNum = 209;BA.debugLine="Return \"09\"&tel.SubString2(1,tel.Length)";
if (true) return "09"+_tel.substring((int) (1),_tel.length());
 }else {
 //BA.debugLineNum = 211;BA.debugLine="Return tel";
if (true) return _tel;
 };
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static String  _senoghte(anywheresoftware.b4a.BA _ba,int _adad) throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub SeNoghte(adad As Int)As String";
 //BA.debugLineNum = 153;BA.debugLine="Return NumberFormat2( adad,3,0, 0,True)";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat2(_adad,(int) (3),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public static String  _setanimation(anywheresoftware.b4a.BA _ba,String _inanimation,String _outanimation) throws Exception{
String _package = "";
int _in = 0;
int _out = 0;
 //BA.debugLineNum = 606;BA.debugLine="Sub SetAnimation(InAnimation As String, OutAnimati";
 //BA.debugLineNum = 608;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 609;BA.debugLine="Dim package As String";
_package = "";
 //BA.debugLineNum = 610;BA.debugLine="Dim In, out As Int";
_in = 0;
_out = 0;
 //BA.debugLineNum = 611;BA.debugLine="package = r.GetStaticField(\"anywheresoftware.b4a.";
_package = BA.ObjectToString(_r.GetStaticField("anywheresoftware.b4a.BA","packageName"));
 //BA.debugLineNum = 612;BA.debugLine="In = r.GetStaticField(package & \".R$anim\", InAnim";
_in = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$anim",_inanimation)));
 //BA.debugLineNum = 613;BA.debugLine="out = r.GetStaticField(package & \".R$anim\", OutAn";
_out = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$anim",_outanimation)));
 //BA.debugLineNum = 614;BA.debugLine="r.Target = r.GetActivity";
_r.Target = (Object)(_r.GetActivity((_ba.processBA == null ? _ba : _ba.processBA)));
 //BA.debugLineNum = 615;BA.debugLine="r.RunMethod4(\"overridePendingTransition\", Array A";
_r.RunMethod4("overridePendingTransition",new Object[]{(Object)(_in),(Object)(_out)},new String[]{"java.lang.int","java.lang.int"});
 //BA.debugLineNum = 617;BA.debugLine="End Sub";
return "";
}
public static String  _setcornerradii(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v,float _rx_topleft,float _ry_topleft,float _rx_topright,float _ry_topright,float _rx_bottomright,float _ry_bottomright,float _rx_bottomleft,float _ry_bottomleft) throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub SetCornerRadii(v As View, Rx_TopLeft As Float,";
 //BA.debugLineNum = 146;BA.debugLine="Dim JO As JavaObject = v.Background";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_v.getBackground()));
 //BA.debugLineNum = 147;BA.debugLine="If v.Background Is ColorDrawable Or v.Background";
if (_v.getBackground() instanceof android.graphics.drawable.Drawable || _v.getBackground() instanceof android.graphics.drawable.GradientDrawable) { 
 //BA.debugLineNum = 148;BA.debugLine="JO.RunMethod(\"setCornerRadii\", Array As Object(A";
_jo.RunMethod("setCornerRadii",new Object[]{(Object)(new float[]{_rx_topleft,_ry_topleft,_rx_topright,_ry_topright,_rx_bottomright,_ry_bottomright,_rx_bottomleft,_ry_bottomleft})});
 };
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _setelevation(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _b,float _f) throws Exception{
anywheresoftware.b4a.phone.Phone _sdk = null;
 //BA.debugLineNum = 986;BA.debugLine="Sub SetElevation (b As View ,f As Float)";
 //BA.debugLineNum = 987;BA.debugLine="Dim sdk As Phone";
_sdk = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 988;BA.debugLine="Dim JO As JavaObject = b";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_b.getObject()));
 //BA.debugLineNum = 989;BA.debugLine="If sdk.SdkVersion > 20 Then";
if (_sdk.getSdkVersion()>20) { 
 //BA.debugLineNum = 990;BA.debugLine="JO.RunMethod(\"setElevation\",Array As Object(f))";
_jo.RunMethod("setElevation",new Object[]{(Object)(_f)});
 //BA.debugLineNum = 991;BA.debugLine="JO.RunMethod(\"setStateListAnimator\", Array(Null)";
_jo.RunMethod("setStateListAnimator",new Object[]{anywheresoftware.b4a.keywords.Common.Null});
 };
 //BA.debugLineNum = 993;BA.debugLine="End Sub";
return "";
}
public static String  _seterroredittext(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _et,String _text) throws Exception{
anywheresoftware.b4j.object.JavaObject _j = null;
 //BA.debugLineNum = 980;BA.debugLine="Sub SetErrorEditText(ET As EditText,text As String";
 //BA.debugLineNum = 981;BA.debugLine="Dim j As JavaObject";
_j = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 982;BA.debugLine="j=ET";
_j = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_et.getObject()));
 //BA.debugLineNum = 983;BA.debugLine="j.RunMethod(\"setError\",Array(text))";
_j.RunMethod("setError",new Object[]{(Object)(_text)});
 //BA.debugLineNum = 984;BA.debugLine="End Sub";
return "";
}
public static String  _setnavigationbarcolor(anywheresoftware.b4a.BA _ba,int _clr) throws Exception{
anywheresoftware.b4j.object.JavaObject _j = null;
 //BA.debugLineNum = 522;BA.debugLine="Sub SetNavigationBarColor(Clr As Int)";
 //BA.debugLineNum = 524;BA.debugLine="Dim j As JavaObject";
_j = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 525;BA.debugLine="J.InitializeStatic(\"android.os.Build$VERSION\")";
_j.InitializeStatic("android.os.Build$VERSION");
 //BA.debugLineNum = 526;BA.debugLine="If j.GetField(\"SDK_INT\") > 20 Then";
if ((double)(BA.ObjectToNumber(_j.GetField("SDK_INT")))>20) { 
 //BA.debugLineNum = 527;BA.debugLine="J.InitializeContext.RunMethodJO(\"getWindow\",Null";
_j.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA)).RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("setNavigationBarColor",new Object[]{(Object)(_clr)});
 };
 //BA.debugLineNum = 530;BA.debugLine="End Sub";
return "";
}
public static String  _setstatusbarcolor(anywheresoftware.b4a.BA _ba,int _clr) throws Exception{
anywheresoftware.b4j.object.JavaObject _window = null;
 //BA.debugLineNum = 507;BA.debugLine="Sub SetStatusBarColor(clr As Int)";
 //BA.debugLineNum = 509;BA.debugLine="If p.SdkVersion >= 21 Then";
if (_p.getSdkVersion()>=21) { 
 //BA.debugLineNum = 510;BA.debugLine="Dim JO As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 511;BA.debugLine="JO.InitializeContext";
_jo.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 512;BA.debugLine="Dim window As JavaObject = JO.RunMethodJO(\"getWi";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = _jo.RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 513;BA.debugLine="window.RunMethod(\"addFlags\", Array (0x80000000))";
_window.RunMethod("addFlags",new Object[]{(Object)(0x80000000)});
 //BA.debugLineNum = 514;BA.debugLine="window.RunMethod(\"clearFlags\", Array (0x04000000";
_window.RunMethod("clearFlags",new Object[]{(Object)(0x04000000)});
 //BA.debugLineNum = 515;BA.debugLine="window.RunMethod(\"setStatusBarColor\", Array(clr)";
_window.RunMethod("setStatusBarColor",new Object[]{(Object)(_clr)});
 };
 //BA.debugLineNum = 518;BA.debugLine="SetNavigationBarColor(clr)";
_setnavigationbarcolor(_ba,_clr);
 //BA.debugLineNum = 520;BA.debugLine="End Sub";
return "";
}
public static String  _settextshadow(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _pview,float _pradius,float _pdx,float _pdy,int _pcolor) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _ref = null;
 //BA.debugLineNum = 568;BA.debugLine="Sub SetTextShadow(pView As View, pRadius As Float,";
 //BA.debugLineNum = 569;BA.debugLine="Dim ref As Reflector";
_ref = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 570;BA.debugLine="ref.Target = pView";
_ref.Target = (Object)(_pview.getObject());
 //BA.debugLineNum = 571;BA.debugLine="ref.RunMethod4(\"setShadowLayer\", Array As Object(";
_ref.RunMethod4("setShadowLayer",new Object[]{(Object)(_pradius),(Object)(_pdx),(Object)(_pdy),(Object)(_pcolor)},new String[]{"java.lang.float","java.lang.float","java.lang.float","java.lang.int"});
 //BA.debugLineNum = 572;BA.debugLine="End Sub";
return "";
}
public static String  _settoolbarstyle(anywheresoftware.b4a.BA _ba,de.amberhome.objects.appcompat.ACToolbarLightWrapper _toolbar,String _toolbartitle,boolean _hasbackbtn,anywheresoftware.b4a.objects.ConcreteViewWrapper _nextbox) throws Exception{
anywheresoftware.b4a.object.XmlLayoutBuilder _xml1 = null;
 //BA.debugLineNum = 34;BA.debugLine="Sub SetToolbarStyle(toolbar As ACToolBarLight,Tool";
 //BA.debugLineNum = 36;BA.debugLine="If MaterialActionBarHeight == 0 Then";
if (_materialactionbarheight==0) { 
 //BA.debugLineNum = 37;BA.debugLine="ToastMessageShow(CSB(\"خطا | مجددا وارد شوید\"),Tr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_csb(_ba,"خطا | مجددا وارد شوید").getObject()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 38;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 41;BA.debugLine="toolbar.SetBackgroundImage(LoadBitmapResize(File.";
_toolbar.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"toolbar.jpg",anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (313),_ba),(int) (_materialactionbarheight+(_statusbarheight*1.5)),anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 42;BA.debugLine="toolbar.Title=CSBTitle(ToolbarTitle)";
_toolbar.setTitle(BA.ObjectToCharSequence(_csbtitle(_ba,_toolbartitle).getObject()));
 //BA.debugLineNum = 43;BA.debugLine="toolbar.TitleTextColor=Colors.White";
_toolbar.setTitleTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 44;BA.debugLine="toolbar.Padding=Array As Int(0,StatusBarHeight,0,";
_toolbar.setPadding(new int[]{(int) (0),_statusbarheight,(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 45;BA.debugLine="toolbar.Height=MaterialActionBarHeight+(StatusBar";
_toolbar.setHeight((int) (_materialactionbarheight+(_statusbarheight*1.5)));
 //BA.debugLineNum = 47;BA.debugLine="If HasBackBtn Then";
if (_hasbackbtn) { 
 //BA.debugLineNum = 48;BA.debugLine="Dim XML1 As XmlLayoutBuilder";
_xml1 = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 49;BA.debugLine="toolbar.NavigationIconDrawable = XML1.GetDrawabl";
_toolbar.setNavigationIconDrawable(_xml1.GetDrawable("round_arrow_back_white_24"));
 };
 //BA.debugLineNum = 52;BA.debugLine="NextBox.SetLayout(0,toolbar.Height,100%x,100%y-to";
_nextbox.SetLayout((int) (0),_toolbar.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),_ba),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),_ba)-_toolbar.getHeight()));
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static boolean  _validateemail(anywheresoftware.b4a.BA _ba,String _emailaddress) throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matchemail = null;
 //BA.debugLineNum = 553;BA.debugLine="Sub ValidateEmail(EmailAddress As String) As Boole";
 //BA.debugLineNum = 555;BA.debugLine="Dim MatchEmail As Matcher = Regex.Matcher(\"^(?i)[";
_matchemail = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matchemail = anywheresoftware.b4a.keywords.Common.Regex.Matcher("^(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])$",_emailaddress);
 //BA.debugLineNum = 557;BA.debugLine="If MatchEmail.Find = True Then";
if (_matchemail.Find()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 558;BA.debugLine="Log(MatchEmail.Match)";
anywheresoftware.b4a.keywords.Common.LogImpl("46619141",_matchemail.getMatch(),0);
 //BA.debugLineNum = 559;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 561;BA.debugLine="Log(\"Oops, please double check your email addres";
anywheresoftware.b4a.keywords.Common.LogImpl("46619144","Oops, please double check your email address...",0);
 //BA.debugLineNum = 562;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 565;BA.debugLine="End Sub";
return false;
}
}
