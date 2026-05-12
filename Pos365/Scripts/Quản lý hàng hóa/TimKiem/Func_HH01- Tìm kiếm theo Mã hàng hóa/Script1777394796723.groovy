import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage

ProductPage productPage = new ProductPage()

WebUI.callTestCase(findTestCase("common/login"), [:])
productPage.navigateToList()
String maHH = "Hatest17021"

productPage.searchProductByMa(maHH)
WebUI.delay(2)
productPage.verifyFirstProductCode(maHH)
