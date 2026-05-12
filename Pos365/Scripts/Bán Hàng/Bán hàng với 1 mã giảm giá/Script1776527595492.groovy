
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject

SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")

WebUI.callTestCase(findTestCase("common/login"), [:])

sellPage.moManHinhBanHang()

for (int i = 1; i <= dataSP.getRowNumbers(); i++) {
	String ma = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)
	WebUI.comment("Thêm SP: " + ma + " | SL: " + soLuong)
	sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
}

double tongDong = sellPage.verifyThanhTienTungDong()
sellPage.verifyTongThanhTien(tongDong)
WebUI.comment(" Nhập mã giảm giá: " + maGiamGia)
sellPage.nhapMaGiamGia(maGiamGia)

TestObject giamGiaObj = findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_TienMaGiam')
WebUI.waitForElementVisible(giamGiaObj, 5)

double tongThanhTien = sellPage.getTongThanhTien()
double tienGiamGia = sellPage.getTienGiamGia()
double tongCongUI = sellPage.getTongCong()

WebUI.comment("Tổng thành tiền: " + tongThanhTien)
WebUI.comment("Tiền giảm giá: " + tienGiamGia)
WebUI.comment("Tổng cộng UI: " + tongCongUI)

double expected = Math.max(tongThanhTien - tienGiamGia, 0)

WebUI.comment("Expected: " + expected)

// ===== VERIFY =====
double diff = Math.abs(expected - tongCongUI)

if (diff > 2) {
	WebUI.takeScreenshot("Loi_Voucher_" + maGiamGia)
	assert false : "Sai tổng cộng | expected=" + expected + " | UI=" + tongCongUI
} else {
	WebUI.comment(" PASS - Voucher hoạt động đúng")
}