import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String tenNCC = "Nhà cung cấp huy thanh"
productPage.searchProductByNCC(tenNCC)
WebUI.click(findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien'))
WebUI.delay(2)
productPage.clickFix()
WebUI.delay(2)
WebUI.click(findTestObject('Hàng hóa/ThemMoi/NhaCungCap/tab_NhaCungCap'))
def toNCC = findTestObject('Hàng hóa/ThemMoi/NhaCungCap/txt_TenNCC')
WebUI.waitForElementVisible(toNCC, 5)

String actualNCC = WebUI.getText(toNCC)?.trim()
assert actualNCC?.toLowerCase()?.contains(tenNCC.toLowerCase()) :"Sai tên NCC "
WebUI.comment("Tên NCC đúng")