import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.awt.Robot
import java.awt.event.KeyEvent
SellPage sellPage = new SellPage()

String maCT = "CT" + new Date().format("HHmmss")
String ngayTao = "01/05/2026 16:40"
String nguoiBan = "Admin"
String khachHang = "Huy Thanh"
String sdt = "0379889823"
String maSP = "SP001"
String soLuong = "2"

WebUI.callTestCase(findTestCase("common/login"), [:])

sellPage.moManHinhBanHang()

sellPage.nhapMaCT(maCT)
sellPage.nhapVaLayNgayTao(ngayTao)

sellPage.chonNguoiBan(nguoiBan, true)

sellPage.nhapKhachHang(sdt)
sellPage.verifyThongTinKhachHang(khachHang, sdt)

sellPage.timVaThemSanPhamNhieuDong(maSP, soLuong)

double tongCong = sellPage.getTongCong()

sellPage.clickSave()

WebUI.delay(2) 

Robot robot = new Robot()
robot.delay(2000)
robot.keyPress(KeyEvent.VK_ESCAPE)
robot.keyRelease(KeyEvent.VK_ESCAPE)

WebUI.delay(2)

WebUI.openBrowser('')
WebUI.navigateToUrl("https://hvtester.pos365.vn/Signin")
WebUI.callTestCase(findTestCase("common/login"), [:])
sellPage.moDanhSachDonHang()
sellPage.searchProductByMa(maCT)

sellPage.verifyMaCT(maCT)
sellPage.verifyNgayTao(ngayTao)
sellPage.verifyNguoiBan(nguoiBan)
sellPage.verifyKhachHang(khachHang)

sellPage.verifyTongCong(String.valueOf((long) tongCong))