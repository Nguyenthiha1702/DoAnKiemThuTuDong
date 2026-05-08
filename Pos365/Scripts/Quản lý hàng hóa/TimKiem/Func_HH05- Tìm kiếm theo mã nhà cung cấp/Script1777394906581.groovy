import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String maNCC = "DH01"
productPage.searchProductByNCC(maNCC)
WebUI.click(findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien'))
WebUI.delay(2)
productPage.clickFix()
WebUI.delay(2)
WebUI.click(findTestObject('Hàng hóa/ThemMoi/NhaCungCap/tab_NhaCungCap'))
def toNCC = findTestObject('Hàng hóa/ThemMoi/NhaCungCap/txt_MaNCC')
WebUI.waitForElementVisible(toNCC, 5)

String actualNCC = WebUI.getText(toNCC)?.trim()
assert actualNCC?.toLowerCase()?.contains(maNCC.toLowerCase()) :"Sai mã NCC "
WebUI.comment("MÃ NCC đúng")