import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)
ProductPage productPage = new ProductPage()
productPage.navigateToCreate()
String suffix = new Date().format('HHmmss')
String Ma_HH = "maHH"+suffix
productPage.createProduct(Ma_HH, Ten_HH, GiaBan, GiaVon, TonKho, DVT)
productPage.clickSave()
WebUI.delay(2)
productPage.searchProductByMa(Ma_HH)
productPage.verifyProduct(Ten_HH, Ma_HH, GiaBan, GiaVon, TonKho, DVT)
WebUI.comment("thêm mới thành công")
WebUI.takeScreenshot()