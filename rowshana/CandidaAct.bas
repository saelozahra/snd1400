B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.5
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
	#Extends: android.support.v7.app.AppCompatActivity
#End Region

Sub Process_Globals
	Dim CandidateID As String
End Sub

Sub Globals
	Private CF As Hitex_FlexibleSpace
	Private XML As XmlLayoutBuilder
	Dim Config 	As Amir_SliderConfig
	Dim Show 	As Amir_SliderShow
	Dim ToolBar As Hitex_Toolbar
	Dim ImageJob As HttpJob
	Dim CandidateJob As HttpJob
	
	Dim tel As String
	Dim TextLbl As Label
	Private TextPanel,ImagesSliderPanel,PropertyPanel As Panel
	Private CategoryLbl,TagLbl As Label
	Private P1Lbl,P2Lbl,P3Lbl,P4Lbl,P5Lbl,P6Lbl As Label
	Dim PicList As List
	Dim PropertyNumber As Int
	Dim SliderWV As WebView
End Sub


Sub Activity_Create(FirstTime As Boolean)
	
	CF.Initialize("CF")
	Activity.AddView(CF,0,0,-1,-1)
	
	CF.Panel.LoadLayout("CandidatePanelLayout")
	Activity.Padding = Array As Int (0, 30dip, 0, 0)
	
	CF.CardBackgroundColor=Colors.Transparent
	CF.ImageBitmap = LoadBitmap(File.DirAssets,"box.jpg")
	CF.ImageScaleType = CF.SCALE_CENTER_CROP
	CF.Icon = XML.GetDrawable("twotone_perm_phone_msg_white_24")
	CF.SetToolbarColor(SaeloZahra.ColorDark,SaeloZahra.ColorLightTransparent)
	CF.SetFabColor(SaeloZahra.ColorAccent,SaeloZahra.ColorLightTransparent)
	CF.Title="     "&Application.LabelName
	
	
	Dim T As Hitex_Toolbar = CF.ToolbarDark
	
	ToolBar.Initialize("Toolbar")
	Dim XML As XmlLayoutBuilder
	Dim Jo = T.Parent As JavaObject
	T.RemoveView
	Jo.RunMethod("addView",Array(ToolBar))
	ToolBar.Height = 56dip
	ToolBar.SetSupportActionBar
	ToolBar.Title = "     "&Application.LabelName
	ToolBar.Color = Colors.Transparent
	ToolBar.NavigationIcon = XML.GetDrawable("round_arrow_back_white_24")
	
	PicList.Initialize
	ImageJob.Initialize("ImageJob",Me)
	CandidateJob.Initialize("CandidateJob",Me)
	CandidateJob.Download(SaeloZahra.JsonUrl&"product/index/"&CandidateID)
	
	
	CategoryLbl.Typeface = SaeloZahra.fontBold
	TagLbl.SetLayout((CategoryLbl.Left+CategoryLbl.Width+5dip),TagLbl.top,TagLbl.Width,TagLbl.Height) : TagLbl.Color=SaeloZahra.ColorAccent : TagLbl.Typeface=SaeloZahra.Font
	TextLbl.Typeface = SaeloZahra.font
	TextLbl.Gravity = Gravity.RIGHT
	
	
	If SaeloZahra.P.SdkVersion>23 Then
		Config.Initialize
		Config.position(Config.POSITION_LEFT)
		Config.primaryColor(SaeloZahra.ColorDark)
		Config.edge(True)
		Config.sensitivity(100)
		Config.scrimColor(Colors.ARGB(180,0,0,0))
		Show.convertActivityToTranslucent
		Show.attachActivity(Config)
	End If
	
	TextPanel.SetLayout(3%x,TextPanel.Top,83%x,TextPanel.Height)
	TextLbl.SetLayout(1%x,TextLbl.Top,78%x,TextLbl.Height)
	PropertyPanel.SetLayout(3%x,PropertyPanel.Top,83%x,PropertyPanel.Height)
	ImagesSliderPanel.SetLayout(3%x,ImagesSliderPanel.Top,83%x,ImagesSliderPanel.Height)
	AddImageSlider(ImagesSliderPanel)

