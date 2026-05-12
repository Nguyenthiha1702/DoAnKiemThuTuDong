//import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
//import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
//import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
//import com.kms.katalon.core.model.FailureHandling
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import com.pages.ProductPage
//
//ProductPage productPage = new ProductPage()
//
//WebUI.callTestCase(findTestCase("common/login"), [:])
//
//productPage.navigateToList()
//String maHH = "Hatest1702@@@@"
//
//productPage.searchProductByMa(maHH)
//def to = findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien')
//boolean isVisible = WebUI.waitForElementVisible(to, 5, FailureHandling.OPTIONAL)
//assert !isVisible : " Vẫn hiển thị mã hàng không tồn tại: " + maHH
//WebUI.comment("Không hiển thị mã hàng")


import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage

ProductPage productPage = new ProductPage()

WebUI.callTestCase(findTestCase("common/login"), [:])

productPage.navigateToList()

String maHH = "Hatest1702@@@@"

productPage.searchProductByMa(maHH)

String actual = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien')).trim()
assert !actual.equals(maHH) :
	"Vẫn hiển thị mã hàng không tồn tại: ${maHH}"

WebUI.comment("Không hiển thị mã hàng")