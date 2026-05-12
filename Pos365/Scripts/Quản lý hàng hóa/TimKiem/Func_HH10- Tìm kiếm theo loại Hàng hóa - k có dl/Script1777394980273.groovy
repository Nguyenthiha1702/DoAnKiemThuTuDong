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
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_HangHoa'))
productPage.searchProductByMa(ma)
String actual = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien')).trim()
assert !actual.equals(ma) :
	"Vẫn hiển thị mã hàng không tồn tại: ${ma}"

WebUI.comment("Không hiển thị mã hàng")