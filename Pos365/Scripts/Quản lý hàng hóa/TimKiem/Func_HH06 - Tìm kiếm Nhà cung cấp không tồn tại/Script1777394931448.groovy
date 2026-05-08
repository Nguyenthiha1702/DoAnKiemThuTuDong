import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.pages.ProductPage
ProductPage productPage = new ProductPage()
WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String maNCC = "DH01aaaaa@@@@@"
productPage.searchProductByNCC(maNCC)
WebUI.delay(2)
def to = findTestObject('Hàng hóa/ThemMoi/NhaCungCap/note_KhongXacDinh')
boolean isVisible = WebUI.waitForElementVisible(to, 5, FailureHandling.OPTIONAL)
assert !isVisible : "Vẫn hiển thị mã hàng không tồn tại: " + maNCC
WebUI.comment("Không hiển thị mã hàng")