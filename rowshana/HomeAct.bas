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
	Dim Show As Amir_SliderShow
	Private HomeTitleLbl As Label
	Private Homebtn1,Homebtn2,Homebtn3,HomeBTN4 As Label

End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("HomeLayout")
	
	Dim DifTime As Long = Round2((DateTime.DateParse("09/27/2021") - DateTime.Now)/1000/60/60/24,0)
	Dim CsbT As CSBuilder
	CsbT.Initialize.Color(Colors.White).Typeface(SaeloZahra.fontBold).Size(60).Append(DifTime).Color(0xFFFCE8DF).Typeface(SaeloZahra.Font).Size(33).Append(" روز"&CRLF).Size(28).Append(" تا اربعین حسینی").PopAll
	HomeTitleLbl.Text=CsbT
	
	DTTC.InItIaLiZe("مجددا دکمه خروج را بزنید")
	
	Homebtn1.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn1.png",Homebtn1.Width,Homebtn1.Height,True)).Gravity=Gravity.CENTER
	Homebtn2.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn2.png",Homebtn2.Width,Homebtn2.Height,True)).Gravity=Gravity.CENTER
	Homebtn3.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn3.png",Homebtn3.Width,Homebtn3.Height,True)).Gravity=Gravity.CENTER
	HomeBTN4.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"btn4.png",HomeBTN4.Width,HomeBTN4.Height,True)).Gravity=Gravity.CENTER
	
	
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


Private Sub Homebtn1_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(NotificationAct)
End Sub

Private Sub Homebtn2_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(ContentListAct)
End Sub

Private Sub Homebtn3_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(MapAct)
End Sub

Private Sub Homebtn4_Click
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(ElectionAct)
End Sub

