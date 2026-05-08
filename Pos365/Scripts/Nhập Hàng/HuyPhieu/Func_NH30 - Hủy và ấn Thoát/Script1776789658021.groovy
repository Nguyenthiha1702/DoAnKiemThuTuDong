import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
PurchasePage page = new PurchasePage()
WebUI.callTestCase(findTestCase("common/login"), [:])

String suffix = new Date().format('HHmmss')
String maCT = ma + suffix

page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)
String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"
page.themSanPhamDayDu(tenHang, soLuong,giaBan)
page.clickSave()

page.moManHinhNhapHang()
page.searchProductByMa(maCT)
WebUI.delay(1)
page.clickHuy()
page.clickThoatHuy()
WebUI.delay(1)
page.searchProductByMa(maCT)
page.verifyTrangThai("Đang xử lý")