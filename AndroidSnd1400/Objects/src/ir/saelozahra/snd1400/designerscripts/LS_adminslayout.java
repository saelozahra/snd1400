package ir.saelozahra.snd1400.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_adminslayout{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 1;BA.debugLine="AutoScaleAll"[AdminsLayout/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 2;BA.debugLine="MosaicBgLbl.Height = MosaicBgLbl.Width"[AdminsLayout/General script]
views.get("mosaicbglbl").vw.setHeight((int)((views.get("mosaicbglbl").vw.getWidth())));
//BA.debugLineNum = 3;BA.debugLine="TitleLbl.Top = 8%y"[AdminsLayout/General script]
views.get("titlelbl").vw.setTop((int)((8d / 100 * height)));

}
}