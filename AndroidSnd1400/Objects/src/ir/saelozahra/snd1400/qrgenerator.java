package ir.saelozahra.snd1400;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class qrgenerator extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "ir.saelozahra.snd1400.qrgenerator");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", ir.saelozahra.snd1400.qrgenerator.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
public int _modulesize = 0;
public int _gfsize = 0;
public int[] _exptable = null;
public int[] _logtable = null;
public int[] _polyzero = null;
public int[] _generator7 = null;
public int[] _generator10 = null;
public int[] _generator13 = null;
public int[] _generator15 = null;
public int[] _generator16 = null;
public int[] _generator17 = null;
public int[] _generator18 = null;
public int[] _generator20 = null;
public int[] _generator22 = null;
public int[] _generator24 = null;
public int[] _generator26 = null;
public int[] _generator28 = null;
public int[] _generator30 = null;
public ir.saelozahra.snd1400.b4xbytesbuilder _tempbb = null;
public boolean[][] _matrix = null;
public boolean[][] _reserved = null;
public int _numberofmodules = 0;
public int _mbitmapsize = 0;
public anywheresoftware.b4a.objects.collections.List _versions = null;
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
public ir.saelozahra.snd1400.selectmapact _selectmapact = null;
public ir.saelozahra.snd1400.singlecandidateact _singlecandidateact = null;
public ir.saelozahra.snd1400.b4xcollections _b4xcollections = null;
public ir.saelozahra.snd1400.httputils2service _httputils2service = null;
public static class _qrversiondata{
public boolean IsInitialized;
public byte[] Format;
public int[] Generator;
public int MaxSize;
public int Version;
public int MaxUsableSize;
public int[] Alignments;
public int Group1Size;
public int Group2Size;
public int Block1Size;
public int Block2Size;
public String VersionName;
public byte[] VersionInformation;
public void Initialize() {
IsInitialized = true;
Format = new byte[0];
;
Generator = new int[0];
;
MaxSize = 0;
Version = 0;
MaxUsableSize = 0;
Alignments = new int[0];
;
Group1Size = 0;
Group2Size = 0;
Block1Size = 0;
Block2Size = 0;
VersionName = "";
VersionInformation = new byte[0];
;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public String  _addalignments(int[] _positions) throws Exception{
int _left = 0;
int _top = 0;
 //BA.debugLineNum = 405;BA.debugLine="Private Sub AddAlignments (Positions() As Int)";
 //BA.debugLineNum = 406;BA.debugLine="For Each left As Int In Positions";
{
final int[] group1 = _positions;
final int groupLen1 = group1.length
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_left = group1[index1];
 //BA.debugLineNum = 407;BA.debugLine="For Each top As Int In Positions";
{
final int[] group2 = _positions;
final int groupLen2 = group2.length
;int index2 = 0;
;
for (; index2 < groupLen2;index2++){
_top = group2[index2];
 //BA.debugLineNum = 408;BA.debugLine="AddFinder (left - 2, top - 2, 4)";
_addfinder((int) (_left-2),(int) (_top-2),(int) (4));
 }
};
 }
};
 //BA.debugLineNum = 411;BA.debugLine="End Sub";
return "";
}
public String  _adddatatomatrix(byte[] _encoded,ir.saelozahra.snd1400.qrgenerator._qrversiondata _vd) throws Exception{
byte[] _format = null;
anywheresoftware.b4a.objects.collections.List _order = null;
byte _b = (byte)0;
int[] _xy = null;
int _i = 0;
byte[] _versioninformation = null;
int _c = 0;
int _x = 0;
int _y = 0;
 //BA.debugLineNum = 239;BA.debugLine="Private Sub AddDataToMatrix (Encoded() As Byte, vd";
 //BA.debugLineNum = 240;BA.debugLine="Dim format() As Byte = vd.Format";
_format = _vd.Format /*byte[]*/ ;
 //BA.debugLineNum = 241;BA.debugLine="Dim order As List = CreateOrder";
_order = new anywheresoftware.b4a.objects.collections.List();
_order = _createorder();
 //BA.debugLineNum = 243;BA.debugLine="For Each b As Byte In Encoded";
{
final byte[] group3 = _encoded;
final int groupLen3 = group3.length
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_b = group3[index3];
 //BA.debugLineNum = 244;BA.debugLine="Dim xy() As Int = GetNextPosition(order)";
_xy = _getnextposition(_order);
 //BA.debugLineNum = 245;BA.debugLine="Matrix(xy(0), xy(1)) = (b = 1)";
_matrix[_xy[(int) (0)]][_xy[(int) (1)]] = (_b==1);
 //BA.debugLineNum = 246;BA.debugLine="If (xy(1) + xy(0)) Mod 2 = 0 Then Matrix(xy(0),";
if ((_xy[(int) (1)]+_xy[(int) (0)])%2==0) { 
_matrix[_xy[(int) (0)]][_xy[(int) (1)]] = __c.Not(_matrix[_xy[(int) (0)]][_xy[(int) (1)]]);};
 }
};
 //BA.debugLineNum = 248;BA.debugLine="For i = 0 To 5";
{
final int step8 = 1;
final int limit8 = (int) (5);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
 //BA.debugLineNum = 249;BA.debugLine="Matrix(i, 8) = format(i) = 1";
_matrix[_i][(int) (8)] = _format[_i]==1;
 //BA.debugLineNum = 250;BA.debugLine="Matrix(8, NumberOfModules - 1 - i) = format(i) =";
_matrix[(int) (8)][(int) (_numberofmodules-1-_i)] = _format[_i]==1;
 }
};
 //BA.debugLineNum = 252;BA.debugLine="Matrix(7, 8) = format(6) = 1";
_matrix[(int) (7)][(int) (8)] = _format[(int) (6)]==1;
 //BA.debugLineNum = 253;BA.debugLine="Matrix(8, NumberOfModules - 1 - 6) = format(6) =";
_matrix[(int) (8)][(int) (_numberofmodules-1-6)] = _format[(int) (6)]==1;
 //BA.debugLineNum = 254;BA.debugLine="Matrix(8, 8) = format(7) = 1";
_matrix[(int) (8)][(int) (8)] = _format[(int) (7)]==1;
 //BA.debugLineNum = 255;BA.debugLine="Matrix(8, 7) = format(8) = 1";
_matrix[(int) (8)][(int) (7)] = _format[(int) (8)]==1;
 //BA.debugLineNum = 256;BA.debugLine="For i = 0 To 5";
{
final int step16 = 1;
final int limit16 = (int) (5);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
 //BA.debugLineNum = 257;BA.debugLine="Matrix(8, 5 - i) = format(i + 9) = 1";
_matrix[(int) (8)][(int) (5-_i)] = _format[(int) (_i+9)]==1;
 }
};
 //BA.debugLineNum = 259;BA.debugLine="For i = 0 To 7";
{
final int step19 = 1;
final int limit19 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit19 ;_i = _i + step19 ) {
 //BA.debugLineNum = 260;BA.debugLine="Matrix(NumberOfModules - 1 - 7 + i, 8) = format(";
_matrix[(int) (_numberofmodules-1-7+_i)][(int) (8)] = _format[(int) (7+_i)]==1;
 }
};
 //BA.debugLineNum = 262;BA.debugLine="If vd.Version >= 7 Then";
