
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")

WebUI.callTestCase(findTestCase("common/login"), [:])

sellPage.moManHinhBanHang()

for (int i = 1; i <= dataSP.getRowNumbers(); i++) {

	String ma = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)

	sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
	WebUI.delay(1)
}
WebUI.comment("Nhập chiết khấu tiền: " + tienChietKhau)

sellPage.nhapChietKhauTien(tienChietKhau)

sellPage.verifyToanBoTien(Double.parseDouble(tienChietKhau), false )

WebUI.comment(" PAS Chiết khấu tiền")