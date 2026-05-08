import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.ProductPage
import com.pages.SellPage

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.awt.Robot
import java.awt.event.KeyEvent

// ===== LOGIN =====
WebUI.callTestCase(findTestCase("common/login"), [:])

String ma = "add01205622"
int soLuong = 1

SellPage sellPage = new SellPage()
ProductPage productPage = new ProductPage()

// ===== LẤY TỒN TRƯỚC =====
productPage.navigateToList()
productPage.searchProductByMa(ma)
int tonBefore = productPage.getTonKho()

// ===== BÁN HÀNG =====
sellPage.moManHinhBanHang()
sellPage.timVaThemSanPhamNhieuDong(ma, String.valueOf(soLuong))
sellPage.clickSave()

// ===== ĐÓNG PRINT DIALOG =====
Robot robot = new Robot()
robot.delay(2000)
robot.keyPress(KeyEvent.VK_ESCAPE)
robot.keyRelease(KeyEvent.VK_ESCAPE)

// ===== CHỜ HỆ THỐNG UPDATE =====
WebUI.delay(2)

WebUI.openBrowser('')
WebUI.navigateToUrl("https://hvtester.pos365.vn/Signin")
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
productPage.searchProductByMa(ma)
int tonAfter = productPage.getTonKho()

// ===== VERIFY =====
assert tonAfter == tonBefore - soLuong :
	"❌ Sai tồn kho | Before: ${tonBefore} | After: ${tonAfter} | Sold: ${soLuong}"