if (_vd.Version /*int*/ >=7) { 
 //BA.debugLineNum = 263;BA.debugLine="Dim VersionInformation() As Byte = vd.VersionInf";
_versioninformation = _vd.VersionInformation /*byte[]*/ ;
 //BA.debugLineNum = 264;BA.debugLine="Dim c As Int = 18";
_c = (int) (18);
 //BA.debugLineNum = 265;BA.debugLine="For x = 0 To 5";
{
final int step25 = 1;
final int limit25 = (int) (5);
_x = (int) (0) ;
for (;_x <= limit25 ;_x = _x + step25 ) {
 //BA.debugLineNum = 266;BA.debugLine="For y = 0 To 2";
{
final int step26 = 1;
final int limit26 = (int) (2);
_y = (int) (0) ;
for (;_y <= limit26 ;_y = _y + step26 ) {
 //BA.debugLineNum = 267;BA.debugLine="c = c - 1";
_c = (int) (_c-1);
 //BA.debugLineNum = 268;BA.debugLine="Matrix(x, NumberOfModules - 11 + y) = VersionI";
_matrix[_x][(int) (_numberofmodules-11+_y)] = _versioninformation[_c]==1;
 //BA.debugLineNum = 269;BA.debugLine="Matrix(NumberOfModules - 11 + y, x) = VersionI";
_matrix[(int) (_numberofmodules-11+_y)][_x] = _versioninformation[_c]==1;
 }
};
 }
};
 };
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return "";
}
public String  _addfinder(int _left,int _top,int _sizeminone) throws Exception{
int _y = 0;
int _x = 0;
boolean _value = false;
 //BA.debugLineNum = 413;BA.debugLine="Private Sub AddFinder (Left As Int, Top As Int, Si";
 //BA.debugLineNum = 414;BA.debugLine="For y = 0 To SizeMinOne";
{
final int step1 = 1;
final int limit1 = _sizeminone;
_y = (int) (0) ;
for (;_y <= limit1 ;_y = _y + step1 ) {
 //BA.debugLineNum = 415;BA.debugLine="For x = 0 To SizeMinOne";
{
final int step2 = 1;
final int limit2 = _sizeminone;
_x = (int) (0) ;
for (;_x <= limit2 ;_x = _x + step2 ) {
 //BA.debugLineNum = 416;BA.debugLine="If Reserved(Left + x, Top + y) Then";
if (_reserved[(int) (_left+_x)][(int) (_top+_y)]) { 
 //BA.debugLineNum = 417;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 }
};
 //BA.debugLineNum = 421;BA.debugLine="For y = 0 To SizeMinOne";
{
final int step8 = 1;
final int limit8 = _sizeminone;
_y = (int) (0) ;
for (;_y <= limit8 ;_y = _y + step8 ) {
 //BA.debugLineNum = 422;BA.debugLine="For x = 0 To SizeMinOne";
{
final int step9 = 1;
final int limit9 = _sizeminone;
_x = (int) (0) ;
for (;_x <= limit9 ;_x = _x + step9 ) {
 //BA.debugLineNum = 423;BA.debugLine="Dim value As Boolean";
_value = false;
 //BA.debugLineNum = 424;BA.debugLine="If x = 0 Or x = SizeMinOne Or y = 0 Or y = Size";
if (_x==0 || _x==_sizeminone || _y==0 || _y==_sizeminone) { 
 //BA.debugLineNum = 425;BA.debugLine="value = True";
_value = __c.True;
 }else if(_x!=1 && _y!=1 && _x!=_sizeminone-1 && _y!=_sizeminone-1) { 
 //BA.debugLineNum = 427;BA.debugLine="value = True";
_value = __c.True;
 };
 //BA.debugLineNum = 429;BA.debugLine="Matrix(Left + x, Top + y) = value";
_matrix[(int) (_left+_x)][(int) (_top+_y)] = _value;
 //BA.debugLineNum = 430;BA.debugLine="Reserved(Left + x, Top + y) = True";
_reserved[(int) (_left+_x)][(int) (_top+_y)] = __c.True;
 }
};
 }
};
 //BA.debugLineNum = 433;BA.debugLine="End Sub";
return "";
}
public String  _addfinders(ir.saelozahra.snd1400.qrgenerator._qrversiondata _vd) throws Exception{
int _x = 0;
int _y = 0;
int _i = 0;
 //BA.debugLineNum = 367;BA.debugLine="Private Sub AddFinders (vd As QRVersionData)";
 //BA.debugLineNum = 368;BA.debugLine="AddFinder(0, 0, 6)";
_addfinder((int) (0),(int) (0),(int) (6));
 //BA.debugLineNum = 369;BA.debugLine="AddFinder(NumberOfModules - 7, 0, 6)";
_addfinder((int) (_numberofmodules-7),(int) (0),(int) (6));
 //BA.debugLineNum = 370;BA.debugLine="AddFinder(0, NumberOfModules - 7, 6)";
_addfinder((int) (0),(int) (_numberofmodules-7),(int) (6));
 //BA.debugLineNum = 371;BA.debugLine="AddAlignments(vd.Alignments)";
_addalignments(_vd.Alignments /*int[]*/ );
 //BA.debugLineNum = 372;BA.debugLine="If vd.Version >= 7 Then";
if (_vd.Version /*int*/ >=7) { 
 //BA.debugLineNum = 373;BA.debugLine="For x = 0 To 2";
{
final int step6 = 1;
final int limit6 = (int) (2);
_x = (int) (0) ;
for (;_x <= limit6 ;_x = _x + step6 ) {
 //BA.debugLineNum = 374;BA.debugLine="For y = 0 To 5";
{
final int step7 = 1;
final int limit7 = (int) (5);
_y = (int) (0) ;
for (;_y <= limit7 ;_y = _y + step7 ) {
 //BA.debugLineNum = 375;BA.debugLine="Reserved(y, NumberOfModules - 11 + x) = True";
_reserved[_y][(int) (_numberofmodules-11+_x)] = __c.True;
 //BA.debugLineNum = 376;BA.debugLine="Reserved(NumberOfModules - 11 + x, y) = True";
_reserved[(int) (_numberofmodules-11+_x)][_y] = __c.True;
 }
};
 }
};
 };
 //BA.debugLineNum = 381;BA.debugLine="For i = 8 To NumberOfModules - 8";
{
final int step13 = 1;
final int limit13 = (int) (_numberofmodules-8);
_i = (int) (8) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 382;BA.debugLine="Matrix(i, 6) = (i Mod 2 = 0)";
_matrix[_i][(int) (6)] = (_i%2==0);
 //BA.debugLineNum = 383;BA.debugLine="Matrix(6, i) = (i Mod 2 = 0)";
_matrix[(int) (6)][_i] = (_i%2==0);
 //BA.debugLineNum = 384;BA.debugLine="Reserved(i, 6) = True";
_reserved[_i][(int) (6)] = __c.True;
 //BA.debugLineNum = 385;BA.debugLine="Reserved(6, i) = True";
_reserved[(int) (6)][_i] = __c.True;
 }
};
 //BA.debugLineNum = 387;BA.debugLine="Matrix(8, NumberOfModules - 1 - 7) = True";
_matrix[(int) (8)][(int) (_numberofmodules-1-7)] = __c.True;
 //BA.debugLineNum = 388;BA.debugLine="Reserved(8, NumberOfModules - 1 - 7) = True";
_reserved[(int) (8)][(int) (_numberofmodules-1-7)] = __c.True;
 //BA.debugLineNum = 389;BA.debugLine="For i = 0 To 7";
{
final int step21 = 1;
final int limit21 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit21 ;_i = _i + step21 ) {
 //BA.debugLineNum = 390;BA.debugLine="Reserved(7, i) = True";
_reserved[(int) (7)][_i] = __c.True;
 //BA.debugLineNum = 391;BA.debugLine="Reserved(7, NumberOfModules - 1 - i) = True";
_reserved[(int) (7)][(int) (_numberofmodules-1-_i)] = __c.True;
 //BA.debugLineNum = 392;BA.debugLine="Reserved(8, NumberOfModules - 1 - i) = True";
_reserved[(int) (8)][(int) (_numberofmodules-1-_i)] = __c.True;
 //BA.debugLineNum = 393;BA.debugLine="Reserved(NumberOfModules -1 - 7, i) = True";
_reserved[(int) (_numberofmodules-1-7)][_i] = __c.True;
 //BA.debugLineNum = 394;BA.debugLine="Reserved(i, 7) = True";
_reserved[_i][(int) (7)] = __c.True;
 //BA.debugLineNum = 395;BA.debugLine="Reserved(i,NumberOfModules -1 - 7) = True";
_reserved[_i][(int) (_numberofmodules-1-7)] = __c.True;
 //BA.debugLineNum = 396;BA.debugLine="Reserved(NumberOfModules -1 - i, 7) = True";
_reserved[(int) (_numberofmodules-1-_i)][(int) (7)] = __c.True;
 //BA.debugLineNum = 397;BA.debugLine="Reserved(NumberOfModules -1 - i, 8) = True";
_reserved[(int) (_numberofmodules-1-_i)][(int) (8)] = __c.True;
 }
};
 //BA.debugLineNum = 399;BA.debugLine="For i = 0 To 8";
{
final int step31 = 1;
final int limit31 = (int) (8);
_i = (int) (0) ;
for (;_i <= limit31 ;_i = _i + step31 ) {
 //BA.debugLineNum = 400;BA.debugLine="Reserved(8, i) = True";
_reserved[(int) (8)][_i] = __c.True;
 //BA.debugLineNum = 401;BA.debugLine="Reserved(i, 8) = True";
_reserved[_i][(int) (8)] = __c.True;
 }
};
 //BA.debugLineNum = 403;BA.debugLine="End Sub";
return "";
}
public int  _bitstounsignedbyte(byte[] _b,int _offset) throws Exception{
int _res = 0;
int _i = 0;
int _x = 0;
 //BA.debugLineNum = 328;BA.debugLine="Private Sub BitsToUnsignedByte (b() As Byte, Offse";
 //BA.debugLineNum = 329;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 330;BA.debugLine="For i = 0 To 7";
{
final int step2 = 1;
final int limit2 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 331;BA.debugLine="Dim x As Int = Bit.ShiftLeft(1, 7 - i)";
_x = __c.Bit.ShiftLeft((int) (1),(int) (7-_i));
 //BA.debugLineNum = 332;BA.debugLine="res = res + b(i + Offset) * x";
_res = (int) (_res+_b[(int) (_i+_offset)]*_x);
 }
};
 //BA.debugLineNum = 334;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return 0;
}
public int[]  _calcreedsolomon(int[] _input,int[] _generator) throws Exception{
int _ecbytes = 0;
int[] _infocoefficients = null;
int[] _remainder = null;
 //BA.debugLineNum = 437;BA.debugLine="Private Sub CalcReedSolomon (Input() As Int, Gener";
 //BA.debugLineNum = 438;BA.debugLine="Dim ecBytes As Int = Generator.Length - 1";
_ecbytes = (int) (_generator.length-1);
 //BA.debugLineNum = 439;BA.debugLine="Dim InfoCoefficients(Input.Length) As Int";
_infocoefficients = new int[_input.length];
;
 //BA.debugLineNum = 440;BA.debugLine="IntArrayCopy(Input, 0, InfoCoefficients, 0, Input";
_intarraycopy(_input,(int) (0),_infocoefficients,(int) (0),_input.length);
 //BA.debugLineNum = 441;BA.debugLine="InfoCoefficients = CreateGFPoly(InfoCoefficients)";
_infocoefficients = _creategfpoly(_infocoefficients);
 //BA.debugLineNum = 442;BA.debugLine="InfoCoefficients = PolyMultiplyByMonomial(InfoCoe";
_infocoefficients = _polymultiplybymonomial(_infocoefficients,_ecbytes,(int) (1));
 //BA.debugLineNum = 443;BA.debugLine="Dim remainder() As Int = PolyDivide(InfoCoefficie";
_remainder = _polydivide(_infocoefficients,_generator);
 //BA.debugLineNum = 444;BA.debugLine="Return remainder";
if (true) return _remainder;
 //BA.debugLineNum = 445;BA.debugLine="End Sub";
return null;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 4;BA.debugLine="Public cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
 //BA.debugLineNum = 5;BA.debugLine="Private ModuleSize As Int";
_modulesize = 0;
 //BA.debugLineNum = 6;BA.debugLine="Private GFSize As Int = 256";
_gfsize = (int) (256);
 //BA.debugLineNum = 7;BA.debugLine="Private ExpTable(GFSize) As Int";
_exptable = new int[_gfsize];
;
 //BA.debugLineNum = 8;BA.debugLine="Private LogTable(GFSize) As Int";
_logtable = new int[_gfsize];
;
 //BA.debugLineNum = 9;BA.debugLine="Private PolyZero() As Int = Array As Int(0)";
_polyzero = new int[]{(int) (0)};
 //BA.debugLineNum = 11;BA.debugLine="Private Generator7() As Int = Array As Int(1, 127";
_generator7 = new int[]{(int) (1),(int) (127),(int) (122),(int) (154),(int) (164),(int) (11),(int) (68),(int) (117)};
 //BA.debugLineNum = 12;BA.debugLine="Private Generator10() As Int = Array As Int(1, 21";
_generator10 = new int[]{(int) (1),(int) (216),(int) (194),(int) (159),(int) (111),(int) (199),(int) (94),(int) (95),(int) (113),(int) (157),(int) (193)};
 //BA.debugLineNum = 13;BA.debugLine="Private Generator13() As Int = Array As Int(1, 13";
_generator13 = new int[]{(int) (1),(int) (137),(int) (73),(int) (227),(int) (17),(int) (177),(int) (17),(int) (52),(int) (13),(int) (46),(int) (43),(int) (83),(int) (132),(int) (120)};
 //BA.debugLineNum = 14;BA.debugLine="Private Generator15() As Int = Array As Int(1, 29";
_generator15 = new int[]{(int) (1),(int) (29),(int) (196),(int) (111),(int) (163),(int) (112),(int) (74),(int) (10),(int) (105),(int) (105),(int) (139),(int) (132),(int) (151),(int) (32),(int) (134),(int) (26)};
 //BA.debugLineNum = 15;BA.debugLine="Private Generator16() As Int = Array As Int(1, 59";
_generator16 = new int[]{(int) (1),(int) (59),(int) (13),(int) (104),(int) (189),(int) (68),(int) (209),(int) (30),(int) (8),(int) (163),(int) (65),(int) (41),(int) (229),(int) (98),(int) (50),(int) (36),(int) (59)};
 //BA.debugLineNum = 16;BA.debugLine="Private Generator17() As Int = Array As Int(1, 11";
_generator17 = new int[]{(int) (1),(int) (119),(int) (66),(int) (83),(int) (120),(int) (119),(int) (22),(int) (197),(int) (83),(int) (249),(int) (41),(int) (143),(int) (134),(int) (85),(int) (53),(int) (125),(int) (99),(int) (79)};
 //BA.debugLineNum = 17;BA.debugLine="Private Generator18() As Int = Array As Int(1, 23";
_generator18 = new int[]{(int) (1),(int) (239),(int) (251),(int) (183),(int) (113),(int) (149),(int) (175),(int) (199),(int) (215),(int) (240),(int) (220),(int) (73),(int) (82),(int) (173),(int) (75),(int) (32),(int) (67),(int) (217),(int) (146)};
 //BA.debugLineNum = 18;BA.debugLine="Private Generator20() As Int = Array As Int(1, 15";
_generator20 = new int[]{(int) (1),(int) (152),(int) (185),(int) (240),(int) (5),(int) (111),(int) (99),(int) (6),(int) (220),(int) (112),(int) (150),(int) (69),(int) (36),(int) (187),(int) (22),(int) (228),(int) (198),(int) (121),(int) (121),(int) (165),(int) (174)};
 //BA.debugLineNum = 19;BA.debugLine="Private Generator22() As Int = Array As Int(1, 89";
_generator22 = new int[]{(int) (1),(int) (89),(int) (179),(int) (131),(int) (176),(int) (182),(int) (244),(int) (19),(int) (189),(int) (69),(int) (40),(int) (28),(int) (137),(int) (29),(int) (123),(int) (67),(int) (253),(int) (86),(int) (218),(int) (230),(int) (26),(int) (145),(int) (245)};
 //BA.debugLineNum = 20;BA.debugLine="Private Generator24() As Int = Array As Int(1, 12";
_generator24 = new int[]{(int) (1),(int) (122),(int) (118),(int) (169),(int) (70),(int) (178),(int) (237),(int) (216),(int) (102),(int) (115),(int) (150),(int) (229),(int) (73),(int) (130),(int) (72),(int) (61),(int) (43),(int) (206),(int) (1),(int) (237),(int) (247),(int) (127),(int) (217),(int) (144),(int) (117)};
 //BA.debugLineNum = 21;BA.debugLine="Private Generator26() As Int = Array As Int(1, 24";
_generator26 = new int[]{(int) (1),(int) (246),(int) (51),(int) (183),(int) (4),(int) (136),(int) (98),(int) (199),(int) (152),(int) (77),(int) (56),(int) (206),(int) (24),(int) (145),(int) (40),(int) (209),(int) (117),(int) (233),(int) (42),(int) (135),(int) (68),(int) (70),(int) (144),(int) (146),(int) (77),(int) (43),(int) (94)};
 //BA.debugLineNum = 22;BA.debugLine="Private Generator28() As Int = Array As Int(1, 25";
_generator28 = new int[]{(int) (1),(int) (252),(int) (9),(int) (28),(int) (13),(int) (18),(int) (251),(int) (208),(int) (150),(int) (103),(int) (174),(int) (100),(int) (41),(int) (167),(int) (12),(int) (247),(int) (56),(int) (117),(int) (119),(int) (233),(int) (127),(int) (181),(int) (100),(int) (121),(int) (147),(int) (176),(int) (74),(int) (58),(int) (197)};
 //BA.debugLineNum = 23;BA.debugLine="Private Generator30() As Int = Array As Int(1, 21";
_generator30 = new int[]{(int) (1),(int) (212),(int) (246),(int) (77),(int) (73),(int) (195),(int) (192),(int) (75),(int) (98),(int) (5),(int) (70),(int) (103),(int) (177),(int) (22),(int) (217),(int) (138),(int) (51),(int) (181),(int) (246),(int) (72),(int) (25),(int) (18),(int) (46),(int) (228),(int) (74),(int) (216),(int) (195),(int) (11),(int) (106),(int) (130),(int) (150)};
 //BA.debugLineNum = 26;BA.debugLine="Private TempBB As B4XBytesBuilder";
_tempbb = new ir.saelozahra.snd1400.b4xbytesbuilder();
 //BA.debugLineNum = 27;BA.debugLine="Private Matrix(0, 0) As Boolean";
_matrix = new boolean[(int) (0)][];
{
int d0 = _matrix.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_matrix[i0] = new boolean[d1];
}
}
;
 //BA.debugLineNum = 28;BA.debugLine="Private Reserved(0, 0) As Boolean";
_reserved = new boolean[(int) (0)][];
{
int d0 = _reserved.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_reserved[i0] = new boolean[d1];
}
}
;
 //BA.debugLineNum = 29;BA.debugLine="Private NumberOfModules As Int";
