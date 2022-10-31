package ir.saelozahra.snd1400.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_homelayout{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("homemosaicbglbl").vw.setHeight((int)((views.get("homemosaicbglbl").vw.getWidth())));
//BA.debugLineNum = 4;BA.debugLine="HomeTitleLbl.Top = 8%y"[HomeLayout/General script]
views.get("hometitlelbl").vw.setTop((int)((8d / 100 * height)));
//BA.debugLineNum = 5;BA.debugLine="HomeBTN2.SetLeftAndRight(2%x,47%x)"[HomeLayout/General script]
views.get("homebtn2").vw.setLeft((int)((2d / 100 * width)));
views.get("homebtn2").vw.setWidth((int)((47d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 6;BA.debugLine="HomeBTN1.SetLeftAndRight(49%x,95%x)"[HomeLayout/General script]
views.get("homebtn1").vw.setLeft((int)((49d / 100 * width)));
views.get("homebtn1").vw.setWidth((int)((95d / 100 * width) - ((49d / 100 * width))));

}
}