import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_TonKhoHetHang'))
String tonKhoText = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/row_TonKhoDauTien'))
tonKhoText = tonKhoText.replace(",", "").trim()
int tonKho = Integer.parseInt(tonKhoText)
WebUI.verifyEqual(tonKho, 0)

String ma1 ="Hatest17021"
productPage.searchProductByMa(ma1)
WebUI.delay(1)
def to = findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien')
boolean isVisible = WebUI.waitForElementVisible(to, 1, FailureHandling.OPTIONAL)
assert !isVisible : "Vẫn hiển thị mã hàng không tồn tại: " + ma1

WebUI.comment("Không hiển thị mã hàng")