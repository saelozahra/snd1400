B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.8
@EndOfDesignText@

#Region  Activity Attributes 
	#Extends: androidx.appcompat.app.AppCompatActivity
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	Dim sql1 As SQL
	Dim cu1 As Cursor
	Dim share As Intent
	Dim type_str As String
End Sub

Sub Globals
	Dim Config 	As Amir_SliderConfig
	Dim Show 	As Amir_SliderShow
	Dim su 		As StringUtils
	Dim X1	 	As XmlLayoutBuilder
	Dim ToolBar As ACToolBarLight
	Public css_start,css_end As String
	Dim Web 	As PhoneIntents
	Dim ScrollView1 As ScrollView
	
	Dim StopintNotif As Int=18dip
	
End Sub


Sub Activity_Create(FirstTime As Boolean)
	
	css_start	= "<html><body style='white-space: pre-wrap;box-sizing: border-box; padding:2%; margin: auto;text-align: right; width:100%;max-width:100%;direction:rtl;line-height:1.4;font-family:samim;font-size:110%;color:#777;'>           <style>@import url(https://cdn.rawgit.com/rastikerdar/samim-font/v3.1.0/dist/font-face.css); h1{font-size:130%;margin:3% auto;} hr, .hr {background: rgba(0, 0, 0, 0) url('file:///android_asset/sp.png') repeat-x scroll center center; border: 0 none;clear: both;height: 19px;margin: 8px auto;width: 100%;} a{color:#111;font-weight: bold;} .img{max-width:100%;}</style>       <pre style='white-space: pre-wrap;width:100%;font-family:Samim;'>"
	css_end		= "</pre><br style='clear:both;' > </body></html>"
	
	
	Activity.LoadLayout("SVLayout")
	
	SaeloZahra.SetToolbarStyle(ToolBar,"اطلاعیـــــــــه ها",True,ScrollView1)
	ToolBar.TitleTextColor=Colors.White
	
	If File.Exists(SaeloZahra.dir,"db.db") = False Then
		File.Copy(File.DirAssets,"db.db",SaeloZahra.dir,"db.db")
	End If
	
	
	If sql1.IsInitialized = False Then
		sql1.Initialize(SaeloZahra.dir,"db.db",False)
	End If
	
	
	LoadMsgs
	
		
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
	
End Sub

Sub LoadMsgs
	
	cu1 = sql1.ExecQuery("SELECT * FROM notification ORDER BY id DESC limit 110")
	
		
	For i = 0 To cu1.RowCount-1
			
		cu1.Position = i
		Dim CsbTxt As CSBuilder
		CsbTxt.Initialize
		CsbTxt.Bold.Typeface(SaeloZahra.font).Size(16).Color(Colors.DarkGray).Append(cu1.GetString("title")).PopAll
		CsbTxt.Append(CRLF)
		CsbTxt.Append(CRLF)
		CsbTxt.Typeface(SaeloZahra.font).Color(0xFF7C7979).Size(12).Append(cu1.GetString("value").Replace("<br>",CRLF).Replace("<hr>","___________________________________"&CRLF)).PopAll
		
		Log(cu1.GetString("id"))
		
		Dim ThisIcon As String = cu1.GetString("icon")
		If ThisIcon=="" Then ThisIcon = 0
		
		Dim P1 As Panel
		P1 = addAlertBox(cu1.GetString("id"),CsbTxt,cu1.GetString("time"))
		
		ScrollView1.Panel.AddView( P1, 20dip, StopintNotif, Activity.Width-40dip, P1.Height )
		StopintNotif = StopintNotif+P1.Height + 24dip
		ScrollView1.Panel.Height = StopintNotif +22dip
		
	Next
	
	ScrollView1.Panel.Height=StopintNotif+22dip
	
End Sub

Sub Activity_Resume
	
	
	Dim in1 As Intent
	
	in1 = Activity.GetStartingIntent
	If in1.HasExtra("Notification_Tag") Then
		Log(in1.GetExtra("Notification_Tag")) 'Will log the tag
		If SaeloZahra.Debug Then ToastMessageShow(in1.GetExtra("Notification_Tag"),True)
	Else
		If SaeloZahra.Debug Then ToastMessageShow("Dont Has Extra",True)
	End If
	
	If in1.HasExtra("Notification_Tag") Then
	
	
	
		cu1 = sql1.ExecQuery("SELECT * FROM notification ORDER BY id DESC limit 1")
		
			
		For i = 0 To cu1.RowCount-1
			
			cu1.Position = i
			
			type_str = cu1.GetString("type")
			
			
			If type_str == "telegram" Then
				Activity.Finish
				open_telegram(cu1.GetString("value"))
			End If
		
			If type_str == "url" Then
				Activity.Finish
				StartActivity(Web.OpenBrowser(cu1.GetString("value")))
			End If
		
			If type_str == "activity" Then
				Activity.Finish
				StartActivity(cu1.GetString("value"))
			End If

