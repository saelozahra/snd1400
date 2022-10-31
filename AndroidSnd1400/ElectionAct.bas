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
	Private LMelli As Label
	Private ScrollView1 As ScrollView
	Dim IME1 As IME
	Dim XML As XmlLayoutBuilder
	Dim Snake As Hitex_Snackbar
	
	Dim time As JK_MaterialTimePicker
	
	Private SelectTimeBtn As ACButton
	Private TimeEt,TelEt,MelliET As EditText
	Private M1ET,M2ET,M3ET,M4ET,M5ET,M6ET,M7ET,M8ET,M9ET,M10ET As EditText
	Private cmelli1,cmelli2,cmelli3,cmelli4,cmelli5,cmelli6,cmelli7,cmelli8,cmelli9,cmelli10 As Label
	Private SelectLocationBtn As Button
	Private PanelLocation As Panel
	Private SaveBtn As Button
	Private LLocation As Label
	Private LDateTime As Label
	Private LHamrah As Label
	
	Private LSayar As Label
	Private SayarJavabLbl As Label
	Private SayarSwitch As ACSwitch
	Private SayerDalilLbl As Label
	Private SayarDalilSpinner As ACSpinner
	Private PanelSayar,PanelSayarDalil As Panel
	Private PanelHamrah As Panel
	Private HamrahSpinner As ACSpinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	IME1.Initialize("IME1")
	IME1.AddHeightChangedEvent
	
	Activity.LoadLayout("SVLayout")
	ScrollView1.Panel.LoadLayout("ElectionLayout")
	
	SaeloZahra.SetToolbarStyle(ToolBar,"ثبت اطلاعات فردی",True,ScrollView1)
	
	SetLayout
	SaeloZahra.SetStatusBarColor(0xFF493B34)
	
'	ToolBar.Menu.Add2(1,1,"اطلاعیه ها",XML.GetDrawable("twotone_link_white_24")).ShowAsAction=2
	ToolBar.Menu.Add2(2,2,"ورود ناظران",XML.GetDrawable("round_login_white_24")).ShowAsAction=2
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
			LoginKon
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
	
	LLocation.Text = SaeloZahra.CSB("موقعیت مکانی خود را برای پیدا کردن نزدیک ترین شعبه خلوت، وارد کنید.")
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

	LMelli.Typeface=SaeloZahra.fontBold
	LTel.Typeface=SaeloZahra.fontBold
	LDateTime.Typeface=SaeloZahra.fontBold
	LHamrah.Typeface=SaeloZahra.fontBold
	LLocation.Typeface=SaeloZahra.fontBold
	
	TelEt.Typeface=SaeloZahra.Font
	TelEt.Background=Null
	
	cmelli1.Typeface=SaeloZahra.Font
	cmelli2.Typeface=SaeloZahra.Font
	cmelli3.Typeface=SaeloZahra.Font
	cmelli4.Typeface=SaeloZahra.Font
	cmelli5.Typeface=SaeloZahra.Font
	cmelli6.Typeface=SaeloZahra.Font
	cmelli7.Typeface=SaeloZahra.Font
	cmelli8.Typeface=SaeloZahra.Font
	cmelli9.Typeface=SaeloZahra.Font
	cmelli10.Typeface=SaeloZahra.Font
	M1ET.Background=Null
	M2ET.Background=Null
	M3ET.Background=Null
	M4ET.Background=Null
	M5ET.Background=Null
	M6ET.Background=Null
	M7ET.Background=Null
	M8ET.Background=Null
	M9ET.Background=Null
	M10ET.Background=Null
	M1ET.Typeface=SaeloZahra.Font
	M2ET.Typeface=SaeloZahra.Font
	M3ET.Typeface=SaeloZahra.Font
	M4ET.Typeface=SaeloZahra.Font
	M5ET.Typeface=SaeloZahra.Font
	M6ET.Typeface=SaeloZahra.Font
	M7ET.Typeface=SaeloZahra.Font
	M8ET.Typeface=SaeloZahra.Font
	M9ET.Typeface=SaeloZahra.Font
	M10ET.Typeface=SaeloZahra.Font
	
	TimeEt.Typeface=SaeloZahra.Font
	TimeEt.Background=Null
	
