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
	Dim SQL1 As SQL
	Dim CU1 As Cursor
End Sub

Sub Globals
	Dim Config 	As Amir_SliderConfig
	Dim Show 	As Amir_SliderShow
	Private ToolBar As ACToolBarLight
	Private PanelCandidate As Panel
	Dim sv As ACSearchView
	Dim SI As ACMenuItem
	Dim X1 As XmlLayoutBuilder
	Dim StateSpin,CitySpin As ACSpinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("CandidateListLayout")
	SaeloZahra.SetToolbarStyle(ToolBar,"",True,PanelCandidate)
	PanelCandidate.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"oskol.jpg",PanelCandidate.Width,PanelCandidate.Height,True)).Gravity=Bit.Or(Gravity.BOTTOM,Gravity.CENTER_VERTICAL)

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

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub PanelCandidate_Click
	SingleCandidateAct.CandidateID=6
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	StartActivity(SingleCandidateAct)
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode==KeyCodes.KEYCODE_BACK Then
		ToolBar_NavigationItemClick
		Return False
	Else
		Return True
	End If
End Sub

Sub ToolBar_NavigationItemClick
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_enter","zoom_exit")
End Sub













#Region MenuSearch

'Inline Java code to initialize the Menu
#If Java
	public boolean _onCreateOptionsMenu(android.view.Menu menu) {
		if (processBA.subExists("activity_createmenu")) {
			processBA.raiseEvent2(null, true, "activity_createmenu", false, new de.amberhome.objects.appcompat.ACMenuWrapper(menu));
			return true;
		}
		else
			return false;
	}
#End If

Sub Activity_CreateMenu(Menu As ACMenu)
	
	Log("Create Menu")
	
	sv.Initialize2("search", sv.THEME_DARK)
	sv.IconifiedByDefault = True

	Menu.Clear
	ToolBar.InitMenuListener
	sv.QueryHint=SaeloZahra.csb("نام کاندیدای را وارد کنید")
	SI = Menu.Add2(1, 3, "جستجو",X1.GetDrawable("round_search_white_24") )
	SI.SearchView = sv
	
	
	
	
	
	
	
	StateSpin.Initialize("StateSpin")
	StateSpin.TextColor=SaeloZahra.ColorLight
	StateSpin.DropdownBackgroundColor=SaeloZahra.ColorLight
	StateSpin.Add(SaeloZahra.CSBWhite("فارس"))
	StateSpin.Add(SaeloZahra.CSBWhite("البرز"))
	StateSpin.Add(SaeloZahra.CSBWhite("اصفهان"))
	StateSpin.Add(SaeloZahra.CSBWhite("اردبيل"))
	StateSpin.Add(SaeloZahra.CSBWhite("ايلام"))
	StateSpin.Add(SaeloZahra.CSBWhite("آذربايجان شرقي"))
	StateSpin.Add(SaeloZahra.CSBWhite("آذربايجان غربي"))
	StateSpin.Add(SaeloZahra.CSBWhite("بوشهر"))
	StateSpin.Add(SaeloZahra.CSBWhite("تهران"))
	StateSpin.Add(SaeloZahra.CSBWhite("چهارمحال وبختياري"))
	StateSpin.Add(SaeloZahra.CSBWhite("خراسان جنوبي"))
	StateSpin.Add(SaeloZahra.CSBWhite("خراسان رضوي"))
	StateSpin.Add(SaeloZahra.CSBWhite("خراسان شمالي"))
	StateSpin.Add(SaeloZahra.CSBWhite("خوزستان"))
	StateSpin.Add(SaeloZahra.CSBWhite("زنجان"))
	StateSpin.Add(SaeloZahra.CSBWhite("سمنان"))
	StateSpin.Add(SaeloZahra.CSBWhite("سيستان وبلوچستان"))
	StateSpin.Add(SaeloZahra.CSBWhite("قزوين"))
	StateSpin.Add(SaeloZahra.CSBWhite("قم"))
	StateSpin.Add(SaeloZahra.CSBWhite("كردستان"))
	StateSpin.Add(SaeloZahra.CSBWhite("كرمان"))
	StateSpin.Add(SaeloZahra.CSBWhite("كرمانشاه"))
	StateSpin.Add(SaeloZahra.CSBWhite("كهگيلويه وبويراحمد"))
	StateSpin.Add(SaeloZahra.CSBWhite("گلستان"))
	StateSpin.Add(SaeloZahra.CSBWhite("گيلان"))
	StateSpin.Add(SaeloZahra.CSBWhite("لرستان"))
	StateSpin.Add(SaeloZahra.CSBWhite("مازندران"))
	StateSpin.Add(SaeloZahra.CSBWhite("مركزي"))
	StateSpin.Add(SaeloZahra.CSBWhite("هرمزگان"))
	StateSpin.Add(SaeloZahra.CSBWhite("همدان"))
	StateSpin.Add(SaeloZahra.CSBWhite("يزد"))
	
	ToolBar.AddView(StateSpin,SaeloZahra.MaterialActionBarHeight*2.2,SaeloZahra.MaterialActionBarHeight,Gravity.RIGHT)
	
	CitySpin.Initialize("CitySpin")
	CitySpin.DropdownBackgroundColor=SaeloZahra.ColorLight
	CitySpin.Visible=False
	ToolBar.AddView(CitySpin,SaeloZahra.MaterialActionBarHeight*2.2,SaeloZahra.MaterialActionBarHeight,Gravity.RIGHT)
	
End Sub



Sub Search_QuerySubmitted (Query As String)
	
	SaeloZahra.P.HideKeyboard(Activity)
	Sleep(110)
	sv.Iconfied = True
	SI.ItemCollapsed = True
   
	Log("Search for '" & Query & "'")
	
	Dim KeyWord As String = Query.Trim
	If KeyWord = "" Then
		ProgressDialogHide
		Return
	End If
'	KeyWord = KeyWord.Replace(" ", "+")

'	Dim SU As StringUtils
'	KeyWord = SU.EncodeUrl(Query,"UTF8")
	
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	Show.convertActivityFromTranslucent
	
	ToastMessageShow(KeyWord,False)
	
End Sub




Sub ToolBar_MenuItemClick (Item As ACMenuItem)
	Log(Item.Id)
	Select Item.Id
		Case 0
			
	End Select
End Sub

#End Region

Sub StateSpin_ItemClick (Position As Int, Value As Object)
	CitySpin.Clear
	CitySpin.SetVisibleAnimated(313,True)
	Dim txt As String = SaeloZahra.CSBWhite(Value).ToString
	Try
		If SQL1.IsInitialized = False Then
			SQL1.Initialize(SaeloZahra.dir,"db.db",False)
		End If
		CU1 = SQL1.ExecQuery("SELECT * FROM ostanha WHERE ostanha.state = '"&txt&"'")
		For i = 0 To CU1.RowCount-1
			CU1.Position = i
			CitySpin.Add(SaeloZahra.CSBWhite(CU1.GetString("city")))
		Next
	Catch
		Log(LastException)
	End Try
End Sub


Sub CitySpin_ItemClick (Position As Int, Value As Object)
	ToastMessageShow("پیدا کردن نامزدهای "&Value,True)
End Sub