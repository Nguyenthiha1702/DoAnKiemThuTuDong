import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String ma1 = "Hatest17021"
String ma3 = "Hatest17023"
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_HangHoa'))
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_Combo'))
productPage.searchProductByMa(ma3)
WebUI.delay(2)
productPage.clickFix()
WebUI.delay(1)
def to1 = findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/combo_LoaiHang')
WebUI.waitForElementVisible(to1, 1, FailureHandling.OPTIONAL)
String actualValue1 = WebUI.getAttribute(to1, 'value')
assert actualValue1 == "3" : "Lỗi: Giá trị thực tế của loại hàng là '${actualValue1}' thay vì 'combo'"
WebUI.comment("loại hàng hiển thị đúng là 'combo'")

productPage.navigateToList()
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_Combo'))
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_HangHoa'))
productPage.searchProductByMa(ma1)
WebUI.delay(2)
productPage.clickFix()
WebUI.delay(1)
def to2 = findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/combo_LoaiHang')
WebUI.waitForElementVisible(to2, 1, FailureHandling.OPTIONAL)
String actualValue2 = WebUI.getAttribute(to2, 'value')
assert actualValue2 == "1" : "Lỗi: Giá trị thực tế của loại hàng là '${actualValue2}' thay vì 'Hàng hóa'"
WebUI.comment("loại hàng hiển thị đúng là 'Hàng hóa'")