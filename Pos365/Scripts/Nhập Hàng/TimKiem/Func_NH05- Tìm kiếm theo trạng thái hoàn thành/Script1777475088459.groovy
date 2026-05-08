import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
PurchasePage page = new PurchasePage()
String suffix = new Date().format('HHmmss')
String maCT1 = ma1 + suffix
String maCT2 = ma2 + suffix

WebUI.callTestCase(findTestCase("common/login"), [:])
String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"

page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT1)
page.themSanPhamDayDu(tenHang, soLuong,giaBan)
page.clickSave()

page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT2)
page.themSanPhamDayDu(tenHang, soLuong,giaBan)
page.clickDone()
WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_DongYHoanThanh'))

page.moManHinhNhapHang()
WebUI.click(findTestObject('Nhập hàng/TimKiem/cbox_HoanThanh'))
page.searchProductByMa(maCT1)
page.verifyFirstProductCodeNo(maCT1)

page.searchProductByMa(maCT2)
page.verifyFirstProductCode(maCT2)

