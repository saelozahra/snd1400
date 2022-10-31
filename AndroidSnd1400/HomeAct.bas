B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.5
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	Dim DTTC As DoubleTaptoClose
End Sub

Sub Globals
	Dim Counter As Int
	Dim Show As Amir_SliderShow
	Private HomeTitleLbl As Label
	Private Homebtn1,Homebtn2,Homebtn3 As Label
	Dim Snake As Hitex_Snackbar

	Dim UserNameET,PasswordET As EditText
	Dim LoginDialog As CustomDialog2
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("HomeLayout")
	
	Dim DifTime As Long = Round2((DateTime.DateParse("06/18/2021") - DateTime.Now)/1000/60/60/24,0)
	Dim CsbT As CSBuilder
	CsbT.Initialize.Color(Colors.White).Typeface(SaeloZahra.fontBold).Size(60).Append(DifTime).Color(0xFFFCE8DF).Typeface(SaeloZahra.Font).Size(33).Append(" روز"&CRLF).Size(28).Append(" تا انتخابات ریاست جمهوری 1400").PopAll
	HomeTitleLbl.Text=CsbT
	
	DTTC.InItIaLiZe("مجددا دکمه خروج را بزنید")
	
	Homebtn1.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn1.png",Homebtn1.Width,Homebtn1.Height,True)).Gravity=Gravity.CENTER
	Homebtn2.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn2.png",Homebtn2.Width,Homebtn2.Height,True)).Gravity=Gravity.CENTER
	
	If File.Exists(SaeloZahra.Dir,"melli") Then
		Homebtn3.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn33.png",Homebtn3.Width,Homebtn3.Height,True)).Gravity=Gravity.CENTER
	Else
		Homebtn3.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn3.png",Homebtn3.Width,Homebtn3.Height,True)).Gravity=Gravity.CENTER
	End If
	
	
End Sub

Sub Activity_Touch (Action As Int, X As Float, Y As Float)
	
	
	Log("Counter: "&Counter&"  Action: "&Action&" X: "&X&" y: "&Y)
	
	
	If Action == Activity.ACTION_DOWN Then
		Counter=0
	End If
	
	If Action == Activity.ACTION_MOVE Then
		Counter=Counter+Y
	End If
	
	If Action == Activity.ACTION_UP Then
		If Counter > 12000 Then
			LogColor("Hi",Colors.Cyan)
			
			Starter.RP.CheckAndRequest(Starter.RP.PERMISSION_CAMERA)
			Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
			If Not(Result) Then
				ToastMessageShow("شما اجازه دسترسی به دوربین را ندارید", True)
				Return
			Else
				LoginKon
			End If
			
		End If
	End If
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	Select KeyCode
		Case KeyCodes.KEYCODE_BACK
			DTTC.TapToClose
			Return True
		Case Else
			Return False
	End Select
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub Homebtn2_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(CandidaListAct)
End Sub

Private Sub Homebtn1_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(NotificationAct)
End Sub

Private Sub Homebtn3_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	
	If File.Exists(SaeloZahra.Dir,"melli") Then
		StartActivity(QRForScanAct)
	Else
		StartActivity(ElectionAct)
	End If
	
End Sub



Sub LoginKon
	Dim PLogin As Panel
	PLogin.Initialize("PLogin")
	UserNameET.Initialize("UserNameET")
	UserNameET.InputType=UserNameET.INPUT_TYPE_PHONE
	UserNameET.Typeface=SaeloZahra.Font
	UserNameET.Text = "demo"
	UserNameET.Hint = "شماره تماس"
	PLogin.AddView(UserNameET,5%x,5%x,60%x,14%x)
				
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