End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


#Region LVProductSlider


Sub AddImageSlider(ViewParent As Panel)
	
	ViewParent.Visible=False
	
	SliderWV.Initialize("SliderWV")
	SliderWV.Color=Colors.Transparent
	SliderWV.ZoomEnabled=False
	
	ViewParent.AddView(SliderWV,0,0,ViewParent.Width,ViewParent.Height)
	
End Sub


#End Region



Sub jobDone(J As HttpJob)
	Log(j.JobName&" | "&j.Success)
	If J.Success Then
		Select j.JobName
			Case "CandidateJob"
				
				Dim parser As JSONParser
				parser.Initialize(j.GetString)
				Dim root As List = parser.NextArray
				For Each colroot As Map In root
					PicList = colroot.Get("pic")
					ImageJob.Download(PicList.Get(0))
					ImagesSliderPanel.SetVisibleAnimated(313,True)
	
					Dim SliderHTML As String = File.ReadString(File.DirAssets,"slider_1.html")
					For Each PicUrl As String In PicList
						SliderHTML = SliderHTML & " <div class='item'><img src='"&PicUrl&"'></div>"
					Next
					SliderHTML = SliderHTML & File.ReadString(File.DirAssets,"slider_2.html") & "<style>#owl-demo .item img{width: 100%; height: 100%; top: 0; left: 0; max-width:none; max-height:none; object-fit:cover; } .owl-buttons {top: 27%;} </style>"
					SliderWV.LoadHtml(SliderHTML)
					ImagesSliderPanel.Visible=True
					
					CF.Title=SaeloZahra.CSBTitle("     "&colroot.Get("title"))
'					Dim date_diff As String = colroot.Get("date_diff")

					tel 		= colroot.Get("tel")
'					Dim state As String = colroot.Get("state")
'					Dim publisher As String = colroot.Get("publisher")
					Dim cat_title As String = colroot.Get("cat_title")
					CategoryLbl.Text = cat_title
					Dim viewcount As String = colroot.Get("viewcount")
					Dim CSBText As CSBuilder
					CSBText.Initialize.Append(colroot.Get("text")).Append(CRLF).Size(10).Color(Colors.DarkGray).Append("تعداد بازدید: ").Bold.Append(viewcount).PopAll
					TextLbl.Text= CSBText
					Dim city As String = colroot.Get("city")
					TagLbl.Text = city
'					Dim email As String = colroot.Get("email")
'					Dim proposal As String = colroot.Get("proposal")
'					Dim priceinfo As String = colroot.Get("priceinfo")
'					Dim date As String = colroot.Get("date")
'					Dim thumbnails As List = colroot.Get("thumbnails")
'					For Each colthumbnails As String In thumbnails
'					Next
					PropertyNumber = 0
					Dim Properties As Map = colroot.Get("properties")
					Dim Property1 As Map = Properties.Get("property1")
					P1Lbl.Text=BuildPropertyItemStyle(Property1.Get("name"),Property1.Get("value"))
					P1Lbl.Width=-1
					Dim Property2 As Map = Properties.Get("property2")
					P2Lbl.Text=BuildPropertyItemStyle(Property2.Get("name"),Property2.Get("value"))
					P2Lbl.Width=-1
					Dim Property3 As Map = Properties.Get("property3")
					P3Lbl.Text=BuildPropertyItemStyle(Property3.Get("name"),Property3.Get("value"))
					P3Lbl.Width=-1
					Dim property4 As Map = Properties.Get("property4")
					P4Lbl.Text=BuildPropertyItemStyle(property4.Get("name"),property4.Get("value"))
					P4Lbl.Width=-1
					Dim property5 As Map = Properties.Get("property5")
					P5Lbl.Text=BuildPropertyItemStyle(property5.Get("name"),property5.Get("value"))
					P5Lbl.Width=-1
					Dim property6 As Map = Properties.Get("property6")
					P6Lbl.Text=BuildPropertyItemStyle(property6.Get("name"),property6.Get("value"))
					P6Lbl.Width=-1
					Responsive
					
				Next
				
				
				Responsive
				
			Case "ImageJob"
				Try
					CF.ImageBitmap = J.GetBitmap
				Catch
					CF.ImageBitmap = LoadBitmap(File.DirAssets,"box.jpg")
					Log(LastException)
				End Try
		End Select
	Else
		ToastMessageShow(SaeloZahra.CSB("خطا در بارگزاری"),True)
		ToastMessageShow(J.ErrorMessage,False)
	End If
	J.Release
	
