import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.PurchasePage

PurchasePage page = new PurchasePage()

String suffix = new Date().format('HHmmss')
String ma = "CT"
String maNhaCC = "NCC001"

String maCT = ma + suffix
String maNCC = maNhaCC
String maNCCGia = "NCC_FAKE_" + suffix

String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()

def txtMaCT = findTestObject('Nhập Hàng/ThemMoi/txt_MaCT')
WebUI.waitForElementVisible(txtMaCT, 5)
WebUI.setText(txtMaCT, maCT)
page.searchNCCByMa(maNCC)
page.themSanPhamDayDu(tenHang, soLuong, giaBan)
page.clickSave()
page.moManHinhNhapHang()

page.searchProductByMaDT(maNCC)
page.verifyFirstProductCode(maCT)

