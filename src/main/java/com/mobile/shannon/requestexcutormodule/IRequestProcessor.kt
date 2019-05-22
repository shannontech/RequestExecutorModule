package com.mobile.shannon.requestexcutormodule

import android.app.Application
import javax.net.ssl.SSLContext

/*******************shannonai.com的ssl证书（字符串版本）***********************/
const val SSL_CERTIFICATE_STRING = "-----BEGIN CERTIFICATE-----\n" +
        "MIIFujCCBKKgAwIBAgIQCKZFxopikDJiYIMk20tdqDANBgkqhkiG9w0BAQsFADBe\n" +
        "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
        "d3cuZGlnaWNlcnQuY29tMR0wGwYDVQQDExRSYXBpZFNTTCBSU0EgQ0EgMjAxODAe\n" +
        "Fw0xODExMTYwMDAwMDBaFw0xOTExMTYxMjAwMDBaMBoxGDAWBgNVBAMMDyouc2hh\n" +
        "bm5vbmFpLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALEmyldP\n" +
        "jVDYzU4+M7Oo6Y31EY0JmeB48POBmZF4nsdYBr1jC9A+Alqb/a6VlymeaUjkSaPd\n" +
        "srh6/YQKp7Ea3ToPFO6CsrVGT67+OjEI/jZ/k/9hKcsNITt0cI8+RWwin56zkvlJ\n" +
        "wMYRji0qfHK7tdVeVkDqX8COm94BQl0cnoW57zW3WW/0+OcZVqCMXBb/rctp8VzS\n" +
        "X6pVyHzFvJxK3UXDO/52UKpZRdk+Wq52fhS05JwcWNnpckMpXo2/XlAY90isQwSX\n" +
        "nzGv7TMYfr5MTAneYz2jMm38duQGs/Dl/peljBM+6796pEg+KyHAxFMRMtx77E2w\n" +
        "AaGawLZGhqHHRgkCAwEAAaOCArYwggKyMB8GA1UdIwQYMBaAFFPKF1n8a8ADIS8a\n" +
        "ruSqqByCVtp1MB0GA1UdDgQWBBRpeFdU/AqE77BKQNvK5Dml7/5IZjApBgNVHREE\n" +
        "IjAggg8qLnNoYW5ub25haS5jb22CDXNoYW5ub25haS5jb20wDgYDVR0PAQH/BAQD\n" +
        "AgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjA+BgNVHR8ENzA1MDOg\n" +
        "MaAvhi1odHRwOi8vY2RwLnJhcGlkc3NsLmNvbS9SYXBpZFNTTFJTQUNBMjAxOC5j\n" +
        "cmwwTAYDVR0gBEUwQzA3BglghkgBhv1sAQIwKjAoBggrBgEFBQcCARYcaHR0cHM6\n" +
        "Ly93d3cuZGlnaWNlcnQuY29tL0NQUzAIBgZngQwBAgEwdQYIKwYBBQUHAQEEaTBn\n" +
        "MCYGCCsGAQUFBzABhhpodHRwOi8vc3RhdHVzLnJhcGlkc3NsLmNvbTA9BggrBgEF\n" +
        "BQcwAoYxaHR0cDovL2NhY2VydHMucmFwaWRzc2wuY29tL1JhcGlkU1NMUlNBQ0Ey\n" +
        "MDE4LmNydDAJBgNVHRMEAjAAMIIBBAYKKwYBBAHWeQIEAgSB9QSB8gDwAHYAu9nf\n" +
        "vB+KcbWTlCOXqpJ7RzhXlQqrUugakJZkNo4e0YUAAAFnGwhXOQAABAMARzBFAiEA\n" +
        "vPM7h5y3CE2AishZ6xzX5kO/pdr/5ftqFOvwIRr6HZ4CIG3U/AZqxb+6cyg/QQCm\n" +
        "UkEG2CayZ7myOnkYwVnHbJEIAHYAh3W/51l8+IxDmV+9827/Vo1HVjb/SrVgwbTq\n" +
        "/16ggw8AAAFnGwhYDQAABAMARzBFAiEA3L05EkW/dLwBucayQUPkuqR4O1C5wGon\n" +
        "FBvge5DQX8UCIGMesz+0klOdmGYgIxZI7fSLaYNum56+sJJB9GLi7+z3MA0GCSqG\n" +
        "SIb3DQEBCwUAA4IBAQCu8AWaooCcQlkPjMbQdBPIKofYb7tRdIqSl2GF5Bmvvy3U\n" +
        "iR36xT39QKr8BXj1Jt5+QGTdtWUeid9pAKXgXIlB0nag5ymExwOwQpbXi8WpL89Q\n" +
        "k2GuguOaTc+oXM/5l9d13/MtHHEku7YN/AuPXiFTi0CLfxWoDoLXTmJ2Qt2dpEQW\n" +
        "PIX+Ocuo1NgUgZ6vDPk2PZHfJs6Mr5lDgPhMhTic9RlS2wrUsrJrlm2ZNbNOCBBA\n" +
        "TQD9yzqkO3LfbTi9PtD23wYASDpTVGqYPp3ke9LnKpKIfWgxeIlGbv0F272idXZ0\n" +
        "MHnoAN8ShZ9I2+CwJOVjPr+0K4itO4TKuPt6XVmM\n" +
        "-----END CERTIFICATE-----"

/*******************shannonai.com的ssl证书（字符串版本）***********************/
interface IRequestProcessor {

    /**
     * https证书配置，为其他模块（如需）返回sslContext
     */
    fun getSSLContext(): SSLContext

    /**
     * 设置应用
     */
    fun setApplication(app: Application)

    /**
     * HTTP POST..JSON
     * @param url:请求的url
     * @param json:post所提交的json字符串
     * @param callback：请求返回时的回调
     */
    fun postRequest(url: String, json: String, callback: RequestCallback)

    /**
     * HTTP POST..JSON
     * @param header:添加的请求头
     * @param url:请求的url
     * @param json:post所提交的json字符串
     * @param callback：请求返回时的回调
     */
    fun postRequest(header: Pair<String, String>, url: String, json: String, callback: RequestCallback)

    /**
     * HTTP GET
     * @param header:添加的请求头
     * @param url:请求的url
     * @param callback：请求返回时的回调
     */
    fun getRequest(url: String, header: Pair<String, String>, callback: RequestCallback)
}