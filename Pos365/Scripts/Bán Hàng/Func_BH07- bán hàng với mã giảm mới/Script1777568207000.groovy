// ===== IMPORT =====
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")

String maGiamGia = "GG001"

WebUI.callTestCase(findTestCase("common/login"), [:])

sellPage.moManHinhBanHang()

for (int i = 1; i <= dataSP.getRowNumbers(); i++) {

	String ma = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)

	WebUI.comment(" Thêm SP: " + ma + " | SL: " + soLuong)

	sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
}

sellPage.verifyThanhTienTungDong()

sellPage.nhapMaGiamGia(maGiamGia)

WebUI.delay(2)

sellPage.verifyToanBoTien(0, false)