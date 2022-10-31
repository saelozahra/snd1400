B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.6
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
	#IgnoreWarnings: 11
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	Dim X1 As XmlLayoutBuilder
	Dim Config 	As Amir_SliderConfig
	Dim Show 	As Amir_SliderShow
	Dim ToolBar As ACToolBarLight
	Dim ScrollView1 As ScrollView
	Dim Stopint As Int=18dip
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("SVLayout")
	
	SaeloZahra.SetToolbarStyle(ToolBar,"نمایه شناسایی شما",True,ScrollView1)
	
	Dim mi As ACMenuItem
	ToolBar.Menu.Add2(1,1,"اصلاح اطلاعات",X1.GetDrawable("baseline_text_snippet_white_24")).ShowAsAction = mi.SHOW_AS_ACTION_IF_ROOM
	
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
	
	
	
	
	For i = 0 To 2
		Dim P1 As Panel
		P1 = AddProfileBox(i,File.ReadString(SaeloZahra.Dir,"melli"),File.ReadString(SaeloZahra.Dir,"time"))
			
		ScrollView1.Panel.AddView( P1, 20dip, Stopint, Activity.Width-40dip, P1.Height )
		Stopint = Stopint+P1.Height + 24dip
		ScrollView1.Panel.Height = Stopint +22dip
	Next
	
	ScrollView1.Panel.Height=Stopint+22dip
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub AddProfileBox(Index As String, Text As String, Time As String) As Panel
	
'	Dim cdPanel As ColorDrawable
'	cdPanel.Initialize(Colors.White,18)
	
	Dim cdPanel As GradientDrawable
	cdPanel.Initialize("TR_BL", Array As Int(Colors.White, Colors.White))
	cdPanel.CornerRadius = 12dip
	
	Dim pnl As Panel
	pnl.Initialize("")
	pnl.Tag = Index
	pnl.Background=cdPanel
	pnl.Elevation=12dip
	
	Dim cd As ColorDrawable
	cd.Initialize(SaeloZahra.ColorDark,7dip)
	
	Dim timeLbl As Label
	timeLbl.Initialize("")
	timeLbl.Background	= cd
	timeLbl.Text	 	= SaeloZahra.CSB(Time)
	timeLbl.TextSize	= 14
	timeLbl.SingleLine=True
	timeLbl.Ellipsize = "END"
	timeLbl.Gravity		= Bit.Or(Gravity.CENTER_VERTICAL, Gravity.CENTER_HORIZONTAL)
	timeLbl.textColor	= Colors.White
	pnl.AddView(timeLbl,10dip,10dip,133dip,22dip)
	
	
	
	Dim QR As QRGenerator
	QR.Initialize(ScrollView1.Width)
	
	
	Dim ImgV As ImageView
	ImgV.Initialize("")
	ImgV.Gravity = Gravity.CENTER
	ImgV.SetBackgroundImage(QR.Create(Text).Resize(Activity.Width-60dip,Activity.Width-60dip,True)).Gravity=Gravity.CENTER
	pnl.AddView(ImgV, 35dip,35dip,Activity.Width-60dip,Activity.Width-60dip)
	
	
	Dim lbl As Label
	lbl.Initialize("lbl")
	lbl.Tag  = Text
	lbl.Text = Text
	lbl.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
'	lbl.TextSize = DefaultTextSize
	lbl.TextColor = SaeloZahra.ColorDark
	lbl.Typeface = SaeloZahra.fontBold
	lbl.Gravity=Bit.Or(Gravity.CENTER_HORIZONTAL,Gravity.CENTER_VERTICAL)
	pnl.AddView(lbl, 0, Activity.Width, -1  , 60dip)
	
	
	pnl.Height = Activity.Width+60dip
	
	Return pnl
	
End Sub


Sub ToolBar_MenuItemClick (Item As ACMenuItem)
	Select Item.Id
		Case 1
			SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
			Show.convertActivityFromTranslucent
			StartActivity(ElectionAct)
	End Select
End Sub