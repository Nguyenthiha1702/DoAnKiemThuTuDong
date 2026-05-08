import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.SellPage
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")

String ma = "HH-0061"
String soLuong ="10"
WebUI.callTestCase(findTestCase("common/login"), [:])
sellPage.moManHinhBanHang()
sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
sellPage.clickSave()

TestObject tbLoi = findTestObject('Bán Hàng/TaoDonHang/ERR/err_LoiTonKho')
boolean isVisible = WebUI.waitForElementVisible(tbLoi, 5, FailureHandling.OPTIONAL)
assert isVisible : "Không hiển thị thông báo tồn kho"
WebUI.comment("Hiển thị lỗi tồn kho đúng")