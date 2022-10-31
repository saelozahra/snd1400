B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=6.3
@EndOfDesignText@
#IgnoreWarnings:7,30

#Region  Service Attributes 
	#StartAtBoot: true
#End Region

Sub Process_Globals
	Dim fm As FirebaseMessaging
	Dim msg_type,msg_title,msg_body As String
'	Dim n1 As Notification
	Dim sql1 As SQL
	Dim R As Reflector
	Dim nb As NotificationBuilder
'	Dim MyToken As String
End Sub

Sub Service_Create
	
	fm.Initialize("fm")
'	MyToken = fm.Token
'	n1.Initialize
	
	fm.SubscribeToTopic("shirazzist")
	
	If Not(sql1.IsInitialized) Then
		If Not(File.Exists(SaeloZahra.dir,"db.db")) Then File.Copy(File.DirAssets,"db.db",SaeloZahra.dir,"db.db")
		sql1.Initialize(SaeloZahra.dir,"db.db",False)
	End If
	
	
End Sub

Public Sub SubscribeToTopic
	fm.SubscribeToTopic("general")
End Sub

Public Sub SubscribeToTopics( TopicName As String )
	
'	If Not(fm.IsInitialized) Then fm.Initialize("fm")
	
	TopicName=TopicName.Replace(" ","_")
	TopicName=TopicName.Replace(":","_")
	TopicName=TopicName.Replace("\n","")
	
	If TopicName=="" Then TopicName="shirazzist"
	
	Try
		fm.SubscribeToTopic( TopicName )
		Log("adding topic "&TopicName)
	Catch
		Log("error1: "&LastException.Message)
		R.Target = fm
		R.RunMethod2("subscribeToTopic",TopicName,"java.lang.String")
	End Try
	
	Sleep(0)
	
End Sub

Sub Service_Start (StartingIntent As Intent)
	If StartingIntent.IsInitialized Then fm.HandleIntent(StartingIntent)
	Sleep(0)
	Service.StopAutomaticForeground 'remove if not using B4A v8+.
End Sub

Sub fm_TokenRefresh (Token As String)
	Log("Token: "&Token)
End Sub

Sub fm_MessageArrived (Message As RemoteMessage)

	Log("Message arrived")
	Log("Message data: "&Message.GetData)
	
	If Message.GetData.ContainsKey("type") Then
		msg_type = Message.GetData.Get("type")
		msg_body = Message.GetData.Get("body")
	Else
		msg_type = "text"
		If Message.GetData.ContainsKey("body") Then
			msg_body = Message.GetData.Get("body")
		Else If Message.GetData.ContainsKey("val") Then
			msg_body = Message.GetData.Get("val")
		End If
	End If
	
	If Message.GetData.Get("title") == Null Then
		msg_title=Application.LabelName
	Else
		msg_title=Message.GetData.Get("title")
	End If
	
		
	If Message.GetData.Get("body") == Null Then
		msg_body=Application.LabelName
	Else
		msg_body=Message.GetData.Get("body")
	End If
	
	If Message.GetData.Get("title")==Null And Message.GetData.Get("body")==Null Then Return
	
	If msg_type == "expire" And msg_body=="yes" Then
		File.WriteString(saelozahra.dir,"expire",True)
	Else If msg_type == "expire" And msg_body=="no" Then
		File.Delete(saelozahra.dir,"expire")
	End If

	
	Log(msg_title&"  :  "&msg_type&"  :  "&msg_body)
	
	saveAndAlert(msg_title, msg_body, True)
	
End Sub



Sub saveAndAlert(title_str As String, content_str As String, NotifBool As Boolean)
	
		
	Try
		sql1.ExecNonQuery($"insert into notification (title,type,value,time) VALUES ('${msg_title}','${msg_type}','${msg_body}','${DateTime.Now}')"$)
		Log("Inserted")
	Catch
		Log($" ${LastException.Message} error to insert this id to notification = ${msg_title}"$   )
	End Try
	
	
	If NotifBool Then CreateNotification(title_str , content_str ,"icon",NotificationAct,True,True).Notify(1)'teraktor
	
End Sub

Public Sub CreateNotification(Title As String, ContentStr As String, Icon As String, TargetActivity As Object, Sound As Boolean, Vibrate As Boolean) As Notification
	
	Dim p As Phone
	If p.SdkVersion >= 21 Then
		Dim nb As NotificationBuilder
		nb.Initialize
		nb.DefaultSound = Sound
		nb.DefaultVibrate = Vibrate
		nb.AutoCancel = True
		nb.ContentTitle = Title
		nb.ContentText = ContentStr
		nb.setActivity(TargetActivity)
		nb.AddAction("ic_done_white_24dp","نمایش اطلاعیه","full",TargetActivity)
		nb.AddAction("baseline_my_location_white_24","کل اطلاعیه ها","full",NotificationAct)
		nb.SmallIcon = Icon
		
		Dim javaobjectInstance As JavaObject
		javaobjectInstance = nb
		javaobjectInstance.RunMethod ("setColor", Array As Object (saelozahra.Color))
	
		If p.SdkVersion >= 26 Then
			Dim ctxt As JavaObject
			ctxt.InitializeContext
			Dim manager As JavaObject
			manager.InitializeStatic("android.app.NotificationManager")
			Dim Channel As JavaObject
			Dim importance As String
			If Sound Then importance = "IMPORTANCE_DEFAULT" Else importance = "IMPORTANCE_LOW"
			Dim ChannelVisibleName As String = Application.LabelName
			Channel.InitializeNewInstance("android.app.NotificationChannel", _
                   Array("MyChannelId1", ChannelVisibleName, manager.GetField(importance)))
			manager = ctxt.RunMethod("getSystemService", Array("notification"))
			manager.RunMethod("createNotificationChannel", Array(Channel))
			Dim jo As JavaObject = nb
			jo.RunMethod("setChannelId", Array("MyChannelId1"))
		End If
		Return  nb.GetNotification
	Else
		Dim n As Notification
		n.Initialize
'		n.Sound=Sound
		n.AutoCancel=True
		n.Vibrate = Vibrate
		n.Icon = Icon
		
		n.SetInfo(Title, ContentStr, TargetActivity)
		Return n
	End If
End Sub

Sub Service_Destroy

End Sub