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

End Sub

Sub Globals
	Dim Config 	As Amir_SliderConfig
	Dim Show 	As Amir_SliderShow
	Private ZoomImageView1 As H_ZoomImageView
	Private ToolBar As ACToolBarLight
	Private ScrollView1 As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("SVLayout")
	SaeloZahra.SetToolbarStyle(ToolBar,"نقشه پیاده روی",True,ScrollView1)
	ToolBar.TitleTextColor=Colors.White
	ScrollView1.Visible=False
	Activity.Color=0xFFDBCDAD
	
	ZoomImageView1.Initialize("ZoomImageView1")
	ZoomImageView1.DoubleTapToZoom=True
	ZoomImageView1.ImageBitmap=LoadBitmap(File.DirAssets, "map.jpg")
	Activity.AddView(ZoomImageView1,ScrollView1.Left,ScrollView1.Top,ScrollView1.Width,ScrollView1.Height)
	
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
