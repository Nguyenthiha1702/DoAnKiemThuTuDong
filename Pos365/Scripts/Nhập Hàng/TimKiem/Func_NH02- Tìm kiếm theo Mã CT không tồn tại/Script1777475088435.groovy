import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
PurchasePage page = new PurchasePage()
String suffix = new Date().format('HHmmss')
String maCT = ma + suffix
String maCTGia = "maGia" + suffix
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)
String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"
page.themSanPhamDayDu(tenHang, soLuong,giaBan)
page.clickSave()
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
page.verifyFirstProductCode(maCT)
page.searchProductByMa(maCTGia)
page.verifyFirstProductCodeNo(maCTGia)
