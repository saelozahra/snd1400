B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: false
	#IncludeTitle: true
#End Region
#Extends: android.support.v7.app.AppCompatActivity

Sub Process_Globals
	Dim SelectedMakanlatlng As String
'	Dim ApiKey As String = "AIzaSyC0P4XSC-UZoO2wpZ2wfQlQM8P4ErQLljY"
	Dim myLocation As Location
	Dim t,T2 As Timer
End Sub

Sub Globals
	
	Dim gmap As GoogleMap
	Dim cp As CameraPosition
	Dim MapFragment1 As MapFragment
	Dim gme As GoogleMapsExtras
	Dim OnMyLocationChangeListener1 As OnMyLocationChangeListener
	Dim latlng_old As LatLng
	Dim DTTC As DoubleTaptoClose
	Dim Show 	As Amir_SliderShow
	
	Dim get_my_address As HttpJob
	Dim Started As Boolean = True
	Private address_bar_lbl As Label
	Dim time,Time2 As Int
	Private Panel1 As Panel
	Private MarkerIV As ImageView
	Private SelectLocationBTN As Button
	Private ActionBar As ACToolBarDark
	
	
	
	
	
'	Dim PE As PersianEncryption
'	Dim TIP As MSShowTipsBuilder
'	Dim TipState As Int = 0
End Sub


Sub Activity_Create(FirstTime As Boolean)
	Try
			
		Activity.LoadLayout("MapLayout")
		
		
		
'		ActionBar.NavigationIconBitmap = LoadBitmapResize(File.DirAssets,"menu.png",ActionBar.Height/2,ActionBar.Height/2,True)
		
		
		
		ActionBar.Color=SaeloZahra.ColorDark
		ActionBar.TitleTextColor=Colors.White
		ActionBar.subTitleTextColor=Colors.LightGray
		ActionBar.subTitle=SaeloZahra.CSB("موقعیت مکانی اتومبیل را انتخاب کنید")
		
		Dim X1 As XmlLayoutBuilder
		If File.Exists(SaeloZahra.dir,"usrnm") Then
			If File.ReadString(SaeloZahra.dir,"usrnm")=="majedi" Or File.ReadString(SaeloZahra.dir,"usrnm")=="saelozahra" Then
				ActionBar.Menu.Add2(1,1,"عضویت کاربران",X1.GetDrawable("twotone_how_to_reg_white_24")).ShowAsAction=2
			End If
		End If
		
		
		address_bar_lbl.Color		= SaeloZahra.Color
		address_bar_lbl.TextColor	= Colors.White
		address_bar_lbl.Typeface= SaeloZahra.font
		
		
		If MapFragment1.IsGooglePlayServicesAvailable = False Then
			ToastMessageShow("لطفا گوگل پلی را نصب کنید.", True)
			Activity.Finish
		End If
		
		Activity.Title = SaeloZahra.CSBTitle("موقعیت مکانی اتومبیل را انتخاب کنید")
		myLocation.Initialize
		
		If Not(File.Exists(SaeloZahra.dir,"lat")) Then
			File.WriteString(SaeloZahra.dir,"lat" ,"29.609503")
			File.WriteString(SaeloZahra.dir,"lng","52.542800")
		End If
		
		myLocation.Latitude =File.ReadString(SaeloZahra.dir,"lat")
		myLocation.Longitude=File.ReadString(SaeloZahra.dir,"lng")
		
		
		
		
		get_my_address.Initialize(	"get_my_address", Starter)
		
		
		t.Initialize("t",100)
		t.Enabled=True
		T2.Initialize("T2",100)
		T2.Enabled=True
		
		SelectLocationBTN.Typeface=SaeloZahra.fontBold
		
		
		MarkerIV.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"location.png",MarkerIV.Width,MarkerIV.Height,True))
		
		DTTC.InItIaLiZe("مجددا دکمه بازگشت را فشار دهید")
		
		
	Catch
		Log(LastException)
	End Try
	

End Sub

Sub Activity_Resume
	Try
		If Starter.GPS1.GPSEnabled = False Then
			ToastMessageShow("لطفا جی پی اس را فعال کنید", True)
			If DialogResponse.POSITIVE == Msgbox2Async("لطفا موقعیت یاب خود را روشن کنید","موقعیت","روشن کن","لغو","",Null,False) Then
				StartActivity(Starter.GPS1.LocationSettingsIntent) 'Will open the relevant settings screen.
			End If
		Else
			Starter.rp.CheckAndRequest(Starter.rp.PERMISSION_ACCESS_FINE_LOCATION)
			Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
			If Result Then CallSubDelayed(Starter, "StartGPS")
		End If
		SaeloZahra.ActivePage="selectmap"
	Catch
		Log(LastException)
	End Try
End Sub


Sub Activity_Pause (UserClosed As Boolean)
	get_my_address.Release
	
	CallSubDelayed(Starter, "StopGPS")
	SaeloZahra.ActivePage="other"
End Sub






Sub ActionBar_NavigationItemClick
	
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		DTTC.TapToClose
		Return True
	Else
		Return False
	End If
End Sub














Sub goToMyLocation
	Try

		If Starter.gpsStarted Then
			cp.Initialize( myLocation.Latitude, myLocation.Longitude, gme.GetMaxZoomLevel(gmap) )
			gmap.AnimateCamera(cp)
			Log("Go To My Location")
			CallSubDelayed(Starter, "StopGps")
		End If
	
		If Started Then
			Started=False
		End If
	
		If Starter.GPS1.GPSEnabled Then
			gmap.MyLocationEnabled=True
		End If
	
	Catch
		Log(LastException)
	End Try
