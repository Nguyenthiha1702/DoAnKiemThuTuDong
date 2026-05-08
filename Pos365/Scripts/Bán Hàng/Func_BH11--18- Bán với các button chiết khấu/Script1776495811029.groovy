import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('common/login'), [:])
SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")
sellPage.moManHinhBanHang()

for (int i = 1; i <= dataSP.getRowNumbers(); i++) {

	String maHang = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)
	sellPage.timVaThemSanPhamNhieuDong(maHang, soLuong)
}

sellPage.verifyTongThanhTien(sellPage.verifyThanhTienTungDong())

WebUI.comment("Áp chiết khấu: " + chietKhau + "%")
sellPage.chonChietKhauNhanh(chietKhau)

sellPage.verifyToanBoTien(Double.parseDouble(chietKhau), true)