'	DateET.Typeface=SaeloZahra.Font
'	DateET.Background=Null
	
	MelliET.Typeface=SaeloZahra.Font
	MelliET.Background=Null
	
	
	SelectTimeBtn.Typeface=SaeloZahra.Font
	SelectTimeBtn.ButtonColor=SaeloZahra.Color
	SelectLocationBtn.Typeface=SaeloZahra.Font
	SelectLocationBtn.Color=SaeloZahra.Color
	
	SaveBtn.Typeface=SaeloZahra.fontBold
	
	
	Dim CsbSayer As CSBuilder
	CsbSayer.Initialize.Typeface(SaeloZahra.fontBold).Size(16).Append("درخواست شعبه سیار دارید؟").Append(CRLF).Size(14).Typeface(SaeloZahra.font).Color(SaeloZahra.ColorAccent).Append("ویژه سالمندان و بیماران خاص").PopAll
	LSayar.Text = CsbSayer
	
	SayarDalilSpinner.Add2(SaeloZahra.CSB("درگیر ویروس کرونا"),XML.GetDrawable("baseline_coronavirus_black_36dp"))
	SayarDalilSpinner.Add2(SaeloZahra.CSB("سالمندان و ناتوانان"),XML.GetDrawable("baseline_elderly_black_36dp"))
	SayarDalilSpinner.Add2(SaeloZahra.CSB("سایر"),XML.GetDrawable("baseline_sick_black_36dp"))
	
	SayerDalilLbl.Typeface=SaeloZahra.Font
	
	
	HamrahSpinner.Add(SaeloZahra.CSBTitle("بدون همراه"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("یک نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("دو نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("سه نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("چهار نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("پنج نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("شش نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("هفت نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("هشت نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("نه نفر"))
	HamrahSpinner.Add(SaeloZahra.CSBTitle("ده نفر"))
	
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

Private Sub MelliET_FocusChanged (HasFocus As Boolean)
	If Not(HasFocus) Then
		If Not(SaeloZahra.CheckMelliCode(MelliET.text)) Then
			ToastMessageShow(SaeloZahra.CSB("کد ملی وارد شده اشتباه است"),True)
			MelliET.RequestFocus
		End If
	End If
End Sub

Private Sub SelectTimeBtn_Click
	time.show("time")
End Sub






Sub Time_onTimeSet(hourStart As Int, minuteStart As Int, hourEnd As Int, minuteEnd As Int)
	Dim StartTime, EndTime As Long
	DateTime.TimeFormat = "HH:mm"
	StartTime = DateTime.TimeParse(hourStart&":"&minuteStart)
	EndTime = DateTime.TimeParse(hourEnd&":"&minuteEnd)
	TimeEt.Text = DateTime.Time(StartTime) & "  __  " & DateTime.Time(EndTime)
	TimeEt.Tag = StartTime&"|"&EndTime
	Log(StartTime)
End Sub

Sub Time_onCancel
	Log("Time_onCancel")
End Sub

Sub time_onDismiss
	Log("Time_onDismiss")
End Sub



Private Sub SaveBtn_Click
	Log(SelectMapACT.SelectedMakanlatlng)
	
	If SelectMapACT.SelectedMakanlatlng.Length<5 Then
		Snake.Initialize(Activity,SaeloZahra.CSB("ابتدا موقعیت مکانی را انتخاب کنید"),Snake.LENGTH_LONG)
		Snake.SetAction("SelectLocationBtn_Click",SaeloZahra.CSB("انتخاب مکان"))
		Snake.Show
		Return
	End If
	
	If MelliET.Text.Length<5 Then
		Snake.Initialize(Activity,SaeloZahra.CSB("ابتدا کد ملی خود را وارد کنید"),Snake.LENGTH_LONG)
		Snake.Show
		MelliET.RequestFocus
		Return
	End If
	
	If Not(SaeloZahra.CheckMelliCode(MelliET.Text)) Then
		Snake.Initialize(Activity,SaeloZahra.CSB("کد ملی وارد شده صحیح نیست"),Snake.LENGTH_LONG)
		MelliET.RequestFocus
		Snake.Show
		Return
	End If
	
	If SayarSwitch.Checked Then
		
		Msgbox2Async(SaeloZahra.CSB(""&CRLF&"پیشاپیش از صبوری شما متشکریم"),SaeloZahra.CSBTitle("منتظر بمانید"),SaeloZahra.CSB("با تشکر"),"","",Null,True)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Activity.Finish
		End If
	Else
		Msgbox2Async(SaeloZahra.CSB("در تاریخ پیشنهادی شما ، شعبه مسجد ابوالفضل پیشنهاد میشود"&CRLF&"آیا مایل به رفتن به این شعبه در ساعت پیشنهادی خودتان هستید؟"),SaeloZahra.CSBTitle("نتیجه"),SaeloZahra.CSB("بله، نام بنده را ثبت کنید"),SaeloZahra.CSB("خیر، شعبه دیگری پیشنهاد کنید"),SaeloZahra.CSB("شاید بعدا ساعت را تغییر دهم"),Null,True)
	End If
	
	File.WriteString(SaeloZahra.Dir,"melli",MelliET.Text)
	File.WriteString(SaeloZahra.Dir,"tel",TelEt.Text)
	File.WriteString(SaeloZahra.Dir,"time",TimeEt.Text)
	
End Sub

Private Sub SelectLocationBtn_Click
	
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	
	Starter.rp.CheckAndRequest(Starter.rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	If Result Then StartActivity(SelectMapACT)
	
End Sub

Private Sub SayarSwitch_CheckedChange(Checked As Boolean)
	If Checked Then
		SayarJavabLbl.Text = SaeloZahra.CSB("بله نیاز دارم")
		PanelSayarDalil.SetVisibleAnimated(313,True)
		PanelSayar.SetLayoutAnimated(313,PanelSayar.Left,PanelSayar.Top,PanelSayar.Width,222dip)
	Else
		SayarJavabLbl.Text = SaeloZahra.CSB("خیر، نیاز ندارم")
		PanelSayarDalil.SetVisibleAnimated(313,False)
		PanelSayar.SetLayoutAnimated(313,PanelSayar.Left,PanelSayar.Top,PanelSayar.Width,150dip)
	End If
	SaveBtn.SetLayoutAnimated(313,SaveBtn.Left,PanelSayar.Top+PanelSayar.Height+33dip,SaveBtn.Width,SaveBtn.Height)
	Responsive
End Sub

Sub Responsive
	PanelSayar.SetLayoutAnimated(313,PanelSayar.Left,PanelHamrah.Top+PanelHamrah.Height + 20dip,PanelSayar.Width,PanelSayar.Height)
	Sleep(0)
	SayarSwitch_CheckedChange(SayarSwitch.Checked)
	Sleep(0)
	SaveBtn.SetLayoutAnimated(313,SaveBtn.Left,PanelSayar.Top+PanelSayar.Height+36dip,SaveBtn.Width,SaveBtn.Height)
	Sleep(313)
	ScrollView1.Panel.Height = SaveBtn.Top + SaveBtn.Height + 40dip
End Sub

Private Sub HamrahSpinner_ItemClick (Position As Int, Value As Object)

	Dim Height As Int = 120dip
	
	Select Position
		Case 0
			Height = 120dip
		Case 1
			Height = 190dip
		Case 2
			Height = 260dip
		Case 3
			Height = 333dip
		Case 4
			Height = 410dip
		Case 5
			Height = 480dip
		Case 6
			Height = 560dip
		Case 7
			Height = 635dip
		Case 8
			Height = 708dip
		Case 9
			Height = 780dip
		Case 10
			Height = 860dip
	End Select
	
	PanelHamrah.SetLayoutAnimated(313,PanelHamrah.Left,PanelHamrah.Top,PanelHamrah.Width,Height)
	
	Sleep(0)
	
	Responsive
	
End Sub



Sub LoginKon
	Dim PLogin As Panel
	PLogin.Initialize("PLogin")
	Dim UserNameET As EditText
	UserNameET.Initialize("UserNameET")
	UserNameET.InputType=UserNameET.INPUT_TYPE_PHONE
	UserNameET.Typeface=SaeloZahra.Font
	UserNameET.Text = "demo"
	UserNameET.Hint = "شماره تماس"
	PLogin.AddView(UserNameET,5%x,5%x,60%x,14%x)
	
	Dim PasswordET As EditText
	PasswordET.Initialize("PasswordET")
	PasswordET.Typeface=SaeloZahra.Font
	PasswordET.Hint = "کلمه عبور"
	PasswordET.Text = "demo"
	PasswordET.PasswordMode=True
	PLogin.AddView(PasswordET,5%x,25%x,60%x,14%x)
				
	If File.Exists(SaeloZahra.Dir,"username") And File.Exists(SaeloZahra.Dir,"password") Then
		UserNameET.Text= File.ReadString(SaeloZahra.Dir,"username")
		PasswordET.Text= File.ReadString(SaeloZahra.Dir,"password")
	End If
	
	Dim LoginDialog As CustomDialog2
	LoginDialog.AddView(PLogin,72%x,45%x)
	Dim loginDialogResult As Int = LoginDialog.Show( SaeloZahra.CSB("ورود به بخش مجریان") , SaeloZahra.CSB("ورود") , SaeloZahra.CSB("لغو") , "" , Null)
	If loginDialogResult == DialogResponse.POSITIVE Then
					
'		LoginJob.Initialize("LoginJob",Starter)
'		LoginJob.Tag="home"
'		Dim M1 As Map
'		M1.Initialize
'		M1.Put("username",UserNameET.Text)
'		M1.Put("password",PasswordET.Text)
'		File.WriteMap(SaeloZahra.Dir,"UPTemp",M1)
'		LogColor(SaeloZahra.JsonUrl&"members/login?username="&UserNameET.Text&"&password="&PasswordET.Text,SaeloZahra.ColorDark)
'		LoginJob.PostMultipart(SaeloZahra.JsonUrl&"members/login",M1,Null)
		If UserNameET.Text=="demo" And PasswordET.Text=="demo" Then
			SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
			Show.convertActivityFromTranslucent
			StartActivity(AdminsAct)
		Else
			Snake.Initialize(Activity,SaeloZahra.CSB("نام کاربری یا کلمه عبور غلط است"),Snake.LENGTH_LONG)
			Snake.Show
		End If
	End If
End Sub

