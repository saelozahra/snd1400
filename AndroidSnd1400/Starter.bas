B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals

	Public GPS1 As GPS
	Public gpsStarted As Boolean
	Dim MyLocation As Location
	
	Public RP As RuntimePermissions
End Sub

Sub Service_Create
	'This is the program entry point.
	'This is a good place to load resources that are not specific to a single activity.

End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StopAutomaticForeground 'Starter service can start in the foreground state in some edge cases.
End Sub

Sub Service_TaskRemoved
	'This event will be raised when the user removes the app from the recent apps list.
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub



Sub Service_Destroy
	StopGps
End Sub


Sub GPS_LocationChanged (Location1 As Location)
	MyLocation = Location1
	
	If SaeloZahra.ActivePage =="selectmap" Then
		
		CallSub(SelectMapACT,"goToMyLocation")
		SelectMapACT.myLocation=MyLocation
		
		File.WriteString(SaeloZahra.dir,"lat",Location1.Latitude)
		File.WriteString(SaeloZahra.dir,"lng",Location1.Longitude)

	End If
	
End Sub


Public Sub StartGps
	If gpsStarted = False Then
		GPS1.Start(0, 0)
		gpsStarted = True
	End If
End Sub

Public Sub StopGps
	If gpsStarted Then
		GPS1.Stop
		gpsStarted = False
	End If
End Sub










Sub JobDone (Job As HttpJob)
	
	Log(Job.JobName&"  "&Job.Success)
	
	ProgressDialogHide
    

	If Job.Success Then
		Select Job.JobName
			Case "get_my_address"
				Dim parser As JSONParser
				parser.Initialize(Job.GetString)
				Dim root As Map = parser.NextObject
				Dim display_name As String = root.Get("display_name")
				Dim address As Map = root.Get("address")
				Dim postcode As String = address.Get("postcode")
				
				File.WriteString(SaeloZahra.dir,"postcode",postcode)
				File.WriteString(SaeloZahra.dir,"address",display_name)
				CallSubDelayed2(SelectMapACT,"change_addressbar_text",display_name)
		End Select
	Else
        
		If Not(SaeloZahra.CheckConnection) Then
			ToastMessageShow(SaeloZahra.CSB("اینترنت شما قطع است"),True)
		Else If Not(SaeloZahra.checkSite) Then
			ToastMessageShow(SaeloZahra.CSB("اتصال به سایت برقرار نشد"),True)
		Else
			Log("Error: " & Job.ErrorMessage)
			
			If Job.ErrorMessage.Contains("Unable to resolve host") Then
				ToastMessageShow(SaeloZahra.CSB(" اینترنتتون وصل نیست "),True)
			Else if Job.ErrorMessage.Contains("too_many_requests") Then
				Job.Release
			Else
				ToastMessageShow(SaeloZahra.CSB(" خطا در ورود ")&CRLF&Job.ErrorMessage,True)
			End If
		End If
		
	End If
    
	Job.Release
	
End Sub
