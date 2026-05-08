import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage

PurchasePage page = new PurchasePage()

String suffix = new Date().format('HHmmss')
String maCT1 = "Today" + suffix
String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"
String ngayNhap = "20/03/2026 15:09"

WebUI.callTestCase(findTestCase("common/login"), [:])

page.moManHinhThemHang()

def txtMaCT = findTestObject('Nhập Hàng/ThemMoi/txt_MaCT')
def dateNgayNhap = findTestObject('Nhập Hàng/ThemMoi/date_NgayNhap')

// ✅ FIX 1: wait trước khi setText
WebUI.waitForElementVisible(txtMaCT, 5)
WebUI.setText(txtMaCT, maCT1)

// ✅ FIX 2: date cũng phải wait (rất hay gây lỗi)
WebUI.waitForElementVisible(dateNgayNhap, 5)
WebUI.clearText(dateNgayNhap)
WebUI.setText(dateNgayNhap, ngayNhap)

page.themSanPhamDayDu(tenHang, soLuong, giaBan)
page.clickSave()

page.moManHinhNhapHang()

def chkThangTruoc = findTestObject('Nhập hàng/TimKiem/cbox_ThangTruoc')

// ✅ FIX 3: bỏ delay → dùng wait
WebUI.waitForElementClickable(chkThangTruoc, 5)
WebUI.click(chkThangTruoc)

// --- search + verify
page.searchProductByMa(maCT1)
page.verifyFirstProductCode(maCT1)