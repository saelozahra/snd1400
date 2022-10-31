package ir.saelozahra.banooyar.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_maplayout{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("markeriv").vw.setLeft((int)((43d / 100 * width)));
views.get("markeriv").vw.setWidth((int)((57d / 100 * width) - ((43d / 100 * width))));
views.get("markeriv").vw.setTop((int)((37.5d / 100 * height)));
views.get("markeriv").vw.setHeight((int)((50.5d / 100 * height) - ((37.5d / 100 * height))));

}
}