End Sub


Sub change_addressbar_text(text As String)
	address_bar_lbl.Text=text
	address_bar_lbl.SetVisibleAnimated(313,True)
'	address_bar_lbl.Top=GetDeviceLayoutValues.Height-address_bar_lbl.Height-9dip
	time=0
End Sub

Sub t_Tick
	time=time+72
	If time>9999 Then
		address_bar_lbl.SetVisibleAnimated(1000,False)
	End If
End Sub

Sub T2_Tick
	Time2=Time2+1
End Sub

Sub MapFragment1_CameraChange (Position As CameraPosition)
	Try
		
		If Time2>33 Then
		
			Time2 = 0
			MarkerIV.SetLayoutAnimated(10,MarkerIV.Left+5dip,MarkerIV.Top-18dip,MarkerIV.Width-10dip,MarkerIV.Height-2dip)
			MarkerIV.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"location.png",MarkerIV.Width,MarkerIV.Height,True))
		
			If SaeloZahra.CheckConnection Then
				get_my_address.Download("https://us1.locationiq.com/v1/reverse.php?key=50f886904c60e7&lat="&Position.Target.Latitude&"&lon="&Position.Target.Longitude&"&accept-language=fa&format=json")
				'		get_my_address.Download("http://maps.google.com/maps/api/geocode/json?latlng="&Position.Target.Latitude&","&Position.Target.Longitude&"&sensor=False&language=fa")
				LogColor("https://us1.locationiq.com/v1/reverse.php?key=50f886904c60e7&lat="&Position.Target.Latitude&"&lon="&Position.Target.Longitude&"&accept-language=fa&format=json",Colors.Yellow)
			Else
				address_bar_lbl.SetVisibleAnimated(313,False)
			End If
		
			Sleep(50)
			MarkerIV.SetLayoutAnimated(202,MarkerIV.Left-5dip,MarkerIV.Top+18dip,MarkerIV.Width+10dip,MarkerIV.Height+2dip)
			MarkerIV.SetBackgroundImage(LoadBitmapResize(File.DirAssets,"location.png",MarkerIV.Width,MarkerIV.Height,True))
		
			MarkerIV.Tag=CreateMap("lat":Position.Target.Latitude,"lng":Position.Target.Longitude)
		
		End If
	Catch
		Log(LastException)
	End Try
End Sub


Sub MapFragment1_Ready
	Try
		
		Log("map ready")
		
		gmap = MapFragment1.GetMap
		
		Starter.rp.CheckAndRequest(Starter.rp.PERMISSION_ACCESS_FINE_LOCATION)
		Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
		If Result Then
			gmap.MyLocationEnabled = True
		Else
			gmap.MyLocationEnabled = False
			Log("No permission!")
		End If
		
			
		If Not(gmap.IsInitialized) Then
			ToastMessageShow("نتوانستیم نقشه را نصب کنیم", True)
		Else
			latlng_old.Initialize(myLocation.Latitude,myLocation.Longitude)
			cp.Initialize( myLocation.Latitude, myLocation.Longitude, 17 )
			gmap.AnimateCamera(cp)
			latlng_old.Initialize(myLocation.Latitude,myLocation.Longitude)
	'		NearPlace.Download(SaeloZahra.json_url&"/user/index/json/2/"&myLocation.Latitude&"/"&myLocation.Longitude)
			Log("Go To My Location")
		End If
		
		SaeloZahra.ChangeGooglemapStyle(File.ReadString(File.DirAssets,"googlemapstyle.txt"),gmap)
		
		OnMyLocationChangeListener1.Initialize("OnMyLocationChangeListener1")
		gme.SetOnMyLocationChangeListener( gmap , OnMyLocationChangeListener1 )
		
		MarkerIV.Tag=CreateMap("lat":myLocation.Latitude,"lng":myLocation.Longitude)
		
		
'		afradJob.Download(	SaeloZahra.json_url&"afrad?per_page=100&filter[meta_key]=submitbyapp&filter[meta_value]=true")
		
		
		
		
		
		
		
		
		
		
		
		
	Catch
		Log(LastException)
	End Try
		
End Sub
		
Sub OnMyLocationChangeListener1_MyLocationChange(Location1 As Location)
	myLocation = Location1
End Sub
	


















Sub JobDone(Job As HttpJob)
	Log(Job.JobName&" | "&Job.Success)
	ProgressDialogHide
	If Job.Success Then
		Select Job.JobName
			
		End Select
	Else
		ToastMessageShow(SaeloZahra.CSB("خطا در تکمیل درخواست..."),False)
	End If
End Sub

Sub SelectLocationBTN_Click
	Try
			
		Dim point As Map = MarkerIV.Tag
		
		If gmap.IsInitialized Then
			
			File.WriteString(SaeloZahra.dir,"place_lat",point.Get("lat"))
			File.WriteString(SaeloZahra.dir,"place_lng",point.Get("lng"))
			
			SelectedMakanlatlng = point.Get("lat")&","&point.Get("lng")
			
'			SaeloZahra.SetAnimation("zoom_exit","zoom_enter")
'			Show.convertActivityFromTranslucent
'			StartActivity(NewKhalafAct)
			
			Activity.Finish
			
		End If

	Catch
		Log(LastException)
		ToastMessageShow("خطا در خواندن موقعیت مکانی",True)
		MsgboxAsync(LastException.Message,"خطای ثبت موقعیت")
		Activity.Finish
	End Try
End Sub


