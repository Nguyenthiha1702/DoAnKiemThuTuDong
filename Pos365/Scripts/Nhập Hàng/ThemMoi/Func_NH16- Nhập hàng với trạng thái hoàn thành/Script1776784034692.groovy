import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
PurchasePage page = new PurchasePage()
String suffix = new Date().format('HHmmss')
String maCT = maCT + suffix
boolean isHoanThanh = true

WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)

String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"
page.themSanPhamDayDu(tenHang, soLuong,giaBan)
if (isHoanThanh) {
	WebUI.comment(" Click Hoàn thành")
	page.clickDone()
	WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_DongYHoanThanh'))
} else {
	WebUI.comment("Click Lưu")
	page.clickSave()
}
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
WebUI.delay(2)

String trangthai = isHoanThanh ? "Hoàn thành" : "Đang xử lý"

page.verifyTrangThai(trangthai)

WebUI.comment("pass trạng thái hoàn thành")

