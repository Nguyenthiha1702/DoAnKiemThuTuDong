
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.pages.SellPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
SellPage sellPage = new SellPage()
def dataSP = findTestData("Sell/addManyProduct")
def dataVoucher = findTestData("Sell/addManyVoucher")

double tongVoucherNhap = 0

WebUI.callTestCase(findTestCase("common/login"), [:])
sellPage.moManHinhBanHang()
for (int i = 1; i <= dataSP.getRowNumbers(); i++) {

	String ma = dataSP.getValue("ma", i)
	String soLuong = dataSP.getValue("soLuong", i)
	sellPage.timVaThemSanPhamNhieuDong(ma, soLuong)
}

double tongDong = sellPage.verifyThanhTienTungDong()
sellPage.verifyTongThanhTien(tongDong)

for (int i = 1; i <= dataVoucher.getRowNumbers(); i++) {

	String maVoucher = dataVoucher.getValue("MaGiamGia", i)

	WebUI.comment(" Nhập voucher: " + maVoucher)

	sellPage.nhapMaGiamGia(maVoucher)

	WebUI.delay(1)
	tongVoucherNhap = sellPage.getTienGiamGia()
}
//double tongThanhTien = sellPage.getTongThanhTien()
//double tongCongUI = sellPage.getTongCong()
//
//double expected = Math.max(tongThanhTien - tongVoucherNhap, 0)
//WebUI.comment("Tổng: " + tongThanhTien)
//WebUI.comment("Voucher tổng: " + tongVoucherNhap)
//WebUI.comment("Expected: " + expected)
//WebUI.comment("UI: " + tongCongUI)
//
//double diff = Math.abs(expected - tongCongUI)
//
//if (diff > 2) {
//	WebUI.takeScreenshot("Loi_MultiVoucher")
//	assert false : "Sai tổng cộng multi voucher | diff=" + diff
//} else {
//	WebUI.comment("PASS multi voucher")
//}

sellPage.verifyToanBoTien(0, false)