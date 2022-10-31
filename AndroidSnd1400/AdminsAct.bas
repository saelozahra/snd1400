B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.6
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	Dim T As Timer
End Sub

Sub Globals
	Dim Config 	As Amir_SliderConfig
	Dim Show 	As Amir_SliderShow
	Dim Camera  As Camera
	Private MosaicBgLbl As Label
	Private TitleLbl As Label
	Private CameraPanel As Panel
	Dim ab As ABZxing
	Private BackBtn As Button
	Dim XML As XmlLayoutBuilder
	Dim AghlamDialog As CustomDialog2
	Private BtnReqThing As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("AdminsLayout")
	
	BackBtn.Color=Colors.Transparent
	
	
	Starter.RP.CheckAndRequest(Starter.RP.PERMISSION_CAMERA)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	If Not(Result) Then
		ToastMessageShow("شما اجازه دسترسی به دوربین را ندارید", True)
		Return
	Else
		
		Camera.Initialize(CameraPanel,"camera")
		
		Dim LblHover As Label
		LblHover.Initialize("LblHover")
		LblHover.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"camera.png",CameraPanel.Width,CameraPanel.Height,False))
		CameraPanel.AddView(LblHover,0,0,-2,-2)
		SaeloZahra.SetCornerRadii(CameraPanel,14dip,14dip,14dip,14dip,14dip,14dip,14dip,14dip)
		
	End If
	
	BtnReqThing.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"thing_for_shoabe.png",BtnReqThing.Width,BtnReqThing.Height,True)).Gravity=Gravity.CENTER
	
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
	
	T.Initialize("t",1000)
	T.Enabled=True
	
	ToastMessageShow("برای شناسایی فرد روی تصویر کلیک کنید",False)
	
End Sub

Sub t_Tick
	
	Dim TimeToClean As Int
	If DateTime.GetMinute(DateTime.Now)>30 Then
		TimeToClean = 60-DateTime.GetMinute(DateTime.Now)
	Else
		TimeToClean = 30-DateTime.GetMinute(DateTime.Now)
	End If
	
	Dim SecToClean As Int
	SecToClean = 60-DateTime.GetSecond(DateTime.Now)
	
	
	Dim CsbT As CSBuilder
	CsbT.Initialize.Color(Colors.White).Typeface(SaeloZahra.fontBold).Size(60).Append(TimeToClean&":"&SecToClean).Color(0xFFFCE8DF).Typeface(SaeloZahra.Font).Size(33).Append(" دقیقه"&CRLF).Size(28).Append(" تا ضدعفونی مجدد شعبه").PopAll
	TitleLbl.Text=CsbT
	
	
	
End Sub

Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)

	Try
		Camera.StopPreview
		Camera.Release
	Catch
		Log(LastException)
	End Try
	
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

Sub ToolBar_NavigationItemClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub


Sub camera_Ready (Success As Boolean)

	Try
		If Success Then Camera.StartPreview
	Catch
		Log(LastException)
	End Try
	
End Sub

Sub LblHover_Click
	Activity_Click
End Sub

Sub Activity_Click
	Log("Activity_Click")
	Dim PI As PhoneIntents
	Dim Ok As Boolean
	Dim pm As PackageManager
	Dim packages As List
	packages = pm.GetInstalledPackages
	For i = 0 To packages.Size - 1
		Dim sss As String = packages.Get(i)
		If sss.Contains("com.google.zxing.client.android") Then
			Ok = True
			Log(packages.Get(i))
		End If
	Next
		
	If Ok Then
		ab.ABGetBarcode("qr","QR_CODE_TYPES")
	Else
		StartActivity (PI.OpenBrowser("https://play.google.com/store/apps/details?id=com.google.zxing.client.android"))
	End If
	
	Log("Activity_Click shod")
End Sub
	
Sub qr_BarcodeFound (barCode As String, formatName As String)
	
	Log(barCode)
	Log(formatName)
	
	Log(formatName&":  "&barCode)
	
	ToastMessageShow(SaeloZahra.CSB("کدملی "&barCode&" در این ساعت اجازه ورود به شعبه را دارد"),True)
	
End Sub

Sub qr_Canceled()
	ToastMessageShow("لغو شد",True)
End Sub


Private Sub BackBtn_Click
	ToolBar_NavigationItemClick
End Sub




Sub BtnReqThing_click
	Dim PLogin As Panel
	PLogin.Initialize("PLogin")
	
	Dim NameSpinn As ACSpinner
	NameSpinn.Initialize("NameSpinn")
	NameSpinn.Add2(SaeloZahra.CSB("تعرفه رای‌دهی"),XML.GetDrawable("baseline_text_snippet_white_24"))
	NameSpinn.Add2(SaeloZahra.CSB("مواد ضد عفونی کننده"),XML.GetDrawable("baseline_coronavirus_black_36dp"))
	NameSpinn.Add2(SaeloZahra.CSB("لوازم تحریر و استامپ"),XML.GetDrawable("round_search_white_24"))
	PLogin.AddView(NameSpinn,5%x,5%x,60%x,14%x)
			
	Dim TedadET As EditText
	TedadET.Initialize("TedadET")
	TedadET.InputType=TedadET.INPUT_TYPE_NUMBERS
	TedadET.Typeface=SaeloZahra.Font
	TedadET.Text = "12"
	TedadET.Hint = "چه تعدادی میخواین؟"
	PLogin.AddView(TedadET,5%x,25%x,60%x,14%x)
	
	
	AghlamDialog.AddView(PLogin,72%x,45%x)
	Dim AghlamDialogResult As Int = AghlamDialog.Show( SaeloZahra.CSB("چه چیزی برای شعبه نیاز دارید؟") , SaeloZahra.CSB("ثبت درخواست") , "" , "" , Null)
	If AghlamDialogResult == DialogResponse.POSITIVE Then
		Dim Snake As Hitex_Snackbar
		Snake.Initialize(Activity,SaeloZahra.CSB("درخواست شما ثبت شد"),Snake.LENGTH_LONG)
		Snake.Show
	End If
End Sub

