Imports RTC
Imports omg.org.RTC



Public Class foo
    Inherits DataFlowComponentBase



    Private m_int_param0Conf As int = New int()
    Private Function int_param0Trans(ByVal str As String) As Boolean
                Return int.TryParse(str, m_int_param0Conf)
    End Function
    Private m_float_param0Conf As List(Of float) = New List(Of float)()
    Private Function float_param0Trans(ByVal str As String) As Boolean
                Dim items As String = str.Split(New Char() {","})
                m_float_param0Conf.Clear()
                Dim ret As Boolean = True
                For Each item As String in items
                    Dim temp As float
                    if !float.TryParse(item, temp) Then
                        ret = True
                        Continue For
                    End If
                    m_float_param0Conf.Add(temp)
                Next
                Return ret
    End Function


    Public Sub New(ByVal manager As Manager)
        MyBase.New(manager)


    End Sub

    Protected Overrides Function onInitialize() As ReturnCode_t
        bindParameter("int_param0", "0", New RTC.Config.TransFunc(AddressOf int_param0Trans))
        bindParameter("float_param0", "1.0,2.0,3.0", New RTC.Config.TransFunc(AddressOf float_param0Trans))

        Return ReturnCode_t.RTC_OK
    End Function

    'Protected Overrides Function onFinalize() As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onStartup(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onShutdown(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onActivated(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onDeactivated(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onExecute(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onAborting(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onError(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onReset(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onStateUpdate(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onRateChanged(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function
End Class

Public Class fooInit

    Private spec() As String = New String() _
    { _
        "implementation_id", "foo", _
        "type_name",         "foo", _
        "description",       "MDesc", _
        "version",           "1.0.3", _
        "vendor",            "TA2", _
        "category",          "manip2", _
        "activity_type",     "DataFlowComponent", _
        "max_instance",      "3", _
        "language",          "Visual Basic", _
        "lang_type",         "COMPILE" _
        , "conf.default.int_param0",   "0" _
        , "conf.default.float_param0",   "1.0,2.0,3.0" _
    }

    Public Sub New(ByVal manager As Manager)
        Dim profile As Properties = New Properties(spec)
        manager.registerFactory(profile, _
            New RtcNewFunc(AddressOf Createfoo), _
            New RtcDeleteFunc(AddressOf Deletefoo))
    End Sub

    Public Function Createfoo(ByVal manager As Manager) As RTObject_impl
        Return New foo(manager)
    End Function

    Public Sub Deletefoo(ByVal rtc As RTObject_impl)
        rtc.Dispose()
    End Sub

End Class
