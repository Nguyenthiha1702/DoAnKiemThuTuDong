import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.pages.SellPage
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")

String maGiam = "abcskdkdkd"
WebUI.callTestCase(findTestCase("common/login"), [:])
sellPage.moManHinhBanHang()

for (int i = 1; i <= dataSP.getRowNumbers(); i++) {

	String ma = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)
	WebUI.comment(" Thêm SP: " + ma + " | SL: " + soLuong)
	sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
}
sellPage.nhapMaGiamGia(maGiam)
TestObject tbLoi = findTestObject('Bán Hàng/TaoDonHang/ERR/err_LoiMaGiamGia')
boolean isVisible = WebUI.waitForElementVisible(tbLoi, 5, FailureHandling.OPTIONAL)
assert isVisible : "Không hiển thị thông báo lỗi mã giảm giá"
WebUI.comment("Hiển thị lỗi mã giảm giá đúng")
