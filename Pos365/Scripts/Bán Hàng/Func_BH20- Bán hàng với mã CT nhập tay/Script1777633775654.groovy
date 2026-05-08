
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.awt.Robot
import java.awt.event.KeyEvent

SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")

String maCT = "CT" + new Date().format("HHmmss")
WebUI.callTestCase(findTestCase("common/login"), [:])
sellPage.moManHinhBanHang()
sellPage.nhapMaCT(maCT)

for (int i = 1; i <= dataSP.getRowNumbers(); i++) {

	String ma = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)
	WebUI.comment(" Thêm SP: " + ma + " | SL: " + soLuong)
	sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
}

sellPage.clickSave()
WebUI.delay(2)

Robot robot = new Robot()
robot.delay(2000)
robot.keyPress(KeyEvent.VK_ESCAPE)
robot.keyRelease(KeyEvent.VK_ESCAPE)
sellPage.veTrangQuanLy()
sellPage.moDanhSachDonHang()
sellPage.searchProductByMa(maCT)
sellPage.verifyMaCT(maCT)