_numberofmodules = 0;
 //BA.debugLineNum = 30;BA.debugLine="Private mBitmapSize As Int";
_mbitmapsize = 0;
 //BA.debugLineNum = 31;BA.debugLine="Type QRVersionData (Format() As Byte, Generator()";
;
 //BA.debugLineNum = 33;BA.debugLine="Private versions As List";
_versions = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper  _create(String _text) throws Exception{
byte[] _bytes = null;
ir.saelozahra.snd1400.qrgenerator._qrversiondata _vd = null;
ir.saelozahra.snd1400.qrgenerator._qrversiondata _version = null;
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
byte[] _mode = null;
byte[] _contentcountindicator = null;
ir.saelozahra.snd1400.b4xbytesbuilder _encodeddata = null;
byte _b = (byte)0;
int _padsize = 0;
byte[] _pad = null;
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _bmp = null;
 //BA.debugLineNum = 128;BA.debugLine="Public Sub Create(Text As String) As B4XBitmap";
 //BA.debugLineNum = 129;BA.debugLine="Dim Bytes() As Byte = Text.GetBytes(\"utf8\") 'non-";
_bytes = _text.getBytes("utf8");
 //BA.debugLineNum = 130;BA.debugLine="Dim vd As QRVersionData";
_vd = new ir.saelozahra.snd1400.qrgenerator._qrversiondata();
 //BA.debugLineNum = 131;BA.debugLine="For Each version As QRVersionData In versions";
{
final anywheresoftware.b4a.BA.IterableList group3 = _versions;
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_version = (ir.saelozahra.snd1400.qrgenerator._qrversiondata)(group3.Get(index3));
 //BA.debugLineNum = 132;BA.debugLine="If version.MaxUsableSize >= Bytes.Length Then";
if (_version.MaxUsableSize /*int*/ >=_bytes.length) { 
 //BA.debugLineNum = 133;BA.debugLine="vd = version";
_vd = _version;
 //BA.debugLineNum = 134;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 137;BA.debugLine="If vd.IsInitialized = False Then";
if (_vd.IsInitialized /*boolean*/ ==__c.False) { 
 //BA.debugLineNum = 139;BA.debugLine="Log(\"Too long!\")";
__c.LogImpl("615073291","Too long!",0);
 //BA.debugLineNum = 140;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper(), (android.graphics.Bitmap)(__c.Null));
 };
 //BA.debugLineNum = 142;BA.debugLine="Log(vd.VersionName & \", Size: \" & Bytes.Length)";
__c.LogImpl("615073294",_vd.VersionName /*String*/ +", Size: "+BA.NumberToString(_bytes.length),0);
 //BA.debugLineNum = 144;BA.debugLine="NumberOfModules = 17 + vd.Version * 4";
_numberofmodules = (int) (17+_vd.Version /*int*/ *4);
 //BA.debugLineNum = 145;BA.debugLine="ModuleSize = mBitmapSize / (NumberOfModules + 8)";
_modulesize = (int) (_mbitmapsize/(double)(_numberofmodules+8));
 //BA.debugLineNum = 147;BA.debugLine="mBitmapSize = ModuleSize * (NumberOfModules + 8)";
_mbitmapsize = (int) (_modulesize*(_numberofmodules+8));
 //BA.debugLineNum = 148;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 149;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, mBitmapSize, mBitmap";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_mbitmapsize,_mbitmapsize);
 //BA.debugLineNum = 150;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize(_p);
 //BA.debugLineNum = 153;BA.debugLine="Dim Matrix(NumberOfModules, NumberOfModules) As B";
_matrix = new boolean[_numberofmodules][];
{
int d0 = _matrix.length;
int d1 = _numberofmodules;
for (int i0 = 0;i0 < d0;i0++) {
_matrix[i0] = new boolean[d1];
}
}
;
 //BA.debugLineNum = 154;BA.debugLine="Dim Reserved(NumberOfModules, NumberOfModules) As";
_reserved = new boolean[_numberofmodules][];
{
int d0 = _reserved.length;
int d1 = _numberofmodules;
for (int i0 = 0;i0 < d0;i0++) {
_reserved[i0] = new boolean[d1];
}
}
;
 //BA.debugLineNum = 156;BA.debugLine="Dim Mode() As Byte = Array As Byte(0, 1, 0, 0) 'b";
_mode = new byte[]{(byte) (0),(byte) (1),(byte) (0),(byte) (0)};
 //BA.debugLineNum = 157;BA.debugLine="Dim ContentCountIndicator() As Byte";
_contentcountindicator = new byte[(int) (0)];
;
 //BA.debugLineNum = 158;BA.debugLine="If vd.Version >= 10 Then";
if (_vd.Version /*int*/ >=10) { 
 //BA.debugLineNum = 159;BA.debugLine="ContentCountIndicator = IntTo16Bits(Bytes.Length";
_contentcountindicator = _intto16bits(_bytes.length);
 }else {
 //BA.debugLineNum = 161;BA.debugLine="ContentCountIndicator = UnsignedByteToBits(Bytes";
_contentcountindicator = _unsignedbytetobits(_bytes.length);
 };
 //BA.debugLineNum = 163;BA.debugLine="Dim EncodedData As B4XBytesBuilder";
_encodeddata = new ir.saelozahra.snd1400.b4xbytesbuilder();
 //BA.debugLineNum = 164;BA.debugLine="EncodedData.Initialize";
_encodeddata._initialize /*String*/ (ba);
 //BA.debugLineNum = 165;BA.debugLine="EncodedData.Append(Mode)";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_mode);
 //BA.debugLineNum = 166;BA.debugLine="EncodedData.Append(ContentCountIndicator)";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_contentcountindicator);
 //BA.debugLineNum = 167;BA.debugLine="For Each b As Byte In Bytes";
{
final byte[] group33 = _bytes;
final int groupLen33 = group33.length
;int index33 = 0;
;
for (; index33 < groupLen33;index33++){
_b = group33[index33];
 //BA.debugLineNum = 168;BA.debugLine="EncodedData.Append(UnsignedByteToBits(Bit.And(0x";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_unsignedbytetobits(__c.Bit.And((int) (0xff),(int) (_b))));
 }
};
 //BA.debugLineNum = 171;BA.debugLine="Dim PadSize As Int = Min(4, vd.MaxSize - EncodedD";
_padsize = (int) (__c.Min(4,_vd.MaxSize /*int*/ -_encodeddata._getlength /*int*/ ()));
 //BA.debugLineNum = 172;BA.debugLine="Dim pad(PadSize) As Byte";
_pad = new byte[_padsize];
;
 //BA.debugLineNum = 173;BA.debugLine="EncodedData.Append(pad)";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_pad);
 //BA.debugLineNum = 174;BA.debugLine="Do While EncodedData.Length Mod 8 <> 0";
while (_encodeddata._getlength /*int*/ ()%8!=0) {
 //BA.debugLineNum = 175;BA.debugLine="EncodedData.Append(Array As Byte(0))";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (0)});
 }
;
 //BA.debugLineNum = 178;BA.debugLine="Do While EncodedData.Length < vd.MaxSize";
while (_encodeddata._getlength /*int*/ ()<_vd.MaxSize /*int*/ ) {
 //BA.debugLineNum = 179;BA.debugLine="EncodedData.Append(Array As Byte(1,1,1,0,1,1,0,0";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0)});
 //BA.debugLineNum = 180;BA.debugLine="If EncodedData.Length < vd.MaxSize Then EncodedD";
if (_encodeddata._getlength /*int*/ ()<_vd.MaxSize /*int*/ ) { 
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1)});};
 }
