import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage

PurchasePage page = new PurchasePage()

String suffix = new Date().format('HHmmss')
String maCT1 = "Today" + suffix
String maCT2 = "NH010526-0004"

WebUI.callTestCase(findTestCase("common/login"), [:])

String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"

page.moManHinhThemHang()

def txtMaCT = findTestObject('Nhập Hàng/ThemMoi/txt_MaCT')

WebUI.waitForElementVisible(txtMaCT, 5)
WebUI.setText(txtMaCT, maCT1)

page.themSanPhamDayDu(tenHang, soLuong, giaBan)
page.clickSave()

page.moManHinhNhapHang()

def chkThangNay = findTestObject('Nhập hàng/TimKiem/cbox_ThangNay')

WebUI.waitForElementClickable(chkThangNay, 5)
WebUI.click(chkThangNay)

page.searchProductByMa(maCT1)
page.verifyFirstProductCode(maCT1)

page.searchProductByMa(maCT2)
page.verifyFirstProductCodeNo(maCT2)