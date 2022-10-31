B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.6
@EndOfDesignText@

#Region  Activity Attributes 
	#Extends: androidx.appcompat.app.AppCompatActivity
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
End Sub

Sub Globals
	
End Sub


Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("LangLayout")
	
End Sub


Private Sub Activity_Click
	Activity.Finish
	SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
	StartActivity(HomeAct)
End Sub