;
 //BA.debugLineNum = 182;BA.debugLine="VersionWithTwoGroups(vd.Generator, vd.Group1Size,";
_versionwithtwogroups(_vd.Generator /*int[]*/ ,_vd.Group1Size /*int*/ ,_vd.Group2Size /*int*/ ,_vd.Block1Size /*int*/ ,_vd.Block2Size /*int*/ ,_encodeddata);
 //BA.debugLineNum = 183;BA.debugLine="AddFinders (vd)";
_addfinders(_vd);
 //BA.debugLineNum = 184;BA.debugLine="AddDataToMatrix(EncodedData.ToArray, vd)";
_adddatatomatrix(_encodeddata._toarray /*byte[]*/ (),_vd);
 //BA.debugLineNum = 185;BA.debugLine="DrawMatrix";
_drawmatrix();
 //BA.debugLineNum = 186;BA.debugLine="cvs.Invalidate";
_cvs.Invalidate();
 //BA.debugLineNum = 187;BA.debugLine="Dim bmp As B4XBitmap = cvs.CreateBitmap";
_bmp = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bmp = _cvs.CreateBitmap();
 //BA.debugLineNum = 188;BA.debugLine="cvs.Release";
_cvs.Release();
 //BA.debugLineNum = 189;BA.debugLine="Return bmp";
if (true) return _bmp;
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return null;
}
public int[]  _creategfpoly(int[] _coefficients) throws Exception{
int _firstnonzero = 0;
int[] _res = null;
 //BA.debugLineNum = 464;BA.debugLine="Private Sub CreateGFPoly(Coefficients() As Int) As";
 //BA.debugLineNum = 465;BA.debugLine="If Coefficients.Length > 1 And Coefficients(0) =";
if (_coefficients.length>1 && _coefficients[(int) (0)]==0) { 
 //BA.debugLineNum = 466;BA.debugLine="Dim FirstNonZero As Int = 1";
_firstnonzero = (int) (1);
 //BA.debugLineNum = 467;BA.debugLine="Do While FirstNonZero < Coefficients.Length And";
while (_firstnonzero<_coefficients.length && _coefficients[_firstnonzero]==0) {
 //BA.debugLineNum = 468;BA.debugLine="FirstNonZero = FirstNonZero + 1";
_firstnonzero = (int) (_firstnonzero+1);
 }
;
 //BA.debugLineNum = 470;BA.debugLine="If FirstNonZero = Coefficients.Length Then";
if (_firstnonzero==_coefficients.length) { 
 //BA.debugLineNum = 471;BA.debugLine="Return Array As Int(0)";
if (true) return new int[]{(int) (0)};
 };
 //BA.debugLineNum = 473;BA.debugLine="Dim res(Coefficients.Length - FirstNonZero) As I";
_res = new int[(int) (_coefficients.length-_firstnonzero)];
;
 //BA.debugLineNum = 474;BA.debugLine="IntArrayCopy(Coefficients, FirstNonZero, res, 0,";
_intarraycopy(_coefficients,_firstnonzero,_res,(int) (0),_res.length);
 //BA.debugLineNum = 475;BA.debugLine="Return res";
if (true) return _res;
 };
 //BA.debugLineNum = 477;BA.debugLine="Return Coefficients";
if (true) return _coefficients;
 //BA.debugLineNum = 478;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _createorder() throws Exception{
anywheresoftware.b4a.objects.collections.List _order = null;
int _x = 0;
int _y = 0;
int _dy = 0;
 //BA.debugLineNum = 284;BA.debugLine="Private Sub CreateOrder As List";
 //BA.debugLineNum = 285;BA.debugLine="Dim Order As List";
_order = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 286;BA.debugLine="Order.Initialize";
_order.Initialize();
 //BA.debugLineNum = 287;BA.debugLine="Dim x As Int = NumberOfModules - 1";
_x = (int) (_numberofmodules-1);
 //BA.debugLineNum = 288;BA.debugLine="Dim y As Int = NumberOfModules - 1";
_y = (int) (_numberofmodules-1);
 //BA.debugLineNum = 289;BA.debugLine="Dim dy As Int = -1";
_dy = (int) (-1);
 //BA.debugLineNum = 290;BA.debugLine="Do While x >= 0 And y >= 0";
while (_x>=0 && _y>=0) {
 //BA.debugLineNum = 291;BA.debugLine="Order.Add(Array As Int(x, y))";
_order.Add((Object)(new int[]{_x,_y}));
 //BA.debugLineNum = 292;BA.debugLine="Order.Add(Array As Int(x - 1, y))";
_order.Add((Object)(new int[]{(int) (_x-1),_y}));
 //BA.debugLineNum = 293;BA.debugLine="y = y + dy";
_y = (int) (_y+_dy);
 //BA.debugLineNum = 294;BA.debugLine="If y = -1 Then";
if (_y==-1) { 
 //BA.debugLineNum = 295;BA.debugLine="x = x - 2";
_x = (int) (_x-2);
 //BA.debugLineNum = 296;BA.debugLine="y = 0";
_y = (int) (0);
 //BA.debugLineNum = 297;BA.debugLine="dy = 1";
_dy = (int) (1);
 }else if(_y==_numberofmodules) { 
 //BA.debugLineNum = 299;BA.debugLine="x = x - 2";
_x = (int) (_x-2);
 //BA.debugLineNum = 300;BA.debugLine="y = NumberOfModules - 1";
_y = (int) (_numberofmodules-1);
 //BA.debugLineNum = 301;BA.debugLine="dy = -1";
_dy = (int) (-1);
 };
 //BA.debugLineNum = 303;BA.debugLine="If x = 6 Then x = x - 1";
if (_x==6) { 
_x = (int) (_x-1);};
 }
;
 //BA.debugLineNum = 305;BA.debugLine="Return Order";
if (true) return _order;
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return null;
}
public ir.saelozahra.snd1400.qrgenerator._qrversiondata  _createversiondata(int _version,String _name,int[] _generator,byte[] _format,int _maxsize,int _maxusablesize,int[] _alignments,int _group1size,int _group2size,int _block1size,int _block2size,byte[] _versioninformation) throws Exception{
ir.saelozahra.snd1400.qrgenerator._qrversiondata _v = null;
 //BA.debugLineNum = 109;BA.debugLine="Private Sub CreateVersionData (Version As Int, Nam";
 //BA.debugLineNum = 111;BA.debugLine="Dim v As QRVersionData";
_v = new ir.saelozahra.snd1400.qrgenerator._qrversiondata();
 //BA.debugLineNum = 112;BA.debugLine="v.Initialize";
_v.Initialize();
 //BA.debugLineNum = 113;BA.debugLine="v.Version = Version";
_v.Version /*int*/  = _version;
 //BA.debugLineNum = 114;BA.debugLine="v.VersionName = Name";
_v.VersionName /*String*/  = _name;
 //BA.debugLineNum = 115;BA.debugLine="v.Generator = Generator";
_v.Generator /*int[]*/  = _generator;
 //BA.debugLineNum = 116;BA.debugLine="v.Format = Format";
_v.Format /*byte[]*/  = _format;
 //BA.debugLineNum = 117;BA.debugLine="v.MaxSize = MaxSize";
_v.MaxSize /*int*/  = _maxsize;
 //BA.debugLineNum = 118;BA.debugLine="v.MaxUsableSize = MaxUsableSize";
_v.MaxUsableSize /*int*/  = _maxusablesize;
 //BA.debugLineNum = 119;BA.debugLine="v.Alignments = Alignments";
_v.Alignments /*int[]*/  = _alignments;
 //BA.debugLineNum = 120;BA.debugLine="v.Group1Size = Group1Size";
_v.Group1Size /*int*/  = _group1size;
 //BA.debugLineNum = 121;BA.debugLine="v.Group2Size = Group2Size";
_v.Group2Size /*int*/  = _group2size;
 //BA.debugLineNum = 122;BA.debugLine="v.Block1Size = Block1Size";
_v.Block1Size /*int*/  = _block1size;
 //BA.debugLineNum = 123;BA.debugLine="v.Block2Size = Block2Size";
_v.Block2Size /*int*/  = _block2size;
 //BA.debugLineNum = 124;BA.debugLine="v.VersionInformation = VersionInformation";
_v.VersionInformation /*byte[]*/  = _versioninformation;
 //BA.debugLineNum = 125;BA.debugLine="Return v";
if (true) return _v;
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return null;
}
public String  _drawmatrix() throws Exception{
anywheresoftware.b4a.objects.B4XCanvas.B4XRect _r = null;
int _y = 0;
int _x = 0;
int _clr = 0;
 //BA.debugLineNum = 308;BA.debugLine="Private Sub DrawMatrix";
 //BA.debugLineNum = 309;BA.debugLine="cvs.DrawRect(cvs.TargetRect, xui.Color_White, Tru";
_cvs.DrawRect(_cvs.getTargetRect(),_xui.Color_White,__c.True,(float) (0));
 //BA.debugLineNum = 310;BA.debugLine="Dim r As B4XRect";
_r = new anywheresoftware.b4a.objects.B4XCanvas.B4XRect();
 //BA.debugLineNum = 311;BA.debugLine="For y = 0 To NumberOfModules - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numberofmodules-1);
_y = (int) (0) ;
for (;_y <= limit3 ;_y = _y + step3 ) {
 //BA.debugLineNum = 312;BA.debugLine="For x = 0 To NumberOfModules - 1";
{
final int step4 = 1;
final int limit4 = (int) (_numberofmodules-1);
_x = (int) (0) ;
for (;_x <= limit4 ;_x = _x + step4 ) {
 //BA.debugLineNum = 313;BA.debugLine="r.Initialize((x + 4) * ModuleSize, (y + 4) * Mo";
_r.Initialize((float) ((_x+4)*_modulesize),(float) ((_y+4)*_modulesize),(float) (0),(float) (0));
 //BA.debugLineNum = 314;BA.debugLine="r.Width = ModuleSize";
_r.setWidth(_modulesize);
 //BA.debugLineNum = 315;BA.debugLine="r.Height = ModuleSize";
_r.setHeight((float) (_modulesize));
 //BA.debugLineNum = 316;BA.debugLine="Dim clr As Int";
_clr = 0;
 //BA.debugLineNum = 317;BA.debugLine="If Matrix(x, y) Then";
if (_matrix[_x][_y]) { 
 //BA.debugLineNum = 318;BA.debugLine="clr = xui.Color_Black";
_clr = _xui.Color_Black;
 //BA.debugLineNum = 320;BA.debugLine="cvs.DrawRect(r, clr, True, 0)";
_cvs.DrawRect(_r,_clr,__c.True,(float) (0));
 };
 }
};
 }
};
 //BA.debugLineNum = 324;BA.debugLine="End Sub";