'			subtitle_lbl.Text=cu1.GetString("title")
			
'			matn.LoadHtml(css_start &  cu1.GetString("value") & css_end )' "<h1>" & cu1.GetString("title") &  "</h1>" &
			
			
	
		Next

	

				
	Else
		
	End If
	

End Sub


Sub matn_OverrideUrl (Url As String) As Boolean
	ProgressDialogShow2("چند لحظه صبر کنید",True)
	StartActivity(Web.OpenBrowser(Url))
	Return True
End Sub


Sub matn_PageFinished (Url As String)
	ProgressDialogHide
End Sub



Sub Activity_Pause (UserClosed As Boolean)
	If cu1.IsInitialized Then cu1.Close
End Sub




Sub ToolBar_NavigationItemClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode==KeyCodes.KEYCODE_BACK Then
		ToolBar_NavigationItemClick
		Return False
	Else
		Return True
	End If
End Sub




Sub open_telegram(tid As String)
	Log("Open Telegram")
	Try
		share.Initialize(share.ACTION_EDIT,"tg://"&tid)
		StartActivity(share)
	Catch
		StartActivity(Web.OpenBrowser("https://t.me/"&tid))
	End Try
End Sub




Sub addAlertBox(Index As String, Text As CSBuilder, Time As Long) As Panel
	
'	Dim cdPanel As ColorDrawable
'	cdPanel.Initialize(Colors.White,18)
	
	Dim MyColor As Int = Colors.ARGB(255,Rnd(0,255),Rnd(0,255),Rnd(0,255))
	
	Dim cdPanel As GradientDrawable
	cdPanel.Initialize("TR_BL", Array As Int(Colors.White, Colors.White))
	cdPanel.CornerRadius = 12dip
	
	Dim pnl As Panel
	pnl.Initialize("")
	pnl.Tag = Index
	pnl.Background=cdPanel
	pnl.Elevation=12dip
	
	Dim lbl As Label
	lbl.Initialize("lbl")
	lbl.Tag  = Text
	lbl.Text = Text
	lbl.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
'	lbl.TextSize = DefaultTextSize
'	lbl.TextColor = DefaultTextColor
	lbl.Typeface = SaeloZahra.font
	pnl.AddView(lbl, 10dip, 10dip, Activity.Width - (25%x)-24dip  , 50dip)
	
	
	Dim cd As ColorDrawable
	cd.Initialize(MyColor,7dip)
	
	Dim timeLbl As Label
	timeLbl.Initialize("")
	timeLbl.Background	= cd
	
	timeLbl.Text	 	= SaeloZahra.mohasebe_tarikh(Time)

	
	
	timeLbl.TextSize	= 12
	timeLbl.SingleLine=True
	timeLbl.Ellipsize = "END"
	timeLbl.Gravity		= Bit.Or(Gravity.CENTER_VERTICAL, Gravity.CENTER_HORIZONTAL)
	timeLbl.textColor	= Colors.White
	pnl.AddView(timeLbl,12dip,12dip,88dip,18dip)
	
	
	Dim LineLbl As Label
	LineLbl.Initialize("")
	LineLbl.Color=Colors.LightGray
	pnl.AddView(LineLbl, Activity.Width - (25%x)+7dip, 14dip, 1dip, 50dip)
	
	Dim cdBubble As ColorDrawable
	cdBubble.Initialize2(MyColor,14dip,2dip,SaeloZahra.Color)
	
	Dim bubbleLbl As Label
	bubbleLbl.Initialize("")
	bubbleLbl.Background=cdBubble
	pnl.AddView(bubbleLbl, Activity.Width - (25%x), 12dip, 14dip, 14dip)
	
	
	Dim ImgV As ImageView
	ImgV.Initialize("")
	ImgV.Gravity = Gravity.CENTER
	ImgV.Background = X1.GetDrawable("baseline_text_snippet_white_24")
	pnl.AddView(ImgV, Activity.Width - 26%x,16dip,20%x,20%x)
	
	
	Dim minHeight As Int
	minHeight 		= su.MeasureMultilineTextHeight(lbl, Text) + 40dip
	lbl.Height  	= Max(50dip, minHeight)
	ImgV.Height 	= Max(50dip, minHeight)
	LineLbl.Height	= Max(50dip, minHeight)
	pnl.Height 		= Max(50dip, minHeight)+18dip
	
	Return pnl
	
End Sub

Sub lbl_Click
	
End Sub