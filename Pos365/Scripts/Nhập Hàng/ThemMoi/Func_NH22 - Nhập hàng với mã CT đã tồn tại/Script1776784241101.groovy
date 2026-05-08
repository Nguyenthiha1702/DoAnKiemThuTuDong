import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
PurchasePage page = new PurchasePage()

WebUI.callTestCase(findTestCase("common/login"), [:])

page.moManHinhThemHang()
String maCT = "HoanThanh223629"
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)
page.themSanPhamDayDu("SP001", "2", "10000")
page.clickSave()
page.checkErrMaCT()