return "";
}
public int[]  _getnextposition(anywheresoftware.b4a.objects.collections.List _order) throws Exception{
int[] _xy = null;
 //BA.debugLineNum = 275;BA.debugLine="Private Sub GetNextPosition (order As List) As Int";
 //BA.debugLineNum = 276;BA.debugLine="Do While True";
while (__c.True) {
 //BA.debugLineNum = 277;BA.debugLine="Dim xy() As Int = order.Get(0)";
_xy = (int[])(_order.Get((int) (0)));
 //BA.debugLineNum = 278;BA.debugLine="order.RemoveAt(0)";
_order.RemoveAt((int) (0));
 //BA.debugLineNum = 279;BA.debugLine="If Reserved(xy(0), xy(1)) = False Then Return xy";
if (_reserved[_xy[(int) (0)]][_xy[(int) (1)]]==__c.False) { 
if (true) return _xy;};
 }
;
 //BA.debugLineNum = 281;BA.debugLine="Return Null";
if (true) return (int[])(__c.Null);
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return null;
}
public int[]  _gfbuildmonomial(int _degree,int _coefficient) throws Exception{
int[] _c = null;
 //BA.debugLineNum = 543;BA.debugLine="Private Sub GFBuildMonomial(Degree As Int, Coeffic";
 //BA.debugLineNum = 544;BA.debugLine="If Coefficient = 0 Then Return PolyZero";
if (_coefficient==0) { 
if (true) return _polyzero;};
 //BA.debugLineNum = 545;BA.debugLine="Dim c(Degree + 1) As Int";
_c = new int[(int) (_degree+1)];
;
 //BA.debugLineNum = 546;BA.debugLine="c(0) = Coefficient";
_c[(int) (0)] = _coefficient;
 //BA.debugLineNum = 547;BA.debugLine="Return c";
if (true) return _c;
 //BA.debugLineNum = 548;BA.debugLine="End Sub";
return null;
}
public int  _gfinverse(int _a) throws Exception{
 //BA.debugLineNum = 532;BA.debugLine="Private Sub GFInverse(a As Int) As Int";
 //BA.debugLineNum = 533;BA.debugLine="Return ExpTable(GFSize - LogTable(a) - 1)";
if (true) return _exptable[(int) (_gfsize-_logtable[_a]-1)];
 //BA.debugLineNum = 534;BA.debugLine="End Sub";
return 0;
}
public int  _gfmultiply(int _a,int _b) throws Exception{
 //BA.debugLineNum = 536;BA.debugLine="Private Sub GFMultiply(a As Int, b As Int) As Int";
 //BA.debugLineNum = 537;BA.debugLine="If a = 0 Or b = 0 Then";
if (_a==0 || _b==0) { 
 //BA.debugLineNum = 538;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 540;BA.debugLine="Return ExpTable((LogTable(a) + LogTable(b)) Mod (";
if (true) return _exptable[(int) ((_logtable[_a]+_logtable[_b])%(_gfsize-1))];
 //BA.debugLineNum = 541;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,int _bitmapsize) throws Exception{
innerInitialize(_ba);
byte[] _l0 = null;
byte[] _m0 = null;
byte[] _q0 = null;
byte[] _h0 = null;
 //BA.debugLineNum = 37;BA.debugLine="Public Sub Initialize (BitmapSize As Int)";
 //BA.debugLineNum = 38;BA.debugLine="TempBB.Initialize";
_tempbb._initialize /*String*/ (ba);
 //BA.debugLineNum = 39;BA.debugLine="mBitmapSize = BitmapSize";
_mbitmapsize = _bitmapsize;
 //BA.debugLineNum = 40;BA.debugLine="PrepareTables";
_preparetables();
 //BA.debugLineNum = 41;BA.debugLine="versions.Initialize";
_versions.Initialize();
 //BA.debugLineNum = 43;BA.debugLine="Dim L0() As Byte = Array As Byte(1,1,1,0,1,1,1,1,";
_l0 = new byte[]{(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0)};
 //BA.debugLineNum = 44;BA.debugLine="Dim M0() As Byte = Array As Byte(1,0,1,0,1,0,0,0,";
_m0 = new byte[]{(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0)};
 //BA.debugLineNum = 45;BA.debugLine="Dim Q0() As Byte = Array As Byte(0,1,1,0,1,0,1,0,";
_q0 = new byte[]{(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1)};
 //BA.debugLineNum = 46;BA.debugLine="Dim H0() As Byte = Array As Byte(0,0,1,0,1,1,0,1,";
_h0 = new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1)};
 //BA.debugLineNum = 48;BA.debugLine="versions.Add(CreateVersionData(1, \"1L\", Generator";
