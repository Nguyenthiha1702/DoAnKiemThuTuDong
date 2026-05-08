import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String tenNhom= "RƯỢU"
productPage.clickNhom(tenNhom)
WebUI.delay(1)
WebUI.click(findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien'))
WebUI.delay(2)
productPage.clickFix()
WebUI.delay(1)
def obj = findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/getValueNhom')
WebUI.waitForElementVisible(obj, 10)

String actualValue = WebUI.getText(obj).trim()

assert actualValue == tenNhom : "Lỗi: Giá trị thực tế là '${actualValue}'"