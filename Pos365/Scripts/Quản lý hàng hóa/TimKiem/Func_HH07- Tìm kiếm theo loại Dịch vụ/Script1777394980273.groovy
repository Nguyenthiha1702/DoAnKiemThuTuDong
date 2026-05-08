import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String ma = "Hatest17022"
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_DichVu'))
productPage.searchProductByMa(ma)
WebUI.delay(2)
productPage.clickFix()
WebUI.delay(1)
def to = findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/combo_LoaiHang')
WebUI.waitForElementVisible(to, 5, FailureHandling.OPTIONAL)
String actualValue = WebUI.getAttribute(to, 'value')
assert actualValue == "2" : "Lỗi: Giá trị thực tế của loại hàng là '${actualValue}' thay vì 'Dịch vụ'"
WebUI.comment("loại hàng hiển thị đúng là 'Dịch vụ'")