_versions.Add((Object)(_createversiondata((int) (1),"1L",_generator7,_l0,(int) (19*8),(int) (17),new int[]{},(int) (1),(int) (0),(int) (19),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 49;BA.debugLine="versions.Add(CreateVersionData(1, \"1M\", Generator";
_versions.Add((Object)(_createversiondata((int) (1),"1M",_generator10,_m0,(int) (16*8),(int) (14),new int[]{},(int) (1),(int) (0),(int) (16),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 50;BA.debugLine="versions.Add(CreateVersionData(1, \"1Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (1),"1Q",_generator13,_q0,(int) (13*8),(int) (11),new int[]{},(int) (1),(int) (0),(int) (13),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 51;BA.debugLine="versions.Add(CreateVersionData(1, \"1H\", Generator";
_versions.Add((Object)(_createversiondata((int) (1),"1H",_generator17,_h0,(int) (9*8),(int) (7),new int[]{},(int) (1),(int) (0),(int) (9),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 53;BA.debugLine="versions.Add(CreateVersionData(2, \"2L\", Generator";
_versions.Add((Object)(_createversiondata((int) (2),"2L",_generator10,_l0,(int) (34*8),(int) (32),new int[]{(int) (6),(int) (18)},(int) (1),(int) (0),(int) (34),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 54;BA.debugLine="versions.Add(CreateVersionData(2, \"2M\", Generator";
_versions.Add((Object)(_createversiondata((int) (2),"2M",_generator16,_m0,(int) (28*8),(int) (26),new int[]{(int) (6),(int) (18)},(int) (1),(int) (0),(int) (28),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 55;BA.debugLine="versions.Add(CreateVersionData(2, \"2Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (2),"2Q",_generator22,_q0,(int) (22*8),(int) (20),new int[]{(int) (6),(int) (18)},(int) (1),(int) (0),(int) (22),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 56;BA.debugLine="versions.Add(CreateVersionData(2, \"2H\", Generator";
_versions.Add((Object)(_createversiondata((int) (2),"2H",_generator28,_h0,(int) (16*8),(int) (14),new int[]{(int) (6),(int) (18)},(int) (1),(int) (0),(int) (16),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 58;BA.debugLine="versions.Add(CreateVersionData(3, \"3L\", Generator";
_versions.Add((Object)(_createversiondata((int) (3),"3L",_generator15,_l0,(int) (55*8),(int) (53),new int[]{(int) (6),(int) (22)},(int) (1),(int) (0),(int) (55),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 59;BA.debugLine="versions.Add(CreateVersionData(3, \"3M\", Generator";
_versions.Add((Object)(_createversiondata((int) (3),"3M",_generator26,_m0,(int) (44*8),(int) (42),new int[]{(int) (6),(int) (22)},(int) (1),(int) (0),(int) (44),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 60;BA.debugLine="versions.Add(CreateVersionData(3, \"3Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (3),"3Q",_generator18,_q0,(int) (34*8),(int) (32),new int[]{(int) (6),(int) (22)},(int) (2),(int) (0),(int) (17),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 61;BA.debugLine="versions.Add(CreateVersionData(3, \"3H\", Generator";
_versions.Add((Object)(_createversiondata((int) (3),"3H",_generator22,_h0,(int) (26*8),(int) (24),new int[]{(int) (6),(int) (22)},(int) (2),(int) (0),(int) (13),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 63;BA.debugLine="versions.Add(CreateVersionData(4, \"4L\", Generator";
_versions.Add((Object)(_createversiondata((int) (4),"4L",_generator20,_l0,(int) (80*8),(int) (78),new int[]{(int) (6),(int) (26)},(int) (1),(int) (0),(int) (80),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 64;BA.debugLine="versions.Add(CreateVersionData(4, \"4M\", Generator";
_versions.Add((Object)(_createversiondata((int) (4),"4M",_generator18,_m0,(int) (64*8),(int) (62),new int[]{(int) (6),(int) (26)},(int) (2),(int) (0),(int) (32),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 65;BA.debugLine="versions.Add(CreateVersionData(4, \"4Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (4),"4Q",_generator26,_q0,(int) (48*8),(int) (46),new int[]{(int) (6),(int) (26)},(int) (2),(int) (0),(int) (24),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 66;BA.debugLine="versions.Add(CreateVersionData(4, \"4H\", Generator";
_versions.Add((Object)(_createversiondata((int) (4),"4H",_generator16,_h0,(int) (36*8),(int) (34),new int[]{(int) (6),(int) (26)},(int) (4),(int) (0),(int) (9),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 68;BA.debugLine="versions.Add(CreateVersionData(5, \"5L\", Generator";
_versions.Add((Object)(_createversiondata((int) (5),"5L",_generator26,_l0,(int) (108*8),(int) (106),new int[]{(int) (6),(int) (30)},(int) (1),(int) (0),(int) (108),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 69;BA.debugLine="versions.Add(CreateVersionData(5, \"5M\", Generator";
_versions.Add((Object)(_createversiondata((int) (5),"5M",_generator24,_m0,(int) (86*8),(int) (84),new int[]{(int) (6),(int) (30)},(int) (2),(int) (0),(int) (43),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 70;BA.debugLine="versions.Add(CreateVersionData(5, \"5Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (5),"5Q",_generator18,_q0,(int) (62*8),(int) (60),new int[]{(int) (6),(int) (30)},(int) (2),(int) (2),(int) (15),(int) (16),(byte[])(__c.Null))));
 //BA.debugLineNum = 71;BA.debugLine="versions.Add(CreateVersionData(5, \"5H\", Generator";
_versions.Add((Object)(_createversiondata((int) (5),"5H",_generator22,_h0,(int) (46*8),(int) (44),new int[]{(int) (6),(int) (30)},(int) (2),(int) (2),(int) (11),(int) (12),(byte[])(__c.Null))));
 //BA.debugLineNum = 73;BA.debugLine="versions.Add(CreateVersionData(6, \"6L\", Generator";
_versions.Add((Object)(_createversiondata((int) (6),"6L",_generator18,_l0,(int) (136*8),(int) (134),new int[]{(int) (6),(int) (34)},(int) (2),(int) (0),(int) (68),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 74;BA.debugLine="versions.Add(CreateVersionData(6, \"6M\", Generator";
_versions.Add((Object)(_createversiondata((int) (6),"6M",_generator16,_m0,(int) (108*8),(int) (106),new int[]{(int) (6),(int) (34)},(int) (4),(int) (0),(int) (27),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 75;BA.debugLine="versions.Add(CreateVersionData(6, \"6Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (6),"6Q",_generator24,_q0,(int) (76*8),(int) (74),new int[]{(int) (6),(int) (34)},(int) (4),(int) (0),(int) (19),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 76;BA.debugLine="versions.Add(CreateVersionData(6, \"6H\", Generator";
_versions.Add((Object)(_createversiondata((int) (6),"6H",_generator28,_h0,(int) (60*8),(int) (58),new int[]{(int) (6),(int) (34)},(int) (4),(int) (0),(int) (15),(int) (0),(byte[])(__c.Null))));
 //BA.debugLineNum = 78;BA.debugLine="versions.Add(CreateVersionData(7, \"7L\", Generator";
_versions.Add((Object)(_createversiondata((int) (7),"7L",_generator20,_l0,(int) (156*8),(int) (154),new int[]{(int) (6),(int) (22),(int) (38)},(int) (2),(int) (0),(int) (78),(int) (0),new byte[]{(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 79;BA.debugLine="versions.Add(CreateVersionData(7, \"7M\", Generator";
_versions.Add((Object)(_createversiondata((int) (7),"7M",_generator18,_m0,(int) (124*8),(int) (122),new int[]{(int) (6),(int) (22),(int) (38)},(int) (4),(int) (0),(int) (31),(int) (0),new byte[]{(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 80;BA.debugLine="versions.Add(CreateVersionData(7, \"7Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (7),"7Q",_generator18,_q0,(int) (88*8),(int) (86),new int[]{(int) (6),(int) (22),(int) (38)},(int) (2),(int) (4),(int) (14),(int) (15),new byte[]{(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 81;BA.debugLine="versions.Add(CreateVersionData(7, \"7H\", Generator";
_versions.Add((Object)(_createversiondata((int) (7),"7H",_generator26,_h0,(int) (66*8),(int) (64),new int[]{(int) (6),(int) (22),(int) (38)},(int) (4),(int) (1),(int) (13),(int) (14),new byte[]{(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 83;BA.debugLine="versions.Add(CreateVersionData(8, \"8L\", Generator";
_versions.Add((Object)(_createversiondata((int) (8),"8L",_generator24,_l0,(int) (194*8),(int) (192),new int[]{(int) (6),(int) (24),(int) (42)},(int) (2),(int) (0),(int) (97),(int) (0),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 84;BA.debugLine="versions.Add(CreateVersionData(8, \"8M\", Generator";
_versions.Add((Object)(_createversiondata((int) (8),"8M",_generator22,_m0,(int) (154*8),(int) (152),new int[]{(int) (6),(int) (24),(int) (42)},(int) (2),(int) (2),(int) (38),(int) (39),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 85;BA.debugLine="versions.Add(CreateVersionData(8, \"8Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (8),"8Q",_generator22,_q0,(int) (110*8),(int) (108),new int[]{(int) (6),(int) (24),(int) (42)},(int) (4),(int) (2),(int) (18),(int) (19),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 86;BA.debugLine="versions.Add(CreateVersionData(8, \"8H\", Generator";
_versions.Add((Object)(_createversiondata((int) (8),"8H",_generator26,_h0,(int) (86*8),(int) (84),new int[]{(int) (6),(int) (24),(int) (42)},(int) (4),(int) (2),(int) (14),(int) (15),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 88;BA.debugLine="versions.Add(CreateVersionData(9, \"9L\", Generator";
_versions.Add((Object)(_createversiondata((int) (9),"9L",_generator30,_l0,(int) (232*8),(int) (230),new int[]{(int) (6),(int) (26),(int) (46)},(int) (2),(int) (0),(int) (116),(int) (0),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1)})));
 //BA.debugLineNum = 89;BA.debugLine="versions.Add(CreateVersionData(9, \"9M\", Generator";
_versions.Add((Object)(_createversiondata((int) (9),"9M",_generator22,_m0,(int) (182*8),(int) (180),new int[]{(int) (6),(int) (26),(int) (46)},(int) (3),(int) (2),(int) (36),(int) (37),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1)})));
 //BA.debugLineNum = 90;BA.debugLine="versions.Add(CreateVersionData(9, \"9Q\", Generator";
_versions.Add((Object)(_createversiondata((int) (9),"9Q",_generator20,_q0,(int) (132*8),(int) (130),new int[]{(int) (6),(int) (26),(int) (46)},(int) (4),(int) (4),(int) (16),(int) (17),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1)})));
 //BA.debugLineNum = 91;BA.debugLine="versions.Add(CreateVersionData(9, \"9H\", Generator";
_versions.Add((Object)(_createversiondata((int) (9),"9H",_generator24,_h0,(int) (100*8),(int) (98),new int[]{(int) (6),(int) (26),(int) (46)},(int) (4),(int) (4),(int) (12),(int) (13),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (1)})));
 //BA.debugLineNum = 93;BA.debugLine="versions.Add(CreateVersionData(10, \"10L\", Generat";
_versions.Add((Object)(_createversiondata((int) (10),"10L",_generator18,_l0,(int) (274*8),(int) (271),new int[]{(int) (6),(int) (28),(int) (50)},(int) (2),(int) (2),(int) (68),(int) (69),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1)})));
 //BA.debugLineNum = 94;BA.debugLine="versions.Add(CreateVersionData(10, \"10M\", Generat";
_versions.Add((Object)(_createversiondata((int) (10),"10M",_generator26,_m0,(int) (216*8),(int) (213),new int[]{(int) (6),(int) (28),(int) (50)},(int) (4),(int) (1),(int) (43),(int) (44),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1)})));
 //BA.debugLineNum = 95;BA.debugLine="versions.Add(CreateVersionData(10, \"10Q\", Generat";
_versions.Add((Object)(_createversiondata((int) (10),"10Q",_generator24,_q0,(int) (154*8),(int) (151),new int[]{(int) (6),(int) (28),(int) (50)},(int) (6),(int) (2),(int) (19),(int) (20),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1)})));
 //BA.debugLineNum = 96;BA.debugLine="versions.Add(CreateVersionData(10, \"10H\", Generat";
_versions.Add((Object)(_createversiondata((int) (10),"10H",_generator28,_h0,(int) (122*8),(int) (119),new int[]{(int) (6),(int) (28),(int) (50)},(int) (6),(int) (2),(int) (15),(int) (16),new byte[]{(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1),(byte) (1)})));
 //BA.debugLineNum = 98;BA.debugLine="versions.Add(CreateVersionData(23, \"23H\", Generat";
_versions.Add((Object)(_createversiondata((int) (23),"23H",_generator30,_h0,(int) (464*8),(int) (461),new int[]{(int) (6),(int) (30),(int) (54),(int) (78),(int) (102)},(int) (16),(int) (14),(int) (15),(int) (16),new byte[]{(byte) (0),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0)})));
 //BA.debugLineNum = 101;BA.debugLine="versions.Add(CreateVersionData(40, \"40H\", Generat";
_versions.Add((Object)(_createversiondata((int) (40),"40H",_generator30,_h0,(int) (1276*8),(int) (1273),new int[]{(int) (6),(int) (30),(int) (58),(int) (86),(int) (114),(int) (142),(int) (170)},(int) (20),(int) (61),(int) (15),(int) (16),new byte[]{(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1)})));
 //BA.debugLineNum = 104;BA.debugLine="versions.Add(CreateVersionData(40, \"40L\", Generat";
_versions.Add((Object)(_createversiondata((int) (40),"40L",_generator30,_l0,(int) (2956*8),(int) (2953),new int[]{(int) (6),(int) (30),(int) (58),(int) (86),(int) (114),(int) (142),(int) (170)},(int) (19),(int) (6),(int) (118),(int) (119),new byte[]{(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (0),(byte) (0),(byte) (1),(byte) (1),(byte) (0),(byte) (1),(byte) (0),(byte) (0),(byte) (1)})));
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public String  _intarraycopy(int[] _src,int _srcoffset,int[] _dest,int _destoffset,int _count) throws Exception{
int _i = 0;
 //BA.debugLineNum = 499;BA.debugLine="Private Sub IntArrayCopy(Src() As Int, SrcOffset A";
 //BA.debugLineNum = 500;BA.debugLine="For i = 0 To Count - 1";
{
final int step1 = 1;
final int limit1 = (int) (_count-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 501;BA.debugLine="Dest(DestOffset + i) = Src(SrcOffset + i)";
_dest[(int) (_destoffset+_i)] = _src[(int) (_srcoffset+_i)];
 }
};
 //BA.debugLineNum = 503;BA.debugLine="End Sub";
return "";
}
public byte[]  _intto16bits(int _value) throws Exception{
int _i = 0;
int _x = 0;
int _ii = 0;
 //BA.debugLineNum = 352;BA.debugLine="Private Sub IntTo16Bits (Value As Int) As Byte()";
 //BA.debugLineNum = 353;BA.debugLine="TempBB.Clear";
_tempbb._clear /*String*/ ();
 //BA.debugLineNum = 354;BA.debugLine="For i = 15 To 0 Step - 1";
{
final int step2 = -1;
final int limit2 = (int) (0);
_i = (int) (15) ;
for (;_i >= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 355;BA.debugLine="Dim x As Int = Bit.ShiftLeft(1, i)";
_x = __c.Bit.ShiftLeft((int) (1),_i);
 //BA.debugLineNum = 356;BA.debugLine="Dim ii As Int = Bit.And(Value, x)";
_ii = __c.Bit.And(_value,_x);
 //BA.debugLineNum = 357;BA.debugLine="If ii <> 0 Then";
if (_ii!=0) { 
 //BA.debugLineNum = 358;BA.debugLine="TempBB.Append(Array As Byte(1))";
_tempbb._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (1)});
 }else {
 //BA.debugLineNum = 360;BA.debugLine="TempBB.Append(Array As Byte(0))";
_tempbb._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (0)});
 };
 }
};
 //BA.debugLineNum = 364;BA.debugLine="Return TempBB.ToArray";
if (true) return _tempbb._toarray /*byte[]*/ ();
 //BA.debugLineNum = 365;BA.debugLine="End Sub";
return null;
}
public int[]  _polyaddorsubtract(int[] _this,int[] _other) throws Exception{
int[] _small = null;
int[] _large = null;
int[] _temp = null;
int[] _sumdiff = null;
int _lengthdiff = 0;
int _i = 0;
 //BA.debugLineNum = 480;BA.debugLine="Private Sub PolyAddOrSubtract(This() As Int, Other";
 //BA.debugLineNum = 481;BA.debugLine="If This(0) = 0 Then Return Other";
if (_this[(int) (0)]==0) { 
if (true) return _other;};
 //BA.debugLineNum = 482;BA.debugLine="If Other(0) = 0 Then Return This";
if (_other[(int) (0)]==0) { 
if (true) return _this;};
 //BA.debugLineNum = 483;BA.debugLine="Dim Small() As Int = This";
_small = _this;
 //BA.debugLineNum = 484;BA.debugLine="Dim Large() As Int = Other";
_large = _other;
 //BA.debugLineNum = 485;BA.debugLine="If Small.Length > Large.Length Then";
if (_small.length>_large.length) { 
 //BA.debugLineNum = 486;BA.debugLine="Dim temp() As Int = Small";
_temp = _small;
 //BA.debugLineNum = 487;BA.debugLine="Small = Large";
_small = _large;
 //BA.debugLineNum = 488;BA.debugLine="Large = temp";
_large = _temp;
 };
 //BA.debugLineNum = 490;BA.debugLine="Dim SumDiff(Large.Length) As Int";
_sumdiff = new int[_large.length];
;
 //BA.debugLineNum = 491;BA.debugLine="Dim LengthDiff As Int = Large.Length - Small.Leng";
_lengthdiff = (int) (_large.length-_small.length);
 //BA.debugLineNum = 492;BA.debugLine="IntArrayCopy(Large, 0, SumDiff, 0, LengthDiff)";
_intarraycopy(_large,(int) (0),_sumdiff,(int) (0),_lengthdiff);
 //BA.debugLineNum = 493;BA.debugLine="For i = LengthDiff To Large.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_large.length-1);
_i = _lengthdiff ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 494;BA.debugLine="SumDiff(i) = Bit.Xor(Small(i - LengthDiff), Larg";
_sumdiff[_i] = __c.Bit.Xor(_small[(int) (_i-_lengthdiff)],_large[_i]);
 }
};
 //BA.debugLineNum = 496;BA.debugLine="Return CreateGFPoly(SumDiff)";
if (true) return _creategfpoly(_sumdiff);
 //BA.debugLineNum = 497;BA.debugLine="End Sub";
return null;
}
public int[]  _polydivide(int[] _this,int[] _other) throws Exception{
int[] _quotient = null;
int[] _remainder = null;
int _denominatorleadingterm = 0;
int _inversedenominatorleadingterm = 0;
int _degreedifference = 0;
int _scale = 0;
int[] _term = null;
int[] _iterationquotient = null;
 //BA.debugLineNum = 516;BA.debugLine="Private Sub PolyDivide (This() As Int, Other() As";
 //BA.debugLineNum = 517;BA.debugLine="Dim quotient() As Int = PolyZero";
_quotient = _polyzero;
 //BA.debugLineNum = 518;BA.debugLine="Dim remainder() As Int = This";
_remainder = _this;
 //BA.debugLineNum = 519;BA.debugLine="Dim denominatorLeadingTerm As Int = Other(0)";
_denominatorleadingterm = _other[(int) (0)];
 //BA.debugLineNum = 520;BA.debugLine="Dim inverseDenominatorLeadingTerm As Int = GFInve";
_inversedenominatorleadingterm = _gfinverse(_denominatorleadingterm);
 //BA.debugLineNum = 521;BA.debugLine="Do While remainder.Length >= Other.Length And rem";
while (_remainder.length>=_other.length && _remainder[(int) (0)]!=0) {
 //BA.debugLineNum = 522;BA.debugLine="Dim DegreeDifference As Int = remainder.Length -";
_degreedifference = (int) (_remainder.length-_other.length);
 //BA.debugLineNum = 523;BA.debugLine="Dim scale As Int = GFMultiply(remainder(0), inve";
_scale = _gfmultiply(_remainder[(int) (0)],_inversedenominatorleadingterm);
 //BA.debugLineNum = 524;BA.debugLine="Dim term() As Int = PolyMultiplyByMonomial(Other";
_term = _polymultiplybymonomial(_other,_degreedifference,_scale);
 //BA.debugLineNum = 525;BA.debugLine="Dim iterationQuotient() As Int = GFBuildMonomial";
_iterationquotient = _gfbuildmonomial(_degreedifference,_scale);
 //BA.debugLineNum = 526;BA.debugLine="quotient = PolyAddOrSubtract(quotient, iteration";
_quotient = _polyaddorsubtract(_quotient,_iterationquotient);
 //BA.debugLineNum = 527;BA.debugLine="remainder = PolyAddOrSubtract(remainder, term)";
_remainder = _polyaddorsubtract(_remainder,_term);
 }
;
 //BA.debugLineNum = 529;BA.debugLine="Return remainder";
if (true) return _remainder;
 //BA.debugLineNum = 530;BA.debugLine="End Sub";
return null;
}
public int[]  _polymultiplybymonomial(int[] _this,int _degree,int _coefficient) throws Exception{
int[] _product = null;
int _i = 0;
 //BA.debugLineNum = 507;BA.debugLine="Private Sub PolyMultiplyByMonomial (This() As Int,";
 //BA.debugLineNum = 508;BA.debugLine="If Coefficient = 0 Then Return PolyZero";
if (_coefficient==0) { 
if (true) return _polyzero;};
 //BA.debugLineNum = 509;BA.debugLine="Dim product(This.Length + Degree) As Int";
_product = new int[(int) (_this.length+_degree)];
;
 //BA.debugLineNum = 510;BA.debugLine="For i = 0 To This.Length - 1";
{
final int step3 = 1;
final int limit3 = (int) (_this.length-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 511;BA.debugLine="product(i) = GFMultiply(This(i), Coefficient)";
_product[_i] = _gfmultiply(_this[_i],_coefficient);
 }
};
 //BA.debugLineNum = 513;BA.debugLine="Return CreateGFPoly(product)";
if (true) return _creategfpoly(_product);
 //BA.debugLineNum = 514;BA.debugLine="End Sub";
return null;
}
public String  _preparetables() throws Exception{
int _x = 0;
int _primitive = 0;
int _i = 0;
 //BA.debugLineNum = 448;BA.debugLine="Private Sub PrepareTables";
 //BA.debugLineNum = 449;BA.debugLine="Dim x = 1 As Int";
_x = (int) (1);
 //BA.debugLineNum = 450;BA.debugLine="Dim Primitive As Int = 285";
_primitive = (int) (285);
 //BA.debugLineNum = 451;BA.debugLine="For i = 0 To GFSize - 1";
{
final int step3 = 1;
final int limit3 = (int) (_gfsize-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 452;BA.debugLine="ExpTable(i) = x";
_exptable[_i] = _x;
 //BA.debugLineNum = 453;BA.debugLine="x = x * 2";
_x = (int) (_x*2);
 //BA.debugLineNum = 454;BA.debugLine="If x >= GFSize Then";
if (_x>=_gfsize) { 
 //BA.debugLineNum = 455;BA.debugLine="x = Bit.Xor(Primitive, x)";
_x = __c.Bit.Xor(_primitive,_x);
 //BA.debugLineNum = 456;BA.debugLine="x = Bit.And(GFSize - 1, x)";
_x = __c.Bit.And((int) (_gfsize-1),_x);
 };
 }
};
 //BA.debugLineNum = 459;BA.debugLine="For i = 0 To GFSize - 2";
{
final int step11 = 1;
final int limit11 = (int) (_gfsize-2);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 460;BA.debugLine="LogTable(ExpTable(i)) = i";
_logtable[_exptable[_i]] = _i;
 }
};
 //BA.debugLineNum = 462;BA.debugLine="End Sub";
return "";
}
public byte[]  _unsignedbytetobits(int _value) throws Exception{
int _i = 0;
int _x = 0;
int _ii = 0;
 //BA.debugLineNum = 337;BA.debugLine="Private Sub UnsignedByteToBits (Value As Int) As B";
 //BA.debugLineNum = 338;BA.debugLine="TempBB.Clear";
_tempbb._clear /*String*/ ();
 //BA.debugLineNum = 339;BA.debugLine="For i = 7 To 0 Step - 1";
{
final int step2 = -1;
final int limit2 = (int) (0);
_i = (int) (7) ;
for (;_i >= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 340;BA.debugLine="Dim x As Int = Bit.ShiftLeft(1, i)";
_x = __c.Bit.ShiftLeft((int) (1),_i);
 //BA.debugLineNum = 341;BA.debugLine="Dim ii As Int = Bit.And(Value, x)";
_ii = __c.Bit.And(_value,_x);
 //BA.debugLineNum = 342;BA.debugLine="If ii <> 0 Then";
if (_ii!=0) { 
 //BA.debugLineNum = 343;BA.debugLine="TempBB.Append(Array As Byte(1))";
_tempbb._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (1)});
 }else {
 //BA.debugLineNum = 345;BA.debugLine="TempBB.Append(Array As Byte(0))";
_tempbb._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (new byte[]{(byte) (0)});
 };
 }
};
 //BA.debugLineNum = 349;BA.debugLine="Return TempBB.ToArray";
if (true) return _tempbb._toarray /*byte[]*/ ();
 //BA.debugLineNum = 350;BA.debugLine="End Sub";
return null;
}
public String  _versionwithtwogroups(int[] _generator,int _group1size,int _group2size,int _block1size,int _block2size,ir.saelozahra.snd1400.b4xbytesbuilder _encodeddata) throws Exception{
anywheresoftware.b4a.objects.collections.List _ecs = null;
anywheresoftware.b4a.objects.collections.List _datablocks = null;
int _previndex = 0;
int _block1 = 0;
int _blocksize = 0;
byte[] _data = null;
int[] _dataasints = null;
int _i = 0;
int[] _ec = null;
int[] _ec2 = null;
ir.saelozahra.snd1400.b4xbytesbuilder _interleaved = null;
int[] _ii = null;
 //BA.debugLineNum = 192;BA.debugLine="Private Sub VersionWithTwoGroups (generator() As I";
 //BA.debugLineNum = 193;BA.debugLine="Dim ecs As List";
_ecs = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 194;BA.debugLine="ecs.Initialize";
_ecs.Initialize();
 //BA.debugLineNum = 195;BA.debugLine="Dim dataBlocks As List";
_datablocks = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 196;BA.debugLine="dataBlocks.Initialize";
_datablocks.Initialize();
 //BA.debugLineNum = 197;BA.debugLine="Dim PrevIndex As Int";
_previndex = 0;
 //BA.debugLineNum = 198;BA.debugLine="For block1 = 0 To Group1Size + Group2Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_group1size+_group2size-1);
_block1 = (int) (0) ;
for (;_block1 <= limit6 ;_block1 = _block1 + step6 ) {
 //BA.debugLineNum = 199;BA.debugLine="Dim BlockSize As Int";
_blocksize = 0;
 //BA.debugLineNum = 200;BA.debugLine="If block1 < Group1Size Then BlockSize = Block1Si";
if (_block1<_group1size) { 
_blocksize = _block1size;}
else {
_blocksize = _block2size;};
 //BA.debugLineNum = 201;BA.debugLine="Dim Data() As Byte = EncodedData.SubArray2(PrevI";
_data = _encodeddata._subarray2 /*byte[]*/ ((int) (_previndex*8),(int) ((_previndex+_blocksize)*8));
 //BA.debugLineNum = 202;BA.debugLine="PrevIndex = PrevIndex + BlockSize";
_previndex = (int) (_previndex+_blocksize);
 //BA.debugLineNum = 203;BA.debugLine="Dim DataAsInts(Data.Length / 8) As Int";
_dataasints = new int[(int) (_data.length/(double)8)];
;
 //BA.debugLineNum = 204;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 205;BA.debugLine="For i = 0 To Data.Length - 1 Step 8";
{
final int step13 = 8;
final int limit13 = (int) (_data.length-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 206;BA.debugLine="DataAsInts(i / 8) = BitsToUnsignedByte(Data, i)";
_dataasints[(int) (_i/(double)8)] = _bitstounsignedbyte(_data,_i);
 }
};
 //BA.debugLineNum = 208;BA.debugLine="dataBlocks.Add(DataAsInts)";
_datablocks.Add((Object)(_dataasints));
 //BA.debugLineNum = 209;BA.debugLine="Dim ec() As Int = CalcReedSolomon(DataAsInts, ge";
_ec = _calcreedsolomon(_dataasints,_generator);
 //BA.debugLineNum = 210;BA.debugLine="If ec.Length < generator.Length - 1 Then";
if (_ec.length<_generator.length-1) { 
 //BA.debugLineNum = 211;BA.debugLine="Dim ec2(generator.Length - 1) As Int";
_ec2 = new int[(int) (_generator.length-1)];
;
 //BA.debugLineNum = 212;BA.debugLine="IntArrayCopy(ec, 0, ec2,  generator.Length - 1";
_intarraycopy(_ec,(int) (0),_ec2,(int) (_generator.length-1-_ec.length),_ec.length);
 //BA.debugLineNum = 213;BA.debugLine="ec = ec2";
_ec = _ec2;
 };
 //BA.debugLineNum = 215;BA.debugLine="ecs.Add(ec)";
_ecs.Add((Object)(_ec));
 }
};
 //BA.debugLineNum = 217;BA.debugLine="Dim Interleaved As B4XBytesBuilder";
_interleaved = new ir.saelozahra.snd1400.b4xbytesbuilder();
 //BA.debugLineNum = 218;BA.debugLine="Interleaved.Initialize";
_interleaved._initialize /*String*/ (ba);
 //BA.debugLineNum = 219;BA.debugLine="For i = 0 To Max(Block1Size, Block2Size) - 1";
{
final int step27 = 1;
final int limit27 = (int) (__c.Max(_block1size,_block2size)-1);
_i = (int) (0) ;
for (;_i <= limit27 ;_i = _i + step27 ) {
 //BA.debugLineNum = 220;BA.debugLine="For block1 = 0 To dataBlocks.Size - 1";
{
final int step28 = 1;
final int limit28 = (int) (_datablocks.getSize()-1);
_block1 = (int) (0) ;
for (;_block1 <= limit28 ;_block1 = _block1 + step28 ) {
 //BA.debugLineNum = 221;BA.debugLine="Dim ii() As Int = dataBlocks.Get(block1)";
_ii = (int[])(_datablocks.Get(_block1));
 //BA.debugLineNum = 222;BA.debugLine="If ii.Length > i Then";
if (_ii.length>_i) { 
 //BA.debugLineNum = 223;BA.debugLine="Interleaved.Append(UnsignedByteToBits(ii(i)))";
_interleaved._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_unsignedbytetobits(_ii[_i]));
 };
 }
};
 }
};
 //BA.debugLineNum = 227;BA.debugLine="For i = 0 To generator.Length - 2";
{
final int step35 = 1;
final int limit35 = (int) (_generator.length-2);
_i = (int) (0) ;
for (;_i <= limit35 ;_i = _i + step35 ) {
 //BA.debugLineNum = 228;BA.debugLine="For block1 = 0 To dataBlocks.Size - 1";
{
final int step36 = 1;
final int limit36 = (int) (_datablocks.getSize()-1);
_block1 = (int) (0) ;
for (;_block1 <= limit36 ;_block1 = _block1 + step36 ) {
 //BA.debugLineNum = 229;BA.debugLine="Dim ii() As Int = ecs.Get(block1)";
_ii = (int[])(_ecs.Get(_block1));
 //BA.debugLineNum = 230;BA.debugLine="Interleaved.Append(UnsignedByteToBits(ii(i)))";
_interleaved._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_unsignedbytetobits(_ii[_i]));
 }
};
 }
};
 //BA.debugLineNum = 233;BA.debugLine="EncodedData.Clear";
_encodeddata._clear /*String*/ ();
 //BA.debugLineNum = 234;BA.debugLine="EncodedData.Append(Interleaved.ToArray)";
_encodeddata._append /*ir.saelozahra.snd1400.b4xbytesbuilder*/ (_interleaved._toarray /*byte[]*/ ());
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