End Sub


Sub Responsive
	
	Dim SU As StringUtils
	TextLbl.Height = SU.MeasureMultilineTextHeight(TextLbl,TextLbl.text)+5%y
	TextPanel.Height = TextLbl.Height
	TagLbl.Top = TextLbl.Height-5%y
	CategoryLbl.Top = TextLbl.Height-5%y
	ImagesSliderPanel.Top=TextPanel.top+TextPanel.Height+20dip
	PropertyPanel.Top=ImagesSliderPanel.top+ImagesSliderPanel.Height+20dip
'	PropertyPanel.Height=(PropertyNumber+1)*56dip
	CF.Panel.Height = PropertyPanel.top+PropertyPanel.Height+20dip
	
	If PropertyNumber==0 Then
		PropertyPanel.Visible=False
		CF.Panel.Height = ImagesSliderPanel.top+ImagesSliderPanel.Height+20dip
	End If
	
End Sub

Sub CF_Click
	Dim intent1 As Intent
	intent1.Initialize(intent1.ACTION_VIEW, "tel:"&tel)
	StartActivity(intent1)
End Sub
Sub CF_LongClick As Boolean
	ToastMessageShow("تماس با فروشنده",False)
	Return True
End Sub
Sub CF_NavigationOnClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub

Sub ToolBar_NavigationClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub
Sub ToolBar_NavigationOnClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean
	If KeyCode==KeyCodes.KEYCODE_BACK Then
		CF_NavigationOnClick
		Return False
	Else
		Return True
	End If
End Sub


Sub BuildPropertyItemStyle(VarName As String,VarValue As String) As CSBuilder
	If VarValue.Length>2 Then PropertyNumber = PropertyNumber+1
	Log("LVProductSlider: "&PropertyNumber&"   VarName: "&VarName&"   VarValue: "&VarValue)
	Dim CSP As CSBuilder
	CSP.Initialize.Typeface(SaeloZahra.Font).color(Colors.LightGray).Append(VarName).Append("	 	").Typeface(SaeloZahra.fontBold).Color(Colors.DarkGray).Append(VarValue).PopAll
	Return CSP
End Sub



Sub Activity_CreateOptionsMenu (Menu As Hi_Menu)

	Menu.Add2(1,1,"وب سایت یا شبکه‌های اجتماعی",XML.GetDrawable("twotone_link_white_24")).SetShowAsAction(2)
	Menu.Add2(2,2,"صحبت با کاندیدا",XML.GetDrawable("twotone_perm_phone_msg_white_24")).SetShowAsAction(2)
	Menu.Add2(2,3,"تبلیغات انتخاباتی",XML.GetDrawable("baseline_text_snippet_white_24")).SetShowAsAction(2)
	
End Sub


Sub ToolBar_MenuItemClick (Item As Hi_MenuItem)
	Log(Item.ItemId)
	Select Item.ItemId
		Case 0
		Case 1
	End Select
End Sub

#IF JAVA

  import ir.hitexroid.material.x.others.Hi_Menu;
  import android.view.Menu;

   public boolean _onCreateOptionsMenu(Menu menu) {
     if (processBA.subExists("activity_createoptionsmenu")) {
       processBA.raiseEvent2(this, true, "activity_createoptionsmenu", false, new Hi_Menu(menu));
       return true;
     } else {
       return false;
     }   
    }

 #End If
 
 
 
 
 
 