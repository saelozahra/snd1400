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
	
End Sub

Sub Globals
	Dim AC As AppCompat
	Private ToolBar As ACToolBarLight
	Dim Show As Amir_SliderShow
	Dim Config As Amir_SliderConfig
	Private LTel As Label
	Private ScrollView1 As ScrollView
	Dim IME1 As IME
	Dim Snake As Hitex_Snackbar
	
	Dim time As JK_MaterialTimePicker
	
	Private TelEt As EditText
	Private SelectLocationBtn As Button
	Private PanelLocation As Panel
	Private SaveBtn As Button
	Private LLocation As Label
	
	Private PanelMoshavere As Panel
	Private LMoshavere As Label
	Private PanelMoshavere2 As Panel
	Private MoshavereET As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	IME1.Initialize("IME1")
	IME1.AddHeightChangedEvent
	
	Activity.LoadLayout("SVLayout")
	ScrollView1.Panel.LoadLayout("ElectionLayout")
	
	SaeloZahra.SetToolbarStyle(ToolBar,"فرم مشاوره",True,ScrollView1)
	
	SetLayout
	SaeloZahra.SetStatusBarColor(0xFF493B34)
	
'	ToolBar.Menu.Add2(1,1,"اطلاعیه ها",XML.GetDrawable("twotone_link_white_24")).ShowAsAction=2
'	ToolBar.Menu.Add2(2,2,"ورود ناظران",XML.GetDrawable("round_login_white_24")).ShowAsAction=2
	ToolBar.Padding=Array As Int(0,1dip,0,0dip)
	ToolBar.Height = SaeloZahra.MaterialActionBarHeight
	
	time.Initialize("Time",13, 0, 14, 0, True)
	time.CancelButtonText = "انصراف"
	time.OkButtonText = "تایید"
	time.StartTitle = "از"
	time.EndTitle = "تا"
	time.Typeface = SaeloZahra.Font
	time.AmText = "ق.ظ"
	time.PmText = "ب.ظ"
	time.AccentColor = SaeloZahra.ColorDark
	
'	date.Initialize("Date", 1400, 03, 28, 1400, 04, 02) ' Dar sorati ke mikhayd tarikh feli ra daryaft konid 0 vared konid.
'	date.YearRange(1400, 1400)
'	date.CancelButtonText = "انصراف"
'	date.OkButtonText = "تایید"
'	date.StartTitle = "از"
'	date.EndTitle = "تا"
'	date.Typeface = SaeloZahra.Font
'	date.AccentColor = SaeloZahra.ColorDark
'	date.HideTabHost
	
	
	
	
	
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
	
	AC.SetElevation(SaveBtn,7dip)
	
	ScrollView1.SetLayout(0,ToolBar.Height,100%x,100%y-SaeloZahra.MaterialActionBarHeight)
	
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)
	Select Item.Id
		Case 2
	End Select
End Sub

Sub ToolBar_NavigationItemClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub


Sub IME1_HeightChanged (NewHeight As Int, OldHeight As Int)
	Log(NewHeight)
	ScrollView1.SetLayout(0,ToolBar.Height,100%x,NewHeight-SaeloZahra.MaterialActionBarHeight)
End Sub

Sub Activity_Resume
	
	If Not(SaeloZahra.CheckConnection) Then
		ToastMessageShow( SaeloZahra.CSB("حتما به اینترنت متصل شوید") ,True)
	End If
	
	LLocation.Text = SaeloZahra.CSB("موقعیت مکانی خود را برای یافتن نزدیک ترین مرکز مشاوره وارد کنید.")
	If File.Exists(SaeloZahra.dir,"address") Then
		Dim CsbAdd As CSBuilder
		CsbAdd.Initialize.Append(LLocation.Text).Append(CRLF).Size(10).Color(SaeloZahra.ColorAccent).Append(File.ReadString(SaeloZahra.dir,"address")).PopAll
		LLocation.Text = CsbAdd
	End If
		
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub SetLayout
	
	ScrollView1.Panel.Height=SaveBtn.Top+110dip
'	ToolBar.TitleTextColor=Colors.White

	LMoshavere.Typeface=SaeloZahra.fontBold
	LTel.Typeface=SaeloZahra.fontBold
	LLocation.Typeface=SaeloZahra.fontBold
	
	TelEt.Typeface=SaeloZahra.Font
	TelEt.Background=Null
	
	
	MoshavereET.Typeface=SaeloZahra.Font
	MoshavereET.Background=Null
	
	
	SelectLocationBtn.Typeface=SaeloZahra.Font
	SelectLocationBtn.Color=SaeloZahra.Color
	
	SaveBtn.Typeface=SaeloZahra.fontBold
	
	
	
	Responsive
	
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	Select KeyCode
		Case KeyCodes.KEYCODE_BACK
			ToolBar_NavigationItemClick
			Return True
		Case Else
			Return False
	End Select
End Sub


Private Sub SelectTimeBtn_Click
	time.show("time")
End Sub







Private Sub SaveBtn_Click
	Log(SelectMapACT.SelectedMakanlatlng)
	
	If SelectMapACT.SelectedMakanlatlng.Length<5 Then
		Snake.Initialize(Activity,SaeloZahra.CSB("ابتدا موقعیت مکانی را انتخاب کنید"),Snake.LENGTH_LONG)
		Snake.SetAction("SelectLocationBtn_Click",SaeloZahra.CSB("انتخاب مکان"))
		Snake.Show
		Return
	End If
	
	
	Msgbox2Async(SaeloZahra.CSB(""&CRLF&"پیشاپیش از صبوری شما متشکریم"),SaeloZahra.CSBTitle("منتظر بمانید"),SaeloZahra.CSB("با تشکر"),"","",Null,True)
		Wait For Msgbox_Result (Result As Int)
	If Result = DialogResponse.POSITIVE Then
		Activity.Finish
	End If
	
	
End Sub

Private Sub SelectLocationBtn_Click
	
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	
	Starter.rp.CheckAndRequest(Starter.rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	If Result Then StartActivity(SelectMapACT)
	
End Sub

Sub Responsive
	Sleep(313)
	ScrollView1.Panel.Height = SaveBtn.Top + SaveBtn.Height + 40dip
End Sub
