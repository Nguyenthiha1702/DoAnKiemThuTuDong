import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
PurchasePage page = new PurchasePage()

WebUI.callTestCase(findTestCase("common/login"), [:])

page.moManHinhNhapHang()

String maDong1 = page.getFirstProductCode()

page.moManHinhThemHang()
page.themSanPhamDayDu("SP001", "2", "10000")

page.clickExit()

WebUI.delay(2)

page.verifyFirstProductCode(maDong1)